package repositories.repoArchivos;

import java.util.List;
import java.util.stream.Collectors;

import methodologies.Metodologia;
import methodologies.MetodologiesBuilder;

public class RepositorioMetodologias extends RepoArchivos<Metodologia>{

	private static RepositorioMetodologias instance;

	public static RepositorioMetodologias getInstance() {
		if (instance == null) {
			instance = new RepositorioMetodologias();
			instance.agregar(new MetodologiesBuilder().buildPredefWarrenBuffet().build());
		}
		
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}
	
	public List<Metodologia> getListaMetodologiasForExport() {
		return this.getElementos().stream().filter(met -> !met.getNombre().equals("Warren Buffet"))
				.collect(Collectors.toList());
	}

}
