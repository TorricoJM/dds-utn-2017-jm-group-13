package repositories;

import java.util.LinkedList;
import java.util.List;

import indicators.Indicador;
import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;

public class RepositorioIndicadores {

	private static RepositorioIndicadores instance;

	public static RepositorioIndicadores getInstance() {
		if (instance == null) {
			instance = new RepositorioIndicadores();
			instance.agregar(PredefinidoPruebaAcida.getInstance());
			instance.agregar(PredefinidoROA.getInstance());
			instance.agregar(PredefinidoROE.getInstance());
			instance.agregar(PredefinidoROI.getInstance());
		}

		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	private List<Indicador> indicadores = new LinkedList<>();

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void anexarIndicadores(List<Indicador> nuevosIndicadores) {
		this.getIndicadores().addAll(nuevosIndicadores);
	}

	public void agregar(Indicador indicador) {
		this.getIndicadores().add(indicador);
	}

	public boolean tieneIndicador(String nombre) {
		return this.getIndicadores().stream()
				.anyMatch(indicador -> indicador.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}

	public Indicador obtenerIndicadorDesdeNombre(String nombre) {
		return this.getIndicadores().stream().filter(indicador -> indicador.getNombre().equals(nombre)).findFirst()
				.get();
	}
}