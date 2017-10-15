package controllers;

import java.util.List;

import repositories.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import user.User;

public class LoginController {

	public static ModelAndView login(Request request, Response response) {
		String nombre = request.queryParams("nombre");
		String password = request.queryParams("password");
		List<User> users = RepositorioUsuarios.getInstance().obtenerUserDesdeNombre(nombre);
		
		if(users.size() != 0){
			User user = users.get(0);
			if(password.equals(user.getPassword())){
				response.redirect("/menu");
			} else {
				response.redirect("/");
			}
		}
		response.redirect("/");
		return null;
	}
}
