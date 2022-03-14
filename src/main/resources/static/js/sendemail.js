
// using Twilio SendGrid's v3 Node.js Library
// https://github.com/sendgrid/sendgrid-nodejs
// javascript

const sgMail = require('@sendgrid/mail')
sgMail.setApiKey('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')
// sgMail.setApiKey(process.env.SENDGRID_API_KEY)


const msg = {
    to: ['test@gmail.com','jlongoria66@yahoo.com'], // Change to your recipient
    from: 'stackknot@gmail.com', // Change to your verified sender
    subject: 'Sending with SendGrid is Fun',
    text: 'and easy to do anywhere, even with Node.js',
    html: '<strong>and easy to do anywhere, even with Node.js</strong>',
}
sgMail
    .send(msg)
    .then(() => {
        console.log('Email sent')
    })
    .catch((error) => {
        console.error(error)
    })