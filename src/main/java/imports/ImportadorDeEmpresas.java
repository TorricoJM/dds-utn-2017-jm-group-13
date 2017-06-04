package imports;

import java.util.List;

import model.Empresa;
import model.LineaEmpresa;
import repositories.RepositorioCuentas;
import repositories.RepositorioEmpresas;

public abstract class ImportadorDeEmpresas extends ImportadorDeArchivos{

	@Override
	public void importar(){
		this.importarEmpresas();
	}
	
	protected void importarEmpresas() {
		this.cargarEnRepositorio(this.obtenerEmpresas());
		RepositorioCuentas.refrescar();//con esto, actualizo el repo de cuentas
	}

	private void cargarEnRepositorio(List<LineaEmpresa> empresas) {
		empresas.stream().forEach((empresa) -> this.insertarEmpresaEnRepositorioLocal(empresa));
	}

	private void insertarEmpresaEnRepositorioLocal(LineaEmpresa lineaEmpresa) {
		if (RepositorioEmpresas.yaEstaCargadaUna(lineaEmpresa)) {
			this.actualizarValorDe(lineaEmpresa);
		} else
			RepositorioEmpresas.agregar(this.crearEmpresaEnBaseA(lineaEmpresa));
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
		return RepositorioEmpresas.obtenerEmpresaAActualizarPor(lineaEmpresa);
	}

	protected abstract List<LineaEmpresa> obtenerEmpresas();
}
