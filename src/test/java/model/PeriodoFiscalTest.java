package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PeriodoFiscalTest {

	PeriodoFiscal periodo2016;

	@Before
	public void initialize() {
		periodo2016 = new PeriodoFiscal("2016");

		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setCuenta("cuentaVieja");

		periodo2016.agregarUnaCuentaPara(lineaEmpresa);
	}

	@Test
	public void yaExisteUnaCuentaParaDaTrue() {

		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setCuenta("cuentaVieja");

		assertTrue(periodo2016.yaExisteUnaCuentaPara(lineaEmpresa));

	}

	@Test
	public void yaExisteUnaCuentaParaDaFalse() {

		LineaEmpresa lineaEmpresa = new LineaEmpresa();
		lineaEmpresa.setCuenta("cuentaViejaNueva");

		assertFalse(periodo2016.yaExisteUnaCuentaPara(lineaEmpresa));

	}

}
