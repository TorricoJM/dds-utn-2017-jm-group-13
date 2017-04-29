package model;

import repositories.Repositorios;

public class ValidadorDeCargaDeEmpresasEnRepositorio {
	private static ValidadorDeCargaDeEmpresasEnRepositorio instance;
	
	public static ValidadorDeCargaDeEmpresasEnRepositorio getInstance(){
		if(instance == null)
			instance = new ValidadorDeCargaDeEmpresasEnRepositorio();
		
		return instance	;
	}
	
	public int obtenerIndiceDeEmpresaAActualizarPor(Empresa unaEmpresa){
		Empresa empresaAModificar = Repositorios.listaEmpresas.stream()
										.filter((algunaEmpresa) -> this.tieneUnaMismaCuentaQue(unaEmpresa,algunaEmpresa))
										.findFirst().get();
		
		return Repositorios.listaEmpresas.indexOf(empresaAModificar);
	}
	
	public boolean yaEstaCargadaEnRepositorioUna(Empresa empresa){
		return Repositorios.listaEmpresas.stream()
				.anyMatch((empresaDeLaLista) -> this.tieneUnaMismaCuentaQue(empresa,empresaDeLaLista));
	}
	
	private boolean tieneUnaMismaCuentaQue(Empresa unaEmpresa,Empresa empresaDeLaLista){
		return this.mismoNombreQue(unaEmpresa, empresaDeLaLista) &&
				this.mismoPeriodoFiscalQue(unaEmpresa, empresaDeLaLista) &&
				this.mismaCuentaQue(unaEmpresa, empresaDeLaLista);
	}
	
	private boolean mismoNombreQue(Empresa unaEmpresa,Empresa empresaDeLaLista){
		return unaEmpresa.getNombre().equals(empresaDeLaLista.getNombre());
	}
	private boolean mismoPeriodoFiscalQue(Empresa unaEmpresa,Empresa empresaDeLaLista){
		return unaEmpresa.getPeriodoFiscal().equals(empresaDeLaLista.getPeriodoFiscal());
	}
	private boolean mismaCuentaQue(Empresa unaEmpresa,Empresa empresaDeLaLista){
		return unaEmpresa.getCuenta().equals(empresaDeLaLista.getCuenta());
	}
}
