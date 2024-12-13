<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <head>
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>GreatKart | One of the Biggest Online Shopping Platform</title>
    <%@include file="headerResource.jsp"%>
  </head>
<body>
<jsp:include page="includes/navbarNotSearch.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<section class="section-content container-fluid" style="margin-top: 100px">
  <div class="card mx-auto " style="max-width: 520px">
    <article class="card-body">
      <header class="mb-4"><h4 class="card-title">Edit profile</h4></header>
      <form action="eidt" method="POST">
<div>
  <input type="file" class="form-control border mb-3" id="customFile" />
</div>
        <div class="form-row">
          <div class="col form-group">
            <label>First namne </label>
            <input
                    name="FirstName"
                    type="text"
                    class="form-control"
                    placeholder="${user.getFirstName()}"
                    maxlength="255"
                    autofocus
                    required
            />
          </div>
          <div class="col form-group">
            <label>Last name</label>
            <input
                    name="LastName"
                    type="text"
                    class="form-control"
                    placeholder="${user.getLastName()}"
                    maxlength="255"
                    required
            />
          </div>
        </div>
        <div class="form-group">
          <label>Email</label>
          <input
                  id="email"
                  name="Email"
                  type="email"
                  class="form-control"
                  placeholder="${user.getEmail()}"
                  required
                  onChange="validateEmail()"
          />
          <p id="error-email" style="color: red; display: none">
            Email invalid
          </p>
        </div>
        <div class="form-group">
          <label>phone</label>
          <input type="text" id="phone"       placeholder="${user.getPhone()}" oninput="validatePhone()"  required   class="form-control" />
          <span id="error-phone" style="color: red; display: none;"></span>


        </div>
        <div class="form-group">
          <label class="custom-control custom-radio custom-control-inline">
            <input
                    name="gender"
                    class="custom-control-input"
                    checked
                    type="radio"
                    value="1"
            />
            <span class="custom-control-label"> Male </span>
          </label>
          <label class="custom-control custom-radio custom-control-inline">
            <input
                    required
                    name="gender"
                    class="custom-control-input"
                    type="radio"
                    value="0"
            />
            <span class="custom-control-label"> Female </span>
          </label>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label>Country</label>
            <input type="text" class="form-control" />
          </div>
          <div class="form-group col-md-6">
            <label>City</label>
            <input list="inputState" class="form-control" />
            <datalist name="city" id="inputState">
              <option name="city" selected value="${user.getAddress()}"></option>
              <option name="city"  value="Long An"></option>
              <option name="city" value="Binh Dinh"></option>
              <option name="city" value="Quy Nhon"></option>
              <option name="city" value="Quang Ngai"></option>
              <option name="city" value="Ha Noi"></option>
              <option name="city" value="Ho Chi Minh"></option>
            </datalist>
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="password">Password:</label>
            <input
                    required
                    type="password"
                    name="password"
                    id="password"
                    class="form-control"
                    onchange="validatePassword()"
            />
            <p id="password-error" style="color: red; display: none">
              The password must be between 8 and 20 characters, including uppercase letters,
              usually, numbers, and special characters.
            </p>
          </div>
          <div class="form-group col-md-6">
            <label>Confirm password</label>
            <input
                    id="Checkpassword"
                    name="Checkpassword"
                    class="form-control"
                    type="password"
                    required
                    onChange="validateCheckPassword()"
            />
            <p id="Checkpassword-error" style="color: red; display: none">
              password not match
            </p>
          </div>
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-primary btn-block">
            Update Profile
          </button>
        </div>
      </form>
    </article>
    <!-- card-body.// -->
  </div>
  <!-- card .// -->
  <br /><br />
</section>
<jsp:include page="includes/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/register.js"></script>
</body>
</html>
