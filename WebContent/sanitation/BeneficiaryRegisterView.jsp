<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>



<!DOCTYPE html>
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

<script type="text/javascript" src="js/jquery.plugin.js"></script>

<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/sanitation.js"></script>
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
<style type="text/css">
/* th {
	background: #d2c8c8;
} */
.modal-header {
	background-color: #000000;
	padding: 6px 6px;
	color: #E8EAF6;
	border-bottom: 2px dashed #000000;
}

.modal-title {
	text-align: center;
}

.close {
	color: #ffffff
}

.close {
	color: #fff;
	opacity: 1;
}

.modal-dialog {
	overflow-y: initial !important
}

.modal-body {
	height: 85%;
	overflow-y: auto;
}

.modal-dialog {
	width: 971px;
	height: 700px overflow-y: initial !important
}

/* .modal-content {
	width: 400px;
} */

/* .modal-footer {
	margin: -10px;
} */
</style>

<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);

	function de_find() {
		var status = validateField();
		if (status) {
			var district = $('#district').val();
			var block = $('#block').val();
			var villageId = $('#villageId').val();
			viewBeneficiary.viewBeneficiaryType(district, block, villageId);
			document.getElementById('datatable_sh').style.display = '';
		}

	}
</script>


</head>


<html:html>

<html:form action="benifecieryEntryViewAction" method="post"
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
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Beneficiary Entry Details</h4>
				</div>
				<div class="modal-body">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>


	<input type='hidden' name='beneficiaryId' id='beneficiaryId'>

	<div class="panel panel-danger" id="datagrid">
		<div class="panel-body">
			<div id="datatable_sh"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12"
				style="display: none">
				<table id="beneficiaryRegisterDT"
					class="display table-responsive table-bordered  table-hover nowrap"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Person Name</th>
							<th>Father/Spouse Name</th>
							<th>Religion</th>
							<th>Aadhar Card Number</th>
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
	function validateField() {
		var district = $('#district').val();
		var block = $('#block').val();
		var villageId = $('#villageId').val();
		if (district === '' || district === null) {
			alert("Please Select District");
			return false;
		}
		if (block === '' || block === null) {
			alert("Please Select Block");
			return false;
		}
		if (villageId === '' || villageId === null) {
			alert("Please Select Village");
			return false;
		}

		return true;
	}
</script>
</html:html>