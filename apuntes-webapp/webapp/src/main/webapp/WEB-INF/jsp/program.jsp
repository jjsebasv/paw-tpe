<%@ include file="header.jsp" %>

<body>
<div id="wrapper">

    <%@ include file="headerBody.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${program.name}</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <div class="row">
            <nav class="breadcrumb">
                <a class="breadcrumb-item" href="<%=request.getContextPath()%>"><i class="fa fa-home"></i> / </a>
                <span class="breadcrumb-item active">${program.name}</span>

            </nav>
        </div>

        <!-- /.row -->
        <div class="row">

            <div class="col-lg-12">
                <div class="panel panel-default">

                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <ul class="list-group">

                            <c:forEach items="${courses}" var="course">
                                <li>
                                    <a href="<%=request.getContextPath()%>/course/${course.code}"
                                       class="list-group-item">
                                        <i class="fa fa-download fa-fw"></i> ${course.name}
                                    </a>
                                </li>
                            </c:forEach>


                        </ul>
                        <!-- /.list-group -->

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