package repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class Repositorio<T>{
	
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	public void agregar(T elemento) {
		entityManager.persist(elemento);
	}
	
	public void agregarMuchos(List<T> elementos) {
		elementos.forEach(elemento->entityManager.persist(elemento));
	}
	
}