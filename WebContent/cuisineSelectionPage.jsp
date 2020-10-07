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

	<div>
		<%Cuisine cuisine = (Cuisine) session.getAttribute("cuisine");%>
		<img src="<%=cuisine.getPhoto()%>" width="300" height="300" alt=""><p>
		<h2><%=cuisine.getName()%> Cuisine</h2>
	</div>
		<%session.setAttribute("cuisine", cuisine);%>

	<div class="row">
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col" style="display: flex">
			<form action="getNextCuisine">
				<input type="image" src="./resources/images/cross.png" width=90 height=90 alt="No" class="select-buttons"/>
			</form>
			<form action="selectedCuisine">
				<input type="image" src="./resources/images/heart-blue.png" width=82 height=82 alt="Yes" class="select-buttons"/>
			</form>
		</div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
	</div>


</body>
</html>

