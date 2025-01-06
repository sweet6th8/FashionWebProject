<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Confirm OTP</title>
    <jsp:include page="headerResource.jsp"/>
</head>
<body>
<section class="section-content padding-y" style="min-height:84vh">
    <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
        <div class="card-body">
            <h4 class="card-title mb-4">Confirm OTP</h4>
            <form action="ConfirmOTP" Method="POST">
                <div class="form-group">
                    <label> OTP :</label>
                    <input type="text" class="form-control" placeholder="your otp" name="otp" REQUIRED>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Confirm  </button>
                </div>
            </form>
        </div>
    </div>
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