
$(document).ready(function (){

    $('#resetPassword').click(function (e){
        e.preventDefault();
        var myPassword= $('#password').val();
        var confirmPassword=$('#confirmPassword').val();

        if(myPassword===confirmPassword){
            $('#samePasswordError').css('display','none');
            $('#myForm').submit();

        } else {
            $('#samePasswordError').css('display','block');
            $('#samePasswordError').html('your password dont match ').css({
                fontSize:'18px',
                color:'red',
                textAlign:'center',
                marginTop:'7px'

            })

        }
    })
})

