package readers;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import adapters.AdapterIndicadoresToJSON;
import imports.ImportadorArchivos;
import indicators.DataIndicador;
import indicators.Indicador;
import indicators.PredefinidoPruebaAcida;
import indicators.PredefinidoROA;
import indicators.PredefinidoROE;
import indicators.PredefinidoROI;
import model.Exception;
import repositories.repoArchivos.RepositorioIndicadores;

public class LectorIndicadoresTest {

	List<Indicador> listaIndicadores;

	@Before
	public void inicializar() {
		listaIndicadores = new LinkedList<>();
		listaIndicadores.add(new PredefinidoPruebaAcida());
		listaIndicadores.add(new PredefinidoROA());
		listaIndicadores.add(new PredefinidoROE());
		listaIndicadores.add(new PredefinidoROI());
	}

	@Test
	public void seCarganIndicadoresPredefinidosExitosamente() {
		assertTrue(RepositorioIndicadores.getInstance().getElementos().stream()
				.allMatch(indicador -> listaIndicadores.contains(indicador)));
	}

	@Before
	public void seAgregaIndicadorCustomDePrueba() {
		listaIndicadores.add(new DataIndicador("Indicador Nombre Test", "1000 + 500"));
	}

	@Test
	public void seLeeCorrectamenteElArchivoIndicadoresJSON() {
		new ImportadorArchivos(new AdapterIndicadoresToJSON(), "./indicadores.json").importar();
		assertTrue(RepositorioIndicadores.getInstance().getElementos().stream().allMatch(indicador -> listaIndicadores
				.stream().anyMatch(hardInd -> hardInd.getNombre().equals(indicador.getNombre()))));
	}// verifica que todos los indicadores del repo, sean los consignados en el @Before, matcheados por nombre

	@Test(expected = Exception.class)
	public void lanzaExcepcionIOSiNoExisteArchivo() {
		new ImportadorArchivos(new AdapterIndicadoresToJSON(), "./pathInvalido.json").importar();
	}

	@After
	public void finalizar() {
		RepositorioIndicadores.deleteInstance();
	}
}
