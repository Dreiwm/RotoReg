<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stats</title>
</head>
<body>
	<div class="header">
		<img class="logo" src="assets/oplLogo.png"
			alt="otafest-pokemon-league-logo">
	</div>
	<div class="topnav">
		<a href="AddTrainer">New Trainer</a> <a href="SearchTrainers">Look
			Up</a> <a href="Home">Home</a>
	</div>
	<div class="content">
		<p>Number of People Registered: ${teamNum}</p>
		<p>Number of New Participates: ${newTrainers}</p>
		<p>Number of Returning Participates: ${returingTrainers}</p>
		<p>Most Used Pokemon: ${mostUsedPokemon}</p>
		<p>Most Used Item: ${mostUsedItem}</p>
	</div>
</body>
</html>