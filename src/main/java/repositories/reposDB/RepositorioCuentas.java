package repositories.reposDB;

import model.Cuenta;

public class RepositorioCuentas extends RepositorioDB<Cuenta>{
	
	public boolean tieneCuentaSegunNombre(String nombre){
		tx.begin();
		//TODO
		entityManager.createQuery("");
		return false;
	}
}