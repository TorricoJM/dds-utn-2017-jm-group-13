package repositories;

import java.util.List;

import javax.persistence.NoResultException;
import criterios.CriterioTaxativo;

public class RepositorioCriteriosTaxativos extends Repositorio<CriterioTaxativo> {

	private static RepositorioCriteriosTaxativos instance;

	public static RepositorioCriteriosTaxativos getInstance() {
		if (instance == null) {
			instance = new RepositorioCriteriosTaxativos();
			//instance.agregar(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1));
			//instance.agregar(new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()));
			//instance.agregar(new CriterioComparativo("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance()));
		}
		return instance;
	}

	private RepositorioCriteriosTaxativos() {};
	
	public static void deleteInstance() {
		instance = null;
	}

	public boolean tieneCriterio(String nombre) {
		try {
		CriterioTaxativo criterio = this.obtenerCriterioDesdeNombre(nombre);
		return criterio!=null;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public CriterioTaxativo obtenerCriterioDesdeNombre(String nombre) {
		String query = "Select c from CriterioTaxativo c where c.nombre=:name";
		CriterioTaxativo criterio = this.entityManager.createQuery(query, CriterioTaxativo.class).setParameter("name", nombre).getSingleResult();
		return criterio;
	}
	
	public List<CriterioTaxativo> getElementos(){
		return this.entityManager.createQuery("from CriterioTaxativo", CriterioTaxativo.class).getResultList();
	}
}