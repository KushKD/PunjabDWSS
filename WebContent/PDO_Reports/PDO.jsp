
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>

<style type="text/css">
div#footer {
	position: absolute;
	bottom: 0;
	top: 90%;
	left: -5px;
	width: 100%;
	height: 60px;
}

div#buttons {
	margin-left: 47%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<title>PDO Reports</title>
<link rel="stylesheet" href="css/prwss.css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/layout.js"></script>

<link href="css/jquery-ui-calendar.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery/jquery.js"></script>
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery-ui.min.js"></script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	//swap_nonswap_abstract

	function selectRadioZone() {

		var zone = document.getElementById('zoneId').value;
		if (zone != "") {
			document.getElementById('zoneSelection').checked = true;
		} else {
			document.getElementById('zoneAll').checked = true;
			document.getElementById('circleAll').checked = true;
			document.getElementById('districtAll').checked = true;
		}
	}

	function selectRadioCircle() {

		var circle = document.getElementById('circleId').value;
		if (circle != "") {
			document.getElementById('circleSelection').checked = true;
		} else {
			document.getElementById('circleAll').checked = true;
			document.getElementById('districtAll').checked = true;
		}
	}

	function selectRadioDistrict() {

		var district = document.getElementById('districtId').value;
		if (district != "") {
			document.getElementById('districtSelection').checked = true;
		} else {
			document.getElementById('districtAll').checked = true;
		}
	}

	function called() {

		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		var periodReport = habitationDataReport();

		document.pdoForm.action = "/PRWSS_MIS/pdoReportsAction.do?method=filePDF";
		document.pdoForm.submit();

	}

	function calledExcel() {

		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		var periodReport = habitationDataReport();

		document.pdoForm.action = "/PRWSS_MIS/pdoReportsAction.do?method=fileExcel";
		document.pdoForm.submit();

	}
	function de_filePrint() {
		document.committedLiabilityReportForm.action = "pdoReportsAction.do?method=filePrint";
		document.pdoForm.submit();
	}

	function de_file_swap() {
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today = (day + "-" + month + "-" + year);
		document.getElementById("toDate").value = today;
		document.pdoForm.action = "pdoReportsAction.do?method="
				+ (isPDF ? "filePDF" : "fileExcel");
		document.pdoForm.submit();
	}
	function disable_enable(ele) {
		var select = [ 'swap_nonswap_abstract', 'swap_nonswap_detail' ];
		//if(ele=='') return;
		for ( var i = 0; i < select.length; i++) {
			if (ele == select[i])
				document.getElementById(ele).disabled = false;
			else {
				document.getElementById(select[i]).disabled = true;
				document.getElementById(select[i]).selectedIndex = 0;
			}
		}
	}

	function habitationDataReport() {
		var val = "";
		var swap_nonswap_abstract = document
				.getElementById("swap_nonswap_abstract").value;
		//alert("swap_nonswap_abstract:  " + swap_nonswap_abstract);
		var swap_nonswap_detail = document
				.getElementById("swap_nonswap_detail").value;
		//alert("swap_nonswap_detail:  " + swap_nonswap_detail);

		if (swap_nonswap_abstract == "ABWRK_1"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_82";
			//alert("In2: " + val);
		}

		if (swap_nonswap_abstract == "ABWRK_2"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_81";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_3"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_84";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_4"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_86";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_5"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_88";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_6"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_90";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_7"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_97";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_8"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_92";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_9"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_75";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_10"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_76_1A";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_11"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_77_2A";
			//alert("In2: " + val);
		}
		if (swap_nonswap_abstract == "ABWRK_12"
				&& document.getElementById("selectReport1").checked) {
			val = "PMMRPT001_78_2B";
			//alert("In2: " + val);
		}

		if (swap_nonswap_detail == "DTWRK_1"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_79";
			//alert("In2: " + val);
		}

		if (swap_nonswap_detail == "DTWRK_2"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_80";
			//alert("In2: " + val);
		}
		if (swap_nonswap_detail == "DTWRK_3"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_83";
			//alert("In2: " + val);
		}
		if (swap_nonswap_detail == "DTWRK_4"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_85";
			//alert("In2: " + val);
		}
		if (swap_nonswap_detail == "DTWRK_5"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_87";
			//alert("In2: " + val);
		}
		if (swap_nonswap_detail == "DTWRK_6"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_89";
			//alert("In2: " + val);
		}
		if (swap_nonswap_detail == "DTWRK_7"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_96";
			//alert("In2: " + val);
		}
		if (swap_nonswap_detail == "DTWRK_8"
				&& document.getElementById("selectReport2").checked) {
			val = "PMMRPT001_91";
			//alert("In2: " + val);
		}

		return val;
	}
</script>

