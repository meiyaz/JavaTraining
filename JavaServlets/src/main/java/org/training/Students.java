package org.training;

import jakarta.servlet.ServletException;
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

public class Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/training";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "root";
    private final String STUDENTS_GETALL = "SELECT * FROM training.students;";
    private final String STUDENTS_INSERT = "INSERT INTO `training`.`students` (`name`, `dateOfBirth`, `address`, `mobile`, `email`) VALUES (?, ?, ?, ?, ?);";

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject responseObj = new JSONObject();
		
		new CommonUtils().registerDriver();
		try(Connection con = DriverManager.getConnection(CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD)) {
			if(con != null) {
				System.out.println("Connected to database");
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(STUDENTS_GETALL);

			JSONArray studentsList = new JSONArray();
			JSONObject obj;
			while(rs.next()) {
				obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("name", rs.getString("name"));
				obj.put("email", rs.getString("email"));
				obj.put("mobile", rs.getString("mobile"));
				studentsList.put(obj);
			}
			responseObj.put("students", studentsList);
			responseObj.put("status", "success");
		} catch(Exception e) {
			System.out.println(e);
			responseObj.put("status", "failure");
		}
		
		response.getWriter().println(responseObj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject responseObject = new JSONObject();
		JSONObject inputData = new CommonUtils().readJSONInput(request.getReader());
		
		new CommonUtils().registerDriver();
		try(Connection conn = DriverManager.getConnection(CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD)) {
			if(conn != null) {
				System.out.print("db connection success");
			}
			
			PreparedStatement stmt = conn.prepareStatement(STUDENTS_INSERT);
			stmt.setString(1, inputData.getString("name"));
			stmt.setString(2, inputData.getString("dateOfBirth"));
			stmt.setString(3, inputData.getString("address"));
			stmt.setString(4, inputData.getString("mobile"));
			stmt.setString(5, inputData.getString("email"));
			
			int rowsInserted = stmt.executeUpdate();
			
			if(rowsInserted == 1)
				responseObject.put("status", "success");
			else
				responseObject.put("status", "failed");
		} catch(Exception e) {
			System.out.print(e);
			responseObject.put("status", "failed");
		}
		
		response.getWriter().print(responseObject);
		
	}

}