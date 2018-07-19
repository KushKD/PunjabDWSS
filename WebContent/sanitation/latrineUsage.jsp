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
<html:html>
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
<script type="text/javascript" 	src="js/bootstrap.min.js"></script>
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
<script type="text/javascript" 	src="js/bootstrap.min.js"></script>
		

<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);

	function de_add() {
		var result = validateField();
		if (result) {
			document.latrineUsageForm.action = "latrineUsageAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT009";
			document.latrineUsageForm.submit();
		}

	}
</script>


</head>
<body>
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
				document.getElementById("p1").innerHTML=text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

<html:form action="latrineUsageAction" method="post"
	styleId="latrineUsageForm">


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
					styleClass="ci5 form-control" style="width: 150px;"
					onchange="ajaxFunction('GetFilterValues.to?villageId4321='+this.value+'&method=getBeneficary', 'beneficiaryId');ajaxFunction('GetFilterValues.to?villages='+this.value+'&method=fetchGramPanchayats', 'gramPanchayatId');">
					<html:option value="">Select Village</html:option>
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Gram Panchayat</label>
				<html:select property="gramPanchayatId" styleId="gramPanchayatId"
					value="" styleClass="ci5 form-control" style="width: 150px;">
					<html:option value="">Select Gram Panchayat</html:option>
				</html:select>
			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Beneficiary</label>
				<html:select property="beneficiaryId" styleId="beneficiaryId" value=""
					styleClass="ci5 form-control" style="width: 150px;"
					>
					<html:option value="">Select Beneficiary</html:option>
					<html:option value="1">1</html:option>
					<html:option value="2">2</html:option>
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Used By All Members</label>
				<html:select property="membersId" styleId="membersId"
					value="" styleClass="ci5 form-control" style="width: 150px;">
					<html:option value="">Please select</html:option>
					<html:option value="1">Yes</html:option>
					<html:option value="2">No</html:option>
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
				
				<div class=" form-inline col-lg-9 col-md-9 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Comments</label>
				<html:textarea property="comments" styleId="comments"
					value="" styleClass="ci5 form-control" style="width: 150px;"></html:textarea>
					
			</div>
				
		</div>
	</div>
	


	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="latrineUsageDt"
					class="display nowrap table-responsive table-bordered table-striped table-hover "
					style="width: 100%">

					<thead>
						<tr>
						<th></th>
							<th>Beneficiary</th>
							<th>District</th>
							<th>Block</th>
							<th>Village</th>
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
document.getElementById("datatable_sh").style.display = '';
latrineUsage.latrineUsageFn();
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