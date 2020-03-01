<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions"%>
<html>
<jsp:include page="fragments/headTag.jsp" />
<body>
	<script type="text/javascript" src="resources/js/topjava.common.js"	defer></script>
	<script type="text/javascript" src="resources/js/topjava.meals.js"	defer></script>
	<jsp:include page="fragments/bodyHeader.jsp" />

	<!-- <section> -->
	<div class="jumbotron pt-4">
		<div class="container">

			<h3 class="text-center"><spring:message code="meal.title" /></h3>

			<div class="card border-dark">
				<div class="card-body pb-0">
					<form id="filter" method="get" action="meals/filter">
						<div class="row">
							<div class="offset-1 col-2">
								<dl>
									<dt><spring:message code="meal.startDate" />:</dt>
									<dd><input class="form-control" type="date" name="startDate" value="${param.startDate}"></dd>
								</dl>
							</div>
							<div class="col-2">
								<dl>
									<dt><spring:message code="meal.endDate" />:</dt>
									<dd><input class="form-control" type="date" name="endDate" value="${param.endDate}"></dd>
								</dl>
							</div>
							<div class="offset-2 col-2">
								<dl>
									<dt><spring:message code="meal.startTime" />:</dt>
									<dd><input class="form-control" type="time" name="startTime" value="${param.startTime}"></dd>
								</dl>
							</div>
							<div class="col-2">
								<dl>
									<dt><spring:message code="meal.endTime" />:</dt>
									<dd><input class="form-control" type="time" name="endTime" value="${param.endTime}"></dd>
								</dl>
							</div>
							<div class="card-footer text-right">
								<button class="btn btn-primary" type="submit"><spring:message code="meal.filter" /></button>
							</div>
						</div>
					</form>
				</div>
			</div>

			<hr>
				<%-- <a href="meals/create"><spring:message code="meal.add" /></a> --%>
		        <button class="btn btn-primary" onclick="add()">
		            <span class="fa fa-plus"></span>
		            <spring:message code="meal.add"/>
		        </button>
			<hr>
			
			<div id="datatable_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-striped dataTable no-footer" id="datatable" border="1" cellpadding="8" cellspacing="0">
							<thead>
								<tr>
									<th class="sorting_desc"><spring:message code="meal.dateTime" /></th>
									<th class="sorting"><spring:message code="meal.description" /></th>
									<th class="sorting"><spring:message code="meal.calories" /></th>
									<th class="sorting_disabled"></th>
									<th class="sorting_disabled"></th>
								</tr>
							</thead>
							<c:forEach items="${meals}" var="meal">
								<jsp:useBean id="meal" scope="page"
									type="ru.javawebinar.topjava.to.MealTo" />
								<tr data-mealExcess="${meal.excess}">
									<td>
										<%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
										<%--<%=TimeUtil.toString(meal.getDateTime())%>--%> <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
										${fn:formatDateTime(meal.dateTime)}
									</td>
									<td>${meal.description}</td>
									<td>${meal.calories}</td>
									<td>
										<a href="meals/update?id=${meal.id}">
											<%-- <spring:message code="common.update" /> --%>
											<span class="fa fa-pencil"></span>
										</a>
									</td>
									<td>
										<a href="meals/delete?id=${meal.id}">
											<%-- <spring:message code="common.delete" /> --%>
											<span class="fa fa-remove"></span>
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<!-- </section> -->
		</div>
	</div>
	
<div class="modal fade" tabindex="-1" id="editRow" style="display: none;" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle">Добавить еду</h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">×</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id" value="">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label">Дата/Время</label>
                        <input class="form-control" type="datetime-local" value="1970-01-01T00:00" id="dateTime" name="dateTime" autocomplete="off" placeholder="Дата/Время">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label">Описание</label>
                        <input type="text" class="form-control" id="description" name="description" placeholder="Описание">
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label">Калории</label>
                        <input type="number" class="form-control" id="calories" name="calories" placeholder="1000">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    Отменить
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    Сохранить
                </button>
            </div>
        </div>
    </div>
</div>	
	<jsp:include page="fragments/footer.jsp" />
	
	<script type="text/javascript">
	    var i18n = [];
	    
	    i18n["addTitle"] = 'Добавить еду';
	    i18n["editTitle"] = 'Редактировать еду';
	    i18n['common.deleted'] = 'Запись удалена';
	    i18n['common.saved'] = 'Запись сохранена';
	    i18n['common.enabled'] = 'Запись активирована';
	    i18n['common.disabled'] = 'Запись деактивирована';
	    i18n['common.search'] = 'Искать';
	    i18n['common.confirm'] = 'Вы уверены?';
	</script>

</body>
</html>