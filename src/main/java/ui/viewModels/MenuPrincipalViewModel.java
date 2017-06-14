package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import readers.LectorCuentas;
import readers.LectorIndicadores;

@Observable
public class MenuPrincipalViewModel {

	public MenuPrincipalViewModel() {
		new LectorCuentas().importar();
		new LectorIndicadores().importar();
	}
}
