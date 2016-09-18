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
                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/download/${file.fileid}">Download</a>

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
                    	<form method="POST" action="<%=request.getContextPath()%>/file/${file.fileid}/addReview">
							<table>
						   		<tr>
						    		<td><form:label path="name">Name</form:label></td>
						        	<td><form:input path="name" /></td>
							    </tr>
							    <tr>
							        <td><form:label path="age">Age</form:label></td>
							        <td><form:input path="age" /></td>
							    </tr>
							    <tr>
							        <td><form:label path="id">id</form:label></td>
							        <td><form:input path="id" /></td>
							    </tr>
							    <tr>
							        <td colspan="2">
							            <input type="submit" value="Submit"/>
							        </td>
							    </tr>
							</table>  
						</form>
  
                        Reviews
                        <hr>
                        <div class="review">
                        	esto es un review
                        </div>                        
                        <hr>
						<textarea rows="5" cols="55"></textarea>
						<a class="btn btn-success">Submit Review</a>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <a class="btn btn-info" href="<%=request.getContextPath()%>/courses/${file.courseid}"> < Go Back</a>
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