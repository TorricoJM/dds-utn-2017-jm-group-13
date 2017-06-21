package adapters;

import java.util.List;

import indicators.DataIndicador;
import repositories.RepositorioIndicadores;

public class AdapterIndicadoresToJSON extends AdaptadorJSON<List<DataIndicador>> {

	@Override
	protected List<DataIndicador> getContenido() {
		return RepositorioIndicadores.getInstance().getIndicadores();
	}
}