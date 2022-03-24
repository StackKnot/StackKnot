$("#formValidation").validate({
    rules:{
        title:{
            minlength: 2
        },
        description: {
            minlength: 2
        }
    },
    messages: {
        title: {
            required: "title must not be blank",
            minlength: "title must be at least 2 characters"
        },
        description: {
            required: "description must not be blank",
            minlength: "description must be at least 2 characters"
        }
    },
    submitHandler: function(form) {
        form.submit();
    }
});