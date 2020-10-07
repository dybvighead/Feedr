<%@page import="com.benjamin.feedr.models.Cuisine"%>
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
	img {border-radius: 50px;}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-white flex-column flex-sm-row">
		<a class="navbar-brand" href="welcome"><img src="./resources/images/feedr.png" width="93" height="32" alt=""></a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="welcome">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="quitApp">Quit Feedr</a></li>
		</ul>
	</nav>
	
	<div>
		<br>
		<img src="./resources/images/feedr.png" alt="" width=140 height=50><p>
	</div>

	<div class="white">
		<%Cuisine cuisine = (Cuisine) session.getAttribute("cuisine");%>
		<img src="<%=cuisine.getPhoto()%>" width="300" height="300"><p>
	</div> 
	
	<div class="white">
		<h2 style="colour: white;">You selected ${cuisine.name} cuisine!</h2><p>
	</div>
	
	<div>
		<form action="getRestaurants">
			<input type="submit" value="Find Restaurants">
		</form>
	</div>

</body>
</html>