package exports;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import repositories.repoArchivos.RepoArchivos;
import model.Exception;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class ExportadorDB<T> implements Exportador {
	
	private EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	private EntityTransaction tx = entityManager.getTransaction();
	RepoArchivos<T> repo;
	
	public ExportadorDB(RepoArchivos<T> unRepo) {
		this.repo = unRepo;
	}
	
	
	@Override
	public void exportar() {
		try{
			tx.begin();
			repo.getElementos().forEach(elemento->entityManager.persist(elemento));
			tx.commit();
		}catch (Exception e) {
			throw new Exception("Rollback. Persistencia de empresas");
		}
	}
}