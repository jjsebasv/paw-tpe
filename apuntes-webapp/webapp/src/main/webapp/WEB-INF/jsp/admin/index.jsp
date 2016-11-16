<%@ include file="../header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="../navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="content-title"><spring:message code="admin.index.title"/></h3>
        <div class="white-container-content">
            <ul class="list-wrapper">

                <div class="semester-wrapper">

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/programs/list"
                           class="list-group-item">
                            <span><spring:message code="models.program.plural"/></span>
                        </a>
                    </li>

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/courses/list"
                           class="list-group-item">
                            <span><spring:message code="models.course.plural"/></span>
                        </a>
                    </li>

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/clients/list"
                           class="list-group-item">
                            <span><spring:message code="models.client.plural"/></span>
                        </a>
                    </li>

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/documents/list"
                           class="list-group-item">
                            <span><spring:message code="models.document.plural"/></span>
                        </a>
                    </li>

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/courseprogramrelation/list"
                           class="list-group-item">
                            <span><spring:message code="models.coursetoprogramrelation.plural"/></span>
                        </a>
                    </li>

                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="admin/reviews/list"
                           class="list-group-item">
                            <span><spring:message code="models.review.plural"/></span>
                        </a>
                    </li>

                </div>

            </ul>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
