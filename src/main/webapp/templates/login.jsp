<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>GreatKart | One of the Biggest Online Shopping Platform</title>
  <%@include file="headerResource.jsp"%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
</head>
<body>
<%@ include file="/templates/includes/navbar.jsp" %>
  <section class="section-conten padding-y" style="min-height:84vh">
    <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
      <div class="card-body">
        <h4 class="card-title mb-4">Sign in</h4>
        <form action="login" method="POST">
          <div class="form-group">
            <input type="email" class="form-control" placeholder="Email Address" name="email">
          </div>
          <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" name="password">
          </div>
          <div class="form-group">
            <a href="forgot.jsp" class="float-right">Forgot password?</a>
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"> Login </button>
          </div>
          <c:if test="${ not  empty requestScope.message}">
            <p style="color: red;" class="text-center">${requestScope.message} !</p>
          </c:if>
        </form>
      </div>
    </div>
    <p class="text-center mt-4">Don'\'t have account? <a href="${pageContext.request.contextPath}/templates/register.jsp">Sign up</a></p>
    <br><br>
  </section>
<%@ include file="/templates/includes/footer.jsp" %>
</body>
</html>