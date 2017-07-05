package indicators;

import repositories.RepositorioEmpresas;

public class PredefinidoROE extends Indicador {

	private static PredefinidoROE instance;
	
	public static PredefinidoROE getInstance() {
		if (instance == null) {
			instance = new PredefinidoROE();
		}
		return instance;
	}
	
	@Override
	public String getNombre() {
		return "ROE";
	}

	@Override
	public String getOperacion() {
		return "Utilidad Neta/Patrimonio Total";
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Utilidad Neta", empresaEvaluada, periodoEvaluado);
		final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Patrimonio Total", empresaEvaluada, periodoEvaluado);
		return val1 / val2;
	}
}
