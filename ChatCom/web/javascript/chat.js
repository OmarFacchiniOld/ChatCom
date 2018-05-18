/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

