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

var rootURL = "http://localhost:8080/ChatCom/api/instance";
var rootURLMessage = "http://localhost:8080/ChatCom/api/message";
var totalk = "";




getChat();
//getAllMessages();

window.setInterval(function(){
  $('.profilecard').remove();
  $('.messaggio').remove();
  getChat();
  //getAllMessages();
}, 5000);



function getChat() {
    $.ajax({
        url: rootURL+'/'+${user.id},
        type: "GET",

        contentType: 'application/json; charset=utf-8',

                    success: function(data) {
                        $.each(data, function (index, data){
                            addchat(data.chatgroup.name,data.message.text,index);
                           $("#chat"+index).click(function() {
                               totalk = "";
                               alert(data.chatgroup.name);
                            });
                        });
                    }
    });
}



function getAllMessages(){
    $.ajax({
        url: rootURLMessage,
        type: "GET",
        
        contentType: 'application/json; charset=utf-8',
        
        success: function (data){
            $.each(data, function(index,data){
                if(data.id % 2 == 0)
                    dxtext("test", data.data);
                else
                    sxtext("test", data.data);
                    //addchat(data.nickname, data.lastname, index);
            });  
        }
    }); 
    
}

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