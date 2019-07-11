<%@ page import="ru.job4j.servlets.http.validate.ValidateService" %>
<%@ page import="ru.job4j.servlets.http.validate.Validate" %><%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 11.07.19
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% final Validate validate = ValidateService.getInstance();
   if(request != null && !request.getParameterMap().isEmpty()) {
    validate.add(request.getParameter("name"), request.getParameter("login"), request.getParameter("email"));
    response.sendRedirect(String.format("%s/index.jsp", request.getContextPath()));
} %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>New User </p></br>

<form action = '<%=request.getContextPath()%>/create.jsp' method = "post">
    Name:  <input type = 'text' value = "name" name = "name" /> </br>
    Login: <input type = 'text' value = 'login' name = 'login'/> </br>
    Email: <input type = 'text' value = 'email' name = 'email' /> </br>
           <input type = "submit" value = "Save">
    </form>
</body>
</html>
