package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class LevantadorCSV extends LevantadorDeCuentasDeEmpresas {

	@Override
	public List<Empresa> levantarEmpresasDelArchivo(String path) throws FileNotFoundException{
		HeaderColumnNameMappingStrategy<Empresa> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Empresa.class);
        CsvToBean<Empresa> csvToBean = new CsvToBean<>();
        
        return csvToBean.parse(strategy, new FileReader(path));
	}
}
