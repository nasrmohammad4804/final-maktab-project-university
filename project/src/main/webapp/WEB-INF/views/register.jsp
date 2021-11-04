<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>register</title>
    <link rel="stylesheet" href="../../resource/css/register.css">
</head>
<body>

<div class="parent">
    <form action="/register" method="post" >
        <input class="myInput" type="text" name="firstName"  placeholder="firstName" required>

        <input class="myInput"  type="text" name="lastName"  placeholder="lastName" required>
        <input class="myInput"  type="text" name="userName"  placeholder="userName" required>



        <input class="myInput" type="password" name="password"  placeholder="password" required>


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
</body>
</html>