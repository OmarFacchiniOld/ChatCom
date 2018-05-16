<%-- 
    Document   : chat
    Created on : 10-apr-2018, 17.58.18
    Author     : Abate Simone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container extend text-center">
    <div id="chat" class="row upperrow text-center">
        <div id="chatcontainer" class="container-fluid chatcontainer">
            <br />
            <div id="start"></div>
            <!--    insert by jquery        -->
            <br />
        </div>
    </div>
    
    <div class="row centerrow align-items-end">
        <div class="col-1">
            <div id="mastercard" class="card">
                            <div class="card-header">
                                <form method="get" action="${pageContext.request.contextPath}/logout">
                                    <button id="Logoutbtn" type="submit" class="btn btn-sm btn-outline-success">Logout ${user.nickname}</button>
                                </form>    
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">Create new chat</h5>
                                <div class="row">
                                    <div class="col"></div>
                            <div class="col">
                                <button id="newchatbtn" type="button" class="btn btn-sm" data-toggle="modal" data-target="#NewChatModal"></button>
                            </div>
                        <div class="col"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-5">
            <button id="buttonopen" value=""><img src="images/arrow.svg"></button>
        </div>
        <div class="col-6">
            <div class="card messagearea">
                <form id="messagesend">
                    <div class="card-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-11">
                                    <textarea id="textarea" rows="2" name="messagearea" placeholder="Type something..."></textarea>
                                </div>
                                <div class="col-1">
                                    <button id="sendbutton"></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <div id="menu" class="row downrow">
       
        <div class="col scrollable">
            <div id="secondstart"></div>
            <!--    insert by jquery        -->
        </div>
    </div> 
</div>


<div class="modal fade" id="NewChatModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New chat</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <form action="#" method="POST"> 
            <div class="modal-body">
                <input type="text" class="form-control" placeholder="Insert name of chat" name="chatname"><br />
                <input type="password" class="form-control" placeholder="Insert" name=""><br />
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-sm btn-outline-success" value="Create">
            </div>
        </form>
    </div>
  </div>
</div>
