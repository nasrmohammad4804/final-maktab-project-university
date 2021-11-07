<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/selectMaster.css">
</head>
<body>

    <table>
        <tr>
            <th>firstName</th>
            <th>lastName</th>
            <th>userName</th>
            <th>select</th>

        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userName}</td>
                <td> <a href="course/confirm/${user.id}" >choose</a> </td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>