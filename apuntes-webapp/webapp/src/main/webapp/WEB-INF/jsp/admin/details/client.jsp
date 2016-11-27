<%@ include file="../../header.jsp" %>
<%@ include file="../header.admin.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="/admin/clients/list" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <c:if test="${empty pk}">
                <h3 class="content-title"><spring:message code="models.client.create"/></h3>
            </c:if>
            <c:if test="${not empty pk}">
                <h3 class="content-title"><spring:message code="models.client.edit" arguments="${pk}"/></h3>
            </c:if>

            <div class="white-container-content">

                <form:form class="column left" modelAttribute="clientForm" action="" method="post">
                    <c:set var="usernameHasBindError">
                        <form:errors path="username"/>
                    </c:set>
                    <c:set var="emailHasBindError">
                        <form:errors path="email"/>
                    </c:set>
                    <c:set var="passwordHasBindError">
                        <form:errors path="password"/>
                    </c:set>
                    <c:set var="roleHasBindError">
                        <form:errors path="role"/>
                    </c:set>


                    <div class="column left form-group input-base-wrapper ${not empty usernameHasBindError?"has-error":""}">
                        <form:label path="username"><spring:message code="models.client.username"/>: </form:label>
                        <form:input class="form-control" type="text" path="username"/>
                        <form:errors path="username" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty emailHasBindError?"has-error":""}">
                        <form:label path="email"><spring:message code="models.client.email"/>: </form:label>
                        <form:input class="form-control" type="email" path="email"/>
                        <form:errors path="email" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty passwordHasBindError?"has-error":""}">
                        <form:label path="password"><spring:message code="models.client.password"/>: </form:label>
                        <form:input class="form-control" type="password" path="password"/>
                        <form:errors path="password" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty roleHasBindError?"has-error":""}">
                        <form:label path="role"><spring:message code="models.client.role"/>:</form:label>
                        <form:select path="role" items="${roleOptions}"/>
                        <form:errors path="role" cssClass="help-block" element="p"/>
                    </div>


                    <div class="form-group item-center">
                        <button class="submit-button" type="submit" name="action" value="save">
                            <spring:message code="models.save"/>
                        </button>
                        <button class="submit-button" type="submit" name="action" value="delete">
                            <spring:message code="models.delete"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>