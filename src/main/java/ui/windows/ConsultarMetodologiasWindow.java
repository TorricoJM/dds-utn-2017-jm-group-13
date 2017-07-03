package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import methodologies.DataMetodologia;
import ui.viewModels.ConsultarMetodologiasViewModel;
import model.Empresa;

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

		new Label(form).setText("Metodologías");
		Selector<Empresa> selectorMetodologia = new Selector<Empresa>(form).allowNull(true);
		selectorMetodologia.setWidth(200);

		Table<DataMetodologia> table = new Table<DataMetodologia>(mainPanel, DataMetodologia.class);
		table.setNumberVisibleRows(10);

		this.mostrarColumnas(table);
		
		new Button(mainPanel).setCaption("Comparar empresas");

	}

	private void mostrarColumnas(Table<DataMetodologia> table) {

		Column<DataMetodologia> nombres = new Column<DataMetodologia>(table);
		nombres.setTitle("Empresa").setFixedSize(200).bindContentsToProperty("empresa");

		Column<DataMetodologia> valores = new Column<DataMetodologia>(table);
		valores.setTitle("Valor").setFixedSize(110).bindContentsToProperty("valor");
	}
}