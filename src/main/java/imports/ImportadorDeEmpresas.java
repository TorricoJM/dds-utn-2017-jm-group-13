package imports;

import java.util.List;
import java.util.stream.Collectors;

import model.Empresa;
import model.LineaEmpresa;
import repositories.RepositorioEmpresas;

public abstract class ImportadorDeEmpresas implements Importador{

	@Override
	public void importar(){
		this.cargarEnRepositorio(this.obtenerEmpresas());;
	}

	private void cargarEnRepositorio(List<LineaEmpresa> lineasEmpresas) {
		List<Empresa> empresas = lineasEmpresas.stream().map(lineaEmpresa -> this.crearEmpresaEnBaseA(lineaEmpresa)).collect(Collectors.toList());
		RepositorioEmpresas.getInstance().agregarMuchos(empresas);
	}

	private Empresa crearEmpresaEnBaseA(LineaEmpresa lineaEmpresa) {
		Empresa nuevaEmpresa = new Empresa(lineaEmpresa.getNombre());
		nuevaEmpresa.agregarPeriodoPara(lineaEmpresa);

		return nuevaEmpresa;
	}

	protected abstract List<LineaEmpresa> obtenerEmpresas();
}
