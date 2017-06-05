package model.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import imports.ImportadorDeEmpresasCSV;
import indicators.Indicador;
import model.parser.objetosParser.*;

public class ParserTest{
	Indicador indicadorSimple;
	Indicador indicadorA;
	Indicador indicadorB;
	Indicador indicadorC;
	List<Indicador> indicadores;
	public double resultado;
	ImportadorDeEmpresasCSV importador;
	
	@Before
	public void initialize(){
		indicadorSimple = new Indicador("indicadorSimple", "15+35");
		indicadorA = new Indicador("indicadorA", "ebitda + 100");
		indicadorB = new Indicador("indicadorB", "fds * 10");
		indicadorC = new Indicador("indicadorC", "indicador1 + fds");
		
		indicadores = new LinkedList<>();
		indicadores.add(indicadorA);
		indicadores.add(indicadorB);
		indicadores.add(indicadorC);
		
		importador = new ImportadorDeEmpresasCSV("empresas.csv");
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
}