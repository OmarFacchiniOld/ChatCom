/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$("#sendregister").click(function() {
            if($("#pass").val() != $("#pass2").val()){
                alert("Passwords not equal");
            }
            else if($("#pass").val().length<8){
                alert("password too short");
            }
            else if($("#pass").val().length>20){
                alert("Passwords is too long");
            }
            else if($("#first").val() == ""){
                alert("first name is void");
            }
            else if($("#last").val() == ""){
                alert("last name is void");
            }
            else if($("#nick").val() == ""){
                alert("nickname is void");
            }
            else if($("#email").val() == ""){
                alert("email is void");
            }
            else{
                $("#register").submit();
            }
});