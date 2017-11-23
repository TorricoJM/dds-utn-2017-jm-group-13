package imports;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledImport{
	
	private final String rutaDestino = "empresasTest.csv";
	public final ScheduledExecutorService importProgramado =
			Executors.newScheduledThreadPool(1);
	
	public void importarCadaXMinutos(long minutos) {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		final Runnable scheduler = new Runnable() {
			public void run() {
				System.out.println("importando");
				new ImportFileHandler()
				.procesarArchivo(rutaDestino);
			}
		};
		@SuppressWarnings("unused")
		// se guarda en una variable el schedule
		// por si se requiere que se cancele después de un lapso de tiempo
		final ScheduledFuture<?> schedulerHandler = 
				importProgramado.scheduleAtFixedRate(scheduler, 1, 20, TimeUnit.SECONDS);
	}
}

