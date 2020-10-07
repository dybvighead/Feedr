<%@page import="com.benjamin.feedr.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="resources/style.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md bg-white flex-column flex-sm-row">
		<a class="navbar-brand" href="welcome"><img src="./resources/images/feedr.png" width="93" height="32" alt=""></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="getFirstCuisine">Start</a></li>
			<li class="nav-item"><a class="nav-link" href="userSettings">Settings</a></li>
			<li class="nav-item"><a class="nav-link" href="quitApp">Quit Feedr</a></li>
		</ul>
	</nav>
	
	<div>
		<br>
		<img src="./resources/images/feedr.png" alt="" width=140 height=50><p>
	</div>
	
		<%User user = (User) session.getAttribute("current_user");%>
	<div class="white">
		<h3>Welcome <%=user.getFirstName()%>!</h3>
	</div>
	
	<div>
		<p><form action="getFirstCuisine"><input type="submit" value="Start Choosing!"></form><p>
	</div>
	
	<div>
		<a href="userSettings">Settings</a>
	</div>
</body>
</html>