package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import repositories.RepositorioIndicadores;

public class ImportadorIndicadores extends ImportadorDeArchivos {

	private final String PATH = "./indicadores.json";

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
			throw new ErrorImportacionException("No se pudo abrir el archivo de indicadores");
		}
	}

	public void parsearContenidoDeArchivo(String contenido) {
		Gson gson = new Gson();
		IndicadorImportado indImp = gson.fromJson(contenido, IndicadorImportado.class);
		RepositorioIndicadores.indicadores = indImp.getIndicadoresImportados();
	}
}
