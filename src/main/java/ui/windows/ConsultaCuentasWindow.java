package ui.windows;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

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
	protected void createFormPanel(Panel formPanel) {
		new Label(formPanel).setText("Hola");
	}


}