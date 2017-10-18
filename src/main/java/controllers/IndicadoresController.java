package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import indicators.Indicador;
import repositories.RepositorioIndicadores;
import server.AuthenticationFilter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController extends Controller{

	public static ModelAndView listar(Request request, Response response) {
		AuthenticationFilter auth = new AuthenticationFilter();
		setearRutaAnterior(request,response);
		auth.isAuthorized(request, response);

		Map<String, Object> model = new HashMap<>();
		List<Indicador> indicadores = RepositorioIndicadores.getInstance().getElementos();

		model.put("Indicadores", indicadores);
		model.put("usuario", auth);
		model.put("username", request.session().attribute("user"));

		return new ModelAndView(model, "indicadores/indicadores.hbs");
	}
	
	public static ModelAndView consultar(Request request, Response response) {
		
		
		Map<String, Object> model = new HashMap<>();
		return new ModelAndView(model, "indicadores/consultarIndicadores.hbs");
	}
}
