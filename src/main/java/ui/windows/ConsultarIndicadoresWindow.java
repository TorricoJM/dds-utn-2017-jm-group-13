package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import indicators.DataIndicador;
import model.Empresa;
import model.PeriodoFiscal;
import model.parser.ErrorEmpresaPeriodoVacioException;
import model.parser.ErrorEvaluacionException;
import ui.viewModels.ConsultarIndicadoresViewModel;

@SuppressWarnings("serial")
public class ConsultarIndicadoresWindow extends Dialog<ConsultarIndicadoresViewModel> {

	public ConsultarIndicadoresWindow(WindowOwner owner) {
		super(owner, new ConsultarIndicadoresViewModel());
		// TODO Auto-generated constructor stub
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
		
		new Label(form).setText("Indicador");
		Selector<DataIndicador> selectorIndicador = new Selector<DataIndicador>(form).allowNull(true);
		selectorIndicador.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(DataIndicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		
		new Label(mainPanel).setText("OPERACION:").setWidth(150);
		new Label(mainPanel).setBackground(Color.DARK_GRAY).setForeground(Color.WHITE).setFontSize(12).bindValueToProperty("indicadorSeleccionado.operacion");
		
		new Button(mainPanel).setCaption("Aplicar").onClick(() -> this.llamarEvaluador());
		Panel form2 = new Panel(mainPanel);
		form2.setLayout(new VerticalLayout());
		new Label(form2).setText("RESULTADO:").setWidth(400);
		new Label(form2).setBackground(Color.DARK_GRAY).setForeground(Color.WHITE).setFontSize(12).setWidth(150).bindValueToProperty("resultado");
	}
	
	private void llamarEvaluador() {
		try {
		this.getModelObject().llamarEvaluador();
		} catch (ErrorEvaluacionException exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
		  catch (ErrorEmpresaPeriodoVacioException exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}
	
}
