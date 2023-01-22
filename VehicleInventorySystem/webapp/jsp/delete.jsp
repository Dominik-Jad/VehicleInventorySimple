<!-- Dominik Jadczak 17081079 -->
<!DOCTYPE html>
<html>
<head>
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<meta charset="UTF-8">
<title>Delete Vehicle</title>
<link rel="stylesheet" href="css/site.css">
</head>
<body>
	<h1>Delete Vehicle</h1>
	<form method="POST" action="./delete">
		Vehicle Id: <input type="text" name="vehicle_id">  <!--Create a form for user to specify vehicle id -->
		<input type="submit"value="Delete Vehicle">
	</form>
	<br>
	<a class="btn" href="./admin"> Back</a> <!-- button to go back -->
</body>
</html>