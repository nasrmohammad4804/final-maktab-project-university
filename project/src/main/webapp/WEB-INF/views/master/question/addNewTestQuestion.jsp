<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../../resource/css/question/addNewTestQuestion.css">
</head>
<body>

<div id="master">

    <div class="header-parent">
        <div >
            <label for="questionText">questionText:</label>
            <input type="text" name="" id="questionText" cols="30" rows="2">
        </div>

        <div >
            <label  for="title">title:</label>
            <input type="text" id="title">
        </div>
        <div class="myLink">
            <a id="add" href="">add option to question</a>
        </div>
        <div class="myLink" id="remove" > <a href="">remove last option</a></div>

    </div>
    <div class="parent">
        <div class="section">
            <label for="btn1">1:</label>
            <input type="radio" name="myOption" id="btn1" value="false">
            <input type="text" id="test1" >
        </div>
        <div class="section" >
            <label for="btn2">2:</label>
            <input type="radio" name="myOption" id="btn2" value="false">
            <input type="text" id="test2">
        </div>

    </div>

    <div id="myConfirm"><a href="" id="confirmTest">confirm test</a></div>
    <div id="myError"></div>
</div>
<script src="../../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../../resource/js/addNewTestQuestion.js"> </script>
</body>
</html>