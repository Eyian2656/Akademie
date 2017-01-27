<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Welcome</title>

<jsp:useBean id="blog" class="com.csc.azubiblog.model.Blog" scope="page" />

<!-- Bootstrap core CSS -->
<link href="../bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Bootstrap theme</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>

	<div class="container theme-showcase" role="main">

		<div class="alert alert-success" role="alert">
			<strong>Wellcome!</strong> You successfully logged on to this awesome
			Blog!
		</div>

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>Theme example</h1>
			<p>This is a template showcasing the optional theme stylesheet
				included in Bootstrap. Use it as a starting point to create
				something more unique by building on or modifying it.</p>
		</div>


		<%
		blog.getContent();
		pageContext.setAttribute("name", blog.getCategory(), pageContext.SESSION_SCOPE);

		String result = request.getParameter("address");

		// Errorpage aktivieren mit einer Ausnahme (s.o.)
		// int x = 4 / 0;

		if (result != null && result.length() > 0) {
			out.println(result);
		}
		// Aufruf einer anderen JSP mit Java
		if (result.equals(""))
			response.sendRedirect("Weiter.jsp");
		String button = request.getParameter("senden1");
		System.out.println(button);
		button = request.getParameter("senden2");
		System.out.println(button);
		
	%>

	</div>
</body>
</html>