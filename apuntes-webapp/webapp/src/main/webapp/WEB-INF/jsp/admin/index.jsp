<%@ include file="../header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="../navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title"><spring:message code="admin.index.title"/></h3>
        <div class="white-container-content">
            <ul class="list-wrapper">


                <div class="semester-wrapper">

                    <%--<p><c:out value="${model.key}"/></p>--%>
                    <c:forEach items="${models}" var="model">
                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/${model.value}/list"
                           class="list-group-item">
                            <span><c:out value="${model.key}"/></span>
                        </a>
                        </c:forEach>
                    </li>
                </div>

            </ul>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
