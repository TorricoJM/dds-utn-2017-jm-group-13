package ui.windows;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import indicators.Indicador;
import model.Empresa;
import model.PeriodoFiscal;
import ui.viewModels.ConsultarIndicadoresViewModel;

@SuppressWarnings("serial")
public class ConsultarIndicadoresWindow extends Dialog<ConsultarIndicadoresViewModel> {

	public ConsultarIndicadoresWindow(WindowOwner owner) {
		super(owner, new ConsultarIndicadoresViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new HorizontalLayout());
		this.setTitle("Consulta de indicadores");
		
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		
		new Label(form).setText("Periodo");
		Selector<PeriodoFiscal> selectorPeriodo = new Selector<PeriodoFiscal>(form);
		selectorPeriodo.setWidth(30);
		selectorPeriodo.bindItemsToProperty("empresaSeleccionada.periodos").setAdapter(new PropertyAdapter(PeriodoFiscal.class, "periodo"));
		selectorPeriodo.bindValueToProperty("periodoSeleccionado");
		
		Panel tabPanel = new Panel(mainPanel);
		tabPanel.setLayout(new HorizontalLayout());
		
		List<Indicador> indicadores = new List<Indicador>(tabPanel);
		indicadores.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Indicador.class, "nombre"));
		indicadores.setHeight(100);
		
		List<String> valores = new List<String>(tabPanel);
		valores.bindItemsToProperty("resultados");
		valores.setHeight(100);
		valores.setWidth(100);
	}
	
}
