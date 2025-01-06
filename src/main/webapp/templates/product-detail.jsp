<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>GreatKart | One of the Biggest Online Shopping Platform</title>
<jsp:include page="headerResource.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/navbar.css" type="text/css"/>
</head>
<body>
<!-- Navbar -->
 <%@ include file="includes/navbar.jsp" %>
<c:set var="singleProduct" value="${requestScope.product}" />
 <section class="section-content padding-y bg mt-5">
     <div class="container">
         <!-- ============================ COMPONENT 1 ================================= -->
         <div class="card">
             <div class="row no-gutters">
                 <aside class="col-md-6">
                     <article class="gallery-wrap">
                         <div class="img-big-wrap">
                             <a href="#"><img src="${pageContext.request.contextPath}${singleProduct.getPhoto()}" alt="${singleProduct.getName()}"></a>
                         </div> <!-- img-big-wrap.// -->
                     </article> <!-- gallery-wrap .end// -->
                 </aside>
                 <main class="col-md-6 border-left">
                     <article class="content-body">

                         <h2 class="title"><c:out value="${singleProduct.getName()}"/></h2>

                         <div class="mb-3">
                             <var class="price h4"> ${singleProduct.getPrice()}</var>
                         </div>

                         <p>${singleProduct.getDescription()}</p>

                         <!-- item-option-select -->
                         <hr>
                         <hr>
                         <div class="row">
                             <div class="item-option-select">
                                 <h6>Choose Color</h6>
                                 <div class="btn-group btn-group-sm btn-group-toggle" data-toggle="buttons">
                                     <label class="btn btn-light">
                                         <input type="radio" name="radio_color"> Silver
                                     </label>
                                     <label class="btn btn-light">
                                         <input type="radio" name="radio_color" > Gray
                                     </label>
                                     <label class="btn btn-light active">
                                         <input type="radio" name="radio_color checked"> Gold
                                     </label>
                                     <label class="btn btn-light">
                                         <input type="radio" name="radio_color"> Black
                                     </label>
                                 </div>
                             </div>
                         </div> <!-- row.// -->
                         <div class="row">
                             <div class="item-option-select">
                                 <h6>Select Size</h6>
                                 <div class="btn-group btn-group-sm btn-group-toggle" data-toggle="buttons">
                                     <label class="btn btn-light">
                                         <input type="radio" name="radio_size"> S
                                     </label>
                                     <label class="btn btn-light active">
                                         <input type="radio" name="radio_size" checked> M
                                     </label>
                                     <label class="btn btn-light">
                                         <input type="radio" name="radio_size"> L
                                     </label>
                                     <label class="btn btn-light">
                                         <input type="radio" name="radio_size"> XL
                                     </label>
                                 </div>
                             </div>
                         </div> <!-- row.// -->
                         <hr>
<%--                         <a href="addToCart" class="btn  btn-primary"> <span class="text">Add to cart</span> <i class="fas fa-shopping-cart"></i>  </a>--%>
                         <form action="${pageContext.request.contextPath}/addToCart" method="post">
                             <input type="hidden" name="productId" value="${singleProduct.getId()}">
                             <input type="number" name="quantity" min="1" value="1" class="form-control mb-2" required>
                             <button type="submit" class="btn btn-primary">
                                 <span class="text">Add to cart</span>
                                 <i class="fas fa-shopping-cart"></i>
                             </button>
                             <a href="secure/saved?id=${singleProduct.getId()}"> <i class="fas fa-heart"></i></a>
                         </form>
                     </article> <!-- product-info-aside .// -->
                 </main> <!-- col.// -->
             </div> <!-- row.// -->
         </div> <!-- card.// -->
         <br>
     </div> <!-- container .// -->
 </section>
 <!-- ========================= SECTION CONTENT END// ========================= -->
 <!-- Footer -->
<script src="${pageContext.request.contextPath}/js/navbar.js"></script>
<%@ include file="includes/footer.jsp" %>
 </body>

