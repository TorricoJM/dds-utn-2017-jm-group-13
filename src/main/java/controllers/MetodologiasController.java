package controllers;

import java.util.HashMap;
import java.util.Map;

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
		return new ModelAndView(model, "proximamente.hbs");
	}
}
