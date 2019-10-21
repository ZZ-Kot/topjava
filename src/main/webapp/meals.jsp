<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
   	<link rel="stylesheet" type="text/css" href="js/jquery.datetimepicker.min.css"/>
	<script src="js/jquery.js"></script>
	<script src="js/jquery.datetimepicker.full.min.js"></script>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        
        <!-- 
		<select>
	        <c:forEach items="${users}" var="user">
	            <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
				  <option  id="id" value="${user.id}">${user.name}</option>
	        </c:forEach>
		</select>
		 --> 
        
        <form action="meals" method="get">
	        <label>Date from</label>
	        <input type="text" name="dateFrom" id="dateFrom"/>
			<script>
				$('#dateFrom').datetimepicker({
					lang:'ru',
					timepicker:false,
					format:'d.m.Y',
				});
			</script>
	
	        <label>Date to</label>
	        <input type="text" name="dateTo" id="dateTo"/>
			<script>
				$('#dateTo').datetimepicker({
					lang:'ru',
					timepicker:false,
					format:'d.m.Y',
				});
			</script>
	
	        <label>Time from</label>
	        <input type="text" name="timeFrom" id="timeFrom"/>
			<script>
				$('#timeFrom').datetimepicker({
					datepicker:false,
					format:'H:i',
				});
			</script>
			
	        <label>Time to</label>
	                <input type="text" name="timeTo" id="timeTo"/>
			<script>
				$('#timeTo').datetimepicker({
					datepicker:false,
					format:'H:i',
				});
			</script>
			
			<input type="hidden" name="userId" value="${userId}"/>
			<input type="hidden" name="action" value="filter"/>
			<input type="submit" value="Фильтровать"/	>
		</form>
        
		
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&userId=${userId}&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&userId=${userId}&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>