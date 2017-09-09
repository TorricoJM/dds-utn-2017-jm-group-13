package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import criterios.Criterio;
import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Normal;
import criterios.modificador.Promedio;
import criterios.modificador.Sumatoria;
import imports.ImportadorDeEmpresasCSV;
import indicators.DataIndicador;
import indicators.Indicador;
import repositories.repoArchivos.RepositorioEmpresas;
import repositories.repoArchivos.RepositorioIndicadores;

public class CriterioTaxativoTest {

	public Indicador indicador1 = new DataIndicador("i1", "ebitda*10");
	public Criterio taxativo = new CriterioTaxativo("tax1", OperadorComparacion.MAYOR, indicador1, new Normal(), 500);
	public Criterio taxativo2 = new CriterioTaxativo("tax2", OperadorComparacion.MAYOR, indicador1, new Sumatoria(),
			2500);
	public Criterio taxativo3 = new CriterioTaxativo("tax3", OperadorComparacion.MENOR, indicador1, new Promedio(),
			750);
	public List<String> periodos = new LinkedList<>();

	@Before
	public void setUp() {
		new ImportadorDeEmpresasCSV("empresasTest.csv").importar();
		periodos.add("2015");
		periodos.add("2016");
		periodos.add("2017");
	}

	@Test
	public void seVerificaQueUnaEmpresaCumplaNormalMayorA500() {
		assertTrue(taxativo.verificarParaUna(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa2"),periodos));
	}
	
	@Test
	public void seVerificaQueUnaEmpresaNoCumplaNormalMayorA500() {
		assertTrue(!taxativo.verificarParaUna(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa1"),periodos));
	}
	
	@Test
	public void seVerificaQueUnaEmpresaCumplaSumatoriaMayorA2500() {
		assertTrue(taxativo2.verificarParaUna(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa3"), periodos));
	}
	
	@Test
	public void seVerificaQueUnaEmpresaNoCumplaSumatoriaMayorA2500() {
		assertTrue(!taxativo2.verificarParaUna(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa1"), periodos));
	}
	
	@Test
	public void metodoInherenteAOtroCriterioRetornaNull() {
		assertNull(taxativo.posicionLuegoDeAplicarDe(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa1"), RepositorioEmpresas.getInstance()
				.getElementos(), periodos));
	}

	@Test
	public void seVerificaQueUnaEmpresaCumplaPromedioMenorA750() {
		assertTrue(taxativo3.verificarParaUna(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa1"), periodos));
	}
	
	@Test
	public void seVerificaQueUnaEmpresaNoCumplaPromedioMenorA750() {
		assertTrue(!taxativo3.verificarParaUna(RepositorioEmpresas.getInstance()
				.obtenerEmpresaDesdeNombre("empresa2"), periodos));
	}

	@After
	public void tearDown() {
		RepositorioIndicadores.deleteInstance();
		RepositorioEmpresas.deleteInstance();
	}
}