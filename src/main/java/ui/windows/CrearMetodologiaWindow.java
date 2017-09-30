package ui.windows;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import criterios.CriterioComparativo;
import criterios.CriterioTaxativo;

import org.uqbar.arena.windows.MessageBox.Type;

import model.Exception;
import ui.viewModels.CrearMetodologiaViewModel;

@SuppressWarnings("serial")
public class CrearMetodologiaWindow extends SimpleWindow<CrearMetodologiaViewModel> {

	public CrearMetodologiaWindow(WindowOwner parent) {
		super(parent, new CrearMetodologiaViewModel());
	}

	@Override
	protected void addActions(Panel arg0) {

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Crear metodologia");

		Panel cabecera = new Panel(mainPanel).setLayout(new HorizontalLayout()).setWidth(10);

		new Label(cabecera).setText("Nombre: ");
		new TextBox(cabecera).setWidth(358).bindValueToProperty("nombre");

		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout()).setWidth(10);

		new Label(form).setText("Seleccionar un criterio");

		Selector<CriterioComparativo> selectorCriterioComp = new Selector<CriterioComparativo>(form).allowNull(true);
		selectorCriterioComp.bindItemsToProperty("criteriosComp").setAdapter(new PropertyAdapter(CriterioComparativo.class, "nombre"));
		selectorCriterioComp.bindValueToProperty("criterioComparativoSeleccionado");
		selectorCriterioComp.setWidth(125);
		
		Selector<CriterioTaxativo> selectorCriterioTax = new Selector<CriterioTaxativo>(form).allowNull(true);
		selectorCriterioTax.bindItemsToProperty("criteriosTax").setAdapter(new PropertyAdapter(CriterioTaxativo.class, "nombre"));
		selectorCriterioTax.bindValueToProperty("criterioTaxativoSeleccionado");
		selectorCriterioTax.setWidth(125);

		new Button(form).setCaption("Agregar Criterio").onClick(() -> this.agregarCriterio()).setWidth(150)
				.bindEnabledToProperty("enableAgregate");

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption("Crear criterio taxativo")
				.onClick(() -> this.abrirCreadorCriteriosTaxativos()).setWidth(210);
		new Button(operaciones).setCaption("Crear criterio comparativo")
				.onClick(() -> this.abrirCreadorCriteriosComparativos()).setWidth(211);

		Panel tabPanel = new Panel(mainPanel);
		tabPanel.setLayout(new HorizontalLayout());

		List<String> criteriosTax = new List<String>(tabPanel);
		criteriosTax.bindItemsToProperty("criteriosTaxativosElegidos")
				.setAdapter(new PropertyAdapter(CriterioTaxativo.class, "nombre"));
		criteriosTax.setHeight(100);
		criteriosTax.setWidth(200);
		
		List<String> criteriosComp = new List<String>(tabPanel);
		criteriosComp.bindItemsToProperty("criteriosComparativosElegidos")
				.setAdapter(new PropertyAdapter(CriterioComparativo.class, "nombre"));
		criteriosComp.setHeight(100);
		criteriosComp.setWidth(200);

		new Label(mainPanel).setText("");
		new Button(mainPanel).setCaption("Borrar")
				.onClick(() -> this.getModelObject().borrarCriterios());
		new Label(mainPanel).setText("");

		new Button(mainPanel).setCaption("Guardar").onClick(() -> this.getModelObject().crearMetodologia())
				.setWidth(150).bindEnabledToProperty("enableSave");
	}

	private void agregarCriterio() {
		try {
			this.mostrarPonderaciones();
			this.getModelObject().agregarCriterio();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}

	private void mostrarPonderaciones() {
			Dialog<?> jerarquias = new PuntuarCriterioComparativoWindow(this, this.getModelObject());
			jerarquias.open();
	}

	private void abrirCreadorCriteriosComparativos() {
		SimpleWindow<?> creadorCriteriosComparativosWindow = new CrearCriterioComparativoWindow(this);
		creadorCriteriosComparativosWindow.open();
		this.getModelObject().actualizarListaCriterios();
	}

	private void abrirCreadorCriteriosTaxativos() {
		SimpleWindow<?> creadorCriteriosTaxativosWindow = new CrearCriterioTaxativoWindow(this);
		creadorCriteriosTaxativosWindow.open();
		this.getModelObject().actualizarListaCriterios();
	}
}
