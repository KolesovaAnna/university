<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Test Page</title>
	<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="wrapper">
	<header>

		<div class="title">
			<i class="fa fa-bars visible-xs" aria-hidden="true"></i>
			<a href="<c:url value='/'/>" >All tests  <i class="fa fa-angle-right" aria-hidden="true"></i></a>
			<h3>${test.title}</h3>
		</div>
		<div class="descr hidden-xs">${test.description}</div>
	</header>

	<form method="post" action="/result/${test.id}" class="formDiv">
	<c:forEach items="${question}" var="q">
		<div class="questionDiv">
			<p class="questionTitle">${q.title}</p>
			<c:if test="${empty q.answers}">
					<p>empty</p>
			</c:if>
			<c:forEach items="${q.answers}" var="answer">
				<label class="answerLabel"><input type="checkbox" name="checking" value="${answer.id}">${answer.choice}</label>
			</c:forEach>
		</div>
	</c:forEach>
		<p style="text-align: right; padding-top:10px;"><input class="pinkSubmit" type="submit" value="Submit"></p>
	</form>
</div>
</body>
</html>
