package imports;

import java.util.List;

import model.Empresa;
import model.LineaEmpresa;
import repositories.repoArchivos.RepositorioCuentas;
import repositories.repoArchivos.RepositorioEmpresas;

public abstract class ImportadorDeEmpresas implements Importador{

	@Override
	public void importar(){
		this.importarEmpresas();
	}
	
	protected void importarEmpresas() {
		this.cargarEnRepositorio(this.obtenerEmpresas());
		RepositorioCuentas.getInstance().refrescar();//con esto, actualizo el repo de cuentas
	}

	private void cargarEnRepositorio(List<LineaEmpresa> empresas) {
		empresas.stream().forEach((empresa) -> this.insertarEmpresaEnRepositorioLocal(empresa));
		RepositorioEmpresas.getInstance().persistirEmpresas();
	}

	private void insertarEmpresaEnRepositorioLocal(LineaEmpresa lineaEmpresa) {
		if (RepositorioEmpresas.getInstance().yaEstaCargadaUna(lineaEmpresa)) {
			this.actualizarValorDe(lineaEmpresa);
		} else
			RepositorioEmpresas.getInstance().agregar(this.crearEmpresaEnBaseA(lineaEmpresa));
	}

	private Empresa crearEmpresaEnBaseA(LineaEmpresa lineaEmpresa) {
		Empresa nuevaEmpresa = new Empresa(lineaEmpresa.getNombre());
		nuevaEmpresa.agregarPeriodoPara(lineaEmpresa);

		return nuevaEmpresa;
	}

	private void actualizarValorDe(LineaEmpresa lineaEmpresa) {
		this.traerEmpresaDelRepositorioDadaPor(lineaEmpresa).cargarOModificarCuentaParaUna(lineaEmpresa);
	}

	private Empresa traerEmpresaDelRepositorioDadaPor(LineaEmpresa lineaEmpresa) {
		return RepositorioEmpresas.getInstance().obtenerEmpresaAActualizarPor(lineaEmpresa);
	}

	protected abstract List<LineaEmpresa> obtenerEmpresas();
}
