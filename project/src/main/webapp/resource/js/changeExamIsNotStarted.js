$(document).ready(function () {

    $('#deleteExam').click(function (e) {

        e.preventDefault();

        var id = $(this).attr('href');
        $.ajax({

            method: "delete",
            url: "http://localhost:8080/master/delete-exam/" + id,
            success: function (resp) {

                $('.parent').html('');
                $('.shortMessage').css('display', 'block').html(resp)

                $('body').append(`<a href="/master/panel">back</a>`).delay(5000).trigger('click');

            },
            error: function (err) {

                $('.parent').html('');

                $('.shortMessage').before(`<p style="color: red;font-size: 25px;text-align: center">error ${err.status} </p>`)
                $('.shortMessage').css('display', 'block').html(err.responseText)
            }
        });
    })
    $('#changeExam').click(function (e) {

        var id = $('#changeExam').attr('href');
        e.preventDefault();

        $.ajax({
            method: 'get',
            url: 'http://localhost:8080/master/check/' + id,

            success: function (resp) {

                window.location.replace("http://localhost:8080/master/update-examNotStarted/"+id);
            },
            error: function (err) {

                $('.parent').html('');
                $('.shortMessage').before(`<p style="color: red;font-size: 25px;text-align: center">error ${err.status} </p>`)
                $('.shortMessage').css('display', 'block').html(err.responseText)
            }

        })
    })
})