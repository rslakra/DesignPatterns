<%@page contentType="text/html"%>
<jsp:useBean id="mailingbean" scope="request"
	type="com.rslakra.j2eepatterns.frontcontroller.model.MailingBean" />
<html>
<head>
<title>Subscription Results</title>
</head>
<body>
	<br>
	<br> Dear
	<jsp:getProperty name="mailingbean" property="first" />,
	<br>
	<br> We're sorry, the address
	<jsp:getProperty name="mailingbean" property="email" />
	could not be added to the list.
	<br>
	<br> The problem was:
	<jsp:getProperty name="mailingbean" property="errorString" />.
</body>
</html>
