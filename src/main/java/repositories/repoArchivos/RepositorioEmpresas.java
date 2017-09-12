package repositories.repoArchivos;

import java.util.NoSuchElementException;

import exports.ExportadorDB;
import imports.ImportadorDB;
import model.Empresa;
import model.LineaEmpresa;
import model.parser.ErrorEvaluacionException;

public class RepositorioEmpresas extends RepoArchivos<Empresa> {

	private static RepositorioEmpresas instance;
	
	public static RepositorioEmpresas getInstance() {
		if(instance == null){
			instance = new RepositorioEmpresas();
		}
		
		return instance;
	}
	
	private RepositorioEmpresas(){
		this.setElementos(new ImportadorDB<Empresa>()
				.importar("from Empresa"));
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
		return this.getElementos().stream().filter(empresa -> empresa.getNombre().equals(nombre))
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
	
	public void persistirEmpresas() {
		new ExportadorDB<>(this).exportar();
	}
}