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
                <div class="header mt-4">
                    <ul class="sidenav d-flex gap-4 m-4 nav-underline">
                        <a onclick="openTab(0)" href="#" class="nav-item nav-link fw-bold fs-3 text-black-50">ALL</a>
                        <a onclick="openTab(1)" href="#" class="nav-item nav-link fw-bold fs-3 text-black-50">SHIP</a>
                        <a onclick="openTab(2)" href="#" class="nav-item nav-link fw-bold fs-3 text-black-50">DONE</a>
                        <a onclick="openTab(3)" href="#" class="nav-item nav-link fw-bold fs-3 text-black-50">CANCEL</a>
                        <a onclick="openTab(4)" href="#" class="nav-item nav-link fw-bold fs-3 text-black-50">REFUND</a>
                    </ul>

                </div>
                <div id="ALL" class="tab-content">
                    <c:forEach var="item" items="${requestScope.products}">
                        <div class="card mb-3" style="max-width: 100%; max-height: 400px; ">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="${pageContext.request.contextPath}${item.getPhoto()}"
                                         class="img-thumbnail rounded-start" alt="...">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body" style="height: 60%; ">
                                        <p class="status">
                                            COMPLETE
                                        </p>
                                        <h5 class="card-title fa-2x">${item.getName()}</h5>
                                        <p class=" card-textfa-2">${item.getDescription()} </p>
                                        <p class="card-text fa-2"><small class="text-body-secondary">Quantity :
                                            ?? </small></p>

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
                <div id="SHIP" class="tab-content"><c:forEach var="item" items="${applicationScope.products}">
                    <div class="card mb-3" style="max-width: 100%; max-height: 400px; ">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="${pageContext.request.contextPath}${item.getPhoto()}"
                                     class="img-thumbnail rounded-start" alt="...">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body" style="height: 60%; ">
                                    <p class="status">
                                        COMPLETE
                                    </p>
                                    <h5 class="card-title fa-2x">${item.getName()}</h5>
                                    <p class=" card-textfa-2">${item.getDescription()} </p>
                                    <p class="card-text fa-2"><small class="text-body-secondary">Quantity :
                                        ?? </small></p>

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
                <div id="DONE" class="tab-content">
                </div>
                <div id="CANCEL" class="tab-content">
                </div>
                <div id="REFUND" class="tab-content">
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    const tab = document.getElementsByClassName("tab-content");
    const tabLink = document.getElementsByClassName("tab-btn");
    const listLink = Array.from(tabLink);
    const listTab = Array.from(tab);

    function openTab(index) {
        listLink.forEach(content => content.classList.remove("bg-primary"));
        listTab.forEach(content => content.classList.remove("active"));
        listTab[index].classList.add("active");
        listLink[index].classList.add("bg-primary");
    }

    openTab(0);
</script>
</body>
</html>
