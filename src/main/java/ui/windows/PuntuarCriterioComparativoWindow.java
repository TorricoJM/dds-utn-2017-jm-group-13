package ui.windows;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.viewModels.CrearMetodologiaViewModel;

@SuppressWarnings("serial")
public class PuntuarCriterioComparativoWindow extends Dialog<CrearMetodologiaViewModel> {

	public PuntuarCriterioComparativoWindow(WindowOwner parent, CrearMetodologiaViewModel vm) {
		super(parent, vm);
		this.puntuar(null);
	}

	@Override
	protected void createFormPanel(Panel arg0) {
		this.setTitle("Definir importancia");

		new Label(arg0).setText("");
		
		Panel panel = new Panel(arg0);
		
		new Button(panel).setCaption("Muy importante").onClick(() -> this.elegirPuntajeYCerrar(1.0));
		new Button(panel).setCaption("Importante").onClick(() -> this.elegirPuntajeYCerrar(0.75));
		new Button(panel).setCaption("Normal").onClick(() -> this.elegirPuntajeYCerrar(0.50));
		new Button(panel).setCaption("Poco importante").onClick(() -> this.elegirPuntajeYCerrar(0.25));
		new Button(panel).setCaption("Casi nada").onClick(() -> this.elegirPuntajeYCerrar(0.1));

	}

	private void elegirPuntajeYCerrar(Double numero) {
		this.puntuar(numero);
		this.tearDown();
	}
	
	private void puntuar(Double numero) {
		this.getModelObject().setPonderacionSeleccionada(numero);
	}
	
	private void tearDown() {
		this.close();
	}
}