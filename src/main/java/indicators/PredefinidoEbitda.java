package indicators;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioEmpresas;

public class PredefinidoEbitda extends Indicador {

	private static PredefinidoEbitda instance;
	
	public static PredefinidoEbitda getInstance() {
		if (instance == null) {
			instance = new PredefinidoEbitda();
		}
		return instance;
	}
	
	@Override
	public String getNombre() {
		return "Ebitda";
	}

	@Override
	public String getOperacion() {
		return "Ebitda";
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		try {
			final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("ebitda", empresaEvaluada, periodoEvaluado);
			return val1;
		}
		catch (ErrorEvaluacionException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}
}
