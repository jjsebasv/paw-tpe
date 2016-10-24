<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar-container" role="navigation">
  <div class="navbar-wrapper">
    <div class="navbar-element">
      <a class="navbar-brand" href="${pageContext.request.contextPath}">
        <img src="resources/assets/logo.svg">
      </a>
    </div>
    <div class="navbar-element">
      <select class="form-control course-select input-base course-on-nav" data-placeholder="Buscar una materia"></select>
    </div>
    <div class="navbar-element">
      <div class="row row-center">
        <security:authorize access="!isAuthenticated()">
          <a class="link nav-link m-right-1" href="login"><spring:message code="navbar.login"/> </a>
          <a class="link nav-link m-right-1" href="register"><spring:message code="navbar.register"/> </a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
          <div class="btn-group show-on-hover">
            <button type="button" class="btn btn-default dropdown-toggle dropdown-custom" data-toggle="dropdown">
              <img class="avatar" src="resources/assets/avatar_1.svg">
              <span> <security:authentication property="principal.username"/></span>
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
              <li>
                <a href="profile"><spring:message code="navbar.profile"/> </a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/uploadDocument"><spring:message code="navbar.upload"/> </a>
              </li>
              <li>
                <a href="logout"><spring:message code="navbar.logout"/> </a>
              </li>
            </ul>
          </div>
        </security:authorize>
      </div>
    </div>
  </div>
</nav>
