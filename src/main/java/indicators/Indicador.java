package indicators;

import org.uqbar.commons.utils.Observable;

@Observable
public abstract class Indicador {

	public abstract String getNombre();
	
	public abstract String getOperacion();
	
	public abstract double evaluateEn(String empresaEvaluada, String periodoEvaluado);
}