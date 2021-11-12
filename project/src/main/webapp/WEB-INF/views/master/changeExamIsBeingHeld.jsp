<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/changeExamIsBeingHeld.css">
</head>
<body>

<h2 class="myHeader">change exam is being held</h2><hr>


    <c:if test="${not empty error}">
        <span class="red">${error}</span>
    </c:if>

<form action="/master/change-examBeingHeld" method="post" >

    <div class="myTime myArea "><textarea name="description" id="myTextArea" cols="30" rows="10"  placeholder="newDescription" required></textarea> </div>
    <div class="myTime"> previos of time exam: <span>${exam.endTime}</span> &nbsp;&nbsp;</div> &nbsp;&nbsp;

    <div class="myTime " >
        <label for="newTime">newTime: </label>
        <input  id="newTime" type="time" name="endTime" required> </div>
        <input type="hidden" name="id" value="${exam.id}">
    <input type="submit" value="confirm" id="mySubmit">
</form>
</body>
</html>