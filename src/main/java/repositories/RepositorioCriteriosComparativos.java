package repositories;

import java.util.List;

import javax.persistence.NoResultException;

import criterios.CriterioComparativo;

public class RepositorioCriteriosComparativos extends Repositorio<CriterioComparativo>{
	private static RepositorioCriteriosComparativos instance;

	public static RepositorioCriteriosComparativos getInstance() {
		if (instance == null) {
			instance = new RepositorioCriteriosComparativos();
			//instance.agregar(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR, PredefinidoROA.getInstance(), new Normal(), 1));
			//instance.agregar(new CriterioComparativo("Maximizar ROE", OperadorComparacion.MAYOR, PredefinidoROA.getInstance()));
			//instance.agregar(new CriterioComparativo("Minimizar el nivel de deuda", OperadorComparacion.MENOR, PredefinidoROA.getInstance()));
		}
		return instance;
	}

	private RepositorioCriteriosComparativos() {};
	
	public static void deleteInstance() {
		instance = null;
	}

	public boolean tieneCriterio(String nombre) {
		try {
			CriterioComparativo criterio = this.obtenerCriterioDesdeNombre(nombre);
		return criterio!=null;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public CriterioComparativo obtenerCriterioDesdeNombre(String nombre) {
		String query = "Select c from Criterio where c.nombre=:name";
		CriterioComparativo criterio = this.entityManager.createQuery(query, CriterioComparativo.class).setParameter("name", nombre).getSingleResult();
		return criterio;
	}
	
	public List<CriterioComparativo> getElementos(){
		return this.entityManager.createQuery("from Criterio", CriterioComparativo.class).getResultList();
	}
}