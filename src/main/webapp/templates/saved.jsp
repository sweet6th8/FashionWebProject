<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Title</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>GreatKart | One of the Biggest Online Shopping Platform</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/history.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">

    <jsp:include page="headerResource.jsp"/>
</head>
<body>
<%@ include file="/templates/includes/navbar.jsp" %>
<section class="section-content">
    <div class="container p-4">
        <div class="row mt-4">
            <div class="col-md-12">
                <div>
                    <c:forEach var="item" items="${requestScope.products}">
                        <div class="card mb-3" style="max-width: 100%; max-height: 400px; ">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}${item.getPhoto()}"
                                         class="img-thumbnail rounded-start" alt="...">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body" style="height: 60%; ">

                                        <h5 class="card-title fa-2x">${item.getName()}</h5>

                                    </div>
                                    <div class="card-footer d-flex justify-content-between align-items-md-end ">
                                        <p class="fa-2x"> Order total : ${item.getPrice()}</p>
                                        <a href="#" class="btn btn-primary" style="float: right">Buy again</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>