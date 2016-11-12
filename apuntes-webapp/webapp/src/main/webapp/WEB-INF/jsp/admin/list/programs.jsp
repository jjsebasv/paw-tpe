<%@ include file="../../header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="admin" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="content-title"><spring:message code="models.programs"/></h3>
        <div class="white-container-content">
            <h4 class="course-size">
                <c:if test="${totalRows==1}">
                    <spring:message code="models.program.size" arguments="${totalRows}"/> <a
                        href="admin/programs/create">+</a>
                </c:if>
                <c:if test="${totalRows>1}">
                    <spring:message code="models.program.size.plural" arguments="${totalRows}"/> <a
                        href="admin/programs/create">+</a>
                </c:if>
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

            <%@ include file="pagination.jsp" %>

            <c:if test="${totalRows == 0}">
                <h4><spring:message code="models.program.empty"/></h4>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
