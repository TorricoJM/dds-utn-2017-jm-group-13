package repositories.reposDB;

import model.Empresa;

public class RepositorioEmpresasDB extends RepositorioDB<Empresa>{
	
	public Empresa obtenerEmpresaDesdeNombre(String nombre) {
		String query = "";
		Empresa empresa = this.entityManager
				.createQuery(query, Empresa.class)
				.setParameter("name", nombre)
				.getSingleResult();
		return empresa;
	}
	
	public double obtenerValorDeCuentaDeEmpresaEnPeriodo(String cuenta,String empresa,String periodo) {
		//TODO
		return 0;
	}
}