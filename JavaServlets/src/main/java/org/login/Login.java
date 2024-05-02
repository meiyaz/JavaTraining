package org.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;
import org.utils.CommonUtils;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/training";
	private final String MYSQL_USERNAME = "root";
	private final String MYSQL_PASSWORD = "root";
	private String VALIDATE_USER = "SELECT * FROM training.users where username = ? and password = ?;";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject inputData = new CommonUtils().readJSONInput(request.getReader());
		String username = inputData.getString("username");
		String password = inputData.getString("password");

		JSONObject responseObj = new JSONObject();

		new CommonUtils().registerDriver();
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD)) {
			if (con != null) {
				System.out.println("Connected to database");
			}
			PreparedStatement stmt = con.prepareStatement(VALIDATE_USER);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				responseObj.put("login", "success");
				responseObj.put("token", "123");
			} else
				responseObj.put("login", "failed");
			responseObj.put("status", "success");
		} catch (Exception e) {
			System.out.println(e);
			responseObj.put("status", "failure");
		}
		response.getWriter().print(responseObj);
	}

}
