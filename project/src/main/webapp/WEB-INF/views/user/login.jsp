<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <style>
        input{
            position: static;
        }
      #myParrent{

          display: block;
          border: 1.5px solid black;
          border-radius: 3px;
          background-color: aliceblue;


      }
      .success-data{
          background-color: darkgrey !important;
          padding-top: 5px;
          padding-bottom: 5px;
      }
        #google-login:hover{
            background-color: crimson !important;
        }
    </style>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>

    <link rel="stylesheet" type="text/css"
          href="https://bootswatch.com/4/cerulean/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" />
    <script src="https://use.fontawesome.com/e344ad35b2.js"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>
<body>

<div class="container" >
    <form class="form-signin" method="post" action="/login" id="myParrent">
        <h2 class="form-signin-heading">Please sign in</h2>

        <c:choose>
        <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message !=null}">
            <div class="alert alert-danger" role="alert">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
        </c:when>
            <c:when test="${param.logout != null}">
            <div class="text-warning">
                You have been logged out.
            </div>
            </c:when>
            <c:when test="${changePasswordMessage!=null}">
                <div  style="border-radius: 5px;margin: 8px;padding-left: 4px" class="alret alert-success success-data">${changePasswordMessage}</div>
            </c:when>
            <c:when test="${registerSuccess!=null}">
                <div  style="border-radius: 5px;margin: 8px;padding-left: 4px" class="alret alert-success success-data">${registerSuccess}</div>
            </c:when>

        </c:choose>
        <p>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required
                   autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>

        <div style="margin-bottom: 6px" class="g-recaptcha"
             data-sitekey="6Lf8y50dAAAAAArhVxDi9OveuI8eRGm6QpbfkIFL">
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div style="text-align: center"><a href="/forgot-password">forgot password?</a> </div>
        <div id="google-login" class="alert-danger" style="text-align: center;margin-top: 20px;opacity: 1;padding: 5px 0"><a style="text-decoration: none;color: black !important;" href="/oauth2/authorization/google">Login with Google</a></div>
    </form>


</div>

</body>
</html>