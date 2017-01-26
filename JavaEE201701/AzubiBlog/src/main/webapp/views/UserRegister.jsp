<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DisplayAddress</title>
<link href="../bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../bootstrap-3.3.7-dist/css/signing.css" rel="stylesheet">

</head>
<body>

	<div class="container">

		<form class="form-signin" action="${pageContext.request.contextPath}/UserRegister" method="post">
			<h2 class="form-signin-heading">Register now!</h2>
			<label for="inputAlias" class="sr-only">Username</label> <input
				type="text" name="alias" id="inputAlias" class="form-control"
				placeholder="Username" required="" autofocus=""> 
				
				<label for="inputSurname" class="sr-only">Surname</label> <input
				type="text" name="surname" id="inputSurname" class="form-control"
				placeholder="Surname" required="" autofocus="">
				
				<label for="inputLastname" class="sr-only">Lastname</label> <input
				type="text" name="lastname" id="inputLastname" class="form-control"
				placeholder="Lastname" required="" autofocus="">
				
				<label for="inputBirthday" class="sr-only">Birthday</label> <input
				type="date" name="birthday" id="inputBirthday" class="form-control"
				placeholder="Birthday" required="" autofocus="">
				
				<label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="password" id="inputPassword"
				class="form-control" placeholder="Password" required="">
				
			<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
		</form>
	</div>
</body>

</html>