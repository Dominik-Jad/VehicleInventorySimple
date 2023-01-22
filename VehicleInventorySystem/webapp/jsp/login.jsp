<!-- Dominik Jadczak 17081079 -->
<!DOCTYPE html>
<html>
<head>
<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<meta charset="UTF-8">
<title>Log in</title>
<link rel="stylesheet" href="css/site.css">
</head>
<body>
	<h1>Log in</h1>
	<!-- form that allows admin to login -->
	<form method="POST" action="./login">
		Enter Name : <input type="text" name="login" />
		Enter Password : <input type="password" name="password" />
		<input type="submit" value="login" />
	</form> 
	<br>
	<a class="btn" href="./index"> Back</a> <!-- button to go back -->
</body>
</html>

