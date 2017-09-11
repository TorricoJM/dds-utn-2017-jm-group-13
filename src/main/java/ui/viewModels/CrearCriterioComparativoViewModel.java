package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.uqbar.commons.utils.Observable;

import adapters.AdapterCriteriosToJSON;
import criterios.Criterio;
import criterios.CriterioComparativo;
import criterios.OperadorComparacion;
import exports.ExportadorArchivos;
import indicators.Indicador;
import model.Exception;
import repositories.repoArchivos.RepositorioCriterios;
import repositories.repoArchivos.RepositorioIndicadores;
import states.EstadoCrearComparativos;

@Observable
public class CrearCriterioComparativoViewModel {

	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	private String criterio = "";
	private String nombreCriterio;
	private OperadorComparacion operador;
	private EstadoCrearComparativos estado;

	public CrearCriterioComparativoViewModel() {
		this.indicadores = new LinkedList<>(RepositorioIndicadores.getInstance().getElementos());
		this.estado = new EstadoCrearComparativos();
	}

	public void crearCriterio() {
		if (!this.tieneNombreValido(nombreCriterio) || RepositorioCriterios.getInstance().tieneCriterio(nombreCriterio))
			throw new Exception("El nombre del criterio es invalido, o ya existe");
		else {
			Criterio nuevoCriterio = new CriterioComparativo(nombreCriterio, operador, indicadorSeleccionado);
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

	public void borrarTodo() {
		this.setOperador(null);
		this.indicadorSeleccionado = null;
		this.setCriterio("");

		this.getEstado().itsTimeForOperations();
	}

	public void agregarMayor() {
		this.setOperador(OperadorComparacion.MAYOR);
		this.setCriterio(criterio + "Mayor ");

		this.getEstado().itsTimeForIndicators();
	}

	public void agregarMenor() {
		this.setOperador(OperadorComparacion.MENOR);
		this.setCriterio(criterio + "Menor ");

		this.getEstado().itsTimeForIndicators();
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

		this.getEstado().itsTimeForSave();
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
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

	public EstadoCrearComparativos getEstado() {
		return this.estado;
	}
}