package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import model.SelectorDeArchivos;

@Observable
public class MenuPrincipalViewModel {

	public void importarAchivo(){
		new SelectorDeArchivos().seleccionar();
	}
}
