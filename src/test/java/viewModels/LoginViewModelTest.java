package viewModels;

import static org.junit.Assert.*;

import repositories.*;

import org.junit.Before;
import org.junit.Test;

public class LoginViewModelTest {
	
	public LoginViewModel login;
	
	@Before
	public void init() {
		
     	login = new LoginViewModel();
     	
 	}
	
	@Test
	public void puedeLoguearseDaCorrecto() {

		String password = Repositorios.PASSWORD;
		login.setPassword(password);
		login.puedeLoguearse();
		
		assertTrue(login.puedeLoguearse());
		
	}
	
	@Test
	public void puedeLoguearseDaIncorrecto() {

		String password = "contraseñaIncorrecta";
		login.setPassword(password);
		login.puedeLoguearse();
		
		assertFalse(login.puedeLoguearse());
		
	}
	
	@Test
	public void levantarCuentasDeArchivoDaCorrecto() {

     	String path = "./empresas.txt";
		login.levantarCuentasDeArchivo(path);
		assertNotNull(Repositorios.listaEmpresas.getEmpresas());
		
	}
	
	@Test
	public void levantarCuentasDeArchivoDaIncorrecto() {
		
		String pathIncorrecto = "./pathIncorrecto.txt";
		login.levantarCuentasDeArchivo(pathIncorrecto);
		assertNull(Repositorios.listaEmpresas);
	
	}

}
