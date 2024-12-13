<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="section-header container-fluid fixed-top mb-4 bg-white">
    <section class="header-main border-bottom">
        <div class="row  d-flex justify-content-between">
            <div class="col-lg-2 col-md-3 col-6">
                <a href="${pageContext.request.contextPath}/" class="ms-1 brand-wrap">
                    <img class="logo" src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo">
                </a>
            </div>

            <div class="col-lg-3 col-sm-6 col-8 order-2 order-lg-3">
                <div class="d-flex justify-content-end mb-3 mb-lg-0">
                    <a href="./cart.html" class="widget-header pl-3 mr-3">
                        <div class="icon icon-sm rounded-circle border d-flex justify-content-center align-items-center"><i class="fa fa-shopping-cart"></i></div>
                        <span class="badge badge-pill badge-danger notify">0</span>
                    </a>
                    <c:set var="user" value="${sessionScope.user}"/>
                    <c:choose>
                        <c:when test="${user != null}">

                            <div class="dropdown" style="margin-left: auto; margin-right: auto;">
                                <button class="dropdown-button user rounded-circle">${user.getUsername().toUpperCase().charAt(0)}</button>
                                <div class="dropdown-menu">
                                    <a href="${pageContext.request.contextPath}/templates/edit.jsp">Edit profile</a>
                                    <a href="${pageContext.request.contextPath}/secure/saved">Saved</a>
                                    <a href="${pageContext.request.contextPath}/secure/history">History</a>
                                    <a href="${pageContext.request.contextPath}/secure/logout">Log out</a>
                                </div>
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
