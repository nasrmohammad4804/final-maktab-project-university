<%@ page import="ir.maktab.project.domain.Question" %>
<%@ page import="ir.maktab.project.domain.ExamQuestion" %>
<%@ page import="ir.maktab.project.domain.TestAnswer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../../resource/css/question/showAllQuestionExam.css">
</head>
<body>

<p class="header-title">question items of ${exam.title} exam</p>

<table>

    <thead>
    <tr id="header">
        <th>id</th>
        <th>title</th>
        <th>questionText</th>
        <th>questionType</th>
        <th>score</th>
    </tr>
    </thead>

    <tbody>
    <%!int scoreNumber; %>
    <c:forEach var="question" items="${exam.examQuestionList}">

        <%scoreNumber++;%>
        <tr>
            <td>${question.id}</td>
            <td>${question.title}</td>
            <td>${question.questionText}</td>
            <% ExamQuestion examQuestion = (ExamQuestion) pageContext.getAttribute("question");
                if (examQuestion.getAnswer() instanceof TestAnswer) {

            %>
            <td>Test</td>
            <%
            } else {
            %>
            <td>Descriptive</td>
            <%
                }
            %>
            <td><input type="text" value="${question.score}" id="score<%=scoreNumber%>"></td>
        </tr>

    </c:forEach>

    </tbody>
    <input type="hidden" value="<%=scoreNumber%>" id="scoreNumber">
    <%scoreNumber = 0;%>
    <tfoot>
    <th colspan="5">
        <div style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">maximum score: <span
                style="color: darkred;font-size: 17px;">15</span></div>

        <a href="" id="scoreConfirm">confirm score</a>
        <div id="error"></div>
    </th>
    </tfoot>
</table>
<div id="message"></div>
<script src="../../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../../resource/js/axios.min.js"></script>
<script src="../../../../resource/js/showAllQuestionExam.js"></script>
</body>
</html>