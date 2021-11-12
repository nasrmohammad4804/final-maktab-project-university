<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{
            width: 100%;
            height: 100vh;
            background-image: url('https://safirsayee.com/wp-content/uploads/2021/04/2B-%CF%80ae%E2%88%9ENight_view-min.jpg');
            background-repeat: no-repeat;
            background-size: 100% 100%;
        }
        #myTextArea{
            resize: none;
            width: 96%;
            border-radius: 10px;

        }
        form{
            display: flex;
            flex-wrap: wrap;
            width: 600px;
            padding-left: 5px;

            background-color: rgb(175, 169, 169);
            align-content: flex-end;
            border-radius: 5px;


        }
        .red{

            color: red;
            font-size: 17px;
        }
        .myTime{
            flex-grow: 1;
        }
        #mySubmit{
            display: block;
            margin: 0 auto;
        }
        .myTime:nth-child(2){
            margin-bottom: 10px;
        }
        .myArea{
            margin-top: 5px;
            /* flex-grow: 1; */
            width: 80%;
        }
        .myHeader{
            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        }
        .previousData{

            width: 90%;
            text-align: center;
        }
        .test{
            display: flex;
            flex-wrap: wrap;
            margin: 5px;
        }
        .newTime{
            flex-grow: 1;
            margin: 5px;
        }
        .myButton{
            width: 100%;
            text-align: center;
        }
        .endTime{
            flex-grow: 2;
            margin: 5px;
        }

    </style>
</head>
<body>

<h2 class="myHeader">change exam witch not started</h2>     <hr>
<c:if test="${not empty error}">
    <span class="red">${error}</span>
</c:if>

<form action="/master/update-examNotStarted" method="post">

    <div class="myTime myArea "><textarea name="description" id="myTextArea" cols="30" rows="10" placeholder="newDescrption" required></textarea> </div>
    <div class="myTime previousData"> previos of time exam: <span>${exam.startTime}</span> &nbsp;&nbsp;</div> &nbsp;&nbsp;


    <div class="test myTime">

        <div class="newTime">
            <label for="newTime">newTime: </label>
            <input  id="newTime" type="datetime-local" name="startTime" required> </div>

        <div class="endTime">
            <label for="endTime">endTime: </label>
            <input id="endTime" type="time" required name="endTime">
        </div>
        <input type="hidden" value="${exam.id}" name="id">

        <div class="myButton">
            <input type="submit" value="confirm" id="mySubmit"> </div>
    </div>

    </div>

</form>
</body>
</html>