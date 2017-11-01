package repositories;

import java.util.List;

import javax.persistence.EntityTransaction;

import model.Empresa;

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

	public Empresa obtenerEmpresaAActualizarPor(Empresa unaEmpresa) {
		return this.getElementos().stream()
				.filter((empresaExistente) -> this.mismoNombreQue(unaEmpresa, empresaExistente))
				.findFirst().get();
	}

	public boolean yaEstaCargadaUna(Empresa nuevaEmpresa) {
		return this.getElementos().stream()
				.anyMatch((empresaDeLaLista) -> this.mismoNombreQue(nuevaEmpresa, empresaDeLaLista));
	}

	private boolean mismoNombreQue(Empresa unaEmpresa, Empresa empresaDeLaLista) {
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
	
	@Override
	public void agregarMuchos(List<Empresa> nuevasEmpresas) {
		nuevasEmpresas.forEach((nuevaEmpresa) -> this.actualizarOCargar(nuevaEmpresa));
		EntityTransaction tx = entityManager.getTransaction();
		
		if(!tx.isActive()) {
		tx.begin();}
		RepositorioEmpresas.getInstance().getElementos().forEach((elem) -> entityManager.persist(elem));
		tx.commit();
	}
	
	private void actualizarOCargar(Empresa nuevaEmpresa) {
		if (RepositorioEmpresas.getInstance().yaEstaCargadaUna(nuevaEmpresa)) {
			this.obtenerEmpresaAActualizarPor(nuevaEmpresa).mergearCon(nuevaEmpresa);
		} else
			RepositorioEmpresas.getInstance().agregar(nuevaEmpresa);
	}
	
}