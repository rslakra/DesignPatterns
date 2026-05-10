<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>AntiPatterns - Home</title>
</head>
<body>
	<h1>AntiPatterns (Chapter 12 Sample)</h1>
	<p>Choose a page:</p>
	<ul>
		<li><a href="<%= request.getContextPath() %>/LoginPage.jsp">Login</a> &mdash; simple login demo</li>
		<li><a href="<%= request.getContextPath() %>/PersonView.jsp">Person view</a> &mdash; address list (needs data from PersonServlet)</li>
		<li><a href="<%= request.getContextPath() %>/servlet/PersonServlet?method=ejb">PersonServlet</a> &mdash; EJB path; on Jetty uses in-memory demo data when JNDI is missing</li>
	</ul>
</body>
</html>
