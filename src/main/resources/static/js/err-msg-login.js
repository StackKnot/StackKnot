$("#validateLog").validate({
    messages: {
        username: "username must not be blank",
        password: "password must not be blank",
    },
    submitHandler: function(form) {
        form.submit();
    }
});