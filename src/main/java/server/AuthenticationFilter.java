package server;

import spark.Request;
import spark.Response;

public class AuthenticationFilter {

	public void isAuthorized(Request req, Response res) {
		boolean authenticated = req.session().attribute("user") != null;

		if (!authenticated) {
			res.redirect("/login");
		}

	}
}