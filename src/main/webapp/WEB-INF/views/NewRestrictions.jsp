<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<!--This Jsp allows for the item and pokemon lists to be edited before being commited   -->
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
<link rel="stylesheet" href="assets/pokemonStyleSheet.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Edit Allowed Pokemon</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
.removeBtn {
	background-color: #93bdf0; /* Blue background */
	border: 1px solid black; /* black border */
	color: black; /* Black text */
	padding: 9px 20px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 100%; /* Set a width if needed */
	display: block; /* Make the buttons appear below each other */
}

.returnBtn {
	background-color: #fccaf6; /* Pink background */
	border: 1px solid black; /* black border */
	color: black; /* Black text */
	padding: 9px 20px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 100%; /* Set a width if needed */
	display: block; /* Make the buttons appear below each other */
}

.subBtn {
	background-color: #93bdf0; /* Blue background */
	border: 1px solid black; /* black border */
	color: black; /* Black text */
	padding: 9px 60px; /* Some padding */
	cursor: pointer; /* Pointer/hand icon */
	width: 100%; /* Set a width if needed */
	display: block; /* Make the buttons appear below each other */
}

.btn-group button:not(:last-child) {
	border-bottom: none; /* Prevent double borders */
}

/* Add a background color on hover */
.btn-group-vertical button:hover {
	background-color: #a64852;
}

.input {
	bottom: 5px;
	left: 50%;
	width: 50px;
	margin-left: -25px;
}

.chosen-mon {
	width: 100%;
	height: 100px;
}

.select-mon {
	width: 100%;
}
</style>
<script>
	$(document).ready(
			function() {
				$("#remove").click(
						function() {
							return !$(".select-mon option:selected").remove()
									.appendTo(".chosen-mon");
						});

				$("#return").click(
						function() {
							$(".chosen-mon option:selected").remove().appendTo(
									".select-mon");
						});
				 $('select').selectize({
			            sortField: 'text'
			        });
			});
	  // to clear the selected value.
	   $('form').find('.selectized').each(function(index, element) { element.selectize && element.selectize.clear() })
</script>
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
		<div class="container">

			<div class="row" style="text-align: center">
				<div class="col">
					<select class="select-mon" name="allowedPokemon"
						multiple="multiple" size="20" style="float: left">
						<c:forEach var="pkm" items="${pokemon }">
							<option value="${pkm}">${pkm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
					<div class="btn-group-vertical">
						<button class="removeBtn" id="remove">Remove From List</button>
						<button class="returnBtn" id="return">Return To List</button>
						<form action="NewRestrictions" method="post">
							<br> <input class="subBtn" type="submit" value="Submit">
					</div>
				</div>
				<div class="col">

					<select class="chosen-mon" id="removePokemon" name="removePokemon"
						multiple="multiple" size="20" style="min-height: 460px;"></select>

					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>