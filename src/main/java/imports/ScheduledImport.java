package imports;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.chainsaw.Main;
import org.eclipse.jetty.server.Server;

public class ScheduledImport{
	
	private final String rutaDestino = Server.class.getResource("cuentasBatch/directorioEspecial/empresas.csv").getPath();
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
				importProgramado.scheduleAtFixedRate(scheduler, 0, minutos, TimeUnit.MINUTES);
	}
}

