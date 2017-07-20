package criterios;

import java.util.List;

import model.Empresa;

public interface Criterio{
	public List<Empresa> evaluar();
	
	public String getNombre();
}