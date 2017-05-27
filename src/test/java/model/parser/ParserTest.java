package model.parser;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class ParserTest{
	LineaEmpresa e;
	LineaEmpresa e1;
	LineaEmpresa e2;
	List<LineaEmpresa> empresas;
	Indicador indicadorSimple;
	Indicador indicador1;
	Indicador indicador2;
	Indicador indicador3;
	List<Indicador> indicadores;
	EvaluadorDeIndicador evaluador;
	public double resultado;
	ImportadorDeEmpresasCSV importador;
	
	@Before
	public void initialize(){
		e = new LineaEmpresa();
		e.setNombre("coca");
		e.setPeriodo("2017");
		e.setCuenta("ebitda");
		e.setValor("1000");

		e1 = new LineaEmpresa();
		e1.setNombre("coca");
		e1.setPeriodo("2016");
		e1.setCuenta("fds");
		e1.setValor("200");

		e2 = new LineaEmpresa();
		e2.setNombre("sprite");
		e2.setPeriodo("2016");
		e2.setCuenta("fds");
		e2.setValor("5000");

		empresas = new LinkedList<>();
		empresas.add(e);
		empresas.add(e1);
		empresas.add(e2);
		
		indicadorSimple = new Indicador("indicadorSimple", "15+35");
		indicador1 = new Indicador("indicador1", "ebitda + 100");
		indicador2 = new Indicador("indicador2", "fds * 10");
		indicador3 = new Indicador("indicador3", "indicador1 - fds");
		
		indicadores = new LinkedList<>();
		indicadores.add(indicador1);
		indicadores.add(indicador2);
		indicadores.add(indicador3);
		
		evaluador = new EvaluadorDeIndicador();
	}
	
	@Test
	public void evaluarValoresTest(){
		resultado = evaluador.evaluarIndicador(indicadorSimple," "," ");
		assertEquals(resultado,(double) 50,(double)0);
	}
	//TODO Faltan implementaciones
	/*@Test
	public void evaluarIndicador1Test(){
		
		resultado = evaluador.evaluarIndicador(indicador1, "sprite", "2016");
		assertEquals(resultado,(double) 600,(double)0);
	}*/
}