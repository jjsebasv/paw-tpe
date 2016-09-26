<%@ include file="header.jsp" %>

<body>
  <%@ include file="navbar.jsp" %>
  <div class="content-wrapper">
    <h3 class="program-title">#${program.name}</h3>
    <h4 class="course-size">${coursesSize} Materias</h4>
    <ul class="list-wrapper">
      <c:forEach items="${courses}" var="course">
        <li class="list-item">
          <div class="border"></div>
          <a href="<%=request.getContextPath()%>/course/${course.code}"
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
  <%@ include file="footer.jsp" %>
</body>
