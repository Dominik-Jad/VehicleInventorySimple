<!-- Dominik Jadczak 17081079 -->
<!DOCTYPE html>
<!-- import vehicle and arraylist so that vehicles can be displayed in a table  -->
<%@page import="models.Vehicle"%> 
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>> <!--use jstl tag core library, used to display vehicle-->
<meta charset="UTF-8">
<title>Admin Index</title>
<link rel="stylesheet" href="css/site.css"> <!-- link to style sheet -->
</head>
<body>
	<h1>All Vehicles</h1>
	<table> <!-- table that displays all vehicles -->
		<tr>
			<th>Vehicle ID</th>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>Price</th>
			<th>License Number</th>
			<th>Colour</th>
			<th>Number of Doors</th>
			<th>Transmission</th>
			<th>Mileage</th>
			<th>Fuel Type</th>
			<th>Engine Size</th>
			<th>Body Style</th>
			<th>Condition</th>
			<th>Notes</th>
		</tr>
		<c:forEach items="${Vehicles}" var="v"> <!-- for each vehicle in the database make a new row in the table and display the vehicle -->
			<tr>
				<td>${v.getVehicle_id()}</td>
				<td>${v.getMake()}</td>
				<td>${v.getModel()}</td>
				<td>${v.getYear()}</td>
				<td>${v.getPrice()}</td>
				<td>${v.getLicense_number()}</td>
				<td>${v.getColour()}</td>
				<td>${v.getNumber_doors()}</td>
				<td>${v.getTransmission()}</td>
				<td>${v.getMileage()}</td>
				<td>${v.getFuel_type()}</td>
				<td>${v.getEngine_size()}</td>
				<td>${v.getBody_style()}</td>
				<td>${v.getCondition()}</td>
				<td>${v.getNotes()}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a class="btn" href="./addNew">+ New Vehicle</a> <!-- buttons for admin function -->
	<a class="btn" href="./delete">- Delete</a>
	<a class="btn" href="./update"> ~ Update</a>
	<a class="btn" href="./index"> Logout</a>
</body>
</html>
