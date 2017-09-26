package repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class Repositorio<T>{
	
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	
	//el agregar muchos puede agregar un solo elemento con
	//una lista de 1 elemento, pero se mantiene este metodo
	//para simplicidad de los tests
	public void agregar(T elemento) {
		entityManager.persist(elemento);
	}
	
	public void agregarMuchos(List<T> elementos) {
		elementos.forEach(elemento->entityManager.persist(elemento));
	}
	
}