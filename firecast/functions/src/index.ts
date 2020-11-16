'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

// Start writing Firebase Functions
// https://firebase.google.com/docs/functions/typescript

//export const getTemperature = functions.https.onRequest((request, response) => {
//    functions.logger.info("Hello logs!", {structuredData: true});
//    response.send("Hello from Firebase!");
//});

exports.getTemperature = functions.database.ref('/Temperature/')
    .onWrite(async (change, context) =>{
        const original = change.after.val();
        const old = change.before.val();
        const vals = original - old;
    
        console.log('New temperature value for plant: ', vals);

        const payload = {
            notification: {
                title: 'The temperature is too low!',
                body: 'Please check on the plant ${userPlantId}.',
            },
            topic: 'temps',
        };
        
       admin.messaging().send(payload)
       .then((response) => {
           console.log('Successfully sent message: ', response);
       })
       .catch((error) => {
           console.log('Error sending message: ', error);
       });
})
