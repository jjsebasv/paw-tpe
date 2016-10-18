<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
  <%@ include file="../navbar.jsp" %>
  <div class="login-form">
    <div class="content-wrapper">
      <div class="white-container">
        <a href="" class="back-button">
          <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title">Inicia sesion</h3>
        <div class="white-container-content">
          <c:url value="/login" var="loginUrl"/>
          <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
            <c:if test="${not empty error}">
            <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
            </c:if>
            <div class="form-group column left">
              <label class="control-label" for="username">Usuario</label>
              <input type="text" class="form-control input-base" name="j_username" id="username">
            </div>
            <div class="form-group column left">
              <label class="control-label" for="password">Password</label>
              <input type="password" class="form-control input-base" name="j_password" id="password">
            </div>
            <div class="form-group column left">
              <input class="submit-button" type="submit" value="Ingresar"/>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="../footer.jsp" %>
</body>