package ui.windows;

import java.awt.Color;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.FileSelector;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.windows.MessageBox.Type;

import model.ErrorImportacionException;
import ui.viewModels.SelectorArchivosViewModel;

@SuppressWarnings("serial")
public class SelectorArchivosWindow extends SimpleWindow<SelectorArchivosViewModel> {

	public SelectorArchivosWindow(WindowOwner parent) {
		super(parent, new SelectorArchivosViewModel());
	}

	@Override
	protected void addActions(Panel arg0) {
	}

	@Override
	protected void createFormPanel(Panel formPanel) {
		this.setTitle("Importar");
		Panel panelPrincipal = new Panel(formPanel);

		FileSelector fileSelector = new FileSelector(panelPrincipal);
		fileSelector.extensions("*.csv").title("Cargar archivo").setCaption("Seleccionar archivo")
				.bindValueToProperty("pathArchivo");

		new Label(panelPrincipal).setText("Archivo seleccionado");
		new Label(panelPrincipal).setBackground(Color.DARK_GRAY).setForeground(Color.WHITE).setFontSize(12)
				.setWidth(150).bindValueToProperty("nombreParaMostrar");

		new Button(panelPrincipal).setCaption("Aceptar").onClick(() -> this.importarArchivo());
	}

	private void importarArchivo() {
		try {
			this.getModelObject().importarAchivo();
			this.close();
		} catch (ErrorImportacionException exception) {
			MessageBox dialogWindow = new MessageBox(this, Type.Error);
			dialogWindow.setMessage(exception.getMensaje());
			dialogWindow.open();
		}
	}
}