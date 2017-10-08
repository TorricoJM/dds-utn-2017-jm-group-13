package server;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;
import spark.Spark;
import spark.debug.DebugScreen;
import user.User;

public class Server {
	public static void main(String[] args) {
		seed();
		Spark.port(8000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
	
	private static void seed() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		
		if(entityManager.createQuery("Select i from Indicador i where i.nombre = 'ROA'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoROA());
		if(entityManager.createQuery("Select i from Indicador i where i.nombre = 'ROE'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoROE());
		if(entityManager.createQuery("Select i from Indicador i where i.nombre = 'ROI'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoROI());
		if(entityManager.createQuery("Select i from Indicador i where i.nombre = 'Prueba Acida'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoPruebaAcida());
		if(entityManager.createQuery("Select u from User u where u.nombre = 'Admin'").getResultList().isEmpty())
			entityManager.persist(new User("Admin", "Admin"));
		
		tx.commit();
	}

}
