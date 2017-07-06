package indicators;

import org.uqbar.commons.utils.Observable;

import model.parser.ErrorEvaluacionException;
import model.parser.objetosParser.IndicadorParser;

@Observable
public class DataIndicador extends Indicador {

	private String nombre;
	private String operacion;

	public DataIndicador(String nombre, String operacion) {
		this.nombre = nombre;
		this.operacion = operacion;
	}

	// -----------------------------------------------------GETTERS AND SETTERS
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
	// ---------------------------------------------------/GETTERS AND SETTERS

	@Override
	public double evaluateEn(String empresaEvaluada, String periodoEvaluado) {
		try {
		return new IndicadorParser(this.nombre, this.operacion).operar(empresaEvaluada, periodoEvaluado);
		} catch (NullPointerException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}
}