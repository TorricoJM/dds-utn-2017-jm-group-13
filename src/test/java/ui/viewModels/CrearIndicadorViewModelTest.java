package ui.viewModels;

import model.Exception;
import repositories.RepositorioIndicadores;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import indicators.DataIndicador;

public class CrearIndicadorViewModelTest {

	public CrearIndicadorViewModel indicadorViewModel;

	@Test
	public void crearIndicadorDaCorrectoTest() {

		indicadorViewModel = new CrearIndicadorViewModel();

		indicadorViewModel.setNombreIndicador("Indicador Nombre Test");
		indicadorViewModel.setIndicador("1000 + 500");
		
		indicadorViewModel.crearIndicador();
		
		DataIndicador indicador = RepositorioIndicadores.getInstance().obtenerIndicadorDesdeNombre("Indicador Nombre Test");
		assertTrue(indicador.getOperacion().equals(indicadorViewModel.getIndicador()));

	}
	
	@Test (expected = Exception.class)
	public void crearIndicadorSinNombreDaIncorrectoTest() {
		indicadorViewModel = new CrearIndicadorViewModel();

		indicadorViewModel.setIndicador("1000 + 500");
		
		indicadorViewModel.crearIndicador();
	}
	
	@Test (expected = Exception.class)
	public void crearIndicadorSinOperacionDaIncorrectoTest() {
		indicadorViewModel = new CrearIndicadorViewModel();

		indicadorViewModel.setNombreIndicador("Indicador Nombre Test");
		indicadorViewModel.setIndicador("");
		
		indicadorViewModel.crearIndicador();
	}
	
	@After
	public void finalizar() {
		RepositorioIndicadores.deleteInstance();
	}
}
