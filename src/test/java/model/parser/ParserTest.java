package model.parser;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Arrays;
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
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;

public class ParserTest{
	Indicador indicadorSimple;
	DataIndicador indicadorA;
	DataIndicador indicadorB;
	DataIndicador indicadorC;
	public double resultado;
	public String resultadoString;
	List<String> listaCuentas;
	public ImportadorDeEmpresasCSV importadorCSV;
	ExpresionLexer lexer;
	
	@Before
	public void setUp(){
		indicadorSimple = new DataIndicador("indicadorSimple", "15+35");
		indicadorA = new DataIndicador("indicadorA", "ebitda + 100");
		indicadorB = new DataIndicador("indicadorB", "fds * 10");
		indicadorC = new DataIndicador("indicadorC", "indicadorA + fds");
		
		importadorCSV = new ImportadorDeEmpresasCSV("empresas.csv");
		importadorCSV.importar();
		new ImportadorArchivos(new AdapterCuentasToJSON(), "./cuentas.json").importar();
		new ImportadorArchivos(new AdapterIndicadoresToJSON(), "./indicadores.json").importar();
		lexer = new ExpresionLexer();
	}

	@Test
	public void listaVacia(){
		ObjetosParserStrategy objeto = new ObjetosParserStrategy();
		
		assertTrue(objeto.expresiones.isEmpty());
	}

	@Test
	public void extraerOperadores(){
		final ExpresionLexer lexer = new ExpresionLexer();
		lexer.extraerOperadores("1.4 +5.59 + hola - mundo");
		assertTrue(lexer.operadores.equals(Arrays.asList("+","+","-")));
	}

	@Test
	public void evaluarOperacion(){
		//hago que el lexer cree y evalue segun la operacion 2*3+4
		final ConstanteParser cons = new ConstanteParser("2");
		final ConstanteParser cons1 = new ConstanteParser("3");
		final ConstanteParser cons2 = new ConstanteParser("4");
		lexer.expresiones.add(cons);
		lexer.expresiones.add(cons1);
		lexer.expresiones.add(cons2);
		lexer.operadores.add("*");
		lexer.operadores.add("+");
		lexer.crearOperacion(lexer.expresiones);
		lexer.crearOperacion(lexer.expresiones);
		assertEquals(lexer.expresiones.get(0).operar("hola", "mundo"),(double) 10,(double) 0);
	}
	
	@Test
	public void evaluarOperacion2(){
		//hago que el lexer cree y evalue segun la operacion ebitda+100 para sprite en periodo 2016
		final ConstanteParser cons = new ConstanteParser("100");
		final CuentaParser cuenta = new CuentaParser("ebitda");
		lexer.expresiones.add(cuenta);
		lexer.expresiones.add(cons);
		lexer.operadores.add("+");
		lexer.crearOperacion(lexer.expresiones);
		assertEquals(lexer.expresiones.get(0).operar("sprite", "2016"),(double) 600,(double) 0);
	}
	
	@Test
	public void evaluarIndicador(){
		assertEquals(indicadorSimple.evaluateEn("", ""),(double) 50,(double) 0);
	}
	
	@Test
	public void evaluarIndicadorA(){
		RepositorioIndicadores.getInstance().agregar(indicadorA);
		resultado = indicadorA.evaluateEn("coca cola", "2017");
		assertEquals(resultado,(double) 1125.55,(double) 2);
	}
	
	@Test
	public void evaluarIndicadorB(){
		RepositorioIndicadores.getInstance().agregar(indicadorB);
		resultado = indicadorB.evaluateEn("sprite", "2016");
		assertEquals(resultado,(double) 10000000,(double) 0);
	}
	
	@Test
	public void evaluarIndicadorC(){
		RepositorioIndicadores.getInstance().agregar(indicadorA);
		RepositorioIndicadores.getInstance().agregar(indicadorC);
		resultado = indicadorC.evaluateEn("sprite", "2016");
		DecimalFormat formato = new DecimalFormat("###############.############");
		resultadoString = String.valueOf(formato.format(resultado));
		assertEquals(resultadoString, "1000600");
	}
	
	@After
	public void finalizar() {
		RepositorioIndicadores.deleteInstance();
		RepositorioEmpresas.deleteInstance();
	}
}