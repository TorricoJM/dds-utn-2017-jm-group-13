package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import viewModels.MenuPrincipalViewModel;

@SuppressWarnings("serial")
public class MenuPrincipalWindow extends SimpleWindow<MenuPrincipalViewModel> {

	public MenuPrincipalWindow(WindowOwner parent) {
		super(parent, new MenuPrincipalViewModel());
	}

	@Override
	protected void addActions(Panel arg0) {

	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.setTitle("Menu Principal");
		
		new Label(formPanel).setText("Consultas");
		new Button(formPanel)
			.setCaption("Analizar Empresa");
		new Button(formPanel).setCaption("Ver Graficos");
		
		Panel panelCuentasEIndicadores = new Panel(formPanel);
		panelCuentasEIndicadores.setLayout(new HorizontalLayout());
		
		new Button(panelCuentasEIndicadores)
			.setCaption("Cuentas")
			.onClick(() -> this.abrirVentanaConsultaCuentas());
		new Button(panelCuentasEIndicadores)
			.setCaption("Indicadores");
		
		new Label(formPanel).setText("");
		new Label(formPanel).setText("Actualizacion de Datos");
		new Button(formPanel).setCaption("Datos de Cuentas")
			.onClick(() -> this.abrirVentanaOpcionElegirEmpresa());
		new Button(formPanel).setCaption("Datos de indicadores");
		new Button(formPanel).setCaption("Metodologias");
		
	}

	private void abrirVentanaConsultaCuentas() {
		SimpleWindow<?> ventanaConsultaCuentas= new ConsultaCuentasWindow(this);
		ventanaConsultaCuentas.open();
	}
	private void abrirVentanaOpcionElegirEmpresa() {
		//SimpleWindow<?> ventanaOpcionDeEmpresa = new CargarCuentasDeEmpresaWindow(this);
		//ventanaOpcionDeEmpresa.open();
	}


}