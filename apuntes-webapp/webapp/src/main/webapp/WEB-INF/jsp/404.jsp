<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<%@ include file="header.jsp" %>
<body >
<section id="error-page" >
  <div class="error-page-inner">
    <div class="container-fluid" >
      <div class="row">
        <div class="col-sm-12">
          <div class="text-center">
            <div class="bg-404">
              <div class="error-image">
                <img class="img-responsive" src="resources/images/404.png" alt="">
              </div>
            </div>
            <h2><spring:message code="errorPage.title"/> </h2>
            <p><spring:message code="errorPage.description"/></p>
            <a href="" class="btn btn-error"><spring:message code="errorPage.button"/></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>