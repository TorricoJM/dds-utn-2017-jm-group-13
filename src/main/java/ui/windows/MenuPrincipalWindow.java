package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ui.viewModels.MenuPrincipalViewModel;

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
		new Button(formPanel).setCaption("Analizar Empresa");
		new Button(formPanel).setCaption("Ver Graficos");

		Panel panelCuentasEIndicadores = new Panel(formPanel);
		panelCuentasEIndicadores.setLayout(new HorizontalLayout());

		new Button(panelCuentasEIndicadores).setCaption("Cuentas").onClick(() -> this.consultarCuentas());
		new Button(panelCuentasEIndicadores).setCaption("Indicadores").onClick(() -> this.consultarIndicadores());

		new Button(formPanel).setCaption("Metodologias").onClick(() -> this.consularMetologias());
		new Label(formPanel).setText("");
		new Label(formPanel).setText("Actualizacion de Datos");

		new Button(formPanel).setCaption("Importar cuentas").onClick(() -> this.abrirSelectorArchivos());
		new Button(formPanel).setCaption("Cargar indicador").onClick(() -> this.abrirCreadorIndicador());
		new Button(formPanel).setCaption("Cargar metodologia").onClick(() -> this.abrirCreadorMetodologia());

	}

	private void consularMetologias() {
		Dialog<?> dialog = new ConsultarMetodologiasWindow(this);
		dialog.open();
	}

	public void consultarCuentas() {
		Dialog<?> dialog = new ConsultarCuentasWindow(this);
		dialog.open();
	}
	
	public void consultarIndicadores() {
		Dialog<?> dialog = new ConsultarIndicadoresWindow(this);
		dialog.open();
	}

	private void abrirCreadorMetodologia(){
		SimpleWindow<?> selectorWindow = new CrearMetodologiaWindow(this);
		selectorWindow.open();
	}
	
	private void abrirSelectorArchivos() {
		SimpleWindow<?> selectorWindow = new SelectorArchivosWindow(this);
		selectorWindow.open();
	}

	private void abrirCreadorIndicador() {
		SimpleWindow<?> selectorWindow = new CrearIndicadorWindow(this);
		selectorWindow.open();
	}

}