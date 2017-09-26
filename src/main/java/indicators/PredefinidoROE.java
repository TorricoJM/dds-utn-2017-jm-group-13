package indicators;

import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioEmpresas;

@Observable
@Entity
public class PredefinidoROE extends Indicador {

	private String nombre = "ROE";
	
	public PredefinidoROE() {
	}
	
	@Override
	public String getNombre() {
		return nombre;
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
