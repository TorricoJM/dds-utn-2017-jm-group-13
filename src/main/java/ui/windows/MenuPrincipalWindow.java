package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.MessageBox.Type;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import model.ErrorImportacionException;
import repositories.Repositorios;
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

		new Button(panelCuentasEIndicadores).setCaption("Cuentas").onClick(() -> this.algo());
		new Button(panelCuentasEIndicadores).setCaption("Indicadores");

		new Label(formPanel).setText("");
		new Label(formPanel).setText("Actualizacion de Datos");
		new Button(formPanel).setCaption("Datos de Cuentas").onClick(() -> this.importarArchivo());
		new Button(formPanel).setCaption("Datos de indicadores");
		new Button(formPanel).setCaption("Metodologias");
	}

	private void algo() {
		try {
			// portadorCSV.getInstance().levantarEmpresasDe("./empresas.csv");
			System.out.println(Repositorios.listaEmpresas.get(0).getNombre() + " "
					+ Repositorios.listaEmpresas.get(0).getPeriodos().get(1).getPeriodo() + " "
					+ Repositorios.listaEmpresas.get(0).getPeriodos().get(1).getCuentas().get(0).getCuenta() + " "
					+ Repositorios.listaEmpresas.get(0).getPeriodos().get(1).getCuentas().get(0).getValor());
			System.out.println(Repositorios.listaEmpresas.get(1).getNombre() + " "
					+ Repositorios.listaEmpresas.get(1).getPeriodos().get(0).getPeriodo() + " "
					+ Repositorios.listaEmpresas.get(1).getPeriodos().get(0).getCuentas().get(0).getCuenta() + " "
					+ Repositorios.listaEmpresas.get(1).getPeriodos().get(0).getCuentas().get(0).getValor());
		} catch (Exception e) {

		}
	}

	private void importarArchivo() {
		try {
			this.getModelObject().importarAchivo();
		} catch (ErrorImportacionException exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}

}