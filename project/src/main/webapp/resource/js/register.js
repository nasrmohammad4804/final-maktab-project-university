$(document).ready(function () {

    $('#mySubmit').click(function (e) {


        e.preventDefault();

        let usName = $('#myUserName').val();
        let password = $('#myPassword').val();

        console
        var test1 = usName.length >= 8;
        var test2 = password.length >= 8;

        if (usName === "") {

            $('#userNameError').addClass('red').html('Enter userName');
            $('#myUserName').css('border-color', 'red')
        } else if (usName.length >= 1) {
            $('#userNameError').removeClass('red').html('');
            $('#myUserName').css('border-color', 'black')
        }


        if (usName.length >= 1 && usName.length < 8) {
            $('#userNameError').addClass('red').html('Use 8 characters or more for your userName');
            $('#myUserName').css('border-color', 'red')
        } else if (test1) {
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
            $('#passwomyPasswordrdError').css('border-color', 'red');
        } else if (test2) {
            $('#passwordError').removeClass('red').html('');
            $('#myPassword').css('border-color', 'black');
        }


        if (test1 && test2) {

            $('#myForm').submit();
        }


    })

})
