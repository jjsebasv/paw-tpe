<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<body>


<div id="wrapper">

    <%@ include file="navbar.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${file.subject} - ${file.fileName}</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="row">
            <nav class="breadcrumb">
                <a class="breadcrumb-item" href="<%=request.getContextPath()%>"><i class="fa fa-home"></i> / </a>
                <a class="breadcrumb-item" href="<%=request.getContextPath()%>/courses/${file.course.courseid}">${file.course.name} / </a>
                <span class="breadcrumb-item active">${file.fileName}</span>
            </nav>
        </div>

        <!-- /.row -->
        <div class="row">
			
            <div class="col-lg-12">
                
                <div class="panel panel-default">
					
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        A file by ${username} - File points: ${average}
                        <hr>
                        <a href="<%=request.getContextPath()%>/download/${file.fileid}">
	                        <button type="button" class="btn btn-primary btn-circle btn-xl">
	                        	<i class="fa fa-download"></i>
    	                    </button>
                        </a>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <div class="row">

                    <div class="col-lg-5">


                        <div class="panel-body">
                            <h3>Add a file review!</h3>
                            <hr>
                            <div class="col-lg-offset-2 col-lg-8">
                            <form:form method="POST" action="/webapp/file/${file.fileid}/addReview" modelAttribute="reviewForm">
                                <fieldset>
                                    <div class="form-group">
                                        <form:input path="ranking" type='number' value="5" min='1' max='5' step="1" class="form-control" placeholder="Ranking"/>
                                    </div>
                                    <div class="form-group">
                                        <form:input path="review" htmlEscape="true" class="form-control" placeholder="Review"/>
                                    </div>
                                    <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit"/>
                                    <!-- Change this to a button or input when using this as a form -->

                                </fieldset>
                            </form:form>
                            </div>
                        </div>

                    </div>

                    <div class="col-lg-7">
                        <div class="review-pannel panel panel-default">

                            <div class="panel-body">
                                <h3>Reviews Feed</h3>
                                <hr>
                                <c:forEach items="${reviews}" var="review">
                                    <ul class="list-group">
                                        <li class="list-group-item">
                                            <span class="badge">${review.ranking}</span>
                                            <p>${review.review}</p>
                                            <p class="right">By: ${review.user.name}</p>
                                        </li>
                                    </ul>
                                </c:forEach>
                                <hr>


                            </div>
                            <!-- /.panel-body -->
                        </div>
                    </div>

                </div>



                <!-- /.panel -->
            </div>
            <!-- /.col-lg-4 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->


</div>


</body>

<%@ include file="footer.jsp" %>