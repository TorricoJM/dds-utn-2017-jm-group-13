package model.parser;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import imports.ImportadorDeEmpresasCSV;
import indicators.Indicador;
import model.*;

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
}