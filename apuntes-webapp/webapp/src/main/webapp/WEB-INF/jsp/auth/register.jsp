<%@ include file="../header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
  <%@ include file="../navbar.jsp" %>
  <div class="register-form">
    <c:url value="/register" var="postPath"/>
    <div class="content-wrapper">
      <div class="white-container">
        <a href="" class="back-button">
          <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title"><spring:message code="register.title"/></h3>
        <div class="white-container-content">
          <form:form class="column left" modelAttribute="registerForm" action="${postPath}" method="post">
            <c:set var="usernameHasBindError">
            <form:errors path="username"/>
              </c:set>
              <c:set var="emailHasBindError">
              <form:errors path="email"/>
                </c:set>
                <c:set var="passwordHasBindError">
                <form:errors path="password"/>
                  </c:set>
                  <c:set var="repeatPasswordHasBindError">
                  <form:errors path="repeatPassword"/>
                    </c:set>
                    <div class="column left form-group input-base-wrapper ${not empty usernameHasBindError?"has-error":""}">
                    <form:label path="username"><spring:message code="register.username"/>: </form:label>
                    <form:input class="form-control" type="text" path="username"/>
                      <form:errors path="username" cssClass="help-block" element="p"/>
                      </div>
                      <div class="column left form-group input-base-wrapper ${not empty emailHasBindError?"has-error":""}">
                      <form:label path="email"><spring:message code="register.email"/>: </form:label>
                      <form:input class="form-control" type="email" path="email"/>
                        <form:errors path="email" cssClass="help-block" element="p"/>
                        </div>
                        <div class="column left form-group input-base-wrapper ${not empty passwordHasBindError?"has-error":""}">
                        <form:label path="password"><spring:message code="register.password"/>: </form:label>
                        <form:input class="form-control" type="password" path="password"/>
                          <form:errors path="password" cssClass="help-block" element="p"/>
                          </div>
                          <div class="column left form-group input-base-wrapper ${not empty repeatPasswordHasBindError?"has-error":""}">
                          <form:label path="repeatPassword"><spring:message code="register.repeat.password"/>: </form:label>
                          <form:input class="form-control" type="password" path="repeatPassword"/>
                            <form:errors path="repeatPassword" cssClass="help-block" element="p"/>
                            </div>
                            <div class="form-group item-center">
                              <input class="submit-button" type="submit" value="<spring:message code="register.submit"/>"/>
                            </div>
                          </form:form>
                        </div>
                      </div>
                    </div>
                  </div>
                  <%@ include file="../footer.jsp" %>
                </body>