package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import model.ImportadorDeEmpresasCSV;

@Observable
public class SelectorArchivosViewModel {

	private String pathArchivo;
	private String nombreParaMostrar;

	// -------------------------------------------GETTERS AND SETTERS
	public String getPathArchivo() {
		return pathArchivo;
	}

	public String getNombreParaMostrar() {
		return nombreParaMostrar;
	}

	public void setPathArchivo(String pathArchivo) {
		this.pathArchivo = pathArchivo;
		this.setNombreParaMostrar(this.obtenerNombreDelPath());
	}
	
	public void setNombreParaMostrar(String nombre){
		this.nombreParaMostrar = nombre;
	}
	// ------------------------------------------/GETTERS AND SETTERS

	
	public void importarAchivo() {
		new ImportadorDeEmpresasCSV(this.getPathArchivo()).importarEmpresas();
	}

	private String obtenerNombreDelPath() {
		return this.pathArchivo.substring(this.pathArchivo.lastIndexOf('\\') + 1);
	}
}