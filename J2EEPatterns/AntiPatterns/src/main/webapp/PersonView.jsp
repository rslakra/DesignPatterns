<%@ page contentType="text/html" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<%--
  personCommand is set by PersonServlet before forward. Direct browser hits
  forward to PersonServlet (method=ejb) so the table loads without a manual link.
--%>
<% if (request.getAttribute("personCommand") == null) { %>
<jsp:forward page="/servlet/PersonServlet">
	<jsp:param name="method" value="ejb" />
</jsp:forward>
<% } %>
<html>
<head>
    <title>Addresses</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty personCommand}">
            <p>Unable to load address data. Try
                <a href="<%= request.getContextPath() %>/servlet/PersonServlet?method=ejb">PersonServlet</a>
                or <a href="<%= request.getContextPath() %>/">home</a>.</p>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty demoDataNotice}">
                <p><em>In-memory demo data (no EJB/JNDI on this server; <code>method=ejb</code> fell back for local run).</em></p>
            </c:if>
            <table border="1" cellpadding="4">
                <tr><th>First</th><th>Last</th><th>Phone</th></tr>
                <c:forEach var="person" items="${personCommand.people}">
                    <tr>
                        <td><c:out value="${person.firstName}" /></td>
                        <td><c:out value="${person.lastName}" /></td>
                        <td><c:out value="${person.phoneNumber}" /></td>
                    </tr>
                </c:forEach>
            </table>
            <p><a href="<%= request.getContextPath() %>/">Home</a></p>
        </c:otherwise>
    </c:choose>
</body>
</html>
