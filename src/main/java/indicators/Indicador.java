package indicators;

import org.uqbar.commons.utils.Observable;

import model.parser.ObjetosParserStrategy;
import model.parser.TipoParserStrategy;

@Observable
public class Indicador {

	private String nombre;
	private String operacion;
	private TipoParserStrategy parserStrategy;

	public Indicador(String nombre, String operacion) {
		this.parserStrategy = new ObjetosParserStrategy();
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
		return parserStrategy.evaluarIndicador(this, empresaEvaluada, periodoEvaluado);
	}
}