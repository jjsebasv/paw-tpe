<%@ include file="header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="content-title"><c:out value="\#${program.name}"/></h3>
        <div class="white-container-content">
            <h2 class="course-size"><c:out value="${coursesSize}"/> <spring:message code="program.course.size"/></h2>
            <ul class="list-wrapper">
                <div class="input-base-wrapper">
                    <input class="input-base m-bottom-5 filter-input"
                           placeholder="<spring:message code="program.search.course"/>" id="filter"/>
                </div>
                <c:forEach items="${courses}" var="entry">
                    <c:if test="${entry.value.size()>0}">
                        <div class="semester-wrapper m-bottom-3">

                            <h3 class="m-bottom-1">
                                <c:set var="mappedSemesters" value="${sm:mapSemester(entry.key)}"/>
                                <spring:message code="tag.semestermapper"
                                                arguments="${mappedSemesters[0]},${mappedSemesters[1]}"/>
                            </h3>
                            <c:forEach items="${entry.value}" var="course">
                                <li class="list-item course-item">
                                    <div class="border"></div>
                                    <a href="course/${course.code}"
                                       class="list-group-item">
                                        <span><c:out value="${course.code}"/></span>
                                        <span><c:out value="${course.name}"/></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:forEach>
                <p><spring:message code="program.optional.courses"/></p>
                <c:forEach items="${optativas}" var="course">
                <div class="semester-wrapper">
                    <li class="list-item course-item">
                        <div class="border"></div>
                        <a href="course/${course.code}"
                           class="list-group-item">
                            <span><c:out value="${course.code}"/></span>
                            <span><c:out value="${course.name}"/></span>
                        </a>
                    </li>
                    </c:forEach>
                </div>
            </ul>
            <c:if test="${coursesSize == 0}">
                <h4><spring:message code="profile.no.documents"/></h4>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
