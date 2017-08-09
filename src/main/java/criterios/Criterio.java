package criterios;

import java.util.List;

import model.Empresa;

public interface Criterio{
	public List<Empresa> evaluar(List<String> listaPeriodos, List<Empresa> listaEmpresas);
	
	public String getNombre();

	public boolean verificarParaUna(Empresa empresa, List<String> periodos);
}