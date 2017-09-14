package repositories.reposDB;

import java.util.List;

import javax.persistence.NoResultException;

import indicators.DataIndicador;
import indicators.Indicador;

public class RepositorioIndicadoresDB extends RepositorioDB<DataIndicador>{
	
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
	
	@SuppressWarnings("unchecked")
	public List<Indicador> getElementos(){
		return this.entityManager.createQuery("from Indicador").getResultList();
	}
}