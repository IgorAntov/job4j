<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 13.07.19
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron text-center">
    <h1> Welcome to the user manager</h1>
    <p>
        Select an action on a user or add a new user.
    </p>
    <p>
        <a href="${pageContext.request.contextPath}/logout">Logout</a><br>
    </p>
</div>

<div class="container">
    <h2>User List.</h2>
    <p>Your current Role: ${sessionScope.role} </p>
    <table class="table table-bordered" id='table'>
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>City</th>
            <th>*</th>
            <th>*</th>
            <th>*</th>
        </tr>
        </thead>

        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td> <c:out value="${user.name}"></c:out></td>
                <td> <c:out value="${user.email}"></c:out></td>
                <td> <c:out value="${user.country}"></c:out></td>
                <td> <c:out value="${user.city}"></c:out></td>
                <td>
                    <c:if test="${sessionScope.role == 'All'}">
                        <form action='${pageContext.request.contextPath}/' method="post">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                            <input type='hidden' name='task' value="delete">
                            <button type="submit" class="btn btn-default">Delete</button>
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${sessionScope.role == 'All' || (sessionScope.role == 'Update' && user.name != 'admin')}">
                        <form action='${pageContext.request.contextPath}/' method="post">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                            <input type='hidden' name='task' value="update">
                            <button type="submit" class="btn btn-default">Update</button>
                        </form>
                    </c:if>
                </td>
                <td>
                    Role: <c:out value="${user.role}"></c:out>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <c:if test="${sessionScope.role == 'All'}">
                <div class="container-fluid">
                    <form action='${pageContext.request.contextPath}/' id="myForm">
                        <input type='hidden' name='task' value="add"></br>
                        <button type="submit" class="btn btn-default">Add New User</button>
                    </form>
                </div>
            </c:if>
        </div>
        <div class="col-sm-4">
            <%--            <h3>Column 2</h3>
                        <p>Lorem ipsum dolor..</p>--%>
        </div>
        <div class="col-sm-4">
            <%--            <h3>Column 3</h3>
                        <p>Lorem ipsum dolor..</p>--%>
        </div>
    </div>
</div><br>
<div class="well well-sm"><h3>Upload and Download images manager</h3></div>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <div class="container-fluid">
                <form method="post" action = '${pageContext.request.contextPath}/' enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="file">Select file to upload (*.png)</label>
                        <input type="file" class="form-control-file" id="file" name="file">
                    </div>
                    <button type="submit" class="btn btn-default">Upload</button>
                </form>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="panel panel-default">
                <div class="panel-body">
                    <c:forEach items="${listFiles}" var="file">
                        <a href="${pageContext.request.contextPath}/?fileName=<c:out value="${file.name}"></c:out>">Download Link: <c:out value="${file.name}"></c:out></a><br>
                        <img src="${pageContext.request.contextPath}/?fileName=<c:out value="${file.name}"></c:out>" width="250" height="250" class="img-thumbnail" alt="Cinque Terre"><br>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
