package org.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import java.security.Key;

import org.json.JSONObject;
import org.utils.CommonUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/training";
	private final String MYSQL_USERNAME = "root";
	private final String MYSQL_PASSWORD = "root";
	private String VALIDATE_USER = "SELECT * FROM training.users where username = ? and password = ?;";
	
	private final String SECRET_KEY = "SSBhbSB0cnlpbmcgdG8gd3JpdGUgSmF2YSBjb2RlICh0byBiZSBydW4gb24gdGhlIHNlcnZlciBzaWRlKSB0byBnZXQgdGhlIEpXVCB0b2tlbiBwcm9ncmFtYXRpY2FsbHkuIEkgaGF2ZSBhIEpXVCBBcHAgd2l0aCBBUEkgS2V5IGFuZCBzZWNyZXQgZnJvbSB0aGUgc2FtZS4gV2hlbiBydW5uaW5nIHRoZSBjb2RlLCBJIGdldCBhbiBleGNlcHRpb24="; // SECRETKEY123

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
				// static or random token
//				responseObj.put("token", "123");
				
				// jwt authentication
//				String email = rs.getString("email");
				responseObj.put("token", generateToken(username, "dummy@gmail.com"));			
			} else
				responseObj.put("login", "failed");
			responseObj.put("status", "success");
		} catch (Exception e) {
			System.out.println(e);
			responseObj.put("status", "failure");
		}
		response.getWriter().print(responseObj);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h2>Hi, myself Mei</h2>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
	
	
	private String generateToken(String loginUser, String email) throws Exception {
		String sessionId = UUID.randomUUID().toString();
		Date currentDate = new Date();
		Date expiryDate = new Date(System.currentTimeMillis()+(5*60*1000));
		Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
		JwtBuilder builder = Jwts.builder()
				.id(sessionId)
				.subject(loginUser)
				.claim("name", loginUser)
				.claim("email", email)
				.issuedAt(currentDate)
				.expiration(expiryDate)
//				.signWith(key);
				.signWith(key, SignatureAlgorithm.HS512); // define algorithm manually
		String token = builder.compact();
		return token;
	}
	
	public boolean isValidUser(String token) {
//		Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
		SecretKeySpec key1 = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), SignatureAlgorithm.HS512.getJcaName());
		JwtParser claims = Jwts.parser().verifyWith(key1).build();
		System.out.print(claims.parse(token).getPayload());
		return false;
	}
	
}
