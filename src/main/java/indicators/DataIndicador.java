package indicators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

import model.parser.ErrorEvaluacionException;
import model.parser.objetosParser.IndicadorParser;

@Entity
@Observable
public class DataIndicador extends Indicador {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String operacion;

	public DataIndicador(){
	}
	
	public DataIndicador(String nombre, String operacion) {
		this.nombre = nombre;
		this.operacion = operacion;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getOperacion() {
		return operacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	@Override
	public double evaluateEn(String empresaEvaluada, String periodoEvaluado) {
		try {
			return new IndicadorParser(this.nombre, this.operacion).operar(empresaEvaluada, periodoEvaluado);
		} catch (ErrorEvaluacionException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		} catch (NullPointerException e) {
			throw new ErrorEvaluacionException(e.getMessage());
		}
	}
}