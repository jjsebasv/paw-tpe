<%@ include file="header.jsp" %>
<body>
  <%@ include file="navbar.jsp" %>  <div class="content-wrapper">
    <div class="white-container file-container">
      <a href="" class="back-button">
        <img src="resources/assets/back.svg">
      </a>
      <h3 class="content-title"><c:out value="${client.name}"/></h3>
      <div class="white-container-content">
      <div class="white-container-content">
        <h4 class="client-size"><c:out value="${documentsSize}"/> <spring:message code="profile.document.size"/></h4>
        <ul class="list-wrapper">
          <c:forEach items="${documents}" var="document">
          <li class="list-item">
            <div class="border"></div>
            <a href="document/${document.documentId}"
              class="list-group-item">
              <span><c:out value="${document.subject}"/></span>
            </a>
          </li>
          </c:forEach>
        </ul>
        <%--@elvariable id="coursesSize" type="java.lang.Integer"--%>
        <c:if test="${documentsSize == 0}">
          <h4><spring:message code="profile.no.documents"/></h4>
        </c:if>
        <!-- FIXME: DELETE THIS br -->
        <br>
        <h4 class="client-size"><c:out value="${reviewsSize}"/> <spring:message code="profile.reviews"/></h4>
        <ul class="list-wrapper">
          <c:forEach items="${reviews}" var="review">
          <li class="list-item">
            <div class="border"><c:out value="${review.ranking}"/></div>
            <a href="document/${review.file.documentId}"
              class="list-group-item">
              <span><b><c:out value="${review.review}"/></b> <spring:message code="profile.review.to"/> </span>
              <span><i><c:out value="${review.file.subject}"/></i></span>
            </a>
          </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
</body>
