<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="assets/pokemonStyleSheet.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.bootstrap3.min.css"
	integrity="sha256-ze/OEYGcFbPRmvCnrSeKbRTtjG4vGLHXgOqsyLFTRjg="
	crossorigin="anonymous" />
<!-- Be sure to put the links in the right position to avoid dependency issue.-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/js/standalone/selectize.min.js"
	integrity="sha256-+C0A5Ilqmu4QcSPxrlGpaZxJ04VjsRjKu+G82kl5UJk="
	crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {
        $('select').selectize({
            sortField: 'text'
        });
    });
  // to clear the selected value.
  $('form').find('.selectized').each(function(index, element) { element.selectize && element.selectize.clear() })
 </script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		<div>
			<label>Trainer Name: ${trainerName}</label> <label>Trainer
				ID: ${trainerID}</label> <label>Nature: ${trainerNature}</label>
		</div>
		<form action="CreateTeam" method="post">
			<c:forEach var="i" begin="1" end="6">
				<div class="row">
					<div class="col">
						<label for="pokemon">Pokemon ${i}</label> <select id="pokemon"
							size="10" name="pokemon">
							<option value="None">None</option>
							<c:forEach var="pkm" items="${pokemon}">
								<option value="${pkm.pokemonName}">${pkm.pokemonName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col">
						<label for="item">Item ${i}</label> <select id="item" name="item">
							<option value="None">None</option>
							<c:forEach var="itm" items="${items}">
								<option value="${itm.itemName}">${itm.itemName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</c:forEach>
			<input type="submit" value="Submit">
		</form>
		<label id="errorMsg">${errorMsg}</label>
	</div>
</body>
</html>