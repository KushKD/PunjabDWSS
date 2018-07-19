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
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>


<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);

	function de_add() {
		var result = validateField();
		if (result) {
			document.odfDeclarationForm.action = "odfDeclarationAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT010";
			document.odfDeclarationForm.submit();
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
					document.getElementById("p1").innerHTML = text;
					$("#myModal").modal('show');
				}
			</script>
	</logic:messagesPresent>

	<html:form action="odfDeclarationAction" method="post"
		styleId="odfDeclarationForm" enctype="multipart/form-data">


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
						onchange="ajaxFunction('receiveSampleAction.do?villageId='+this.value+'&method=fetchGramPanchayat', 'gramPanchayatId');">
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
					<label class="text-right labledesign">Date of Meeting</label>
					<html:text property="dateOfMeeting" styleId="dateOfMeeting"
						styleClass="ci5 form-control" style="width: 150px;"></html:text>

				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Place of Meeting</label>
					<html:select property="meetingPlace" styleId="meetingPlace"
						value="" styleClass="ci5 form-control" style="width: 150px;">
						<html:option value="">Select Place of Meeting</html:option>

						<html:option value="1">1</html:option>
						<html:option value="2">2</html:option>
					</html:select>
				</div>

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Chaired By</label>
					<html:text property="chairedBy" styleId="chairedBy"
						styleClass="ci5 form-control" style="width: 150px;"></html:text>

				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Declaration</label>
					<html:file property="declaration" styleId="declaration" value=""
						styleClass="ci5 form-control" style="width: 150px;"></html:file>

				</div>

			</div>
		</div>

		<!-- 

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

 -->

	</html:form>

</body>
<script type="text/javascript">
$('#dateOfMeeting,#defaultInline').datepick();
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