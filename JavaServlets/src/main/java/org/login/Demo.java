package org.login;

import java.io.IOException;

import org.utils.CommonUtils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Demo extends HttpServlet {
	
	// localhost:8080/JavaServlets/getUserDetails
	
	protected void doPost(HttpServletRequest resuest, HttpServletResponse response) throws IOException {
		response.getWriter().println("Hello, am post");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String authTokenFromHeader = request.getHeader("Authorization");
			String usernameInPayload = new CommonUtils().readJSONInput(request.getReader()).getString("username");
			if(new Login().getLoggedInUser(authTokenFromHeader).equals(usernameInPayload)) {
				response.getWriter().println("Hello, am get");
			} else {
				response.getWriter().println("Unauthorized access");
			}
		} catch (IOException e) {
			e.printStackTrace();
			response.getWriter().println("Internal exception occured");
		}
		
	}

}
