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
		var result = validateField();
		if (result) {
			document.ddoMasterForm.action = "ddoMasterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN011";
			document.ddoMasterForm.submit();
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

<html:form action="ddoMasterAction" method="post"
	styleId="ddoMasterForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				DDO Master</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Zone" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='zone' styleId='zone'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction('GetFilterValues.to?parentCircle='+this.value+'&method=fetchCircle', 'circle');"></html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Circle" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="circle" styleId="circle"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?circle='+this.value+'&method=fetchDivision', 'division');">

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
						bundle="finance" key="Division" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>
			<div class=" form-inline col-lg-4 col-md-4 sm-hidden xs-hidden">&nbsp;
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
						bundle="finance" key="DDO.Number" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="ddoNumber" styleId="ddoNumber"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="DDO.Name" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="ddoName" styleId="ddoName"
					style="width: 150px;" styleClass="form-control">
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
						bundle="finance" key="Teasury" /></label>
				<html:select property="teasury" styleId="teasury"
					style="width: 150px;" styleClass="form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Status" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="status" styleId="status"
					style="width: 150px;" styleClass="form-control">
					<html:option value="1">Active</html:option>
					<html:option value="0">InActive</html:option>
				</html:select>
			</div>
		</div>
	</div>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="ddoMasterDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
							<th>DDO Number</th>
							<th>DDO Name</th>
							<th>Division</th>
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
						'GetFilterValues.to?parentZone=-1&method=fetchZone',
						'zone');

				document.getElementById("datatable_sh").style.display = '';
				ddoMaster.ddoMasterFn();
			});
	
	
	
	function validateField(){
		var zone = document.getElementById('zone').value;
		var circle = document.getElementById('circle').value;
		var division = document.getElementById('division').value;
		var ddoNumber = document.getElementById('ddoNumber').value;
		var ddoName = document.getElementById('ddoName').value;
		
		
		if(zone===''){
			alert('Please select Zone');
			return false;
		}if(circle===''){
			alert('Please select Circle');
			return false;
		}if(division===''){
			alert('Please select Division');
			return false;
		}if(ddoNumber===''){
			alert('DDO Number field could not be left blank');
			return false;
		}if(ddoName===''){
			alert('DDO Name field could not be left blank');
			return false;
		}
		
		return true;
		
		
	}
</script>
<script type='text/javascript'>
	
</script>
</html:html>