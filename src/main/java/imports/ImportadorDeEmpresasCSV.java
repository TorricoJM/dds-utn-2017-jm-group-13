package imports;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import model.Cuenta;
import model.Empresa;
import model.Exception;
import model.LineaEmpresa;
import model.PeriodoFiscal;
import repositories.RepositorioEmpresas;

public class ImportadorDeEmpresasCSV implements Importador {

	private String path;

	public ImportadorDeEmpresasCSV(String path) {
		this.path = path;
	}
	
	private String getPath(){
		return path;
	}

	private List<Empresa> obtenerEmpresas() {
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

		return this.convertirAEmpresas(empresasObtenidas);
	}
	
	public void importar() {
		RepositorioEmpresas.getInstance().agregarMuchos(this.obtenerEmpresas());
	}
	
	private List<Empresa> convertirAEmpresas(List<LineaEmpresa> empresas) {
		return empresas.stream().map((linEmp) -> this.crearEmpresaEnBaseA(linEmp)).collect(Collectors.toList());
	}
	
	private Empresa crearEmpresaEnBaseA(LineaEmpresa lineaEmpresa) {
		Empresa nuevaEmpresa = new Empresa(lineaEmpresa.getNombre());
		PeriodoFiscal periodo = new PeriodoFiscal(lineaEmpresa.getPeriodo());
		Cuenta cuenta = new Cuenta(lineaEmpresa.getCuenta(),lineaEmpresa.getValor());
		
		periodo.agregarUna(cuenta);
		nuevaEmpresa.getPeriodos().add(periodo);
		
		return nuevaEmpresa;
	}
}
