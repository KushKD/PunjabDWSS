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

	function de_find() {
		document.financeHeadsForm.action = "labMasterAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=FN001";
		document.financeHeadsForm.submit();

	}

	function de_add() {
		var result = validateFields();
		if (result) {
			document.fundSourceMasterForm.action = "fundSourceMasterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN009";
			document.fundSourceMasterForm.submit();
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

<html:form action="fundSourceMasterAction" method="post"
	styleId="fundSourceMasterForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Fund Source Master</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Program" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='program' styleId='program'
					styleClass="form-control ci5" style="width: 150px;" onchange="ajaxFunction('fundSourceMasterAction.do?programId='+this.value+'&method=fetchSchemeByProgram','scheme')"></html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Scheme" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="scheme" styleId="scheme"
					style="width: 150px;" styleClass="cs2 form-control">

				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-6 col-md-7 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Fund.Source.Master" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="fundSrcMaster" styleId="fundSrcMaster"
					style="width: 300px;" styleClass="cs2 form-control">
				</html:text>

			</div>
			<div class=" form-inline col-lg-2 col-md-3 col-xs-12 col-sm-6">
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
			<div class=" form-inline col-lg-4 col-md-7 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="State.Share" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="stateShare" styleId="stateShare"
					style="width: 150px;" styleClass="cs2 form-control" onkeypress="return validateKey1(event,	this,'9@17@3')">
				</html:text>

			</div>
			<div class=" form-inline col-lg-4 col-md-7 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Center.Share" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="centerShare" styleId="centerShare"
					style="width: 150px;" styleClass="cs2 form-control" onkeypress="return validateKey1(event,	this,'9@17@3')">
				</html:text>

			</div>
		</div>
	</div>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="fundSourceMasterDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
							<th>Scheme</th>
							<th>Fund Source Master</th>
							<th>State Share</th>
							<th>Center Share</th>
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
						$(document)
								.ready(
										function() {
											ajaxFunction(
													'fundSourceMasterAction.do?method=fetchProgram',
													'program')

											document
													.getElementById("datatable_sh").style.display = '';
											fundSourceMst.fundSourceMstFun();
										});
					});

	
</script>
<script type='text/javascript'>
function validateFields() {
	var program = document.getElementById('program').value;
	var scheme = document.getElementById('scheme').value;
	var fundScMaster = document.getElementById('fundSrcMaster').value;
	var centerShare = document.getElementById('centerShare').value;
	var stateShare = document.getElementById('stateShare').value;
	
	
	if(program===''){
		alert('Please select Program');
		return false;
	}if(scheme===''){
		alert('Please select Program');
		return false;
	}if(fundScMaster===''){
		alert('Fund source Master field could not be left blank');
		return false;
	}if(centerShare===''){
		alert('Center share field could not be left blank');
		return false;
	}if(stateShare===''){
		alert('State Share field could not be left blank');
		return false;
	}
	return true;
}
</script>
</html:html>