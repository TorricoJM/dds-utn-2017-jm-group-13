package db;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import indicators.DataIndicador;
import indicators.Indicador;
import repositories.reposDB.RepositorioIndicadoresDB;

public class RepositoriosTest {
	
	public DataIndicador indicador = new DataIndicador("ebitdamasmil","ebitda+1000");
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	EntityTransaction tx = em.getTransaction();
	
	@Test
	public void buscarPorAlgoQueNoSeaID() {

		tx.begin();
		em.persist(indicador);
		Indicador i1 = new RepositorioIndicadoresDB().obtenerIndicadorDesdeNombre(indicador.getNombre());
		assertTrue(indicador.getNombre().equals(i1.getNombre()));
	}
	
	@Test
	public void tieneIndicador() {
		assertTrue(!new RepositorioIndicadoresDB().tieneIndicador(indicador.getNombre()));
	}
	
	@After
	public void tearDown() {
		em.clear();
	}
}