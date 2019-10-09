<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Meal</title>
	</head>
	<body>
		<h3>
			<a href="index.html">Home</a>
		</h3>
		<hr>
		<h2>Meals create</h2>
	
		<form action="create" method="post">
			<p>
				description:<input type="text" name="description" size="50" /><br/>
				calories:<input type="text" name="calories" size="50" /><br/>
			</p>
			<input type="submit" value="Сохранить" />
		</form>
	
		<a href="topjava">Вернуться к списку</a>
	
	</body>
</html>