package repositories.reposDB;

import java.util.List;
import java.util.stream.Collectors;

import model.Cuenta;

public class RepositorioCuentasDB extends RepositorioDB<Cuenta>{
	
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
		
	public List<String> getElementos(){	
		return this.entityManager.createQuery("from Cuenta",Cuenta.class).getResultList().stream().map(cuenta->cuenta.getNombre()).collect(Collectors.toSet()).stream().collect(Collectors.toList());
	}
	
}
