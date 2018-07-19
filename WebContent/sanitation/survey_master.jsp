<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>


<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/sanitation.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="js/dataTables.select.min.js"></script>

<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>

<script type="text/javascript" src="js/buttons.flash.min.js"></script>


<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script>
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script type="text/javascript" src="js/buttons.colVis.min.js"></script>




<script type="text/javascript">
	hide_ctrl('modalPeriod', true);
	
	function de_modify() {
		
		if (d__mode == 'ent_modify'){
			var result = true;//valid.validate();

			if (result) {
				document.surveyMasterForm.action = "surveyMasterAction.do?method=update&d__mode="
						+ d__mode + "&menuId=SAN003";
				document.surveyMasterForm.submit();
			}
		}
	}
	
	function de_add() {
		var status = validateFields();
		
		if (status) {
			document.surveyMasterForm.action = "surveyMasterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SAN003";
			document.surveyMasterForm.submit();
		}
	}
</script>

</head>


<html:html>

	<logic:messagesPresent>
		<body bgcolor="#6699FF">
			<div id="modalContainer"></div>
			<p id="sanitation1" style='display: none;'>
				<html:errors bundle="sanitation" />
			</p>
			<script type="text/javascript">
				displayMessage(true);
				var para = document.getElementById('sanitation1');
				var text = para.firstChild.nodeValue;
				if (text != "") {
					document.getElementById("p1").innerHTML = text;
					$("#myModal").modal('show');
				}
			</script>
	</logic:messagesPresent>
<html:form action="surveyMasterAction" method="post"
	styleId="surveyMasterForm">


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Survey Master</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Survey Name</label>
				<html:text property='surveyName' styleId='surveyName'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Survey Status</label>
				<html:select property="surveyStatus" styleId="surveyStatus"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
					<html:option value="">Select</html:option>
					<html:option value="1">Initiated</html:option>
					<html:option value="2">Ongoing</html:option>
					<html:option value="3">Finished</html:option>
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Planned Start Date</label> 
				
				<input
					type="text" name="plannedStartDate" style="width: 150px" id="plannedStartDate"
					class="ci5 form-control"
					></input>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Planned End Date</label> <input
					type='text' name="plannedEndDate" style="width: 150px" id="plannedEndDate"
					class="ci5 form-control" />

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Actual Start Date</label> <input
					type="text" name="actualStartDate" style="width: 150px" id="actualStartDate"
					class="ci5 form-control"
					></input>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Actual End Date</label> <input
					type='text' name="actualEndDate" style="width: 150px" id="actualEndDate"
					class="ci5 form-control" />

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Purpose</label>
				<html:text property='purpose' styleId='purpose'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


		</div>
	</div>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="surveyMasterDT"
					class="display nowrap table-responsive table-bordered table-striped table-hover "
					style="width: 100%">

					<thead>
						<tr>
							<th></th>
							<th>Survey Name</th>
							<th>Survey Status</th>
							<th>Planned Start date</th>
							<th>Planned End date</th>
							<th>Actual Start date</th>
							<th>Actual End date</th>
							<th>Purpose</th>

						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>




</html:form>

<script type='text/javascript'>
$('#plannedStartDate,#defaultInline').datepick();
$('#plannedEndDate,#defaultInline').datepick();
$('#actualStartDate,#defaultInline').datepick();
$('#actualEndDate,#defaultInline').datepick();

SurveyMaster.SurveyMasterType();
document.getElementById("datatable_sh").style.display = '';



function validateFields() {
	
	var surveyName = document.getElementById('surveyName').value;
	var surveyStatus = document.getElementById('surveyStatus').value;
	var plannedStartDate = document.getElementById('plannedStartDate').value;
	var plannedEndDate = document.getElementById('plannedEndDate').value;
	var actualStartDate = document.getElementById('actualStartDate').value;
	var actualEndDate = document.getElementById('actualEndDate').value;
	var purpose = document.getElementById('purpose').value;
	
	if (surveyName == "" || surveyName == null) {
		alert("Enter Survey Name ")
		return false;
	} 
	else if (surveyStatus == "" || surveyStatus == null) {
		alert("Please Select Survey Status");
		return false;
	}
	else if(plannedStartDate == "" || plannedStartDate == null) {
		alert("Please Select Planned Start Date");
		return false;
	}
	else if(plannedEndDate == "" || plannedEndDate == null) {
		alert("Please Select Planned End Date");
		return false;
		} 
	 else if(plannedEndDate != null) {

		var userDate = Date.parse(plannedEndDate);
		var userStartDate = Date.parse(plannedStartDate);

		if (userDate < userStartDate) {
			alert("Planned End Date cannot be less than Planned Start Date");
			return false;
		}


		}
	if (actualStartDate == ""||actualStartDate==null) {
		alert("Please Select  Actual Start Date");
		return false;
	} 
	else if(actualEndDate == "" || actualEndDate == null) {
		alert("Please Select Actual End Date");
		return false;
		} 
	else if (actualEndDate != null) {
		var userDate1 = Date.parse(actualEndDate);
		var userStartDate1 = Date.parse(actualStartDate);

		if (userDate1 < userStartDate1) {
			alert("Actual End Date cannot be less than Actual Start Date");
			return false;
		}

	}
	
	 if (purpose == "" || purpose == null) {
		alert("Please Discribe Purpose");
		return false;
	}
		
	else return true;	
}



</script>

</body>

</html:html>