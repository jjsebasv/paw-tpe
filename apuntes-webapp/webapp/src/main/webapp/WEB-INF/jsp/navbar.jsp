<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">Apuntes - PAW v1.1</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="uploadDocument">Upload Document</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-left">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="profile"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>
                <li class="divider"></li>
                <li><a href="logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
</nav>