<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
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
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/constructionQuality.js"></script>
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
$(document).ready(function() {
	ajaxFunction(
			'GetFilterValues.to?parentComboId=106&method=getCombo',
			'yearPlan');
	ajaxFunction(
			'GetFilterValues.to?parentComboId=122&method=getCombo',
			'month');
	Req = "ent_frwrd";
	de_init('ent_frwrd', "test");

});
	

	function de_find() {
	 var status = validateFields();
	 if (status) {
	 var yearlyPlan = $('#yearPlan').val();
	 var month = $('#month').val();
	 monthlyPlan.monthlyPlanView(yearlyPlan, month);
	 document.getElementById('datatable_sh').style.display = '';
	 }
	 } 

</script>
<style type="text/css">
div.dataTables_wrapper {
        width: 1000px;
        margin: 0 auto;
    }
</style>
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

<html:form action="monthlyPlanViewAction" method="post"
	styleId="monthlyPlanInspectionForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				View Monthly Plan</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="financial.year" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='yearPlan' styleId='yearPlan'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="month" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='month' styleId='month'
					styleClass="form-control ci5" style="width: 150px;">
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
				<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" onclick="de_find()"
					style="width: 200px;">Populate Record</button>
			</div>
		</div>
	</div>

	<div class="panel panel-danger" id="datagrid">
		<div class="panel-body">
			<div id="datatable_sh"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12" style="display: none">
				<table id="monthlyPlanView"
					class="display table-responsive table-bordered  table-hover nowrap"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Component and Description</th>
							<th>Monthly Villages to be Visited</th>
							<th>Villages to be Visited This Month</th>
							<th>Team</th>
							<th>Scheme</th>
							<th>District</th>
							<th>Division</th>
							<th>Constituency</th>
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
<script type="text/javascript">
	function validateFields() {
		var month = $('#month').val();
		var financialYear = $('#yearPlan').val();
		if (financialYear === "") {
			alert("Please Select Financial Year");
			return false;
		}if (month === "") {
			alert("Please Select Month");
			return false;
		}
		return true;
	}
</script>

</html:html>