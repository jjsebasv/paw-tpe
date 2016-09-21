<%@ include file="header.jsp" %>

<body>
    <div id="wrapper">

        <%@ include file="headerBody.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Programs</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

            <!-- /.row -->
            <div class="row">

                <div class="col-lg-12">
                    <div class="panel panel-default">

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul class="list-group">


                                    <c:forEach items="${programs}" var="program">
                                        <li>
                                            <a href="<%=request.getContextPath()%>/program/${program.programid}"
                                               class="list-group-item">
                                                <!--<i class="fa fa-download fa-fw"></i>-->${program.name}
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