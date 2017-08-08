package ui.windows;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
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
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		form.setWidth(10);
		this.setTitle("Crear metodologia");

		new Label(form).setText("Criterios");
		Selector<Criterio> selectorCriterio = new Selector<Criterio>(form).allowNull(true);
		selectorCriterio.bindItemsToProperty("criterios").setAdapter(new PropertyAdapter(Criterio.class, "nombre"));
		selectorCriterio.bindValueToProperty("criterioSeleccionado");
		selectorCriterio.setWidth(200);

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(form).setCaption("Agregar Criterio").onClick(() -> this.agregarCriterio()).setWidth(150);
		new Button(operaciones).setCaption("Crear criterio taxativo")
				.onClick(() -> this.abrirCreadorCriteriosTaxativos()).setWidth(215);
		new Button(operaciones).setCaption("Crear criterio comparativo")
				.onClick(() -> this.abrirCreadorCriteriosComparativos()).setWidth(215);

		Panel tabPanel = new Panel(mainPanel);
		tabPanel.setLayout(new HorizontalLayout());

		List<String> valores = new List<String>(tabPanel);
		valores.bindItemsToProperty("criteriosPuntajesElegidos").setAdapter(new PropertyAdapter(Criterio.class, "nombre"));
		valores.setHeight(100);
		valores.setWidth(400);

		new Label(mainPanel).setText("Nombre metodologia");
		new TextBox(mainPanel).setWidth(265).bindValueToProperty("nombre");

		new Button(mainPanel).setCaption("Guardar").onClick(() -> this.crearMetodologia()).setWidth(150);
	}

	private void agregarCriterio() {
		try {
			this.getModelObject().agregarCriterio();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
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
