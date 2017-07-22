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
import ui.viewModels.CrearCriterioComparativoViewModel;

@SuppressWarnings("serial")
public class CrearCriterioComparativoWindow extends SimpleWindow<CrearCriterioComparativoViewModel> {

	public CrearCriterioComparativoWindow(WindowOwner parent) {
		super(parent, new CrearCriterioComparativoViewModel());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		form.setWidth(10);
		this.setTitle("Crear criterio comparativo");

		new Label(form).setText("Indicadores");
		Selector<DataIndicador> selectorIndicador = new Selector<DataIndicador>(form).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores")
				.setAdapter(new PropertyAdapter(DataIndicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.setWidth(350);

		new Button(mainPanel).setCaption("Agregar indicador").onClick(() -> this.agregarIndicador());

		new Label(mainPanel).setText("Nombre criterio");
		new TextBox(mainPanel).setWidth(265).bindValueToProperty("nombreCriterio");

		Panel operaciones = new Panel(mainPanel);
		operaciones.setLayout(new HorizontalLayout());

		new Button(operaciones).setCaption("Mayor").onClick(() -> this.agregarMayor()).setWidth(100);
		new Button(operaciones).setCaption("Menor").onClick(() -> this.agregarMenor()).setWidth(100);
		/*
		 * new Button(operaciones).setCaption(">").onClick(() ->
		 * this.agregarSignoMayor()).setWidth(100); new
		 * Button(operaciones).setCaption("<").onClick(() ->
		 * this.agregarSignoMenor()).setWidth(100);
		 */
		new Button(operaciones).setCaption("Borrar").onClick(() -> this.borrarCriterio()).setWidth(100);

		Panel constante = new Panel(mainPanel);
		constante.setLayout(new HorizontalLayout());

		new NumericField(constante).setWidth(350).bindValueToProperty("constante");
		new Button(constante).setCaption("Agregar constante").onClick(() -> this.agregarConstante());

		new Label(mainPanel).setText("Criterio");
		new Label(mainPanel).setBackground(Color.LIGHT_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150)
				.bindValueToProperty("criterio");

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

	/*
	 * private void agregarSignoMayor() {
	 * this.getModelObject().agregarSignoMayor(); }
	 * 
	 * private void agregarSignoMenor() {
	 * this.getModelObject().agregarSignoMenor(); }
	 */

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
}
