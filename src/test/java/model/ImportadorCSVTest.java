package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import imports.ImportadorDeEmpresasCSV;
import repositories.RepositorioEmpresas;

public class ImportadorCSVTest {

	public ImportadorDeEmpresasCSV importadorCSV;

	@Test
	public void seCarganCorrectamenteLasEmpresas() {

		importadorCSV = new ImportadorDeEmpresasCSV("empresas.csv");
		importadorCSV.importar();

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

		assertTrue(empresasEsperadas.stream()
				.allMatch((linea) -> RepositorioEmpresas.getInstance().yaEstaCargadaUna(linea)));
	}

	@Test(expected = Exception.class)
	public void fallaAlImportarAlNoExistirArchivo() {

		importadorCSV = new ImportadorDeEmpresasCSV("pathInvalido");
		importadorCSV.importar();

	}
}
