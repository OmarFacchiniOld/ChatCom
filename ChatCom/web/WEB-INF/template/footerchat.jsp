<%-- 
    Document   : footerchat
    Created on : 11-apr-2018, 22.08.15
    Author     : Abate Simone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="javascript/chat.js"></script>
    
    <script>

//var rootURLchat = "http://localhost:8080/ChatCom/api/instance?userid=";
var rootURLsendmessage = "http://localhost:8080/ChatCom/api/message";
var rootURLsendchatgroup = "http://localhost:8080/ChatCom/api/chatgroup";
var rootURLsenduser = "http://localhost:8080/ChatCom/api/user";
var rootURLsendinstance = "http://localhost:8080/ChatCom/api/instance";
var rootURLgetnick = "http://localhost:8080/ChatCom/api/user?nickname=";
var lastmsg ="";
var lastchat="";
var myuser="";
var idsaved="";

$('#messagesend').submit(false);

//getChat();

window.setInterval(function(){
  $('.profilecard').remove();
//  getChat();
}, 5000);


$("#sendbutton").click(function() {
    if($("#textarea").val() != ""){       
        sendmessage($("#textarea").val());
        sendinstance(myuser.id,lastchat.id,idsaved);
        $("#textarea").val("");
    }
});
$("#chatgroup-button").click(function() {
    if($("#name").val() != "" && $("#nickname").val() != ""){       
        sendchatgroup();
        var chatid =idsaved;
        alert(chatid);
//        getnickid();
//        var nickid=idsaved;
        sendinstance(${user.id},chatid,1);
        sendinstance(3,chatid,1);
        $("#name").val("");
        $("#nickname").val("");
    }
});


//function getChat() {
//    $.ajax({
//        url: rootURLchat+${user.id},
//        type: "GET",
//
//        contentType: 'application/json; charset=utf-8',
//
//                    success: function(data) {
//                        $.each(data, function (index, data){
//                            addchat(data.chatgroup.name,data.message.data,index);
//                            myuser = data.user;
//                           $("#chat"+index).click(function() {
//                               //getAllMessages(data.chatgroup.id)
//                               lastchat= data.chatgroup;
//                            });
//                        });
//                    }
//    });
//}


function sendmessage(text){
    var sendobj = new Object();
    sendobj.data = text;
    sendobj.type = "";
    var json= JSON.stringify(sendobj);
    send(rootURLsendmessage,json);
}

function getnickid(){
    $.ajax({
        url: rootURLgetnick+$("#nickname").val(),
        type: "GET",

        contentType: 'application/json; charset=utf-8',

                    success: function(data) {
                        idsaved = data.id;
                    }
    });
} 

function sendchatgroup(){
    var sendobj = new Object();
    sendobj.name = $("#name").val();
    var json= JSON.stringify(sendobj);
    send(rootURLsendchatgroup,json);
}


function sendinstance(userid,chatid,messageid){
    var sendobj = new Object();
    sendobj.id_user = userid;
    sendobj.id_chatgroup = chatid;
    sendobj.id_message = messageid;
    var json= JSON.stringify(sendobj);
    send(rootURLsendinstance,json);
}


function send(url, json){
    $.ajax({
    type: "POST",
    contentType: 'application/json; charset=utf-8',
    url: url,
    data: json,
    success: function(data) {
                        idsaved = data;
                    },
});
}

//function getAllMessages(){
//    $.ajax({
//        url: rootURLMessage+lastchat.chatgroup.id,
//        type: "GET",
//        
//        contentType: 'application/json; charset=utf-8',
//        
//        success: function (data){
//            $.each(data, function(index,data){
//                
//            });  
//        }
//    }); 
//    
//}

function sxtext(name, text) {
    $('#start').append('<div class="row messaggio"><div class="col-3"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + name + '</h6></div><div class="card-body"><p class="card-text">' + text + '</p></div></div></div><div class="col-7"></div></div>');
}

function dxtext(name, text) {
    $('#start').append('<div class="row messaggio"><div class="col-7"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + name + '</h6></div><div class="card-body"><p class="card-text">' + text + '</p></div></div></div><div class="col-3"></div></div>');
}

function addchat(name, text, id) {
    $('#secondstart').append('<div id="chat'+id+'" class="card profilecard"><div class="card-header"><img class="profileimage" src="images/test.jpeg"></div><div class="card-body"><h5 class="card-title">' + name + '</h5><h6 class="card-subtitle mb-2 text-muted">' + text + '</h6></div></div>');
}




</script>


    </body>
</html>