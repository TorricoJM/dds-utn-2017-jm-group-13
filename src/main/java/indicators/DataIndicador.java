package indicators;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.uqbar.commons.utils.Observable;

import model.Empresa;
import model.parser.ErrorEvaluacionException;
import model.parser.objetosParser.IndicadorParser;
import repositories.RepositorioEmpresas;

@Entity
@Observable
public class DataIndicador extends Indicador {

	private String nombre;
	private String operacion;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "indicador_id")
	private List<IndicadorConResultado> precalculados;

	public DataIndicador(){
	}
	
	public DataIndicador(String nombre, String operacion) {
		this.nombre = nombre;
		this.operacion = operacion;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getOperacion() {
		return operacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	
	public void obtenerPrecalculados() {
		List<Empresa> empresas = RepositorioEmpresas.getInstance().getElementos();
		this.precalculados = empresas.stream().flatMap(empresa->obtenerPrecalculadosPorEmpresa(empresa).stream()).collect(Collectors.toList());
		precalculados.forEach(precalculado->precalculado.obtenerResultado(this.getOperacion()));
	}

	private List<IndicadorConResultado> obtenerPrecalculadosPorEmpresa(Empresa empresa) {
		List<IndicadorConResultado> precalculadosDe1Empresa = new LinkedList<>();
		empresa.getPeriodos().forEach(periodo->precalculadosDe1Empresa.add(new IndicadorConResultado(this.nombre,empresa.getNombre(),periodo.getPeriodo())));
		return precalculadosDe1Empresa;
	}

	//a eliminar con test y demás
	@Override
	public double evaluateEn(String empresaEvaluada, String periodoEvaluado) {
		try {
			return new IndicadorParser(this.nombre, this.operacion).operar(empresaEvaluada, periodoEvaluado);
		} catch (ErrorEvaluacionException e) {
			throw new ErrorEvaluacionException("No se pudo resolver");
		} catch (NullPointerException e) {
			throw new ErrorEvaluacionException(e.getMessage());
		}
	}

	@Override
	public List<IndicadorConResultado> getPrecalculados() {
		return this.precalculados;
	}
}