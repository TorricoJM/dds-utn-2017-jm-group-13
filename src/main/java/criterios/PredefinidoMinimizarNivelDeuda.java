package criterios;

import indicators.Indicador;
import indicators.PredefinidoROA;

public class PredefinidoMinimizarNivelDeuda extends CriterioComparativo{

	private PredefinidoMinimizarNivelDeuda(String nombre, OperadorComparacion operador, Indicador indicador) {
		super(nombre, operador, indicador);
		// TODO Auto-generated constructor stub
	}

	private static PredefinidoMinimizarNivelDeuda instance;
	
	public static PredefinidoMinimizarNivelDeuda getInstance() {
		if (instance == null) {
			instance = new PredefinidoMinimizarNivelDeuda("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance());
		}
		return instance;
	}
}
