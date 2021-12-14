
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../resource/css/forgotPasswordForm.css">
</head>
<div class="card login-form">
    <div class="card-body">
        <h3 class="card-title text-center">Reset password</h3>

        <div class="card-text">
            <form th:action="@{/forgot_password}" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">Enter your email address and we will send you a link to reset your password.</label>
                    <input id="exampleInputEmail1" type="email" class="form-control form-control-sm" placeholder="Enter your email address" required name="email">
                </div>

                <button type="submit" class="btn btn-primary btn-block">Send password reset email</button>
                <div th:if="${error != null}">
                    <p class="text-danger">${error}</p>
                </div>
            </form>
        </div>
    </div>
</div>