package adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import repositories.RepositorioCuentas;

public class AdapterCuentasToJSON extends AdaptadorToJSON<List<String>> {

	@Override
	protected List<String> getContenido() {
		return RepositorioCuentas.getInstance().getCuentas();
	}

	@Override
	protected void setContenido(List<String> contenido) {
		RepositorioCuentas.getInstance().setCuentas(contenido);
	}

	@Override
	protected Type obtenerTypeToken() {
		return new TypeToken<List<String>>(){}.getType();
	}

}
