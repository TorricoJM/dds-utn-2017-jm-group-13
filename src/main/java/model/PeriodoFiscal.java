package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class PeriodoFiscal {

	private String periodo;
	private List<CuentaYValor> cuentas = new LinkedList<>();

	public String getPeriodo() {
		return periodo;
	}

	public List<CuentaYValor> getCuentas() {
		return cuentas;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void setCuentas(List<CuentaYValor> cuentas) {
		this.cuentas = cuentas;
	}

	public void agregarUnaCuentaPara(LineaEmpresa empresa) {
		CuentaYValor cuentaCreada = new CuentaYValor();
		cuentaCreada.setCuenta(empresa.getCuenta());
		cuentaCreada.setValor(empresa.getValor());

		cuentas.add(cuentaCreada);
	}

	public void actualizarUnaCuentaPara(LineaEmpresa empresa) {
		if (ValidadorDeCamposDeEmpresa.getInstance().yaExisteUnaCuentaPara(this, empresa))
			ValidadorDeCamposDeEmpresa.getInstance().obtenerCuentaAModificarDadaPor(this, empresa)
					.setValor(empresa.getValor());
		else
			this.agregarUnaCuentaPara(empresa);
	}
}