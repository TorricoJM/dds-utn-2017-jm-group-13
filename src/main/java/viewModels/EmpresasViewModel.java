package viewModels;

import java.util.List;

import javax.ws.rs.core.MediaType;
import org.uqbar.commons.utils.Observable;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;

import model.Empresas;
import model.Empresa;
import repositories.Repositorios;

@Observable
public class EmpresasViewModel {

	//json de prueba del TP0
	//private String jsonPrueba="{\"assignments\":[{\"id\":1,\"title\":\"TPA1\",\"description\":\"Entrega	1 del TP Anual\",\"grades\":[{\"id\":1,\"value\":2,\"created_at\":\"2017-03-25T13:56:07.526Z\",\"updated_at\":\"2017-03-25T13:56:07.526Z\"},{\"id\":3,\"value\":5,\"created_at\":\"2017-03-25T13:56:07.526Z\",\"updated_at\":\"2017-03-25T13:56:07.526Z\"},{\"id\":4,\"value\":7,\"created_at\":\"2017-03-25T13:56:07.526Z\",\"updated_at\":\"2017-03-25T13:56:07.526Z\"}]},{\"id\":2,\"title\":\"TPA2\",\"description\":\"Entrega 2 del TP Anual\",\"grades\":[{\"id\":2,\"value\":7,\"created_at\":\"2017-03-25T13:56:07.526Z\",\"updated_at\":\"2017-03-25T13:56:07.526Z\"},{\"id\":5,\"value\":6,\"created_at\":\"2017-03-25T13:56:07.526Z\",\"updated_at\":\"2017-03-25T13:56:07.526Z\"}]}]}";
	
	//a modo de prueba armé un json con datos para poder mostrar algo reutilizando lo del TP0
	//tenemos que definir que formato le damos al archivo a importar con las empresas y sus cuentas
	private String jsonPrueba="{\"empresas\":[{\"id\":1,\"razonSocial\":\"Google SRL\",\"nombreFantasia\":\"Google\",\"cuit\":\"33-44455522-6\"},{\"id\":2,\"razonSocial\":\"Facebook SA\",\"nombreFantasia\":\"Faceboook\",\"cuit\":\"33-77755522-1\"},{\"id\":3,\"razonSocial\":\"Coca Cola Femsa\",\"nombreFantasia\":\"Coca-Cola\",\"cuit\":\"30-66665555-9\"}]}";

	
	//private Client client = new Client();

	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;

	public EmpresasViewModel() {
		super();
		this.levantarListaEmpresas();
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	private void levantarListaEmpresas() {
		Gson gson = new Gson();
		String respuesta = this.traerEmpresasesDelServer();
		Repositorios.empresas = gson.fromJson(respuesta, Empresas.class);
		this.empresas = Repositorios.empresas.getEmpresas();
	}

	private String traerEmpresasesDelServer() {
		/*String respuesta = this.client.resource(Repositorios.URL)
				.path(Repositorios.STUDENT_RESOURCE + Repositorios.ASSIGNMENT_RESOURCE)
				.header("Authorization", "Bearer " + Repositorios.tokenGenerico).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		return respuesta;*/
		 return this.jsonPrueba;
	}

	public int cantidadEmpresas() {
		return Repositorios.empresas.getEmpresas().toArray().length;
	}

	public void guardarEmpresaSeleccionadoEnRepositorio() {
		Repositorios.empresaSeleccionada= this.empresaSeleccionada;
	}
}
