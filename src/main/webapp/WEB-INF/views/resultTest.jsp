<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hanna Kolesava
  Date: 12/17/2016
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result</title>
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
    <div class="result">
        <p>Your result:</p>
        <p>${result}%</p>
    </div>
</div>




</body>
</html>
