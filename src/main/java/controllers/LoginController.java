package controllers;

import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import user.User;

public class LoginController {

	public static ModelAndView home(Request request, Response response) {
		return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView login(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		String password = request.queryParams("password");
		User user = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(nombre);
		
		if(user != null){
			if(password.equals(user.getPassword())){
				
				request.session(true);
				request.session().attribute("user", nombre);
				request.session().attribute("password", password);
				
				String urlAnterior = request.session().attribute("urlFrom");
				if(urlAnterior == null || urlAnterior.isEmpty() || urlAnterior.equals("/login"))
					response.redirect("/home");
					else
					response.redirect(urlAnterior);
			} else {
				response.redirect("/login");
			}
		}
		response.redirect("/login");
		return null;
	}
}