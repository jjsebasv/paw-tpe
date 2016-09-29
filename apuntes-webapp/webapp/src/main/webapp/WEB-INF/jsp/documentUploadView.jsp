<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>s

<spring:htmlEscape defaultHtmlEscape="true" />
<body>
  <%@ include file="navbar.jsp" %>
  <div class="upload-document-form">
	  <form:form method="POST" action="/webapp/uploadDocument/finish" enctype="multipart/form-data" modelAttribute="documentForm">
          <fieldset>
              <div class="form-group">
                  <form:textarea path="subject" htmlEscape="true" class="form-control" placeholder="Subject"/>
              </div>
              <form:select path="courseid" class="form-control course-select">
      				  <c:forEach var="course" items="${courses}">
      				      <form:option value="${course.courseid}">
      				      	${course.code} - ${course.name}
      				      </form:option>
      				  </c:forEach>
      			  </form:select>
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
