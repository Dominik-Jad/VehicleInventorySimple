//Dominik Jadczak 17081079
package controller;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;

import models.Vehicle;

public class Controller {

	public static void main(String[] args) throws Exception {
		//Creating the server on port 8045
				Server server = new Server(8045); //Creating the server on port 8080
				
				WebAppContext ctx = new WebAppContext(); // Creating the WebAppContext for the created content				
				ctx.setResourceBase("webapp"); // resource base is pointing where the web content (html. jsp, css ect) is stored
				ctx.setContextPath("/VehicleInventorySystem"); // base url			
				ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");// setting some attributes (setting up server to use JSTL)				
				ClassList classlist = ClassList.setServerDefault(server); // more set up (loading classes )
				classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");				
				ctx.addServlet("servlets.ServletLogin", "/login"); // add servlet handlers mapped to URLs
				ctx.addServlet("servlets.ServletHome", "/index");
				ctx.addServlet("servlets.ServletAddNew", "/addNew");
				ctx.addServlet("servlets.ServletDelete", "/delete");
				ctx.addServlet("servlets.ServletUpdate", "/update");	
				ctx.addServlet("servlets.ServletAdminHome", "/admin");	
				server.setHandler(ctx);// Setting the handler and starting the Server
				server.start();
				
		Scanner input = new Scanner(System.in); //Create scanner for the user
		VehicleDAO dao = new VehicleDAO(); // initialise Vehicle DAO
		ArrayList<Vehicle> allVehicles = null; 
		boolean terminate = false; // initialise terminate which quits the menu when is true

		while (terminate == false) {
			System.out.print("-------------------------\n"  // print out the menu
							+ "Vehicle Inventory System Choose from these options\n"
							+ "-------------------------\n" 
							+ "1 - Retrieve all vehicles\n" 
							+ "2 - Search for vehicle\n"
							+ "3 - Insert new vehicle into database\n" 
							+ "4 - Update existing vehicle details\n"
							+ "5 - Delete vehicle from database\n" 
							+ "6 - Exit\n" + "Enter choice > ");

			int i = input.nextInt(); // take in user option 

			switch (i) { // switch used for options 
			case 1: // option 1 get all vehicles
				allVehicles = dao.getAllVehicles();
				for (Vehicle d : allVehicles) {
					System.out.println("---------------------------");
					System.out.println(d.toString());
				}
				break;
			case 2:// option 2 select one vehicle
				System.out.print("Search Vehicle By ID: ");
				int vehicle_id = input.nextInt();
				System.out.println("---------------------------");
				System.out.println(dao.getVehicle(vehicle_id).toString());
				break;
			case 3:// option 3 insert vehicle 
				System.out.print("Insert New Vehicle into database: \n"); // set all variables for the new vehicle
				System.out.print("Insert New Vehicle ID: ");
				vehicle_id = input.nextInt();
				System.out.print("Insert New Vehicle Make: ");
				String make = input.next();
				System.out.print("Insert New Vehicle Model: ");
				String model = input.next();
				System.out.print("Insert New Vehicle Year: ");
				int year = input.nextInt();
				System.out.print("Insert New Vehicle Price: ");
				int price = input.nextInt();
				System.out.print("Insert New Vehicle License Number (No Spaces): ");
				String license_number = input.next();
				System.out.print("Insert New Vehicle Colour: ");
				String colour = input.next();
				System.out.print("Insert New Vehicle Number Of Doors: ");
				int number_doors = input.nextInt();
				System.out.print("Insert New Vehicle Transmission: ");
				String transmission = input.next();
				System.out.print("Insert New Vehicle Mileage: ");
				int mileage = input.nextInt();
				System.out.print("Insert New Vehicle Fuel Type: "); 
				String fuel_type = input.next();
				System.out.print("Insert New Vehicle Engine Size: ");
				int engine_size = input.nextInt();
				System.out.print("Insert New Vehicle Body Style: ");
				String body_style = input.next();
				System.out.print("Insert New Vehicle Condition: ");
				String condition = input.next();
				String notes = input.nextLine();
				System.out.print("Insert New Vehicle Notes: \n");		
				notes = input.nextLine();
				
				// create new vehicle using user inputs 
				Vehicle V = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors,
						transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
				//use the insertVehicle function and output false or true if successful.
				System.out.println("Inserted Succesfully : " + dao.insertVehicle(V));
				break;
			case 4:// option 4 udpate vehicle
				System.out.print("Insert Vehicle ID you want to update : \n"); // select vehicle to update
				vehicle_id = input.nextInt();
				System.out.println(dao.getVehicle(vehicle_id).toString());// display selected vehicle 
				System.out.print("Update Vehicle Make: ");
				make = input.next();
				System.out.print("Update Vehicle Model: ");
				model = input.next();
				System.out.print("Update Vehicle Year: ");
				year = input.nextInt();
				System.out.print("Update Vehicle Price: ");
				price = input.nextInt();
				System.out.print("Update Vehicle License Number (No Spaces): ");
				license_number = input.next();
				System.out.print("Update Vehicle Colour: ");
				colour = input.next();
				System.out.print("Update Vehicle Number Of Doors: ");
				number_doors = input.nextInt();
				System.out.print("Update Vehicle Transmission: ");
				transmission = input.next();
				System.out.print("Update Vehicle Mileage: ");
				mileage = input.nextInt();
				System.out.print("Update Vehicle Fuel Type: ");
				fuel_type = input.next();
				System.out.print("Update Vehicle Engine Size: ");
				engine_size = input.nextInt();
				System.out.print("Update Vehicle Body Style: ");
				body_style = input.next();
				System.out.print("Update Vehicle Condition: ");
				condition = input.next();
				notes = input.nextLine();
				System.out.print("Update Vehicle Notes: \n");		
				notes = input.nextLine();
				
				V = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, // using what the use input create a new vehicle
						transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
				
				System.out.println("Updated Successfully : " + dao.updateVehicle(V, vehicle_id)); //update vehicle using created vehicle and vehicle id
				System.out.println(dao.getVehicle(vehicle_id).toString());
				break;
			case 5:// option 5 delete vehicle 
				System.out.print("Insert Vehicle ID you want to delete : \n");
				vehicle_id = input.nextInt();
				System.out.println("---------------------------");
				System.out.println("Deleted Successfully : " + dao.deleteVehicle(vehicle_id)); // delete vehicle using user input 
				break;
			case 6:// option 6 exit 
				System.out.print("Exiting.... ");
				terminate = true; // set terminate to true which closes menu and ends the program
				break;
			default:// defualt option displays a message when user inputs something else
				System.out.println("Invalid Option Try Again ");
				break;
				
				
			}
		} 
	
	}

}
