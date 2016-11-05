<%@ include file="../../header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title"><spring:message code="models.programs"/></h3>
        <div class="white-container-content">
            <h4 class="course-size">
                <c:out value="${entries.size()}"/> <spring:message code="program.course.size"/> <a href="admin/courses/create">+</a>
            </h4>
            <ul class="list-wrapper">


                <c:forEach items="${entries}" var="entry">

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/courses/${entry.courseid}/edit"
                           class="list-group-item">
                            <span><c:out value="${entry.courseid}"/></span>
                            <span><c:out value="${entry.name}"/></span>
                        </a>
                    </li>

                </c:forEach>

            </ul>
            <c:if test="${entries.size() == 0}">
                <h4><spring:message code="profile.no.documents"/></h4>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
