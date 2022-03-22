
// using Twilio SendGrid's v3 Node.js Library
// https://github.com/sendgrid/sendgrid-nodejs
// javascript
//Need Node.js Module to Run


// require('dotenv').config
// const sgMail = require('@sendgrid/mail')
// // sgMail.setApiKey('') PLUG IN API KEY DIRECTLY TO SEND EMAIL WITH NODE JS, DELETE AFTER SENT
// sgMail.setApiKey(process.env.SENDGRID_API_KEY)
//
//
// const msg = {
//     to: ['test@gmail.com','jlongoria66@yahoo.com'], // Change to your recipient
//     from: 'stackknot@gmail.com', // Change to your verified sender
//     subject: 'Sending with SendGrid is Fun',
//     text: 'and easy to do anywhere, even with Node.js',
//     html: '<strong>and easy to do anywhere, even with Node.js</strong>',
// }
// sgMail
//     .send(msg)
//     .then(() => {
//         console.log('Email sent')
//     })
//     .catch((error) => {
//         console.error(error)
//     })