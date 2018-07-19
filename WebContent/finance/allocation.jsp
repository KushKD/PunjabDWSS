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
<script type="text/javascript" src="js/finance.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>

<script type="text/javascript">
	function de_add() {
		var result = validateForm();
		if (result) {
			document.allocationForm.action = "allocationAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN013";
			document.allocationForm.submit();
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

<html:form action="allocationAction" method="post"
	styleId="allocationForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				General Details</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-10 col-md-12 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Request.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='requestNo' styleId='requestNo'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="getFundRequestDetails(this);
					enable_disable(this);
					"></html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div id='firstSec1'>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="Division" /> &nbsp;&nbsp;&nbsp;&nbsp; </label> <span
						id='division' style="width: 150px;"></span>
				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="DDO.Number" /> <span class="text-danger">
							&nbsp;</span> </label> <span id='ddoNumber'></span>
				</div>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div id='firstSec2'>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="Scheme.Code" /> </label> <span id='schemeCode'></span>

				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="Scheme.Name" /> </label> <span id='schemeName'></span>
				</div>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Head" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="head" styleId="head" style="width: 150px;"
					styleClass="form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Allocation.Number" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="allocationNumber" styleId="allocationNumber"
					style="width: 150px;" styleClass="form-control" >
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
						bundle="finance" key="Date.Allocation" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="dateAllocation" styleId="dateAllocation"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"
				id="components">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Component" /> </label> <span id='component'></span>
			</div>
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">
			
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div id="secSection">
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="Installment" /><span class="text-danger">
					</span> </label> <span id='installment'></span>
				</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="finance" key="Amount.Requested" /> </label> <span
						id='amountRequested' style="width: 150px;"></span>
				</div>

			

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Amount.Released" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="amountReleased" styleId="amountReleased"
					style="width: 150px;" styleClass="form-control"
					onkeypress="return validateKey1(event,	this,'9@20@3')">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Revoked" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="revoked" styleId="revoked"
					style="width: 150px;" styleClass="form-control"
					onkeypress="return validateKey1(event,	this,'9@20@3')"
					onblur="netAmountFirstSec(document.getElementById('amountReleased').value,this)">
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Net.Amount" /><span class="text-danger">
						&nbsp;*</span> </label> <label id='netAmount'></label>
						
						<html:hidden property="netAmount" styleId="netAmounts"/>
			</div>


		</div>
	</div>
	<%-- 
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				if Comp 4</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Activity" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="activity" styleId="activity"
					style="width: 150px;" styleClass="form-control">
				</html:select>
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
						bundle="finance" key="Total" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="total" styleId="total" style="width: 150px;"
					styleClass="form-control">
				</html:text>
			</div>



		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				if Support</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Amount.Requested" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="amountRequested" styleId="amountRequested"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Amount.Released" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="amountReleased" styleId="amountReleased"
					style="width: 150px;" styleClass="form-control">
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
						bundle="finance" key="Revoked" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="revoked" styleId="revoked"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Net.Amount" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="netAmount" styleId="netAmount"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
		</div>
	</div>




	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				if NRDWP,H2S Kit,Lab Contractor</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="NRDWP.Normal" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="nrdwpNormal" styleId="nrdwpNormal"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="NRDWP.Sustain" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="nrdwpSustain" styleId="nrdwpSustain"
					style="width: 150px;" styleClass="form-control">
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
						bundle="finance" key="NRDWP.Support" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="nrdwpSupport" styleId="nrdwpSupport"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="NRDWP" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="nrdwpOAndM" styleId="nrdwpOAndM"
					style="width: 150px;" styleClass="form-control">
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
				&nbsp;</div><div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="NRDWP.WQMSP" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="nrdwpWQMSP" styleId="nrdwpWQMSP"
					style="width: 150px;" styleClass="form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Revoke" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="revoke" styleId="revoke" style="width: 150px;"
					styleClass="form-control">
				</html:text>
			</div>
		</div>
	</div>
 --%>

	<!-- Table -->
</html:form>

</body>
<script type='text/javascript'>

function validateForm(){
	var requestNo=document.getElementById('requestNo').value;
	var head=document.getElementById('head').value;
	var allocationNumber=document.getElementById('allocationNumber').value;
	var dateAllocation=document.getElementById('dateAllocation').value;
	var amountReleased=document.getElementById('amountReleased').value;
	var revoked=document.getElementById('revoked').value;
	
	if(requestNo===''){
		alert("Request No could not be left blank");
		return false;
	}if(head===''){
		alert("Head could not be left blank");
		return false;
	}if(allocationNumber===''){
		alert("Allocation Number could not be left blank");
		return false;
	}if(dateAllocation===''){
		alert("Allocation Date could not be left blank");
		return false;
	}if(amountReleased===''){
		alert("Released Amount could not be left blank");
		return false;
	}if(revoked===''){
		alert("Revoked Amount could not be left blank");
		return false;
	}
	
	return true;
}

	hide_ctrl('firstSec1~firstSec2~components~secSection', true);

	$(document)
			.ready(
					function() {
						ajaxFunction(
								'allocationAction.do?request=test&method=fetchRequestNo',
								'requestNo');

						ajaxFunction(
								'allocationAction.do?request=test&method=fetchHeads',
								'head');
					});
	$('#dateAllocation,#defaultInline').datepick();

	function getFundRequestDetails(e) {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"allocationAction.do?method=fetchFundRequestDetails&request="
						+ e.value, false);
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				var d__lst = optionContent.split("~");
				document.getElementById('division').innerHTML = d__lst[0];
				document.getElementById('schemeCode').innerHTML = d__lst[1];
				document.getElementById('schemeName').innerHTML = d__lst[2];
				document.getElementById('component').innerHTML = d__lst[3];
				document.getElementById('ddoNumber').innerHTML = d__lst[3];

				populateFirstSection(e.value);

			}
		}
	}

	function populateFirstSection(requestNo) {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"allocationAction.do?method=fetchInstallmentAmountDetails&requestNo="
						+ requestNo, false);
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				var d__lst = optionContent.split("~");
				document.getElementById('installment').innerHTML = d__lst[1];
				document.getElementById('amountRequested').innerHTML = d__lst[0];
			}
		}
	}

	function netAmountFirstSec(releasedAmount, revoked) {
		var releasedAmount = parseInt(releasedAmount);
		var revoked = parseInt(revoked.value);

		var netAmount = releasedAmount - revoked;
	if(!(isNaN(netAmount))){
		document.getElementById('netAmount').innerHTML = netAmount;
		
		document.getElementById('netAmounts').value=netAmount;
		
	}

	}

	function enable_disable(e) {
		if (e.value == '') {
			hide_ctrl('firstSec1~firstSec2~components~secSection', true);
		} else {
			hide_ctrl('firstSec1~firstSec2~components~secSection', false);
		}
	}
</script>

</html:html>