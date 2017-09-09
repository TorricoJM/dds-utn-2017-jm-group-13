package repositories.repoArchivos;

import java.util.LinkedList;
import java.util.List;

import indicators.Indicador;
import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;

public class RepositorioIndicadores extends RepoArchivos<Indicador> {

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
	
	public List<Indicador> getIndicatorsForExport() {
		List<Indicador> indicatorsForExport = new LinkedList<>(this.getElementos());
		
		indicatorsForExport.remove(indicatorsForExport.indexOf(PredefinidoPruebaAcida.getInstance()));
		indicatorsForExport.remove(indicatorsForExport.indexOf(PredefinidoROE.getInstance()));
		indicatorsForExport.remove(indicatorsForExport.indexOf(PredefinidoROA.getInstance()));
		indicatorsForExport.remove(indicatorsForExport.indexOf(PredefinidoROI.getInstance()));
		
		return indicatorsForExport;
	}

	public boolean tieneIndicador(String nombre) {
		return this.getElementos().stream()
				.anyMatch(indicador -> indicador.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}

	public Indicador obtenerIndicadorDesdeNombre(String nombre) {
		return this.getElementos().stream().filter(indicador -> indicador.getNombre().equals(nombre)).findFirst()
				.get();
	}
}