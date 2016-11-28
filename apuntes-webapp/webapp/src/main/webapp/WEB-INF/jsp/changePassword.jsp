<%@ include file="header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="navbar.jsp" %>
<div class="register-form">
    <c:url value="/profile/change_password" var="postPath"/>
    <div class="content-wrapper">
        <div class="white-container">
            <h3 class="content-title"><spring:message code="register.title"/></h3>
            <div class="white-container-content">
                <form:form class="column left" modelAttribute="changePasswordForm" action="${postPath}" method="post">
                    <c:set var="oldPasswordHasBindError">
                        <form:errors path="oldPassword"/>
                    </c:set>
                    <c:set var="newPasswordHasBindError">
                        <form:errors path="newPassword"/>
                    </c:set>
                    <c:set var="repeatPasswordHasBindError">
                        <form:errors path="repeatPassword"/>
                    </c:set>

                    <div class="column left form-group input-base-wrapper ${not empty oldPasswordHasBindError?"has-error":""}">
                        <form:label path="oldPassword"><spring:message code="models.client.password.old"/>:
                        </form:label>
                        <form:input class="form-control" type="password" path="oldPassword"/>
                        <form:errors path="oldPassword" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty newPasswordHasBindError?"has-error":""}">
                        <form:label path="newPassword"><spring:message code="models.client.password.new"/>:
                        </form:label>
                        <form:input class="form-control" type="password" path="newPassword"/>
                        <form:errors path="newPassword" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty repeatPasswordHasBindError?"has-error":""}">
                        <form:label path="repeatPassword"><spring:message code="models.client.password.repeat"/>:
                        </form:label>
                        <form:input class="form-control" type="password" path="repeatPassword"/>
                        <form:errors path="repeatPassword" cssClass="help-block" element="p"/>
                    </div>

                    <div class="item-center">
                        <input class="base-button" type="submit"
                               value="<spring:message code="models.client.password.change"/>"/>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>