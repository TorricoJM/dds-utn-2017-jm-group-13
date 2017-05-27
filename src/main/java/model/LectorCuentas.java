package model;

import com.google.gson.Gson;

import repositories.RepositorioCuentas;

public class LectorCuentas extends LectorJSON {

	public LectorCuentas(){
		this.PATH = "./cuentas.json";
	}
	
	@Override
	protected void parsearContenidoDeArchivo(String contenido) {
		Gson gson = new Gson();
		CuentaImportada cuentas = gson.fromJson(contenido, CuentaImportada.class);
		RepositorioCuentas.cuentas = cuentas.getCuentas();
		
		System.out.println(RepositorioCuentas.cuentas.get(0));
		System.out.println(RepositorioCuentas.cuentas.get(1));
	}
}