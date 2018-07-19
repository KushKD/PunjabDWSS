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
<script type="text/javascript" src="js/waterquality.js"></script>
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
	/*  $(window).load(function(){
		 
		 labName.getLabName();
	}); */
	hide_ctrl('modalPeriod', true);

	function de_find() {
		document.labMasterForm.action = "labMasterAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=WQ002";
		//locationMaster.location1("locationMasterAction.do?method=populate&d__mode="+d__mode+"&menuId=ADM014");
		document.labMasterForm.submit();

	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			var result = true;//valid.validate();

			if (result) {
				document.labMasterForm.action = "labMasterAction.do?method=update&d__mode="
						+ d__mode + "&menuId=WQ002";
				document.labMasterForm.submit();
			}
		}
	}

	function de_add() {
		var result = true /* isCheckedWaterWorksLocation() */;
		var status = validateFields();

		if (status) {
			document.labMasterForm.action = "labMasterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=WQ002";
			document.labMasterForm.submit();
		}
	}
</script>



</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="waterQuality" style='display: none;'>
			<html:errors bundle="Waterquality" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('waterQuality');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

<html:form action="labMasterAction" method="post"
	styleId="labMasterForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Lab Master</h4>
			<div class="line"></div>




			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="Waterquality" key="lab.name" /><span class="text-danger">
						&nbsp;*</span> </label>

				<html:text property='labName' styleId='labName'
					styleClass="form-control ci5" style="width: 150px;"
					onkeypress="return lettersOnly()"></html:text>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Lab Level <span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="labLevel" styleId="labLevel"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Select Level</html:option>
					
				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<%-- 
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Tehsil</label>

				<html:select property="tehsil" styleId="tehsil"
					style="width: 150px;" styleClass="form-control">

				</html:select>


			</div> --%>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District <span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');ajaxFunction('GetFilterValues.to?parameterId=4&method=fetchDividions', 'division');">
					<html:option value="">Select Location</html:option>
					<html:options collection="districtLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>

			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Block <span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="block" styleId="block" style="width: 150px;"
					styleClass="form-control">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Division<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="division" styleId="division" style="width: 150px;"
					styleClass="form-control">
				</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Phone No <span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="phoneNo" styleId="phoneNo"
					style="width: 150px;" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@10@3')">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Contact Person <span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="contactPerson" styleId="contactPerson"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Mobile No<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="mobileNo" styleId="mobileNo"
					style="width: 150px;" styleClass="form-control" onkeypress="return validateKey1(event,	this,'9@10@3')">
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
				
				<div class=" form-inline col-lg-8 col-md-7 col-xs-12 col-sm-12">
				<label class="text-right labledesign">Address <span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:textarea property="address" styleId="address"
					style="width:40%;height:10%" styleClass="form-control">
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
				
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Status <span
					class="text-danger"> &nbsp;*</span>
				</label>

				<html:select property='status' styleId='status'
					styleClass="ci5 form-control" style="width: 150px;">
					<html:option value="1">Active</html:option>
					<html:option value="0">InActive</html:option>

				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Start date <span
					class="text-danger"> &nbsp;*</span>
				</label> <input type="text" name="startDate" style="width: 150px"
					id="startDate" class="ci5 form-control"
					value='<fmt:formatDate value="${dateVar}" pattern="MM-dd-yyyy"  />'></input>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>




			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">End date</label> <input
					type='text' name="endDate" style="width: 150px" id="endDate"
					class="ci5 form-control" />
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

		</div>
	</div>
	<input type='hidden' name='labId' id='labId'>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="labMasterDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
							<th></th>
							<th>Lab Name</th>
							<th>District</th>
							<th>Block</th>
							<!-- <th>Village</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>status</th>
							<th>Lab Id</th> -->

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
	$(document).ready(function() {
		document.getElementById("datatable_sh").style.display = '';
		LabMaster.LabMasterType();
	});

	function validateFields() {

		//var villageId = document.getElementById('villageId').value;
		var block = document.getElementById('block').value;
		//var tehsil = document.getElementById('tehsil').value;
		var district = document.getElementById('district').value;
		var labName = document.getElementById('labName').value;
		var endDate = document.getElementById('endDate').value;

		if (labName === "" || labName == null) {
			alert("Lab Name is mandatory")
			return false;
		} else if (district == "" || district == null) {
			alert("Please Select District");
			return false;
		} 
		/* else if (block == "" || block == null) {
			alert("Please Select Block");
			return false;
		} */ //else if (villageId == "" || villageId == null) {
		//alert("Please Select Village");
		//return false;
		//} 
		return true;
	}

	$('#startDate,#defaultInline').datepick();
	$('#endDate,#defaultInline').datepick();

	function validateEndDate() {
		var startDate = new Date($('#startDate').val());
		var endDate = new Date($('#endDate').val());
		if (endDate != '' && typeof endDate != 'undefined'
				&& endDate != 'Invalid Date') {
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0!
			var yyyy = today.getFullYear();

			if (dd < 10) {
				dd = '0' + dd
			}

			if (mm < 10) {
				mm = '0' + mm
			}

			today = mm + '/' + dd + '/' + yyyy;

			var EffectiveDate = $.trim($("[id$='endDate']").val());

			if (EffectiveDate < today) {
				alert('end date could not less than current date');
				return false;
			}
			if (endDate < startDate) {
				alert('end date could not less than start date');
				return false;
			}
		}

		return true;
	}

	$(document).ready(function() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!

		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd;
		}
		if (mm < 10) {
			mm = '0' + mm;
		}
		var today = mm + '/' + dd + '/' + yyyy;
		document.getElementById("startDate").value = today;

		if (document.getElementById('endDate') === 'undefined') {
			document.getElementById('endDate').value = '';
		}

	});
</script>
<script type='text/javascript'>

$(document)
.ready(
		function() {
			ajaxFunction(
					'GetFilterValues.to?parentComboId=173&method=getCombo',
					'labLevel');
		});
	$(document).ready(function() {
		$('.select-checkbox').prop('disabled', true);
	});
</script>
</html:html>