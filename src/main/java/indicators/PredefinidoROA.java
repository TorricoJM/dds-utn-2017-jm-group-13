package indicators;

import javax.persistence.Entity;
import javax.persistence.NoResultException;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioEmpresas;

@Entity
public class PredefinidoROA extends Indicador {

	private String nombre = "ROA";
	
	public PredefinidoROA() {
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
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
		catch (NoResultException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}
}
