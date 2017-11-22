package indicators;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

@Entity
@Observable
public abstract class Indicador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public abstract String getNombre();

	public abstract String getOperacion();
	
	public abstract List<IndicadorConResultado> getPrecalculados();
	
	public abstract void obtenerPrecalculados();

	public abstract double evaluateEn(String empresaEvaluada, String periodoEvaluado);
}