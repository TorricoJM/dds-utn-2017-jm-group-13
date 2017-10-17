package controllers;

import java.util.List;

import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import user.User;

public class LoginController {

	public static ModelAndView home(Request request, Response response) {
		return new ModelAndView(null, "login.html");
	}
	
	public static ModelAndView login(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		String password = request.queryParams("password");
		List<User> users = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(nombre);
		
		if(users.size() != 0){
			User user = users.get(0);
			if(password.equals(user.getPassword())){
				
				request.session(true);
				request.session().attribute("user", nombre);
				request.session().attribute("password", password);
				
				response.redirect("/");
			} else {
				response.redirect("login");
			}
		}
		response.redirect("/login");
		return null;
	}
}
