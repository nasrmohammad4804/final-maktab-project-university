<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>add new descriptive question</title>
   <link rel="stylesheet" href="../../../../resource/css/question/addNewDescriptiveQuestion.css">
</head>
<body>

<h3>add new descriptive question</h3> <hr>
<div class="parent">
    <form action="/master/new-descriptive-question" method="post" >
        <div class="section">
            <label for="title">enter title:</label>
            <input id="title" type = "text" name = "title"  maxlength = "25" required />
        </div>
        <div class="section" id="question">
            <label for="questionContext">enter question context: </label>

            <textarea name="questionText" id="questionContext" required></textarea>
        </div>
        <div class="section">
            <input type="submit" value="confirm" >
            <a href="#" id="cancel">cancel</a>
        </div>
    </form>
</div>
</body>
</html>