package adapters;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import indicators.DataIndicador;
import indicators.Indicador;
import repositories.RepositorioIndicadores;

public class AdapterIndicadoresToJSON extends AdaptadorToJSON<List<Indicador>> {

	@Override
	protected List<Indicador> getContenido() {
		return RepositorioIndicadores.getInstance().getIndicadores();
	}

	@Override
	protected void setContenido(List<Indicador> contenido) {
		RepositorioIndicadores.getInstance().anexarIndicadores(contenido);
	}

	@Override
	protected Type obtenerTypeToken() {
		return new TypeToken<List<DataIndicador>>(){}.getType();
	}
}