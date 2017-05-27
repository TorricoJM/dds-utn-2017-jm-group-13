package model;

import com.google.gson.Gson;

import repositories.RepositorioIndicadores;

public class LectorIndicadores extends LectorJSON {

	public LectorIndicadores(){
		this.PATH = "./indicadores.json";
	}
	
	@Override
	protected void parsearContenidoDeArchivo(String contenido) {
		Gson gson = new Gson();
		IndicadorImportado indImp = gson.fromJson(contenido, IndicadorImportado.class);
		RepositorioIndicadores.indicadores = indImp.getIndicadoresImportados();
	}
}
