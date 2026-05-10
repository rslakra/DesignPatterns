<%@page contentType="text/html"%>
<jsp:useBean id="userbean" scope="session" class="com.devamatre.patterns.s2w.model.UserBeanImpl" />
<html>
<head><title>Welcome</title></head>
<body>
<br><br>
Username: <jsp:getProperty name="userbean" property="username"/>
<br>
Language: <jsp:getProperty name="userbean" property="language"/>
<br>
<form action="<%= request.getContextPath() %>/pages/workflow" method="get">
<input type="submit" value="Start Over">
</form>
</body>
</html>
