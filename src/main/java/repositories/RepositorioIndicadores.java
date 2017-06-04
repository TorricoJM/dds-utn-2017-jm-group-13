package repositories;

import java.util.LinkedList;
import java.util.List;

import indicators.Indicador;

public class RepositorioIndicadores {

	public static List<Indicador> indicadores = new LinkedList<>();

	public static List<Indicador> all() {
		return indicadores;
	}

	public static void agregar(Indicador indicador) {
		indicadores.add(indicador);
	}

	public static boolean tieneIndicador(String nombre) {
		return RepositorioIndicadores.indicadores.stream().anyMatch(indicador -> indicador.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}

	public static Indicador obtenerIndicadorDesdeNombre(String nombre) {
		return RepositorioIndicadores.indicadores.stream().filter(indicador -> indicador.getNombre().equals(nombre))
				.findFirst().get();
	}

}