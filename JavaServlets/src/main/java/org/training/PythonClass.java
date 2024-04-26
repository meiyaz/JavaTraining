package org.training;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * @author: meiyaz
 */
public class PythonClass extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("Hello, am from Python class");
	}
}