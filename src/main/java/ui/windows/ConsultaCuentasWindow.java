package ui.windows;

import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import model.Empresa;
import model.PeriodoFiscalDeEmpresa;
import viewModels.ConsultaCuentasViewModel;

@SuppressWarnings("serial")
public class ConsultaCuentasWindow extends SimpleWindow<ConsultaCuentasViewModel> {

	public ConsultaCuentasWindow(WindowOwner parent) {
		super(parent, new ConsultaCuentasViewModel());
	}

	@Override
	protected void addActions(Panel arg0) {

	}

	@Override
	protected void createFormPanel(Panel formP) {
		this.setTitle("Consulta de cuentas");
		
		Panel panelGeneral = new Panel(formP)
				.setLayout(new ColumnLayout(2));
		new Label(panelGeneral).setText("Empresas");
		new Label(panelGeneral).setText("Periodo Fiscal");
		
		Selector<Empresa> selectorDeEmpresas = new Selector<Empresa>(panelGeneral)
				.allowNull(false);
		selectorDeEmpresas.bindValueToProperty("empresaSeleccionada");
		selectorDeEmpresas.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		
		Selector<PeriodoFiscalDeEmpresa> selectorDePeriodos = new Selector<PeriodoFiscalDeEmpresa>(panelGeneral)
				.allowNull(false);
		selectorDePeriodos.bindValueToProperty("periodoFiscalSeleccionado");
		selectorDePeriodos.bindItemsToProperty("periodosFiscalesDeEmpresa")
			.setAdapter(new PropertyAdapter(PeriodoFiscalDeEmpresa.class, "periodo"));
		
		new Label(panelGeneral).setText("EBITDA:");
		new Label(panelGeneral).bindValueToProperty("periodoFiscalSeleccionado.ebitda");
		
	}
	
	private void avisarMostrar(){
		this.getModelObject().mostrarEmpresaSeleccionda();
	}


}