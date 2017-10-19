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
		model.put("user", username);
		
		return new ModelAndView(model, "home.hbs");
	}
}