<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
<script type="text/javascript">
$(document).ready(
		function() {
			
			ajaxFunction(
					'GetFilterValues.to?parentComboId=106&method=getCombo',
					'financialYear');
		
			ajaxFunction(
					'GetFilterValues.to?parentComboId=115&method=getCombo',
					'component');
			ajaxFunction(
					'GetFilterValues.to?parentComboId=112&method=getCombo',
					'inspectionType');
			ajaxFunction(
					'yearlyInspectionPlanAction.do?method=fetchExternalAgency',
					'assignedTo');
			
			<%if (MisUtility.ifEmpty(request.getAttribute("financialYear"))) {%>
				document.getElementById('financialYear').value="<%=request.getAttribute("financialYear")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("externalAgency"))) {%>
				document.getElementById('assignedTo').value="<%=request.getAttribute("externalAgency")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("inspectionType"))) {%>
				document.getElementById('inspectionType').value="<%=request.getAttribute("inspectionType")%>";
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("component"))) {%>
				document.getElementById('component').value="<%=request.getAttribute("component")%>";
			<%}%>
						var financialYearName = $(
								"#financialYear option:selected").text();
						if (financialYearName != '') {
							document.getElementById('financialYearName').value = financialYearName;
						}
						Req = "ent_frwrd";
						de_init('ent_frwrd', "test");

					});
	function de_find() {
		var status = validateSearchFields();
		if (status) {
			document.yearlyPlanInspectionForm.action = "updateYearlyPlanAction.do?method=find&d__mode="
					+ d__mode + "&menuId=CQ002";
			document.yearlyPlanInspectionForm.submit();
		}
	}

	function de_modify() {
		var result = true /* isCheckedWaterWorksLocation() */;
		//var status = validateFields();
		if (result) {
			document.yearlyPlanInspectionForm.action = "updateYearlyPlanAction.do?method=update&d__mode="
					+ d__mode + "&menuId=CQ002";
			document.yearlyPlanInspectionForm.submit();
		}
	}
</script>

</head>
<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="construction" style='display: none;'>
			<html:errors bundle="construction" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('construction');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

<html:form action="updateYearlyPlanAction" method="post"
	styleId="yearlyPlanInspectionForm">

	<html:hidden property='financialYearName' styleId="financialYearName" />
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Yearly Plan for Inspection</h4>
			<div class="line"></div>
			<html:hidden property='yearPlanId' />
			<html:hidden property='componentPlanId' />
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="financial.year" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='financialYear' styleId='financialYear'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="populateField()">
				</html:select>
			</div>


			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="component" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass=" form-control">
				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-3 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" onclick="de_find()"
					style="width: 200px;">Populate Record</button>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="inspection.type" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="inspectionType" styleId="inspectionType"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="assigned" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="assignedTo" styleId="assignedTo"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="total.number.villages" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="numberOfVillage" styleId="numberOfVillage"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="period.for.implementation" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="periodIml" styleId="periodIml"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="No.Villages.Visited" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="visitVillage" styleId="visitVillage"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="visit.per.village" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="visitPerVillage" styleId="visitPerVillage"
					style="width: 150px;" styleClass="cs2 form-control"
					onblur="getTotalVisit();">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="total.visits" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="totalVisit" styleId="totalVisit"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="total.duration" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="totalDuration" styleId="totalDuration"
					style="width: 150px;" styleClass="cs2 form-control"
					onblur="getTotalMonthVisit()">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="village.visited" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="perMonthVisit" styleId="perMonthVisit"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="comments" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:textarea property="comments" styleId="comments"
					style="width: 400px;" styleClass="cs2 form-control">
				</html:textarea>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" style="width: 200px;"
					onclick="de_modify()">Update Record</button>
			</div>

		</div>
	</div>

</html:form>

</body>
<script type="text/javascript">
	function validateSearchFields() {
		var financialYear = document.getElementById("financialYear").value;
		var component = document.getElementById('component').value;

		if (financialYear === '' || financialYear === null) {
			alert('Please Select Financial Year');
			return false;
		} else if (component === '' || component === null) {
			alert('Please Select Component');
			return false;
		}
		return true;
	}

	function getTotalVisit() {

		if (document.getElementById("visitPerVillage").value != ""
				&& document.getElementById("visitPerVillage").value != null) {
			document.getElementById("totalVisit").value = parseInt(document
					.getElementById("visitVillage").value)
					* parseInt(document.getElementById("visitPerVillage").value);
		}
	}
	function getTotalMonthVisit() {
		if (document.getElementById("totalDuration").value != ""
				&& document.getElementById("totalDuration").value != null) {
			var result = parseInt(document.getElementById("totalVisit").value)
					/ parseInt(document.getElementById("totalDuration").value);

			document.getElementById("perMonthVisit").value = parseInt(result);
					

		}
	}
</script>
<script type="text/javascript">
	
</script>
</html:html>