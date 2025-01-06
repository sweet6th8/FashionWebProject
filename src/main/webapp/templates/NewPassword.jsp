<!DOCTYPE HTML>
<html lang="en">
<head>
  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>GreatKart | One of the Biggest Online Shopping Platform</title>
  <jsp:include page="headerResource.jsp"/>
</head>
<body>
<section class="section-content padding-y" style="min-height:84vh">
  <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
    <div class="card-body">
      <h4 class="card-title mb-4">Create new password</h4>
      <form action="NewPassword" Method="POST">
        <div class="form-group">
          <input type="password" class="form-control" placeholder="New password" name="newPassword" id="newPassword" REQUIRED >
        </div> <!-- form-group// -->
        <div class="form-group">
          <input type="password" class="form-control" placeholder="Confirm password" name="confirmPassword" id="confirmPassword" REQUIRED>
        </div> <!-- form-group// -->
        <div class="form-group">
          <button type="submit" class="btn btn-primary btn-block"> Confirm Password   </button>
        </div> <!-- form-group// -->
      </form>
    </div> <!-- card-body.// -->
  </div> <!-- card .// -->
  <br><br>
</section>
<footer class="section-footer border-top padding-y">
  <div class="container">
    <p class="float-md-right">
      &copy Copyright 2019 All rights reserved
    </p>
    <p>
      <a href="#">Terms and conditions</a>
    </p>
  </div><!-- //container -->
</footer>
</body>