<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="navbar" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${pageContext.request.contextPath}">
      <img src="resources/assets/logo.svg">
    </a>
  </div>
  <div class="navbar-element search-desktop">
    <spring:message code="navbar.search.course" var="message_search_course" htmlEscape="true"/>
  <select class="form-control course-select input-base course-on-nav" data-placeholder="${message_search_course}"></select>
</div>
<div class="navbar-collapse collapse sidebar-navbar-collapse navbar-right">
  <ul class="nav navbar-nav">
    <li class="search-mobile">
      <spring:message code="navbar.search.course" var="message_search_course" htmlEscape="true"/>
    <select class="form-control course-select input-base course-on-nav" data-placeholder="${message_search_course}"></select>
  </li>
  <li>
    <security:authorize access="!isAuthenticated()">
    <a class="link nav-link m-right-1" href="login"><spring:message code="navbar.login"/> </a>
    </security:authorize>
  </li>
  <security:authorize access="!isAuthenticated()">
  <li>
    <a class="link nav-link m-right-1" href="register"><spring:message code="navbar.register"/> </a>
  </li>
  </security:authorize>
  <security:authorize access="isAuthenticated()">
  <li class="dropdown search-desktop">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
      <span class="line-h-5">
        <security:authentication property="principal.username"/>
      </span><img class="arrow-down" src="resources/assets/arrow_down.svg"></a>
      <ul class="dropdown-menu">
        <li><a href="profile"><spring:message code="navbar.profile"/> </a></li>
        <li><a href="${pageContext.request.contextPath}/uploadDocument"><spring:message code="navbar.upload"/> </a></li>
        <li class="divider"></li>
        <li class="nav-header"><a href="logout"><spring:message code="navbar.logout"/> </a></li>
      </ul>
    </li>
    </security:authorize>
  <security:authorize access="isAuthenticated()">
    <div class="search-mobile">
      <li><a href="profile"><spring:message code="navbar.profile"/> </a></li>
      <li><a href="${pageContext.request.contextPath}/uploadDocument"><spring:message code="navbar.upload"/> </a></li>
      <li class="nav-header"><a href="logout"><spring:message code="navbar.logout"/> </a></li>
    </div>
    </security:authorize>
  </ul>
  </div><!--/.nav-collapse -->
</div>