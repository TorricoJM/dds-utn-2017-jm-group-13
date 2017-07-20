package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
//import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
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

		new Button(operaciones).setCaption("Agregar Criterio").onClick(() -> this.agregarCriterio()).setWidth(150);
		new Button(operaciones).setCaption("Crear Criterio Taxativo").onClick(() -> this.abrirCreadorCriteriosTaxativos()).setWidth(150);

		new Label(mainPanel).setText("Metodologia");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150);

		new Label(mainPanel).setText("Nombre metodologia");
		new TextBox(mainPanel).setWidth(265);

		new Button(mainPanel).setCaption("Guardar");
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

	private void abrirCreadorCriteriosTaxativos() {
		SimpleWindow<?> creadorCriteriosTaxativosWindow = new CrearCriterioTaxativoWindow(this);
		creadorCriteriosTaxativosWindow.open();
	}

}
