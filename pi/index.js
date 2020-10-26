var firebase = require("firebase");

var config = {
    apiKey: "AIzaSyCALbKuTEHHGc3jW6CfE4dmvKWpdCh-X78",
    authDomain: "healthyleaves-a5549.firebaseapp.com",
    databaseURL: "https://healthyleaves-a5549.firebaseio.com",
    storageBucket: "bucket.appspot.com"
};

firebase.initializeApp(config);

var database = firebase.database();

function writeToDatabase(sensor, value) {
  database.ref('sensor/' + sensor).set({
    value: value
  });
}

writeToDatabase("light", "0.724");
writeToDatabase("moisture", "0.892");
writeToDatabase("temperature", "0.123");
