<%-- 
    Document   : modal
    Created on : 3-apr-2018, 19.36.55
    Author     : Abate Simone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <form action="${pageContext.request.contextPath}/login" method="post"> 
            <div class="modal-body">
                <input type="text" class="form-control" placeholder="Insert username" name="username"><br />
                <input type="password" class="form-control" placeholder="Insert password" name="password"><br />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-outline-success" data-dismiss="modal" data-toggle="modal" data-target="#RegisterModal">Are you not registered? register now</button>
                <input type="submit" class="btn btn-sm btn-outline-success" value="Login">
            </div>
        </form>
    </div>
  </div>
</div>


<!-- Modal -->
<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Register</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <form id="register" action="${pageContext.request.contextPath}/register" method="post"> 
            <div class="modal-body">
                <input id="first" type="text" class="form-control" placeholder="Insert first name" name="firstname"><br />
                <input id="last" type="text" class="form-control" placeholder="Insert last name" name="lastname"><br />
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text">@</div>
                    </div>
                    <input id="nick" type="text" class="form-control" placeholder="Insert nickname" name="nickname">
                </div><br />
                <input id="email" type="email" class="form-control" placeholder="Insert email" name="email"><br />
                <input id="pass" type="password" class="form-control" placeholder="Insert password" name="password"><br />
                <input id="pass2" type="password" class="form-control" placeholder="Insert password" aria-describedby="passwordHelpBlock" name="confirm-password">
                <small id="passwordHelpBlock" class="form-text" style="color: #CE93D8">
                    Your password must be 8-20 characters long, contain letters and/or numbers.
                </small><br />
            </div>
            <div class="modal-footer">
                <input id="sendregister" data-dismiss="modal" data-toggle="modal" type="submit" class="btn btn-sm btn-outline-success" value="Register">
            </div>
        </form>
    </div>
  </div>
</div>