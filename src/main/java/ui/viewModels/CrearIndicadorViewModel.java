package ui.viewModels;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.uqbar.commons.utils.Observable;

import adapters.AdapterIndicadoresToJSON;
import exports.ExportadorArchivos;
import indicators.DataIndicador;
import indicators.Indicador;
import model.Exception;
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
		this.indicadores = RepositorioIndicadores.getInstance().getIndicadores();
		this.cuentas = RepositorioCuentas.getInstance().getCuentas();
	}

	public void crearIndicador() {
		if (nombreIndicador == null || indicador == "") {
			throw new Exception("Nombre o indicador vacio");
		} else if (!this.tieneNombreValido(nombreIndicador)
				|| RepositorioIndicadores.getInstance().tieneIndicador(nombreIndicador))
			throw new Exception("Nombre repetido o no válido");
		else {
			DataIndicador nuevoIndicador = new DataIndicador(nombreIndicador, indicador);
			RepositorioIndicadores.getInstance().agregar(nuevoIndicador);
			new ExportadorArchivos(new AdapterIndicadoresToJSON(), "./indicadores.json").exportar();
		}
	}

	private boolean tieneNombreValido(String nombre) {
		final String Regex = "[a-zA-Z]+[a-zA-Z ]*[a-zA-Z]+";
		final String input = nombre;
		Pattern patron;
		Matcher matcheador;
		patron = Pattern.compile(Regex);
		matcheador = patron.matcher(input);
		return matcheador.matches();
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
