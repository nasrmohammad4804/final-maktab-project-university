<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/showAllCourse.css">
</head>
<body>
<table>

    <tr>
        <th>courseName</th>
        <th>start time</th>
        <th>end time</th>
        <th>courseNumber & groupNumber</th>
        <th>select</th>
    </tr>
    <c:forEach var="course" items="${allCourse}">
        <tr>
            <td>${course.name}</td>
            <td>${course.courseStartedDate}</td>
            <td>${course.courseFinishedDate}</td>
            <td>${course.courseNumber}_${course.groupNumber}</td>
            <td><a id="myLink" href="/course/show/${course.id}">choose</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>