<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>s

<body>
  <%@ include file="navbar.jsp" %>
  <div class="upload-document-form">
	  <form:form method="POST" action="/webapp/uploadDocument/finish" enctype="multipart/form-data" modelAttribute="documentForm">
          <fieldset>
              <div class="form-group">
                  <form:input path="subject" htmlEscape="true" class="form-control" placeholder="Subject"/>
              </div>
              <div class="form-group">
                  <input type='file' class="form-control" name="document" placeholder="Document"/>
              </div>
              <input class="btn btn-lg btn-primary btn-block" type="submit" value="Submit"/>
              <!-- Change this to a button or input when using this as a form -->

          </fieldset>
      </form:form>
  </div>
  
  <%@ include file="footer.jsp" %>
</body>
