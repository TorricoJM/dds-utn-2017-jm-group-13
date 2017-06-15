package model;

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
		periodo2016 = new PeriodoFiscal("2016");
		periodos.add(periodo2016);

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
		empresa.agregarPeriodoPara(lineaEmpresa1);
		empresa.cargarOModificarCuentaParaUna(lineaEmpresa2);
		
		assertEquals("0",empresa.obtenerPeriodoDesdeNombre("2017").obtenerCuentaDesdeNombre("ebitda").getValor());
	}

	@Test
	public void cargarOModificarCuentaParaUnaDaCorrectoPisaValorCuentaVieja() {
		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setPeriodo("2016");
		lineaEmpresa.setCuenta("cuentaVieja");
		lineaEmpresa.setValor("100");

		periodo2016.agregarUnaCuentaPara(lineaEmpresa);

		empresa.cargarOModificarCuentaParaUna(lineaEmpresa);
		assertTrue(empresa.getPeriodos().get(0).getCuentas().get(0).getValor().equals("100"));
	}

}
