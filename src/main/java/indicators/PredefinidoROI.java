package indicators;

import model.parser.ErrorEvaluacionException;
import repositories.repoArchivos.RepositorioEmpresas;

public class PredefinidoROI extends Indicador {

	private static PredefinidoROI instance;
	
	public static PredefinidoROI getInstance() {
		if (instance == null) {
			instance = new PredefinidoROI();
		}
		return instance;
	}
	
	@Override
	public String getNombre() {
		return "ROI";
	}

	@Override
	public String getOperacion() {
		return "(Ingresos - Inversion)/Inversion*100";
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		try {
			final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Ingresos", empresaEvaluada, periodoEvaluado);
			final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Inversion", empresaEvaluada, periodoEvaluado);
			final double val3 = val1 - val2;
			return val3 / val2 * 100;
		}
		catch (ErrorEvaluacionException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}

}