package repositories;

import java.util.List;

import javax.persistence.NoResultException;

import indicators.Indicador;

public class RepositorioIndicadores extends Repositorio<Indicador> {

	private static RepositorioIndicadores instance;
	
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
		String query = "Select i from Indicador i where i.nombre = :name";
		Indicador indicador = this.entityManager
				.createQuery(query, Indicador.class)
				.setParameter("name", nombre).getSingleResult();
		return indicador;
	}

	
	public List<Indicador> getElementos(){
		List<Indicador> indicadores = this.entityManager.createQuery("from Indicador",Indicador.class).getResultList();
		return indicadores;
	}
	
	public List<Indicador> getElementosByUserID(long id){
		String query ="select * from Indicador where user_id = '"+String.valueOf(id)+"'";
		List<Indicador> indicadores = this.entityManager.createNativeQuery(query, Indicador.class).getResultList();
		return indicadores;
	}

}