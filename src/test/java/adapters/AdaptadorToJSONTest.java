package adapters;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import readers.LectorCuentas;
import readers.LectorIndicadores;
import repositories.RepositorioCuentas;
import repositories.RepositorioIndicadores;

public class AdaptadorToJSONTest {

	final String cuentas = "[\"ebitda\",\"free cash flow\",\"fds\",\"operaciones continuas\",\"operaciones discontinuas\"]";
	final String indicadores = "[{\"nombre\":\"Indicador Nombre Test\",\"operacion\":\"1000 + 500\"}]";
	
	@Before
	public void inicializar() {
		new LectorCuentas("./cuentas.json").importar();
		new LectorIndicadores("./indicadores.json").importar();
	}
	
	@Test
	public void seAdaptanLasCuentasCorrectamente() {
		assertEquals(new AdapterCuentasToJSON().transformar(),cuentas);
	}
	
	@Test
	public void seAdaptanLosIndicadoresCorrectamente() {
		assertEquals(new AdapterIndicadoresToJSON().transformar(),indicadores);
	}
	
	@After
	public void finalizar() {
		RepositorioCuentas.deleteInstance();
		RepositorioIndicadores.deleteInstance();
	}
}
