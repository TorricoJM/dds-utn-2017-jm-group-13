package repositories;

import java.util.LinkedList;
import java.util.List;

import model.Empresa;
import model.LineaEmpresa;

public class RepositorioEmpresas {

	public static List<Empresa> listaEmpresas = new LinkedList<>();

	public static List<Empresa> all() {
		return listaEmpresas;
	}

	public static void agregar(Empresa empresa) {
		listaEmpresas.add(empresa);
	}

	public static Empresa obtenerEmpresaAActualizarPor(LineaEmpresa unaEmpresa) {
		return RepositorioEmpresas.listaEmpresas.stream()
				.filter((empresaExistente) -> RepositorioEmpresas.mismoNombreQue(unaEmpresa, empresaExistente))
				.findFirst().get();
	}

	public static boolean yaEstaCargadaUna(LineaEmpresa lineaEmpresa) {
		return RepositorioEmpresas.listaEmpresas.stream()
				.anyMatch((empresaDeLaLista) -> RepositorioEmpresas.mismoNombreQue(lineaEmpresa, empresaDeLaLista));
	}

	private static boolean mismoNombreQue(LineaEmpresa unaEmpresa, Empresa empresaDeLaLista) {
		return unaEmpresa.getNombre().equals(empresaDeLaLista.getNombre());
	}
}
