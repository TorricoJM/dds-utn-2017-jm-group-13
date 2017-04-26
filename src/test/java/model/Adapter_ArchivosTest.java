package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Adapter_ArchivosTest {
	
public Adapter_Archivos adapter;
	
	@Before
	public void init() {
     	adapter = new Adapter_Archivos();
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
	
}
