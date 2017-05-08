package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import model.SelectorDeArchivos;

@Observable
public class MenuPrincipalViewModel {

	private String pathDelSelector;
	
	public void importarAchivo(){
		new SelectorDeArchivos().seleccionar();
	}

	public String getPathDelSelector() {
		return pathDelSelector;
	}
	public void setPathDelSelector(String pathDelSelector) {
		this.pathDelSelector = pathDelSelector;
	}
}
