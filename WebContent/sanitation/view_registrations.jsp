<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />



<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
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
	//hide_ctrl('modalPeriod', true);

	function de_find() {
     // x();
		/*  viewRegistration_gm.viewRegistration_gm_Type();
		document.getElementById('datatable_sh').style.display = ' ';  */
		
		var district=document.getElementById("district").value;
		var block=document.getElementById("block").value;
		var villageId=document.getElementById("villageId").value;
		
		
		
      	viewRegistration_gm.viewRegistration_gm_Type(district,block,villageId);
      	document.getElementById('datatable_sh').style.display = '';

	}

	function de_add() {
		//var status = validateFields();
		if (true) {
			document.viewRegistrationsForm.action = "viewRegistrationsAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SAN002";
			document.viewRegistrationsForm.submit();
		}
	}
</script>


</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">

		<div class="panel panel-body panel-danger" id="modalPeriod"
			style="position: absolute; min-width: 300px;; z-index: 121; background-color: #646b71; font-size: 150%; display: none;">

			<div class="row">
				<div class="col-lg-12">
					<font size="4" color="#FFFFFF"><b><html:errors
								bundle="sanitation" /></b></font>
				</div>
				<br>

				<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 ">
					<input type="button"
						class="navbar-footer btn btn-success col-lg-12" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;">
				</div>

			</div>
		</div>
		<!-- <script type="text/javascript">
			var alertObj = document.getElementById("modalPeriod");
			// center the alert box
			if (document.all && !window.opera)
				alertObj.style.top = document.documentElement.scrollTop + 50
						+ "px";
			alertObj.style.left = (document.documentElement.scrollWidth - alertObj.offsetWidth)
					/ 4 + "px";
			//alert(alertObj.style.left+":"+document.documentElement.scrollWidth+":"+ alertObj.offsetWidth);
			hide_ctrl('modalPeriod', false);
		</script> -->
</logic:messagesPresent>
<html:form action="viewRegistrationsAction" method="post"
	styleId="gramPanchayatRegisterForm">


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Location
				Details</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District</label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
					<html:option value="">Select Location</html:option>
					<html:options collection="districtLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Block</label>
				<html:select property="block" styleId="block"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
					<html:option value="">Please Select Block</html:option>
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Village</label>
				<html:select property="village" styleId="villageId" value=""
					styleClass="ci5 form-control" style="width: 150px;">


					<html:option value="">Select Village</html:option>
				</html:select>
			</div>


		</div>
	</div>



	<div class="panel panel-danger" id="datagrid">
		<div class="panel-body">
			<div id="datatable_sh"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12" style="display:none">
				<table id="viewRegisterDT"
					class="display table-responsive table-bordered  table-hover nowrap"
					cellspacing="0" width="100%">

					<thead>
						<tr>
							
							<th>Village Name</th>
							<th>Habitation Name</th>
							<th>Family Id</th>
							<th>Family Id</th>
						
							
							<!-- <th>Family Head Name</th>
						<th>Father/Husband Name</th>
						<th>Gender</th>
						<th>Caste</th>
						<th>Card Type</th>
						<th>Category</th>
						<th>Sub Category</th>
						<th>Aadhaar Card Number</th>
						<th>As Per Baseline Survey</th>
						<th>Is Covered</th>
						<th>Individual/Shared</th> -->

						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>



</html:form>



<script>

/* function x (){
	alert("x callled")
	
} */

</script>

</body>

</html:html>