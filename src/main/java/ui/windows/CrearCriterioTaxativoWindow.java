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
import ui.viewModels.CrearCriterioTaxativoViewModel;

@SuppressWarnings("serial")
public class CrearCriterioTaxativoWindow extends SimpleWindow<CrearCriterioTaxativoViewModel> {

	public CrearCriterioTaxativoWindow(WindowOwner parent) {
		super(parent, new CrearCriterioTaxativoViewModel());
	}

	@Override
	protected void addActions(Panel arg0) {
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Crear criterio taxativo");

		Panel cabecera = new Panel(mainPanel);
		cabecera.setLayout(new HorizontalLayout());

		new Label(cabecera).setText("Nombre criterio");
		new TextBox(cabecera).setWidth(265).bindValueToProperty("nombreCriterio");

		Panel panelDeIndicadores = new Panel(mainPanel);
		panelDeIndicadores.setLayout(new HorizontalLayout());

		new Label(panelDeIndicadores).setText("Seleccionar un indicador");

		Selector<DataIndicador> selectorIndicador = new Selector<DataIndicador>(panelDeIndicadores).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores")
				.setAdapter(new PropertyAdapter(DataIndicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.setWidth(350);
		selectorIndicador.bindEnabledToProperty("estado.timeForIndicators");

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption("Promedio").onClick(() -> this.agregarPromedio()).setWidth(100)
				.bindEnabledToProperty("estado.timeForModificators");
		new Button(operaciones).setCaption("Sumatoria").onClick(() -> this.agregarSumatoria()).setWidth(100)
				.bindEnabledToProperty("estado.timeForModificators");

		new Label(operaciones).setText("").setWidth(75);

		new Button(operaciones).setCaption(">").onClick(() -> this.agregarMayor()).setWidth(111)
				.bindEnabledToProperty("estado.timeForOperations");
		new Button(operaciones).setCaption("<").onClick(() -> this.agregarMenor()).setWidth(111)
				.bindEnabledToProperty("estado.timeForOperations");

		Panel constante = new Panel(mainPanel);
		constante.setLayout(new HorizontalLayout());

		NumericField campoDeConstante = new NumericField(constante);
		campoDeConstante.setWidth(350).bindValueToProperty("constante");
		campoDeConstante.bindEnabledToProperty("estado.timeForConstant");
		new Button(constante).setCaption("Agregar constante").onClick(() -> this.agregarConstante()).setWidth(145)
				.bindEnabledToProperty("estado.timeForConstant");

		new Label(mainPanel).setText("");

		new Label(mainPanel).setText("Vista previa del criterio");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(15)
				.bindValueToProperty("criterio");
		new Button(mainPanel).setCaption("Borrar todo").onClick(() -> this.borrarCriterio()).setWidth(10);

		new Label(mainPanel).setText("");

		new Button(mainPanel).setCaption("Guardar").onClick(() -> this.crearCriterio()).bindEnabledToProperty("estado.timeForSave");
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

	private void agregarPromedio() {
		this.getModelObject().agregarPromedio();
	}

	private void agregarSumatoria() {
		this.getModelObject().agregarSumatoria();
	}

	private void agregarMenor() {
		this.getModelObject().agregarMenor();
	}

	private void agregarMayor() {
		this.getModelObject().agregarMayor();
	}
}
