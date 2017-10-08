package controllers;

import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import user.User;

public class LoginController {

	public static ModelAndView home(Request request, Response response) {
		return new ModelAndView(null, "login/login.html");
	}

	public static ModelAndView login(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		String password = request.queryParams("password");
		User user = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(nombre);

		if(password == user.getPassword()){
			response.redirect("/home");
		} else {
			response.redirect("/");
		}
		return null;
	}
}
