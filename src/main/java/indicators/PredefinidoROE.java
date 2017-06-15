package indicators;

import repositories.RepositorioEmpresas;

public class PredefinidoROE extends DataIndicador{

	private static PredefinidoROE instance;
	
	private PredefinidoROE(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}
	
	public static PredefinidoROE getInstance() {
		if (instance == null) {
			instance = new PredefinidoROE("ROE", "Utilidad Neta/Patrimonio Total");
		}
		return instance;
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Utilidad Neta", empresaEvaluada, periodoEvaluado);
		final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Patrimonio Total", empresaEvaluada, periodoEvaluado);
		return val1 / val2;
	}
}
