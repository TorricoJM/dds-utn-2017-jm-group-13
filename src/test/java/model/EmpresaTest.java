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
	public void cargarOModificarCuentaParaUnaDaCorrectoCargaNuevaCuenta() {
		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setPeriodo("1000");
		lineaEmpresa.setCuenta("nuevaCuenta");
		lineaEmpresa.setValor("10");

		empresa.cargarOModificarCuentaParaUna(lineaEmpresa);
		assertTrue(empresa.getPeriodos().get(1).getCuentas().get(0).getCuenta().equals("nuevaCuenta"));
		assertTrue(empresa.getPeriodos().get(1).getCuentas().get(0).getValor().equals("10"));
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
