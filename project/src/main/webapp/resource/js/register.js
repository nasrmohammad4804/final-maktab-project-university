$(document).ready(function () {

    $('#mySubmit').click(function (e) {


        e.preventDefault();

        $('#passwordError').css('display','block');
        $('#userNameError').css('display','block');
        let usName = $('#myUserName').val();
        let password = $('#myPassword').val();

        var test1 = isUserNameValid();
        var test2 = password.length >= 8;


        function isUserNameValid(){
            let pattern=/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;
          return   pattern.test(usName);
        }

        if(!test1){
                $('#userNameError').addClass('red').html('your email not valid');
                $('#myUserName').css('border-color', 'red');
        }
        else {
            $('#userNameError').removeClass('red').html('');
            $('#myUserName').css('border-color', 'black');
        }

        if (password === "") {
            $('#passwordError').addClass('red').html(`Enter password`)
            $('#myPassword').css('border-color', 'red')
        } else if (password.length >= 1) {
            $('#passwordError').removeClass('red').html('');
            $('#myPassword').css('border-color', 'black');
        }


        if (password.length >= 1 && password.length < 8) {
            $('#passwordError').addClass('red').html('Use 8 characters or more for your password');
            $('#myPassword').css('border-color', 'red');
        } else if (test2) {

            $('#passwordError').removeClass('red').html('').css('display','none');
            $('#myPassword').css('border-color', 'black');

            $('#myForm').submit();
        }

        if (test1 && test2) {

            $('#myForm').submit();
        }
    })

})
