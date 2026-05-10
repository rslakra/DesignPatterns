<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="rss" uri="/ReadRSS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RSS Results</title>
</head>
<body>
	<%-- Bundled feed under webapp root; avoids HTTP to another port or self-request during JSP --%>
	<rss:RSSChannel URL="/patternsnews.xml">
		<b><a href="<%=channelLink%>"><%=channelName%></a></b>
		<br>
		<table>
			<rss:RSSItems>
				<tr>
					<td><a href="<%=itemLink%>"><%=itemName%></a></td>
				</tr>
			</rss:RSSItems>
		</table>
	</rss:RSSChannel>
</body>
</html>
