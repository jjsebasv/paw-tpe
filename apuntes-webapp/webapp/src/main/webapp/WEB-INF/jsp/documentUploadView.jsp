<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<body>
<%@ include file="navbar.jsp" %>
<div class="register-form">
    <c:url value="/uploadDocument/finish" var="postPath"/>

    <div class="row margin-top">

        <div class="col-xs-4 col-xs-offset-4">
            <form:form method="POST" action="${postPath}" enctype="multipart/form-data" modelAttribute="documentForm">

                <c:set var="subjectHasBindError">
                    <form:errors path="subject"/>
                </c:set>
                <c:set var="courseidHasBindError">
                    <form:errors path="courseid"/>
                </c:set>

                <div class="form-group ${not empty subjectHasBindError?"has-error":""}">
                    <form:label path="subject"><spring:message code="uploadview.subject"/>: </form:label>
                    <form:textarea path="subject" htmlEscape="true" class="form-control" placeholder="Subject"/>
                    <form:errors path="subject" cssClass="help-block" element="p"/>
                </div>

                <div class="form-group ${not empty courseidHasBindError?"has-error":""}">
                    <form:label path="courseid"><spring:message code="uploadview.course"/>: </form:label>
                    <form:select path="courseid" class="form-control course-select"
                                 data-placeholder="Buscar una materia"/>
                    <form:errors path="courseid" cssClass="help-block" element="p"/>
                </div>

                <div class="form-group">
                    <label for="file"><spring:message code="uploadview.file"/>: </label>
                    <input type='file' class="form-control" name="document" placeholder="Document" id="file"/>
                </div>


                <input class="submit-button" type="submit" value="Subir"/>

            </form:form>
        </div>
    </div>
  </div>
<%@ include file="footer.jsp" %>
</body>
