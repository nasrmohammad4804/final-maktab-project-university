<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>register</title>
    <link rel="stylesheet" href="../../../resource/css/register.css">
</head>
<body>

<div class="parent">
    <form action="/register" method="post"  id="myForm" >
        <input class="myInput" type="text" name="firstName" id="myFirstName" placeholder="firstName" required>

        <input class="myInput"  type="text" name="lastName" id="myLastName" placeholder="lastName" required>
        <input class="myInput"  type="text" name="userName" id="myUserName" placeholder="userName"  required>
        <span id="userNameError"></span>


        <input class="myInput" type="text" name="password" id="myPassword" placeholder="password" required>
        <span id="passwordError"></span>


        <div id="userType">
            <label for="master">master</label>
            <input type="radio" name="userType" value="MASTER" id="master" required>
            <label for="student">student</label>
            <input type="radio" name="userType" value="STUDENT" id="student" required>
        </div>
        <input class="myInput" type="submit" value="confirm" id="mySubmit">
    </form>
    <c:if test="${error eq 'occurred'}">
        <div style="color: red;font-size: 18px">this userName already exists</div>
    </c:if>

</div>
<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../resource/js/register.js"></script>
</body>

</html>