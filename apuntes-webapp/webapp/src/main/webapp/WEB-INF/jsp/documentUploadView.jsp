<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<body>
<%@ include file="navbar.jsp" %>
<div class="upload-document-form">
    <form:form method="POST" action="uploadDocument/finish" enctype="multipart/form-data" modelAttribute="documentForm">
        <fieldset>
            <div class="form-group">
                <form:textarea path="subject" htmlEscape="true" class="form-control" placeholder="Subject"/>

                <form:select path="courseid" class="form-control course-select" data-placeholder="Buscar una materia">
                    <%--<c:forEach var="course" items="${courses}">--%>
                    <%--<form:option value="${course.courseid}">--%>
                    <%--${course.code} - ${course.name}--%>
                    <%--</form:option>--%>
                    <%--</c:forEach>--%>
                </form:select>
                <div class="form-group">
                    <input type='file' class="form-control <%--filestyle--%>" name="document" placeholder="Document"/>
                </div>
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit"/>
                <!-- Change this to a button or input when using this as a form -->

            </div>
        </fieldset>
    </form:form>
</div>

<%@ include file="footer.jsp" %>
</body>
