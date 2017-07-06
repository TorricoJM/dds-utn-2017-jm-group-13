package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import indicators.DataIndicador;
import indicators.Indicador;
import model.CuentaYValor;
import model.Empresa;
import model.PeriodoFiscal;
import model.parser.EmpresaPeriodoVacioException;
import model.parser.ErrorEvaluacionException;
import model.parser.IdentificadorInvalidoException;
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
		indicadores.setHeight(70);
		indicadores.bindEnabledToProperty("enabled");
		
		List<String> valores = new List<String>(tabPanel);
		valores.bindItemsToProperty("resultados");
		valores.setHeight(70);
		valores.bindEnabledToProperty("enabled");
	}
	
}
