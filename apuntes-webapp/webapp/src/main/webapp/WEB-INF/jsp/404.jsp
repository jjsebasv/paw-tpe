<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<%@ include file="header.jsp" %>
<body class="perspective-body">
  <a href="" class="back-button">
    <img src="resources/assets/back.svg">
  </a>
  <ul class="perspective">
    <li class="perspective-line">
      <p>&nbsp;</p>
      <p class="perpective-bold">404</p>
    </li>
    <li class="perspective-line">
      <p class="perpective-bold">404</p>
      <p>Ooops!</p>
    </li>
    <li class="perspective-line">
      <p>Ooops!</p>
      <p>La pagina que</p>
    </li>
    <li class="perspective-line">
      <p>La pagina que</p>
      <p>buscas ha sido</p>
    </li>
    <li class="perspective-line">
      <p>buscas ha sido</p>
      <p>removida o remombrada</p>
    </li>
    <li class="perspective-line">
      <p>removida o remombrada</p>
      <p>&nbsp;</p>
    </li>
  </ul>
</body>