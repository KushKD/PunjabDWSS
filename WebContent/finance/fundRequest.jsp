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


<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/finance.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>




<script type="text/javascript">
	function de_add() {
		var result = true;
		if (true) {
			document.fundRequestForm.action = "fundRequestAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN012";
			document.fundRequestForm.submit();
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

<html:form action="fundRequestAction" method="post"
	styleId="fundRequestForm" enctype="multipart/form-data">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				General Details</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Installment" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='installment' styleId='installment'
					styleClass="form-control ci5" style="width: 150px;"></html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Division" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?divisionIdss='+this.value+'&method=fetchDistrictByDivision', 'district');">
					<html:option value="">Select Location</html:option>
					<html:options collection="userLocations" labelProperty="label"
						property="value"></html:options>
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
						bundle="finance" key="District" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');ajaxFunction('fundRequestAction.do?districtId='+this.value+'&method=fetchGPWSC', 'gpwsc');">
				</html:select>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Block" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="block" styleId="block" style="width: 150px;"
					styleClass="form-control"
					onchange="ajaxFunction('GetFilterValues.to?subdivision1='+this.value+'&method=fetchScheme', 'schemeName');">
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
						bundle="finance" key="GPWSC" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="gpwsc" styleId="gpwsc" style="width: 150px;"
					styleClass="form-control" onchange="getRequestNo()">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Request.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="requestNo" styleId="requestNo"
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
						bundle="finance" key="Scheme.Type" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="schemeType" styleId="schemeType"
					style="width: 150px;" styleClass="form-control">
					<html:option value="2">2</html:option>
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Scheme.Name" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="schemeName" styleId="schemeName"
					style="width: 150px;" styleClass="form-control"
					onchange="getSchemeDetailsUpgrade(this);getContractManagement(this)">
				</html:select>
			</div>
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Administrative Approval</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Approval.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="approvalNo" styleId="approvalNo"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Value" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="value" styleId="value" style="width: 150px;"
					styleClass="form-control">
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
						bundle="finance" key="Date" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="date" styleId="date" style="width: 150px;"
					styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="MIS.Code" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="misCode" styleId="misCode"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Technical Sanction</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Approval.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="sanctionApprovalNo"
					styleId="sanctionApprovalNo" style="width: 150px;"
					styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Value" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="sanctionValue" styleId="sanctionValue"
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
						bundle="finance" key="Date" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="sanctionDate" styleId="sanctionDate"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				World Bank Approval</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Date" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="worldBankDate" styleId="worldBankDate"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Approval.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="worldBankApprovalNo"
					styleId="worldBankApprovalNo" style="width: 150px;"
					styleClass="form-control">
				</html:text>
			</div>
		</div>
	</div>




	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Beneficiary Share</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Due" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="due" styleId="due" style="width: 150px;"
					styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Actually.Collected" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="actuallyCollected" styleId="actuallyCollected"
					style="width: 150px;" styleClass="form-control"
					onblur='getNetDSR()'>
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Net.DSR" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="netDSR" styleId="netDSR" style="width: 150px;"
					styleClass="form-control" onblur="getIstInstallmentValue()">
				</html:text>
			</div>

		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Procurement Package</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Date" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="procDate" styleId="procDate"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Number" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="procNumber" styleId="procNumber"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Contract Award</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Award.Date" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="awardDate" styleId="awardDate"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Award.Number" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="awardNumber" styleId="awardNumber"
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
						bundle="finance" key="Value" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="awardValue" styleId="awardValue"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
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
						bundle="finance" key="Value.of.1st.Installment" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="valueInstallment" styleId="valueInstallment"
					style="width: 150px;" styleClass="form-control"  readonly="true">
				</html:text>
			</div>

		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Contract Award</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-6 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style="width: 250px;"><bean:message
						bundle="finance" key="Scheme.Package.entered" /><span
					class="text-danger"> &nbsp;*</span> </label>

				<html:select property="schemePackage" styleId="schemePackage"
					style="width: 150px;" styleClass="form-control">
				</html:select>
			</div>
			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Package.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="packageNo" styleId="packageNo"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
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
				&nbsp;</div><div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div><div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div><div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="Attachments" /><span class="text-danger">
							&nbsp;*</span> </label>
					<html:file property="attachment" styleId="attachment"
						style="width: 150px;" styleClass="form-control">
					</html:file>
				</div>
				
		
		</div>
		</div>
		<!-- Table -->
</html:form>

</body>
<script type='text/javascript'>
	$('#procDate,#defaultInline').datepick();


	$(document).ready(
			function() {
				ajaxFunction(
						'GetFilterValues.to?parentComboId=205&method=getCombo',
						'installment');

				ajaxFunction(
						'GetFilterValues.to?parentComboId=115&method=getCombo',
						'schemeType');
				ajaxFunction(
						'GetFilterValues.to?parentComboId=5&method=getCombo',
						'schemePackage');
			});

	function getIstInstallmentValue(){
		var dsr=parseInt(document.getElementById('netDSR').value);
		alert(dsr)
		var insValue;
		if(dsr<100000){
			 insValue=(75/100)*dsr;
		}else if(dsr>1000000&&dsr<10000000){
			insValue=(60/100)*dsr;
		}else if(dsr>10000000){
			insValue=(30/100)*dsr;
		}
		alert(insValue);
		document.getElementById('valueInstallment').value=insValue;
	}
	function getNetDSR() {

		var value = parseInt(document.getElementById('sanctionValue').value);
		var actuallyCollected = parseInt(document
				.getElementById('actuallyCollected').value);
		var netDSR = value - actuallyCollected;

		document.getElementById('netDSR').value = netDSR;

	}
	function getRequestNo() {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST", "fundRequestAction.do?method=fetchRequestNo",
				false);
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				document.getElementById('requestNo').value = optionContent;
			}
		}
	}

	function getSchemeDetailsUpgrade(e) {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"fundRequestAction.do?method=fetchSchemeDetailsUpgrade&schemeId="
						+ e.value, false);
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;

				if(optionContent!=''){
				var d__lst = optionContent.split("~");
				document.getElementById('approvalNo').value = d__lst[0];
				document.getElementById('value').value = d__lst[1];
				document.getElementById('date').value = d__lst[2];
				document.getElementById('misCode').value = d__lst[3];

				document.getElementById('sanctionApprovalNo').value = d__lst[4];
				document.getElementById('sanctionValue').value = d__lst[5];
				document.getElementById('sanctionDate').value = d__lst[6];

				document.getElementById('worldBankDate').value = d__lst[7];
				document.getElementById('worldBankApprovalNo').value = d__lst[8];
				}
			}
		}
	}

	function getContractManagement(e) {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"fundRequestAction.do?method=fetchContractAwardDetails&schemeId="
						+ e.value, false);
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				if(optionContent!=''){
				var d__lst = optionContent.split("~");
				document.getElementById('awardDate').value = d__lst[0];
				document.getElementById('awardNumber').value = d__lst[1];
				document.getElementById('awardValue').value = d__lst[2];
				}

			}
			//document.getElementById('requestNo').value = optionContent;
		}
	}
</script>

</html:html>