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

exports.getTemperature = functions.database.ref('/Temperature/{id}')
    .onWrite((change: { after: any; }, context: any) =>{
        
        
        const snapshot = change.after;
        const val = snapshot.val();
        const time = val.time;
        const userPlantId = val.userPlantId;
        const value = val.value;
        
        const stringTime = JSON.stringify(time);
        const stringUserPlantId = JSON.stringify(userPlantId);
        const stringValue = JSON.stringify(value);

    
        console.log('Posted data for values: ', val)

    
        console.log('New temperature value for plant: ', stringUserPlantId, ' with value ', stringValue, 'at time ', stringTime);

        const payload = {
            data:{
                type: "temperature",
                time: stringTime,
                userPlantId: stringUserPlantId,
                value: stringValue,
            },
        };

        const options = {
            priority: "high",
        }
        
       return admin.messaging().sendToTopic("temps", payload, options)
       .then((response: any) => {
           console.log('Successfully sent message: ', response);
       })
       .catch((error: any) => {
           console.log('Error sending message: ', error);
       });

})

exports.getMoisture = functions.database.ref('/Moisture/{id}')
    .onWrite((change: { after: any; }, context: any) =>{
        
        
        const snapshot = change.after;
        const val = snapshot.val();
        const time = val.time;
        const userPlantId = val.userPlantId;
        const value = val.value;
        
        const stringTime = JSON.stringify(time);
        const stringUserPlantId = JSON.stringify(userPlantId);
        const stringValue = JSON.stringify(value);

    
        console.log('Posted data for values: ', val)

    
        console.log('New moisture value for plant: ', stringUserPlantId, ' with value ', stringValue, 'at time ', stringTime);

        const payload = {
            data:{
                type: "moisture",
                time: stringTime,
                userPlantId: stringUserPlantId,
                value: stringValue,
            },
        };

        const options = {
            priority: "high",
        }
        
       return admin.messaging().sendToTopic("temps", payload, options)
       .then((response: any) => {
           console.log('Successfully sent message: ', response);
       })
       .catch((error: any) => {
           console.log('Error sending message: ', error);
       });

})


exports.getLight = functions.database.ref('/Light/{id}')
    .onWrite((change: { after: any; }, context: any) =>{
        
        
        const snapshot = change.after;
        const val = snapshot.val();
        const time = val.time;
        const userPlantId = val.userPlantId;
        const value = val.value;
        
        const stringTime = JSON.stringify(time);
        const stringUserPlantId = JSON.stringify(userPlantId);
        const stringValue = JSON.stringify(value);

    
        console.log('Posted data for values: ', val)

    
        console.log('New light value for plant: ', stringUserPlantId, ' with value ', stringValue, 'at time ', stringTime);

        const payload = {
            data:{
                type: "light",
                time: stringTime,
                userPlantId: stringUserPlantId,
                value: stringValue,
            },
        };

        const options = {
            priority: "high",
        }
        
       return admin.messaging().sendToTopic("temps", payload, options)
       .then((response: any) => {
           console.log('Successfully sent message: ', response);
       })
       .catch((error: any) => {
           console.log('Error sending message: ', error);
       });

})


