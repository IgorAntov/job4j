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
<p>
    <a href="${pageContext.request.contextPath}/logout">Logout</a><br>
</p>
<table cellspacing="1" border="2" cellpadding="2" style="border-collapse: collapse; border: 1px solid black; text-align: center; vertical-align: middle;">
    <tr><td><b> Name </b></td> <td><b> Email </b></td><td> </td><td> </td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td> <c:out value="${user.name}"></c:out></td>
            <td> <c:out value="${user.email}"></c:out></td>
            <c:if test="${sessionScope.role == 'All'}">
                <td><form action='${pageContext.request.contextPath}/' method="post">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                    <input type='hidden' name='task' value="delete">
                    <input type = 'submit' value = "delete">
                </form>
                </td>
            </c:if>
            <td>
                <c:if test="${sessionScope.role == 'All' || (sessionScope.role == 'Update' && user.name != 'admin')}">
                    <form action='${pageContext.request.contextPath}/' method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                        <input type='hidden' name='task' value="update">
                        <input type = 'submit' value = "update">
                    </form>
                </c:if>
            </td>
            <td>
                Role: <c:out value="${user.role}"></c:out>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${sessionScope.role == 'All'}">
    <form action = '${pageContext.request.contextPath}/' method = "post">
        <input type='hidden' name='task' value="add"></br>
        <input type = "submit" value = "Add User">
    </form>
</c:if>
<br>
<p>File Upload</p><br>
<form method="post" action = '${pageContext.request.contextPath}/' enctype="multipart/form-data">
    Select file to upload (picture -> *.png): <input type="file" name="file" size="60" /><br>
    <br /> <input type="submit" value="Upload" />
</form>
Click on the link to download picture (*.png):</br>
<c:forEach items="${listFiles}" var="file">
    <a href="${pageContext.request.contextPath}/?fileName=<c:out value="${file.name}"></c:out>">Download Link: <c:out value="${file.name}"></c:out></a><br>
    <img src="${pageContext.request.contextPath}/?fileName=<c:out value="${file.name}"></c:out>" width="150" height="130"><br>
</c:forEach>
</body>
</html>
