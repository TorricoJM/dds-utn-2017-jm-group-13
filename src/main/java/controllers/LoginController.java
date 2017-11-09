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
				request.session().attribute("user", user.getNombre());
				request.session().attribute("password", user.getPassword());
				
				String urlAnterior = request.session().attribute("urlAnterior");
				if(urlAnterior == null || urlAnterior.isEmpty() || urlAnterior.equals("/login"))
					response.redirect("/");
					else
					response.redirect(urlAnterior);
			} else {
				response.redirect("/login");
			}
		}
		response.redirect("/login");
		return null;
	}
	
	public static ModelAndView logout(Request request, Response response) {
		request.session(true);
		request.session().attribute("user", null);
		request.session().attribute("password", null);
		response.redirect("/");
		return null;
	}
}