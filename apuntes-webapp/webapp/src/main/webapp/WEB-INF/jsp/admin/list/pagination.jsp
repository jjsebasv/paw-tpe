<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="pagination">
    <li>
        <a href="admin/<c:out value="${model}"/>/list?page=1">&laquo;</a>
    </li>

    <c:forEach items="${pagesToShow}" var="pageToShow">
        <li>
            <c:if test="${pageToShow == page}">
                <a href="admin/<c:out value="${model}"/>/list?page=<c:out value="${pageToShow}"/>">
                    <b>
                        <c:out value="${pageToShow}"/>
                    </b>
                </a>
            </c:if>
            <c:if test="${pageToShow != page}">
                <a href="admin/<c:out value="${model}"/>/list?page=<c:out value="${pageToShow}"/>">
                    <c:out value="${pageToShow}"/>
                </a>
            </c:if>
        </li>
    </c:forEach>

    <li>
        <a href="admin/<c:out value="${model}"/>/list?page=<c:out value="${maxPage}"/>">&raquo;</a>
    </li>
</ul>


