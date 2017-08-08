package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import criterios.Criterio;
import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Normal;
import imports.ImportadorDeEmpresasCSV;
import indicators.DataIndicador;
import indicators.Indicador;
import methodologies.Metodologia;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class MetodologiaTest {
	/*
	public ImportadorDeEmpresasCSV importador = new ImportadorDeEmpresasCSV("empresasTest.csv");
	public Indicador indicador1 = new DataIndicador("i1","ebitda*10");
	public Indicador indicador2 = new DataIndicador("i2","Activo Total - 1");
	public Pair<Criterio,Double> taxativo = Pair.with(new CriterioTaxativo("tax1",OperadorComparacion.MAYOR,indicador1,new Normal(),500), -1.0);
	public Pair<Criterio,Double> taxativo2 = Pair.with(new CriterioTaxativo("tax2",OperadorComparacion.MENOR,indicador2,new Normal(),10), -1.0);
	public List<Empresa> empresasEsperadas = new LinkedList<>();
	public List<Empresa> empresasRepo = new LinkedList<>();
	public List<Pair<Criterio,Double>> taxativos;
	public Metodologia metodologia1;
	
	@Before
	public void setUp(){
		RepositorioEmpresas.deleteInstance();
		importador.importar();
		RepositorioIndicadores.getInstance().agregar(indicador1);
		RepositorioIndicadores.getInstance().agregar(indicador2);
		taxativos.add(taxativo);
		taxativos.add(taxativo2);
		metodologia1 = new Metodologia("m1", taxativos);
		empresasRepo.addAll(RepositorioEmpresas.getInstance().getListaEmpresas());
	}
	
	@Test
	public void metodologiaAplicaBienCriteriosTaxativos() {
		List<String> periodos = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1").getPeriodos().stream().map(p->p.getPeriodo()).collect(Collectors.toList());
		empresasEsperadas.add(RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa3"));
		
		assertEquals(empresasEsperadas,metodologia1.aplicarMetodologia(empresasRepo, periodos));
		
	}
	
	@After
	public void tearDown() {
		RepositorioIndicadores.deleteInstance();
		RepositorioEmpresas.deleteInstance();
		empresasEsperadas.clear();
		empresasRepo.clear();
		taxativos.clear();
	}
	*/
}