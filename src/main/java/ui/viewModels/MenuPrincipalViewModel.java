package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import model.LectorCuentas;
import model.LectorIndicadores;

@Observable
public class MenuPrincipalViewModel {

	public MenuPrincipalViewModel() {
		new LectorIndicadores().importar();
		new LectorCuentas().importar();
	}
}
