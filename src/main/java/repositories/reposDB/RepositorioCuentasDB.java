package repositories.reposDB;

import java.util.List;

import javax.persistence.NoResultException;

import model.Cuenta;

public class RepositorioCuentasDB extends RepositorioDB<Cuenta>{
	
	public boolean tieneCuentaSegunNombre(String nombre) {
		try {
		String query = "Select c from Cuenta c where c.nombre = :name";
		Cuenta cuenta = this.entityManager.createQuery(query, Cuenta.class)
				.setParameter("name", nombre).getResultList().get(0);
		return cuenta.getNombre()!=null;
		} catch (NoResultException e) {
			return false;
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Cuenta> getElementos(){	
		return this.entityManager.createQuery("from Cuenta").getResultList();
	}
	
}
