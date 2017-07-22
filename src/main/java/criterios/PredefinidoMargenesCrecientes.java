package criterios;

import criterios.modificador.Modificador;
import criterios.modificador.Normal;
import indicators.Indicador;
import indicators.PredefinidoROA;

public class PredefinidoMargenesCrecientes extends CriterioTaxativo{

	private PredefinidoMargenesCrecientes(String nombre, OperadorComparacion operador, Indicador indicador,
			Modificador modificador, double valor) {
		super(nombre, operador, indicador, modificador, valor);
		// TODO Auto-generated constructor stub
	}

	private static PredefinidoMargenesCrecientes instance;
	
	public static PredefinidoMargenesCrecientes getInstance() {
		if (instance == null) {
			instance = new PredefinidoMargenesCrecientes("Márgenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1);
		}
		return instance;
	}
}
