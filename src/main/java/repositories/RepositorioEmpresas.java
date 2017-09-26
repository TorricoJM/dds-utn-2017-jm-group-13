package repositories;

import java.util.List;
import model.Empresa;
import model.LineaEmpresa;

public class RepositorioEmpresas extends Repositorio<Empresa> {

	private static RepositorioEmpresas instance;
	
	public static RepositorioEmpresas getInstance() {
		if(instance == null){
			instance = new RepositorioEmpresas();
		}
		
		return instance;
	}
	
	private RepositorioEmpresas(){
	}
	
	public static void deleteInstance() {
		instance = null;
	}

	public Empresa obtenerEmpresaAActualizarPor(LineaEmpresa unaEmpresa) {
		return this.getElementos().stream()
				.filter((empresaExistente) -> this.mismoNombreQue(unaEmpresa, empresaExistente))
				.findFirst().get();
	}

	public boolean yaEstaCargadaUna(LineaEmpresa lineaEmpresa) {
		return this.getElementos().stream()
				.anyMatch((empresaDeLaLista) -> this.mismoNombreQue(lineaEmpresa, empresaDeLaLista));
	}

	private boolean mismoNombreQue(LineaEmpresa unaEmpresa, Empresa empresaDeLaLista) {
		return unaEmpresa.getNombre().equals(empresaDeLaLista.getNombre());
	}

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
	
	@SuppressWarnings("unchecked")
	public List<Empresa> getElementos(){
		return this.entityManager.createQuery("from Empresa").getResultList();
	}
	
}