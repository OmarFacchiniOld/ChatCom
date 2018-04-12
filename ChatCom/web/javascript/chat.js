/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var rootURL = "https://jsonplaceholder.typicode.com/posts";



$("#buttonopen").click(function() {
            if($("#menu").css("display") != "none"){
                $("#menu").css("display", "none");
                $("#chat").css("max-height","80vh");
                $("#chatcontainer").css("max-height","80vh");
                $("#chat").css("min-height","80vh");
                $("#chatcontainer").css("min-height","80vh");
            }
            else{
                $("#menu").css("display", "block");
                $("#chat").css("max-height","60vh");
                $("#chatcontainer").css("max-height","60vh");
                $("#chat").css("min-height","60vh");
                $("#chatcontainer").css("min-height","60vh");
            }
});

getAllMessages();

function getAllMessages(){
    $.ajax({
            url: rootURL,
            type: "GET",

            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                        $.each(data, function (index, data){
                           if(data.id % 2 == 0)
                               dxtext(data.title,data.body);
                           else
                               sxtext(data.title,data.body);
                           addchat(data.title,data.body);
                        });
            }
        });
}


function sxtext(name, text){
    $('#start').append('<div class="row"><div class="col-3"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">'+name+'</h6></div><div class="card-body"><p class="card-text">'+text+'</p></div></div></div><div class="col-7"></div></div>');
}

function dxtext(name, text){
    $('#start').append('<div class="row"><div class="col-7"></div><div class="col-2"><div class="card message"><div class="card-header"><h6 class="card-title">'+name+'</h6></div><div class="card-body"><p class="card-text">'+text+'</p></div></div></div><div class="col-3"></div></div>');
}

function addchat(name, text, image, id){
    $('#secondstart').append('<div class="card profilecard"><div class="card-header"><img class="profileimage" src="images/test.jpeg"></div><div class="card-body"><h5 class="card-title">'+name+'</h5><h6 class="card-subtitle mb-2 text-muted">'+text+'</h6></div></div>');
}
