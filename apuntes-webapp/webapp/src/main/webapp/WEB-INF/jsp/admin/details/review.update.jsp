<%@ include file="../../header.jsp" %>
<%@ include file="../header.admin.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../../navbar.jsp" %>
<div class="register-form">
    <div class="content-wrapper">
        <div class="white-container">
            <a href="admin/reviews/list" class="back-button">
                <img src="resources/assets/back.svg">
            </a>

            <h3 class="content-title"><spring:message code="models.review.edit"
                                                      arguments="${pk}"/></h3>
            <div class="white-container-content">
                <form:form class="column left" method="POST" action="" modelAttribute="reviewForm">

                    <c:set var="reviewHasBindError">
                        <form:errors path="review"/>
                    </c:set>
                    <c:set var="rankingHasBindError">
                        <form:errors path="ranking"/>
                    </c:set>

                    <div class="form-group column left input-base-wrapper">
                        <label for="document"><spring:message code="models.review.document"/>:</label>
                        <input type="text" class="form-control" value="<c:out value="${review.file.subject}" />" id="document" readonly="readonly">
                    </div>
                    <div class="form-group column left input-base-wrapper">
                        <label for="client"><spring:message code="models.review.client"/>:</label>
                        <input type="text" class="form-control" value="<c:out value="${review.user.name}" />" id="client" readonly="readonly">
                    </div>

                    <div class="column left form-group input-base-wrapper ${not empty reviewHasBindError?"has-error":""}">
                        <form:label path="review"><spring:message code="models.review.review"/>:</form:label>
                        <form:textarea class="form-control" type="text" path="review"/>
                        <form:errors path="review" cssClass="help-block" element="p"/>
                    </div>
                    <div class="column left form-group input-base-wrapper ${not empty rankingHasBindError?"has-error":""}">
                        <form:label path="ranking"><spring:message code="models.review.review"/>:</form:label>
                        <form:input class="form-control" type='number' value="5" min='1' max='5' step="1" path="ranking"/>
                        <form:errors path="ranking" cssClass="help-block" element="p"/>
                    </div>

                    <div class="form-group item-center">
                        <button class="submit-button" type="submit" name="action" value="save">
                            <spring:message code="models.save"/>
                        </button>
                        <button class="submit-button" type="submit" name="action" value="delete">
                            <spring:message code="models.delete"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>
</body>