<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 13.07.19
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p> User List (JSTL) </p>
<table cellspacing="1" border="2" cellpadding="2" style="border-collapse: collapse; border: 1px solid black; text-align: center; vertical-align: middle;">
    <tr><td><b> Name </b></td> <td><b> Email </b></td><td> </td><td> </td>
    </tr>
<c:forEach items="${users}" var="user">
    <tr>
        <td> <c:out value="${user.name}"></c:out></td>
        <td> <c:out value="${user.email}"></c:out></td>
        <td><form action='${pageContext.request.contextPath}/' method="post">
            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
            <input type='hidden' name='task' value="delete">
            <input type = 'submit' value = "delete">
        </form>
        </td>
        <td>
            <form action='${pageContext.request.contextPath}/' method="post">
                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                <input type='hidden' name='task' value="update">
                <input type = 'submit' value = "update">
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<form action = '${pageContext.request.contextPath}/' method = "post">
    <input type='hidden' name='task' value="add"></br>
    <input type = "submit" value = "Add User">
</form>
</body>
</html>
