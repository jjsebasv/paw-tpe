<%@ include file="header.jsp" %>
<body>
  <%@ include file="navbar.jsp" %>
  <section class="main-section">
    <div class='overlay'></div>

    <h1><spring:message code="index.title"/></h1>
    <div class="separator"></div>
    <h2 class="m-bottom-5"><spring:message code="index.subtitle"/></h2>
    <a class="scroll-indicator-item" href="#about">
      <div class="scroll-indicator">
      </div>
    </a>
  </section>
  <section id="about" class="section-program">
    <h3><spring:message code="index.bottom.title"/></h3>
    <div class="separator orange"></div>
    <h4><spring:message code="index.bottom.subtitle"/></h4>
    <div class="programs-wrapper">
      <c:forEach items="${programs}" var="program">
      <a class="program-item" href="program/${program.programid}">
        <c:out value="\#${program.name}"/>
      </a>
      </c:forEach>
    </div>
  </section>
  <%@ include file="footer.jsp" %>
</body>
