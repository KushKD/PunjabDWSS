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



<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
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
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	hide_ctrl('modalPeriod', true);
	function de_modify() {
		if (d__mode == 'ent_modify') {
			var result = true;//valid.validate();

			if (result) {
				document.sampleDistributionForm.action = "sampleDistributionAction.do?method=update&d__mode="
						+ d__mode + "&menuId=ADM014";
				document.sampleDistributionForm.submit();
			}
		}
	}
	function de_add() {
		var status = validateFields();
		if (status) {
			document.sampleDistributionForm.action = "sampleDistributionAction.do?method=save&d__mode="
					+ d__mode + "&menuId=WQ003";
			document.sampleDistributionForm.submit();

		}
	}

	function de_qrcode() {
		var samplePartNumber = document.getElementById('samplePartNum').value;
		if (samplePartNumber != '') {
			document.sampleDistributionForm.action = "sampleDistributionAction.do?method=generateQrCode&d__mode="
					+ d__mode
					+ "&menuId=WQ003"
					+ "&samplePartNumber="
					+ samplePartNumber;
			document.sampleDistributionForm.submit();
		}
	}
	
</script>

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
			var para = document.getElementById("Waterquality");
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

<html:form action="sampleDistributionAction" method="post"
	styleId="sampleDistributionForm">




	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Sample Distribution</h4>
			<div class="line"></div>


			<input type='hidden' name='distributionId' id='distributionId'>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Lab Name</label>
				<html:select property="labName" styleId="labName"
					styleClass="form-control" style="width:200px;"
					onchange="ajaxFunction('sampleDistributionAction.do?labname='+this.value+'&method=fetchSampleNo', 'sampleNum');ajaxFunction('sampleDistributionAction.do?labname='+this.value+'&method=fetchEmployee', 'technician');ajaxFunction('sampleDistributionAction.do?labId='+this.value+'&method=fetchTests','tests')">
					<html:option value="">Select Lab</html:option>
					<html:options collection="labNames" labelProperty="label"
						property="value"></html:options>
				</html:select>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Sample Number</label>

				<html:select property='sampleNum' styleId='sampleNum'
					styleClass="form-control" style="width:200px;"></html:select>
			</div>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<div class="checkbox ">
					<label class="text-right labledesign">Freeze</label>
					<html:checkbox property="freeze" styleId="freeze"
						styleClass="form-control">
					</html:checkbox>
				</div>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Technician</label>
				<html:select property="technician" styleId="technician"
					style="width: 200px;" styleClass="form-control">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="font-size: 11px">Distribution
					Date</label> <input type='text' name="distributionDate"
					style="width: 200px" id="distributionDate" class="ci5 form-control" />

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Sample Part No.</label>

				<html:text property='samplePartNum' styleId='samplePartNum'
					styleClass="form-control ci5" style="width:200px;"
					onkeypress="return isNumberKey(event)" readonly="true"></html:text>

			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Tests</label>
				<html:select property="tests" styleId="tests" style="width: 200px;"
					styleClass="form-control" onchange="getSamplePartNo(this.value)">
				</html:select>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Sample Type</label>
				<html:select property="sampleType" styleId="sampleType"
					style="width: 200px;" styleClass="form-control">
					<html:option value="Normal">Normal</html:option>
					<html:option value="Reserve">Reserve</html:option>
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


		</div>
	</div>


	<br>

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
				<table id="sampleDistributionDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					cellspacing="0" width="100%">

					<thead>
						<tr>
							<th></th>
							<th>Sample Number</th>
							<th>Sample Part Number</th>
							<th>Lab</th>
							<th>Tests</th>
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
		SampleDistribution.SampleDistributionType();
		document.getElementById('datatable_sh').style.display = ' ';
	});

	//$('#requiredBy,#defaultInline').datepick();
	$('#distributionDate,#defaultInline').datepick();

	function validateFields() {

		var sampleNum = document.getElementById('sampleNum').value;
		var labName = document.getElementById('labName').value;
		//var locationname = document.getElementById('locationname').value;
		var distributionDate = document.getElementById('distributionDate').value;
		var technician = document.getElementById('technician').value;
		var samplePartNum = document.getElementById('samplePartNum').value;
		var tests = document.getElementById('tests').value;
		//var requiredBy = document.getElementById('requiredBy').value;

		var x = distributionDate.split('/');
		var distributionDate1 = new Date(x[2], (x[0] - 1), x[1]);

		var currentDate = new Date();
		var dt1 = currentDate.getDate();
		var mon1 = currentDate.getMonth();
		var yr1 = currentDate.getFullYear();
		currentDate = new Date(yr1, mon1, dt1);


		if (distributionDate1 - currentDate != 0) {
			alert('Distribution Date should be Current date');
			return false;
		}

		if (sampleNum === "" || sampleNum == null) {
			alert("Enter Sample Num")
			return false;
		} else if (labName == "" || labName == null) {
			alert("Please Select labName");
			return false;
		} else if (distributionDate == "" || distributionDate == null) {
			alert("Please Select distribution Date ");
			return false;
		} else if (technician == "" || technician == null) {
			alert("Please Select Technician");
			return false;
		} else if (samplePartNum == "" || samplePartNum == null) {
			alert("Please enter sample Part Num");
			return false;
		} else if (tests == "" || tests == null) {
			alert("Please Select Tests");
			return false;
		}
		/*  else if (requiredBy == "" || requiredBy == null) {
			alert("Please Enter required by");
			return false;
		}
		 */
		else if (distributionDate == "") {
			alert("Distribution  Date cannot  be null");
			return false;
		} /* else if (distributionDate != null) {

							var userDate = converToDate(distributionDate);
							var today = dateToday();

							if (userDate > today) {
								alert("Distribution Date cannot be Future  Date");
								return false;
							}

						} */
		return true;
	}

	function getSamplePartNo(testType) {

		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			// alert(1);
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			// alert(2);
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"sampleDistributionAction.do?method=fetchSamplePartNo&testType="
						+ testType, false);
		xmlHttp.send(null);
		// alert(3);
		if (xmlHttp.readyState == 4) {
			// alert(4);
			if (xmlHttp.status == 200) {
				// alert(5);
				var optionContent = xmlHttp.responseText;
				document.getElementById('samplePartNum').value = optionContent;
			}
		}
	}
</script>
</html:html>