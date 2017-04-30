package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;

public class LevantadorCSV extends LevantadorDeCuentasDeEmpresas {

	private static LevantadorCSV instance;
	
	public static LevantadorCSV getInstance() {
		if(instance == null){
			instance = new LevantadorCSV();
		}
		
		return instance;
	}
	
	@Override
	protected List<Empresa> levantarEmpresasDelArchivo(String path) throws FileNotFoundException,IOException{
		CSVReader reader = new CSVReader(new FileReader(path));
	    String [] nextLine;
	    List<Empresa> empresasObtenidas = new LinkedList<>();
	    
	    try {
			while ((nextLine = reader.readNext()) != null) {
				empresasObtenidas.add(this.armarEmpresaCon(nextLine));
			}
		} catch (RuntimeException e) {	//FIXME esto es asqueroso, hay que corregir
			System.out.println("Formato de archivo incorrecto");
			reader.close();
			throw e;
		}
	    
	    reader.close();
	    
	    return empresasObtenidas;
	}

	private Empresa armarEmpresaCon(String[] nextLine) {
		Empresa empresaArmada = new Empresa();
		this.setearNombreA(empresaArmada, nextLine);
		this.setearPeriodo(empresaArmada, nextLine);
		this.setearCuenta(empresaArmada, nextLine);
		this.setearValorDeCuentaDe(empresaArmada, nextLine);
		
		return empresaArmada;
	}
	
	private void setearNombreA(Empresa empresa,String[] nextLine){
		empresa.setNombre(nextLine[0]);
	}
	private void setearPeriodo(Empresa empresa,String[] nextLine){
		empresa.setPeriodoFiscal(nextLine[1]);
	}
	private void setearCuenta(Empresa empresa,String[] nextLine){
		empresa.setCuenta(nextLine[2]);
	}
	private void setearValorDeCuentaDe(Empresa empresa,String[] nextLine){
		empresa.setValor(nextLine[3]);
	}
}
