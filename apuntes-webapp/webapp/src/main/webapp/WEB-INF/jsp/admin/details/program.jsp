<%@ include file="../../header.jsp" %>
<%@ include file="../header.admin.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="admin/programs/list" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <c:if test="${empty pk}">
                <h3 class="content-title"><spring:message code="models.program.create"/></h3>
            </c:if>
            <c:if test="${not empty pk}">
                <h3 class="content-title"><spring:message code="models.program.edit" arguments="${pk}"/></h3>
            </c:if>

            <div class="white-container-content">

                <form:form class="column left" modelAttribute="programForm" action="" method="post">
                    <c:set var="nameHasBindError">
                        <form:errors path="name"/>
                    </c:set>
                    <c:set var="shortnameHasBindError">
                        <form:errors path="shortName"/>
                    </c:set>
                    <c:set var="groupHasBindError">
                        <form:errors path="group"/>
                    </c:set>

                    <div class="column left form-group input-base-wrapper ${not empty nameHasBindError?"has-error":""}">
                        <form:label path="name"><spring:message code="models.program.name"/>: </form:label>
                        <form:input class="form-control" type="text" path="name"/>
                        <form:errors path="name" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty shortnameHasBindError?"has-error":""}">
                        <form:label path="shortName"><spring:message code="models.program.shortname"/>: </form:label>
                        <form:input class="form-control" type="text" path="shortName"/>
                        <form:errors path="shortName" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty groupHasBindError?"has-error":""}">
                        <form:label path="group"><spring:message code="models.program.group"/>: </form:label>
                        <form:input class="form-control" type="text" path="group"/>
                        <form:errors path="group" cssClass="help-block" element="p"/>
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