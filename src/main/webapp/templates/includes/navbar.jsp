<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="section-header container-fluid">
    <nav class="navbar p-md-0 navbar-expand-sm navbar-light border-bottom">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTop4"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTop4">
            <ul class="navbar-nav mr-auto ml-1">
                <li class="nav-item ">
                    <a href="${pageContext.request.contextPath}/language?lang=vi" class="nav-link"> VN </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/language?lang=en    " class="nav-link"> US </a>
                </li>
            </ul>
            <ul class="navbar-nav mr-1">
                <li><a href="#" class="nav-link"> <i class="fa fa-envelope"></i> Email </a></li>
                <li><a href="#" class="nav-link"> <i class="fa fa-phone"></i> Call us </a></li>
            </ul>
        </div>
    </nav>
    <section class="header-main border-bottom">
        <div class="row align-items-center">
            <div class="col-lg-2 col-md-3 col-6">
                <a href="${pageContext.request.contextPath}/" class="ms-1 brand-wrap">
                    <img class="logo" src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo">
                </a>
            </div>
            <div class="col-lg col-sm col-md col-6 flex-grow-0">

                <div class="category-wrap dropdown d-inline-block float-right">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bars"></i> All category
                    </button>
                    <ul class="dropdown-menu" style="left: 0;">
                        <li> <a class="dropdown-item border-bottom" href="${pageContext.request.contextPath}/category?id=all">All
                            products</a></li>
                        <c:set var="categoryList" value="${applicationScope.categoryList}"/>
                        <c:if test="${categoryList != null}">
                            <c:forEach var="item" items="${categoryList}">
                                <li>
                                    <a class="dropdown-item border-bottom"
                                       href="${pageContext.request.contextPath}/category?id=${ item.getId()}"> ${ item.getTitle()}
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${categoryList == null}">
                            <li>No categories available.</li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/category?id=all" class="btn btn-outline-primary">Store</a>
            <div class="col-lg  col-md-6 col-sm-12 col">
                <form action="search" class="search" method="post">
                    <div class="input-group w-100">
                        <input type="text" name="txt" class="form-control" style="width:60%;" placeholder="Search"
                               value="${txtS}">

                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-sm-6 col-8 order-2 order-lg-3">
                <div class="d-flex justify-content-end mb-3 mb-lg-0">
                    <a href="${pageContext.request.contextPath}/secure/cart?userId=${sessionScope.user.getId()}" class="widget-header pl-3 mr-3">
                        <div class="icon icon-sm rounded-circle border"><i class="fa fa-shopping-cart"></i></div>
                        <span class="badge badge-pill badge-danger notify">0</span>
                    </a>
                    <c:set var="user" value="${sessionScope.user}"/>
                    <c:choose>
                        <c:when test="${user != null}">

                            <div class="dropdown" >
                                <button class="dropdown-button user rounded-circle">${user.getUsername().toUpperCase().charAt(0)}</button>
                                <ul class="dropdown-menu "  style="left: -50px">
                                   <li class="text-center border-bottom" > <a class="dropdown-item "  href="${pageContext.request.contextPath}/templates/edit.jsp">Edit profile</a></li>
                                   <li class="text-center border-bottom"> <a class="dropdown-item "  href="${pageContext.request.contextPath}/secure/saved">Saved</a></li>
                                 <li class="text-center border-bottom">   <a class="dropdown-item "  href="${pageContext.request.contextPath}/secure/history">History</a></li>
                                    <li class="text-center border-bottom"> <a class="dropdown-item "  href="${pageContext.request.contextPath}/secure/logout">Log out</a></li>
                                </ul>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="widget-header">
                                <small class="title text-muted">Welcome guest!</small>
                                <div>
                                    <a href="${pageContext.request.contextPath}/templates/login.jsp">Login</a> <span class="dark-transp"> | </span>
                                    <a href="${pageContext.request.contextPath}/templates/register.jsp"> Register</a>
                                </div>
                            </div>
                            <img style="height: 50px ; width: 50px ; border-radius: 60%; background-color: #1a56e9; margin: 0 20px;"
                                 src="${pageContext.request.contextPath}/static/images/avatars/guest.png">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</header>
