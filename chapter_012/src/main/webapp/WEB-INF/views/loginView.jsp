<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 16.07.19
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != ''}">
    <div>
    <c:out value="${error}" ></c:out>
    </div>
</c:if>
<form action = '${pageContext.request.contextPath}/signin' method = "post">
    Name:  <input type = 'text' name = "name" /> </br>
    Login: <input type = 'password' name = 'password'/> </br>
    <input type = "submit">
</form>
</body>
</html>
