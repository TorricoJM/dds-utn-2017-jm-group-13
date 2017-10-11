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

	public List<User> obtenerUserDesdeNombre(String nombre) {
		return this.entityManager
				.createQuery("from User where nombre like :nombre",User.class) 
				.setParameter("nombre", "%" + nombre + "%") //
				.getResultList();
	}

	public List<User> getElementos() {
		List<User> users = this.entityManager.createQuery("from User", User.class).getResultList();
		return users;
	}

}
