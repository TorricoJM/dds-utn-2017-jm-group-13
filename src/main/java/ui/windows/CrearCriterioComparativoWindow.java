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
import ui.viewModels.CrearCriterioComparativoViewModel;

@SuppressWarnings("serial")
public class CrearCriterioComparativoWindow extends SimpleWindow<CrearCriterioComparativoViewModel> {

	public CrearCriterioComparativoWindow(WindowOwner parent) {
		super(parent, new CrearCriterioComparativoViewModel());
	}

	@Override
	protected void addActions(Panel actionsPanel) {
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Crear criterio comparativo");

		Panel cabecera = new Panel(mainPanel);
		cabecera.setLayout(new HorizontalLayout());

		new Label(cabecera).setText("Nombre criterio");
		new TextBox(cabecera).setWidth(365).bindValueToProperty("nombreCriterio");

		Panel tituloOperaciones = new Panel(mainPanel);
		tituloOperaciones.setLayout(new HorizontalLayout());

		new Label(tituloOperaciones).setText("").setWidth(150);
		new Label(tituloOperaciones).setText("Comparar empresas segun").setWidth(150);
		new Label(tituloOperaciones).setText("").setWidth(150);

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Label(operaciones).setText("").setWidth(75);
		new Button(operaciones).setCaption("Mayor").onClick(() -> this.getModelObject().agregarMayor()).setWidth(150)
				.bindEnabledToProperty("timeForOperations");
		new Label(operaciones).setText("").setWidth(5);
		new Button(operaciones).setCaption("Menor").onClick(() -> this.getModelObject().agregarMenor()).setWidth(150)
				.bindEnabledToProperty("timeForOperations");

		Panel panelIndicadores = new Panel(mainPanel);
		panelIndicadores.setLayout(new HorizontalLayout());
		panelIndicadores.setWidth(10);

		new Label(panelIndicadores).setText("Seleccionar un indicador");
		Selector<DataIndicador> selectorIndicador = new Selector<DataIndicador>(panelIndicadores).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores")
				.setAdapter(new PropertyAdapter(DataIndicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.setWidth(310);
		selectorIndicador.bindEnabledToProperty("timeForIndicators");

		Panel constante = new Panel(mainPanel);
		constante.setLayout(new HorizontalLayout());

		new Label(mainPanel).setText("");

		new Label(mainPanel).setText("Vista previa del criterio");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150)
				.bindValueToProperty("criterio");
		new Button(mainPanel).setCaption("Borrar todo").onClick(() -> this.getModelObject().borrarTodo()).setWidth(150);

		new Label(mainPanel).setText("");

		new Button(mainPanel).setCaption("Guardar").onClick(() -> this.crearCriterio())
				.bindEnabledToProperty("timeForSave");
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
}
