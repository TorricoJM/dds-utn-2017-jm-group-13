package repositories;

import java.util.LinkedList;
import java.util.List;

public class RepositorioCuentas {

	public static List<String> cuentas = new LinkedList<>();

	public static List<String> all() {
		return cuentas;
	}

	public static boolean tieneCuenta(String cuentaOIndicador) {
		return RepositorioCuentas.cuentas.stream().anyMatch(cuenta -> cuenta.equals(cuentaOIndicador));
	}

}
