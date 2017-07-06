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
public class CrearCriterioViewModel {

	private List<Indicador> indicadores;
	private List<String> cuentas;
	private Indicador indicadorSeleccionado;
	private String cuentaSeleccionada;
	private String criterio = ""; //ex indicador
	private Integer constante;
	private String nombreCriterio; //ex nombreIndicador

	public CrearCriterioViewModel() {
		this.indicadores = RepositorioIndicadores.getInstance().getIndicadores();
		this.cuentas = RepositorioCuentas.getInstance().getCuentas();
	}

	public void crearIndicador() {
		if (nombreCriterio == null || criterio == "") {
			throw new Exception("Nombre o indicador vacio");
		} else if (!this.tieneNombreValido(nombreCriterio)
				|| RepositorioIndicadores.getInstance().tieneIndicador(nombreCriterio))
			throw new Exception("Nombre repetido o no válido");
		else {
			DataIndicador nuevoIndicador = new DataIndicador(nombreCriterio, criterio);
			RepositorioIndicadores.getInstance().agregar(nuevoIndicador);
			new ExportadorArchivos(new AdapterIndicadoresToJSON(), "./indicadores.json");
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

	public void borrarCriterio() {
		this.setCriterio("");
	}

	public void agregarMayor() {
		this.setCriterio(criterio + ">");
	}

	public void agregarMenor() {
		this.setCriterio(criterio + "<");
	}

	public void agregarIgual() {
		this.setCriterio(criterio + "=");
	}

	public void agregarConstante() {
		String constanteString = Integer.toString(constante);
		this.setCriterio(criterio + constanteString);
	}

	public void agregarIndicador() {
		if (indicadorSeleccionado == null) {
			throw new Exception("Seleccione algun indicador");
		} else {
			this.setCriterio(criterio + indicadorSeleccionado.getNombre());
		}
	}

	public void agregarCuenta() {
		if (cuentaSeleccionada == null) {
			throw new Exception("Seleccione alguna cuenta");
		} else {
			this.setCriterio(criterio + cuentaSeleccionada);
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

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public Integer getConstante() {
		return constante;
	}

	public void setConstante(Integer constante) {
		this.constante = constante;
	}

	public String getNombreIndicador() {
		return nombreCriterio;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreCriterio = nombreIndicador;
	}
}
