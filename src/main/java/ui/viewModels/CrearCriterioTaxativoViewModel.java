package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.uqbar.commons.utils.Observable;

import criterios.Criterio;
import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Modificador;
import criterios.modificador.Normal;
import criterios.modificador.Promedio;
import criterios.modificador.Sumatoria;
import indicators.Indicador;
import model.Exception;
import repositories.RepositorioCriteriosTaxativos;
import repositories.RepositorioIndicadores;
import states.EstadoCrearTaxativos;

@Observable
public class CrearCriterioTaxativoViewModel {

	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	private String criterio = "";
	private String constante;
	private String nombreCriterio;
	private OperadorComparacion operador;
	private Modificador modificador = new Normal();
	private EstadoCrearTaxativos estado;

	public CrearCriterioTaxativoViewModel() {
		this.indicadores = new LinkedList<>(RepositorioIndicadores.getInstance().getElementos());
		this.estado = new EstadoCrearTaxativos();
	}

	public void crearCriterio() {
		this.validarCreacionDeCriterio();

		Criterio nuevoCriterio = new CriterioTaxativo(nombreCriterio, operador, indicadorSeleccionado, modificador,
				Double.valueOf(constante));
		RepositorioCriteriosTaxativos.getInstance().agregar(nuevoCriterio);

		//new ExportadorArchivos(new AdapterCriteriosToJSON(), "./criterios.json").exportar();
	}

	private void validarCreacionDeCriterio() {
		if (!this.tieneNombreValido(nombreCriterio) || RepositorioCriteriosTaxativos.getInstance().tieneCriterio(nombreCriterio))
			throw new Exception("El nombre del criterio ya existe, o es invalido");
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
		this.setModificador(new Normal());
		this.setOperador(null);
		this.setCriterio("");
		this.setConstante("");
		this.indicadorSeleccionado = null;

		this.estado.itsTimeForIndicators();
	}

	public void agregarMayor() {
		this.setOperador(OperadorComparacion.MAYOR);
		this.setCriterio(criterio + ">");

		this.estado.itsTimeForSomeConstant();
	}

	public void agregarMenor() {
		this.setOperador(OperadorComparacion.MENOR);
		this.setCriterio(criterio + "<");

		this.estado.itsTimeForSomeConstant();
	}

	public void agregarPromedio() {
		Modificador modificadorPromedio = new Promedio();
		this.setModificador(modificadorPromedio);
		this.setCriterio(criterio + "Promedio ");

		this.estado.disableModifiers();
	}

	public void agregarSumatoria() {
		Modificador modificadorSumatoria = new Sumatoria();
		this.setModificador(modificadorSumatoria);
		this.setCriterio(criterio + "Sumatoria ");

		this.estado.disableModifiers();
	}

	public void agregarConstante() {
		this.setCriterio(criterio + constante);
		this.estado.itsTimeForSave();
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}

	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;

		this.setCriterio(criterio + indicadorSeleccionado.getNombre());

		if (this.getOperador() == null)
			this.estado.itsTimeForOperations();
		else
			this.estado.itsTimeForSave();
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
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
	
	public EstadoCrearTaxativos getEstado() {
		return this.estado;
	}
}