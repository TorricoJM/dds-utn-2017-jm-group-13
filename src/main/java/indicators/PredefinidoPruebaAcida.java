package indicators;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NoResultException;

import model.parser.ErrorEvaluacionException;
import repositories.RepositorioEmpresas;

@Entity
public class PredefinidoPruebaAcida extends Indicador{

	private String nombre = "Prueba Acida";
	
	public PredefinidoPruebaAcida() {
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getOperacion() {
		return "(Activo corriente + Inventarios)/Pasivo corriente";
	}
	
	@Override
	public double evaluateEn(String empresaEvaluada, String periodoEvaluado){
		try {	
			final double val1 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Activo corriente", empresaEvaluada, periodoEvaluado);
			final double val2 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Inventarios", empresaEvaluada, periodoEvaluado);
			final double val3 = RepositorioEmpresas.getInstance().obtenerValorDeCuentaDeEmpresaEnPeriodo("Pasivo corriente", empresaEvaluada, periodoEvaluado);
			final double val4 = val1 + val2;
			return val4 / val3;
		}
		catch (NoResultException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		}
	}

	@Override
	public List<IndicadorConResultado> getPrecalculados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void obtenerPrecalculados() {
		// TODO Auto-generated method stub
		
	}

}
