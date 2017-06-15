package indicators;

import repositories.RepositorioEmpresas;

public class PredefinidoROI extends DataIndicador{

	private static PredefinidoROI instance;
	
	private PredefinidoROI(String nombre, String operacion) {
		super(nombre, operacion);
		// TODO Auto-generated constructor stub
	}
	
	public static PredefinidoROI getInstance() {
		if (instance == null) {
			instance = new PredefinidoROI("ROI", "(Ingresos - Inversión)/Inversión*100");
		}
		return instance;
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada,String periodoEvaluado){
		final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Ingresos", empresaEvaluada, periodoEvaluado);
		final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Inversion", empresaEvaluada, periodoEvaluado);
		final double val3 = val1 - val2;
		return val3 / val2 * 100;
	}

}