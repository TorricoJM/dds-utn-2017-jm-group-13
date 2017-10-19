package repositories;

import java.util.List;

import methodologies.Metodologia;

public class RepositorioMetodologias extends Repositorio<Metodologia>{

	private static RepositorioMetodologias instance;

	public static RepositorioMetodologias getInstance() {
		if (instance == null) {
			instance = new RepositorioMetodologias();
			//instance.agregar(new MetodologiesBuilder().buildPredefWarrenBuffet().build());
		}
		
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}
	
	public List<Metodologia> getElementos(){
		return this.entityManager.createQuery("from Metodologia", Metodologia.class).getResultList();
	}
	
	public Metodologia obtenerDesdeNombreUna(String metodologia) {
		return this.getElementos().stream().filter((meto) -> meto.getNombre().equals(metodologia)).findFirst().get();
	}
}
