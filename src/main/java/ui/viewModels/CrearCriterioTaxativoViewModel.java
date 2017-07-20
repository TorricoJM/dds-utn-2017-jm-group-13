package ui.viewModels;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.uqbar.commons.utils.Observable;

import adapters.AdapterCriteriosToJSON;
import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Modificador;
import criterios.modificador.Promedio;
import criterios.modificador.Sumatoria;
import exports.ExportadorArchivos;
import indicators.Indicador;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioCuentas;
import repositories.RepositorioIndicadores;

@Observable
public class CrearCriterioTaxativoViewModel {

	private List<Indicador> indicadores;
	private List<String> cuentas;
	private Indicador indicadorSeleccionado;
	private String cuentaSeleccionada;
	private String criterio = "";
	private Double constante;
	private String nombreCriterio;
	private OperadorComparacion operador;
	private Modificador modificador;

	public CrearCriterioTaxativoViewModel() {
		this.indicadores = RepositorioIndicadores.getInstance().getIndicadores();
		this.cuentas = RepositorioCuentas.getInstance().getCuentas();
	}

	public void crearCriterio() {
		if (nombreCriterio == null || criterio == "") {
			throw new Exception("Nombre o criterio vacio");
		} else if (!this.tieneNombreValido(nombreCriterio)
				|| RepositorioCriterios.getInstance().tieneCriterio(nombreCriterio))
			throw new Exception("El criterio ya existe o es invalido");
		else {
			CriterioTaxativo nuevoCriterio = new CriterioTaxativo(nombreCriterio, operador, indicadorSeleccionado, modificador, constante);
			RepositorioCriterios.getInstance().agregar(nuevoCriterio);
			new ExportadorArchivos(new AdapterCriteriosToJSON(), "./criterios.json");
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
		this.setModificador(null);
		this.setOperador(null);
		this.setCriterio("");
	}

	public void agregarMayor() {
		this.setOperador(OperadorComparacion.MAYOR);
		this.setCriterio(criterio + ">");
	}

	public void agregarMenor() {
		this.setOperador(OperadorComparacion.MENOR);
		this.setCriterio(criterio + "<");
	}

	public void agregarPromedio() {
		Modificador modificadorPromedio = new Promedio();
		this.setModificador(modificadorPromedio);
		this.setOperador(OperadorComparacion.MAYOR);
		this.setCriterio(criterio + "Promedio ");
	}

	public void agregarSumatoria() {
		Modificador modificadorSumatoria = new Sumatoria();
		this.setModificador(modificadorSumatoria);
		this.setCriterio(criterio + "Sumatoria ");
	}
	
	public void agregarConstante() {
		String constanteString = Double.toString(constante);
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

	public Double getConstante() {
		return constante;
	}

	public void setConstante(Double constante) {
		this.constante = constante;
	}

	public String getNombreCriterio() {
		return nombreCriterio;
	}

	public void setNombreCriterio(String nombreCriterio) {
		this.nombreCriterio = nombreCriterio;
	}

	public OperadorComparacion getOperador() {
		return operador;
	}

	public void setOperador(OperadorComparacion operador) {
		this.operador = operador;
	}

	public Modificador getModificador() {
		return modificador;
	}

	public void setModificador(Modificador modificador) {
		this.modificador = modificador;
	}
	
	
}