package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;

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
		String[] nextLine;
		List<LineaEmpresa> empresasObtenidas = new LinkedList<>();

		try {
			reader = new CSVReader(new FileReader(path));
			while ((nextLine = reader.readNext()) != null) {
				empresasObtenidas.add(this.armarEmpresaCon(nextLine));
			}
			reader.close();
		} catch (FileNotFoundException exception) {
			throw new ErrorImportacionException("No existe el archivo");
		} catch (IOException exception) {
			throw new ErrorImportacionException("Formato de archivo incorrecto");
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new ErrorImportacionException("Formato de archivo incorrecto");
		}

		return empresasObtenidas;
	}

	private LineaEmpresa armarEmpresaCon(String[] nextLine) {
		LineaEmpresa empresaArmada = new LineaEmpresa();
		this.setearNombreA(empresaArmada, nextLine);
		this.setearPeriodo(empresaArmada, nextLine);
		this.setearCuenta(empresaArmada, nextLine);
		this.setearValorDeCuentaDe(empresaArmada, nextLine);

		return empresaArmada;
	}

	private void setearNombreA(LineaEmpresa empresa, String[] nextLine) {
		empresa.setNombre(nextLine[0]);
	}

	private void setearPeriodo(LineaEmpresa empresa, String[] nextLine) {
		empresa.setPeriodo(nextLine[1]);
	}

	private void setearCuenta(LineaEmpresa empresa, String[] nextLine) {
		empresa.setCuenta(nextLine[2]);
	}

	private void setearValorDeCuentaDe(LineaEmpresa empresa, String[] nextLine) {
		empresa.setValor(nextLine[3]);
	}
}
