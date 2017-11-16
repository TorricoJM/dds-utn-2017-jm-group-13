package server;

import spark.Request;
import spark.Response;

public class AuthenticationFilter {
	public void autenticar(Request request, Response response) {
		if(request.session().attribute("user") == null
				&& !esRutaPublica(request.pathInfo())) {
			request.session().attribute("urlAnterior",request.pathInfo());
			response.redirect("/login");
		}
	}
	
	public static boolean esRutaPublica(String ruta) {
		return ruta.equals("/") || ruta.equals("/home") || ruta.equals("/login");
	}

}