<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
-->
</head>
<body bgcolor="#3586BE">

	<table border="0" cellpadding="2" cellspacing="2" align="center"
		width="1200px" height="100px">

		<tr>
			<td height="20px" colspan="1"><%@include file="header.jsp"%>
				<hr /></td>
		</tr>
	</table>

	<html:form action="pdoReportsAction">
		<fieldset>

			<legend>Selection Criteria</legend>
			<center>

				<table>
					<%-- 		<tr>
						<td>Select Zone</td>
						<td><html:radio property="selectZone" title="Selection"
								value="S" styleId="selectZone">
					Selection</html:radio> <html:radio property="selectZone" title="All" value="A"
								styleId="selectZone">
					All</html:radio></td>
						<td colspan="2"><html:select property="zoneId"
								styleId="zoneId" style="width: 255px" styleClass="ci5"
								onchange="ajaxFunction('/PRWSS_MIS/GetFilterValues.to?zoneId='+this.value, 'circleId');triggerEvent(document.getElementById('circleId'), 'onchange');">

								<html:option value="">Select Zone</html:option>
								<option value="Z01">Z01->North Zone</option>
								<option value="Z02">Z02->South Zone</option>
								<option value="Z03">Z03->Central Zone</option>
								<html:options collection="zones" labelProperty="label" property="value"></html:options>
							</html:select></td>
					</tr>

					<tr>

						<td>Select Circle</td>
						<td><html:radio property="selectCircle" title="Selection"
								value="S" styleId="selectCircle">
					Selection</html:radio> <html:radio property="selectCircle" title="All" value="A"
								styleId="selectCircle">
					All</html:radio></td>
						<td colspan="2"><html:select property="circleId"
								styleId="circleId" style="width: 255px" styleClass="ci5"
								onchange="ajaxFunction('/PRWSS_MIS/GetFilterValues.to?circleId='+this.value, 'districtId');">

							</html:select></td>
					</tr>
					<tr>
						<td>Select District</td>
						<td><html:radio property="selectDistrict" title="Selection"
								value="S" styleId="selectDistrict">
					Selection</html:radio> <html:radio property="selectDistrict" title="All"
								value="A" styleId="selectDistrict">
					All</html:radio></td>
						<td colspan="2"><html:select property="districtId"
								styleId="districtId" style="width: 255px" styleClass="ci5">

							</html:select></td>
					</tr>

 --%>
<!-- Working Code -->
					<%-- <tr id='zone_s'>
						<td>Select Zone</td>
						<td><html:radio property="selectZone" title="Selection"
								value="S" styleId="zoneSelection">
					Selection</html:radio> <html:radio property="selectZone" title="All" value="A"
								styleId="zoneAll">
					All</html:radio></td>
						<td colspan="2"><html:select property="zoneId"
								styleId="zoneId" style="width: 255px" styleClass="ci5"
								onchange="ajaxFunction('/PRWSS_MIS/GetFilterValues.to?zoneId='+this.value, 'circleId');
					triggerEvent(document.getElementById('circleId'), 'onchange');selectRadioZone();">
								<html:option value="">Select Zone</html:option>
								<option value="Z01">Z01->North Zone</option>
								<option value="Z02">Z02->South Zone</option>
								<option value="Z03">Z03->Central Zone</option>
							</html:select></td>
					</tr> --%>
					<%-- <tr id=circle_s>
	<td>Select Circle</td>
	<td><html:radio property="selectCircle" title="Selection"
		value="S" styleId="circleSelection">
					Selection</html:radio> <html:radio property="selectCircle" title="All" value="A"
		styleId="circleAll">
					All</html:radio></td>
	<td colspan="2"><html:select property="circleId"
		styleId="circleId" style="width: 255px" styleClass="ci5"
		onchange="ajaxFunction('/PRWSS_MIS/GetFilterValues.to?circleId='+this.value, 'districtId');selectRadioCircle();">

	</html:select></td>
