package viewModels;

import org.uqbar.commons.utils.Observable;
import com.google.gson.Gson;

import model.Adapter_Archivos;
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
			this.levantarCuentasDeArchivo(Repositorios.PATH_ARCHIVO_CUENTAS);
			return true;
		}

		return false;
	}
	
	private void levantarCuentasDeArchivo(String path){	//con esto, levantamos las cuentas de las empresas una sola vez
		Gson gson = new Gson();
		String cadenaAParsear = new Adapter_Archivos().obtenerJsonDelArchivo(path);
		System.out.println(cadenaAParsear);
		Repositorios.listaEmpresas = gson.fromJson(cadenaAParsear, model.ListaEmpresas.class);
	}
}