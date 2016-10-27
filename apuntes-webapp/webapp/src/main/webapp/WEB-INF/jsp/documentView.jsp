<%@ include file="header.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<spring:htmlEscape defaultHtmlEscape="true"/>
<body>
  <%@ include file="navbar.jsp" %>
  <spring:message code="documentview.average" var="message_average" htmlEscape="true"/>
  <div class="content-wrapper">
    <div class="white-container file-container">
      <a href="" class="back-button">
        <img src="resources/assets/back.svg">
      </a>
      <h3 class="program-title"><c:out value="${document.subject}"/></h3>
      <div class="white-container-content">
        <div class="document-info">
          <p class="subtitle"><spring:message code="documentview.uploaded.by"/><c:out value=": ${username}"/></p>
          <p class="subtitle"><spring:message code="documentview.average"/><c:out value=": ${average}"/></p>
          <a class="download-button" href="download/${document.documentId}">
            <spring:message code="documentview.download"/>
          </a>
          <a class="download-button" href="open/${document.documentId}" target="_blank">
            <spring:message code="documentview.open"/>
          </a>
        </div>
        <h4><spring:message code="documentview.reviews"/> </h4>
        <ul class="reviews-wrapper">
          <c:forEach items="${reviews}" var="review">
          <li class="review-item column">
            <span class="light-text review-time"><time class="timeago" datetime="${review.dateUploaded}"/></span>
            <img class="avatar" src="https://cdn1.iconfinder.com/data/icons/user-pictures/101/malecostume-512.png">
            <div class="column review-info-wrapper">
              <span class="ranking"><c:out value="${review.ranking}"/></span>
              <p><c:out value="${review.review}"/></p>
              <span class="light-text"><c:out value="${review.user.name}"/></span>
            </div>
          </li>
          </c:forEach>
        </ul>
        <c:if test="${can_review}">
          <h4><spring:message code="documentview.write.review"/> </h4>
          <form:form method="POST" action="document/${document.documentId}/addReview" modelAttribute="reviewForm">
            <fieldset  class="review-form">
              <div class="form-group column left">
                <label><spring:message code="documentview.average"/> :</label>
                <form:input path="ranking" type='number' value="5" min='1' max='5' step="1" class="form-control ranking-input" placeholder="${message_average}"/>
                </div>
                <div class="column left m-bottom-5">
                  <label><spring:message code="documentview.review"/>:</label>
                  <form:textarea path="review" htmlEscape="true" class="form-control review-input"/>
                  </div>
                  <input type="submit" class="submit-button" value="<spring:message code="documentview.submit"/>"/>
                  <!-- Change this to a button or input when using this as a form -->
                </fieldset>
            </form:form>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
</body>
