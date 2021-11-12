<%@ page import="ir.maktab.project.domain.Exam" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="ir.maktab.project.domain.enumeration.ExamState" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="../../../resource/css/showAllExam.css">
</head>
<body>

<a href="/master/create-exam/${course.id}" id="addExam">add exam</a>

<table>
    <tr>
        <th>id</th>
        <th>startTime</th>
        <th>endTime</th>
        <th>subject</th>
        <th>state</th>
        <th>modify</th>
        <th>show</th>
    </tr>
    <c:forEach var="exam" items="${course.examList}">

        <tr>

            <td>${exam.id}</td>
            <td>${exam.startTime}</td>
            <td>${exam.endTime}</td>
            <td>${exam.title}</td>
            <% Exam exam = (Exam) pageContext.getAttribute("exam");
                LocalDateTime currentTime = LocalDateTime.now();

                if (currentTime.isBefore(exam.getStartTime())) {

            %>
            <td><%=ExamState.NOT_STARTED.name()%></td>
            <td><a href="/master/change-examNotStarted?id=${exam.id}" class="not-started">modify</a></td>
            <td><a href="/master/exam/${exam.id}">show</a></td>
            <%
            } else if (currentTime.isAfter(exam.getStartTime()) && currentTime.isBefore(exam.getStartTime().toLocalDate().atTime(exam.getEndTime()))) {
            %>
            <td><%=ExamState.ON_PERFORMING.name()%></td>
            <td><a href="/master/change-examBeingHeld?examId=${exam.id}" class="modify">modify</a></td>
            <td><a href="/master/exam/${exam.id}">show</a></td>
            <%
            } else {
            %>
            <td><%=ExamState.FINISHED.name()%></td>
            <span class="myWrapper">
            <td><a href="#" class="finished">modify</a></td> </span>
            <td><a href="/master/exam/${exam.id}">show</a></td>
            <%
                }
            %>

        </tr>
    </c:forEach>

</table>

</body>
</html>