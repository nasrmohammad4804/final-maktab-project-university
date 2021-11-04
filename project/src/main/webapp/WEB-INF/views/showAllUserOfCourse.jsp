<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        td,th{
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table>

    <tr>
        <th>firstName</th>
        <th>lastName</th>
        <th>userName</th>
        <th>role</th>
    </tr>
    <c:forEach var="user" items="${allUserOfCourse}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.userName}</td>
            <td>${user.role.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>