package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import criterios.Criterio;
import criterios.CriterioComparativo;
import criterios.OperadorComparacion;
import imports.ImportadorDeEmpresasCSV;
import indicators.DataIndicador;
import indicators.Indicador;
import repositories.repoArchivos.RepositorioEmpresas;
import repositories.repoArchivos.RepositorioIndicadores;

public class CriterioComparativoTest {

	public Indicador indicador1 = new DataIndicador("i1", "ebitda+1");
	public Criterio comparativo = new CriterioComparativo("comp1", OperadorComparacion.MAYOR, indicador1);
	public Criterio comparativo2 = new CriterioComparativo("comp2", OperadorComparacion.MENOR, indicador1);
	public List<String> periodos = new LinkedList<>();

	@Before
	public void setUp() {
		new ImportadorDeEmpresasCSV("empresasTest.csv").importar();
		RepositorioIndicadores.getInstance().agregar(indicador1);
		periodos.add("2015");
		periodos.add("2016");
		periodos.add("2017");
	}

	@Test
	public void verificarUnaEmpresaSiempreRetornaFalso() {
		assertTrue(!comparativo
				.verificarParaUna(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1"), periodos));
	}

	@Test
	public void posicionCorrectaAlEvaluarMayorTodosLosPeriodos() {
		assertEquals(comparativo.posicionLuegoDeAplicarDe(
				RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa2"),
				RepositorioEmpresas.getInstance().getElementos(), periodos),new Double(2));
	}
	
	@Test
	public void posicionCorrectaAlEvaluarMayorUnSoloPeriodo() {
		List<String> uniPeriodo = new LinkedList<>(periodos);
		uniPeriodo.remove("2015");
		uniPeriodo.remove("2016");
		
		assertEquals(comparativo.posicionLuegoDeAplicarDe(
				RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1"),
				RepositorioEmpresas.getInstance().getElementos(), uniPeriodo),new Double(1));
	}
	
	@Test
	public void posicionCorrectaAlEvaluarMenorTodosLosPeriodos() {
		assertEquals(comparativo2.posicionLuegoDeAplicarDe(
				RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1"),
				RepositorioEmpresas.getInstance().getElementos(), periodos),new Double(1));
	}
	
	@Test
	public void posicionCorrectaAlEvaluarMenorUnSoloPeriodo() {
		List<String> uniPeriodo = new LinkedList<>(periodos);
		uniPeriodo.remove("2015");
		uniPeriodo.remove("2016");
		
		assertEquals(comparativo.posicionLuegoDeAplicarDe(
				RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1"),
				RepositorioEmpresas.getInstance().getElementos(), uniPeriodo),new Double(1));
	}

	@After
	public void tearDown() {
		RepositorioIndicadores.deleteInstance();
		RepositorioEmpresas.deleteInstance();
	}
}
