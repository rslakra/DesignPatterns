<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.devamatre.j2eepatterns.antipatterns.LoginManager"%>
<%
	LoginManager lm = new LoginManager();
	ServletRequest req = pageContext.getRequest();
	boolean loggedIn = lm.doLogin(req.getParameter("username"), req.getParameter("password"));
	String formAction = request.getContextPath() + "/LoginPage.jsp";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= loggedIn ? "Welcome" : "Login Page" %></title>
</head>
<body>
<% if (!loggedIn) { %>
	<form action="<%= formAction %>" method="post">
		User name: <input type="text" name="username" autocomplete="username"><br>
		Password: <input type="password" name="password" autocomplete="current-password"><br>
		<input type="submit" value="Log in">
	</form>
<% } else { %>
	<p>Welcome to the page!</p>
<% } %>
	<p><a href="<%= request.getContextPath() %>/">Home</a></p>
</body>
</html>
