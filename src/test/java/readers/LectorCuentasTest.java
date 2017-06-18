package readers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Exception;

import java.util.LinkedList;
import java.util.List;

import repositories.RepositorioCuentas;

public class LectorCuentasTest {

	List<String> listaCuentas;

	@Before
	public void inicializar() {
		listaCuentas = new LinkedList<>();
		listaCuentas.add("ebitda");
		listaCuentas.add("free cash flow");
		listaCuentas.add("fds");
		listaCuentas.add("operaciones continuas");
		listaCuentas.add("operaciones discontinuas");
	}

	@Test
	public void seLeeCorrectamenteElArchivoCuentasJSON() {
		new LectorCuentas("./cuentas.json").importar();
		assertTrue(RepositorioCuentas.getInstance().getCuentas().stream()
				.allMatch(cuenta -> listaCuentas.contains(cuenta)));
	}
	
	@Test(expected = Exception.class)
	public void lanzaExcepcionIOSiNoExisteArchivo(){
		new LectorCuentas("pathInvalido").importar();
	}
}
