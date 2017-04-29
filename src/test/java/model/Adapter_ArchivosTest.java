package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Adapter_ArchivosTest {
	
public AdapterArchivos adapter;
	
	@Before
	public void init() {
     	adapter = new AdapterArchivos();
 	}

	@Test
	public void obtenerJsonDelArchivoDaCorrecto() {
		
		String path = "./empresas.txt";
		String respuesta = adapter.obtenerJsonDelArchivo(path);
		assertNotNull(respuesta);
	
	}
	
	@Test
	public void obtenerJsonDelArchivoDaIncorrecto() {
		
		String pathIncorrecto = "./pathIncorrecto.txt";
		String respuesta = adapter.obtenerJsonDelArchivo(pathIncorrecto);
		assertNull(respuesta);
	
	}
	
	@Test
	public void guardarJsonEnElArchivo() {
		
		String path = "./test.txt";
		String cadena = "cadenaTestEjemplo";
		adapter.guardarJsonEnElArchivo(path, cadena);
		
		String cadenaArchivo = adapter.obtenerJsonDelArchivo(path);
		assertEquals(cadena, cadenaArchivo);
		
	}
}
