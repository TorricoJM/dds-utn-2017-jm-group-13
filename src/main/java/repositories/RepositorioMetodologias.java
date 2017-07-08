package repositories;

import java.util.LinkedList;
import java.util.List;

import methodologies.DataMetodologia;

public class RepositorioMetodologias {

	private static RepositorioMetodologias instance;
	
	public static RepositorioMetodologias getInstance() {
		if(instance == null){
			instance = new RepositorioMetodologias();
		}
		
		return instance;
	}
	
	public static void deleteInstance() {
		instance = null;
	}
	
	private List<DataMetodologia> listaMetodologias = new LinkedList<>();

	public List<DataMetodologia> getListaMetodologias() {
		return listaMetodologias;
	}

	public void agregar(DataMetodologia metodologia) {
		listaMetodologias.add(metodologia);
	}
}