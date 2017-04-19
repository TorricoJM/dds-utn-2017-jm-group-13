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
		//System.out.println(this.password);
		
		if(this.password==Repositorios.PASSWORD){
			System.out.println(Repositorios.PASSWORD);
			return true;
		}
		return true;	//en realidad va false, puse true para probar lo que sigue xq funciona mal y nunca ingresa
	}

}