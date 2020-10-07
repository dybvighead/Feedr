<%@page import="com.benjamin.feedr.models.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Feedr</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="resources/style.css" rel="stylesheet">
<style>
	img {border-radius: 5px;}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-white flex-column flex-sm-row">
		<a class="navbar-brand" href="welcome"><img src="./resources/images/feedr.png" width="93" height="32" alt=""></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="welcome">Try Again</a></li>
			<li class="nav-item"><a class="nav-link" href="quitApp">Quit Feedr</a></li>
		</ul>
	</nav>
	
	<div>
		<br>
		<img src="./resources/images/feedr.png" alt="" width=140 height=50><p>
	</div>

	<div class="container">
		<div class="row">
			<%
				ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants");
			for (int i = restaurants.size() - 1; i > restaurants.size() - 7 && i >= 0; i--) {
			%>
			<div class="col-sm">
				<div>
					<img src="<%=restaurants.get(i).getPhoto()%>" alt="<%=restaurants.get(i).getName()%> image"><p>
				</div>	
				<div class="white">
					<h5><%=restaurants.get(i).getName()%></h5>
					<h5><%=restaurants.get(i).getAddress()%></h5><p>
					<h5>Rating: <%=restaurants.get(i).getRating()%></h5><p>
					<h5>Price Level: <%=restaurants.get(i).getPriceLevel()%></h5><p>
				</div>
			</div>
		</div>
		<hr>
		<%
			}
		%>
	</div>
	
	<div>
		<form action="welcome"><input type="submit" value="Try Again"></form>
		<br>
	</div>
</body>
</html>