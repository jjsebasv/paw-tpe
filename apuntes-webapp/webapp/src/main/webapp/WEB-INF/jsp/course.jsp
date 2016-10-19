<%@ include file="header.jsp" %>

<body>
<%@ include file="navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title">${course.code} #${course.name}</h3>
        <div class="white-container-content">
            <h4 class="course-size">${documentsSize}
                <c:if test="${coursesSize == 1}">
                    <spring:message code="course.document"/>
                </c:if>
                <c:if test="${coursesSize != 1}">
                    <spring:message code="course.documents"/>
                </c:if>
            </h4>

            <ul class="list-wrapper">
                <c:forEach items="${documents}" var="document">
                    <li class="list-item">
                        <div class="border"></div>
                        <a href="document/${document.documentId}"
                           class="list-group-item">
                            <span>${document.subject}</span>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            <%--@elvariable id="coursesSize" type="java.lang.Integer"--%>
            <c:if test="${coursesSize == 0}">
                <h4><spring:message code="course.no.documents"/></h4>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
