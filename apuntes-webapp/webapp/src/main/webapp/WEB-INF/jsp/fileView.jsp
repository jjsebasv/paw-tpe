<%@ include file="header.jsp" %>

<body>


<div id="wrapper">

    <%@ include file="headerBody.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${file.subject}</h1>
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


                        <a href="<%=request.getContextPath()%>/download/${file.fileid}">
                        <button type="button" class="btn btn-primary btn-circle btn-xl"><i class="fa fa-download"></i>

                        </button>
                        </a>

                    </div>
                    <!-- /.panel-body -->
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