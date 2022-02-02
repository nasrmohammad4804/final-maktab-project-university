$(document).ready(function () {


    $('#submit-searching').click(function (e) {
        e.preventDefault();

        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var role = $('#role').val();

        var json = {
            firstName: firstName,
            lastName: lastName,
            role: role
        }

        $.ajax({
            url: `http://localhost:8080/search-users`,
            method: "post",
            data: JSON.stringify(json),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {

                if (response.length === 0) {
                    $('table').css('display', 'none');
                    $('#not-found-message').css('display', 'block')
                } else {
                    $('table').css('display', 'table');
                    $('#not-found-message').css('display', 'none');
                }

            /*    $('#my-tbody').html('');


                for (var index = 0; index < response.length; index++) {
                    console.log(response.length);
                    $('#my-tbody').append(`<tr id="${index}">

        <td>${response[index].firstName}</td>
        <td>${response[index].lastName}</td>
        <td>${response[index].userName}</td>
        </tr>`);

                    if (response[index].registerState === "CONFIRM")
                        $(`#${index}`).append(`<td> <a href=change-profile?id=${response[index].id} id=green>confirm</a></td>`)

                    else
                        $(`#${index}`).append(`<td> <a href=/confirm-user/${response[index].id} id=orange>waiting</a> </td>`)

                }*/

                //todo new logic for when search then show with pagination

            },
            error: function (err) {
                console.log(err);
            }
        });

    });
})
