package criterios;

import java.util.List;

import model.Empresa;
import model.PeriodoFiscal;

public interface Criterio{
	public List<Empresa> evaluar(List<PeriodoFiscal> lista);
	
	public String getNombre();
}