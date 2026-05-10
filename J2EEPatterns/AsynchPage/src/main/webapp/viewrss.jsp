<%@page contentType="text/html"%>
<%@ taglib prefix="rss" uri="/ReadRSS" %>
<%-- Feed is bundled as patternsnews.xml; resolve absolute URL for HttpURLConnection in RSSInfo --%>
<%
  String rssFeedUrl = request.getScheme() + "://" + request.getServerName() + ":"
      + request.getServerPort() + request.getContextPath() + "/patternsnews.xml";
%>
<html>
<head><title>RSS Results</title></head>
<body>
<rss:RSSChannel URL="<%= rssFeedUrl %>">
 <b><a href="<%= channelLink %>"><%= channelName %></a></b>
 <br>
 <table>
  <rss:RSSItems>
   <tr><td><a href="<%= itemLink %>"><%= itemName %></a></td></tr> 
  </rss:RSSItems>
 </table>
</rss:RSSChannel>
</body>
</html>
