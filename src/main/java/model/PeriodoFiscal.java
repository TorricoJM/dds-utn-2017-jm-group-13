package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
public class PeriodoFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String periodo;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "periodofiscal_id")
	private List<Cuenta> cuentas = new LinkedList<>();

	public PeriodoFiscal() {
	}

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

	public void agregarUna(Cuenta nuevaCuenta) {
		this.cuentas.add(nuevaCuenta);
	}

	public Boolean tieneUna(Cuenta nuevaCuenta) {
		return this.getCuentas().stream()
				.anyMatch((unaCuenta) -> nuevaCuenta.getNombre().equals(unaCuenta.getNombre()));
	}

	private Cuenta obtenerCuentaAModificarDadaPor(Cuenta nuevaCuenta) {
		return this.getCuentas().stream()
				.filter((unaCuenta) -> nuevaCuenta.getNombre().equals(unaCuenta.getNombre()))
				.findFirst().get();
	}

	public boolean tieneCuenta(String cuentaOIndicador) {
		return this.getCuentas().stream().anyMatch(cuenta -> cuenta.getNombre().equals(cuentaOIndicador));
	}

	public Cuenta obtenerCuentaDesdeNombre(String cuentaOIndicador) {
		return this.getCuentas().stream().filter(cuenta -> cuenta.getNombre().equals(cuentaOIndicador)).findFirst()
				.get();
	}

	public void mergearCon(PeriodoFiscal nuevoPeriodo) {
		nuevoPeriodo.getCuentas().forEach((nuevaCuenta) -> this.cargarOActualizarUna(nuevaCuenta));
	}

	private void cargarOActualizarUna(Cuenta nuevaCuenta) {
		if (this.tieneUna(nuevaCuenta))
			this.obtenerCuentaAModificarDadaPor(nuevaCuenta).setValor(nuevaCuenta.getValor());
		else
			this.agregarUna(nuevaCuenta);
	}
}