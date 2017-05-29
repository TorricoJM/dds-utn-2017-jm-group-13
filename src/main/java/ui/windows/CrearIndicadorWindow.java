package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.NumericField;

import model.Indicador;
import ui.viewModels.CrearIndicadorViewModel;

@SuppressWarnings("serial")
public class CrearIndicadorWindow extends SimpleWindow<CrearIndicadorViewModel> {

	public CrearIndicadorWindow(WindowOwner parent) {
		super(parent, new CrearIndicadorViewModel());
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
		this.setTitle("Crear Indicador");

		new Label(form).setText("Indicadores");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Indicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.setWidth(100);

		new Label(form).setText("Cuentas");
		Selector<String> selectorCuenta = new Selector<String>(form).allowNull(true);
		selectorCuenta.bindItemsToProperty("cuentas");
		selectorCuenta.bindValueToProperty("cuentaSeleccionada");
		selectorCuenta.setWidth(100);

		new Label(mainPanel).setText("Nombre indicador");
		new TextBox(mainPanel).setWidth(265);

		new Label(mainPanel).setText("Indicador");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150);

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption("+").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("-").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("/").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("*").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("(").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption(")").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("Borrar").setWidth(55).setHeight(30);

		Panel constante = new Panel(mainPanel);
		constante.setLayout(new HorizontalLayout());

		new NumericField(constante).setWidth(265);
		new Button(constante).setCaption("Agregar constante");

		new Button(mainPanel).setCaption("Guardar");

	}

}
