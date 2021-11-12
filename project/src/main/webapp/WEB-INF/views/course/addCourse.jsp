<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
  <link rel="stylesheet" href="../../../resource/css/addCourse.css">
</head>
<body>


</form>

<form action="/course/find-master" method="post">
    <div>
        <label for="">courseName:</label>
        <input type="text" name="name" id="" required>
    </div>
    <div>
        <label for="">courseNumber:</label>
        <input type="text" name="courseNumber"  required>
    </div>

    <div>
        <label for="">startCourse:</label>
        <input type="date" name="courseStartedDate" required>
    </div>
    <div>
        <label for="">endCourse:</label>
        <input type="date" name="courseFinishedDate" required>
    </div>


    <div class="last-div">

        <input  type="submit" value="select-master" id="mySubmit">

    </div>
</form>

</body>
</html>