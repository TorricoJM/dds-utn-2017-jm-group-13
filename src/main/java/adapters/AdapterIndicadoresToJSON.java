package adapters;

import java.util.List;

import indicators.Indicador;
import repositories.RepositorioIndicadores;

public class AdapterIndicadoresToJSON extends AdaptadorToJSON<List<Indicador>> {

	@Override
	protected List<Indicador> getContenido() {
		return RepositorioIndicadores.getInstance().getIndicadores();
	}
}