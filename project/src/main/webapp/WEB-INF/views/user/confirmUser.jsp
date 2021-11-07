<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/confirmUser.css">
</head>
<body>

<div class="parent">
    <h2>profile</h2>
    <p>firstName: <span>${unapprovedUser.firstName}</span> </p>
    <p>lastName:  <span>${unapprovedUser.lastName}</span></p>
    <p>userName:  <span>${unapprovedUser.userName}</span></p>
    <p>role:   <span>${userType}</span></p>


    <c:form action="/check-confirmation"  method="post">

        <input type="submit"  class="myLink" id="confirmButton" value="confirm-user">
        <input type="hidden" value="${unapprovedUser.id}" name="id">
        <a  href="/manager-panel" class="myLink" id="cancelButton">cancel</a>

    </c:form>

</div>

</body>
</html>