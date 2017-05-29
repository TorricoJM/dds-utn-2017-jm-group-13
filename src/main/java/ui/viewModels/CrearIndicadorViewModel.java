package ui.viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.CuentaYValor;
import model.Indicador;
import repositories.RepositorioCuentas;
import repositories.RepositorioIndicadores;

@Observable
public class CrearIndicadorViewModel {

	private List<Indicador> indicadores;
	private List<String> cuentas;
	private Indicador indicadorSeleccionado;
	private CuentaYValor cuentaSeleccionada;

	public CrearIndicadorViewModel() {
		this.indicadores = RepositorioIndicadores.all();
		this.cuentas = RepositorioCuentas.all();
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

	public CuentaYValor getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(CuentaYValor cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

}
