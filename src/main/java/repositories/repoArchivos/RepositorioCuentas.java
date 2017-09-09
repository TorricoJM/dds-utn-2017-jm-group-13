package repositories.repoArchivos;

import adapters.AdapterCuentasToJSON;
import exports.ExportadorArchivos;
import model.Cuenta;

public class RepositorioCuentas extends RepoArchivos<Cuenta> {

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

	public boolean tieneCuentaSegunNombre(Cuenta unaCuenta) {
		return this.getElementos().stream().anyMatch(cuenta -> cuenta.getNombre().equals(unaCuenta.getNombre()));
	}

	public void refrescar() {
		RepositorioEmpresas.getInstance().getElementos().stream().flatMap(empresa -> empresa.getCuentas().stream())
				.forEach(cuenta -> this.agregar(cuenta));

		this.actualizarArchivoCuentas();
	}

	private void actualizarArchivoCuentas() {
		new ExportadorArchivos(new AdapterCuentasToJSON(), "./cuentas.json");
	}
}
