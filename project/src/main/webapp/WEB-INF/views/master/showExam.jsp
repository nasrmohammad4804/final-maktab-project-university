<%@ page import="ir.maktab.project.domain.dto.ExamDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/showExam.css">
</head>
<body>

<div class="parent">
    <div class="section"><span class="title">id: </span> ${exam.id}</div>
    <% ExamDTO examDTO= (ExamDTO) request.getAttribute("exam");%>
    <div class="section" ><span class="title">title: </span>${exam.title} </div>
    <div class="section"><span class="title">startTime: </span> ${exam.startTime}</div>
    <div class="section" ><span class="title">endTime: </span>
        <%=examDTO.getStartTime().toLocalDate().atTime(examDTO.getEndTime())%></div>

</div>

</body>
</html>