<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Home Page</title>
</head>
<body>
<div class="wrapper">
	<header>
		<i class="fa fa-bars visible-xs" aria-hidden="true"></i>
		<h3>Tests List</h3>
	</header>
<div class="tableTest">
<c:if test="${!empty listTests}">
	<table class="tg" cellspacing="0">
	<tr class="firstTr hidden-xs">
		<th class="testId hidden-md">Test ID</th>
		<th class="testTitle">Test title</th>
		<th class="testDescription hidden-xs">Test description</th>
		<th class="testEdit" style="text-align: center" >Edit</th>
		<th class="testDelete" style="text-align: center" >Delete</th>
		<th class="testRun" style="text-align: center" >Run</th>
	</tr>
	<c:forEach items="${listTests}" var="test">
		<tr class="testTr">
			<td class="hidden-md">${test.id}</td>
			<td>${test.title}</td>
			<td class="hidden-xs">${test.description}</td>
			<td class="testEdit" style="text-align: center" ><a href="<c:url value='/editTest/${test.id}' />" ><i class="fa fa-pencil-square-o gray-icon" aria-hidden="true"></i></a></td>
			<td class="testDelete" style="text-align: center"><a href="<c:url value='/removeTest/${test.id}' />" ><i class="fa fa-trash-o gray-icon" aria-hidden="true"></i></a></td>
			<td class="testRun" style="text-align: center"><a class="run" href="<c:url value='/runTest/${test.id}' />" ><i class="fa fa-play" aria-hidden="true"></i></a></td>
		</tr>
	</c:forEach>
		<tr class="addNewTest">
			<c:url var="addAction" value="/test/add" ></c:url>
			<form:form action="${addAction}" commandName="test">
					<td class="hidden-md"></td>
					<td>
						<form:input path="title" placeholder="New Title"/>
					</td>
					<td class="hidden-xs">
						<form:input placeholder="New Description" path="description" />
					</td>

					<td colspan="3" class="addSubmit">
						<input type="submit" value="Add" />
					</td>

			</form:form>
		</tr>
	</table>
</c:if>
</div>
	<div class="circleAdd"><i class="fa fa-plus" aria-hidden="true"></i></div>
</div>
</body>
</html>
