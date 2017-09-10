package repositories.repoArchivos;

import adapters.AdapterCuentasToJSON;
import exports.ExportadorArchivos;

public class RepositorioCuentas extends RepoArchivos<String> {

	private static RepositorioCuentas instance;

	public static RepositorioCuentas getInstance() {
		if (instance == null) {
			instance = new RepositorioCuentas();
		}

		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	public boolean tieneCuentaSegunNombre(String unaCuenta) {
		return this.getElementos().stream().anyMatch(cuenta -> cuenta.equals(unaCuenta));
	}

	public void refrescar() {
		RepositorioEmpresas.getInstance().getElementos().stream()
		.flatMap(empresa -> empresa.getCuentas().stream().map(cuenta->cuenta.getNombre()))
		.forEach(cuenta -> this.agregar(cuenta));

		this.actualizarArchivoCuentas();
	}

	private void actualizarArchivoCuentas() {
		new ExportadorArchivos(new AdapterCuentasToJSON(), "./cuentas.json");
	}
}
