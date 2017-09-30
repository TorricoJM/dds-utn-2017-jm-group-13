package indicators;

import javax.persistence.Entity;
import javax.persistence.NoResultException;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioEmpresas;


@Entity
public class PredefinidoROI extends Indicador {

	private String nombre = "ROI";
	
	public PredefinidoROI() {
	}
	
	@Override
	public String getNombre() {
		return nombre;
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
		catch (NoResultException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}

}