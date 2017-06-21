package adapters;

import java.util.List;

import repositories.RepositorioCuentas;

public class AdapterCuentasToJSON extends AdaptadorToJSON<List<String>> {

	@Override
	protected List<String> getContenido() {
		return RepositorioCuentas.getInstance().getCuentas();
	}

}