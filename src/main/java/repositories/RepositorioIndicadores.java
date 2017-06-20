package repositories;

import java.util.LinkedList;
import java.util.List;

import indicators.DataIndicador;

public class RepositorioIndicadores {

	private static RepositorioIndicadores instance;

	public static RepositorioIndicadores getInstance() {
		if (instance == null) {
			instance = new RepositorioIndicadores();
		}

		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	private List<DataIndicador> indicadores = new LinkedList<>();

	public List<DataIndicador> getIndicadores() {
		return indicadores;
	}

	public void anexarIndicadores(List<DataIndicador> nuevosIndicadores) {
		this.getIndicadores().addAll(nuevosIndicadores);
	}

	public void agregar(DataIndicador indicador) {
		this.getIndicadores().add(indicador);
	}

	public boolean tieneIndicador(String nombre) {
		return this.getIndicadores().stream()
				.anyMatch(indicador -> indicador.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}

	public DataIndicador obtenerIndicadorDesdeNombre(String nombre) {
		return this.getIndicadores().stream().filter(indicador -> indicador.getNombre().equals(nombre)).findFirst()
				.get();
	}

}