package repositories;

import java.util.LinkedList;
import java.util.List;

import methodologies.Metodologia;
import methodologies.PredefinidaWarrenBuffet;

public class RepositorioMetodologias {

	private static RepositorioMetodologias instance;
	
	public static RepositorioMetodologias getInstance() {
		if(instance == null){
			instance = new RepositorioMetodologias();
			instance.agregar(PredefinidaWarrenBuffet.getInstance());
		}
		
		return instance;
	}
	
	public static void deleteInstance() {
		instance = null;
	}
	
	private List<Metodologia> listaMetodologias = new LinkedList<>();

	public List<Metodologia> getListaMetodologias() {
		return listaMetodologias;
	}

	public void agregar(Metodologia metodologia) {
		listaMetodologias.add(metodologia);
	}
}
