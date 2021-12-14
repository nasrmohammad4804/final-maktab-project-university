
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../resource/css/resetPasswordForm.css">
</head>
<div class="card login-form myParrent">
    <div class="card-body">
        <h3 class="card-title text-center" style="color: white;">Reset password</h3>

        <div class="card-text">
            <form method="post" action="/reset-password" id="myForm">
                <div class="form-group">
                    <label for="password">NEW PASSWORD</label>
                    <input id="password" type="password" class="form-control form-control-sm" placeholder="Enter a new password" required name="password">
                </div>
                <input type="hidden" name="myToken" value="${token}" />

                <div class="form-group">
                    <label for="confirmPassword">CONFIRM NEW PASSWORD</label>
                    <input id="confirmPassword" type="password" class="form-control form-control-sm" placeholder="Confirm your new password" required oninput="checkPasswordMatch(this);">
                </div>

                <button type="submit" class="btn btn-primary btn-block" id="resetPassword">Reset my password</button>
                <div id="samePasswordError"></div>
            </form>
        </div>
    </div>
</div>
<script src="../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../resource/js/resetPasswordForm.js"></script>



