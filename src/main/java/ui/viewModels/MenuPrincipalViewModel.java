package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import model.ImportadorIndicadores;

@Observable
public class MenuPrincipalViewModel {

	public MenuPrincipalViewModel() {
		new ImportadorIndicadores().importar();
	}
}
