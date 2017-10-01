package repositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import indicators.Indicador;
import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;

public class RepositorioIndicadores extends Repositorio<Indicador> {

	private static RepositorioIndicadores instance;
	private List<String> predefinidos = Arrays.asList("Prueba Acida","ROA","ROE","ROI");

	public static RepositorioIndicadores getInstance() {
		if (instance == null) {
			instance = new RepositorioIndicadores();
		}

		return instance;
	}
	
	private RepositorioIndicadores() {}

	public static void deleteInstance() {
		instance = null;
	}

	public boolean tieneIndicador(String nombre) {
		try {
		Indicador indicador = this.obtenerIndicadorDesdeNombre(nombre);
		return indicador!=null;
		} catch (NoResultException e) {
			return false;
		}
	}

	public Indicador obtenerIndicadorDesdeNombre(String nombre) {
		if(predefinidos.contains(nombre))
			return this.identificarPredefinido(nombre);
		String query = "Select i from Indicador i where i.nombre = :name";
		Indicador indicador = this.entityManager
				.createQuery(query, Indicador.class)
				.setParameter("name", nombre).getSingleResult();
		return indicador;
	}
	
	private Indicador identificarPredefinido(String nombre) {
		switch (nombre) {
			case "PredefinidoPruebaAcida": return new PredefinidoPruebaAcida();
			case "PredefinidoROA": return new PredefinidoROA();
			case "PredefinidoROE": return new PredefinidoROE();
			case "PredefinidoROI": return new PredefinidoROI();
			default: throw new NoResultException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Indicador> getElementos(){
		List<Indicador> indicadores = this.entityManager.createQuery("from Indicador").getResultList();
		indicadores.stream().filter(indicador->!this.predefinidos.contains(indicador.getNombre())).collect(Collectors.toList());
		indicadores.add(new PredefinidoPruebaAcida());
		indicadores.add(new PredefinidoROA());
		indicadores.add(new PredefinidoROE());
		indicadores.add(new PredefinidoROI());
		return indicadores;
	}
}