</tr> --%>
					<tr id='district_s'>
						<td>Select District</td>
						<td><html:radio property="selectDistrict" title="Selection"
								value="S" styleId="districtSelection">
					Selection</html:radio> <html:radio property="selectDistrict" title="All"
								value="A" styleId="districtAll">
					All</html:radio></td>
						<td colspan="2"><html:select property="districtId"
								styleId="districtId" style="width: 255px" styleClass="ci5"
								onchange="selectRadioDistrict();">
								<html:option value="">Select District</html:option>
								<option value="D01">Gurdaspur->D01</option>
								<option value="D02">Amritsar->D02</option>
								<option value="D03">Tarntaran->D03</option>
								<option value="D04">Kapurthala->D04</option>
								<option value="D05">Jalandhar->D05</option>
								<option value="D06">Hoshiarpur->D06</option>
								<option value="D07">SBS Nagar->D07</option>
								<option value="D08">Roopnagar->D08</option>
								<option value="D09">SAS Nagar->D09</option>
								<option value="D10">Fatehgarh Sahib->D10</option>
								<option value="D11">Ludhiana->D11</option>
								<option value="D12">Moga->D12</option>
								<option value="D13">Ferozepur->D13</option>
								<option value="D14">Muktsar->D14</option>
								<option value="D15">Faridkot->D15</option>
								<option value="D16">Bathinda->D16</option>
								<option value="D17">Mansa->D17</option>
								<option value="D18">Sangrur->D18</option>
								<option value="D19">Barnala->D19</option>
								<option value="D20">Patiala->D20</option>
								<option value="D21">Pathankot->D21</option>
								<option value="D22">Fazilka->D22</option>



							</html:select></td>
					</tr>

				</table>
			</center>
		</fieldset>


		<br>
		<br>


		<fieldset>
			<legend>PDO Reports</legend>
			<center>
				<table>
					<tr>
						<td><html:radio property="selectReport"
								value="swap_nonswap_abstract"
								onclick="disable_enable('swap_nonswap_abstract');"
								styleId="selectReport1">
						 Abstracts</html:radio></td>
						<td><select id="swap_nonswap_abstract" class="cs3"
							onchange="document.getElementById('selectReport1').value = habitationDataReport()"
							disabled="true">
								<option value="">Select Report</option>
								<option value="ABWRK_1">A-1 Villages with higher
									service level managed by GPWSC</option>
								<option value="ABWRK_2">A-2 Villages with higher
									service level managed by DWSS</option>
								<option value="ABWRK_3">A-3 No. of villages where
									GPWSCs that are managing O&M of water supply schemes through
									full cost recovery</option>
								<option value="ABWRK_4">A-4 Direct project
									beneficiaries</option>
								<option value="ABWRK_5">A-5 Village receiving improved
									quality of water</option>
								<option value="ABWRK_6">A-6 New Piped household water
									connections that are resulting from project Intervention</option>
								<option value="ABWRK_7">A-7 Piped household water
									connections that are benefiting from rehabilitation works
									undertaken by the project</option>
								<option value="ABWRK_8">A-8 Piped household sewer
									connections constructed under the project</option>
								<option value="ABWRK_9">A-9 Baseline of Service
									Delivery - All Villages</option>
								<option value="ABWRK_10">A-10 Service Delivery Progress
									on Component 1(A) in Participating Villages</option>
								<option value="ABWRK_11">A-11 Service Delivery Progress
									on Component 2(A) in Participating Villages</option>
								<option value="ABWRK_12">A-12 Service Delivery Progress
									on Component 2(B) in Participating Villages</option>


						</select></td>
						<td><html:radio property="selectReport"
								value="swap_nonswap_detail"
								onclick="disable_enable('swap_nonswap_detail');"
								styleId="selectReport2"> Details</html:radio></td>
						<td><select id="swap_nonswap_detail" class="cs3"
							onchange="document.getElementById('selectReport2').value = habitationDataReport()"
							disabled="true">

								<option value="">Select Report</option>
								<option value="DTWRK_1">D-1 Villages with higher
									service level managed by GPWSC</option>
								<option value="DTWRK_2">D-2 Villages with higher
									service level managed by DWSS</option>
								<option value="DTWRK_3">D-3 No. of villages where
									GPWSCs that are managing O&M of water supply schemes through
									full cost recovery</option>
								<option value="DTWRK_4">D-4 Direct project
									beneficiaries</option>
								<option value="DTWRK_5">D-5 Village receiving improved
									quality of water</option>
								<option value="DTWRK_6">D-6 New Piped household water
									connections that are resulting from project Intervention</option>
								<option value="DTWRK_7">D-7 Piped household water
									connections that are benefiting from rehabilitation works
									undertaken by the project</option>
								<option value="DTWRK_8">D-8 Piped household sewer
									connections constructed under the project</option>

						</select></td>
					</tr>
				</table>
			</center>
		</fieldset>

		<!-- <center>
		<input type="submit" value="Download PDF" onClick="called();" />
		<input type="submit" value="Download EXCEL" onClick="calledExcel();" />
		</center> -->

		<br>

		<div id="buttons">
			<a href="javascript:calledExcel();"> <img src="images/excel.gif"
				border="0" align="left" alt="Excel">
			</a> <a href="javascript:called();"> <img src="images/pdf.gif"
				border="0" align="left" alt="PDF">
			</a>
		</div>
	</html:form>


	<table>

		<tr id="tr_logo">
			<td id="dv_btn" style="width: 100%; vertical-align: bottom;"></td>
		</tr>
	</table>
	<center>
		<div id="footer"
			style="width: 100%; background-color: #00A2E2; border-bottom-style: inset; border-bottom-width: medium; border-bottom-color: #00A2E2; color: #FFFFFF;"
			align="center">
			All rights reserved<br> The site is designed & developed by <a
				href="http://www.deloitte.com/view/en_IN/in/index.htm"><font
				color="RED">Deloitte Touche & Tohmatsu India Pvt. Ltd</a></font>,
			Contents Provided by Department of Water Supply & Sanitation Punjab</font>
		</div>
	</center>

</body>
</html>