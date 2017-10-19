package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import indicators.DataIndicador;
import indicators.Indicador;
import model.Empresa;
import model.PeriodoFiscal;
import repositories.RepositorioEmpresas;
import repositories.RepositorioIndicadores;
import repositories.RepositorioUsuarios;
import server.AuthenticationFilter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import user.User;

public class IndicadoresController extends Controller{

	public static ModelAndView listar(Request request, Response response) {
		AuthenticationFilter auth = new AuthenticationFilter();
		setearRutaAnterior(request,response);
		auth.isAuthorized(request, response);

		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		User user = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(username);
		List<Indicador> indicadores = RepositorioIndicadores.getInstance().getElementosByUserID(user.getId());

		model.put("Indicadores", indicadores);
		model.put("usuario", auth);
		model.put("username", username);
		return new ModelAndView(model, "indicadores/indicadores.hbs");
	}

	public static ModelAndView consultar(Request request, Response response) {
		List<Empresa> empresas = RepositorioEmpresas.getInstance().getElementos();
		Map<String, Object> model = new HashMap<>();
		model.put("empresas", empresas);
		
		return new ModelAndView(model, "indicadores/consultarIndicadores.hbs");
	}

	
	public static ModelAndView home(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		AuthenticationFilter auth = new AuthenticationFilter();
		setearRutaAnterior(request,response);
		auth.isAuthorized(request, response);
		model.put("usuario", auth);
		model.put("username", request.session().attribute("user"));
		return new ModelAndView(model, "indicadores/crearIndicador.hbs");
	}
	
	public static ModelAndView crear(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		String operacion = request.queryParams("operacion");
		User usuario = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(request.session().attribute("user"));
		DataIndicador nuevoIndicador = new DataIndicador(nombre, operacion);
		usuario.agregarIndicador(nuevoIndicador);
		RepositorioUsuarios.getInstance().agregar(usuario);
		response.redirect("/");
		return null;
	}
}
