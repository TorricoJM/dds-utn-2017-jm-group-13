package exports;

import adapters.AdaptadorSalienteArchivo;
import readers.LectorIndicadores;
import repositories.RepositorioIndicadores;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExportadorIndicadoresTest {

	AdaptadorSalienteArchivo mockedAdapter = mock(AdaptadorSalienteArchivo.class);
	ExportadorIndicadores exportador = new ExportadorIndicadores(mockedAdapter);

	final String INDICADORES = "[{\"nombre\":\"Indicador Nombre Test\",\"operacion\":\"1000 + 500\"}]";

	@Before
	public void inicializar() {
		new LectorIndicadores("./indicadores.json").importar();
	}
	
	@Test
	public void seParseanIndicadoresAJSONCorrectamente() {
		exportador.exportar();
		verify(mockedAdapter).guardarEnArchivo(INDICADORES);
	}

	@After
	public void finalizar() {
		RepositorioIndicadores.deleteInstance();
	}
}
