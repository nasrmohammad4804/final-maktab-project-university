<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/masterPanel.css">
</head>
<body>


<div class="header">
    <span>hello master ${myUser.userName} </span>
</div>

<table>


        <tr>
            <th>id</th>
            <th>name</th>
            <th>courseStartedDate</th>
            <th>courseFinishedDate</th>
            <th></th>

        </tr>
        <c:forEach var="course" items="${allCourse}">
            <tr>
        <form action="/master/show-course" method="get">

                <td>${course.id}</td>
                <td>${course.name}</td>
                <td>${course.courseStartedDate}</td>
                <td>${course.courseFinishedDate}</td>

                <td> <button type="submit" class="myButton" name="id" value="${course.id}">show</button></td>


        </form>
            </tr>
        </c:forEach>

</table>

</body>
</html>