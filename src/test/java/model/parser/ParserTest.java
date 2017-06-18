package model.parser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import indicators.DataIndicador;
import model.parser.objetosParser.*;
import repositories.RepositorioCuentas;
import repositories.RepositorioEmpresas;

public class ParserTest{
	DataIndicador indicadorSimple;
	DataIndicador indicadorA;
	DataIndicador indicadorB;
	DataIndicador indicadorC;
	List<DataIndicador> indicadores;
	public double resultado;
	RepositorioCuentas mockedRepoCuentas = mock(RepositorioCuentas.class);
	RepositorioEmpresas mockedRepoEmpresas = mock(RepositorioEmpresas.class);
	List<String> listaCuentas;
	
	@Before
	public void setUp(){
		indicadorSimple = new DataIndicador("indicadorSimple", "15+35");
		indicadorA = new DataIndicador("indicadorA", "ebitda + 100");
		indicadorB = new DataIndicador("indicadorB", "fds * 10");
		indicadorC = new DataIndicador("indicadorC", "indicador1 + fds");
		
		indicadores = new LinkedList<>();
		indicadores.add(indicadorA);
		indicadores.add(indicadorB);
		indicadores.add(indicadorC);
		
		listaCuentas = new LinkedList<>();
		listaCuentas.add("ebitda");
		listaCuentas.add("free cash flow");
		listaCuentas.add("fds");
		listaCuentas.add("operaciones continuas");
		listaCuentas.add("operaciones discontinuas");
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
		final ExpresionLexer lexer = new ExpresionLexer();
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
		final ExpresionLexer lexer = new ExpresionLexer();
		final ConstanteParser cons = new ConstanteParser("100");
		final CuentaParser cuenta = new CuentaParser("ebitda");
		lexer.expresiones.add(cuenta);
		lexer.expresiones.add(cons);
		lexer.operadores.add("+");
		lexer.crearOperacion(lexer.expresiones);
		assertEquals(lexer.expresiones.get(0).operar("sprite", "2016"),(double) 600,(double) 0);
	}
}