package exports;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import indicators.DataIndicador;
import repositories.RepositorioIndicadores;

public class ExportadorIndicadores extends ExportadorJSON {

	public ExportadorIndicadores() {
		this.PATH = "./indicadores.json";
	}
	
	@Override
	protected String parsearContenidoDeRepositorio() {
		Gson gson = new Gson();
		Type tipoListaIndicadores = new TypeToken<List<DataIndicador>>(){}.getType();
		return gson.toJson(RepositorioIndicadores.getInstance().getIndicadores(), tipoListaIndicadores);
	}

}