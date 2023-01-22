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

public class ServletAddNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

// Using the doGet to serve the page with a form on a GET request
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if the admin is logged in, display the add new page , else display the no login page 
		// logged in changes to true when the user password and database password are the same in the login page
		if (ServletLogin.loggedIn == true) {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/addNew.jsp");
			view.forward(req, resp); // display addNew
		} else {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/noLogin.jsp");
			view.forward(req, resp); // display noLogin 
		}
	}

// Using the doPost to handle what happens when the form is POST'ed
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		VehicleDAO dao = new VehicleDAO();
		// gets the variables for the car from the form fields on the website.
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
		
		//create a new car using those forms 
		Vehicle V = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
				transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
		try {
			// if done is true redirect to index to show all cars else print error.
			// since insertVehicle returns true when completed successfully then done should
			// always be true when inserting into the db successfully.
			boolean done = dao.insertVehicle(V);
			System.out.println(done);
			if (done) {
				resp.sendRedirect("admin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}