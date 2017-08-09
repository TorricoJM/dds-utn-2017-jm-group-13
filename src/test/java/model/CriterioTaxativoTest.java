package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class CriterioTaxativoTest {
	
	public ImportadorDeEmpresasCSV importador = new ImportadorDeEmpresasCSV("empresasTest.csv");
	public Indicador indicador1 = new DataIndicador("i1","ebitda*10");
	public Criterio taxativo = new CriterioTaxativo("tax1",OperadorComparacion.MAYOR,indicador1,new Normal(),500);
	public Criterio taxativo2 = new CriterioTaxativo("tax2",OperadorComparacion.MAYOR,indicador1,new Sumatoria(),2500);
	public Criterio taxativo3 = new CriterioTaxativo("tax3",OperadorComparacion.MENOR,indicador1,new Promedio(),750);
	public List<Empresa> empresasEsperadas = new LinkedList<>();
	public List<Empresa> empresasRepo = new LinkedList<>();
	public List<String> periodos = new LinkedList<>();
	
	@Before
	public void setUp(){
		RepositorioEmpresas.deleteInstance();
		importador.importar();
		RepositorioIndicadores.getInstance().agregar(indicador1);
		empresasRepo.addAll(RepositorioEmpresas.getInstance().getListaEmpresas());
		periodos = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1").getPeriodos().stream().map(p->p.getPeriodo()).collect(Collectors.toList());
	}
	
	@Test
	public void taxativoIndicador1MayorA500() {
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa2"));
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa3"));
		assertEquals(taxativo.evaluar(periodos,empresasRepo),empresasEsperadas);
	}
	
	@Test
	public void taxativoSumatoriaMayorA2500() {
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa3"));
		assertEquals(taxativo2.evaluar(periodos, empresasRepo),empresasEsperadas);
	}
	
	@Test
	public void taxativoPromedioMenorA7500() {
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1"));
		assertEquals(taxativo3.evaluar(periodos, empresasRepo),empresasEsperadas);
	}

	@After
	public void tearDown(){
		RepositorioIndicadores.deleteInstance();
		RepositorioEmpresas.deleteInstance();
		empresasEsperadas.clear();
		empresasRepo.clear();
	}
}