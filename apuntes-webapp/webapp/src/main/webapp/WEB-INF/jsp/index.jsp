<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
</head>
<body>
<h2>List of Courses!</h2>

<ul>

    <c:forEach items="${courses}" var="course">
        <li>
            <a href="<%=request.getContextPath()%>/courses/${course.courseid}">${course.name}</a>
        </li>
    </c:forEach>

</ul>

</body>

</html>