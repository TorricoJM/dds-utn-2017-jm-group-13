package ui.viewModels;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.uqbar.commons.utils.Observable;

import adapters.AdapterCriteriosToJSON;
import criterios.CriterioComparativo;
import criterios.OperadorComparacion;
import exports.ExportadorArchivos;
import indicators.Indicador;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioIndicadores;

@Observable
public class CrearCriterioComparativoViewModel {

	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	private String criterio = "";
	private String nombreCriterio;
	private OperadorComparacion operador;

	public CrearCriterioComparativoViewModel() {
		this.indicadores = RepositorioIndicadores.getInstance().getIndicadores();
	}

	public void crearCriterio() {
		if (nombreCriterio == null || criterio == "") {
			throw new Exception("Nombre o criterio vacio");
		} else if (!this.tieneNombreValido(nombreCriterio)
				|| RepositorioCriterios.getInstance().tieneCriterio(nombreCriterio))
			throw new Exception("El criterio ya existe o es invalido");
		else {
			CriterioComparativo nuevoCriterio = new CriterioComparativo(nombreCriterio, operador,
					indicadorSeleccionado);
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
		this.setOperador(null);
		this.setCriterio("");
	}

	public void agregarMayor() {
		this.setOperador(OperadorComparacion.MAYOR);
		this.setCriterio(criterio + "Mayor ");
	}

	public void agregarMenor() {
		this.setOperador(OperadorComparacion.MENOR);
		this.setCriterio(criterio + "Menor ");
	}

	public void agregarIndicador() {
		if (indicadorSeleccionado == null) {
			throw new Exception("Seleccione algun indicador");
		} else {
			this.setCriterio(criterio + indicadorSeleccionado.getNombre());
		}
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

}
