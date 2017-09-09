package indicators;

import org.uqbar.commons.utils.Observable;

import model.parser.ErrorEvaluacionException;
import repositories.repoArchivos.RepositorioEmpresas;

@Observable
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
		try {
			final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Utilidad Neta", empresaEvaluada, periodoEvaluado);
			final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Patrimonio Total", empresaEvaluada, periodoEvaluado);
			return val1 / val2;
		}
		catch (ErrorEvaluacionException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}
}
