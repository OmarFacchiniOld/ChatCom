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
           
           var lastmessage = "";
           var lastchat = "";
        
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

        //Per creare un messaggio
        $("#sendbutton").click(function(event) {
            event.preventDefault();
            if($("#textarea").val() != ""){   //non so in che chat li sto inserendo i messaggi, inserisco prima il messaggio e poi l' istanza    
                /*sendmessage($("#textarea").val(),${user.id});
                $("#textarea").val("");*/
            }
        });
        
        //Per creare la chat
        $("#chatgroup-button").click(function(event) {
            event.preventDefault();
            if($("#name").val() != "" && $("#nickname").val() != ""){
    
                var chatgroupname = $("#name").val(); //prendo il nome della chat dal form
                postchat(createchat(chatgroupname), ${user.id});
                
                //var chatnick = $("#nickname").val();  // prendo il nome del tipo a cui voglio scrivere
            }
        });
        
        function getchatsbyuserid(iduser){
            $.getJSON("http://localhost:8080/ChatCom/api/instance?userid="+iduser, function(response){
                console.log(response);
                /*$.each(data, function (index, data){
                                    addchat(data.chatgroup.name,data.message.data,index);
                                   $("#chat"+index).click(function() {
                                       //getAllMessages(data.chatgroup.id)
                                       lastchat= data.chatgroup;
                                    });
                                });*/
            });
        } 
        
        /** **/

        function getchatgroupbyid(idchatgroup){
            $.getJSON("http://localhost:8080/ChatCom/api/chatgroup/"+idchatgroup, function(response){
                console.log(response);
            });
        } 
        
        function getchatgroupbylastchatname(chatname){
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
        } 
        
        function postchat(chat, userid){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/chatgroup?userid="+userid,
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
        
        function postmessage(message, userid){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/message/?idutente="+userid,
                data: JSON.stringify(message),
                success: function(response) {
                    console.log(response);
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
        
        /** **/

        function sxtext(name, text) {
            $('#start').append('<div class="row messaggio"><div class="col-3"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + name + '</h6></div><div class="card-body"><p class="card-text">' + text + '</p></div></div></div><div class="col-7"></div></div>');
        }

        function dxtext(name, text) {
            $('#start').append('<div class="row messaggio"><div class="col-7"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + name + '</h6></div><div class="card-body"><p class="card-text">' + text + '</p></div></div></div><div class="col-3"></div></div>');
        }

        function displaychat(chat) {
            $('#secondstart').append('<div id="' +chat.id+ '" class="card profilecard"><div class="card-header"><img class="profileimage" src="images/test.jpeg"></div><div class="card-body"><h5 class="card-title">' + chat.name + '</h5><h6 class="card-subtitle mb-2 text-muted">' + chat.text + '</h6></div></div>');
        } 
        });
    </script>


    </body>
</html>