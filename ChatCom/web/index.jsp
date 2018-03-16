<%-- 
    Document   : index
    Created on : 16-mar-2018, 15.21.13
    Author     : Uberti Davide
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="WEB-INF/template/header.jsp"/>
    
<div>
    <h1>ChatCom</h1>
    <i class="material-icons">speaker_notes</i>
    <%out.println("Println dalla jsp");%>
</div>
    
<jsp:include page="WEB-INF/template/footer.jsp"/>
