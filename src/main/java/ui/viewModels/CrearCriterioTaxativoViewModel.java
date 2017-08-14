package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.uqbar.commons.utils.Observable;

import adapters.AdapterCriteriosToJSON;
import criterios.Criterio;
import criterios.CriterioTaxativo;
import criterios.OperadorComparacion;
import criterios.modificador.Modificador;
import criterios.modificador.Normal;
import criterios.modificador.Promedio;
import criterios.modificador.Sumatoria;
import exports.ExportadorArchivos;
import indicators.Indicador;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioIndicadores;

@Observable
public class CrearCriterioTaxativoViewModel {

	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	private String criterio = "";
	private Double constante;
	private String nombreCriterio;
	private OperadorComparacion operador;
	private Modificador modificador = new Normal();
	private Boolean timeForOperations = false;
	private Boolean timeForIndicators = true;
	private Boolean timeForModificators = true;
	private Boolean timeForConstant = false;

	public CrearCriterioTaxativoViewModel() {
		this.indicadores = new LinkedList<>(RepositorioIndicadores.getInstance().getIndicadores());
	}

	public void crearCriterio() {
		this.validarCreacionDeCriterio();
		
		Criterio nuevoCriterio = new CriterioTaxativo(nombreCriterio, operador, indicadorSeleccionado, modificador,
				constante);
		RepositorioCriterios.getInstance().agregar(nuevoCriterio);

		new ExportadorArchivos(new AdapterCriteriosToJSON(), "./criterios.json").exportar();

	}

	private void validarCreacionDeCriterio() {
		if (nombreCriterio == null || criterio == "")
			throw new Exception("Nombre o criterio vacio");
		if (!this.tieneNombreValido(nombreCriterio) || RepositorioCriterios.getInstance().tieneCriterio(nombreCriterio))
			throw new Exception("El criterio ya existe o es invalido");
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
		this.setConstante(null);
		
		this.itsTimeForIndicators();
	}

	public void agregarMayor() {
		this.setOperador(OperadorComparacion.MAYOR);
		this.setCriterio(criterio + ">");
		
		this.timeForConstant = true;
	}

	public void agregarMenor() {
		this.setOperador(OperadorComparacion.MENOR);
		this.setCriterio(criterio + "<");
		
		this.timeForConstant = true;
	}

	public void agregarPromedio() {
		Modificador modificadorPromedio = new Promedio();
		this.setModificador(modificadorPromedio);
		this.setCriterio(criterio + "Promedio ");
		
		this.timeForModificators = false;
		this.timeForConstant = false;
	}

	public void agregarSumatoria() {
		Modificador modificadorSumatoria = new Sumatoria();
		this.setModificador(modificadorSumatoria);
		this.setCriterio(criterio + "Sumatoria ");
		
		this.timeForModificators = false;
		this.timeForConstant = false;
	}

	public void agregarConstante() {
		this.setCriterio(criterio + Double.toString(constante));
		this.itsTimeForSave();
	}

	private void itsTimeForOperations() {
		this.timeForOperations = true;
		this.timeForIndicators = false;
		this.timeForModificators = false;
		this.timeForConstant = false;
	}
	
	private void itsTimeForIndicators() {
		this.timeForOperations = false;
		this.timeForIndicators = true;
		this.timeForModificators = true;
		this.timeForConstant = false;
	}
	
	private void itsTimeForSave() {
		this.timeForOperations = false;
		this.timeForIndicators = false;
		this.timeForModificators = false;
		this.timeForConstant = false;
	}

	public Boolean getTimeForOperations() {
		return timeForOperations;
	}

	public Boolean getTimeForIndicators() {
		return timeForIndicators;
	}

	public Boolean getTimeForModificators() {
		return timeForModificators;
	}

	public void setTimeForOperations(Boolean timeForOperations) {
		this.timeForOperations = timeForOperations;
	}

	public void setTimeForIndicators(Boolean timeForIndicators) {
		this.timeForIndicators = timeForIndicators;
	}

	public void setTimeForModificators(Boolean timeForModificators) {
		this.timeForModificators = timeForModificators;
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
		

		if(this.getOperador() == null)
			this.itsTimeForOperations();
		else
			this.itsTimeForSave();
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

	public Boolean getTimeForConstant() {
		return timeForConstant;
	}

	public void setTimeForConstant(Boolean timeForConstant) {
		this.timeForConstant = timeForConstant;
	}

}