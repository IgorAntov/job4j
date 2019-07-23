<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 13.07.19
  Time: 11:15
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
        function updateCountries() {
            var country = new Object();
            country.id = $('#sel2').val();
            $.ajax({
                type: "POST",
                url: "/chapter_012/cities",
                dataType: 'json',
                data: JSON.stringify(country),
                complete:  function (data) {
                    var cities = JSON.parse(data.responseText);
                    var select_options ="";
                    $('#sel3').empty();
                    for (var i = 0; i != cities.length; i++) {
                        $('#sel3').append($('<option>', {
                            value: cities[i].name,
                            text: cities[i].name
                        }));
                    }
                },
                error: function () {
                    alert('error')
                }
            });
        }

        function addCity() {
            var city = new Object();
            city.id = $('#sel4').val();
            city.name = $('#city').val();
            $.ajax({
                type: "POST",
                url: "/chapter_012/cities",
                dataType: 'json',
                data: JSON.stringify(city),
                success:  alert("City was added to the store.")
            });
        }

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
    <p>Add New User</p>
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
                            <input type="text" class="form-control" id="name" placeholder="Enter name" name ="name">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" placeholder="Enter password" name="login">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" class="form-control" id="email" placeholder="Enter email" name ="email">
                        </div>
                        <div class="form-group">
                            <label for="sel1">Select list:</label>
                            <select class="form-control" id="sel1" name="role">
                                <option value="All">All Actions allowed</option>
                                <option value="Update">Update User Role</option>
                                <option value="None">Unprivileged Role</option>
                            </select>
                            <input type='hidden' name='task' value="add"> </br>
                        </div>
                        <div class="form-group">
                            <label for="sel2">Country List:</label>
                            <select class="form-control" id="sel2" name="country" onclick="updateCountries();">
                                <c:forEach var="entry" items="${countries}" >
                                    <option value="<c:out value="${entry.key}"></c:out>"><c:out value="${entry.value}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="sel3">City List:</label>
                            <select class="form-control" id="sel3" name="city">
                            </select>
                        </div>
                        <input type='hidden' name='task' value="add"> </br>
                        <button type="submit" class="btn btn-default" onclick="validate()">Save</button>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action='${pageContext.request.contextPath}/' method = "post" id="myForm2">
                        <div class="form-group">
                            <label for="sel4">Country List:</label>
                            <select class="form-control" id="sel4" name="country">
                                <c:forEach var="entry" items="${countries}" >
                                    <option value="<c:out value="${entry.key}"></c:out>"><c:out value="${entry.value}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="city">City:</label>
                            <input type="text" class="form-control" id = "city" placeholder="Enter city" name ="city">
                        </div>
                        <input type='hidden' name='task' value="addcity"> </br>
                        <button type="button" class="btn btn-default" onclick="addCity();">Add new City</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
        </div>
    </div>
</div>
</body>
</html>