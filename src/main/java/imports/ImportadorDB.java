package imports;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class ImportadorDB<T>{

	@SuppressWarnings("unchecked")
	public List<T> importar(String query) {
		return PerThreadEntityManagers
				.getEntityManager().createQuery(query).getResultList();
	}
	
}