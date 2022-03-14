
// const btn = document.querySelector('button')
// const inputs = document.querySelector('form')
// btn.addEventListener('click', () => {
//     Email.send({
//         Host: "smtp.sendgrid.net",
//         Username:"apikey",
//         Password: "SENDGRID_API_KEY",
//         To: "stackknot@gmail.com",
//         From: "stackknot@gmail.com",
//         // From: inputs.elements["email"].value,
//         Subject: "Contact An Expert Inquiry",
//         Body: inputs.elements["message"].value + "<br>" + inputs.elements["name"].value + "<br>" + inputs.elements["email"].value
//     }).then(msg=>alert("The email successfully sent"))
// })



// using Twilio SendGrid's v3 Node.js Library
// https://github.com/sendgrid/sendgrid-nodejs
// javascript

// const btn = document.querySelector('button')
// const inputs = document.querySelector('form')
//
// require('dotenv').config();
// const sgMail = require('@sendgrid/mail')
// sgMail.setApiKey('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')
//
// btn.addEventListener('click', () => {
// const msg = {
//     to: 'stackknot@gmail.com', // Change to your recipient
//     from: 'stackknot@gmail.com', // Change to your verified sender
//     subject: "Contact An Expert Inquiry",
//     text: inputs.elements["message"].value + "<br>" + inputs.elements["name"].value + "<br>" + inputs.elements["email"].value,
//     html: '<strong>and easy to do anywhere, even with Node.js</strong>',
// }
// sgMail
//     .send(msg)
//     .then(() => {
//         console.log('Email sent')
//         alert("The email successfully sent")
//     })
//     .catch((error) => {
//         console.error(error)
//     })
// })

const btn = document.querySelector('button')
    const inputs = document.querySelector('form')

    const sgMail = require('@sendgrid/mail')
    sgMail.setApiKey(process.env.SENDGRID_API_KEY)
//     sgMail.setApiKey('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')


    const msg = {
        to: 'stackknot@gmail.com', // Change to your recipient
        from: 'stackknot@gmail.com', // Change to your verified sender
        subject: "Contact An Expert Inquiry",
        text: inputs.elements["message"].value + "<br>" + inputs.elements["name"].value + "<br>" + inputs.elements["email"].value,
        html: '<strong>and easy to do anywhere, even with Node.js</strong>',
    }
    btn.addEventListener('click', () => {
        sgMail
            .send(msg)
            .then(() => {
                console.log('Email sent')
                // alert("The email successfully sent")
            })
            .catch((error) => {
                console.error(error)
            })
    })