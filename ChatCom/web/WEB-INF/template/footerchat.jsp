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
    
    <script type="text/javascript">
        $(document).ready(function(){
           
        var selectedchat = "";
        
        $("#buttonopen").click(function () {
            if ($("#menu").css("display") != "none") {
                $("#menu").css("display", "none");
                $("#chat").css("max-height", "80vh");
                $("#chatcontainer").css("max-height", "80vh");
                $("#chat").css("min-height", "80vh");
                $("#chatcontainer").css("min-height", "80vh");
            } else {
                $("#menu").css("display", "block");
                $("#chat").css("max-height", "60vh");
                $("#chatcontainer").css("max-height", "60vh");
                $("#chat").css("min-height", "60vh");
                $("#chatcontainer").css("min-height", "60vh");
            }
        });
        
        //prendo tutte le chat dell utente
        getchatgroupsbyuserid(${user.id});

        //TODO: Per creare un messaggio
        $("#sendbutton").click(function(event) {
            event.preventDefault();
            var messagedata = $("#textarea").val();
            
            if(messagedata != "" && selectedchat != ""){   //non so in che chat li sto inserendo i messaggi, inserisco prima il messaggio e poi l' istanza    
                postmessage(createmessage(messagedata), ${user.id}, selectedchat.id);
            }
        });
        
        //Per creare la chat
        $("#chatgroup-button").click(function(event) {
            event.preventDefault();
            var chatgroupname = $("#name").val(); //prendo il nome della chat dal form
            var chatnick = $("#nickname").val();  // prendo il nome del tipo a cui voglio scrivere
            
            if(chatgroupname != "" && chatnick != ""){
                postchat(createchat(chatgroupname), ${user.id}, chatnick);
                $('#NewChatModal').modal('hide');
            }
        });

        function getchatgroupsbyuserid(userid){
            $.getJSON("http://localhost:8080/ChatCom/api/chatgroup?userid="+userid, function(response){
                console.log(response);
                
                $.each(response, function (index, chat){
                   //aggiungo la chat ricevuta
                    displaychat(chat);
                    
                   $("#chat"+chat.id).click(function() {
                       console.log("clicked "+chat.name);
                       
                       selectedchat = chat;
                       //getAllMessages(data.chatgroup.id)
                    });
                });
            });
        }
        
        /*function getchatgroupbylastchatname(chatname){
            $.getJSON("http://localhost:8080/ChatCom/api/chatgroup/?chatname="+chatname, function(response){
                console.log(response);
            });
        } 

        function getmessagebyid(idmessage){
            $.getJSON("http://localhost:8080/ChatCom/api/message/"+idmessage, function(response){
                console.log(response);
            });
        } 

        function getuserbyid(iduser){
            $.getJSON("http://localhost:8080/ChatCom/api/user/"+iduser, function(response){
                console.log(response);
            });
        } 
        
        function getuserbynickname(usernickname){
            $.getJSON("http://localhost:8080/ChatCom/api/user/?="+usernickname, function(response){
                console.log(response);
            });
        } 
        
        function getinstancebyid(idinstance){
            $.getJSON("http://localhost:8080/ChatCom/api/instance/"+idinstance, function(response){
                console.log(response);
            });
        }*/ 
        
        function postchat(chat, userid, usernick){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/chatgroup/user/"+userid+"/withnick/"+usernick,
                data: JSON.stringify(chat),
                success: function(response) {
                    console.log(response);
                    
                    //aggiungo la chat creata
                    var chat = JSON.parse(response);
                    displaychat(chat);
                }
            });
        }
        
        /*function postinstance(instance){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/instance/",
                data: JSON.stringify(instance),
                success: function(response) {
                    console.log(response);
                }
            });
        }*/
        
        function postmessage(message, userid, chatid){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/message/user/"+userid+"/chatgroup/"+chatid,
                data: JSON.stringify(message),
                success: function(response) {
                    console.log(response);
                    
                    //mostro il messaggio che ho inviato
                    var message = JSON.parse(response);
                    dxtext(message);
                }
            });
        }
        
        //{"id":12,"name":"test chat","dateStart":"May 15, 2018 7:09:48 PM"}
        function createchat(name){
            var chat = new Object();
            chat.name = name;
            return chat;
        }
        
        //[{"id":2,"chatgroup":{"id":2,"name":"test chat","dateStart":"May 15, 2018 7:09:48 PM"},"message":{"id":2,"data":"test message","type":""},"user":{"id":3,"nickname":"omar","email":"test@test.com","firstname":"Omar","lastname":"Facchini","password":"omar123456"}}]
        /*function createinstance(chatgroup, message, user){
            var instance = new Object();
            instance.chatgroup = chatgroup;
            instance.message = message;
            instance.user = user;
            return instance;
        }*/
        
        //{"id":2,"data":"test message","type":""}
        function createmessage(data){
            var message = new Object();
            message.data = data;
            return message;
        }

        function sxtext(message) {
            $('#start').append('<div class="row messaggio"><div class="col-3"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + message.id + '</h6></div><div class="card-body"><p class="card-text">' + message.data + '</p></div></div></div><div class="col-7"></div></div>');
        }

        function dxtext(message) {
            $('#start').append('<div class="row messaggio"><div class="col-7"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + message.id + '</h6></div><div class="card-body"><p class="card-text">' + message.data + '</p></div></div></div><div class="col-3"></div></div>');
        }

        function displaychat(chat) {
            $('#secondstart').append('<div id="chat'+chat.id+'" class="card profilecard"><div class="card-header"><img class="profileimage" src="images/test.jpeg"></div><div class="card-body"><h5 class="card-title">' + chat.name + '</h5><h6 class="card-subtitle mb-2 text-muted">' + chat.text + '</h6></div></div>'); //TODO: al posto di chat.text utilmo messaggio
        } 
        
        });
    </script>


    </body>
</html>