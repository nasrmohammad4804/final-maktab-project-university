<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ir.maktab.project.domain.dto.ExamWithStudentsDTO" %>
<%@ page import="ir.maktab.project.domain.dto.ExamDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>all student participated</title>
    <link rel="stylesheet" href="../../../resource/css/allStudentParticipated.css">
</head>
<body>
<% ExamWithStudentsDTO examWithStudentsDTO=(ExamWithStudentsDTO) request.getAttribute("examWithStudents");
    ExamDTO examDTO=examWithStudentsDTO.getExamDTO();
%>
<div id="first-header">
<h2 id="exam-information" >examId:<span id="examId"><%=examDTO.getId()%></span>  examTitle: <%=examDTO.getTitle()%> , examStartTime:<%=examDTO.getStartTime()%>
    , examEndTime:<%=examDTO.getStartTime().toLocalDate().atTime(examDTO.getEndTime())%></h2>
<hr>
<h2 >persons: <span>${examWithStudents.studentList.size()}</span>  </h2>
<table>
    <tr>

        <th colspan="4">list of all person participated</th>
    </tr>
        <input type="hidden" name="">
    <c:forEach var="student" items="${examWithStudents.studentList}">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.userName}</td>
            <td><a class="get-date" href="#" >set score
            <input type="hidden" value="${student.id}"  name="studentId">
            </a>

            </td>
        </tr>
    </c:forEach>

</table>
</div>

<div id="main-body">
    <div id="myContent" >
        <span id="myQuestionNumber"></span>
        <div id="myQuestionText"></div>
        <div id="maximum-score"></div>
        <div id="myAnswer"></div>
        <div id="score-parent">
        <div class="score" id="get-score">score : <input id="score-value" type="text" width="40px"> </div>
        <div class="score"> max-score : <input type="text" readonly="readonly" id="max-score-value" width="40px"> </div>
        </div>
        <div id="record-score"><a  href="#">record all score</a></div>

        <span style="position: absolute;left: 5px;bottom: 10px;"   > <a  href="#"  id="myNext" class="page-adjuster">&#8249;</a></span>
        <span  style="position: absolute;right: 5px ; bottom: 10px;" > <a href="#" id="myPrevious" class="page-adjuster">&#8250;</a></span>
    </div>


</div>
<div id="message"></div>



<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../resource/js/axios.min.js"></script>
<script src="../../../resource/js/onlineExam.js" ></script>
<script src="../../../resource/js/givingStudentGrade.js"></script>
</body>
</html>