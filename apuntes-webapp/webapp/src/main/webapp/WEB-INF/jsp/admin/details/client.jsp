<%@ include file="../../header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <c:if test="${empty pk}">
                <h3 class="program-title"><spring:message code="admin.clients.create"/></h3>
            </c:if>
            <c:if test="${not empty pk}">
                <h3 class="program-title"><spring:message code="admin.clients.edit"/><c:out value="${pk}"/></h3>
            </c:if>

            <div class="white-container-content">

                <form:form class="column left" modelAttribute="clientForm" action="" method="post">
                    <c:set var="nameHasBindError">
                        <form:errors path="code"/>
                    </c:set>
                    <c:set var="emailHasBindError">
                        <form:errors path="name"/>
                    </c:set>

                    <div class="column left form-group input-base-wrapper ${not empty codeHasBindError?"has-error":""}">
                        <form:label path="code"><spring:message code="models.courses.code"/>: </form:label>
                        <form:input class="form-control" type="text" path="code"/>
                        <form:errors path="code" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty nameHasBindError?"has-error":""}">
                        <form:label path="name"><spring:message code="models.courses.name"/>: </form:label>
                        <form:input class="form-control" type="text" path="name"/>
                        <form:errors path="name" cssClass="help-block" element="p"/>
                    </div>

                    <div class="form-group item-center">
                        <button class="submit-button" type="submit" name="action" value="save">
                            <spring:message code="admin.model.save"/>
                        </button>
                        <button class="submit-button" type="submit" name="action" value="delete">
                            <spring:message code="admin.model.delete"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>