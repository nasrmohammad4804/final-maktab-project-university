$('#continue').click(function (t) {

    setBackGroundForPage();
    t.preventDefault();

    let examId = $('input[name="myExam"]').val();
    let endTime = $('input[name="endTime"]').val();
    let allQuestion;
    let index = 0;
    let answerType;
    let allAnswer = [];
    let studentAnswer;
    let answerText = "";

    let courseId = $.cookie("courseId");

    function ajax1() {
        $.ajax({
            method: "get",
            url: `http://localhost:8080/student/all-question/${examId}`,
            async: false,
            success: function (response) {
                console.log(response);

                allQuestion = response;
                for (var i = 0; i < allQuestion.length; i++) {
                    allQuestion[i].masterAnswer = allQuestion[i].answerList[0];
                }


            },
            error: function () {
                console.log('error')
            }

        })

    }

    function ajax2() {
        $.ajax({
            method: "get",
            url: `http://localhost:8080/student/all-answers/${examId}`,
            async: false,
            success: function (response) {
                console.log(response);
                console.log(allQuestion)
                for (var i = 0; i < response.length; i++) {

                    for (var j = 0; j < allQuestion.length; j++) {
                        if (allQuestion[j].id === response[i].question.id) {

                            allAnswer[j] = response[i];
                            break;
                        }
                    }

                }

            },
            error: function () {
                console.log('error')
            }

        })

    }


    $.when(ajax1(), ajax2()).done(myAdjusterPage());


    $('.header').css('display', 'none');
    $('#myHeader').css('display', 'block')
    $('#time').css('display', 'inline')
    var countDownDate = new Date($('input[name="endTime"]').val()).getTime();

    var x = setInterval(function () {

        var now = new Date().getTime();

        var distance = countDownDate - now;
        var hours = str(Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)));

        var minutes = str(Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)));
        var seconds = str(Math.floor((distance % (1000 * 60)) / 1000));


        document.getElementById("time").innerHTML = hours + " : " +
            minutes + " : " + seconds;

        if (distance < 0) {
            clearInterval(x);

            saveCurrentPage('increase');
            window.location.replace("http://localhost:8080/student/course?id=" + courseId);

        }
    }, 1000);


    function str(data) {
        data = data.toString();

        if (data.length === 1)
            return "0" + data;

        return data;
    }

    function addNewQuestion() {
        $('#questionText').html(allQuestion[index].questionText)
        $('#questionNumber').html('question ' + (index + 1))
        if (allQuestion[index].masterAnswer.hasOwnProperty('options')) {
            answerType = 'test';
            for (var i = 0; i < allQuestion[index].masterAnswer.options.length; i++) {
                let option = allQuestion[index].masterAnswer.options[i].content;
                $('#answer').append(`<div>
            <input type="radio" name="myOption" value="${option}"> <span>${option}</span>
        
</div>`)

            }
        } else {
            answerType = 'descriptive';
            $('#answer').html(`<div>
   
        <textarea style="resize: none;" id="myAnswerText" cols="30" rows="10"></textarea>
</div>`)

        }
    }


    function getAnswerCorrect() {

        studentAnswer = $('input[name="myOption"]:checked').val();
    }

    function saveCurrentPage(type) {

        if (answerType === 'test') {
            getAnswerCorrect();

            for (var i = 0; i < allQuestion[index].masterAnswer.options.length; i++) {
                if (allQuestion[index].masterAnswer.options[i].content === studentAnswer)
                    allQuestion[index].masterAnswer.options[i].isAnswered = true;
                else allQuestion[index].masterAnswer.options[i].isAnswered = false;
            }

            let testAnswer = allQuestion[index].masterAnswer;
            allAnswer[index] = testAnswer;

            if (typeof studentAnswer === "undefined") {
                if (type === 'increase') {
                    index++;
                    myAdjusterPage();
                } else if (type === "decrease") {
                    index--;

                    myAdjusterPage();
                }

            } else {
                axios.post('/student/add-test-answer?questionId=' + allQuestion[index].id, testAnswer)
                    .then(response => {
                        if (type === 'increase') {
                            index++;
                            myAdjusterPage();
                        } else if (type === "decrease") {
                            index--;

                            myAdjusterPage();
                        }

                    }).catch(err => {

                })
            }

        } else {
            let question = allQuestion[index];
            answerText = $('#myAnswerText').val();
            let descriptiveAnswer = question.masterAnswer;
            descriptiveAnswer.answerText = answerText;
            allAnswer[index] = descriptiveAnswer;

            if (answerText === "") {
                if (type === 'increase') {
                    index++;
                    myAdjusterPage();
                } else if (type === "decrease") {
                    index--;

                    myAdjusterPage();
                }

            } else {
                axios.post('/student/add-descriptive-answer?questionId=' + allQuestion[index].id, descriptiveAnswer)
                    .then(() => {
                        if (type === 'increase') {
                            index++;
                            myAdjusterPage();
                        } else if (type === "decrease") {
                            index--;

                            myAdjusterPage();
                        }
                    });
            }

        }
    }

    function myAdjusterPage() {

        $('#answer').html('');
        $('#questionNumber').html('question ' + (index + 1))
        if (index + 1 === allQuestion.length) {
            $('#finish').css('display', 'block')
            $('#next').css('display', 'none');
        } else {
            $('#finish').css('display', 'none');
            $('#next').css('display', 'block');
        }

        if (index === 0)
            $('#previous').css('display', 'none');
        else $('#previous').css('display', 'block');

        if (typeof allAnswer[index] !== "undefined") {
            $('#questionText').html(allQuestion[index].questionText)
            if (allQuestion[index].masterAnswer.hasOwnProperty('options')) {
                answerType = 'test';
                for (var i = 0; i < allAnswer[index].options.length; i++) {
                    let option = allAnswer[index].options[i].content;
                    if (allAnswer[index].options[i].isAnswered === true) {
                        $('#answer').append(`<div>
            <input type="radio" name="myOption" checked value="${option}"> <span>${option}</span></div>`)

                    } else {
                        $('#answer').append(`<div>
            <input type="radio" name="myOption" value="${option}"> <span>${option}</span></div>`)
                    }

                }
            } else {
                answerType = 'descriptive';

                let textValue = allAnswer[index].answerText;
                if (textValue === "null")
                    textValue = "";

                $('#answer').append(`<div>
                    
        <textarea style="resize: none;" id="myAnswerText" cols="30" rows="10" >${textValue}</textarea>
</div>`)

            }

        } else {

            addNewQuestion();
        }
    }

    $('#next').click(function (e) {
        e.preventDefault();
        saveCurrentPage('increase');

    })
    $('#previous').click(function (e) {
        e.preventDefault();
        saveCurrentPage('decrease');
    })
    function finishExam(){
        var data = {
            examId: examId,
            examState: 'FINISHED'
        }
        axios.put('/student/change-exam-state', data)
            .then(response => {
                console.log('ok')
            })
    }
    $('#finish').click(function (e) {
        e.preventDefault();
        finishExam();


        saveCurrentPage('lastPage');
        window.location.replace("http://localhost:8080/student/course?id=" + courseId);

    })
})