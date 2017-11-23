package imports;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImportFileHandler{
	
	public void procesarArchivo(String path) {
		System.out.println(path);
		File archivo = new File(path);
		String destino = "/cuentasBatch/archivosProcesados/";
		if(archivo.isFile()) {
			System.out.println("voy a importar el archivo");
			new ImportadorDeEmpresasCSV(path).importar();
			archivo.renameTo(new File(destino+this.getHoraActualConFecha()));
			System.out.println("Archivo procesado correctamente");
		}
		else System.out.println("No se encontró el archivo para cargar cuentas");
	}
	
	public String getHoraActualConFecha() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}