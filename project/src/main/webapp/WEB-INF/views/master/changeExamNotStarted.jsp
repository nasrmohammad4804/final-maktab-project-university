<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/changeExamNotStarted.css">
</head>
<body>

<div class="shortMessage"> <span>exam successfully deleted</span></div>
<div class="parent">
    <div><a id="deleteExam" href="${examId}">delete exam</a></div>
    <div><a  id="changeExam" href="/master/update-examNotStarted/${examId}">change exam</a></div>
    <div><a href="${examId}" id="addFromQuestionBank">add question from questionBank</a></div>
    <div><a  id="addNewQuestion" href="/master/new-question">add new question </a></div>
    <div><a href="/master/question-all" id="allQuestion">show all question</a></div>
</div>
<p id="not-found-message">
    dont find same examQuestion in questionBank</p>
<table id="questionBank">
    <tr>

            <th colspan="2"><input type="text" placeholder="title" id="title" /></th>
            <th colspan="2"><input id="mySubmit" type="submit" value="search"></th>
    </tr>
    <tr >
        <th>id</th>
        <th>title</th>
        <th colspan="2">questionText</th>

    </tr>
    <tbody id="mytBody">

    </tbody>

</table>
<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../resource/js/changeExamIsNotStarted.js"></script>
</body>
</html>