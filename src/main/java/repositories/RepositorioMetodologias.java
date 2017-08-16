package repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import methodologies.Metodologia;
import methodologies.MetodologiesBuilder;

public class RepositorioMetodologias {

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

	private List<Metodologia> listaMetodologias = new LinkedList<>();

	public List<Metodologia> getListaMetodologias() {
		return listaMetodologias;
	}

	public List<Metodologia> getListaMetodologiasForExport() {
		return listaMetodologias.stream().filter(met -> !met.getNombre().equals("Warren Buffet"))
				.collect(Collectors.toList());
	}

	public void agregar(Metodologia metodologia) {
		listaMetodologias.add(metodologia);
	}

	public void agregarTodo(List<Metodologia> unasMetodologias) {
		listaMetodologias.addAll(unasMetodologias);
	}
}
