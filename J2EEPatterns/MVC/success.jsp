<%@page contentType="text/html"%>
<jsp:useBean id="mailingbean" scope="request"
	type="com.devamatre.j2eepatterns.mvc.model.MailingBean" />
<html>
<head>
<title>Subscription Results</title>
</head>
<body>
	<br>
	<br> Dear
	<jsp:getProperty name="mailingbean" property="first" />,
	<br>
	<br> Congratulations, the address
	<jsp:getProperty name="mailingbean" property="email" />
	has been sucessfully added to the list!
</body>
</html>
