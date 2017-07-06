package indicators;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioEmpresas;

public class PredefinidoROA extends Indicador {

	private static PredefinidoROA instance;
	
	public static PredefinidoROA getInstance() {
		if (instance == null) {
			instance = new PredefinidoROA();
		}
		return instance;
	}
	
	@Override
	public String getNombre() {
		return "ROA";
	}

	@Override
	public String getOperacion() {
		return "Utilidad Neta/Activo Total";
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		try {
			final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Utilidad Neta", empresaEvaluada, periodoEvaluado);
			final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Activo Total", empresaEvaluada, periodoEvaluado);
			return val1 / val2;
		}
		catch (NullPointerException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}
}
