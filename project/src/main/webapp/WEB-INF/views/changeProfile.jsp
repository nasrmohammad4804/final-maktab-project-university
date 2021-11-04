<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../resource/css/changeProfile.css">
</head>
<body>

<div class="parent">
    <h3>change proflie</h3>
    <form action="/change-profile" method="post">
        <label id="firstNameLable">firstName:</label>
        <input type="text" value="${sessionScope.user.firstName}" name="firstName" required> <br> <br>

        <label>lastName:</label>
        <input type="text" value="${sessionScope.user.lastName}" name="lastName" required> <br> <br>

        <span>userType: </span>

        <label for="student">student</label>
        <c:choose>
        <c:when test="${userType eq 'STUDENT'}">
            <input type="radio" name="userType" value="STUDENT" id="student" checked >
        </c:when>
        <c:otherwise>
            <input type="radio" name="userType" value="STUDENT" id="student">

        </c:otherwise>
        </c:choose>

        &nbsp; &nbsp;
        <label for="master">master</label>
        <c:choose>
        <c:when test="${userType eq 'MASTER'}">
            <input type="radio" name="userType" value="MASTER" id="master" checked>
        </c:when>
        <c:otherwise>
            <input type="radio" name="userType" value="MASTER" id="master">
        </c:otherwise>
        </c:choose>
        <br><br>
        <input type="submit" value="confirm" id="mySubmit">
    </form>
</div>
</body>
</html>