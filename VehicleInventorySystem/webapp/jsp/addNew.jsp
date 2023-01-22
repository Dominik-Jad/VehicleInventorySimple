<!-- Dominik Jadczak 17081079 -->
<!DOCTYPE html>
<html>
<head>
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> >
<meta charset="UTF-8">
<title>Add New Vehicle</title>
<link rel="stylesheet" href="css/site.css">
</head>
<body>
	<!-- create a form for the user to add a new vehicle into the database -->
	<form  method="POST" action="./addNew">
		<h1>Insert Vehicle</h1>
			<p>Vehicle Id: <input type="text" name="vehicle_id" placeholder="Vehicle ID"></p>
			<p> Make: <input type="text" name="make" placeholder="Make"><br></p>
			<p> Model: <input type="text" name="model" placeholder="Model"></p>
			<p>Year: <input type="text" name="year"  placeholder="Year"></p>
			<p>Price: <input type="text" name="price" placeholder="Price"></p>
			<p>License number: <input type="text" name="license_number" placeholder="License Number"></p>
			<p>Colour: <input type="text" name="colour" placeholder="Colour"></p>
			<p>Number doors: <input type="text" name="number_doors" placeholder="Number of Doors"></p>
			<p>Transmission: <input type="text" name="transmission" placeholder="Transmission"></p>
			<p>Mileage: <input type="text" name="mileage"  placeholder="Mileage"></p>
			<p>Fuel type: <input type="text" name="fuel_type" placeholder="Fuel type"></p>
			<p>Engine size: <input type="text" name="engine_size" placeholder="Engine Size"></p>
			<p>Body Style: <input type="text" name="body_style" placeholder="Body Style"></p>
			<p>Condition: <input type="text" name="condition" placeholder="Condition"></p>
			<p>Notes: <input type="text" name="notes" placeholder="Notes"></p>
		<input type="submit" value="Add Vehicle">
	</form>
	<br>
	<!-- button to go back to index -->
	<a class="btn" href="./admin"> Back</a> 
</body>
</html>

