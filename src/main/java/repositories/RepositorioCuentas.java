package repositories;

import java.util.List;
import model.Cuenta;

public class RepositorioCuentas extends Repositorio<Cuenta> {

	private static RepositorioCuentas instance;

	public static RepositorioCuentas getInstance() {
		if (instance == null) {
			instance = new RepositorioCuentas();
		}

		return instance;
	}
	
	private RepositorioCuentas() {}

	public static void deleteInstance() {
		instance = null;
	}

	public boolean tieneCuentaSegunNombre(String nombre) {
		try {
		String query = "Select c from Cuenta c where c.nombre = :name";
		Cuenta cuenta = this.entityManager.createQuery(query, Cuenta.class)
				.setParameter("name", nombre).getResultList().get(0);
		return cuenta.getNombre()!=null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
		
	public List<Cuenta> getElementos(){	
		return this.entityManager.createQuery("from Cuenta",Cuenta.class).getResultList();
	}
	
}
