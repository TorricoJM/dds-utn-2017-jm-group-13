package model;

import java.io.*;

public class Adapter_Archivos {
	
	File archivo;
	FileReader fr;
	BufferedReader br;
	String respuesta;
	
	public Adapter_Archivos(){
	}
	
	public String obtenerJsonDelArchivo(String path){
		try{
			archivo = new File(path);		//horriblemente a bajo nivel, pero sirve
			FileInputStream fis = new FileInputStream(archivo);
			byte[] data = new byte[(int) archivo.length()];
			fis.read(data);
			fis.close();
	
			respuesta = new String(data);
		}
		catch (Exception eAbrirArchivo) {
			System.out.println(eAbrirArchivo); //cubro la posibilidad que hayan errores
			
		}
		
		return respuesta;
	}
	
	public void guardarJsonEnElArchivo(String path, String cadena){
		  try {
		      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
		      out.print(cadena);
		      out.close();
		  }
		  catch (IOException e) {
		      System.out.println(e);
		  }
	}
}