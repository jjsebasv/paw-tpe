<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../navbar.jsp" %>
<div class="login-form">
    <c:url value="/login" var="postPath"/>

    <div class="row margin-top">

        <div class="col-xs-4 col-xs-offset-4">

            <form:form modelAttribute="loginForm" action="${postPath}" method="post">

                <c:set var="usernameHasBindError">
                    <form:errors path="username"/>
                </c:set>

                <c:set var="passwordHasBindError">
                    <form:errors path="password"/>
                </c:set>


                <div class="form-group ${not empty usernameHasBindError?"has-error":""}">
                    <form:label path="username">Username: </form:label>
                    <form:input class="form-control" type="text" path="username"/>
                    <form:errors path="username" cssClass="help-block" element="p"/>
                </div>

                <div class="form-group ${not empty passwordHasBindError?"has-error":""}">
                    <form:label path="password">Password: </form:label>
                    <form:input class="form-control" type="password" path="password"/>
                    <form:errors path="password" cssClass="help-block" element="p"/>
                </div>

                <div class="form-group">
                    <input class="btn btn-default" type="submit" value="Login!"/>
                </div>
            </form:form>
        </div>
    </div>

</div>

<%@ include file="../footer.jsp" %>
</body>
