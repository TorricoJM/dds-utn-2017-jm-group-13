package adapters;

import java.util.List;

import model.Criterio;
import repositories.RepositorioCriterios;

public class AdapterCriteriosToJSON extends AdaptadorToJSON<List<Criterio>> {

	@Override
	protected List<Criterio> getContenido() {
		return RepositorioCriterios.getInstance().getCriterios();
	}
}