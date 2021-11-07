<!DOCTYPE html>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/showAllUser.css">
</head>


<div class="header">

    <form action="">
        <input type="text" placeholder="firstName" id="firstName">
        <input type="text" placeholder="lastName" id="lastName">
        <input list="userType" name="role" placeholder="role" id="role">
        <datalist id="userType" >
            <option value="student">student</option>
            <option value="master">master</option>
        </datalist>
        <input type="submit" value="searching" id="submit-searching">

    </form>
</div>
<p id="not-found-message">
    dont find any user</p>
<body>

<table>
    <tr>
        <th>firstName</th>
        <th>lastName</th>
        <th>userName</th>
        <th>state</th>
    </tr>
    <tbody id="my-tbody">
    <c:forEach var="user" items="${allUser}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.userName}</td>

            <c:choose>
                <c:when test="${user.registerState eq 'CONFIRM'}">
                    <td > <a href="/change-profile?id=${user.id}" id="green">confirm</a></td>
                </c:when>

                <c:when test="${user.registerState eq 'WAITING'}">
                    <td > <a href="/confirm-user/${user.id}" id="orange">waiting</a> </td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../resource/js/showAllUser.js"></script>

</body>
</html>