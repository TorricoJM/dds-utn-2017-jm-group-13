package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import repositories.Repositorios;

public abstract class LevantadorDeCuentasDeEmpresas {
	
	public void levantarEmpresasDe(String path) throws FileNotFoundException,IOException{	//metodo que sube las empresas a memoria
		this.cargarEnRepositorio(this.levantarEmpresasDelArchivo(path));
	}
	
	
	private void cargarEnRepositorio(List<Empresa> empresas) {
		empresas.stream().forEach((empresa) -> this.insertarEmpresaEnRepositorioLocal(empresa));;
	}

	private void insertarEmpresaEnRepositorioLocal(Empresa empresa) {
		if(ValidadorDeCargaDeEmpresasEnRepositorio.getInstance().yaEstaCargadaEnRepositorioUna(empresa)){
			this.actualizarValorDe(empresa);
		}
		else
			Repositorios.listaEmpresas.add(empresa);
	}
	
	private void actualizarValorDe(Empresa empresa){
		int indice = ValidadorDeCargaDeEmpresasEnRepositorio.getInstance().obtenerIndiceDeEmpresaAActualizarPor(empresa);
		Repositorios.listaEmpresas.get(indice).setValor(empresa.getValor());
		
		System.out.println("estoy actualizando valores de empresas   " + Repositorios.listaEmpresas.get(0).getNombre());
	}
	
	
	protected abstract List<Empresa> levantarEmpresasDelArchivo(String path) throws FileNotFoundException, IOException;
}
