<%@ page import="ru.job4j.servlets.http.validate.ValidateService" %>
<%@ page import="ru.job4j.servlets.http.validate.Validate" %>
<%@ page import="ru.job4j.servlets.http.User" %><%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 11.07.19
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% final Validate validate = ValidateService.getInstance();
    if(request != null && !request.getParameterMap().isEmpty() && !request.getParameter("id").isEmpty() && request.getParameter("task") != null) {
        User u = validate.findById(request.getParameter("id"));
        u.setName(request.getParameter("name"));
        u.setLogin(request.getParameter("login"));
        u.setEmail(request.getParameter("email"));
        validate.update(u);
        response.sendRedirect(String.format("%s/index.jsp", request.getContextPath()));
    } %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Update User </p><br>
<form action = '<%=request.getContextPath()%>/WEB-INF/views/update.jsp' method = "post">
    Name:  <input type = 'text' value = "<%=request.getParameter("name")%>" name = "name"/> <br>
    Login: <input type = 'text' value = "<%=request.getParameter("login")%>" name = 'login'/> <br>
    Email: <input type = 'text' value = "<%=request.getParameter("email")%>" name = 'email'/> <br>
    <input type="hidden" name="task" value="update">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <input type ="submit" value = "Save">
</form>
</body>
</html>
