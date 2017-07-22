package criterios;

import indicators.Indicador;
import indicators.PredefinidoROA;

public class PredefinidoMaximizarROE extends CriterioComparativo{

	private PredefinidoMaximizarROE(String nombre, OperadorComparacion operador, Indicador indicador) {
		super(nombre, operador, indicador);
		// TODO Auto-generated constructor stub
	}

	private static PredefinidoMaximizarROE instance;
	
	public static PredefinidoMaximizarROE getInstance() {
		if (instance == null) {
			instance = new PredefinidoMaximizarROE("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance());
		}
		return instance;
	}
}
