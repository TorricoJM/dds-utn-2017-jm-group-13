package exports;

import adapters.AdaptadorSalienteArchivo;
import readers.LectorCuentas;
import repositories.RepositorioCuentas;

import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExportadorCuentasTest {

	AdaptadorSalienteArchivo mockedAdapter = mock(AdaptadorSalienteArchivo.class);
	ExportadorCuentas exportador = new ExportadorCuentas(mockedAdapter);

	final String cuentas = "[\"ebitda\",\"free cash flow\",\"fds\",\"operaciones continuas\",\"operaciones discontinuas\"]";

	@Before
	public void inicializar() {
		new LectorCuentas("./cuentas.json").importar();
	}
	
	@Test
	public void seParseanCuentasAJSONCorrectamente() {
		exportador.exportar();
		verify(mockedAdapter).guardarEnArchivo(cuentas);
	}

	@After
	public void finalizar() {
		RepositorioCuentas.deleteInstance();
	}
}
