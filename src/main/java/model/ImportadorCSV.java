package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class ImportadorCSV extends ImportadorDeCuentasDeEmpresas {

	private static ImportadorCSV instance;

	public static ImportadorCSV getInstance() {
		if (instance == null) {
			instance = new ImportadorCSV();
		}

		return instance;
	}

	@Override
	protected List<LineaEmpresa> levantarEmpresasDelArchivo(String path) {
		CSVReader reader;
		List<LineaEmpresa> empresasObtenidas = new LinkedList<>();

		try {
			reader = new CSVReader(new FileReader(path));
			ColumnPositionMappingStrategy<LineaEmpresa> strat = new ColumnPositionMappingStrategy<LineaEmpresa>();
			String[] columns = new String[] {"nombre", "periodo", "cuenta", "valor"};
			CsvToBean<LineaEmpresa> csv = new CsvToBean<LineaEmpresa>(); 
			
			strat.setType(LineaEmpresa.class); 
			strat.setColumnMapping(columns);
			
			empresasObtenidas = csv.parse(strat, reader);
			
			reader.close();
		} catch (FileNotFoundException exception) {
			throw new ErrorImportacionException("No existe el archivo");
		} catch (IOException exception) {
			throw new ErrorImportacionException("Error al leer el archivo");
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new ErrorImportacionException("Formato de archivo incorrecto");
		}

		return empresasObtenidas;
	}
}
