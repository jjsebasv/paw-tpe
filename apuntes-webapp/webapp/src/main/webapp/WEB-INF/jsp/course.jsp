<%@ include file="header.jsp" %>

<body>
<%@ include file="navbar.jsp" %>
  <div class="content-wrapper">
    <div class=white-container>
      <h3 class="program-title">#${course.name}</h3>
      <div class="white-container-content">
        <h4 class="course-size">${documentsSize} Archivos</h4>
        <ul class="list-wrapper">
          <c:forEach items="${documents}" var="document">
            <li class="list-item">
              <div class="border"></div>
              <a href="${pageContext.request.contextPath}/document/${document.documentId}"
                 class="list-group-item">
                 <span>${document.subject}</span>
              </a>
            </li>
          </c:forEach>
        </ul>
        <c:if test="${coursesSize == 0}">
          <h4>No hay apuntes cargados!</h4>
        </c:if>
      </div>
    </div>
  </div>
  <%@ include file="footer.jsp" %>
</body>
