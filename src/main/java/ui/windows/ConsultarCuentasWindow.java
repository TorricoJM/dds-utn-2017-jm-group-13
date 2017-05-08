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
import model.CuentaYValor;
import model.Empresa;
import model.PeriodoFiscal;

public class ConsultarCuentasWindow extends Dialog<ConsultarCuentasViewModel> {
	
	public ConsultarCuentasWindow(WindowOwner owner){
		super(owner, new ConsultarCuentasViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		this.setTitle("Consulta de cuentas");
		
		new Label(form).setText("Empresas");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas")
			.setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		new Label(form).setText("Periodos");
		Selector<PeriodoFiscal> selectorPeriodo = new Selector <PeriodoFiscal>(form).allowNull(true);
		selectorPeriodo.bindItemsToProperty("empresaSeleccionada.periodos")
			.setAdapter(new PropertyAdapter(PeriodoFiscal.class, "periodo"));
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");
		
		
		Table<CuentaYValor> table = new Table<CuentaYValor>(mainPanel, CuentaYValor.class);
		table.setHeight(200);
		table.setWidth(450);
		
		this.mostrarColumnaCuentas(table);
		
	}
	
	protected void mostrarColumnaCuentas(Table<CuentaYValor> table){
		
		Column<CuentaYValor> columnaNombres = new Column<CuentaYValor>(table);
		columnaNombres.setTitle("Cuenta")
			.setFixedSize(150)
			.bindContentsToProperty("cuenta"); // no me sale mostrar las cuentas en las columnas
		
		
		
	}
	
	
}