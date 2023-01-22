//Dominik Jadczak 17081079
package servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VehicleDAO;

public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean loggedIn = false; // use this variable to determine if the user is logged in

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/jsp/login.jsp"); // display login.jsp
		view.forward(req, resp);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		VehicleDAO dao = new VehicleDAO();
		loggedIn = false;
		String password = request.getParameter("password"); // get the password and login from the form on webpage
		String username = request.getParameter("login");
		
		MessageDigest mDigest;
		
		try {
			// Create a MD5 hash for user inputted password 
			mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(password.getBytes());
			byte byteUser[] = mDigest.digest();
			StringBuffer userPassword = new StringBuffer();
			for (int i = 0; i < byteUser.length; i++)
				userPassword.append(Integer.toString((byteUser[i] & 0xff) + 0x100, 16).substring(1));
			
			// Create a MD5 hash for password stored in database
			mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(dao.getPassword().getBytes());
			byte byteDatabase[] = mDigest.digest();
			StringBuffer databasePassword = new StringBuffer();
			for (int i = 0; i < byteDatabase.length; i++)
				databasePassword.append(Integer.toString((byteDatabase[i] & 0xff) + 0x100, 16).substring(1));
			
			
			//compare passowrds if true = logged in, else retry
			if (username.equals("admin") && userPassword.toString().equals(databasePassword.toString())) {
				response.sendRedirect(request.getContextPath() + "/admin");
				loggedIn = true;
			} else {
				response.sendRedirect(request.getContextPath() + "/login");
			}

		} catch (NoSuchAlgorithmException | SQLException e) { // catch either md5 errors or sql errors
			e.printStackTrace();
		}
		doGet(request, response);
	}
}
