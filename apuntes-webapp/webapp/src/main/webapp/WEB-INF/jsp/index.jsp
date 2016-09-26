<%@ include file="header.jsp" %>

<body>
  <%@ include file="navbar.jsp" %>
  <section class="main-section">
    <div class='overlay'></div>
    <h1>Lorem ipsum</h1>
    <div class="separator"></div>
    <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit</h2>
  </section>
  <section class="section-program">
    <h3>Carreras de grado</h3>
    <div class="separator"></div>
    <h4>Lorem ipsum dolor sit amet</h4>
    <div class="programs-wrapper">
      <c:forEach items="${programs}" var="program">
	      <a class="program-item" href="<%=request.getContextPath()%>/program/${program.programid}">
    	      #${program.name}
          </a>
      </c:forEach>
    </div>
  </section>
  <%@ include file="footer.jsp" %>
</body>