package repositories;

import java.util.LinkedList;
import java.util.List;

import model.Criterio;

public class RepositorioCriterios {

	private static RepositorioCriterios instance;

	public static RepositorioCriterios getInstance() {
		if (instance == null) {
			instance = new RepositorioCriterios();
		}
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	private List<Criterio> criterios = new LinkedList<>();

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void agregarCriterios(List<Criterio> nuevosCriterios) {
		this.getCriterios().addAll(nuevosCriterios);
	}

	public void agregar(Criterio criterio) {
		this.getCriterios().add(criterio);
	}

	public boolean tieneCriterio(String nombre) {
		return this.getCriterios().stream()
				.anyMatch(criterio -> criterio.getNombreCriterio().toLowerCase().equals(nombre.toLowerCase()));
	}

	public void setCriterios(List<Criterio> contenido) {
		this.criterios = contenido;
	}

}