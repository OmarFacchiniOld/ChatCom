<%-- 
    Document   : footer
    Created on : 16-mar-2018, 15.22.40
    Author     : Uberti Davide
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

       <script>
        $("#buttonopen").click(function() {
            if($("#menu").css("display") != "none"){
                $("#menu").css("display", "none");
                $("#chat").css("max-height","80vh");
                $("#chatcontainer").css("max-height","80vh");
            }
            else{
                $("#menu").css("display", "block");
                $("#chat").css("max-height","60vh");
                $("#chatcontainer").css("max-height","60vh");
            }
});
    </script>
    </body>
</html>
