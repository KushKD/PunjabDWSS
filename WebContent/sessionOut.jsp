<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body bgcolor="#333333">

<table border="0" cellpadding="2" cellspacing="2" align="center"
	width="1200px" height="400px">
	<tr>
		<td height="20px" colspan="1"><%@include file="header.jsp"%>
		<hr />
		</td>
	</tr>
	<tr>
		<td height="450px" colspan="2"><%@include
			file="sessionExpired.jsp"%></td>
	</tr>
	<tr>
		<td height="20px" colspan="2">
		<hr />
		<%@include file="footer.jsp"%></td>
	</tr>
</table>
</body>
</html>