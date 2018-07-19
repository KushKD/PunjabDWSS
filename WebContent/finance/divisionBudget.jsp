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
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">

<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/finance.js"></script>
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
	function de_add() {
		document.getElementById('financialYearName').value = $(
				"#financialYear option:selected").text();
		var result;
		result = validateField();
		if (result) {
			if (validateQuarter()) {
				document.divisionBudgetForm.action = "divisionBudgetAction.do?method=save&d__mode="
						+ d__mode + "&menuId=FN004";
				document.divisionBudgetForm.submit();
			}
		}
	}
	
	function de_modify() {
		document.getElementById('financialYearName').value = $(
				"#financialYear option:selected").text();
		var result;
		result = validateField();
		if (result) {
			if (validateQuarter()) {
				document.divisionBudgetForm.action = "divisionBudgetAction.do?method=update&d__mode="
						+ d__mode + "&menuId=FN004";
				document.divisionBudgetForm.submit();
			}
		}
	}

	function validateQuarter() {
		var nextYearReq = document.getElementById('nextYearReq').value;

		nextYearReq = parseInt(nextYearReq);

		var quarter1 = document.getElementById('quarter1').value;
		var quarter2 = document.getElementById('quarter2').value;
		var quarter3 = document.getElementById('quarter3').value;
		var quarter4 = document.getElementById('quarter4').value;

		var quarter = parseFloat(quarter1) + parseFloat(quarter2)
				+ parseFloat(quarter3) + parseFloat(quarter4);

		if (nextYearReq != quarter) {
			alert('Sum of All Quarter should be equal  Requirement for Next Year');
			return false;
		}
		return true;

	}
</script>



</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="finance" style='display: none;'>
			<html:errors bundle="finance" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('finance');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

<html:form action="divisionBudgetAction" method="post"
	styleId="divisionBudgetForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				ANNUAL BUDGET - DIVISIONAL OFFICE</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="financial.year" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="financialYear" styleId="financialYear"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Division" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?divisionIds='+this.value+'&method=fetchSchemeByDivisionId', 'scheme');">
					<html:option value="">Select Location</html:option>
					<html:options collection="userLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<html:hidden property="financialYearName" styleId="financialYearName" />

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Scheme" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="scheme" styleId="scheme"
					style="width: 150px;" styleClass="cs2 form-control">

				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Component" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction(
								'divisionBudgetAction.do?componentId='+this.value+'&componentType=194&method=fetchChildComByComponent',
								'subComponent');ajaxFunction(
								'divisionBudgetAction.do?componentType='+this.value+'&method=fetchActivityByComponent',
								'activity');">

				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Sub.Component.LEVEL1" /> </label>
				<html:select property="subComponent" styleId="subComponent"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="	
						ajaxFunction(
								'divisionBudgetAction.do?componentId='+this.value+'&componentType=195&method=fetchChildComByComponent',
								'subSubComponent');">

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Sub.Component.LEVEL2" />  </label>
				<html:select property="subSubComponent" styleId="subSubComponent"
					style="width: 150px;" styleClass="form-control"
					onchange="	
						ajaxFunction(
								'divisionBudgetAction.do?componentId='+this.value+'&componentType=208&method=fetchChildComByComponent',
								'subSubComponentLevel3');
								ajaxFunction(
								'divisionBudgetAction.do?componentId='+this.value+'&componentType=209&method=fetchChildComByComponent',
								'activity');">
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

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Sub.Component.LEVEL3" /> </label>
				<html:select property="subSubComponentLevel3"
					styleId="subSubComponentLevel3" style="width: 150px;"
					styleClass="form-control"
					onchange="	
						ajaxFunction(
								'divisionBudgetAction.do?componentType='+this.value+'&method=fetchChildComByComponent',
								'activity');ajaxFunction(
								'divisionBudgetAction.do?componentId='+this.value+'&componentType=209&method=fetchChildComByComponent',
								'activity');">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Activity" /> <span class="text-danger">
						&nbsp;*</span></label>
				<html:select property="activity" styleId="activity"
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

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Estimated.Costs" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="estimatedCosts" styleId="estimatedCosts"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Already.expended" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="alreadyExpended" styleId="alreadyExpended"
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message bundle="finance"
						key="Requirement.for.Next.Year" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="nextYearReq" styleId="nextYearReq"
					style="width: 150px;" styleClass="cs2 form-control"
					onblur="fillQuarter()">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Quarter1" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="quarter1" styleId="quarter1"
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
				&nbsp;</div><div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div><div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Quarter2" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="quarter2" styleId="quarter2"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Quarter3" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="quarter3" styleId="quarter3"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Quarter4" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="quarter4" styleId="quarter4"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
		<html:hidden property="divAnnBudgId" styleId="divAnnBudgId"/>
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="divisionBudgetDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">
					<thead>
						<tr>
						<th></th>
							<th>Financial Year</th>
							<th>Component</th>
							<th>Division</th>
							<th>Budget Reference No</th>
							<th>Sub Component Level 1</th>
							<th>Sub Component Level 2</th>
							<th>Sub Component Level 3</th>
							<th>Activity</th>
							<th>Estimated Costs</th>
							<th>Already expended</th>
							<th>Requirement for Next Year</th>
							<th>Quarter 1</th>
							<th>Quarter 2</th>
							<th>Quarter 3</th>
							<th>Quarter 4</th>
							<th>Quarter 4</th>
							<th>Quarter 4</th>
							<th>Quarter 4</th>
							<th>Quarter 4</th>
							<th>Quarter 4</th>
							<th>id</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</html:form>

