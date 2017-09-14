package repositories.reposDB;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import repositories.Repositorio;

public abstract class RepositorioDB<T> implements Repositorio<T>{


	protected EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	protected EntityTransaction tx;
	
	public void agregar(T elemento) {
		tx = entityManager.getTransaction();
		if(!tx.isActive()) {
			tx.begin();
		}
		entityManager.persist(elemento);
		tx.commit();
	}
	
	public void agregarMuchos(List<T> elementos) {
		tx = entityManager.getTransaction();
		if(!tx.isActive()) {
			tx.begin();
		}
		elementos.forEach(elemento->entityManager.persist(elemento));
		tx.commit();
	}
}