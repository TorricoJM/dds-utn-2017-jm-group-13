package adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import methodologies.Metodologia;
import repositories.repoArchivos.RepositorioMetodologias;

public class AdapterMetodologiasToJSON extends AdaptadorToJSON<List<Metodologia>> {

	@Override
	protected List<Metodologia> getContenido() {
		return RepositorioMetodologias.getInstance().getListaMetodologiasForExport();
	}

	@Override
	protected void setContenido(List<Metodologia> contenido) {
		RepositorioMetodologias.getInstance().agregarTodo(contenido);
	}

	@Override
	protected Type obtenerTypeToken() {
		return new TypeToken<List<Metodologia>>(){}.getType();
	}

}
