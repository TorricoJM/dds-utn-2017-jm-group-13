package exports;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import adapters.AdaptadorSaliente;
import model.CuentaYValor;
import repositories.RepositorioCuentas;

public class ExportadorCuentas extends ExportadorJSON {

	public ExportadorCuentas(AdaptadorSaliente adaptador) {
		super(adaptador);
		this.adaptador.setPath("./cuentas.json");
	}

	@Override
	protected String parsearContenidoDeRepositorio() {
		Gson gson = new Gson();
		Type tipoListaCuentas = new TypeToken<List<CuentaYValor>>(){}.getType();
		return gson.toJson(RepositorioCuentas.getInstance().getCuentas(), tipoListaCuentas);
	}

}