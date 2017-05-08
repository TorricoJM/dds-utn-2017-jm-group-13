package model;

import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class PeriodoFiscal {

	private String periodo;
	private List<CuentaYValor> cuentas = new LinkedList<>();
	
	public PeriodoFiscal(String periodo){
		this.periodo = periodo;
	}

//-------------------GETTERS AND SETTERS
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
//-------------------/GETTERS AND SETTERS
	
	public void agregarUnaCuentaPara(LineaEmpresa empresa) {
		cuentas.add(new CuentaYValor(empresa.getCuenta(),empresa.getValor()));
	}
	
	private boolean yaExisteUnaCuentaPara(LineaEmpresa empresa) {
		return this.getCuentas().stream().anyMatch((unaCuenta) -> unaCuenta.getCuenta().equals(empresa.getCuenta()));
	}

	private CuentaYValor obtenerCuentaAModificarDadaPor(LineaEmpresa lineaEmpresa) {
		return this.getCuentas().stream()
				.filter((cuentaDeLista) -> cuentaDeLista.getCuenta().equals(lineaEmpresa.getCuenta())).findFirst()
				.get();
	}
	
	public void actualizarUnaCuentaPara(LineaEmpresa empresa) {
		if (this.yaExisteUnaCuentaPara(empresa))
			this.obtenerCuentaAModificarDadaPor(empresa).setValor(empresa.getValor());
		else
			this.agregarUnaCuentaPara(empresa);
	}
}