<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{
            width: 100%;
            height: 100vh;
            background-image: url('https://www.chetor.com/wp-content/uploads/2018/09/142058.jpg');
            background-repeat: no-repeat;
            background-size: 100% 100%;
        }
        td,th{
            border: 2px solid black;
            padding: 7px;
        }
        a{
            text-decoration: none;
            background-color: greenyellow;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <th>firstName</th>
        <th>lastName</th>
        <th>userName</th>
        <th>select</th>
    </tr>

   <c:forEach var="student" items="${students}">
       <tr>
       <td>${student.firstName}</td>
       <td>${student.lastName}</td>
       <td>${student.userName}</td>
       <td> <a href="/course/confirm-student?id=${student.id}">choose </a> </td>
       </tr>
   </c:forEach>


</table>
</body>
</html>