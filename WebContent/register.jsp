<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="resources/style.css" rel="stylesheet">
<script>
	// function to check if the fields "password" and "confirnPassword" match
	function checkPassword(form) {
		password = form.password.value;
		confirmPassword = form.confirmPassword.value;

		if (password != confirmPassword) {
			alert("Passwords do not match. Please try again.")
			return false;
		}
		return true;
	}
</script>
</head>

<body>
	<nav class="navbar navbar-expand-md bg-white flex-column flex-sm-row">
		<a class="navbar-brand" href="index.jsp"><img src="./resources/images/feedr.png" width="93" height="32" alt=""></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="login">Log in</a></li>
		</ul>
	</nav>
	
	<div>
		<br>
		<img src="./resources/images/feedr.png" alt="" width=140 height=50><p>
	</div>
	
	<div class="white">
		A smarter food app for smarter people
		<p>
	</div>
	<div>
		<form onsubmit="return checkPassword(this)" action="addUser" method="post">
			<p><input type="text" name="firstName" placeholder="First name" required>
			<p><input type="text" name="lastName" placeholder="Last name" required>
			<p><input type="text" name="email" placeholder="Email Address" required>
			<p><input type="text" name="username" placeholder="Username" required>
			<p><input type="password" name="password" placeholder="Password" id="password" required>
			<p><input type="password" name="confirmPassword" placeholder="Confirm Password" id="confirmPassword" required>
			<p><input type="submit" value="Create User">
		</form>
	</div>
		<div>
		<a href="login">Cancel</a>
	</div>

</body>
</html>