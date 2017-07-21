package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Normal;
import imports.ImportadorDeEmpresasCSV;
import indicators.DataIndicador;
import indicators.Indicador;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class CriterioTaxativoTest {
	
	public ImportadorDeEmpresasCSV importador = new ImportadorDeEmpresasCSV("empresasTest.csv");
	public Indicador indicador1 = new DataIndicador("i1","ebitda*10");
	public CriterioTaxativo taxativo = new CriterioTaxativo("tax1",OperadorComparacion.MAYOR,indicador1,new Normal(),500);
	public List<Empresa> empresasEsperadas = new LinkedList<>();
	public List<Empresa> empresasRepo = new LinkedList<>();
	
	@Before
	public void setUp(){
		importador.importar();
		
		RepositorioIndicadores.getInstance().agregar(indicador1);
		empresasRepo.addAll(RepositorioEmpresas.getInstance().getListaEmpresas());
	}
	
	@Test
	public void taxativoIndicador1MayorA500() {
		List<String> periodos = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1").getPeriodos().stream().map(p->p.getPeriodo()).collect(Collectors.toList());
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa2"));
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa3"));
		assertEquals(taxativo.evaluar(periodos,empresasRepo),empresasEsperadas);
		
	}

	@After
	public void tearDown(){
		RepositorioIndicadores.deleteInstance();
		empresasEsperadas.clear();
		empresasRepo.clear();
	}
}