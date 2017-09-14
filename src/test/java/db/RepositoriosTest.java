package db;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import indicators.DataIndicador;
import indicators.Indicador;
import model.Empresa;
import model.LineaEmpresa;
import repositories.reposDB.RepositorioEmpresasDB;
import repositories.reposDB.RepositorioIndicadoresDB;

public class RepositoriosTest extends AbstractPersistenceTest {
	
	public DataIndicador indicador = new DataIndicador("ebitdamasmil","ebitda+1000");
	EntityManager em = PerThreadEntityManagers.getEntityManager();
	
	
	LineaEmpresa lineaEmpresa1 = new LineaEmpresa();
	Empresa empresa1;
	
	@Before
	public void setUp() {
		lineaEmpresa1.setNombre("empresaTest");
		lineaEmpresa1.setPeriodo("2017");
		lineaEmpresa1.setCuenta("ebitda");
		lineaEmpresa1.setValor("1025.55");
		
		empresa1 = new Empresa(lineaEmpresa1.getNombre());
		empresa1.cargarOModificarCuentaParaUna(lineaEmpresa1);
		
	}
	
	@Test
	public void buscarIndicadorPorNombre() {
		em.persist(indicador);
		Indicador i1 = new RepositorioIndicadoresDB().obtenerIndicadorDesdeNombre(indicador.getNombre());
		assertTrue(indicador.getNombre().equals(i1.getNombre()));
	}
	
	@Test
	public void tieneIndicador() {
		assertTrue(!new RepositorioIndicadoresDB().tieneIndicador(indicador.getNombre()));
	}
	
	@Test
	public void buscarEmpresaPorNombre() {
		em.persist(empresa1);
		Empresa empresaTraidaDeLaDB = new RepositorioEmpresasDB()
				.obtenerEmpresaDesdeNombre(empresa1.getNombre());
		assertTrue(empresa1.getNombre().equals(empresaTraidaDeLaDB.getNombre()));
	}
	
	@Test
	public void obtenerValorDeCuentaDeEmpresaEnPeriodo() {
		em.persist(empresa1);
		Double resultado = new RepositorioEmpresasDB()
				.obtenerValorDeCuentaDeEmpresaEnPeriodo("ebitda", "empresaTest", "2017");
		
		assertTrue(resultado.equals(new Double(1025.55)));
	}
	
	@After
	public void tearDown() {
	}

	@Override
	public EntityManager entityManager() {
		return PerThreadEntityManagers.getEntityManager();
	}
}