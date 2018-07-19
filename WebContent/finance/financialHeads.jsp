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
	hide_ctrl('modalPeriod', true);

	function de_find() {
		document.financeHeadsForm.action = "labMasterAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=FN001";
		document.financeHeadsForm.submit();

	}

	function de_add() {
		var result = validateForm();
		if (result) {
			document.financeHeadsForm.action = "financialHeadsAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN001";
			document.financeHeadsForm.submit();
		}
	}

	function de_modify() {
		if (d__mode == 'ent_modify') {
			var result = validateForm();
			if (result) {
				document.financeHeadsForm.action = "financialHeadsAction.do?method=update&d__mode="
						+ d__mode + "&menuId=FN001";
				document.financeHeadsForm.submit();
			}
		}
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

<html:form action="financialHeadsAction" method="post"
	styleId="financeHeadsForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Finance Heads</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="head.type" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='headType' styleId='headType'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction('financialHeadsAction.do?headType='+this.value+'&method=fetchParent','parent')"></html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="description" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="description" styleId="description"
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
						bundle="finance" key="number" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="number" styleId="number" style="width: 150px;"
					styleClass="cs2 form-control">
				</html:text>

			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="parent" /> </label>
				<html:select property="parent" styleId="parent"
					style="width: 150px;" styleClass="form-control">

				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
		</div>
	</div>
	<input type='hidden' name='financeHeadId' id='financeHeadId'>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="financeHeadDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
						<th></th>
							<th>Head Type</th>
							<th>Description</th>
							<th>Number</th>
							<th>Parent</th>
							<th>Head Id</th>
							<th>Head Type Id</th>
							<th>Parent Head</th>
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
	$(document).ready(
			function() {

				ajaxFunction(
						'GetFilterValues.to?parentComboId=177&method=getCombo',
						'headType');

				document.getElementById("datatable_sh").style.display = '';
				financeHead.financeHeadType();
			});

	function validateForm() {
		var headType = document.getElementById('headType').value;
		var description = document.getElementById('description').value;
		var number = document.getElementById('number').value;
		var parent = document.getElementById('parent').value;

		if (headType === '') {
			alert("Please select Head Type");
			return false;
		}
		if (description === '') {
			alert("Please Enter Description");
			return false;
		}
		if (number === '') {
			alert("Please Enter Number");
			return false;
		}

		if (headType != '169') {
			if (parent === '-1') {
				alert("Please Select Parent");
				return false;
			}
		}
		return true;
	}
</script>
<script type='text/javascript'>
	
</script>
</html:html>