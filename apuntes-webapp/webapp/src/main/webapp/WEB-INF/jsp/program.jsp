<%@ include file="header.jsp" %>

<body>
  <%@ include file="navbar.jsp" %>
  <div class="row">
      <nav class="breadcrumb">
          <a class="breadcrumb-item" href="<%=request.getContextPath()%>"><i class="fa fa-home"></i> / </a>
          <span class="breadcrumb-item active">${program.name}</span>

      </nav>
  </div>
  <div class="default-list">
  	<c:forEach items="${courses}" var="course">
          <a href="<%=request.getContextPath()%>/course/${course.code}"
             class="list-group-item">
             <span class="list-item">
             	${course.name}
             </span>
          </a>
 	</c:forEach>
  </div>
  <%@ include file="footer.jsp" %>
</body>
