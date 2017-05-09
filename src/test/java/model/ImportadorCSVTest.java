package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ImportadorCSVTest {

	public ImportadorCSV importadorCSV;

	@Test
	public void obtenerEmpresasDaCorrecto() {

		LineaEmpresa e = new LineaEmpresa();
		e.setNombre("coca cola");
		e.setPeriodo("2017");
		e.setCuenta("ebitda");
		e.setValor("1027.50");

		LineaEmpresa e1 = new LineaEmpresa();
		e1.setNombre("coca cola");
		e1.setPeriodo("2016");
		e1.setCuenta("fds");
		e1.setValor("0");

		LineaEmpresa e2 = new LineaEmpresa();
		e2.setNombre("sprite");
		e2.setPeriodo("2016");
		e2.setCuenta("fds");
		e2.setValor("1000000");

		List<LineaEmpresa> empresasEsperadas = new LinkedList<>();
		empresasEsperadas.add(e);
		empresasEsperadas.add(e1);
		empresasEsperadas.add(e2);

		importadorCSV = new ImportadorCSV("empresas.csv");
		List<LineaEmpresa> empresasObtenidas = importadorCSV.obtenerEmpresas();

		// No se porque si hago assertEquals(empresasObtenidas,
		// empresasEsperadas) no funciona y comparando atributo a atrubuto si.
		assertEquals(empresasObtenidas.get(0).getNombre(), empresasEsperadas.get(0).getNombre());
		assertEquals(empresasObtenidas.get(0).getPeriodo(), empresasEsperadas.get(0).getPeriodo());
		assertEquals(empresasObtenidas.get(0).getCuenta(), empresasEsperadas.get(0).getCuenta());
		assertEquals(empresasObtenidas.get(0).getValor(), empresasEsperadas.get(0).getValor());

		assertEquals(empresasObtenidas.get(1).getNombre(), empresasEsperadas.get(1).getNombre());
		assertEquals(empresasObtenidas.get(1).getPeriodo(), empresasEsperadas.get(1).getPeriodo());
		assertEquals(empresasObtenidas.get(1).getCuenta(), empresasEsperadas.get(1).getCuenta());
		assertEquals(empresasObtenidas.get(1).getValor(), empresasEsperadas.get(1).getValor());

		assertEquals(empresasObtenidas.get(2).getNombre(), empresasEsperadas.get(2).getNombre());
		assertEquals(empresasObtenidas.get(2).getPeriodo(), empresasEsperadas.get(2).getPeriodo());
		assertEquals(empresasObtenidas.get(2).getCuenta(), empresasEsperadas.get(2).getCuenta());
		assertEquals(empresasObtenidas.get(2).getValor(), empresasEsperadas.get(2).getValor());

	}

	@Test(expected = ErrorImportacionException.class)
	public void obtenerEmpresasNoExisteElArchivoDaIncorrecto() {

		importadorCSV = new ImportadorCSV("pathInvalido");
		importadorCSV.obtenerEmpresas();

	}

	@Test(expected = ErrorImportacionException.class)
	public void obtenerEmpresasMalFormatoDaIncorrecto() {

		importadorCSV = new ImportadorCSV("pom.xml");
		importadorCSV.obtenerEmpresas();

	}

	@Test(expected = ErrorImportacionException.class)
	public void obtenerEmpresasMalFormato2DaIncorrecto() {

		importadorCSV = new ImportadorCSV("README.md");
		importadorCSV.obtenerEmpresas();

	}

}
