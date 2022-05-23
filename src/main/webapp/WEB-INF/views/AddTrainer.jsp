<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="assets/pokemonStyleSheet.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Add Trainer</title>
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
		<h2>Trainer Form</h2>
		<form action="AddTrainer" Method="post">
			<label for="tname">Trainer name:</label><br> <input type="text"
				id="tname" name="tname" value=""><br> <label
				for="tnature">Nature:</label><br> <input type="text"
				id="tnature" name="tnature" value=""><br> <br> <input
				type="submit" value="Submit">
		</form>
		<label id="errorMsg">${errorMsg}</label>
	</div>
</body>
</html>
