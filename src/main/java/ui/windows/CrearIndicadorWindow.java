package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.CuentaYValor;
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
		new Selector<Indicador>(form).allowNull(true);

		new Label(form).setText("Cuentas");
		new Selector<CuentaYValor>(form).allowNull(true);

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption("+").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("-").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("/").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("*").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption("(").setWidth(55).setHeight(30);
		new Button(operaciones).setCaption(")").setWidth(55).setHeight(30);

		new Label(mainPanel).setText("Indicador");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150);

		new Button(mainPanel).setCaption("Guardar");

	}

}
