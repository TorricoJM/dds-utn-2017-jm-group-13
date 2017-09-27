package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class Repositorio<T>{
	
	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	protected EntityTransaction tx = entityManager.getTransaction();
	//el agregar muchos puede agregar un solo elemento con
	//una lista de 1 elemento, pero se mantiene este metodo
	//para simplicidad de los tests
	public void agregar(T elemento) {
		if(!tx.isActive()) {
		tx.begin();}
		entityManager.persist(elemento);
		tx.commit();
	}
	
	public void agregarMuchos(List<T> elementos) {
		if(!tx.isActive()) {
		tx.begin();}
		elementos.forEach(elemento->entityManager.persist(elemento));
		tx.commit();
	}
	
}