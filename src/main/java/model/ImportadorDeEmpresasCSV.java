package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class ImportadorDeEmpresasCSV extends ImportadorDeEmpresas {

	private String path;

	public ImportadorDeEmpresasCSV(String path) {
		this.path = path;
	}
	
	private String getPath(){
		return path;
	}

	@Override
	protected List<LineaEmpresa> obtenerEmpresas() {
		CSVReader reader;
		List<LineaEmpresa> empresasObtenidas = new LinkedList<>();

		try {
			reader = new CSVReader(new FileReader(this.getPath()));
			HeaderColumnNameMappingStrategy<LineaEmpresa> strat = new HeaderColumnNameMappingStrategy<LineaEmpresa>();
			CsvToBean<LineaEmpresa> csv = new CsvToBean<LineaEmpresa>(); 
			
			strat.setType(LineaEmpresa.class);
			
			empresasObtenidas = csv.parse(strat, reader);
			
			reader.close();
		} catch (FileNotFoundException exception) {
			throw new Exception("No existe el archivo");
		} catch (IOException exception) {
			throw new Exception("Error al leer el archivo");
		} catch (RuntimeException exception) {
			throw new Exception("No se ha podido cargar el archivo. Formato incorrecto");
		}

		return empresasObtenidas;
	}
}
