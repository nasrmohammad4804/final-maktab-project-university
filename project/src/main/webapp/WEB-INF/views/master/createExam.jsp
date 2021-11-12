<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/createExam.css">
</head>
<body>

<c:if test="${not empty myError}">
    <span id="myError">${myError}</span>
</c:if>
<form action="/master/create-exam" method="post">


    <input id="myTitle" type="text" placeholder="title" name="title"> <br><br>

    <textarea name="description" id="description" cols="20" rows="7" placeholder="description" ></textarea><br><br>
    <label for="startedTime">startTime: </label>
    <input type="datetime-local" id="startedTime" name="startTime"  >
    <br><br>
    <label for="finishedTime">finish time: </label>
    <input type="time" id="finishedTime"  name="endTime" >
    <input type="hidden" name="courseId" value="${courseId}">
    <input type="submit" value="confirm">
</form>


</body>
</html>