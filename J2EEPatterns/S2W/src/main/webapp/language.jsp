<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Language Selection</title>
</head>
<body>
<br><br>
<form action="<%= request.getContextPath() %>/pages/workflow" method="get">
Language: <select name="language">
          <option value="En">English</option>
          <option value="Fr">French</option>
          </select>
<br>
<input type="submit" value="Continue">
</form>
</body>
</html>
