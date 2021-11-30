$(document).ready(function () {

    let allPreviousScores = [];

    $(window).on('load', function () {
        allPreviousScores = calculateAllScore();
    })


    let scoreNumber = $('#scoreNumber').val();

    $('input[type="text"]').keyup(function () {
        calculateAllScore();
    })

    function calculateAllScore() {
        let sum = 0;
        let scores = [];

        for (var index = 1; index <= scoreNumber; index++) {
            let data = $(`#score${index}`).val();
            sum = sum + +data;
            scores.push(data);
        }
        $('span').html(sum);
        return scores;

    }

    $('#scoreConfirm').click(function (e) {
        e.preventDefault();
        let myAllScore = calculateAllScore();


        let isValidData = true;
        for (var i in myAllScore) {

            if (isNaN(Number(myAllScore[i]))) {
                isValidData = false;
            }
        }
        if (!isValidData) {
            $('#error').html('you are dont valid data for number').css('display', 'block')
        } else if (myAllScore + "" === allPreviousScores + "") {

            $('#error').html('dont change any score of exam').css('display', 'block')
        } else {

            $('#error').css('display', 'none')
            allPreviousScores = myAllScore;
            jQuery.support.cors = true;
            axios.put('/master/change-score', myAllScore)
                .then(response => {

                    $('#message').css('display', 'block');
                    $('#message').html(`${response.data}`)

                    setTimeout(function () {
                        $('#message').css('display', 'none');
                    }, 4500)

                }).catch(error => {
                if (error.response) {
                    $('#error').html(error.response.data).css('display', 'block')
                }

            })

        }
    })
})