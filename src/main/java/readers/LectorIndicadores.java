package readers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import indicators.Indicador;
import repositories.RepositorioIndicadores;

public class LectorIndicadores extends LectorJSON {

	public LectorIndicadores(){
		this.PATH = "./indicadores.json";
	}
	
	@Override
	protected void parsearContenidoDeArchivo(String contenido) {
		Gson gson = new Gson();
		Type tipoListaIndicadores = new TypeToken<List<Indicador>>(){}.getType();
		RepositorioIndicadores.indicadores = gson.fromJson(contenido, tipoListaIndicadores);
	}
}
