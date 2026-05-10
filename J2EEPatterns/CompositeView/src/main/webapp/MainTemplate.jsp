<%@page contentType="text/html"%>
<%@ taglib uri="/container" prefix="cv"%>
<%-- Template expects request attribute "view" from FrontController; direct hits go through the controller. --%>
<% if (request.getAttribute("view") == null) { %>
<jsp:forward page="/pages/composite">
	<jsp:param name="page" value="page1"/>
</jsp:forward>
<% } %>

<cv:container name="main">
	<html>
<head>
<title><cv:include name="title" /></title>
</head>
<body>
	<table width="100%" height="100%">
		<tr>
			<td><cv:include name="header" /></td>
		</tr>
		<tr>
			<td><cv:include name="body" /></td>
		</tr>
		<tr>
			<td><cv:include name="footer" /></td>
		</tr>
	</table>
</body>
	</html>
</cv:container>
