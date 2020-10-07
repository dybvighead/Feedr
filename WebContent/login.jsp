<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="resources/style.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-expand-md bg-white flex-column flex-sm-row">
		<a class="navbar-brand" href="index.jsp"><img src="./resources/images/feedr.png" width="93" height="32" alt=""></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="register">Sign up</a></li>
		</ul>
	</nav>

	<div>
		<br>
		<img src="./resources/images/feedr.png" alt="" width=140 height=50><p>
	</div>
	
	<div class="white">
		A smarter food app for smarter people<p>
	</div>

	<div>
		<form action="verifyLogin" method="post"><p>
				<input type="text" placeholder="Username" name="username" value="${user.username}" required><p>
				<input type="password" placeholder="Password" name="password" required><p>
				<input type="submit" value="Log In">
		</form>
	</div>

	<div>
		<a href="register">Sign Up</a>
	</div>
</body>
</html>