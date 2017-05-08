package model;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectorDeArchivos {

	private Component componente = null;
	private JFileChooser selector = new JFileChooser();

	public void seleccionar() {
		selector.requestFocusInWindow();
		selector.setFileFilter(new FileNameExtensionFilter(null, "csv"));
		if (selector.showOpenDialog(componente) == JFileChooser.APPROVE_OPTION)
			new ImportadorCSV(selector.getSelectedFile().getPath()).importarEmpresas();
	}
}