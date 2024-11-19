<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>


<header class="section-header">
    <nav class="navbar p-md-0 navbar-expand-sm navbar-light border-bottom">
        <div class="container">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTop4"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTop4">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link"> English </a>

                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link"> USD </a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li><a href="#" class="nav-link"> <i class="fa fa-envelope"></i> Email </a></li>
                    <li><a href="#" class="nav-link"> <i class="fa fa-phone"></i> Call us </a></li>
                </ul> <!-- list-inline //  -->
            </div> <!-- navbar-collapse .// -->
        </div> <!-- container //  -->
    </nav>

    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-2 col-md-3 col-6">
                    <a href="${pageContext.request.contextPath}/" class="brand-wrap">
                        <img class="logo" src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo">
                    </a> <!-- brand-wrap.// -->
                </div>
                <div class="col-lg col-sm col-md col-6 flex-grow-0">
                    <div class="category-wrap dropdown d-inline-block float-right">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-bars"></i> All category
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/category?id=all">All
                                products</a>

                            <%
                                List<Category> categoryList = (List<Category>) application.getAttribute("categoryList");
                            %>

                            <c:if test="${categoryList != null}">
                                <c:forEach var="item" items="${categoryList}">
                                    <li>
                                        <a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/category?id=${ item.getId()}">
                                                ${ item.getTitle()}
                                        </a>
                                    </li>
                                </c:forEach>
                            </c:if>


                            <c:if test="${categoryList == null}">
                                <li>No categories available.</li>
                            </c:if>

                        </div>
                    </div>  <!-- category-wrap.// -->
                </div> <!-- col.// -->
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
                    </form> <!-- search-wrap .end// -->
                </div> <!-- col.// -->
                <div class="col-lg-3 col-sm-6 col-8 order-2 order-lg-3">
                    <div class="d-flex justify-content-end mb-3 mb-lg-0">
                        <div class="widget-header">
                            <small class="title text-muted">Welcome guest!</small>
                            <div>
                                <a href="templates/login.jsp">Login</a> <span class="dark-transp"> | </span>
                                <a href="templates/register.jsp"> Register</a>
                            </div>
                        </div>
                        <a href="./cart.html" class="widget-header pl-3 ml-3">
                            <div class="icon icon-sm rounded-circle border"><i class="fa fa-shopping-cart"></i></div>
                            <span class="badge badge-pill badge-danger notify">0</span>
                        </a>
                    </div> <!-- widgets-wrap.// -->
                </div> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- header-main .// -->


</header>
<!-- section-header.// -->
