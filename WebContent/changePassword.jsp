<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
<logic:messagesPresent>
	<div id="errorDiv" class="error displaynone"
		style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors
		bundle="message" /></div>
</logic:messagesPresent>
<logic:messagesPresent message="true">
	<div id="successDiv" class="success diplaynone" style="width: 470px;">
	<html:messages id="message" bundle="message" message="true">
		<li><bean:write name="message" /></li>
	</html:messages></div>
</logic:messagesPresent>

<center>
<%
session.setAttribute("menuId","LRD001");
%> <html:form
	action="changePasswordAction.do?method=change&menuId=LRD001"
	focus="changePasswordForm" method="POST"
	onsubmit="document.getElementById('submitFormId').value=true">
	<html:javascript formName="changePasswordForm" dynamicJavascript="true"
		staticJavascript="true" />

	<fieldset><html:hidden property="submitForm" value="false"
		styleId="submitFormId" /> <legend>Change Password</legend>
	<table>
		<tr>
			<td>Enter Old Password</td>
			<td><html:password property="oldPassword"></html:password></td>
		</tr>
		<tr>
			<td>Enter New Password</td>
			<td><html:password property="newPassword"></html:password></td>
		</tr>
		<tr>
			<td>Confirm New Password</td>
			<td><html:password property="confirmPassword"></html:password></td>
		</tr>
		<tr>
			<td><html:submit value="Change Password"></html:submit></td>
		</tr>
	</table>
	</fieldset>
</html:form></center>
<script>
</script>
</html:html>