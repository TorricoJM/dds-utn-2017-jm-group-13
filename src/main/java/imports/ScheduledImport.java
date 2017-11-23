package imports;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledImport{
	
	private final String rutaDestino =
			"src/main/resources/cuentasBatch/directorioEspecial/empresasTest.csv";
	public final ScheduledExecutorService importProgramado =
			Executors.newScheduledThreadPool(1);
	
	public void importarCadaXMinutos(long minutos) {
		final Runnable scheduler = new Runnable() {
			public void run() {
				new ImportFileHandler()
				.procesarArchivo(rutaDestino);

			}
		};
		@SuppressWarnings("unused")
		// se guarda en una variable el schedule
		// por si se requiere que se cancele después de un lapso de tiempo
		final ScheduledFuture<?> schedulerHandler = 
				importProgramado.scheduleAtFixedRate(scheduler, 20, 20, TimeUnit.SECONDS);
	}
}

