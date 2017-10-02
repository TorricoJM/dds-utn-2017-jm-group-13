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
import repositories.RepositorioCriteriosTaxativos;
import repositories.RepositorioCriteriosComparativos;
import repositories.RepositorioMetodologias;

@Observable
public class CrearMetodologiaViewModel {

	private List<CriterioComparativo> criteriosComp;
	private List<CriterioTaxativo> criteriosTax;
	private List<Double> ponderaciones = new LinkedList<>();
	private CriterioTaxativo criterioTaxativoSeleccionado;
	private CriterioComparativo criterioComparativoSeleccionado;
	private String nombre;
	private List<ParComparativoPeso> criteriosComparativosElegidos = new LinkedList<>();
	private List<CriterioTaxativo> criteriosTaxativosElegidos = new LinkedList<>();
	private Double ponderacionSeleccionada;
	private Boolean enableAgregate = false;
	private Boolean enableSave = false;

	public CrearMetodologiaViewModel() {
	}

	public void agregarCriterio() {
		if (criterioComparativoSeleccionado == null & criterioTaxativoSeleccionado == null) {
			throw new Exception("Seleccione un criterio");
		} else {
			this.discriminarCriterio();
			this.finalizarAgregadoDeCriterio();
		}
	}

	private void finalizarAgregadoDeCriterio() {
		this.setCriterioComparativoSeleccionado(null);
		this.setCriterioTaxativoSeleccionado(null);
		this.setEnableAgregate(false);
		this.setEnableSave(true);
	}

	public void crearMetodologia() {

		Metodologia nuevaMetodologia = new MetodologiesBuilder().setNombre(nombre)
				.setCriterios(criteriosComparativosElegidos, criteriosTaxativosElegidos).build();

		RepositorioMetodologias.getInstance().agregar(nuevaMetodologia);
}

	public List<CriterioComparativo> getCriteriosComp() {
		return criteriosComp;
	}

	public void setCriteriosComp(List<CriterioComparativo> criteriosComp) {
		this.criteriosComp = criteriosComp;
	}

	public List<CriterioTaxativo> getCriteriosTax() {
		return criteriosTax;
	}

	public void setCriteriosTax(List<CriterioTaxativo> criteriosTax) {
		this.criteriosTax = criteriosTax;
	}

	public CriterioTaxativo getCriterioTaxativoSeleccionado() {
		return criterioTaxativoSeleccionado;
	}

	public void setCriterioTaxativoSeleccionado(CriterioTaxativo criterioTaxativoSeleccionado) {
		this.criterioTaxativoSeleccionado = criterioTaxativoSeleccionado;
		this.setEnableAgregate(true);
	}

	public CriterioComparativo getCriterioComparativoSeleccionado() {
		return criterioComparativoSeleccionado;
	}

	public void setCriterioComparativoSeleccionado(CriterioComparativo criterioComparativoSeleccionado) {
		this.criterioComparativoSeleccionado = criterioComparativoSeleccionado;
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
		this.criteriosComp = new LinkedList<>(RepositorioCriteriosComparativos.getInstance().getElementos());
		this.criteriosTax = new LinkedList<>(RepositorioCriteriosTaxativos.getInstance().getElementos());
		ObservableUtils.firePropertyChanged(this, "criteriosComp");
		ObservableUtils.firePropertyChanged(this, "criteriosTax");
	}

	public void borrarCriterios() {

		this.criteriosComparativosElegidos.clear();
		this.criteriosTaxativosElegidos.clear();

	}

	private void discriminarCriterio() {
		if (this.criterioTaxativoSeleccionado != null) {
			criteriosTaxativosElegidos.add(criterioTaxativoSeleccionado);
		} else if (this.criterioComparativoSeleccionado != null) {
			this.controlarValidezCriterioComparativo();
		}
	}

	private void controlarValidezCriterioComparativo() {
		if (ponderacionSeleccionada != null)
			criteriosComparativosElegidos
					.add(new ParComparativoPeso(criterioComparativoSeleccionado, ponderacionSeleccionada));
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
	
	public void setCriteriosComparativosElegidos(List<ParComparativoPeso> comparativos) {
		this.criteriosComparativosElegidos = comparativos;
	}

	public List<CriterioTaxativo> getCriteriosTaxativosElegidos() {
		return criteriosTaxativosElegidos;
	}

	public void setCriteriosTaxativosElegidos(List<CriterioTaxativo> criteriosTaxativosElegidos) {
		this.criteriosTaxativosElegidos = criteriosTaxativosElegidos;
	}

}
