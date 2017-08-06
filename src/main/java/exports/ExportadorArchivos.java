package exports;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import adapters.Adapter;
import model.Exception;

public class ExportadorArchivos implements Exportador {

	private Adapter<String> adaptador;
	private String path;

	public ExportadorArchivos(Adapter<String> adaptador, String path) {
		this.adaptador = adaptador;
		this.path = path;
	}

	@Override
	public void exportar() {
		try {
			FileWriter fileWriter = new FileWriter(this.path);
			PrintWriter pw = new PrintWriter(fileWriter);

			System.out.println(adaptador.transformarA());
			
			pw.print(adaptador.transformarA());

			pw.close();
		} catch (IOException exception) {
			throw new Exception("No se pudo guardar el archivo: " + this.path);
		}
	}

}
