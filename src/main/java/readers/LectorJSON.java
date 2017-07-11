package readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import imports.*;
import model.Exception;

public abstract class LectorJSON implements Importador {

	protected String PATH;

	@Override
	public void importar() {
		this.leerArchivo();
	}

	private void leerArchivo() {
		try {
			FileReader fileReader = new FileReader(this.PATH);
			BufferedReader buf = new BufferedReader(fileReader);

			this.parsearContenidoDeArchivo(buf.readLine());

			buf.close();

		} catch (IOException exception) {
			throw new Exception("No se pudo abrir el archivo: " + PATH);
		}
	}

	protected abstract void parsearContenidoDeArchivo(String contenido);
}