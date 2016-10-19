<nav class="navbar navbar-default navbar-static-top row" role="navigation">
  <div class="navbar-header  row row-space-between">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">
      <img src="resources/assets/logo.svg">
    </a>
    <div class="row row-center">
      <a class="link m-right-1" href="login">Login</a>
      <a class="link m-right-1" href="register">Register</a>
      <div class="btn-group show-on-hover">
        <button type="button" class="btn btn-default dropdown-toggle dropdown-custom" data-toggle="dropdown">
          <img class="avatar" src="resources/assets/avatar_1.svg">
          <span>username</span>
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
          <li>
            <a href="profile">Mi perfil</a>
          </li>
          <li>
            <a href="${pageContext.request.contextPath}/uploadDocument">Subir apunte</a>
          </li>
          <li>
            <a href="logout">Logout</a>
          </li>
        </ul>
      </div>
    <div>
  </div>
</nav>