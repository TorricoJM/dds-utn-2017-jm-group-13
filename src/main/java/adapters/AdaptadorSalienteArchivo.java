package adapters;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Exception;

public class AdaptadorSalienteArchivo implements AdaptadorSaliente {

	private String path;
	
	public void setPath(String unPath) {
		this.path = unPath;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void guardarEnArchivo(String contenido){
		try {
			FileWriter fileWriter = new FileWriter(this.getPath());
			PrintWriter pw = new PrintWriter(fileWriter);

			pw.print(contenido);

			pw.close();
		} catch (IOException exception) {
			throw new Exception("No se pudo guardar el archivo: " + this.getPath());
		}
	}
}