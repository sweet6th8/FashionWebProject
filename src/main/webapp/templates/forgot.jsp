<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <h4 class="card-title mb-4">Sign in</h4>
      <form action="${pageContext.request.contextPath}/Mail" Method="POST">
          <div class="form-group">
			 <input type="email" class="form-control" placeholder="Email Address" name="email" REQUIRED>
          </div>
          <div class="form-group">
              <button type="submit" class="btn btn-primary btn-block"> Send Email  </button>
          </div>
      </form>
      </div>
    </div>

     <p class="text-center mt-4">${requestScope.message}</p>
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
	</div>
</footer>
</body>