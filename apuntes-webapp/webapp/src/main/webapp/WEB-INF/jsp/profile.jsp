<%@ include file="header.jsp" %>
<body>
  <%@ include file="navbar.jsp" %>  <div class="content-wrapper">
    <div class="white-container file-container">
      <a href="" class="back-button">
        <img src="resources/assets/back.svg">
      </a>
      <h3 class="program-title">${client.name}</h3>
      <div class="white-container-content">
      <div class="white-container-content">
        <h4 class="client-size">${documentsSize} Archivos</h4>
        <ul class="list-wrapper">
          <c:forEach items="${documents}" var="document">
          <li class="list-item">
            <div class="border"></div>
            <a href="document/${document.documentId}"
              class="list-group-item">
              <span>${document.subject}</span>
            </a>
          </li>
          </c:forEach>
        </ul>
        <%--@elvariable id="coursesSize" type="java.lang.Integer"--%>
        <c:if test="${documentsSize == 0}">
          <h4>No hay apuntes subidos!</h4>
        </c:if>
        <!-- FIXME: DELETE THIS br -->
        <br>
        <h4 class="client-size">${reviewsSize} Reviews</h4>
        <ul class="list-wrapper">
          <c:forEach items="${reviews}" var="review">
          <li class="list-item">
            <div class="border">${review.ranking}</div>
            <a href="document/${review.file.documentId}"
              class="list-group-item">
              <span><b>"${review.review}"</b> to </span>
              <span><i>${review.file.subject}</i></span>
            </a>
          </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
</body>
