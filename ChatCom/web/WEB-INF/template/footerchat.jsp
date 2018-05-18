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

        var rootURLchat = "http://localhost:8080/ChatCom/api/instance?userid=";
        var rootURLsendmessage = "http://localhost:8080/ChatCom/api/message";
        var rootURLsendchatgroup = "http://localhost:8080/ChatCom/api/chatgroup";
        var rootURLsenduser = "http://localhost:8080/ChatCom/api/user";
        var rootURLsendinstance = "http://localhost:8080/ChatCom/api/instance";
        var rootURLgetnick = "http://localhost:8080/ChatCom/api/user?nickname=";
        var lastmsg ="";
        var lastchat="";


        $("#sendbutton").click(function() {
            if($("#textarea").val() != ""){   //non so in che chat li sto inserendo i messaggi, inserisco prima il messaggio e poi l' istanza    
                /*sendmessage($("#textarea").val(),${user.id});
                $("#textarea").val("");*/
            }
        });
        
        $("#chatgroup-button").click(function() {
            if($("#name")name.val() != "" && $("#nickname").val() != ""){
    
                var chatgroupname = $("#name").val(); //prendo il nome della chat dal form
                var chat = postchat(createchat(chatgroupname));
                
                var chatnick = $("#nickname").val();  // prendo il nome del tipo 
                
                var message = getmessagebyid(1); //messaggio uguale per tutti della chat creata (già presente nel db)
                var user = getuserbynickname(chatnick);
                var chatgroup = getchatgroupbylastchatname(chatgroupname);
                
                postinstance(createinstance(chatgroup, message, user));
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
        
        function postchat(chat){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/chatgroup/",
                data: JSON.stringify(chat),
                success: function(response) {
                    console.log(response);
                }
            });
        }
        
        function postinstance(instance){
            $.ajax({
                type: "POST",
                contentType: 'application/json; charset=utf-8',
                url: "http://localhost:8080/ChatCom/api/instance/",
                data: JSON.stringify(instance),
                success: function(response) {
                    console.log(response);
                }
            });
        }
        
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
        function createinstance(chatgroup, message, user){
            var instance = new Object();
            instance.chatgroup = chatgroup;
            instance.message = message;
            instance.user = user;
            return instance;
        }
        
        //{"id":2,"data":"test message","type":""}
        function createmessage(data){
            var message = new Object();
            message.data = data;
            return message;
        }
        
        /** **/

        function sendinstance(user,chat,message){
            var sendobj = new Object();
            sendobj.user = user;
            sendobj.chatgroup = chat;
            sendobj.message = message;
            var json= JSON.stringify(sendobj);
            send(rootURLsendinstance,json);
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