package model;

import java.util.LinkedList;
import java.util.List;
import org.uqbar.commons.utils.Observable;

@Observable
public class Empresa {

	private String nombre;
	private List<PeriodoFiscal> periodos = new LinkedList<>();

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

	public void agregarPeriodoPara(LineaEmpresa lineaEmpresa) {
		PeriodoFiscal nuevoPeriodo = new PeriodoFiscal();

		nuevoPeriodo.setPeriodo(lineaEmpresa.getPeriodo());
		nuevoPeriodo.agregarUnaCuentaPara(lineaEmpresa);

		this.periodos.add(nuevoPeriodo);
	}

	public void cargarOModificarCuentaParaUna(LineaEmpresa lineaEmpresa) {
		if (ValidadorDeCamposDeEmpresa.getInstance().yaExisteUnPeriodoPara(this, lineaEmpresa)) {
			ValidadorDeCamposDeEmpresa.getInstance().obtenerPeriodoDeEmpresaDadaPor(this, lineaEmpresa)
					.actualizarUnaCuentaPara(lineaEmpresa);
		} else
			this.agregarPeriodoPara(lineaEmpresa);
	}
}