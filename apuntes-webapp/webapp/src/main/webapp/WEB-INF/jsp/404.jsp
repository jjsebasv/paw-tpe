<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<html>
    <head>
        <meta charset="utf-8">
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
        <title>Campus - PAW</title>
        <link rel="stylesheet" type="text/css" href="resources/css/404.css">
    </head>
    <body><ul class="perspective">
        <li class="perspective-line">
            <p>&nbsp;</p>
            <p class="perpective-bold">404</p>
        </li>

        <li class="perspective-line">
            <p>Ooops!</p>
            <p>the page you're</p>
            <p>looking for</p>
            <p>doesn't exist.</p>
        </li>
    </ul>
</body>
</html>