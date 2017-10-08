package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

	public static ModelAndView home(Request request, Response response) {
		return new ModelAndView(null, "login/login.html");
	}

	public static ModelAndView login(Request req, Response res) {
		//String nombre = req.queryParams("nombre");

		
		// Buscar usuario en base por nombre
		// Agarrar usuario y comparar contraseña con req.queryParams("password")
		if (req.queryParams("nombre").equals("admin") && req.queryParams("password").equals("admin")) {
			res.redirect("/admin");
		}
		return null;
	}
}
