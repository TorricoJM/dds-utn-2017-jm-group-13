package server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import indicators.Indicador;
import repositories.RepositorioIndicadores;

public class SeedPrecalculados{
	
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	EntityTransaction tx = entityManager.getTransaction();
	
	public SeedPrecalculados removerResultadosActuales() {
		tx.begin();
		entityManager.createQuery("DELETE FROM IndicadorConResultado").executeUpdate();
		tx.commit();
		return this;
	}
	
	public void precalcular() {
		tx.begin();
		List<Indicador> indicadores = RepositorioIndicadores.getInstance().getElementos();
		indicadores.forEach(indicador->indicador.obtenerPrecalculados());
		RepositorioIndicadores.getInstance().agregarMuchos(indicadores);
		tx.commit();
	}
	
	
}