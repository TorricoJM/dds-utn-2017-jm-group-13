package repositories;

import java.util.LinkedList;
import java.util.List;

import model.Indicador;

public class RepositorioIndicadores {

	public static List<Indicador> indicadores = new LinkedList<>();

	public static void agregar(Indicador indicador) {
		indicadores.add(indicador);
	}
}
