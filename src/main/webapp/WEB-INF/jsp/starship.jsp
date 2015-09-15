<%@ include file="includes.jsp" %>
<html>
<head>
    <title>Star Ship Information</title>
</head>
<body>
	<div>
		<spring:url value="/" var="home" />
		<h1><a href="${home}">Starships Are Us</a></h1>  
	</div>

Star Ship Information
<br>
<div class="panel panel-primary">
	<table class="table">
		<c:forEach var="ship" items="${starships}">
			<tr>
				<td>
					Name
				</td>
				<td>
					${ship.name}
				</td>
			</tr>
			<tr>
				<td>
					Model
				</td>
				<td>
					${ship.model}
				</td>
			</tr>
			<tr>
				<td>
					Star ship class
				</td>
				<td>
					${ship.starshipClass}
				</td>
			</tr>
			<tr>
				<td>
					HyperDrive rating
				</td>
				<td>
					${ship.hyperdriveRating}
				</td>
			</tr>
			<tr>
				<td>
					MGLT
				</td>
				<td>
					${ship.mglt}
				</td>
			</tr>
			<tr>
				<td>
					Vehicle class
				</td>
				<td>
					${ship.vehicleClass}
				</td>
			</tr>
			<tr>
				<td>
					Manufacturer
				</td>
				<td>
					${ship.manufacturer}
				</td>
			</tr>
			<tr>
				<td>
					Price
				</td>
				<td>
					${ship.costInCredits}
				</td>
			</tr>
			<tr>
				<td>
					Length
				</td>
				<td>
					${ship.length}
				</td>
			</tr>
			<tr>
				<td>
					Crew
				</td>
				<td>
					${ship.crew}
				</td>
			</tr>
			<tr>
				<td>
					Passengers
				</td>
				<td>
					${ship.passengers}
				</td>
			</tr>
			<tr>
				<td>
					Max. Atm. Speed
				</td>
				<td>
					${ship.maxAtmospheringSpeed}
				</td>
			</tr>
			<tr>
				<td>
					Cargo Capacity
				</td>
				<td>
					${ship.cargoCapacity}
				</td>
			</tr>
			<tr>
				<td>
					Consumables
				</td>
				<td>
					${ship.consumables}
				</td>
			</tr>
		</c:forEach>
	
	</table>

</div>

</body>
</html>