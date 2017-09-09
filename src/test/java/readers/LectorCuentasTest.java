package readers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import adapters.AdapterCuentasToJSON;
import imports.ImportadorArchivos;
import model.Exception;
import repositories.repoArchivos.RepositorioCuentas;

import java.util.LinkedList;
import java.util.List;

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
		new ImportadorArchivos(new AdapterCuentasToJSON(), "./cuentas.json").importar();
		assertTrue(RepositorioCuentas.getInstance().getCuentas().stream()
				.allMatch(cuenta -> listaCuentas.contains(cuenta)));
	}

	@Test(expected = Exception.class)
	public void lanzaExcepcionIOSiNoExisteArchivo() {
		new ImportadorArchivos(new AdapterCuentasToJSON(), "./pathInvalido.json").importar();
	}

	@After
	public void finalizar() {
		RepositorioCuentas.deleteInstance();
	}
}
