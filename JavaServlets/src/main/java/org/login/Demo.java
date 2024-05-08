package org.login;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Demo extends HttpServlet {
	
	// localhost:8080/JavaServlets/Demo
	
	protected void doPost(HttpServletRequest resuest, HttpServletResponse response) throws IOException {
		response.getWriter().println("Hello, am post");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			if(new Login().isValidUser(request.getHeader("Authorization"))) {
				response.getWriter().println("Hello, am get");
			} else {
				response.getWriter().println("Unauthorized access");
			}
		} catch (IOException e) {
			e.printStackTrace();
			response.getWriter().println("Exception occured");
		}
		
	}

}
