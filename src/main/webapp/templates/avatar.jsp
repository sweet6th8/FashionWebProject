<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Confirm OTP</title>
    <jsp:include page="headerResource.jsp"/>
</head>
<body>
<jsp:include page="includes/navbar.jsp"/>
<section class="section-content padding-y" style="min-height:84vh">
    <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
        <div class="card-body">
            <h4 class="card-title mb-4">User avatar</h4>
            <img src="${pageContext.request.contextPath}${requestScope.img}" alt="Avatar" class="img-fluid rounded mb-3">
            <form action="${pageContext.request.contextPath}/secure/user/upload" Method="POST" enctype="multipart/form-data">
                <input type="file" name="file">
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
    <br><br>
</section>
<jsp:include page="includes/footer.jsp"/>
</body>