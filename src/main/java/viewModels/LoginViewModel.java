package viewModels;

import org.uqbar.commons.utils.Observable;

import repositories.*;

@Observable
public class LoginViewModel {

	public String password;

	public LoginViewModel() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean puedeLoguearse() {		
		if(this.password.equals(Repositorios.PASSWORD)){
			return true;
		}

		return false;
	}

}