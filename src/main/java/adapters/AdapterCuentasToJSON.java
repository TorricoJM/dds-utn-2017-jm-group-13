package adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import model.Cuenta;
import repositories.repoArchivos.RepositorioCuentas;

public class AdapterCuentasToJSON extends AdaptadorToJSON<List<Cuenta>> {

	@Override
	protected List<Cuenta> getContenido() {
		return RepositorioCuentas.getInstance().getElementos();
	}

	@Override
	protected void setContenido(List<Cuenta> contenido) {
		RepositorioCuentas.getInstance().setElementos(contenido);
	}

	@Override
	protected Type obtenerTypeToken() {
		return new TypeToken<List<Cuenta>>(){}.getType();
	}

}
