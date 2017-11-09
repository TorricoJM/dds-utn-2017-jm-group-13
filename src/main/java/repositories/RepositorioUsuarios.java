package repositories;

import java.util.List;
import javax.persistence.NoResultException;
import user.User;

public class RepositorioUsuarios extends Repositorio<User> {

	private static RepositorioUsuarios instance;

	public static RepositorioUsuarios getInstance() {
		if (instance == null) {
			instance = new RepositorioUsuarios();
		}

		return instance;
	}

	private RepositorioUsuarios() {
	}

	public static void deleteInstance() {
		instance = null;
	}

	public User obtenerUserDesdeNombre(String nombre) {
		User user = null;
		
		try {
			user = this.entityManager.createQuery("from User where nombre like :nombre", User.class)
					.setParameter("nombre", "%" + nombre + "%").getSingleResult();
		} catch (NoResultException exc) {
				//FIXME: esto es horrible, pero hay que devolver null
		}
		
		return user;
	}

	public List<User> getElementos() {
		List<User> users = this.entityManager.createQuery("from User", User.class).getResultList();
		return users;
	}

}
