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
    $('#addFromQuestionBank').click(function (e) {

        e.preventDefault();
        $('#not-found-message').css('display', 'none')
        $('#questionBank').css('display', 'table');
        $('#mySubmit').click(function (t) {
            t.preventDefault();
            let title = $('#title').val();
            $.ajax({
                url: 'http://localhost:8080/master/all-question-bank?title=' + title,
                method: "get",
                success: function (response) {
                    if (response.length === 0) {
                        $('table').css('display', 'none');
                        $('#not-found-message').css('display', 'block')
                    } else {
                        $('table').css('display', 'table');
                        $('#not-found-message').css('display', 'none');
                    }
                    console.log(response.length)
                    $('#mytBody').html('');
                    let id;
                    for (var index = 0; index < response.length; index++) {
                        id = response[index].id;

                        $('#mytBody').append(`
                    <tr>
                        <td>${id}</td>
                        <td>${response[index].title}</td>
                        <td>${response[index].questionText}</td>
                        <td> <button class="myButton" value="${id}" >add</button></td>
                        </tr>
                        `)

                    }
                    $(`.myButton`).click(function () {

                        let questionId = $(this).val();
                        $(`<form action="/master/add-question-bank" method="post">
                            <input type="hidden" name="questionId" value='${questionId}'/>
                        </form>`).appendTo('body').submit();
                        console.log(index)

                    })
                },
            })
        })
    })
})