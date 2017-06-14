package repositories;

import java.util.LinkedList;
import java.util.List;

import exports.ExportadorCuentas;
import model.CuentaYValor;

public class RepositorioCuentas {

	private static RepositorioCuentas instance;

	public static RepositorioCuentas getInstance() {
		if (instance == null) {
			instance = new RepositorioCuentas();
		}

		return instance;
	}

	private List<String> cuentas = new LinkedList<>();

	public List<String> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<String> cuentasNuevas) {
		this.cuentas = cuentasNuevas;
	}

	public boolean tieneCuenta(String cuentaOIndicador) {
		return this.getCuentas().stream().anyMatch(cuenta -> cuenta.equals(cuentaOIndicador));
	}

	private void agregarCuenta(CuentaYValor cuenta) {
		if (!this.tieneCuenta(cuenta.getCuenta())) {
			this.getCuentas().add(cuenta.getCuenta());
		}
	}

	public void refrescar() {
		List<CuentaYValor> cuentas = new LinkedList<>();
		RepositorioEmpresas.getInstance().getListaEmpresas().forEach(empresa -> cuentas.addAll(empresa.getCuentas()));
		cuentas.forEach(cuenta -> this.agregarCuenta(cuenta));

		this.actualizarArchivoCuentas();
	}

	private void actualizarArchivoCuentas() {
		new ExportadorCuentas().exportar();
	}
}
