//Dominik Jadczak 17081079
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vehicle;
import controller.VehicleDAO;

public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// create an arrraylist of Vehicles
			while(ServletLogin.loggedIn == true) {
				ServletLogin.loggedIn = false; // log the user out 
			}
			VehicleDAO dao = new VehicleDAO();
			ArrayList<Vehicle> Vehicles;
			//Get all vehicles and set the name to Vehicles and forward it to the index jsp
			//Where all vehicles are displayed in a table
			//Catch an exeption and display it
			try {
				Vehicles = dao.getAllVehicles();
				RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
				req.setAttribute("Vehicles", Vehicles);
				view.forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
