$("#validateCards").validate({
    rules:{
        question:{
            minlength: 2
        },
        answer: {
            minlength: 2
        }
    },
    messages: {
        question: {
            required: "question must not be blank",
            minlength: "question must be at least 2 characters"
        },
        answer: {
            required: "answer must not be blank",
            minlength: "answer must be at least 2 characters"
        }
    },
    submitHandler: function(form) {
        form.submit();
    }
});