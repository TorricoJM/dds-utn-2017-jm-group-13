package imports;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImportFileHandler{
	
	public void procesarArchivo(String path) {
		File archivo = new File(path);
		String destino = "src/main/resources/cuentasBatch/archivosProcesados/" + this.getHoraActualConFecha() + ".csv";
		String destinoError = "src/main/resources/cuentasBatch/archivosProcesados/" + this.getHoraActualConFecha() + "_ERROR.csv";
		if(archivo.isFile()) {
			try {
			new ImportadorDeEmpresasCSV(path).importar();
			} catch (Exception e) {
				archivo.renameTo(new File(destinoError));
				return;
			}
			archivo.renameTo(new File(destino));
		}
		else System.out.println("No se encontró el archivo para cargar cuentas");
	}
	
	public String getHoraActualConFecha() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}