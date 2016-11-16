<%@ include file="header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="content-title"><c:out value="${course.code} \#${course.name}"/></h3>
        <div class="white-container-content">
            <h4 class="course-size">
                <c:if test="${documentsSize == 1}">
                    <c:out value="${documentsSize}"/>
                    <spring:message code="course.document"/>
                </c:if>
                <c:if test="${documentsSize > 1}">
                    <c:out value="${documentsSize}"/>
                    <spring:message code="course.documents"/>
                </c:if>
                <c:if test="${documentsSize == 0 }">
                    <spring:message code="course.no.documents"/>
                    <a href="${pageContext.request.contextPath}/uploadDocument?course=${course.code}">
                        <button type="button" class="btn btn-default default-btn">
                            <spring:message code="course.first"/>
                        </button>
                    </a>
                </c:if>
            </h4>
            <ul class="list-wrapper">
                <c:forEach items="${documents}" var="document">
                    <li class="list-item" style="height: 100px">
                        <div class="border" style="height: 100px"></div>
                        <a href="document/${document.documentId}"
                           class="list-group-item" style="height:100px">
                            <span><c:out value="${document.subject}"/></span>
                            <span class="light-text review-time"><time class="moment-ago"
                                                                       datetime="${document.dateUploaded}"></time></span>
                        <span style="display: block;margin-top: 20px;font-weight: 400"><c:out value="${document.description}"/></span>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            <%--@elvariable id="coursesSize" type="java.lang.Integer"--%>
            <c:if test="${coursesSize == 0}">
                <h4><spring:message code="models.course.empty"/></h4>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>