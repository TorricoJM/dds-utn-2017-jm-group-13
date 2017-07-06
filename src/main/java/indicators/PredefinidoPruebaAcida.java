package indicators;

import repositories.RepositorioEmpresas;

public class PredefinidoPruebaAcida extends Indicador{

	private static PredefinidoPruebaAcida instance;
	
	public static PredefinidoPruebaAcida getInstance() {
		if (instance == null) {
			instance = new PredefinidoPruebaAcida();
		}
		return instance;
	}
	
	@Override
	public String getNombre() {
		return "Prueba Acida";
	}

	@Override
	public String getOperacion() {
		return "(Activo corriente + Inventarios)/Pasivo corriente";
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada, String periodoEvaluado){
		final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Activo corriente", empresaEvaluada, periodoEvaluado);
		final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Inventarios", empresaEvaluada, periodoEvaluado);
		final double val3 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Pasivo corriente", empresaEvaluada, periodoEvaluado);
		final double val4 = val1 + val2;
		return val4 / val3;
	}

}
