package ui.viewModels;

import model.Exception;
import repositories.repoArchivos.RepositorioIndicadores;
import repositories.reposDB.RepositorioIndicadoresDB;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import indicators.Indicador;

public class CrearIndicadorViewModelTest extends AbstractPersistenceTest{

	public CrearIndicadorViewModel indicadorViewModel;

	@Test
	public void crearIndicadorDaCorrectoTest() {

		indicadorViewModel = new CrearIndicadorViewModel();

		indicadorViewModel.setNombreIndicador("Indicador Nombre Test");
		indicadorViewModel.setOperacion("1000 + 500");
		
		indicadorViewModel.crearIndicador();
		
		Indicador indicador = new RepositorioIndicadoresDB().obtenerIndicadorDesdeNombre("Indicador Nombre Test");
		
		assertTrue(indicador.getOperacion().equals(indicadorViewModel.getOperacion()));

	}
	
	@Test (expected = Exception.class)
	public void crearIndicadorSinNombreDaIncorrectoTest() {
		indicadorViewModel = new CrearIndicadorViewModel();

		indicadorViewModel.setOperacion("1000 + 500");

		
		indicadorViewModel.crearIndicador();
	}
	
	@Test (expected = Exception.class)
	public void crearIndicadorSinOperacionDaIncorrectoTest() {
		indicadorViewModel = new CrearIndicadorViewModel();

		indicadorViewModel.setNombreIndicador("Indicador Nombre Test");
		indicadorViewModel.setOperacion("");
		
		indicadorViewModel.crearIndicador();
	}
	
	@After
	public void finalizar() {
		RepositorioIndicadores.deleteInstance();
	}

	@Override
	public EntityManager entityManager() {
		
		return PerThreadEntityManagers.getEntityManager();
	}

}
