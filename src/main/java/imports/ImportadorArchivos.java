package imports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import adapters.Adapter;
import model.Exception;

public class ImportadorArchivos implements Importador {

	private Adapter<String> adaptador;
	private String path;

	public ImportadorArchivos(Adapter<String> adaptador, String path) {
		this.adaptador = adaptador;
		this.path = path;
	}
	
	@Override
	public void importar() {
		try {
			FileReader fileReader = new FileReader(this.path);
			BufferedReader buf = new BufferedReader(fileReader);

			adaptador.transformarDesde(buf.readLine());

			buf.close();

		} catch (IOException exception) {
			throw new Exception("No se pudo abrir el archivo: " + path);
		}
	}

}