package model;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import repositories.RepositorioCuentas;

public class LectorCuentas extends LectorJSON {

	public LectorCuentas(){
		this.PATH = "./cuentas.json";
	}
	
	@Override
	protected void parsearContenidoDeArchivo(String contenido) {
		Gson gson = new Gson();
		Type tipoListaCuentas = new TypeToken<List<String>>(){}.getType();
		RepositorioCuentas.cuentas = gson.fromJson(contenido, tipoListaCuentas);
	}
}