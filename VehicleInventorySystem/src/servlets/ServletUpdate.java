//Dominik Jadczak 17081079
package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vehicle;
import controller.VehicleDAO;


public class ServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Using the doGet to serve the page with a form on a GET request
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if the admin is logged in, display the add new page , else display the no login page 
		// logged in changes to true when the user password and database password are the same in the login page
		if (ServletLogin.loggedIn == true) {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/update.jsp");
			view.forward(req, resp);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/noLogin.jsp");
			view.forward(req, resp);
		}
	}

	// Using the doPost to handle what happens when the form is POST'ed
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// similar to insert, uses vehicle id set by user, then gets all the other variables from user input on the web.
		VehicleDAO dao = new VehicleDAO();
		int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
		String make = (String) req.getParameter("make");
		String model = (String) req.getParameter("model");
		int year = Integer.parseInt(req.getParameter("year"));
		int price = Integer.parseInt(req.getParameter("price"));
		String license_number = (String) req.getParameter("license_number");
		String colour = (String) req.getParameter("colour");
		int number_doors = Integer.parseInt(req.getParameter("number_doors"));
		String transmission = (String) req.getParameter("transmission");
		int mileage = Integer.parseInt(req.getParameter("mileage"));
		String fuel_type = (String) req.getParameter("fuel_type");
		int engine_size = Integer.parseInt(req.getParameter("engine_size"));
		String body_style = (String) req.getParameter("body_style");
		String condition = (String) req.getParameter("condition");
		String notes = (String) req.getParameter("notes");
		
		// creates vehicle
		Vehicle V = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
				transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
		// if done is true redirect to index to show that the car has been updated, else print error.
		// since delete returns true when completed successfully then done should
		// true and no errors should occur.
		try {
			boolean done = dao.updateVehicle(V, vehicle_id);
			System.out.println(done);
			if (done) {
				resp.sendRedirect("admin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
