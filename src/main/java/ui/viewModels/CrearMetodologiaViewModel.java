package ui.viewModels;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import criterios.*;
import methodologies.Metodologia;
import methodologies.MetodologiesBuilder;
import model.Exception;
import repositories.RepositorioCriterios;
import repositories.RepositorioMetodologias;

@Observable
public class CrearMetodologiaViewModel {

	private List<Criterio> criterios;
	private List<Double> ponderaciones = new LinkedList<>();
	private Criterio criterioSeleccionado;
	private String nombre;
	private List<ParComparativoPeso> criteriosComparativosElegidos = new LinkedList<>();
	private List<CriterioTaxativo> criteriosTaxativosElegidos = new LinkedList<>();
	private Double ponderacionSeleccionada;
	private Boolean enableAgregate = false;
	private Boolean enableSave = false;

	public CrearMetodologiaViewModel() {
	}

	public void agregarCriterio() {
		if (criterioSeleccionado == null) {
			throw new Exception("Seleccione un criterio");
		} else {
			this.discriminarCriterio();
			this.finalizarAgregadoDeCriterio();
		}
	}

	private void finalizarAgregadoDeCriterio() {
		this.setCriterioSeleccionado(null);
		this.setEnableAgregate(false);
		this.setEnableSave(true);
	}

	public void crearMetodologia() {

		Metodologia nuevaMetodologia = new MetodologiesBuilder().setNombre(nombre)
				.setCriterios(criteriosComparativosElegidos, criteriosTaxativosElegidos).build();

		RepositorioMetodologias.getInstance().agregar(nuevaMetodologia);

	}

	public List<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterio> criterios) {
		this.criterios = criterios;
	}

	public Criterio getCriterioSeleccionado() {
		return criterioSeleccionado;
	}

	public void setCriterioSeleccionado(Criterio criterioSeleccionado) {
		this.criterioSeleccionado = criterioSeleccionado;
		this.setEnableAgregate(true);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Double> getPonderaciones() {
		return ponderaciones;
	}

	public void setPonderaciones(List<Double> ponderaciones) {
		this.ponderaciones = ponderaciones;
	}

	public Double getPonderacionSeleccionada() {
		return ponderacionSeleccionada;
	}

	public void setPonderacionSeleccionada(Double ponderacionSeleccionada) {
		this.ponderacionSeleccionada = ponderacionSeleccionada;
	}

	public void actualizarListaCriterios() {
		this.criterios = new LinkedList<>(RepositorioCriterios.getInstance().getElementos());
		ObservableUtils.firePropertyChanged(this, "criterios");
	}

	public void borrarCriterios() {

		this.criteriosComparativosElegidos.clear();
		this.criteriosTaxativosElegidos.clear();

	}

	private void discriminarCriterio() {
		if (this.criterioSeleccionadoEsTaxativo())
			criteriosTaxativosElegidos.add((CriterioTaxativo) criterioSeleccionado);
		else
			this.controlarValidezCriterioComparativo();
	}

	public Boolean criterioSeleccionadoEsTaxativo() {
		return this.criterioSeleccionado.getClass().equals(CriterioTaxativo.class);
	}

	private void controlarValidezCriterioComparativo() {
		if (ponderacionSeleccionada != null)
			criteriosComparativosElegidos
					.add(new ParComparativoPeso((CriterioComparativo) criterioSeleccionado, ponderacionSeleccionada));
	}

	public Boolean getEnableAgregate() {
		return enableAgregate;
	}

	public Boolean getEnableSave() {
		return enableSave;
	}

	public void setEnableAgregate(Boolean enableAgregate) {
		this.enableAgregate = enableAgregate;
	}

	public void setEnableSave(Boolean enableSave) {
		this.enableSave = enableSave;
	}

	public List<CriterioComparativo> getCriteriosComparativosElegidos() {
		return criteriosComparativosElegidos.stream().map(tupla -> tupla.getCriterio()).collect(Collectors.toList());
	}

	public List<CriterioTaxativo> getCriteriosTaxativosElegidos() {
		return criteriosTaxativosElegidos;
	}

	public void setCriteriosTaxativosElegidos(List<CriterioTaxativo> criteriosTaxativosElegidos) {
		this.criteriosTaxativosElegidos = criteriosTaxativosElegidos;
	}

}
