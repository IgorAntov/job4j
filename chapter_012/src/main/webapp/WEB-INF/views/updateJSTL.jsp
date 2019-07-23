<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 14.07.19
  Time: 1:52
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
    <script>
        function validate() {
            name_el = $("#name");
            password_el = $("#password");
            email_el = $("#email");
            if (name_el.val() == '') {
                alert("Please " + name_el.attr('placeholder')); return false;
            }
            if (password_el.val() == '') {
                alert("Please " + password_el.attr('placeholder')); return false;
            }
            if (email_el.val() == '') {
                alert("Please " + email_el.attr('placeholder')); return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="jumbotron text-center">
    <h1>User Manager.</h1>
    <p>Update User</p>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
        </div>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action='${pageContext.request.contextPath}/' style="width:300px" method = "post" id="myForm">
                        <div class="form-group">
                            <label for="name">User Name:</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter name" name ="name" value = "<c:out value="${user.name}"></c:out>" >
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" placeholder="Enter password" name="login" value = "<c:out value="${user.login}"></c:out>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" class="form-control" id="email" placeholder="Enter email" name ="email" value = "<c:out value="${user.email}"></c:out>">
                        </div>

                        <div class="form-group">
                            <label for="sel1">Select list:</label>
                            <select class="form-control" id="sel1" name="role">
                                <option value="All">All Actions allowed</option>
                                <option value="Update">Update User Role</option>
                                <option value="None">Unprivileged Role</option>
                            </select>
                            <input type='hidden' name='task' value="update"> </br>
                            <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                        </div>
                        <button type="submit" class="btn btn-default" onclick="validate()">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
        </div>
    </div>
</div>


<%--<p>Update User JSTL</p><br>
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
</form>--%>
</body>
</html>
