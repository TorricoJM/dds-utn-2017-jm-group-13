package ui.viewModels;

import org.uqbar.commons.utils.Observable;

import imports.ImportadorArchivos;
import adapters.AdapterCuentasToJSON;
import adapters.AdapterIndicadoresToJSON;

@Observable
public class MenuPrincipalViewModel {

	public MenuPrincipalViewModel() {
		new ImportadorArchivos(new AdapterCuentasToJSON(), "./cuentas.json").importar();
		new ImportadorArchivos(new AdapterIndicadoresToJSON(), "./indicadores.json").importar();
	}
}
