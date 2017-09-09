package repositories.repoArchivos;

import java.util.LinkedList;
import java.util.List;

import repositories.Repositorio;

public abstract class RepoArchivos<T> implements Repositorio{
	
	private List<T> elementos = new LinkedList<T>();
	
	public void agregar(T elemento) {
		if (!elementos.contains(elemento))
			elementos.add(elemento);
	}
	
	public List<T> getElementos(){
		return elementos;
	}
	
	public void setElementos(List<T> elementos) {
		this.getElementos().addAll(elementos);
	}

}