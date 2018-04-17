<%-- 
    Document   : chat
    Created on : 10-apr-2018, 17.31.49
    Author     : Abate Simone
--%>

<%@page import="chatcom.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Verifico che ci sia qualcuno loggato, se no lo rimando alla pagina di login
    User user = null;
    
    if(session.getAttribute("user")==null)
        response.sendRedirect("index.jsp");
    else
        user = (User) session.getAttribute("user");
%>

<jsp:include page="WEB-INF/template/headerchat.jsp"/>
<%--<jsp:include page="WEB-INF/template/navbar.jsp"/>--%>
<jsp:include page="WEB-INF/template/chatmain.jsp"/>

<jsp:include page="WEB-INF/template/footerchat.jsp"/>