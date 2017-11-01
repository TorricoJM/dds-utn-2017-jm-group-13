package repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class Repositorio<T>{
	
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	public void agregar(T elemento) {
		//FIXME esto deberia ser una interfaz ahora
	}
	
	public void agregarMuchos(List<T> elementos) {
		//
	}
	
}