package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import imports.ImportadorDeEmpresasCSV;

public class ImportadorCSVTest {

	public ImportadorDeEmpresasCSV importadorCSV;

	@Test
	public void obtenerEmpresasDaCorrecto() {

		LineaEmpresa e = new LineaEmpresa();
		e.setNombre("coca cola");
		e.setPeriodo("2017");
		e.setCuenta("ebitda");
		e.setValor("1025.55");

		LineaEmpresa e1 = new LineaEmpresa();
		e1.setNombre("coca cola");
		e1.setPeriodo("2017");
		e1.setCuenta("free cash flow");
		e1.setValor("99.45");

		LineaEmpresa e2 = new LineaEmpresa();
		e2.setNombre("coca cola");
		e2.setPeriodo("2016");
		e2.setCuenta("fds");
		e2.setValor("0");

		LineaEmpresa e3 = new LineaEmpresa();
		e3.setNombre("sprite");
		e3.setPeriodo("2016");
		e3.setCuenta("fds");
		e3.setValor("1000000");

		LineaEmpresa e4 = new LineaEmpresa();
		e4.setNombre("sprite");
		e4.setPeriodo("2016");
		e4.setCuenta("ebitda");
		e4.setValor("500");

		LineaEmpresa e5 = new LineaEmpresa();
		e5.setNombre("facebook");
		e5.setPeriodo("2016");
		e5.setCuenta("operaciones continuas");
		e5.setValor("4273000000");

		LineaEmpresa e6 = new LineaEmpresa();
		e6.setNombre("facebook");
		e6.setPeriodo("2016");
		e6.setCuenta("operaciones discontinuas");
		e6.setValor("0");

		List<LineaEmpresa> empresasEsperadas = new LinkedList<>();
		empresasEsperadas.add(e);
		empresasEsperadas.add(e1);
		empresasEsperadas.add(e2);
		empresasEsperadas.add(e3);
		empresasEsperadas.add(e4);
		empresasEsperadas.add(e5);
		empresasEsperadas.add(e6);

		importadorCSV = new ImportadorDeEmpresasCSV("empresas.csv");
		List<LineaEmpresa> empresasObtenidas = importadorCSV.importar();

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

		assertEquals(empresasObtenidas.get(3).getNombre(), empresasEsperadas.get(3).getNombre());
		assertEquals(empresasObtenidas.get(3).getPeriodo(), empresasEsperadas.get(3).getPeriodo());
		assertEquals(empresasObtenidas.get(3).getCuenta(), empresasEsperadas.get(3).getCuenta());
		assertEquals(empresasObtenidas.get(3).getValor(), empresasEsperadas.get(3).getValor());

		assertEquals(empresasObtenidas.get(4).getNombre(), empresasEsperadas.get(4).getNombre());
		assertEquals(empresasObtenidas.get(4).getPeriodo(), empresasEsperadas.get(4).getPeriodo());
		assertEquals(empresasObtenidas.get(4).getCuenta(), empresasEsperadas.get(4).getCuenta());
		assertEquals(empresasObtenidas.get(4).getValor(), empresasEsperadas.get(4).getValor());

		assertEquals(empresasObtenidas.get(5).getNombre(), empresasEsperadas.get(5).getNombre());
		assertEquals(empresasObtenidas.get(5).getPeriodo(), empresasEsperadas.get(5).getPeriodo());
		assertEquals(empresasObtenidas.get(5).getCuenta(), empresasEsperadas.get(5).getCuenta());
		assertEquals(empresasObtenidas.get(5).getValor(), empresasEsperadas.get(5).getValor());

		assertEquals(empresasObtenidas.get(6).getNombre(), empresasEsperadas.get(6).getNombre());
		assertEquals(empresasObtenidas.get(6).getPeriodo(), empresasEsperadas.get(6).getPeriodo());
		assertEquals(empresasObtenidas.get(6).getCuenta(), empresasEsperadas.get(6).getCuenta());
		assertEquals(empresasObtenidas.get(6).getValor(), empresasEsperadas.get(6).getValor());

	}

	@Test(expected = Exception.class)
	public void obtenerEmpresasNoExisteElArchivoDaIncorrecto() {

		importadorCSV = new ImportadorDeEmpresasCSV("pathInvalido");
		importadorCSV.obtenerEmpresas();

	}

}
