
function getJRequest(myReq){
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080' + myReq);
    console.log('http://localhost:8080' + myReq);
    xhr.responseType = 'text';
    xhr.onload = function() {
        if (xhr.status === 200) {
            var data = xhr.response;
            //console.log(typeof(JSON.stringify(data))); // or just data for raw text data
            return data;
        }
        else {
            console.log('Request failed.  Returned status of ' + xhr.status);
        }
    };
    xhr.onerror = function() {
        console.log('Request failed. There was an error in the XHR request.');
    };
    xhr.send();
}
