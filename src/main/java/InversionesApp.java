import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;
import ui.windows.*;

public class InversionesApp extends Application{
	
	public static void main(String[] args) {
		//seed();
		new InversionesApp().start();
	}
	
	@Override
	protected Window<?> createMainWindow() {
		return new MenuPrincipalWindow(this);
	}
	
	@SuppressWarnings("unused")
	private static void seed() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		
		if(entityManager.createQuery("from PredefinidoROA where nombre = 'ROA'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoROA());
		if(entityManager.createQuery("from PredefinidoROE where nombre = 'ROE'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoROE());
		if(entityManager.createQuery("from PredefinidoROI where nombre = 'ROI'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoROI());
		if(entityManager.createQuery("from PredefinidoPruebaAcida where nombre = 'Prueba Acida'").getResultList().isEmpty())
			entityManager.persist(new PredefinidoPruebaAcida());
		
		tx.commit();
	}
}