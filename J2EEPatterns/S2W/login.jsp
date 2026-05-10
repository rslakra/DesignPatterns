<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
	private static String displayUsername(String u) {
		if (u == null) {
			return "";
		}
		return u.replace("&", "&amp;").replace("'", "&#39;");
	}
%>
<jsp:useBean id="userbean" scope="session" class="com.devamatre.patterns.s2w.model.UserBeanImpl" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<p>Password must be more than 3 characters (at least 4) for this demo login.</p>
<form action="<%= request.getContextPath() %>/pages/workflow" method="post">
Username: <input type="text" name="username"
                 value='<%= displayUsername(userbean.getUsername()) %>'
                 autocomplete="username">
<br>
Password: <input type="password" name="password" autocomplete="current-password"><br>
<input type="submit" value="Log In">
</form>
</body>
</html>
