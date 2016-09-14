<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/application.css"/>"/>
	</head>
	<body>
		<h2>Hello ${greeting}!</h2>
	
		<h3>File Upload:</h3>
		Select a file to upload: <br />
		<form action="UploadServlet" method="post" enctype="multipart/form-data">
			<input type="file" name="file" size="50" />
		</form>
	
	</body>
	
</html>