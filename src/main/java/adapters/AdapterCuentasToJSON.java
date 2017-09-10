package adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import repositories.repoArchivos.RepositorioCuentas;

public class AdapterCuentasToJSON extends AdaptadorToJSON<List<String>> {

	@Override
	protected List<String> getContenido() {
		return RepositorioCuentas.getInstance().getElementos();
	}

	@Override
	protected void setContenido(List<String> contenido) {
		RepositorioCuentas.getInstance().setElementos(contenido);
	}

	@Override
	protected Type obtenerTypeToken() {
		return new TypeToken<List<String>>(){}.getType();
	}

}
