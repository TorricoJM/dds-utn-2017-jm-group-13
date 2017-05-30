package ui.viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Indicador;
import repositories.RepositorioCuentas;
import repositories.RepositorioIndicadores;

@Observable
public class CrearIndicadorViewModel {

	private List<Indicador> indicadores;
	private List<String> cuentas;
	private Indicador indicadorSeleccionado;
	private String cuentaSeleccionada;
	private String indicador = "";
	private Integer constante;

	public CrearIndicadorViewModel() {
		this.indicadores = RepositorioIndicadores.all();
		this.cuentas = RepositorioCuentas.all();
	}

	public void borrarIndicador() {
		this.setIndicador("");
	}

	public void agregarSuma() {
		this.setIndicador(indicador + "+");
	}

	public void agregarResta() {
		this.setIndicador(indicador + "-");
	}

	public void agregarDivision() {
		this.setIndicador(indicador + "/");
	}

	public void agregarMultiplicacion() {
		this.setIndicador(indicador + "*");
	}

	public void agregarParentesisIzquierdo() {
		this.setIndicador(indicador + "(");
	}

	public void agregarParentesisDerecho() {
		this.setIndicador(indicador + ")");
	}

	public void agregarConstante() {
		String constanteString = Integer.toString(constante);
		this.setIndicador(indicador + constanteString);
	}
	
	public void agregarIndicador() {
		this.setIndicador(indicador + indicadorSeleccionado.getNombre());
	}
	
	public void agregarCuenta() {
		this.setIndicador(indicador + cuentaSeleccionada);
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public List<String> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<String> cuentas) {
		this.cuentas = cuentas;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Integer getConstante() {
		return constante;
	}

	public void setConstante(Integer constante) {
		this.constante = constante;
	}

}
