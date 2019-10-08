<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Meal</title>
</head>
<body>
	<h3>
		<a href="index.html">Home</a>
	</h3>
	<hr>
	<h2>Meals</h2>

	<table border="1">
		<caption>Таблица Meals</caption>
		<tr>
			<th>Дата/Время</th>
			<th>Описание</th>
			<th>Калории</th>
		</tr>
		<c:forEach var="meal" items="${mealsTo}">
			<c:choose>
				<c:when test='${meal.excess}'>
					<tr bgcolor="RED">
				</c:when>
				<c:otherwise>
					<tr bgcolor="GREEN">
				</c:otherwise>
			</c:choose>
				<td>${meal.dateTime}</td>
				<td>${meal.description}</td>
				<td>${meal.calories}</td>
				</tr>
		</c:forEach>
	</table>

</body>
</html>