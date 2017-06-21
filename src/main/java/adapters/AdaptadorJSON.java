package adapters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class AdaptadorJSON<T> {
    protected abstract T getContenido();
    
    public String transformar() {
       return new Gson().toJson(getContenido(),new TypeToken<T>(){}.getType());
    }
}