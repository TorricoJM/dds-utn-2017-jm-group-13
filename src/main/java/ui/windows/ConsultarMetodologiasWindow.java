package ui.windows;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import methodologies.Metodologia;
import ui.viewModels.ConsultarMetodologiasViewModel;
import model.Empresa;
import model.Exception;
import model.PeriodoFiscal;

@SuppressWarnings("serial")
public class ConsultarMetodologiasWindow extends Dialog<ConsultarMetodologiasViewModel> {

	public ConsultarMetodologiasWindow(WindowOwner owner) {
		super(owner, new ConsultarMetodologiasViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		this.setTitle("Consulta de metodologias");

		new Label(form).setText("Metodologias");
		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(form).allowNull(true);
		selectorMetodologia.setWidth(200);
		selectorMetodologia.bindItemsToProperty("metodologias").setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));
		selectorMetodologia.bindValueToProperty("metodologiaSeleccionada");

		new Label(form).setText("Periodo Inicio");
		Selector<PeriodoFiscal> selectorPeriodoInicio = new Selector<PeriodoFiscal>(form);
		selectorPeriodoInicio.setWidth(30);
		selectorPeriodoInicio.bindItemsToProperty("periodos");
		selectorPeriodoInicio.bindValueToProperty("periodoInicioSeleccionado");
		
		new Label(form).setText("Periodo Fin");
		Selector<PeriodoFiscal> selectorPeriodoFin = new Selector<PeriodoFiscal>(form);
		selectorPeriodoFin.setWidth(30);
		selectorPeriodoFin.bindItemsToProperty("periodos");
		selectorPeriodoFin.bindValueToProperty("periodoFinSeleccionado");
		
		Table<Empresa> table = new Table<Empresa>(mainPanel, Empresa.class);
		table.setNumberVisibleRows(10).bindItemsToProperty("empresasResultantes");

		this.mostrarColumnas(table);
		
		new Button(mainPanel).setCaption("Comparar empresas").onClick(() -> this.evaluarMetodologia());

	}

	private void mostrarColumnas(Table<Empresa> table) {

		Column<Empresa> nombres = new Column<Empresa>(table);
		nombres.setTitle("Empresa").setFixedSize(200).bindContentsToProperty("nombre");
		
	}
	
	private void evaluarMetodologia() {
		try {
			this.getModelObject().evaluarMetodologia();
		} catch (Exception exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}
}