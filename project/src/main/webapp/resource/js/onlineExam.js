class TestAnswer {
    constructor(options, questionId) {
        this.options = options;
        this.questionId = questionId;
    }
}

class DescriptiveAnswer {
    constructor(answerText, questionId) {
        this.answerText = answerText;
        this.questionId = questionId;
    }
}

function setBackGroundForPage() {

    $('body').css({
        'background-repeat': 'no-repeat',
        'background-image': 'url(https://cdn.nody.ir/files/2021/07/12/nody-%D8%B9%DA%A9%D8%B3-%D8%AA%D8%B5%D9%88%DB%8C%D8%B1-%D8%B2%D9%85%DB%8C%D9%86%D9%87-%D8%AA%D8%B1%D8%B3%D9%86%D8%A7%DA%A9-%D8%AF%D8%AE%D8%AA%D8%B1%D8%A7%D9%86%D9%87-1626047865.jpg)',
        width: '100%',
        height: '100vh',
        backgroundRepeat: 'none',
        backgroundSize: '100% 100%'
    })
}

$('#participate').click(function (e) {

    setBackGroundForPage();

    e.preventDefault();
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

    function okForAjax1() {

        console.log('ok')
    }

    $.when(ajax1()).done(okForAjax1);
    console.log(allQuestion)

    $('.header').css('display', 'none');
    $('#myHeader').css('display', 'block')
    $('#time').css('display', 'inline')
    var countDownDate = new Date($('input[name="endTime"]').val()).getTime();

    console.log(countDownDate);

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

    $('#previous').css('display', 'none');


    if (allQuestion.length === 1) {
        $('#next').css('display', 'none')
        $('#finish').css('display', 'block')

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

    addNewQuestion();

    var data = {
        examId: examId,
        examState: 'TRYING'
    }
    axios.put('/student/change-exam-state', data)
        .then(response => {
            console.log('ok')
        }).catch(error => {
        console.log('error')
    })

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

            allAnswer[index] = allQuestion[index];
            let testAnswer = allQuestion[index].masterAnswer;

            let myTestAnswer = new TestAnswer(testAnswer.options, testAnswer.question);


            if (typeof studentAnswer === "undefined") {
                if (type === 'increase') {
                    index++;
                    myAdjusterPage();
                } else if (type === "decrease") {
                    index--;

                    myAdjusterPage();
                }

            } else {
                axios.post('/student/add-test-answer?questionId=' + allQuestion[index].id, myTestAnswer)
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
            question.masterAnswer.answerText = answerText;

            allAnswer[index] = question;
            let descriptiveAnswer = new DescriptiveAnswer(answerText, question.masterAnswer.question);
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

        if (allAnswer.length > index) {
            $('#questionText').html(allAnswer[index].questionText)
            if (allAnswer[index].masterAnswer.hasOwnProperty('options')) {
                answerType = 'test';
                for (var i = 0; i < allAnswer[index].masterAnswer.options.length; i++) {
                    let option = allAnswer[index].masterAnswer.options[i].content;
                    if (allAnswer[index].masterAnswer.options[i].isAnswered === true) {
                        $('#answer').append(`<div>
            <input type="radio" name="myOption" checked value="${option}"> <span>${option}</span></div>`)

                    } else {
                        $('#answer').append(`<div>
            <input type="radio" name="myOption" value="${option}"> <span>${option}</span></div>`)
                    }

                }
            } else {
                answerType = 'descriptive';

                let textValue = allAnswer[index].masterAnswer.answerText;
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
    $('#finish').click(function (e) {
        e.preventDefault();

        var data = {
            examId: examId,
            examState: 'FINISHED'
        }
        axios.put('/student/change-exam-state', data)
            .then(response => {
                console.log('ok')
            })

        saveCurrentPage('lastPage');
        window.location.replace("http://localhost:8080/student/course?id=" + courseId);

    })
})