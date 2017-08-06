package repositories;

import java.util.LinkedList;
import java.util.List;

import criterios.Criterio;
import criterios.CriterioComparativo;
import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Normal;
import indicators.PredefinidoROA;


public class RepositorioCriterios {

	private static RepositorioCriterios instance;

	public static RepositorioCriterios getInstance() {
		if (instance == null) {
			instance = new RepositorioCriterios();
			instance.agregar(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1));
			instance.agregar(new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()));
			instance.agregar(new CriterioComparativo("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance()));
		}
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	private List<Criterio> criterios = new LinkedList<>();

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void agregarCriterios(List<Criterio> nuevosCriterios) {
		this.getCriterios().addAll(nuevosCriterios);
	}

	public void agregar(Criterio criterio) {
		this.getCriterios().add(criterio);
	}

	public boolean tieneCriterio(String nombre) {
		return this.getCriterios().stream()
				.anyMatch(criterio -> criterio.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}
}