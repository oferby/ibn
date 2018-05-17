function connect() {
    var socket = new SockJS('/intent-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/intent', function (hint) {
            var res = JSON.parse(hint.body);
            if (res.status == 'DONE') {
//                sendIntent(res);
            }

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

function addUserInput(text) {

    $('#history').append('<div class="user-text-line"><span class="user-text">'+text+'</span></div>')

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
            addUserInput($('#user-input').val());
            $('#user-input').val('')
        }
    });

})