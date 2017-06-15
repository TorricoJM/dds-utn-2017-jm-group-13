package repositories;

import java.util.LinkedList;
import java.util.List;

import indicators.DataIndicador;
import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;

public class RepositorioIndicadores {

	private static RepositorioIndicadores instance;
	
	public static RepositorioIndicadores getInstance() {
		if(instance == null){
			instance = new RepositorioIndicadores();
			instance.agregarIndicadoresPredefinidos();
		}
		
		return instance;
	}
	
	private static List<DataIndicador> indicadores = new LinkedList<>();

	public List<DataIndicador> getIndicadores() {
		return indicadores;
	}
	
	private void agregarIndicadoresPredefinidos(){
		this.agregar(PredefinidoPruebaAcida.getInstance());
		this.agregar(PredefinidoROA.getInstance());
		this.agregar(PredefinidoROE.getInstance());
		this.agregar(PredefinidoROI.getInstance());
	}
	
	public void anexarIndicadores(List<DataIndicador> nuevosIndicadores){
		this.getIndicadores().addAll(nuevosIndicadores);
	}
	
	public void agregar(DataIndicador indicador) {
		this.getIndicadores().add(indicador);
	}

	public boolean tieneIndicador(String nombre) {
		return this.getIndicadores().stream().anyMatch(indicador -> indicador.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}

	public DataIndicador obtenerIndicadorDesdeNombre(String nombre) {
		return this.getIndicadores().stream().filter(indicador -> indicador.getNombre().equals(nombre))
				.findFirst().get();
	}

}