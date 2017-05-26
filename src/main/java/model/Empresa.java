package model;

import java.util.LinkedList;
import java.util.List;
import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {

	private String nombre;
	private List<PeriodoFiscal> periodos = new LinkedList<>();

	public Empresa(String nombre) {
		this.nombre = nombre;
	}

	// -----------------------------------------------------GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}

	public List<PeriodoFiscal> getPeriodos() {
		return periodos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPeriodos(List<PeriodoFiscal> periodos) {
		this.periodos = periodos;
	}
	// ---------------------------------------------------/GETTERS AND SETTERS

	public void agregarPeriodoPara(LineaEmpresa lineaEmpresa) {
		PeriodoFiscal nuevoPeriodo = new PeriodoFiscal(lineaEmpresa.getPeriodo());

		nuevoPeriodo.agregarUnaCuentaPara(lineaEmpresa);

		this.periodos.add(nuevoPeriodo);
	}

	public boolean yaExisteUnPeriodoPara(LineaEmpresa lineaEmpresa) {
		return this.getPeriodos().stream()
				.anyMatch((unPeriodo) -> unPeriodo.getPeriodo().equals(lineaEmpresa.getPeriodo()));
	}

	private PeriodoFiscal obtenerPeriodoDeEmpresaDadaPor(LineaEmpresa lineaEmpresa) {
		return this.getPeriodos().stream()
				.filter((periodoDeLista) -> periodoDeLista.getPeriodo().equals(lineaEmpresa.getPeriodo())).findFirst()
				.get();
	}

	public void cargarOModificarCuentaParaUna(LineaEmpresa lineaEmpresa) {
		if (this.yaExisteUnPeriodoPara(lineaEmpresa)) {
			this.obtenerPeriodoDeEmpresaDadaPor(lineaEmpresa).actualizarUnaCuentaPara(lineaEmpresa);
		} else
			this.agregarPeriodoPara(lineaEmpresa);
	}

	public PeriodoFiscal obtenerPeriodoDesdeNombre(String periodoNombre) {
		return this.getPeriodos().stream()
				.filter(periodo -> periodo.getPeriodo().equals(periodoNombre))
				.findFirst().get();
	}
}