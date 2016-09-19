<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<body>


<div id="wrapper">

    <%@ include file="headerBody.jsp" %>

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
                <span class="breadcrumb-item active">${file.subject}</span>
            </nav>
        </div>

        <!-- /.row -->
        <div class="row">
			
            <div class="col-lg-12">
                
                <div class="panel panel-default">
					
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        A file by ${username}
                        <hr>

                        <a href="<%=request.getContextPath()%>/download/${file.fileid}">
	                        <button type="button" class="btn btn-primary btn-circle btn-xl">
	                        	<i class="fa fa-download"></i>
    	                    </button>
                        </a>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <div class="review-pannel panel panel-default">
					
                    <div class="panel-body">                   
                    	<h3>Reviews</h3>
                        <hr>
                        <c:forEach items="${reviews}" var="review">
                        	<ul class="list-group">
							  <li class="list-group-item">
							    <span class="badge">${review.ranking}</span>
							    <p>${review.review}</p>
							  </li>
							</ul>     
						</c:forEach>              
                        <hr>
                    	<form:form method="POST" action="/webapp/file/${file.fileid}/addReview" modelAttribute="reviewForm">
				            <table>
				               <tr>
				                   <td><form:label path="ranking">Ranking</form:label></td>
				                   <td><form:input path="ranking"/></td>
				               </tr>
				               <tr>
				                   <td><form:label path="review">Review</form:label></td>
				                   <td><form:input path="review"/></td>
				               </tr>
				               <tr>
				                   <td><input class="btn btn-success" type="submit" value="Submit"/></td>
				               </tr>
				           </table>
				       </form:form>
                    
                    </div>
                    <!-- /.panel-body -->
                </div>
                <a class="btn btn-info" href="<%=request.getContextPath()%>/courses/${file.course.courseid}"> < Go Back</a>
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