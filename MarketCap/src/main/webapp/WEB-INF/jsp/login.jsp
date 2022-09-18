<html>
<head>
<title>Login Page</title>
<link rel = "stylesheet" type = "text/css" href = "login.css">
</head>
<body>
	<div class = "header">
		<h2>${error}</h2>
	</div>
	<div class = "login">
	<form method ="post">
		<input type="text" name="userName" placeholder="User Name"/>
		<br><br>
		<input type="password" name="password" placeholder="Password"/>
		<br><br>
		<button>Submit</button>
	</form>
	</div>
</body>
</html>