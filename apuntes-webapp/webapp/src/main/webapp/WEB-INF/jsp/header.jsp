<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="url">${pageContext.request.requestURL}</c:set>
<spring:htmlEscape defaultHtmlEscape="true"/>
<html>
  <head>
    <meta charset="utf-8">
    <title><spring:message code="webapp.title"/></title>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
    <link href="resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
    <link rel="icon" type="image/ico" href="resources/assets/favicon.ico">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script src="<c:url value="/resources/js/bootstrap-filestyle.min.js" />"></script>--%>
    <link rel="stylesheet" type="text/css" href="resources/css/normalize.css">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="resources/css/triangular/css/responsive.css" rel="stylesheet">
    <link href="resources/css/triangular/css/main.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/application.css">
    <script src="resources/js/application.js"></script>
  </head>