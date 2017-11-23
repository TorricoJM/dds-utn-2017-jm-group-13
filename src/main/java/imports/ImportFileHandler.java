package imports;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImportFileHandler{
	
	public void procesarArchivo(String path) {
		File archivo = new File(path);
		File archivoProcesado = 
				new File("/proyecto_anual/src/main/resources/cuentasBatch/directorioEspecial");
		Path source = archivo.toPath();
		Path destino = archivoProcesado.toPath();
		if(archivo.isFile()) {
			new ImportadorDeEmpresasCSV(path).importar();
			try {
				Files.move(source, destino.resolve(this.getHoraActualConFecha()), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.out.println("Error al mover el archivo procesado");
			}
			
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