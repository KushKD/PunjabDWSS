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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beneficiary Entry</title>
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>


<script type="text/javascript">
	function de_add() {
		var result = validateField();
		if (result) {
			document.benifecieryForm.action = "benifecieryEntryAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT004";
			document.benifecieryForm.submit();
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

	<html:form action="benifecieryEntryAction" method="post"
		styleId="benifecieryForm" enctype="multipart/form-data">

		<div class="form-group">
			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Person Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right labledesign">Name:</label>
						<html:text property="personName" styleId="personName"
							styleClass="form-control" style="width:50%;"></html:text>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">Father/Spouse Name:</label>
						<html:text property="fatherSpouseName" styleId="fatherSpouseName"
							styleClass="form-control" style="width:50%;"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">B. Category:</label>
						<html:select property="benifCategory" styleId="benifCategory"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-right  labledesign">Cast:</label>
						<html:select property="cast" styleId="cast"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">Religion:</label>
						<html:select property="religion" styleId="religion"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">Phone Number:</label>
						<html:text property="phoneNumber" styleId="phoneNumber"
							style="width:50%;" styleClass="form-control"
							onkeypress="return validateKey1(event,	this,'9@10@3')"
							onchange="if(this.value.toString().length<10){
								alert('Mobile number should not be less than 10');
							}"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-6 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">Gender:</label>
						<html:select property="gender" styleId="gender"
							styleClass="form-control" style="width:50%;">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-right  labledesign">Photograph:</label>
						<html:file property="photograph" styleId="photograph"
							styleClass="form-control" style="width:50%;"></html:file>
					</div>
					<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
				</div>
			</div>



			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Location Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline  col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">District:</label>
						<html:select property="district" styleId="district"
							style="width:50%;" styleClass="form-control"
							onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
							<html:option value="">Select Location</html:option>
							<html:options collection="districtLocations"
								labelProperty="label" property="value"></html:options>
						</html:select>
					</div>
					<div class="form-inline  col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Block:</label>
						<html:select property="block" styleId="block" style="width:50%;"
							styleClass="form-control"
							onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'village');">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Village:</label>
						<html:select property="village" styleId="village"
							style="width:50%;" styleClass="form-control"
							onchange="ajaxFunction('GetFilterValues.to?villageId='+this.value+'&method=fetchGramPanchayat', 'gramPanchayat');">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">Gram Panchayat:</label>
						<html:select property="gramPanchayat" styleId="gramPanchayat"
							style="width:50%;" styleClass="form-control">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

				</div>
			</div>

			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Identity Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-right labledesign">POI Type:</label>
						<html:select property="poiType" styleId="poiType"
							style="width:50%;" styleClass="form-control">
							<html:option value="">Please select</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">POI Number:</label>
						<html:text property="poiNumber" styleId="poiNumber"
							style="width:50%;" styleClass="form-control"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-right  labledesign">Aadhar Card Number:</label>
						<html:text property="adharNumber" styleId="adharNumber"
							style="width:50%;" styleClass="form-control"
							onkeypress="return validateKey1(event,	this,'9@12@3')"
							onchange="if(this.value.toString().length<12){
								alert('Aadhar Number should not be less than 12');
							}"></html:text>
					</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">

						<label class="text-right labledesign">Electricity
							Connection Act. No.:</label>
						<html:text property="electricityCon" styleId="electricityCon"
							style="width:50%;" styleClass="form-control"></html:text>

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
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-right  labledesign">Electricity Bill:</label>
						<html:file property="electricityBill" styleId="electricityBill"
							style="width:50%;" styleClass="form-control"></html:file>
						<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
							&nbsp;</div>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

				</div>
			</div>

			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Bank Details</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left labledesign">Name of Bank:</label>
						<html:select property="bankName" styleId="bankName" style="width:50%;"
							styleClass="form-control">
							<html:option value="">Please select Bank</html:option>
						</html:select>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Branch:</label>
						<html:text property="branch" styleId="branch" style="width:50%;"
							styleClass="form-control"></html:text>
					</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">Account Number:</label>
						<html:text property="accountNo" styleId="accountNo" style="width:50%;"
							styleClass="form-control"
							onkeypress="return validateKey1(event,	this,'9@20@3')"></html:text>
					</div>
					<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
						<label class="text-left  labledesign">IFSC Code:</label>
						<html:text property="ifsCode" styleId="ifsCode" style="width:50%;"
							styleClass="form-control"></html:text>
					</div>

					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

				</div>
			</div>
		</div>

	</html:form>

