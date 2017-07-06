package ui.windows;
import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import indicators.DataIndicador;
import model.Exception;
import ui.viewModels.CrearCriterioViewModel;

@SuppressWarnings("serial")
public class CrearCriterioWindow extends SimpleWindow<CrearCriterioViewModel> {

	public CrearCriterioWindow(WindowOwner parent) {
		super(parent, new CrearCriterioViewModel());
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
		this.setTitle("Crear criterio");
		
		new Label(mainPanel).setText("Nombre criterio");
		new TextBox(mainPanel).setWidth(265).bindValueToProperty("nombreCriterio");
		
		new Label(form).setText("Indicadores");
		Selector<DataIndicador> selectorIndicador = new Selector<DataIndicador>(form).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(DataIndicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.setWidth(100);

		new Label(form).setText("Cuentas");
		Selector<String> selectorCuenta = new Selector<String>(form).allowNull(true);
		selectorCuenta.bindItemsToProperty("cuentas");
		selectorCuenta.bindValueToProperty("cuentaSeleccionada");
		selectorCuenta.setWidth(100);

		new Button(mainPanel).setCaption("Agregar indicador").onClick(() -> this.agregarIndicador());
		new Button(mainPanel).setCaption("Agregar cuenta").onClick(() -> this.agregarCuenta());
		
		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption(">").onClick(() -> this.agregarMayor()).setWidth(100);
		new Button(operaciones).setCaption("<").onClick(() -> this.agregarMenor()).setWidth(100);
		new Button(operaciones).setCaption("=").onClick(() -> this.agregarIgual()).setWidth(100);
		new Button(operaciones).setCaption("Borrar").onClick(() -> this.borrarCriterio()).setWidth(100);
		
		Panel constante = new Panel(mainPanel);
		constante.setLayout(new HorizontalLayout());

		new NumericField(constante).setWidth(256).bindValueToProperty("constante");
		new Button(constante).setCaption("Agregar constante").onClick(() -> this.agregarConstante());

		new Label(mainPanel).setText("Criterio");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150).bindValueToProperty("criterio");
		
		new Button(mainPanel).setCaption("Guardar").onClick(() -> this.crearCriterio());
	}

	private void crearCriterio() {
		try {
			this.getModelObject().crearCriterio();
			this.close();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}
	
	private void borrarCriterio() {
		this.getModelObject().borrarCriterio();
	}

	private void agregarConstante() {
		this.getModelObject().agregarConstante();
	}

	private void agregarIgual() {
		this.getModelObject().agregarIgual();
	}

	private void agregarMenor() {
		this.getModelObject().agregarMenor();
	}

	private void agregarMayor() {
		this.getModelObject().agregarMayor();
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

	private void agregarCuenta() {
		try {
			this.getModelObject().agregarCuenta();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}
	
}
