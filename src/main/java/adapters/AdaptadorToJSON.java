package adapters;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class AdaptadorToJSON<T> implements Adapter<String> {
	protected abstract T getContenido();
	protected abstract void setContenido(T contenido);
	protected abstract Type obtenerTypeToken();

	public String transformarA() {
		return new Gson().toJson(getContenido(), new TypeToken<T>() {}.getType());
	}
	
	public void transformarDesde(String algunString) {
		this.setContenido(new Gson().fromJson((String) algunString, this.obtenerTypeToken()));
	}
}