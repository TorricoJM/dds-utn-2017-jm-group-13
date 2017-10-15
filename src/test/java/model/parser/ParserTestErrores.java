package model.parser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import indicators.DataIndicador;
import model.parser.objetosParser.*;
import repositories.RepositorioIndicadores;

@SuppressWarnings("unused")
public class ParserTestErrores extends AbstractPersistenceTest{
	DataIndicador indicadorSinNombre;
	DataIndicador indicadorSinOperacion;
	DataIndicador indicadorA;
	DataIndicador indicadorB;
	DataIndicador indicadorC;
	public double resultado;
	public String resultadoString;
	List<String> listaCuentas;
	ExpresionLexer lexer;
	
	@Before
	public void setUp(){
		indicadorA = new DataIndicador("indicadorA", "ebitda + 100");
		indicadorB = new DataIndicador("indicadorB", "fds * 10");
		indicadorC = new DataIndicador("indicadorC", "indicadorA + fds");
		indicadorSinNombre = new DataIndicador(null, "ebitda - 5");
		indicadorSinOperacion = new DataIndicador("indicadorFeo", null);
		
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
	
	@Test(expected=IdentificadorInvalidoException.class)
	public void evaluarEnEmpresaSinCuentaLanzaException(){
		indicadorA.evaluateEn(null, "2016");
	}
	@Test(expected=IdentificadorInvalidoException.class)
	public void evaluarEnEmpresaSinCuentaLanzaException2(){
		indicadorA.evaluateEn("coca cola", "2016");
	}
	@Test(expected=IdentificadorInvalidoException.class)
	public void evaluarEnEmpresaSinPeriodoLanzaException(){
		indicadorA.evaluateEn("coca cola", null);
	}
	@Test(expected=IdentificadorInvalidoException.class)
	public void evaluarEnEmpresaSinPeriodoLanzaException2(){
		indicadorA.evaluateEn("coca cola", "2015");
	}
	
	@Test(expected=IdentificadorInvalidoException.class)
	public void indicadorSinNombreLanzaException(){
		indicadorSinNombre.evaluateEn("coca cola", "2017");
	}
	//TODO
	
	@Test(expected=IdentificadorInvalidoException.class)
	public void indicadorSinOperacionLanzaException(){
		indicadorSinOperacion.evaluateEn("coca cola", "2017");
	}
	
	@After
	public void tearDown(){
		RepositorioIndicadores.deleteInstance();
	}
	@Override
	public EntityManager entityManager() {
		return PerThreadEntityManagers.getEntityManager();
	}
	
}