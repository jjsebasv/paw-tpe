<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<spring:htmlEscape defaultHtmlEscape="true"/>
<body>
  <%@ include file="navbar.jsp" %>
  <div class="content-wrapper">
    <div class="white-container file-container">
      <a href="" class="back-button">
        <img src="resources/assets/back.svg">
      </a>
      <h3 class="program-title">${document.subject}</h3>
      <div class="white-container-content">
        <div class="document-info">
          <p class="subtitle">Subido por: ${username}</p>
          <p class="subtitle">Calificacion: ${average}</p>
          <a class="download-button" href="download/${document.documentId}">
            Descargar
          </a>
          <a class="download-button" href="open/${document.documentId}" target="_blank">
            Ver
          </a>
        </div>
        <h4>Comentarios</h4>
        <ul class="reviews-wrapper">
          <c:forEach items="${reviews}" var="review">
            <li class="review-item row">
              <span class="light-text review-time">Hace 3hr</span>
              <img class="avatar" src="https://cdn1.iconfinder.com/data/icons/user-pictures/101/malecostume-512.png">
              <div class="column review-info-wrapper">
                <span class="ranking">${review.ranking}</span>
                <p>${review.review}</p>
                <span class="light-text">${review.user.name}</span>
              </div>
            </li>
          </c:forEach>
        </ul>

        <c:if test="${can_review}">
          <h4>Deja un comentario!</h4>
          <form:form method="POST" action="document/${document.documentId}/addReview" modelAttribute="reviewForm">
            <fieldset  class="review-form">
              <div class="form-group">
                <label>Calificacion:</label>
                <form:input path="ranking" type='number' value="5" min='1' max='5' step="1" class="form-control ranking-input" placeholder="Ranking"/>
              </div>
              <div class="form-group">
                <label>Comentario:</label>
                <form:textarea path="review" htmlEscape="true" class="form-control review-input"/>
              </div>
              <input type="submit" class="submit-button" value="Enviar"/>
              <!-- Change this to a button or input when using this as a form -->

            </fieldset>
          </form:form>
        </c:if>

      </div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
</body>
