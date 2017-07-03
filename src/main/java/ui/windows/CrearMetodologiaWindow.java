package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import indicators.DataIndicador;

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
		this.setTitle("Crear metodología");

		new Label(form).setText("Indicadores");
		Selector<DataIndicador> selectorIndicador = new Selector<DataIndicador>(form).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(DataIndicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.setWidth(200);

		new Button(form).setCaption("Agregar").onClick(() -> this.agregarIndicador());
		
		new Label(mainPanel).setText("Condiciones");
		Selector<DataIndicador> selectorCondicion = new Selector<DataIndicador>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(200);
		
		new Button(mainPanel).setCaption("Crear condición");
		
		new Label(mainPanel).setText("Nombre metodología");
		new TextBox(mainPanel).setWidth(265);

		new Label(mainPanel).setText("Metodología");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150);

		new Button(mainPanel).setCaption("Guardar");
	}

	private void agregarIndicador() {
		try {
			this.getModelObject().agregarIndicador();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}

}
