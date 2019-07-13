<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 13.07.19
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>New User JSTL</p></br>

<form action = '${pageContext.request.contextPath}/' method = "post">
    Name:  <input type = 'text' value = 'name' name = "name" /> </br>
    Login: <input type = 'text' value = 'login' name = 'login'/> </br>
    Email: <input type = 'text' value = 'email' name = 'email' /> </br>
           <input type='hidden' name='task' value="add"> </br>
    <input type = "submit" value = "Save">
</form>
</body>
</html>