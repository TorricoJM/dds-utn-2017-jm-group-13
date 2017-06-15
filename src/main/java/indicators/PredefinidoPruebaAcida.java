package indicators;

import repositories.RepositorioEmpresas;

public class PredefinidoPruebaAcida extends DataIndicador{

	private static PredefinidoPruebaAcida instance;
	
	private PredefinidoPruebaAcida(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}
	
	public static PredefinidoPruebaAcida getInstance() {
		if (instance == null) {
			instance = new PredefinidoPruebaAcida("Prueba Acida", "(Activo corriente–Inventarios)/Pasivo corriente");
		}
		return instance;
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada, String periodoEvaluado){
		final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Activo corriente", empresaEvaluada, periodoEvaluado);
		final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Inventarios", empresaEvaluada, periodoEvaluado);
		final double val3 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Pasivo corriente", empresaEvaluada, periodoEvaluado);
		final double val4 = val1 - val2;
		return val4 / val3;
	}

}
