package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;


@Observable
public class PeriodoFiscal {

	private String periodo;
	private List<Cuenta> cuentas = new LinkedList<>();

	public PeriodoFiscal(String periodo) {
		this.periodo = periodo;
	}

	// -------------------GETTERS AND SETTERS
	public String getPeriodo() {
		return periodo;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	// -------------------/GETTERS AND SETTERS

	public void agregarUnaCuentaPara(LineaEmpresa empresa) {
		cuentas.add(new Cuenta(empresa.getCuenta(), empresa.getValor()));
	}

	public boolean yaExisteUnaCuentaPara(LineaEmpresa empresa) {
		return this.getCuentas().stream().anyMatch((unaCuenta) -> unaCuenta.getNombre().equals(empresa.getCuenta()));
	}

	private Cuenta obtenerCuentaAModificarDadaPor(LineaEmpresa lineaEmpresa) {
		return this.getCuentas().stream()
				.filter((cuentaDeLista) -> cuentaDeLista.getNombre().equals(lineaEmpresa.getCuenta())).findFirst()
				.get();
	}

	public void actualizarUnaCuentaPara(LineaEmpresa empresa) {
		if (this.yaExisteUnaCuentaPara(empresa))
			this.obtenerCuentaAModificarDadaPor(empresa).setValor(empresa.getValor());
		else
			this.agregarUnaCuentaPara(empresa);
	}

	public boolean tieneCuenta(String cuentaOIndicador) {
		return this.getCuentas().stream()
				.anyMatch(cuenta -> cuenta.getNombre().equals(cuentaOIndicador));
	}

	public Cuenta obtenerCuentaDesdeNombre(String cuentaOIndicador) {
		return this.getCuentas().stream()
				.filter(cuenta -> cuenta.getNombre().equals(cuentaOIndicador))
				.findFirst().get();
	}
}