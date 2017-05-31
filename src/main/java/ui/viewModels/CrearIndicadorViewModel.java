package ui.viewModels;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import model.Exception;
import model.ExportadorIndicadores;
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
	private String nombreIndicador;

	public CrearIndicadorViewModel() {
		this.indicadores = RepositorioIndicadores.all();
		this.cuentas = RepositorioCuentas.all();
	}

	public void crearIndicador() {
		if (nombreIndicador == null || indicador == "") {
			throw new Exception("Nombre o indicador vacio");
		} else {
			Indicador nuevoIndicador = new Indicador(nombreIndicador, indicador);
			RepositorioIndicadores.agregar(nuevoIndicador);
			new ExportadorIndicadores().exportar();
		}
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
		if (indicadorSeleccionado == null) {
			throw new Exception("Seleccione algun indicador");
		} else {
			this.setIndicador(indicador + indicadorSeleccionado.getNombre());
		}
	}

	public void agregarCuenta() {
		if (cuentaSeleccionada == null) {
			throw new Exception("Seleccione alguna cuenta");
		} else {
			this.setIndicador(indicador + cuentaSeleccionada);
		}
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

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
}
