package org.training;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;
import org.utils.CommonUtils;

public class Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/training";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "root";
    private final String STUDENTS_GETALL = "SELECT * FROM training.students;";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray studentsList = new JSONArray();
		new CommonUtils().registerDriver();
		try(Connection con = DriverManager.getConnection(CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD)) {
			if(con != null) {
				System.out.println("Connected to database");
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(STUDENTS_GETALL);
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("name", rs.getString("name"));
				obj.put("email", rs.getString("email"));
				obj.put("mobile", rs.getString("mobile"));
				studentsList.put(obj);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		response.getWriter().println(studentsList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject inputData = new CommonUtils().readJSONInput(request.getReader());
		System.out.print(inputData);
	}

}