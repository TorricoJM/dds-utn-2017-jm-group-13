package repositories;

import java.util.List;

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
		String query = "Select u from User u where u.nombre = :name";
		User user = this.entityManager.createQuery(query, User.class).setParameter("name", nombre).getSingleResult();
		return user;
	}

	public List<User> getElementos() {
		List<User> users = this.entityManager.createQuery("from User", User.class).getResultList();
		return users;
	}

}
