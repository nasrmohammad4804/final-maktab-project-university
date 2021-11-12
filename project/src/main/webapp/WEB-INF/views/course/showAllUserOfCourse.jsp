<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/showAllUserOfCourse.css">
</head>
<body>


<hr>
<h3>user list of ${sessionScope.course.name} course</h3> <hr>


<div  id="master-information-header">
    <div id="master-information">master information</div>
    <br>
    <div class="parent">
        <div>firstName : <span>${master.firstName}</span></div>
        <div>lastName : <span>${master.lastName}</span></div>
        <div>userName : <span>${master.userName}</span></div>
    </div>
</div>

<hr>
<h3 style="margin-top: 20px; color: coral;">information of all student</h3>
<hr>

<div id="add-student-link"> <a href="/course/select-student">add-student</a> </div>
<table>


    <tr>
        <th>firstName</th>
        <th>lastName</th>
        <th>userName</th>
        <th>modify</th>

    </tr>
    <c:forEach var="student" items="${allStudentOfCourse}">
        <tr>
        <td>${student.firstName}</td>
        <td>${student.lastName}</td>
        <td>${student.userName}</td>
        <td><a class="delete" href="/course/delete-student/${student.id}">delete</a></td>
        </tr>
    </c:forEach>

</table>

<div id='message' >
    you are successfully  deleted student from course
</div>
    <script src="../../../resource/js/jquery-3.6.0.min.js"></script>
    <script src="../../../resource/js/deleteStudentFromCourse.js" ></script>
</body>
</html>