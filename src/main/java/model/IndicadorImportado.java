package model;

import java.util.LinkedList;
import java.util.List;

public class IndicadorImportado {
	public List<Indicador> indicadores = new LinkedList<>();
	
	public List<Indicador> getIndicadoresImportados(){
		return this.indicadores;
	}
}
