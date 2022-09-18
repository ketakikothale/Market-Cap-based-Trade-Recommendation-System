<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Login Page</title>
</head>
<body>
	<form method ="post">
		<button name="type" value="small">Small Cap</button>
		<button name="type" value="mid">Mid Cap</button>
		<button name="type" value="large">Large Cap</button>
		
		<br><br>
		
		
		
		<table border="1">
			<c:forEach var="stk" items="${response}" varStatus="theCount">
			<c:if test="${theCount.count le 1}">
				<h2>Recommended Stocks:<h2>
				
 				<thead >
				<tr>
					<th>Sr.No.</th>
					<th>Symbol</th>
					<th>Name</th>
					<th>Price</th>
					<th>Beta</th>
					<th>Market Cap</th>
					<th>Save</th>
				</tr>
			</thead>
			</c:if>
				<tr>
					<td>${theCount.count}</td>
					<td>${stk.symbol}</td>
					<td>${stk.name}</td>
					<td>${stk.price}</td>
					<td>${stk.beta}</td>
					<td>${stk.marketCap}</td>
					<td><button name="ticker" value=${stk.symbol}>Save ${stk.name}</button></td>
				<tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>