<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
-->
<link href="css/layout.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function de_find() {
		document.SchemeUploadForm.action = "schemeUploadAction.do?method=find&d__mode="
				+ d__mode + "&menuId=PMM020";
		document.SchemeUploadForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.SchemeUploadForm.action = "schemeUploadAction.do?method=update&d__mode="
					+ d__mode + "&menuId=PMM020";
			document.SchemeUploadForm.submit();
		}
	}
	function de_remove() {
		if (d__mode == 'ent_delete') {
			document.SchemeUploadForm.action = "schemeUploadAction.do?method=delete&d__mode="
					+ d__mode + "&menuId=PMM020";
			document.SchemeUploadForm.submit();
		}
	}
	function de_confirm() {
		if (d__mode == 'ent_post') {
			document.SchemeUploadForm.action = "schemeUploadAction.do?method=post&d__mode="
					+ d__mode + "&menuId=PMM020";
			document.SchemeUploadForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.SchemeUploadForm.action = "schemeUploadAction.do?method=save&d__mode="
					+ d__mode + "&menuId=PMM020";
			document.SchemeUploadForm.submit();
		}
	}
	function getTenderUploaded(locationId, tenderId) {
		document.SchemeUploadForm.action = "schemeUploadAction.do?method=populate&locationId="
				+ locationId
				+ "&tenderId="
				+ tenderId
				+ "&d__mode="
				+ d__mode
				+ "&menuId=PMM020";
		document.SchemeUploadForm.submit();
	}
</script>
</head>
<html:html>

<body>
	<html:form action="schemeUploadAction" method="post"
		enctype="multipart/form-data">
		<logic:messagesPresent>
			<div id="errorDiv" class="error displaynone"
				style="width: 470px%; margin-bottom: 7px; height: 13px;">
				<html:errors bundle="tender" />
			</div>
		</logic:messagesPresent>
		<logic:messagesPresent message="true">
			<div id="successDiv" class="success diplaynone" style="width: 470px;">
				<html:messages id="message" bundle="tender" message="true">
					<li><bean:write name="message" /></li>
				</html:messages>
			</div>
		</logic:messagesPresent>

		<br>

		<fieldset>
			<legend>Scheme Upload </legend>
			<center>
				<br>
				<table>
					<tr>
						<td>Location</td>
						<td colspan="4"><html:select property="locationId"
								styleClass="cs2" styleId="locationId">
								<html:option value="">Select Location</html:option>
								<html:options collection="userLocations" labelProperty="label"
									property="value"></html:options>
							</html:select></td>
					</tr>

					<tr>
						<td>Scheme Type</td>
						<td colspan="4"><html:select property="schemeType"
								styleClass="cs2" styleId="schemeType"
								onchange="ajaxFunction('schemeUploadAction.do?locationId='+document.getElementById('locationId').value+'&schemeType='+this.value+'&method=fetchScheme', 'schemeId');">
								<html:option value="">Select</html:option>
								<html:option value="ws">Water Supply</html:option>
								<html:option value="sw">Sewerage</html:option>
							</html:select></td>

					</tr>


					<tr>
						<td>Scheme Code</td>
						<td colspan="4"><html:select property="schemeId"
								styleId="schemeId" styleClass="cs2" /></td>
						<td>
					</tr>

				</table>
			</center>
		</fieldset>
		<fieldset>
			<legend>Upload Your Scheme Documents</legend>
			<center>
				<table>



					<tr>

						<%-- <td>Document One Description &nbsp; &nbsp; &nbsp;<html:text
								styleClass="ci5" property="descriptionOne" styleId="descriptionOne"></html:text></td> --%>
						<td>Digital Survey &nbsp; &nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:file
								property="digitalSurvey" styleId="digitalSurvey"></html:file>
						</td>
					</tr>
					<tr>
						<%-- <td>Document Two Description &nbsp; &nbsp; &nbsp;<html:text
								styleClass="ci5" property="descriptiontwo"
								styleId="descriptiontwo"></html:text></td> --%>
						<td>Scheme Estimate &nbsp; &nbsp; &nbsp;&nbsp;<html:file
								property="schemeEstimate" styleId="schemeEstimate"></html:file></td>
					</tr>

					<tr>
						<%-- <td>Document Three Description &nbsp; &nbsp; &nbsp;<html:text
								styleClass="ci5" property="descriptionthree"
								styleId="descriptionthree"></html:text></td> --%>
						<td>Admin Aproval &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<html:file
								property="adminAproval" styleId="adminAproval"></html:file></td>
					</tr>
					<tr>
						<%-- <td>Document Four Description &nbsp; &nbsp; &nbsp;<html:text
								styleClass="ci5" property="descriptionfour"
								styleId="descriptionfour"></html:text></td> --%>
						<td>Bid Document &nbsp; &nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:file
								property="bidDocumentFile" styleId="bidDocumentFile"></html:file>
						</td>
					</tr>

					<tr>
						<%-- <td>Document Four Description &nbsp; &nbsp; &nbsp;<html:text
								styleClass="ci5" property="descriptionfour"
								styleId="descriptionfour"></html:text></td> --%>
						<td>Strata Chart &nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;<html:file property="strataChart" styleId="strataChart"></html:file>
						</td>
					</tr>

					<tr>
						<td></td>
					</tr>

					<tr>
						<td></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td></td>
					</tr>

					<tr>
						<td>** Only PDF are allowed to Upload</td>
					</tr>
					<tr>
						<td>**The Size of the File must not exceed 5MB</td>
					</tr>
				</table>
			</center>
		</fieldset>
		<div id="dispTag">
			<logic:present name="tenderUploadList" scope="request">
				<logic:notEmpty name="tenderUploadList" scope="request">
					<center>
						<display:table name="tenderUploadList" id="tenderUpload"
							class="mars" style="margin:0 1em 1em 0;" pagesize="10"
							requestURI="/tenderUploadAction.do" export="true">
							<display:column title="Click to Display" media="html">
								<a
									href="javascript:getTenderUploaded('${tenderUpload.locationId}','${tenderUpload.tenderId}')">Click</a>
							</display:column>
							<display:column property="tenderId" title="Tender No."
								sortable="true" />
							<display:column property="workDescription"
								title="Work Description" sortable="true" />
							<display:column property="bidsOpeningDate"
								title="Bids OpeningDate" sortable="true"
								decorator="com.prwss.mis.common.util.DateColumnDecorator" />
							<display:column property="lastDateofReceipt"
								title="Last Date of Receipt" sortable="true"
								decorator="com.prwss.mis.common.util.DateColumnDecorator" />
							<display:column property="expiryDate" title="Expiry Date"
								sortable="true"
								decorator="com.prwss.mis.common.util.DateColumnDecorator" />

							<display:setProperty name="export.excel.filename"
								value="TenderUploadedList.xls" />
							<display:setProperty name="export.xml" value="false" />
							<display:setProperty name="export.csv" value="false" />
						</display:table>
					</center>

				</logic:notEmpty>
			</logic:present>
		</div>

	</html:form>
</body>
</html:html>