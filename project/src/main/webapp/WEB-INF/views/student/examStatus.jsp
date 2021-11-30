<%@ page import="ir.maktab.project.domain.Exam" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/student/participateInExam.css">
    <style>

    </style>
</head>

<body>

<div class="header">

    <h2 style="margin-top: 0;">${exam.title}</h2>
    <p style="font-size: 20px;">${exam.description}</p>

    <% Exam exam =(Exam) request.getAttribute("exam");%>

    <hr>
    <div class="parent">
        <div>
            <p style="text-align: center;">this exam start at <%=exam.getStartTime()%> </p>
        </div>

        <div>
        <%if(LocalDateTime.now().isBefore(exam.getStartTime())){

            %>
            <p>this exam still dont started !!</p>
            <%
        }
        else if(LocalDateTime.now().isAfter(exam.getStartTime().toLocalDate().atTime(exam.getEndTime()))){
            %>
            <p>this exam is finished !!</p>
            <%
        }
        %>

        </div>

        <div style="text-align: center;">
            <a id="" href="/student/course?id=${cookie.courseId.value}">back to course</a>
        </div>
    </div>
</div>

</body>

</html>