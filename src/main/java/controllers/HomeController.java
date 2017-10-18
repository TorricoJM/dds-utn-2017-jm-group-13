package controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController{
	
	public static ModelAndView home(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		String username = request.session().attribute("user");
		if(username !=null) {
			model.put("usuario", username);
			model.put("username", username);
		}
		return new ModelAndView(model, "home.hbs");
	}
}