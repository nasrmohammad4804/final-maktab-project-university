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
        #time{
            display: none;
        }
    </style>
</head>

<body>

<div class="header">

    <h2 style="margin-top: 0;">${exam.title}</h2>
    <p style="font-size: 20px;">${exam.description}</p>

    <hr>
    <div class="parent">
        <div>
            <p style="text-align: center;">this exam closed of ${exam.startTime} at ${exam.endTime} </p>
        </div>
        <div>
            <p style="color: red;text-align: center;">*note: you are allow to once attend in exam</p>
        </div>

        <input type="hidden" name="myExam" value="${exam.id}">
        <% Exam exam =(Exam) request.getAttribute("exam");
            LocalDateTime endTime=exam.getStartTime().toLocalDate().atTime(exam.getEndTime());
            System.err.println(endTime);
        %>
        <input type="hidden" name="endTime" value=<%=endTime%>>
        <div style="text-align: center;">
            <a id="continue" href="">continue exam</a>
        </div>
    </div>
</div>
<div id="myHeader">
    <div id="remaining">    remaining time: <span style="color: crimson" id="time"></span></div>
    <div id="content" style=" width: 500px;background-color: rgba(243, 237, 237,.8);text-align: center;padding: 5px 0;border-radius: 5px;position: relative;margin: 0 auto">
        <span id="questionNumber"></span>
        <div id="questionText"></div>
        <div id="answer"></div>
        <div id="finish"> <a href="" >finish exam</a> </div>

        <span style="position: absolute;left: 5px;bottom: 10px;"   > <a  href="#"  id="next" class="page-adjuster">&#8249;</a></span>
        <span  style="position: absolute;right: 5px ; bottom: 10px;" > <a href="#" id="previous" class="page-adjuster">&#8250;</a></span>

    </div>
</div>

<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="../../../resource/js/axios.min.js"></script>
<script src="../../../resource/js/onlineExam.js" ></script>
<script src="../../../resource/js/continueExam.js"></script>

</body>

</html>