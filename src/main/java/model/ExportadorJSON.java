package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class ExportadorJSON {

	protected String PATH;

	public void exportar() {
		this.guardarEnArchivo();
	}

	private void guardarEnArchivo() {
		try {
			FileWriter fileWriter = new FileWriter(this.PATH);
			PrintWriter pw = new PrintWriter(fileWriter);

			pw.print(this.parsearContenidoDeRepositorio());

			pw.close();
		} catch (IOException exception) {
			throw new Exception("No se pudo guardar el archivo: " + PATH);
		}
	}

	protected abstract String parsearContenidoDeRepositorio();
}
