<%@ include file="includes.jsp" %>
<html>
<head>
    <title>Pilot</title>
</head>
<body>
	<div>
		<spring:url value="/" var="home" />
		<h1><a href="${home}">Starships Are Us</a></h1>  
	</div>

Pilot Information
<div class="panel panel-primary">
	<table class="table">
		<tr>
			<td>
				Name
			</td>
			<td>
				${pilot.name}
			</td>
		</tr>
		<tr>
			<td>
				Birth year
			</td>
			<td>
				${pilot.birthYear}
			</td>
		</tr>
		<tr>
			<td>
				Gender
			</td>
			<td>
				${pilot.gender}
			</td>
		</tr>		
		<tr>
			<td>
				Hair color
			</td>
			<td>
				${pilot.hairColor}
			</td>
		</tr>		
		<tr>
			<td>
				Height
			</td>
			<td>
				${pilot.height}
			</td>
		</tr>		
		<tr>
			<td>
				Mass
			</td>
			<td>
				${pilot.mass}
			</td>
		</tr>		
		<tr>
			<td>
				Skin color
			</td>
			<td>
				${pilot.skinColor}
			</td>
		</tr>		
	</table>

</div>

</body>
</html>