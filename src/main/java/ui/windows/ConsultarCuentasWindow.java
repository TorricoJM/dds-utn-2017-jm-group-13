package ui.windows;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.viewModels.ConsultarCuentasViewModel;
import model.Cuenta;
import model.Empresa;
import model.PeriodoFiscal;

@SuppressWarnings("serial")
public class ConsultarCuentasWindow extends Dialog<ConsultarCuentasViewModel> {

	public ConsultarCuentasWindow(WindowOwner owner) {
		super(owner, new ConsultarCuentasViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		this.setTitle("Consulta de cuentas");

		new Label(form).setText("Empresas");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.setWidth(70);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");

		new Label(form).setText("Periodos");
		Selector<PeriodoFiscal> selectorPeriodo = new Selector<PeriodoFiscal>(form);
		selectorPeriodo.setWidth(30);
		selectorPeriodo.bindItemsToProperty("empresaSeleccionada.periodos")
				.setAdapter(new PropertyAdapter(PeriodoFiscal.class, "periodo"));
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");

		Table<Cuenta> table = new Table<Cuenta>(mainPanel, Cuenta.class);
		table.bindItemsToProperty("periodoSeleccionado.cuentas");
		table.setNumberVisibleRows(10);

		this.mostrarColumnas(table);

	}

	private void mostrarColumnas(Table<Cuenta> table) {

		Column<Cuenta> nombres = new Column<Cuenta>(table);
		nombres.setTitle("Cuenta").setFixedSize(200).bindContentsToProperty("nombre");

		Column<Cuenta> valores = new Column<Cuenta>(table);
		valores.setTitle("Valor").setFixedSize(110).bindContentsToProperty("valor");
	}
}