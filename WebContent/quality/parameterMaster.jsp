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
	hide_ctrl('modalPeriod', true);

	function de_add() {
		/* var result = true *//* isCheckedWaterWorksLocation() */;
		var status = validateFields();
		var status_d = validateEndDate();
		//alert(status)
		if (status_d) {
			if (status) {
				document.parameterMasterForm.action = "parameterMasterAction.do?method=save&d__mode="
						+ d__mode + "&menuId=WQ006";
				document.parameterMasterForm.submit();
			}
		}
	}

	function de_modify() {
		//	var result = true /* isCheckedWaterWorksLocation() */;
		if (d__mode == 'ent_modify') {
			var status = validateFields();
			var status_d = validateEndDate();
			if (status_d) {
				if (status) {
					document.parameterMasterForm.action = "parameterMasterAction.do?method=update&d__mode="
							+ d__mode + "&menuId=WQ006";
					document.parameterMasterForm.submit();
				}
			}
		}
	}
</script>

<style type="text/css">
th {
	background: #d2c8c8;
}
</style>

</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="Waterquality" style='display: none;'>
			<html:errors bundle="Waterquality" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('Waterquality');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>
<html:form action="parameterMasterAction" method="post"
	styleId="parameterMasterForm">




	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Parameter Master</h4>
			<div class="line"></div>

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Parameter Name</label>

				<html:text property="parameterName" styleId="parameterId1"
					style="width:150px" styleClass="form-control"
					onkeypress="return lettersOnly()"></html:text>
			</div>

			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Unit Of Measurement</label>
				<%-- <html:select property="uom" styleId="uom"
					styleClass="cs1 form-control" style="width:150px">
					<html:option value="">Select</html:option>
					<html:option value="1">mL/L</html:option>
					<html:option value="2">NTU</html:option>
					<html:option value="3">ppm</html:option>
					<html:option value="4">mg/L</html:option>

				</html:select> --%>
				<html:select property="uom" styleId="uom"
					styleClass="cs1 form-control" style="width:150px">
					<html:option value="">Please Select</html:option>
				</html:select>

			</div>


			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Acceptable Limit</label>
				<html:text property="acceptableLimit" styleId="acceptableLimit"
					styleClass="ci5 form-control" style="width:150px;"
					onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text>
			</div>

			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Permissible Limit</label>

				<html:text property="permissibleLimit" styleId="permissibleLimit"
					styleClass="form-control" style="width:150px;"
					onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text>
					
				<INPUT TYPE="checkbox" name="noRelaxation" styleId="noRelaxation"
					id="noRelaxation" value="-1" />No Relaxation

			</div>

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<%-- <div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" >WHO
					Permissible Limit</label>
				<html:text property="wHOPermissibleLimit"
					styleId="wHOPermissibleLimit" styleClass="ci5 form-control" onkeypress="return isNumberKey(event)"
					style="width:150px;"></html:text>

			</div>
			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" >WHO
					Acceptable Limit</label>

				<html:text property="wHOAcceptableLimit"
					styleId="wHOAcceptableLimit" styleClass="ci5 form-control" onkeypress="return isNumberKey(event)"
					style="width:150px;"></html:text>

			</div>

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div> --%>

			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Status</label>
				<html:select property='status' styleId='status'
					styleClass="ci5 form-control" style="width:150px;">
					<html:option value="1">Active</html:option>
					<html:option value="0">InActive</html:option>

				</html:select>

			</div>



			<div class="col-lg-5 col-md-5 col-sm-6 xs-hidden">&nbsp; &nbsp;</div>
			<div class="col-lg-5 col-md-5 col-sm-6 xs-hidden">&nbsp; &nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Start Date</label> <input
					type="text" name="startDate" id="startDate"
					class="ci5 form-control" style="width: 150px;"
					value='<fmt:formatDate value="${dateVar}" pattern="MM-dd-yyyy"  />'></input>

			</div>

			<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">End Date</label>

				<html:text property="endDate" styleId="endDate" style="width:150px;"
					readonly="readonly" styleClass="form-control"></html:text>

			</div>




			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


		</div>
	</div>



	<br>

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
				<table id="parameterDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					cellspacing="0" width="80%">

					<thead>
						<tr>
							<th></th>
							<th>Parameter Name</th>
							<th>Unit Of Measurement</th>
							<th>Acceptable Limit</th>
							<th>Permissible Limit</th>
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
		ajaxFunction('GetFilterValues.to?uom=uom&method=getCombo', 'uom');
	});

	function validateFields() {
		var parameterName = document.getElementById('parameterId1').value;
		var unitsMeasurements = document.getElementById('uom').value;
		var noRelaxation = document.getElementById('noRelaxation').value;
		var pLimit = document.getElementById('permissibleLimit').value;
		var aLimit = document.getElementById('acceptableLimit').value;
		/* 		var whoPLimit = document.getElementById('wHOPermissibleLimit').value;
		 var whoALimit = document.getElementById('wHOAcceptableLimit').value; */
		var startDate = document.getElementById('startDate').value;
		var endDate = document.getElementById('endDate').value;

		if (parameterName === "" || parameterName == null) {
			alert("Parameter Name is mandatory")
			return false;
		} else if (unitsMeasurements == "" || unitsMeasurements == null) {
			alert("Please Select Units of Measurement");
			return false;
		} else if (aLimit == "" || aLimit == null) {
			alert("Please enter Acceptable Limit");
			return false;
		} else if (pLimit == "" || pLimit == null) {
			if (!(document.getElementById('noRelaxation').checked)) {
				alert("Please enter Permissible Limit");
				return false;

			}
		}if (!(document.getElementById('noRelaxation').checked)) {
		var aNum = new Number(aLimit);
		var pNum = new Number(pLimit);
		if (aNum > pNum) {
			alert("Acceptable Limit Should be Less Then Permissible Limit");
			return false;
		}
		}
		return true;
	}

	$('#startDate,#defaultInline').datepick();
	$('#endDate,#defaultInline').datepick();

	function validateEndDate() {
		var startDate = new Date($('#startDate').val());
		var endDate = new Date($('#endDate').val());
		if (endDate != '' && typeof endDate != 'undefined'
				&& endDate != 'Invalid Date') {
			/* var today = new Date();
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
			} */
			if (endDate < startDate) {
				alert('end date could not less than start date');
				return false;
			}
		}

		return true;
	}

	$(document).ready(function() {
		parameterMaster.parameter1();
		document.getElementById("datatable_sh").style.display = '';

		$('.select-checkbox').prop('disabled', true);

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

	$(function() {
		$('#noRelaxation').change(function() {
			$('#permissibleLimit').attr('disabled', this.checked)
		});
	});

	/*  	$(function(){
	 $("#noRelaxation").change(function(){
	 $("#permissibleLimit").val("").attr("disabled",true);
	 if($("#noRelaxation").is(":unchecked")){
	 $("#permissibleLimit").removeAttr("disabled");
	 $("#permissibleLimit").focus();
	 }
	 else if($("#example_1").is(":checked")){
	 $("#field2").removeAttr("readonly");
	 $("#field2").focus();   
	 }  
	 });
	 });  */

	$(window).load(function() {
		$('.select-checkbox').prop('disabled', true);
	});
</script>
</html:html>