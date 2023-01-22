//Dominik Jadczak 17081079
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Vehicle;

public class VehicleDAO {

	 /* Makes and returns a connection to the vehicle database. 
	    @return Instance of the Connection class for vehicle database
	 */
	private static Connection getDBConnection() {
		Connection conn = null;

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			String url = "jdbc:sqlite:vehicles.sqlite";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	 /*Gets all the vehicle from the vehicles table.
	 * 
	 * @return vehicle ArrayList called vehicles List
	 * @throws SQLException Operations not completed due to an SQL Exception error
	 */
	public ArrayList<Vehicle> getAllVehicles() throws SQLException {
		System.out.println("Retrieving all vehicles ...");
		Connection dbConnection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String query = "SELECT * FROM vehicles;";
		ArrayList<Vehicle> vehicleList = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			System.out.println("DBQuery = " + query);
			ps = dbConnection.prepareStatement(query);

			result = ps.executeQuery();// Execute SQL query and record response to string
			while (result.next()) {

				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("Notes");

				vehicleList.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
						transmission, mileage, fuel_type, engine_size, body_style, condition, notes));

			}

		} catch (SQLException e) {
			throw new SQLException("Couldnt Get Vehicles");
		} finally {
			if (result != null) {
				result.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return vehicleList;
	}
	
	 /*Gets the vehicle from the vehicles table with the user id selected by user
	 * 
	 * @return Vehicle called temp, using variables form the database
	 * @throws SQLException Operations not completed due to an SQL Exception error
	 */
	public Vehicle getVehicle(int vehicleId) throws SQLException {
		System.out.println("Retrieving vehicle with the id of: " + vehicleId + " ...");
		Connection dbConnection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
		Vehicle temp = null;

		try {
			dbConnection = getDBConnection();
			System.out.println("DBQuery = " + query);
			ps = dbConnection.prepareStatement(query);

			ps.setInt(1, vehicleId);

			result = ps.executeQuery();
			while (result.next()) {

				int vehicle_id = result.getInt("vehicle_id");
				String make = result.getString("make");
				String model = result.getString("model");
				int year = result.getInt("year");
				int price = result.getInt("price");
				String license_number = result.getString("license_number");
				String colour = result.getString("colour");
				int number_doors = result.getInt("number_doors");
				String transmission = result.getString("transmission");
				int mileage = result.getInt("mileage");
				String fuel_type = result.getString("fuel_type");
				int engine_size = result.getInt("engine_size");
				String body_style = result.getString("body_style");
				String condition = result.getString("condition");
				String notes = result.getString("Notes");

				temp = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
						transmission, mileage, fuel_type, engine_size, body_style, condition, notes);

			}
		} catch (SQLException e) {
			throw new SQLException("Couldnt Get Vehicle");
		} finally {
			if (result != null) {
				result.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}
	/* Inserts a vehicle into the dabase using a vehicle the user has created
	 * There is no return as this is a update statement and nothing is returned
	 * @throws SQLException Operations not completed due to an SQL Exception error
	*/
	public Boolean insertVehicle(Vehicle V) throws SQLException {
		System.out.println("Inserting Vehicle Into Database...");
		Connection dbConnection = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO vehicles"
				+ "(vehicle_id,make,model,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size, body_style,condition,Notes) VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			dbConnection = getDBConnection();
			System.out.println("DBQuery = " + query);
			ps = dbConnection.prepareStatement(query);

			ps.setInt(1, V.getVehicle_id());
			ps.setString(2, V.getMake());
			ps.setString(3, V.getModel());
			ps.setInt(4, V.getYear());
			ps.setInt(5, V.getPrice());
			ps.setString(6, V.getLicense_number());
			ps.setString(7, V.getColour());
			ps.setInt(8, V.getNumber_doors());
			ps.setString(9, V.getTransmission());
			ps.setInt(10, V.getMileage());
			ps.setString(11, V.getFuel_type());
			ps.setInt(12, V.getEngine_size());
			ps.setString(13, V.getBody_style());
			ps.setString(14, V.getCondition());
			ps.setString(15, V.getNotes());
			// execute insert SQL preparedStatement
			ps.executeUpdate();

			System.out.println("Vehicle Inserted into table");
		} catch (SQLException e) {
			throw new SQLException("Vehicle Not Added");
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}
	 /*Delete Vehicle from the datatabase using user input as the id
	  * Doesnt return anything as its a update statement
	  * @throws SQLException Operations not completed due to an SQL Exception error
	  */
	public Boolean deleteVehicle(int vehicleId) throws SQLException {
		System.out.println("Deleting Vehicle From Database...");
		Connection dbConnection = null;
		PreparedStatement ps = null;
		String query = "DELETE FROM vehicles WHERE vehicle_id = ?";

		try {
			dbConnection = getDBConnection();
			System.out.println("DBQuery = " + query);
			ps = dbConnection.prepareStatement(query); // Execute SQL query and record response to string

			ps.setInt(1, vehicleId);

			ps.executeUpdate();

			System.out.println("Vehicle Deleted");

		} catch (SQLException e) {
			throw new SQLException("Vehicle Not Deleted");
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}
	 /*Updates a vehicle with the id set by user, uses a vehicle made by user to update the database
	  * no return as its a update statement
      * @throws SQLException Operations not completed due to an SQL Exception error
     */
	public Boolean updateVehicle(Vehicle V, int vehicleId) throws SQLException {
		System.out.println("Updating Vehicle with " + vehicleId + " ID ...");
		Connection dbConnection = null;
		PreparedStatement ps = null;
		String query = "UPDATE vehicles Set vehicle_id = ?,make = ?,model = ?,year = ?,price = ?,license_number = ?,colour = ?,number_doors = ?,transmission = ?,mileage = ?,fuel_type = ?,engine_size = ?, body_style = ?,condition = ?,Notes = ? where vehicle_id = ?";
		try {
			dbConnection = getDBConnection();
			System.out.println("DBQuery = " + query);
			ps = dbConnection.prepareStatement(query);

			ps.setInt(1, V.getVehicle_id());
			ps.setString(2, V.getMake());
			ps.setString(3, V.getModel());
			ps.setInt(4, V.getYear());
			ps.setInt(5, V.getPrice());
			ps.setString(6, V.getLicense_number());
			ps.setString(7, V.getColour());
			ps.setInt(8, V.getNumber_doors());
			ps.setString(9, V.getTransmission());
			ps.setInt(10, V.getMileage());
			ps.setString(11, V.getFuel_type());
			ps.setInt(12, V.getEngine_size());
			ps.setString(13, V.getBody_style());
			ps.setString(14, V.getCondition());
			ps.setString(15, V.getNotes());

			ps.setInt(16, V.getVehicle_id());
			// execute insert SQL stetement
			ps.executeUpdate();

			System.out.println("Vehicle Updated");

		} catch (SQLException e) {
			throw new SQLException("Vehicle Not Updated");
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}
	/*Gets the password stored in the database so that it can be hashed and compared to the 
	 * user password to allow for admin actions on the web app.
	 */
	public String getPassword() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		String query = "SELECT password FROM users WHERE id = ?";
		String temp = null;

		try {
			dbConnection = getDBConnection();
			System.out.println("DBQuery = " + query);
			ps = dbConnection.prepareStatement(query);

			ps.setInt(1, 24);

			result = ps.executeQuery();
			while (result.next()) {
				temp = result.getString("password");
			}
		} catch (SQLException e) {
			throw new SQLException("Couldnt Get Vehicle");
		} finally {
			if (result != null) {
				result.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}
}
