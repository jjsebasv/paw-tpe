<%@ include file="../../header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="admin/courseprogramrelation/list" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <h3 class="content-title"><spring:message code="models.coursetoprogramrelation.edit"
                                                      arguments="${program},${course}"/></h3>

            <div class="white-container-content">

                <spring:message code="uploadview.search.course" var="message_searchCourse" htmlEscape="true"/>
                <form:form class="column left" method="POST" action="" modelAttribute="courseProgramRelationUpdateForm">

                    <c:set var="semesterHasBindError">
                        <form:errors path="semester"/>
                    </c:set>

                    <div class="form-group column left input-base-wrapper">
                        <label for="program"><spring:message code="models.coursetoprogramrelation.program"/>:</label>
                        <input type="text" class="form-control" value="${program}" id="program" readonly="readonly">
                    </div>

                    <div class="form-group column left input-base-wrapper">
                        <label for="course"><spring:message code="models.coursetoprogramrelation.course"/>:</label>
                        <input type="text" class="form-control" value="${course}" id="course" readonly="readonly">
                    </div>

                    <div class="column left form-group input-base-wrapper ${not empty semesterHasBindError?"has-error":""}">
                        <form:label path="semester"><spring:message code="models.coursetoprogramrelation.semester"/>:
                        </form:label>
                        <form:input class="form-control" type="text" path="semester"/>
                        <form:errors path="semester" cssClass="help-block" element="p"/>
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