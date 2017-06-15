package indicators;

import repositories.RepositorioEmpresas;

public class PredefinidoROA extends DataIndicador {

	private static PredefinidoROA instance;
	
	private PredefinidoROA(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}

	public static PredefinidoROA getInstance() {
		if (instance == null) {
			instance = new PredefinidoROA("ROA", "Utilidad Neta/Activo Total");
		}
		return instance;
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Utilidad Neta", empresaEvaluada, periodoEvaluado);
		final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Activo Total", empresaEvaluada, periodoEvaluado);
		return val1 / val2;
	}
}