</body>
<script type='text/javascript'>
	$(document)
			.ready(
					function() {

						ajaxFunction(
								'GetFilterValues.to?parentComboId=106&method=getCombo',
								'financialYear');
						ajaxFunction(
								'divisionBudgetAction.do?componentType=192&method=fetchComponentType',
								'component');

						ajaxFunction(
								'divisionBudgetAction.do?componentType=186&method=fetchComponentType',
								'activity');
						
						document.getElementById("datatable_sh").style.display = '';
						divisionBudget.divisionBudgetFun();

						/* ajaxFunction(
								'GetFilterValues.to?parameterId=4&method=fetchDividions',
								'division'); */

					});

	function fillQuarter() {
		var nextYearReq = document.getElementById('nextYearReq').value;

		if (nextYearReq != '') {
			nextYearReq = parseInt(nextYearReq);
			//var quarter = (nextYearReq) / 4;
			document.getElementById('quarter1').value =(20*nextYearReq)/100;
			document.getElementById('quarter2').value = (30*nextYearReq)/100;
			document.getElementById('quarter3').value = (30*nextYearReq)/100;
			document.getElementById('quarter4').value = (20*nextYearReq)/100;
		}
	}
	function validateField() {
		if ($('#financialYear').val() === '') {
			alert('Financial Year Field could not left blank');
			return false;
		} else if ($('#financialYear').val() === '') {
			alert('Financial Year Field could not left blank');
			return false;
		} else if ($('#division').val() === '') {
			alert('Division Field could not left blank');
			return false;
		} else if ($('#scheme').val() === '') {
			alert('Scheme Field could not left blank');
			return false;
		} else if ($('#financialYearName').val() === '') {
			alert('Financial Year Name Field could not left blank');
			return false;
		} else if ($('#component').val() === '') {
			alert('Component Field could not left blank');
			return false;
		} else if($('#activity').val()===''){
			alert('Activity Field could not left blank');
			return false;
		}else if ($('#estimatedCosts').val() === '') {
			alert('Estimated Costs Field could not left blank');
			return false;
		} else if ($('#alreadyExpended').val() === '') {
			alert('Already Expended Field could not left blank');
			return false;
		} else if ($('#nextYearReq').val() === '') {
			alert('Next Year Requirement Field could not left blank');
			return false;
		} else if ($('#quarter1').val() === '') {
			alert('Quarter 1 Field could not left blank');
			return false;
		} else if ($('#quarter2').val() === '') {
			alert('Quarter 2 Field could not left blank');
			return false;
		} else if ($('#quarter3').val() === '') {
			alert('Quarter 3 Field could not left blank');
			return false;
		} else if ($('#quarter4').val() === '') {
			alert('Quarter 4 Field could not left blank');
			return false;
		}
		return true;
	}
</script>

</html:html>