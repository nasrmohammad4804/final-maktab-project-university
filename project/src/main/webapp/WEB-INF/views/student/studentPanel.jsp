<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/masterPanel.css">
    <style>
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>


<div class="header">
    <span>hello student ${student.userName} </span>
</div>

<table>


    <tr>
        <th>id</th>
        <th>courseName</th>
        <th>courseNumber & groupNumber</th>
        <th>masterName</th>
        <th></th>

    </tr>
    <c:forEach var="course" items="${student.courseSet}">
        <tr>
            <form action="/student/course" method="get">

                <td>${course.id}</td>
                <td>${course.name}</td>
                <td>${course.courseNumber}_${course.groupNumber}</td>
                <td>${course.master.firstName} &nbsp; ${course.master.lastName} </td>

                <td>
                    <button type="submit" class="myButton" name="id" value="${course.id}">show</button>
                </td>


            </form>
        </tr>
    </c:forEach>

</table>

</body>
</html>