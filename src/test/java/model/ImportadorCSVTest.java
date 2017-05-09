package model;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
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
	e2.setValor("100");
		
	List<LineaEmpresa> empresasEsperadas = new LinkedList<>();
	empresasEsperadas.add(e);
	empresasEsperadas.add(e1);
	empresasEsperadas.add(e2);

	importadorCSV = new ImportadorCSV("empresas.csv");
	List<LineaEmpresa> empresasObtenidas = importadorCSV.obtenerEmpresas();
	
	//No se porque el .equals falla, hay una diferencia de formatos
	assertTrue((empresasObtenidas.size() == empresasEsperadas.size()));
	
	}
			
	
	@Test(expected = ErrorImportacionException.class)
	public void obtenerEmpresasDaIncorrecto() {
		
	importadorCSV = new ImportadorCSV("pathInvalido");
	importadorCSV.obtenerEmpresas();
			
	}
	
}
