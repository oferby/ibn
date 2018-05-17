var intent = {
    hint: ''
}

function connect() {
    var socket = new SockJS('/intent-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/intent', function (hint) {
            var res = JSON.parse(hint.body);
//            if (res.status == 'DONE') {
//                sendIntent(res);
//            }
            addBotResponse(res.hint);

        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendIntent(userInput){
    intent.hint = userInput;
    console.log('Sending intent to server');
    stompClient.send("/app/getIntent", {}, JSON.stringify(intent));
}

function addUserInput(text) {

    $('#history').append('<div class="user-text-line"><span class="user-text">'+text+'</span></div>')

}


function addBotResponse(text){

    $('#history').append('<div class="bot-text-line"><span class="bot-text">'+text+'</span></div>')
}

$(document).ready( function(){

//    $('.click').click(function(){
//        $('#modal').show();
////        $('#modal').draggable();
//    });

    connect();

    $('#modal').show();

    $('#user-input').keyup(function(e){
        if(e.keyCode == 13)
        {
            var userInput = $('#user-input').val()
            $('#user-input').val('')
            addUserInput(userInput);
            sendIntent(userInput);
        }
    });

})