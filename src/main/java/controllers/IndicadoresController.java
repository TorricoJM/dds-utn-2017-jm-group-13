package controllers;

import server.AuthenticationFilter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {

	public static ModelAndView home(Request request, Response response) {
		
		AuthenticationFilter auth = new AuthenticationFilter();
		auth.isAuthorized(request, response);

		return new ModelAndView(null, "indicadores/consultarIndicadores.hbs");
	}
	
}
