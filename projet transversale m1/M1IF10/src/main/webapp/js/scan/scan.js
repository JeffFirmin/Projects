function scanResponse(altJson) {
    var scanned = document.getElementById("scanned");
    if (scanned.classList.contains("invisible")) {
        scanned.classList.remove("invisible");
        scanned.classList.add("visible");
    }
    var table = document.getElementById("scannedtable");
    var row = table.insertRow();
    var nomCell = row.insertCell(0);
    var prenomCell = row.insertCell(1);
    var numetuCell = row.insertCell(2);
    var photoCell = row.insertCell(3);
    nomCell.innerText = altJson['nom'];
    prenomCell.innerText = altJson['prenom'];
    numetuCell.innerText = altJson['numetu'];
    photoCell.innerHTML = '<img class="img-fluid" src="data:image/png;base64, ' + altJson['photo'] + '" alt="photo étu"/>'
}

function onQRCodeScanned(scannedText) {
    console.log('scanné!');
    var scannedTextMemo = document.getElementById("forminput");
    if (scannedText) {
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
                var jsonResponse = JSON.parse(httpRequest.responseText);
                scanResponse(jsonResponse);
            }
        };
        httpRequest.open('POST', '');
        httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        httpRequest.send('cleQr=' + scannedText);
    }
}

//funtion returning a promise with a video stream
function provideVideoQQ() {
    return navigator.mediaDevices.enumerateDevices()
        .then(function (devices) {
            var exCameras = [];
            devices.forEach(function (device) {
                if (device.kind === 'videoinput') {
                    exCameras.push(device.deviceId)
                }
            });

            return Promise.resolve(exCameras);
        }).then(function (ids) {
            if (ids.length === 0) {
                return Promise.reject('Could not find a webcam');
            }

            return navigator.mediaDevices.getUserMedia({
                video: {
                    'optional': [{
                        'sourceId': ids.length === 1 ? ids[0] : ids[1]//this way QQ browser opens the rear camera
                    }]
                }
            });
        });
}

//this function will be called when JsQRScanner is ready to use
function JsQRScannerReady() {
    //create a new scanner passing to it a callback function that will be invoked when
    //the scanner succesfully scan a QR code
    var jbScanner = new JsQRScanner(onQRCodeScanned, provideVideoQQ);
    //reduce the size of analyzed images to increase performance on mobile devices
    jbScanner.setSnapImageMaxSize(300);
    jbScanner.setScanInterval(3000); // Ralentir le scanner pour ne pas envoyer trop de requêtes
    var scannerParentElement = document.getElementById("scanner");
    if (scannerParentElement) {
        //append the jbScanner to an existing DOM element
        jbScanner.appendTo(scannerParentElement);
    }
    document.getElementsByClassName("qrPreviewVideo")[0].classList.add("embed-responsive")
}