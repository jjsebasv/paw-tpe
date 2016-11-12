<%@ include file="../../header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="admin/documents/list" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <c:if test="${empty pk}">
                <h3 class="content-title"><spring:message code="models.document.create"/></h3>
            </c:if>
            <c:if test="${not empty pk}">
                <h3 class="content-title"><spring:message code="models.document.edit" arguments="${pk}"/></h3>
            </c:if>

            <div class="white-container-content">

                <spring:message code="uploadview.search.course" var="message_searchCourse" htmlEscape="true"/>
                <form:form class="column left" method="POST" action="" enctype="multipart/form-data"
                           modelAttribute="documentForm">
                    <c:set var="subjectHasBindError">
                        <form:errors path="subject"/>
                    </c:set>
                    <c:set var="courseidHasBindError">
                        <form:errors path="courseid"/>
                    </c:set>
                    <div class="form-group column left input-base-wrapper ${not empty subjectHasBindError?"has-error":""}">
                        <form:label path="subject"><spring:message code="models.document.subject"/>: </form:label>
                        <form:textarea path="subject" htmlEscape="true" class="form-control input-base"/>
                        <form:errors path="subject" cssClass="help-block" element="p"/>
                    </div>
                    <div class="form-group column left input-base-wrapper ${not empty courseidHasBindError?"has-error":""}">
                        <form:label path="courseid"><spring:message code="models.document.course"/>: </form:label>
                        <form:select path="courseid" class="form-control course-select input-base"
                                     data-placeholder="${message_searchCourse}">
                            <form:option value="${course.courseid}" label="${course.code} - ${course.name}"/>
                        </form:select>
                        <form:errors path="courseid" cssClass="help-block" element="p"/>
                    </div>
                    <div class="form-group column left input-base-wrapper">
                        <label for="file">
                            <spring:message code="models.document.file"/>:
                        </label>
                        <input type='file' class="form-control input-base" name="multipartFile"
                               placeholder="<spring:message code="models.document.document"/>" id="file"/>
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