<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../../resource/css/getVerificationCode.css">
</head>
<body>

<div class="d-flex justify-content-center align-items-center container">
    <div class="card py-5 px-3">
        <h5 class="m-0">Mobile phone verification</h5><span class="mobile-text">Enter the code we just send on your mobile phone <b class="text-danger">${user.phoneNumber}</b></span>
        <div class="d-flex flex-row mt-5"><input m id="input1" type="text" class="form-control "  maxlength="1" autofocus=""><input maxlength="1" id="input2" type="text" class="form-control"><input maxlength="1" id="input3" type="text" class="form-control"><input maxlength="1" id="input4" type="text" class="form-control">
            <input id="input5" type="text" class="form-control"></div>
        <div class="text-center mt-5" id="submit-data" style="width: 80px;"> <a href="">confirm</a></div>
        <div id="dont-recive" class="text-center mt-5"><span class="d-block mobile-text">Don't receive the code?</span><span class="font-weight-bold text-danger cursor">Resend</span></div>
        <div id="verificationError" class="text-center mt-5"></div>
    </div>
</div>

<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
</body>
<script type="text/javascript">
    $(document).ready(function(){

        var verification="";

        $('#submit-data').click(function (e){
            verification="";
            e.preventDefault();
            getVerificationCode();
            if(verification.length!==5){
                $('#verificationError').css('display','block')
                .html('verification is not valid must 5 digit');

            }
            else {
                let verificationCode=${verificationCode};
                var user={
                    "firstName":"${user.firstName}",
                    "lastName":"${user.lastName}",
                    "userName":"${user.userName}",
                    "password":"${user.password}",
                    "phoneNumber":"${user.phoneNumber}",
                    "userType":"${user.userType}"
                };
                console.log(verificationCode);
                $('#verificationError').css('display','none').html('');


                $.ajax({
                    async:false,
                    method:"post",
                    url:"http://localhost:8080/register/"+verification+"?verification-code="+verificationCode,
                    contentType: "application/json",
                    data:JSON.stringify(user),
                    success:function (resp){
                        console.log(resp);
                        window.location.replace("http://localhost:8080/login?success-message=true")
                    },
                    error:function (resp){
                        $('#verificationError').css('display','block')
                            .html(resp.responseText);
                        console.log('error')
                    }
                })
            }
        })

        function  getVerificationCode(){
            for(var i=1; i<=5; i++){
                verification+=$('#input'+i).val();
            }

        }

    })
</script>
</html>