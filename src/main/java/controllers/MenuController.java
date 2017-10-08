package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MenuController {
	public static ModelAndView menu(Request request, Response response) {
		return new ModelAndView(null, "menu/menu.html");
	}

}
