<%@ include file="../../header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="admin" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title"><spring:message code="models.programs"/></h3>
        <div class="white-container-content">
            <h4 class="course-size">
                <%--TODO Internacionalizacion. El numero deberia estar dentro del string--%>
                <c:out value="${entries.size()}"/> <spring:message code="program.course.size"/> <a href="admin/programs/create">+</a>
            </h4>
            <ul class="list-wrapper">


                <c:forEach items="${entries}" var="entry">

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/programs/${entry.programid}/edit"
                           class="list-group-item">
                            <span><c:out value="${entry.programid}"/></span>
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
