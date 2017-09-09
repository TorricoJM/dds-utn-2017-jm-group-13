package adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import criterios.Criterio;
import repositories.repoArchivos.RepositorioCriterios;

public class AdapterCriteriosToJSON extends AdaptadorToJSON<List<Criterio>> {

	@Override
	protected List<Criterio> getContenido() {
		return RepositorioCriterios.getInstance().getElementos();
	}

	@Override
	protected void setContenido(List<Criterio> contenido) {
		RepositorioCriterios.getInstance().setElementos(contenido);
	}

	@Override
	protected Type obtenerTypeToken() {
		return new TypeToken<List<Criterio>>(){}.getType();
	}
}