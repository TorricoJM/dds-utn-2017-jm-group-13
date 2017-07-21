package model;

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
	
	
	@Before
	public void setUp(){
		importador.importar();
		
		RepositorioIndicadores.getInstance().agregar(indicador1);
	}
	
	@Test
	public void taxativoIndicador1MayorA500() {
		List<PeriodoFiscal> periodos = RepositorioEmpresas.getInstance().obtenerEmpresaDesdeNombre("empresa1").getPeriodos();
		System.out.println(taxativo.evaluar(periodos).stream().map(e -> e.getNombre()).collect(Collectors.toList()));
	}
	
	@After
	public void tearDown(){
		RepositorioIndicadores.deleteInstance();
	}
}