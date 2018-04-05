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
        <form action="#" method="POST"> 
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
        <form action="#" method="POST"> 
            <div class="modal-body">
                <input type="text" class="form-control" placeholder="Insert first name" name="firstname"><br />
                <input type="text" class="form-control" placeholder="Insert last name" name="lastname"><br />
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text">@</div>
                    </div>
                    <input type="text" class="form-control" placeholder="Insert nickname" name="nickname">
                </div><br />
                <input type="email" class="form-control" placeholder="Insert email" name="email"><br />
                <input type="password" class="form-control" placeholder="Insert password" aria-describedby="passwordHelpBlock" name="password">
                <small id="passwordHelpBlock" class="form-text" style="color: #CE93D8">
                    Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
                </small><br />
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-sm btn-outline-success" value="Register">
            </div>
        </form>
    </div>
  </div>
</div>