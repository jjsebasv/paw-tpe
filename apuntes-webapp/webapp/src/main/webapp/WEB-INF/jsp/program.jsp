<%@ include file="header.jsp" %>
<%@ taglib prefix="sm" uri="http://ar.edu.itba.paw/semesterMapper" %>
<body>
<%@ include file="navbar.jsp" %>
<div class="content-wrapper">
    <div class=white-container>
        <a href="" class="back-button">
            <img src="resources/assets/back.svg">
        </a>
        <h3 class="program-title">#${program.name}</h3>
        <div class="white-container-content">
            <h4 class="course-size">${coursesSize} Materias</h4>
            <ul class="list-wrapper">


                <c:forEach items="${courses}" var="entry">
                    <p>Semester ${sm:mapSemester(entry.key)}</p>

                    <c:forEach items="${entry.value}" var="course">
                        <li class="list-item">
                            <div class="border"></div>
                            <a href="course/${course.code}"
                               class="list-group-item">
                                <span>${course.code}</span>
                                <span>${course.name}</span>
                            </a>
                        </li>
                    </c:forEach>

                </c:forEach>

            </ul>
            <c:if test="${coursesSize == 0}">
                <h4>No hay apuntes cargados!</h4>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
