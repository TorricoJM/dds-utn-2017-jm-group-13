package repositories;

import java.util.LinkedList;
import java.util.List;

import model.Empresa;

public class Repositorios {
	
	public static List<Empresa> listaEmpresas = new LinkedList<>();
	
	public static List<Empresa> all(){
		return listaEmpresas;
	}
}
