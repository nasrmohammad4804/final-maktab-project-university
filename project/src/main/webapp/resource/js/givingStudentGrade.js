$(document).ready(function () {

    class DescriptiveAnswer {

        constructor(questionId, score, answerText) {
            this.questionId = questionId;
            this.score = score;
            this.answerText = answerText;

        }
    }

    $('.get-date').click(function (e) {
        e.preventDefault();
        setBackGroundForPage();

        let index = 0;
        let examId = $('#examId').html();
        let studentId = $(this).find('input[name="studentId"]').val();

        let allDescriptiveQuestion;
        let allAnswerOfStudent;
        let allAnswer = [];

        function getData(examId, studentId) {


            $.ajax({
                method: "get",
                async: false,
                url: `http://localhost:8080/master/to-score?studentId=${studentId}&examId=${examId}`,
                success: function (response) {
                    allDescriptiveQuestion = response.questionDTOList;
                    allAnswerOfStudent = response.descriptiveAnswerDTOList;

                    for (var i = 0; i < allAnswerOfStudent.length; i++) {
                        for (var j = 0; j < allDescriptiveQuestion.length; j++) {
                            if (allAnswerOfStudent[i].questionId === allDescriptiveQuestion[j].id) {
                                allAnswer[j] = allAnswerOfStudent[i];
                                break;
                            }
                        }
                    }
                },
                error: function (err) {
                    console.log('myError')
                }
            })
        }

        $('#first-header').css('display', 'none');
        $('#main-body').css('display', 'block');
        getData(examId, studentId);
        $.when(getData(examId, studentId)).done(myAdjusterPage())


        function myAdjusterPage() {
            $('#myAnswer').html('');
            $('#myQuestionNumber').html('question  ' + (index + 1));
            $('#myQuestionText').html(allDescriptiveQuestion[index].questionText)
            if (index + 1 === allDescriptiveQuestion.length) {
                $('#record-score').css('display', 'block')
                $('#myNext').css('display', 'none');
            } else {
                $('#record-score').css('display', 'none');
                $('#myNext').css('display', 'block');
            }

            if (index === 0)
                $('#myPrevious').css('display', 'none');
            else $('#myPrevious').css('display', 'block');

            if (typeof allAnswer[index] !== "undefined") {

                let textValue = allAnswer[index].answerText;
                let scoreValue = allAnswer[index].score;

                $('#myAnswer').append(`<div>
                <textarea  readonly="readonly" style="resize: none;" id="disable-answer-text" cols="58" rows="13" style="text-align: left;font-size: 18px">
                ${textValue}</textarea></div>`)


                $('#score-value').val(scoreValue)
                console.log($(allDescriptiveQuestion[index].score));
                $('#max-score-value').attr('value', allDescriptiveQuestion[index].score)

            } else {

                let scoreValue = 0;
                $('#myAnswer').append(`<div>
                  <textarea disabled style="resize: none;" id="disable-answer-text" cols="58" rows="13" ></textarea></div>`)

                $('#score-value').val(scoreValue)


                allAnswer[index] = new DescriptiveAnswer(allDescriptiveQuestion[index].id, scoreValue, "");
                $('#max-score-value').attr('value', allDescriptiveQuestion[index].score)

            }

        }

        function confirmCurrentPage() {

            allAnswer[index].score = $('#score-value').val();
        }

        async function sendAllScoreGiven() {
            axios.put(`/master/to-score/${studentId}/${examId}`, allAnswer)
                .then(response => {

                    $('#message').css('display', 'block');
                    $('#message').html(`${response.data}`)

                    setTimeout(function () {
                        $('#message').css('display', 'none');
                    }, 4500)
                })
        }

        $('#myNext').click(function (e) {
            e.preventDefault();
            confirmCurrentPage();
            index++;
            myAdjusterPage();
        })
        $('#myPrevious').click(function (e) {
            e.preventDefault();
            confirmCurrentPage();
            index--;
            myAdjusterPage();

        })
        $('#record-score a').click(function (e) {
            e.preventDefault();

            confirmCurrentPage();
            sendAllScoreGiven();

        })
    })

})
