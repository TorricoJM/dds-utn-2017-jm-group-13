package repositories.reposDB;

import model.Empresa;

public class RepositorioEmpresasDB extends RepositorioDB<Empresa>{
	
	public Empresa obtenerEmpresaDesdeNombre(String nombre) {
		String query = "Select e from Empresa e where e.nombre = :name";
		Empresa empresa = this.entityManager
				.createQuery(query, Empresa.class)
				.setParameter("name", nombre)
				.getSingleResult();
		return empresa;
	}
	
	public Double obtenerValorDeCuentaDeEmpresaEnPeriodo(String cuenta
			,String empresa,String periodo) {
		
		String query = "Select c.valor from Cuenta c join PeriodoFiscal p on (c.periodofiscal_id = p.id) join Empresa e on (e.id = p.empresa_id) where c.nombre= '"+ cuenta + "' and p.periodo='" + periodo + "' and e.nombre='" + empresa +"'";
		return Double.parseDouble(this.entityManager.createNativeQuery(query).getSingleResult().toString());
	}
}