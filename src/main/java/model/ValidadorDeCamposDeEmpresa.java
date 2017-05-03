package model;

import repositories.Repositorios;

public class ValidadorDeCamposDeEmpresa {
	private static ValidadorDeCamposDeEmpresa instance;

	public static ValidadorDeCamposDeEmpresa getInstance() {
		if (instance == null)
			instance = new ValidadorDeCamposDeEmpresa();

		return instance;
	}

	public Empresa obtenerEmpresaAActualizarPor(LineaEmpresa unaEmpresa) {
		Empresa empresaAModificar = Repositorios.listaEmpresas.stream()
				.filter((empresaExistente) -> this.mismoNombreQue(unaEmpresa, empresaExistente)).findFirst().get();

		return Repositorios.listaEmpresas.get(Repositorios.listaEmpresas.indexOf(empresaAModificar));
	}

	public PeriodoFiscal obtenerPeriodoDeEmpresaDadaPor(Empresa empresa, LineaEmpresa lineaEmpresa) {
		PeriodoFiscal periodoAModificar = this.obtenerEmpresaAActualizarPor(lineaEmpresa).getPeriodos().stream()
				.filter((periodoDeLista) -> this.mismoPeriodoQue(lineaEmpresa, periodoDeLista)).findFirst().get();

		return empresa.getPeriodos().get(empresa.getPeriodos().indexOf(periodoAModificar));
	}

	public CuentaYValor obtenerCuentaAModificarDadaPor(PeriodoFiscal periodo, LineaEmpresa lineaEmpresa) {
		CuentaYValor cuentaModificable = periodo.getCuentas().stream()
				.filter((cuentaDeLista) -> this.mismaCuentaQue(lineaEmpresa, cuentaDeLista)).findFirst().get();

		return periodo.getCuentas().get(periodo.getCuentas().indexOf(cuentaModificable));
	}

	public boolean yaEstaCargadaEnRepositorioUna(LineaEmpresa lineaEmpresa) {
		return Repositorios.listaEmpresas.stream()
				.anyMatch((empresaDeLaLista) -> this.mismoNombreQue(lineaEmpresa, empresaDeLaLista));
	}

	public boolean yaExisteUnPeriodoPara(Empresa empresa, LineaEmpresa lineaEmpresa) {
		return empresa.getPeriodos().stream()
				.anyMatch((unPeriodo) -> unPeriodo.getPeriodo().equals(lineaEmpresa.getPeriodo()));
	}

	public boolean yaExisteUnaCuentaPara(PeriodoFiscal periodo, LineaEmpresa empresa) {
		return periodo.getCuentas().stream().anyMatch((unaCuenta) -> unaCuenta.getCuenta().equals(empresa.getCuenta()));
	}

	private boolean mismoNombreQue(LineaEmpresa unaEmpresa, Empresa empresaDeLaLista) {
		return unaEmpresa.getNombre().equals(empresaDeLaLista.getNombre());
	}

	private boolean mismoPeriodoQue(LineaEmpresa unaEmpresa, PeriodoFiscal periodoDeLista) {
		return unaEmpresa.getPeriodo().equals(periodoDeLista.getPeriodo());
	}

	private boolean mismaCuentaQue(LineaEmpresa unaEmpresa, CuentaYValor cuentaDeLista) {
		return unaEmpresa.getCuenta().equals(cuentaDeLista.getCuenta());
	}
}