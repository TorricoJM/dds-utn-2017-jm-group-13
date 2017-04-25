package viewModels;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginViewModelTest {
	
	@Test
	public void puedeLoguearseDaCorrecto() {

		String password = "123456";
		LoginViewModel login = new LoginViewModel();
		login.setPassword(password);
		login.puedeLoguearse();
		
		assertTrue(login.puedeLoguearse());
		
	}
	
	@Test
	public void puedeLoguearseDaIncorrecto() {

		String password = "contraseñaIncorrecta";
		LoginViewModel login = new LoginViewModel();
		login.setPassword(password);
		login.puedeLoguearse();
		
		assertFalse(login.puedeLoguearse());
		
	}

}
