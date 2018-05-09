/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



//var rootURL = "https://jsonplaceholder.typicode.com/posts";
var rootURL = "http://localhost:8080/ChatCom/api/user";



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

//checkInstance(${user.id});


function checkInstance(user) {
    $.ajax({
        url: "http://localhost:8080/api/instance?userid= " + user,
        type: "GET",

        contentType: 'application/json; charset=utf-8',
        success: function (data) {

        }
    })
}

getAllMessages();

function getAllMessages() {
    $.ajax({
        url: rootURL,
        type: "GET",

        contentType: 'application/json; charset=utf-8',
//            success: function(data) {
//                        $.each(data, function (index, data){
//                           if(data.id % 2 == 0)  //invece di utilizzare l'id fare tipo data.username == sessione.username
//                               dxtext(data.title,data.body);
//                           else
//                               sxtext(data.title,data.body);
//                           addchat(data.title,data.body);
//                        });
//            }
        success: function (data) {
            $.each(data, function (index, data) {
                if (data.id % 2 == 0)  //invece di utilizzare l'id fare tipo data.username == sessione.username
                    dxtext(data.nickname, data.lastname);
                else
                    sxtext(data.nickname, data.lastname);
                addchat(data.nickname, data.lastname);
            });
        }
    });
}


//pensavo di aggiungere qua e sul dxtext la stampa del message o fare un getMessage che creera una string
// da passare come parametro qua
function sxtext(name, text) {
    $('#start').append('<div class="row"><div class="col-3"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + name + '</h6></div><div class="card-body"><p class="card-text">' + text + '</p></div></div></div><div class="col-7"></div></div>');
}

function dxtext(name, text) {
    $('#start').append('<div class="row"><div class="col-7"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">' + name + '</h6></div><div class="card-body"><p class="card-text">' + text + '</p></div></div></div><div class="col-3"></div></div>');
}

function addchat(name, text, image, id) {
    $('#secondstart').append('<div id="chat'+id+'" class="card profilecard"><div class="card-header"><img class="profileimage" src="images/test.jpeg"></div><div class="card-body"><h5 class="card-title">' + name + '</h5><h6 class="card-subtitle mb-2 text-muted">' + text + '</h6></div></div>');
}





//                    success: function(data) {
//                        $.each(data, function (index, data){
//                            addchat("nome","testo",index);
//                           $( "#chat"+index ).click(function() {
//                                cosa fa se clicchi
//                            });
//                        });
//                    }