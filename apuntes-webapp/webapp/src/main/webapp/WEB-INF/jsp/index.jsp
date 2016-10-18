<%@ include file="header.jsp" %>
<body>
  <%@ include file="navbar.jsp" %>
  <section class="main-section">
    <div class='overlay'></div>
    <h1>apuntes paw</h1>
    <div class="separator"></div>
    <h2 class="m-bottom-5">descarga tus apuntes</h2>
    <a class="scroll-indicator-item" href="#about">
      <div class="scroll-indicator">
      </div>
    </a>
  </section>
  <section id="about" class="section-program">
    <h3>Carreras de grado</h3>
    <div class="separator orange"></div>
    <h4>Busca tus apuntes por carrera</h4>
    <div class="programs-wrapper">
      <c:forEach items="${programs}" var="program">
      <a class="program-item" href="program/${program.programid}">
        #${program.name}
      </a>
      </c:forEach>
    </div>
  </section>
  <%@ include file="footer.jsp" %>
</body>