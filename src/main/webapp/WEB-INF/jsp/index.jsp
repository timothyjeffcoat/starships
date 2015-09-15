<%@ include file="includes.jsp" %>

<spring:url value="/sync?page=" var="next_page" />
<spring:url value="/viewpilots" var="viewpilots" />
<spring:url value="/viewss" var="viewss" />
<spring:url value="/search" var="search" />
<spring:url value="/sortprice" var="sortprice" />
<spring:url value="/filterbyprice" var="filterbyprice" />
<html>
<head>
    <title>Star Wars Star Ships Store</title>
</head>
<body>
	<div>
		<spring:url value="/" var="home" />
		<h1><a href="${home}">Starships Are Us</a></h1>  
	</div>
	<div>
		Browse our fine selection of star ships!  
	</div> 
	<div>
		The number of available ships is ${count}
		<br>
		<form action="${search}" class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" id="name" name="name" class="form-control" placeholder="Search by ship name">
			</div>
			<button type="submit" class="btn btn-default">Submit by ship name</button>
		</form>
		<form action="${filterbyprice}" class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="number" id="filterByPriceLow" name="filterByPriceLow" class="form-control numberinput" pattern="[a-zA-Z0-9_-]{4,10}"  placeholder="Filter By Price Low">
				<input type="number" id="filterByPriceHigh" name="filterByPriceHigh" class="form-control numberinput" pattern="[a-zA-Z0-9_-]{4,10}"  placeholder="Filter By Price High">
			</div>
			<button type="submit" class="btn btn-default">Submit to filter by price</button>
		</form>
		<div>
			<c:if test="${not empty filterError }">
				<div class="alert alert-danger" role="alert">Error on filtering by price; ${filterError}</div>
			</c:if>
		</div>
		<table class="table">
			<tr>
				<th>Ship name</th>
				<th>
					<c:if test="${empty asc }">
						<c:set var="asc" value="true"/>
					</c:if>
					<a href="${sortprice}?asc=${asc}">Price</a>
				</th>
				<th>Pilots</th>
			</tr>
			<c:forEach var="ship" items="${starships}">
				<tr>
					<td>
						<c:set var="ssz" value="${fn:length(ship.url)}"/>
						<c:set var="sfirstslash" value="${fn:indexOf(ship.url,\"s/\")}"/>
						<c:set var="sid" value="${fn:substring(ship.url, sfirstslash+2, ssz-1)}" />
						<a href="${viewss}/?id=${sid}">${ship.name}</a> 
					</td>
					<td>
						<c:set var="price" value="${ship.costInCredits}" />
						<c:if test="${price != 'unknown' }">
					 		<fmt:formatNumber type="number" maxFractionDigits="3" value="${price}" />
						</c:if>
					</td>
					<td>
						<c:forEach var="purls" items="${ship.pilotsUrls}">
							<c:set var="sz" value="${fn:length(purls)}"/>
							<c:if test="${sz > 0 }">
								<c:set var="firstslash" value="${fn:indexOf(purls,\"e/\")}"/>
								<c:set var="pid" value="${fn:substring(purls, firstslash+2, sz-1)}" />
								<a href="${viewpilots}/?id=${pid}">${pid}</a>
								<br>
							</c:if>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>