<%@ page import="ir.maktab.project.domain.Student" %>
<%@ page import="ir.maktab.project.domain.Exam" %>
<%@ page import="ir.maktab.project.domain.embeddable.StudentExamState" %>
<%@ page import="ir.maktab.project.domain.enumeration.ExamState" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.function.Predicate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/student/allExam.css">
</head>
<body>
<h1>${course.name} lesson</h1>
<hr>
<h2>class exam</h2>

<hr style="height: 5px; background-color: rgb(207, 80, 105); border-radius: 5px;">
<div id="parent">
    <c:forEach var="exam" items="${course.examList}">
        <div>

            <img src="https://el-13.yazd.ac.ir/theme/image.php/moove/quiz/1625424831/icon" aria-hidden="true" alt="">

            <% Student student =(Student) request.getAttribute("student");
                Exam myExam = (Exam) pageContext.getAttribute("exam");

                Optional<StudentExamState> studentExamState = student.getStudentExamStates().stream().filter
                        (x -> x.getExamId().equals(myExam.getId())).findFirst();

                if (studentExamState.isEmpty() && examIsAccess(myExam) ) {

            %>
            <%--  if student already attend and dont confrim exam and exam until have time he can attend in exam  --%>
            <span><a href="/student/participate-exam?id=${exam.id}">${exam.title}</a></span>
            <%
            } else if ( studentExamState.isPresent() && studentExamState.get().getExamState().equals(ExamState.TRYING) && examIsAccess(myExam)) {
            %>
            <span><a href="/student/continue-exam?id=${exam.id}">${exam.title}</a></span>

            <%
            } else {
            %>
            <span><a href="/student/exam/mode?id=${exam.id}">${exam.title}</a></span>
            <%
                }
            %>


        </div>
        <%!
            public boolean examIsAccess(Exam myExam){
                LocalDateTime currentTime=LocalDateTime.now();
                Predicate<LocalDateTime> predicate1= ( ( currentDate) -> myExam.getStartTime().isEqual(currentDate)   );
                Predicate<LocalDateTime> checkStartTime= predicate1.or( (currentDate) -> currentDate.isAfter(myExam.getStartTime()) );

                Predicate<LocalDateTime> predicate2= ( ( currentDate) -> myExam.getStartTime().toLocalDate().atTime(myExam.getEndTime()).isEqual(currentDate)   );
                Predicate<LocalDateTime> checkEndTime= predicate2.or( (currentDate) -> currentDate.isBefore(myExam.getStartTime().toLocalDate().atTime(myExam.getEndTime())));
            return checkStartTime.test(currentTime) && checkEndTime.test(currentTime);
            }
        %>
    </c:forEach>

</div>

<hr style="height: 5px; background-color: rgb(207, 80, 105); border-radius: 5px;">

</body>
</html>
