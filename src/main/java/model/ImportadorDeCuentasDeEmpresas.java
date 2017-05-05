package model;

import java.util.List;
import repositories.Repositorios;

public abstract class ImportadorDeCuentasDeEmpresas {

	public void importarEmpresasDe(String path){
		this.cargarEnRepositorio(this.levantarEmpresasDelArchivo(path));
	} // metodo al cual se llama para importar las empresas, cualquiera sea el origen

	
	private void cargarEnRepositorio(List<LineaEmpresa> empresas) {
		empresas.stream().forEach((empresa) -> this.insertarEmpresaEnRepositorioLocal(empresa));
	}

	private void insertarEmpresaEnRepositorioLocal(LineaEmpresa lineaEmpresa) {
		if (ValidadorDeCamposDeEmpresa.getInstance().yaEstaCargadaEnRepositorioUna(lineaEmpresa)) {
			this.actualizarValorDe(lineaEmpresa);
		} else
			Repositorios.listaEmpresas.add(this.crearEmpresaEnBaseA(lineaEmpresa));
	}

	private void actualizarValorDe(LineaEmpresa lineaEmpresa) {
		this.traerEmpresaDelRepositorioDadaPor(lineaEmpresa).cargarOModificarCuentaParaUna(lineaEmpresa);
	}

	private Empresa crearEmpresaEnBaseA(LineaEmpresa lineaEmpresa) {
		Empresa nuevaEmpresa = new Empresa();

		nuevaEmpresa.setNombre(lineaEmpresa.getNombre());
		nuevaEmpresa.agregarPeriodoPara(lineaEmpresa);

		return nuevaEmpresa;
	}

	private Empresa traerEmpresaDelRepositorioDadaPor(LineaEmpresa lineaEmpresa) {
		return ValidadorDeCamposDeEmpresa.getInstance().obtenerEmpresaAActualizarPor(lineaEmpresa);
	}

	protected abstract List<LineaEmpresa> levantarEmpresasDelArchivo(String path);
}
