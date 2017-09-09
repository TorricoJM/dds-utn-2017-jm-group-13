package repositories.repoArchivos;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Empresa;
import model.LineaEmpresa;
import model.parser.ErrorEvaluacionException;

public class RepositorioEmpresas {

	private static RepositorioEmpresas instance;
	
	public static RepositorioEmpresas getInstance() {
		if(instance == null){
			instance = new RepositorioEmpresas();
		}
		
		return instance;
	}
	
	public static void deleteInstance() {
		instance = null;
	}
	
	private List<Empresa> listaEmpresas = new LinkedList<>();

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void agregar(Empresa empresa) {
		listaEmpresas.add(empresa);
	}

	public Empresa obtenerEmpresaAActualizarPor(LineaEmpresa unaEmpresa) {
		return this.getListaEmpresas().stream()
				.filter((empresaExistente) -> this.mismoNombreQue(unaEmpresa, empresaExistente))
				.findFirst().get();
	}

	public boolean yaEstaCargadaUna(LineaEmpresa lineaEmpresa) {
		return this.getListaEmpresas().stream()
				.anyMatch((empresaDeLaLista) -> this.mismoNombreQue(lineaEmpresa, empresaDeLaLista));
	}

	private boolean mismoNombreQue(LineaEmpresa unaEmpresa, Empresa empresaDeLaLista) {
		return unaEmpresa.getNombre().equals(empresaDeLaLista.getNombre());
	}

	public Empresa obtenerEmpresaDesdeNombre(String nombre) {
		return this.getListaEmpresas().stream().filter(empresa -> empresa.getNombre().equals(nombre))
				.findFirst().get();
	}

	public double obtenerValorDeCuentaDeEmpresaEnPeriodo(String cuenta, String empresa,
			String periodo) {
		try {
		return Double.parseDouble(this.obtenerEmpresaDesdeNombre(empresa)
				.obtenerPeriodoDesdeNombre(periodo).obtenerCuentaDesdeNombre(cuenta)
				.getValor());
		} catch (NoSuchElementException e){
			throw new ErrorEvaluacionException("No se encuentra la cuenta: \""+ cuenta + "\" de la empresa: \"" + empresa + "\" para el periodo: " + periodo);
		}
	}
}