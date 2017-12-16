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
import imports.ScheduledImport;

public class Server {
	public static void main(String[] args) {
		if ("app".equals(args[0])) {
			SeedPrecalculados.getInstance().removerResultadosActuales().precalcular();
			seed();
			Spark.port(8000);
			DebugScreen.enableDebugScreen();
			Router.configure();
		}
		if ("batch".equals(args[0])) {
			ScheduledImport importProgramado = new ScheduledImport();
			importProgramado.importarCadaXMinutos(1);
		}
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
		
		if(entityManager.createQuery("Select u from User u where u.nombre = 'admin'").getResultList().isEmpty()){
			User usuario = new User("admin","admin");
			/*DataIndicador datI = new DataIndicador("IndicadorCustomDefault","ebitda+1");
			usuario.agregarIndicador(datI);
			
			List<CriterioTaxativo> crits = new LinkedList<>();
			crits.add(new CriterioTaxativo("Margenes consistentemente crecientes", OperadorComparacion.MAYOR,
					datI, new Normal(), 1));
			
			Metodologia met = new Metodologia("metoCustom",crits,null);
			
			usuario.agregarMetodologia(met);*/ //este asco es para tener una meto de prueba 
			
			
			entityManager.persist(usuario);
		}
			/* creo un user default con dependencias default*/

		tx.commit();
	}

}
