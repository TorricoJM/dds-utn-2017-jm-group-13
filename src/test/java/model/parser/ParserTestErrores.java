package model.parser;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import adapters.AdapterCuentasToJSON;
import adapters.AdapterIndicadoresToJSON;
import imports.ImportadorArchivos;
import imports.ImportadorDeEmpresasCSV;
import indicators.DataIndicador;
import indicators.Indicador;
import model.parser.objetosParser.*;
import repositories.RepositorioIndicadores;

public class ParserTestErrores{
	DataIndicador indicadorSinNombre;
	DataIndicador indicadorSinOperacion;
	DataIndicador indicadorA;
	Indicador indicadorB;
	Indicador indicadorC;
	public double resultado;
	public String resultadoString;
	List<String> listaCuentas;
	public ImportadorDeEmpresasCSV importadorCSV;
	ExpresionLexer lexer;
	
	@Before
	public void setUp(){
		indicadorA = new DataIndicador("indicadorA", "ebitda + 100");
		indicadorB = new DataIndicador("indicadorB", "fds * 10");
		indicadorC = new DataIndicador("indicadorC", "indicadorA + fds");
		indicadorSinNombre = new DataIndicador(null, "ebitda - 5");
		indicadorSinOperacion = new DataIndicador("indicadorFeo", null);
		
		importadorCSV = new ImportadorDeEmpresasCSV("empresas.csv");
		importadorCSV.importar();
		new ImportadorArchivos(new AdapterCuentasToJSON(), "./cuentas.json").importar();
		new ImportadorArchivos(new AdapterIndicadoresToJSON(), "./indicadores.json").importar();
		lexer = new ExpresionLexer();
	}
	@Test(expected=IdentificadorInvalidoException.class)
	public void operacionMalFormadaLanzaException(){
		lexer.generarArbolExpresiones("1+2+");
	}
	
	@Test(expected=IdentificadorInvalidoException.class)
	public void operacionMalFormadaLanzaException2(){
		lexer.generarArbolExpresiones("@+3-5");
	}
	@Test(expected=IdentificadorInvalidoException.class)
	public void operacionVaciaLanzaException(){
		lexer.generarArbolExpresiones("");
	}
	
	@Test(expected=ErrorEvaluacionException.class)
	public void evaluarEnEmpresaSinCuentaLanzaException(){
		RepositorioIndicadores.getInstance().agregar(indicadorA);
		indicadorA.evaluateEn(null, "2016");
	}
	@Test(expected=ErrorEvaluacionException.class)
	public void evaluarEnEmpresaSinCuentaLanzaException2(){
		RepositorioIndicadores.getInstance().agregar(indicadorA);
		indicadorA.evaluateEn("coca cola", "2016");
	}
	@Test(expected=ErrorEvaluacionException.class)
	public void evaluarEnEmpresaSinPeriodoLanzaException(){
		RepositorioIndicadores.getInstance().agregar(indicadorA);
		indicadorA.evaluateEn("coca cola", null);
	}
	@Test(expected=ErrorEvaluacionException.class)
	public void evaluarEnEmpresaSinPeriodoLanzaException2(){
		RepositorioIndicadores.getInstance().agregar(indicadorA);
		indicadorA.evaluateEn("coca cola", "2015");
	}
	@Test(expected=ErrorEvaluacionException.class)
	public void identificadorSinNombreLanzaException(){
		RepositorioIndicadores.getInstance().agregar(indicadorSinNombre);
		indicadorSinNombre.evaluateEn("coca cola", "2017");
	}
	@Test(expected=IdentificadorInvalidoException.class)
	public void identificadorSinOperacionLanzaException(){
		RepositorioIndicadores.getInstance().agregar(indicadorSinOperacion);
		indicadorSinOperacion.evaluateEn("coca cola", "2017");
	}
	
	@After
	public void tearDown(){
		RepositorioIndicadores.deleteInstance();
	}
	
}