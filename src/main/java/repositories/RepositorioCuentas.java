package repositories;

import java.util.LinkedList;
import java.util.List;

import model.CuentaYValor;
import model.ExportadorCuentas;

public class RepositorioCuentas {

	public static List<String> cuentas = new LinkedList<>();

	public static List<String> all() {
		return cuentas;
	}

	public static boolean tieneCuenta(String cuentaOIndicador) {
		return RepositorioCuentas.cuentas.stream().anyMatch(cuenta -> cuenta.equals(cuentaOIndicador));
	}

	private static void agregarCuenta(CuentaYValor cuenta) {
		if (!RepositorioCuentas.tieneCuenta(cuenta.getCuenta())) {
			RepositorioCuentas.cuentas.add(cuenta.getCuenta());
		}
	}

	public static void refrescar() {
		List<CuentaYValor> cuentas = new LinkedList<>();
		RepositorioEmpresas.listaEmpresas.forEach(empresa -> cuentas.addAll(empresa.getCuentas()));
		cuentas.forEach(cuenta -> RepositorioCuentas.agregarCuenta(cuenta));

		RepositorioCuentas.actualizarArchivoCuentas();
	}

	private static void actualizarArchivoCuentas() {
		new ExportadorCuentas().exportar();
	}
}
