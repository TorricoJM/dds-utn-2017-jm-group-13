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
			System.out.println(instance.indicadores.get(0).getNombre());
		}
		
		return instance;
	}
	
	private static List<DataIndicador> indicadores = new LinkedList<>();

	public List<DataIndicador> getIndicadores() {
		indicadores.stream().forEach(indicador -> System.out.println(indicador.getNombre()));
		return indicadores;
	}
	
	private void agregarIndicadoresPredefinidos(){
		this.agregar(PredefinidoPruebaAcida.getInstance());
		this.agregar(PredefinidoROA.getInstance());
		this.agregar(PredefinidoROE.getInstance());
		this.agregar(PredefinidoROI.getInstance());
	}
	
	public void setIndicadores(List<DataIndicador> nuevosIndicadores){
		this.indicadores.addAll(nuevosIndicadores);
	}
	
	public void agregar(DataIndicador indicador) {
		instance.indicadores.add(indicador);
	}

	public boolean tieneIndicador(String nombre) {
		return this.getIndicadores().stream().anyMatch(indicador -> indicador.getNombre().toLowerCase().equals(nombre.toLowerCase()));
	}

	public DataIndicador obtenerIndicadorDesdeNombre(String nombre) {
		return this.getIndicadores().stream().filter(indicador -> indicador.getNombre().equals(nombre))
				.findFirst().get();
	}

}