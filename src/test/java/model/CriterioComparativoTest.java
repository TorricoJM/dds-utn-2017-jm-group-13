package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import criterios.CriterioComparativo;
import criterios.OperadorComparacion;
import imports.ImportadorDeEmpresasCSV;
import indicators.DataIndicador;
import indicators.Indicador;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class CriterioComparativoTest extends AbstractPersistenceTest {

	public Indicador indicador1 = new DataIndicador("i1", "ebitda+1");
	public CriterioComparativo comparativo = new CriterioComparativo("comp1", OperadorComparacion.MAYOR, indicador1);
	public CriterioComparativo comparativo2 = new CriterioComparativo("comp2", OperadorComparacion.MENOR, indicador1);
	public List<String> periodos = new LinkedList<>();

	@Before
	public void setUp() {
		new ImportadorDeEmpresasCSV("empresasTest.csv").importar();
		RepositorioIndicadores.getInstance().agregar(indicador1);
		periodos.add("2015");
		periodos.add("2016");
		periodos.add("2017");
	}
/* por favor corregir quien los haya hecho
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

*/
	@Override
	public EntityManager entityManager() {
		return PerThreadEntityManagers.getEntityManager();
	}
}
