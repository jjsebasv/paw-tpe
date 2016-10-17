<%@ include file="header.jsp" %>

<body>
  <%@ include file="navbar.jsp" %>
  <section class="main-section">
    <div class='overlay'></div>
    <h1>APUNTES PAW</h1>
    <div class="separator"></div>
    <h2>Descarga tus apuntes</h2>
    <br>

    <a href="#about" class="btn btn-dark btn-lg">
      <i class="fa fa- fa-chevron-down fa-fw fa-1x"></i>
    </a>
  </section>
  <section id="about" class="section-program">
    <h3>Carreras de grado</h3>
    <div class="separator"></div>
    <h4>Busca tus apuntes por carrera</h4>
    <div class="programs-wrapper">
      <c:forEach items="${programs}" var="program">
	      <a class="program-item" href="program/${program.programid}">
    	      #${program.name}
          </a>
      </c:forEach>
    </div>
  </section>
  <a id="to-top" href="#top" class="btn btn-dark btn-lg"><i class="fa fa- fa-chevron-up fa-fw fa-1x"></i></a>
  <%@ include file="footer.jsp" %>
</body>