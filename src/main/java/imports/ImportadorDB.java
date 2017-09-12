package imports;

import java.util.List;
import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class ImportadorDB<T>{

	@SuppressWarnings("unchecked")
	public List<T> importar(String query) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return em.createQuery(query).getResultList();
	}
	
}