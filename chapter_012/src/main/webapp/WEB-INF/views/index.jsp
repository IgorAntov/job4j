<%@ page import="ru.job4j.servlets.http.servlet.models.User" %>
<%@ page import="ru.job4j.servlets.http.validate.Validate" %>
<%@ page import="ru.job4j.servlets.http.validate.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 11.07.19
  Time: 8:04
  To change this template use File | Settings | File Templates.
--%>
<%  final Validate validate = ValidateService.getInstance();
    if(request != null && !request.getParameterMap().isEmpty() && !request.getParameter("id").isEmpty()) {
    validate.delete(request.getParameter("id"));
    response.sendRedirect(String.format("%s/index.jsp", request.getContextPath()));
} %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p> User List </p>
<table cellspacing="1" border="1" cellpadding="2">
    <% for (User user : validate.findAll()) { %>
    <tr>
        <td> <%=user.getName()%>  </td>
        <td> <%=user.getEmail()%>  </td>
        <td><form action='${pageContext.request.contextPath}/WEB-INF/index.jsp' method="post">
            <input type="hidden" name="id" value=<%=user.getId()%>>
            <input type = 'submit' value = "delete">
        </form>
        </td>
        <td>
            <form action='${pageContext.request.contextPath}/update.jsp' method="post">
                <input type="hidden" name="name" value="<%=user.getName()%>">
                <input type="hidden" name="login" value="<%=user.getLogin()%>">
                <input type="hidden" name="email" value="<%=user.getEmail()%>">
                <input type = 'submit' value = "update">
            </form>
        </td>
    </tr>
    <%}; %>
</table>
<form action = '${pageContext.request.contextPath}/create.jsp' method = "post">
    <input type = "submit" value = "Add User">
</form>
</body>
</html>