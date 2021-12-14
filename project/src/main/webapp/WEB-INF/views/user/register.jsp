<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>register</title>
    <link rel="stylesheet" href="../../../resource/css/register.css">
    <link rel="stylesheet" type="text/css"
          href="https://bootswatch.com/4/cerulean/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" />
    <script src="https://use.fontawesome.com/e344ad35b2.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

<div class="parent">
    <form action="/register" method="post"  id="myForm" >
        <input style="margin-top: 5px" class="myInput" type="text" name="firstName" id="myFirstName" placeholder="firstName" required>

        <input class="myInput"  type="text" name="lastName" id="myLastName" placeholder="lastName" required>
        <input class="myInput"  type="email" name="userName" id="myUserName" placeholder="email"  required >
        <div id="userNameError"></div>


        <input class="myInput" type="text" name="password" id="myPassword" placeholder="password" required>
        <div id="passwordError"></div>


        <div id="userType">
            <label for="master">master</label>
            <input type="radio" name="userType" value="MASTER" id="master" required>
            <label for="student">student</label>
            <input type="radio" name="userType" value="STUDENT" id="student" required>
        </div>
        <div style="margin-bottom: 6px" class="g-recaptcha"
             data-sitekey="6Lf8y50dAAAAAArhVxDi9OveuI8eRGm6QpbfkIFL">
        </div>
        <input class="myInput" type="submit" value="confirm" id="mySubmit">
    </form>
    <c:if test="${error !=null}">
        <div style="color: red;font-size: 18px" >${error}</div>
    </c:if>

</div>
<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../resource/js/register.js"></script>

</body>

</html>