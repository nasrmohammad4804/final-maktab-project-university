$(document).ready(function(){

    $('.delete').click(function(e){

        e.preventDefault();



        var address= $(this).attr('href');
        var studentId = address.substring(address.lastIndexOf("/")+1,address.length);
        console.log(address);
        console.log(studentId);
        $.ajax({

            method:"delete",
            url:`http://localhost:8080/course/delete-student/${studentId}`,
            success:function(resp){



                $('#message').css('display','block');
                $('#message').html(`${resp}`)

                setTimeout(function(){
                    $('#message').css('display','none');
                },5000)

            },
            error:function(err){
                console.log(err);
                $('body').append(err);
            }
        })


    })

})