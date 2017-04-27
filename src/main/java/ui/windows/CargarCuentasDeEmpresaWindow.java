package ui.windows;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.Empresa;
import ui.viewModels.CargarCuentasEmpresaViewModel;

@SuppressWarnings("serial")
public class CargarCuentasDeEmpresaWindow extends SimpleWindow<CargarCuentasEmpresaViewModel> {
	
	public CargarCuentasDeEmpresaWindow(WindowOwner parent){
		super(parent, new CargarCuentasEmpresaViewModel());
	}

	@Override
	protected void addActions(Panel arg0){
	}

	@Override
	protected void createFormPanel(Panel formP){	//TODO hacer un boton para guardar las cosas
		this.setTitle("Cargar cuentas de empresa");
		
		Panel panelGeneral = new Panel(formP)
				.setLayout(new ColumnLayout(2));
		new Label(panelGeneral).setText("Empresas");
		new Label(panelGeneral).setText("Periodo Fiscal");
		
		Selector<Empresa> selectorDeEmpresas = new Selector<Empresa>(panelGeneral)
				.allowNull(false);
		selectorDeEmpresas.bindValueToProperty("empresaSeleccionada");
		selectorDeEmpresas.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		
		new NumericField(panelGeneral).bindValueToProperty("periodoValorLocal");
		
		new Label(panelGeneral).setText("EBITDA:");
		new NumericField(panelGeneral).bindValueToProperty("ebitdaValorLocal");
		new Label(panelGeneral).setText("FDS:");
		new NumericField(panelGeneral).bindValueToProperty("fdsValorLocal");
		new Label(panelGeneral).setText("FREE CASH FLOW:");
		new NumericField(panelGeneral).bindValueToProperty("freeCashFlowValorLocal");
		new Label(panelGeneral).setText("ingreso Neto en Operaciones Discontinuas:   ");
		new NumericField(panelGeneral).bindValueToProperty("ingresosContinuosValorLocal");
		new Label(panelGeneral).setText("ingreso Neto en Operaciones Continuas:      ");
		new NumericField(panelGeneral).bindValueToProperty("ingresosDiscontinuosValorLocal");
		
		new Button(panelGeneral).setCaption("Guardar").onClick(() -> this.guardarDatosYSalir());
	}
	
	private void guardarDatosYSalir(){
		if(this.getModelObject().puedeGuardar()){
			this.getModelObject().guardarDatosDeCuentas();
			this.close();
		}
		else{
			SimpleWindow<?> ventanaError = new ErrorWindow(this);
			ventanaError.open();
		}
		
	}
}