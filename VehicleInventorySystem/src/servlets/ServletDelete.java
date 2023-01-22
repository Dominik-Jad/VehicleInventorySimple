//Dominik Jadczak 17081079
package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VehicleDAO;

public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Using the doGet to serve the page with a form on a GET request
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if the admin is logged in, display the add new page , else display the no
		// login page
		// logged in changes to true when the user password and database password are
		// the same in the login page
		if (ServletLogin.loggedIn == true) {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/delete.jsp");
			view.forward(req, resp);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/noLogin.jsp");
			view.forward(req, resp);
		}
	}

	// Using the doPost to handle what happens when the form is POST'ed
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VehicleDAO dao = new VehicleDAO();
		// get vehicle int from the form field
		int vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));

		try {
			// if done is true redirect to index to show that the car has been deleted, else print error.
			// since delete returns true when completed successfully then done should
			// always be true when deleting a car from the db successfully.
			boolean done = dao.deleteVehicle(vehicle_id);
			System.out.println(done);
			if (done) {
				resp.sendRedirect("admin"); // redirect to localhost:8055/VehicleInventorySystem/admin
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
