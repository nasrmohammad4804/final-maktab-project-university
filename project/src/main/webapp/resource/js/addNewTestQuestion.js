$(document).ready(function () {

    class Option {
        constructor(content, isAnswered) {
            this.content = content;
            this.isAnswered = isAnswered;
        }
    }

    let defaultOptionNumber = 2;
    let isTestComplete = true;
    let isOptionRecognized = false;

    $('#remove').hover(function () {
        if (defaultOptionNumber === 2) {


            $('#remove').css('cursor', 'not-allowed');
            $('#remove a').css('pointer-events', 'none')
        } else {
            $('#remove').css('cursor', 'auto');
            $('#remove a').css('pointer-events', 'all')
        }

    }, function () {

    })

    $('#add').click(function (e) {
        e.preventDefault();
        defaultOptionNumber++;
        $('.parent').append(function () {
            return $(`<div class="section" >
            <label for="btn${defaultOptionNumber}">${defaultOptionNumber}:</label>
            <input type="radio" name="myOption" id="btn${defaultOptionNumber}" value="false">
            <input type="text" id="test${defaultOptionNumber}">
        </div>`)
        })
        $('input[name="myOption"]').click(function () {
            $('input[name="myOption"]').attr('value', 'false')


            $(this).attr('value', true);
            isOptionRecognized = true;
        })

    })

    $('#remove a').click(function (e) {
        e.preventDefault();
        defaultOptionNumber--;
        $('.parent div').last().remove();
    })

    $('input[name="myOption"]').click(function () {
        $('input[name="myOption"]').attr('value', 'false')


        $(this).attr('value', true);
        isOptionRecognized = true;
    })


    $('#confirmTest').click(function (e) {
        e.preventDefault();
        var myOptions = [];
        let questionText = $('#questionText').val();
        let title = $('#title').val();
        let sum = 0;
        for (var index = 1; index <= defaultOptionNumber; index++) {
            let content = $('#test' + index).val();
            if (content === "" || content === null) {
                isTestComplete = false;
                break;
            } else isTestComplete = true;

            let isAnswered = $('#btn' + index).val();

            let option = new Option(content, isAnswered);
            myOptions.push(option);

        }

        if ((questionText === "") || (title === ""))
            $('#myError').html(`<span>at first fill questionText & title</span>`).css({
                color: 'red'
            })

        else if (isOptionRecognized === false) {
            $('#myError').html('<span>you should choose option</span>').css({
                color: 'red'
            })


        } else if (isTestComplete === false) {
            $('#myError').html(' <span> you should complete all content test or dont same name for test</span>').css({
                color: 'red'
            })


        } else {
            var json = {
                question: {
                    questionText: questionText,
                    title: title
                },

                option: myOptions
            }

            $.ajax({
                url: `http://localhost:8080/master/new-test-question`,
                method: 'post',
                contentType: "application/json",
                data: JSON.stringify(json),
                success: function (resp) {

                    window.location.replace("http://localhost:8080/master/result-add-test-question");
                },
                error: function () {
                    console.log('myerror')
                }
            })
        }

    })
})