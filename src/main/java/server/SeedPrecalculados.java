package server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import repositories.RepositorioUsuarios;
import user.User;

public class SeedPrecalculados{
	
	private static SeedPrecalculados instance;
	
	public static SeedPrecalculados getInstance() {
		if(instance == null) {
			instance = new SeedPrecalculados();
		}
		return instance;
	}
	
	private SeedPrecalculados() {
	}
	
	public static void deleteInstance() {
		instance = null;
	}
	
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	EntityTransaction tx = entityManager.getTransaction();
	
	public SeedPrecalculados removerResultadosActuales() {
		tx.begin();
		entityManager.createQuery("DELETE FROM IndicadorConResultado").executeUpdate();
		tx.commit();
		return instance;
	}
	
	public SeedPrecalculados precalcular() {
		tx.begin();
		List<User> usuarios = RepositorioUsuarios.getInstance().getElementos();
		usuarios.forEach(user->precalcularIndicadoresDeUsuario(user));
		RepositorioUsuarios.getInstance().agregarMuchos(usuarios);
		tx.commit();
		return instance;
	}

	private void precalcularIndicadoresDeUsuario(User user) {
		user.getIndicadores().forEach(indicador->indicador.obtenerPrecalculados());
	}
	
	
}