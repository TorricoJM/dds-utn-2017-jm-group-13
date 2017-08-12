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

import criterios.Criterio;

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

		Selector<Criterio> selectorCriterio = new Selector<Criterio>(form).allowNull(true);
		selectorCriterio.bindItemsToProperty("criterios").setAdapter(new PropertyAdapter(Criterio.class, "nombre"));
		selectorCriterio.bindValueToProperty("criterioSeleccionado");
		selectorCriterio.setWidth(125);

		new Button(form).setCaption("Agregar Criterio").onClick(() -> this.agregarCriterio()).setWidth(150);

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption("Crear criterio taxativo")
				.onClick(() -> this.abrirCreadorCriteriosTaxativos()).setWidth(210);
		new Button(operaciones).setCaption("Crear criterio comparativo")
				.onClick(() -> this.abrirCreadorCriteriosComparativos()).setWidth(211);

		Panel tabPanel = new Panel(mainPanel);
		tabPanel.setLayout(new HorizontalLayout());

		List<String> criterios = new List<String>(tabPanel);
		criterios.bindItemsToProperty("criteriosPonderacionElegidos")
				.setAdapter(new PropertyAdapter(Criterio.class, "nombre"));
		criterios.setHeight(100);
		criterios.setWidth(400);

		new Label(mainPanel).setText("");
		new Button(mainPanel).setCaption("Borrar ultimo criterio")
				.onClick(() -> this.getModelObject().borrarUltimoCriterio());
		new Label(mainPanel).setText("");

		new Button(mainPanel).setCaption("Guardar").onClick(() -> this.crearMetodologia()).setWidth(150);
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
		if (!this.getModelObject().criterioSeleccionadoEsTaxativo()) {
			Dialog<?> jerarquias = new PuntuarCriterioComparativoWindow(this, this.getModelObject());
			jerarquias.open();
		}
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

	private void crearMetodologia() {
		try {
			this.getModelObject().crearMetodologia();
			this.close();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}

}
