<%@ include file="../../header.jsp" %>
<%@ include file="../header.admin.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="admin/courseprogramrelation/list" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <h3 class="content-title"><spring:message code="models.coursetoprogramrelation.create"/></h3>

            <div class="white-container-content">

                <spring:message code="uploadview.search.course" var="message_searchCourse" htmlEscape="true"/>
                <form:form class="column left" method="POST" action="" modelAttribute="courseProgramRelationCreateForm">
                    <c:set var="programidHasBindError">
                        <form:errors path="programid"/>
                    </c:set>
                    <c:set var="courseidHasBindError">
                        <form:errors path="courseid"/>
                    </c:set>
                    <c:set var="semesterHasBindError">
                        <form:errors path="semester"/>
                    </c:set>

                    <div class="form-group column left input-base-wrapper ${not empty programidHasBindError?"has-error":""}">
                        <form:label path="programid"><spring:message code="models.coursetoprogramrelation.program"/>:
                        </form:label>
                        <select name="programid" class="form-control">
                            <c:forEach var="program" items="${programs}">
                                <option value="${program.programid}">${program.name}</option>
                            </c:forEach>
                        </select>
                        <form:errors path="programid" cssClass="help-block" element="p"/>
                    </div>

                    <div class="form-group column left input-base-wrapper ${not empty courseidHasBindError?"has-error":""}">
                        <form:label path="courseid"><spring:message code="models.coursetoprogramrelation.course"/>:
                        </form:label>
                        <form:select path="courseid" class="form-control course-select input-base" readonly="true"
                                     data-placeholder="${message_searchCourse}">
                        </form:select>
                        <form:errors path="courseid" cssClass="help-block" element="p"/>
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
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>