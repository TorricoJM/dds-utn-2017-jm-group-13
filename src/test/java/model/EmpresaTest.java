/*package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EmpresaTest {

	Empresa empresa;
	PeriodoFiscal periodo2016;

	@Before
	public void initialize() {
		List<PeriodoFiscal> periodos = new LinkedList<>();
		periodos.add(new PeriodoFiscal("2016"));

		empresa = new Empresa("EmpresaTest");
		empresa.setPeriodos(periodos);
	}

	@Test
	public void yaExisteUnPeriodoParaDaTrue() {
		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setPeriodo("2016");

		assertTrue(empresa.yaExisteUnPeriodoPara(lineaEmpresa));
	}

	@Test
	public void yaExisteUnPeriodoParaDaFalse() {
		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setPeriodo("2017");

		assertFalse(empresa.yaExisteUnPeriodoPara(lineaEmpresa));
	}

	@Test
	public void seActualizanValoresDeCuentaSiHayDosCuentasIgualesParaEmpresa() {
		
		LineaEmpresa lineaEmpresa1 = new LineaEmpresa();
		lineaEmpresa1.setNombre("coca cola");
		lineaEmpresa1.setPeriodo("2017");
		lineaEmpresa1.setCuenta("ebitda");
		lineaEmpresa1.setValor("1025.55");

		LineaEmpresa lineaEmpresa2 = new LineaEmpresa();
		lineaEmpresa2.setNombre("coca cola");
		lineaEmpresa2.setPeriodo("2017");
		lineaEmpresa2.setCuenta("ebitda");
		lineaEmpresa2.setValor("0");
		
		Empresa empresa = new Empresa(lineaEmpresa1.getNombre());
		empresa.cargarOModificarCuentaParaUna(lineaEmpresa1);
		empresa.cargarOModificarCuentaParaUna(lineaEmpresa2);
		
		assertEquals("0",empresa.obtenerPeriodoDesdeNombre("2017").obtenerCuentaDesdeNombre("ebitda").getValor());
	}
	
	@Test
	public void seObtienePeriodoDesdeNombreCorrectamente() {
		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setNombre("coca cola");
		lineaEmpresa.setPeriodo("2000");
		lineaEmpresa.setCuenta("cuenta");
		lineaEmpresa.setValor("100");
		
		PeriodoFiscal periodo = new PeriodoFiscal("2000");
		periodo.agregarUnaCuentaPara(lineaEmpresa);
		
		empresa = new Empresa("coca cola");
		empresa.agregarPeriodoPara(lineaEmpresa);
		
		assertEquals(periodo.getPeriodo(), empresa.obtenerPeriodoDesdeNombre(periodo.getPeriodo()).getPeriodo());
	}

}
*/