<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 14.07.19
  Time: 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Update User JSTL</p><br>
<form action = '${pageContext.request.contextPath}/' method = "post">
    Name:  <input type = 'text' value = "<c:out value="${user.name}"></c:out>" name = "name"/> </br>
    Login: <input type = 'text' value = "<c:out value="${user.login}"></c:out>" name = 'login'/> </br>
    Email: <input type = 'text' value = "<c:out value="${user.email}"></c:out>" name = 'email'/> </br>
    Role: <select name="role">
    <option value="All">All Actions allowed</option>
    <option value="Update">Update User Role</option>
    <option value="None">Unprivileged Role</option>
</select>
    <input type="hidden" name="task" value="update">
    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
    <input type ="submit" value = "Save">
</form>
</body>
</html>
