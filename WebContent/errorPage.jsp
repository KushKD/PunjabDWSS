<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>

<B><font color="red">Internal Error Occurred.. Please check
logs or report to application maintenance.</font></B>
<logic:messagesPresent>
	<div id="errorDiv" class="error displaynone"
		style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors />
	</div>
</logic:messagesPresent>
<logic:messagesPresent message="true">
	<div id="successDiv" class="success diplaynone" style="width: 470px;">
	<html:messages id="message" message="true">
		<li><bean:write name="message" /></li>
	</html:messages></div>
</logic:messagesPresent>


</body>
</html>