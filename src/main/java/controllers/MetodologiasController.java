package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import repositories.RepositorioEmpresas;
import repositories.RepositorioMetodologias;
import server.AuthenticationFilter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController extends Controller {
	
	public static ModelAndView home(Request request, Response response) {
		AuthenticationFilter auth = new AuthenticationFilter();
		setearRutaAnterior(request,response);
		auth.isAuthorized(request, response);

		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		model.put("usuario", auth);
		model.put("username", username);
		
		model.put("Metodologias", RepositorioMetodologias.getInstance().getElementos());
		
		return new ModelAndView(model, "metodologias/metodologias.hbs");
	}
	
	public static ModelAndView consulta(Request request, Response response) {
		AuthenticationFilter auth = new AuthenticationFilter();
		setearRutaAnterior(request,response);
		auth.isAuthorized(request, response);
		
		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		model.put("usuario", auth);
		model.put("username", username);
		
		model.put("metodologia", request.queryParams("metodologia"));
		model.put("periodos", MetodologiasController.listaPeriodos());
		
		
		return new ModelAndView(model, "metodologias/consulta.hbs");
	}
	
	private static List<String> listaPeriodos() {
		return RepositorioEmpresas.getInstance().getElementos().stream().flatMap(empresa -> empresa.getPeriodos().stream())
					.map(periodo -> periodo.getPeriodo()).distinct().sorted().collect(Collectors.toList());
	}
}