</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						ajaxFunction(
								'GetFilterValues.to?gender=gender&method=getCombo',
								'gender');
						ajaxFunction(
								'GetFilterValues.to?caste_combo=caste_combo&method=getCombo',
								'cast');
						ajaxFunction(
								'GetFilterValues.to?category=category&method=getCombo',
								'benifCategory');
						ajaxFunction(
								'GetFilterValues.to?religion=religion&method=getCombo',
								'religion');
						ajaxFunction(
								'GetFilterValues.to?bankName=bankName&method=getCombo',
								'bankName');
						ajaxFunction(
								'GetFilterValues.to?poiType=poiType&method=getCombo',
								'poiType');
					});

	function validateField() {
		var personName = $('#personName').val();
		var fatherSpouseName = $('#fatherSpouseName').val();
		var benifCategory = $('#benifCategory').val();
		var cast = $('#cast').val();
		var religion = $('#religion').val();
		var phoneNumber = $('#phoneNumber').val();
		var gender = $('#gender').val();
		var photograph = $('#photograph').val();
		var district = $('#district').val();
		var block = $('#block').val();
		var village = $('#village').val();
		var gramPanchayat = $('#gramPanchayat').val();
		var poiType = $('#poiType').val();
		var poiNumber = $('#poiNumber').val();
		var adharNumber = $('#adharNumber').val();
		var electricityCon = $('#electricityCon').val();
		var electricityBill = $('#electricityBill').val();
		var bankName = $('#bankName').val();
		var branch = $('#branch').val();
		var accountNo = $('#accountNo').val();
		var ifsCode = $('#ifsCode').val();

		if (personName === '' || personName === null) {
			alert('Name field should not be blank');
			return false;
		}
		if (fatherSpouseName == '' || fatherSpouseName == null) {
			alert('Father/Spouse Name field should not be blank');
			return false;
		}
		if (benifCategory == '' || benifCategory == null) {
			alert('B. Category field should not be blank');
			return false;
		}
		if (cast == '' || cast == null) {
			alert('Cast field should not be blank');
			return false;
		}
		if (religion == '' || religion == null) {
			alert('Religion field should not be blank');
			return false;
		}
		if (phoneNumber == '' || phoneNumber == null) {
			alert('Phone Number field should not be blank');
			return false;
		}
		if (phoneNumber.toString().length < 10) {
			alert('Mobile number should not be less than 10');
			return false;
		}
		if (gender == '' || gender == null) {
			alert('Gender field should not be blank');
			return false;
		}
		if (photograph == '' || photograph == null) {
			alert('Photograph field should not be blank');
			return false;
		}
		if (district == '' || district == null) {
			alert('District field should not be blank');
			return false;
		}
		if (block == '' || block == null) {
			alert('Block field should not be blank');
			return false;
		}
		if (village == '' || village == null) {
			alert('Village field should not be blank');
			return false;
		}
		if (gramPanchayat == '' || gramPanchayat == null) {
			alert('Gram Panchayat field should not be blank');
			return false;
		}
		if (poiType == '' || poiType == null) {
			alert('POI Type field should not be blank');
			return false;
		}
		if (poiNumber == '' || poiNumber == null) {
			alert('POI Number field should not be blank');
			return false;
		}
		if (adharNumber == '' || adharNumber == null) {
			alert('Aadhar Card Number field should not be blank');
			return false;
		}
		if (adharNumber.toString().length < 12) {
			alert('Aadhar Number should not be less than 12');
			return false;
		}
		if (electricityCon == '' || electricityCon == null) {
			alert('Electricity Connection Act. No field should not be blank');
			return false;
		}
		if (electricityBill == '' || electricityBill == null) {
			alert('Electricity Bill field should not be blank');
			return false;
		}
		if (bankName == '' || bankName == null) {
			alert('Name of Bank field should not be blank');
			return false;
		}
		if (branch == '' || branch == null) {
			alert('Branch field should not be blank');
			return false;
		}
		if (accountNo == '' || accountNo == null) {
			alert('Account Number field should not be blank');
			return false;
		}
		if (ifsCode == '' || ifsCode == null) {
			alert('IFSC Code field should not be blank');
			return false;
		}

		return true;
	}
</script>
</html:html>