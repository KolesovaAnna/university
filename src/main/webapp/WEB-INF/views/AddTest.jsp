<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Hanna Kolesava
  Date: 12/18/2016
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit test</title>
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
            <h3>Edition of ${test.title}</h3>
        </div>
    </header>

<c:url var="addAction" value="/editTest/${test.id}/test" ></c:url>

<form:form action="${addAction}" commandName="test">

    <c:if test="${!empty test.title}">

    <form:input path="id" readonly="true" size="8"  disabled="true" hidden="true" />
    <form:hidden path="id" />

    </c:if>

    <p class="titleInput"><form:input path="title" /></p>

    <p class="descrInput"><form:input path="description" /></p>

            <%--<c:if test="${!empty test.title}">--%>
                <%--<div class="divEditSubmit">--%>
                    <%--<input type="submit" class="editSubmit"--%>
                           <%--value="<spring:message text=""/>" />--%>
                    <%--<i class="fa fa-check-circle" aria-hidden="true"></i>--%>
                <%--</div>--%>

            <%--</c:if>--%>
            <%--<c:if test="${empty test.title}">--%>
                <%--<input type="submit"--%>
                       <%--value="<spring:message text="Add Test"/>" />--%>
            <%--</c:if>--%>

    <c:if test="${!empty test.title}">
    <div class="editQsubmit">
        <input type="submit" value="<spring:message text=""/>" />
        <i class="fa fa-check-circle xsIcons" aria-hidden="true"></i>
    </div>
    </c:if>


</form:form>

<c:forEach items="${question}" var="q">
        <c:url var="addAction" value="/editQuestion" ></c:url>

        <form:form class="questForm" action="${addAction}" commandName="question" modelAttribute="question" method="post">

        <c:if test="${!empty q.title}">
        <div class="questionDiv">
         <input name="id" readonly="true" value="${q.id}" hidden>

         <input name="right_answers" readonly="true" value="${q.right_answers}" hidden>

          <input name="id_test" readonly="true" value="${q.id_test}" hidden>

        </c:if>

        <p class="questionTitle"><input name="title" value="${q.title}"></p>


        <c:if test="${!empty q.title}">
            <div class="editQsubmit">
                <input type="submit" value="<spring:message text=""/>" />
                <i class="fa fa-check-circle xsIcons" aria-hidden="true"></i>
            </div>
         </c:if>
         <a class="removeQuestion" href="<c:url value='/removeQuestion/${q.id}'/>"><i class="xsIcons fa fa-trash-o gray-icon" aria-hidden="true"></i></a>
        </form:form>

        <c:forEach items="${q.answers}" var="answer">
            <c:url var="addAction" value="/editAnswer" ></c:url>

            <form:form action="${addAction}" commandName="answer" modelAttribute="answer" method="post">

                <c:if test="${!empty answer.choice}">

                    <input name="id" readonly="true" value="${answer.id}" hidden>

                    <input name="id_question" readonly="true" value="${answer.id_question}" hidden>

                </c:if>

                <p class="answerLabel editAnswer">
                    <input  name="is_right" type="checkbox" <c:if test="${answer.is_right}"> checked </c:if>>
                <input class="ans_choice" name="choice" value="${answer.choice}"></p>

            <div class="editQsubmit">
                <input type="submit" value="<spring:message text=""/>">
                <i class="fa fa-check-circle xsIcons" aria-hidden="true"></i>
            </div>
                <a class="removeQuestion" href="<c:url value='/removeAnswer/${answer.id}'/>" ><i class="xsIcons fa fa-trash-o gray-icon" aria-hidden="true"></i></a>
            </form:form>

        </c:forEach>
            <div class="addAnswer">
        <c:url var="addAction" value="/addAnswer" ></c:url>
        <form:form action="${addAction}" commandName="answer" modelAttribute="answer" method="post">

            <input name="id" value="0" hidden>

            <input name="id_question" value="${q.id}" hidden>

            <p class="answerLabel editAnswer">
                <input name="is_right" type="checkbox">
                <input placeholder="New Answer" class="choiceInput" name="choice">
            </p>

            <div class="addSubmit answer">
            <input type="submit" value="<spring:message text="Add"/>">
            </div>

        </form:form>
        </div>
        </div>
</c:forEach>

<div class="addQuestion">
    <c:url var="addAction" value="/addQuestion" ></c:url>

    <form:form action="${addAction}" commandName="question" modelAttribute="question">

        <input name="id" value="0" hidden>

        <input name="right_answers" value="0" hidden>

        <input name="id_test" value="${test.id}" hidden>

        <p class="addPQuestion"><input name="title" placeholder="New Question"></p>

        <div class="addSubmit question"> <input type="submit" value="<spring:message text="Add"/>"></div>
    </form:form>

</div>

</body>
</html>
