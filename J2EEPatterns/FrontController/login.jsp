<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
	private static String displayUsername(String u) {
		if (u == null) {
			return "";
		}
		return u.replace("&", "&amp;").replace("'", "&#39;");
	}
%>
<jsp:useBean id="userbean" scope="session"
	class="com.devamatre.j2eepatterns.frontcontroller.model.UserBeanImpl" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<p>Password must be at least 3 characters for this demo login. After login you are forwarded to the subscribe page.</p>
	<form action="<%= request.getContextPath() %>/pages/subscribe.html" method="post">
		Username: <input type="text" name="username"
			value='<%= displayUsername(userbean.getUsername()) %>'
			autocomplete="username"><br>
		Password: <input type="password" name="password" autocomplete="current-password"><br>
		<input type="submit" value="Log In">
	</form>
	<p><a href="<%= request.getContextPath() %>/subscribe.html">Mailing list (no login)</a></p>
</body>
</html>
