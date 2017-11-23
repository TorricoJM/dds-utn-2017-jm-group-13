package imports;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import server.SeedPrecalculados;

public class ImportFileHandler{
	
	public void procesarArchivo(String path) {
		EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
		File archivo = new File(path);
		String destino = "src/main/resources/cuentasBatch/archivosProcesados/" + this.getHoraActualConFecha() + ".csv";
		String destinoError = "src/main/resources/cuentasBatch/archivosProcesados/" + this.getHoraActualConFecha() + "_ERROR.csv";
		if(archivo.isFile()) {
			tx.begin();
			try {
			new ImportadorDeEmpresasCSV(path).importar();
			} catch (Exception e) {
				tx.rollback();
				archivo.renameTo(new File(destinoError));
				return;
			}
			tx.commit();
			archivo.renameTo(new File(destino));
			SeedPrecalculados.getInstance().removerResultadosActuales().precalcular();
			System.out.println("Indicadores precalculados");
		}
		else System.out.println("No se encontró el archivo para cargar cuentas");
	}
	
	public String getHoraActualConFecha() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}