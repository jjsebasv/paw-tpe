<%@ include file="header.jsp" %>

<body>
  <%@ include file="navbar.jsp" %>
  <div class="content-wrapper">
    <div class=white-container>
      <h3 class="program-title">#${program.name}</h3>
      <div class="white-container-content">
        <h4 class="course-size">${coursesSize} Materias</h4>
        <ul class="list-wrapper">
          <c:forEach items="${courses}" var="course">
            <li class="list-item">
              <div class="border"></div>
              <a href="${pageContext.request.contextPath}/course/${course.code}"
                 class="list-group-item">
                 <span>${course.code}</span>
                 <span>${course.name}</span>
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
