package model;

import java.awt.Component;
import javax.swing.JFileChooser;

public class SelectorDeArchivos {

	private Component componente = null;
	private JFileChooser selector = new JFileChooser();

	public void seleccionar() {
		selector.requestFocusInWindow();
		if (selector.showOpenDialog(componente) == JFileChooser.APPROVE_OPTION)
			ImportadorCSV.getInstance().importarEmpresasDe(selector.getSelectedFile().getPath());
	}
}
