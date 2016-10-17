<%@ include file="../header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>
<%@ include file="../navbar.jsp" %>
<div class="login-form">

    <div class="row margin-top">

        <div class="col-xs-4 col-xs-offset-4">

            <c:url value="/login" var="loginUrl"/>
            <form action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>


                <div class="form-group">
                    <label class="control-label" for="username">Username: </label>
                    <input type="text" class="form-control" name="j_username" id="username">
                </div>

                <div class="form-group">
                    <label class="control-label" for="password">Password: </label>
                    <input type="password" class="form-control" name="j_password" id="password">
                </div>

                <div class="form-group">
                    <input class="btn btn-default" type="submit" value="Login!"/>
                </div>
            </form>
        </div>
    </div>

</div>

<%@ include file="../footer.jsp" %>
</body>
