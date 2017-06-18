package indicators;

import org.uqbar.commons.utils.Observable;

import model.parser.IdentificadorInvalidoException;
import model.parser.objetosParser.IndicadorParser;

@Observable
public class DataIndicador {

	private String nombre;
	private String operacion;

	public DataIndicador(String nombre, String operacion) {
		this.nombre = nombre;
		this.operacion = operacion;
	}

	// -----------------------------------------------------GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}

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

	public double evaluateEn(String empresaEvaluada, String periodoEvaluado) {
		try {
		return new IndicadorParser(this.nombre, this.operacion).operar(empresaEvaluada, periodoEvaluado);
		} catch (NullPointerException e) {
			throw new IdentificadorInvalidoException("Elegir empresa y periodo a evaluar");
		}
	}
}