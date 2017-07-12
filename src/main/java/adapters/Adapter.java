package adapters;

public interface Adapter<T> {
	public T transformarA();
	public void transformarDesde(String contenido);
}