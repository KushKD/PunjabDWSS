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
		var result = validateFields();
		if (result) {
			document.gPWSCRegisterForm.action = "gpwscRegisterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN010";
			document.gPWSCRegisterForm.submit();
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

<html:form action="gpwscRegisterAction" method="post"
	styleId="gPWSCRegisterForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				GPWSC Register</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Division" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?subdivision='+this.value+'&method=fetchSubDivision', 'subDivision')">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="sub.division" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="subDivision" styleId="subDivision"
					style="width: 150px;" styleClass="cs2 form-control" onchange="ajaxFunction('GetFilterValues.to?subDivision='+this.value+'&method=fetchVillageBySubDivisionId', 'village');">
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
						bundle="finance" key="Village" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="village" styleId="village"
					style="width: 150px;" styleClass="cs2 form-control" onchange="ajaxFunction('GetFilterValues.to?village='+this.value+'&method=fetchSchemeByVillage', 'schemeName');ajaxFunction('gpwscRegisterAction.do?villageId='+this.value+'&method=fetchGPWSC', 'gPWSC');">
				</html:select>

			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="GPWSC" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="gPWSC" styleId="gPWSC" style="width: 150px;"
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
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Scheme.Name" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="schemeName" styleId="schemeName"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>

			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Transaction.Date" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="transactionDate" styleId="transactionDate"
					style="width: 160px;" styleClass="form-control"></html:text>
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
						bundle="finance" key="Transaction.Type" /> <span
					class="text-danger">*</span> </label>
				<html:select property="transactionType" styleId="transactionType"
					style="width: 150px;" styleClass="cs2 form-control" onchange="showDiv(this);">
					
					<html:option value="1">1</html:option>
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Transaction.No" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="transactionNo" styleId="transactionNo"
					style="width: 150px;" styleClass="cs2 form-control" readonly='true'>
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
						bundle="finance" key="Bank" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="bank" styleId="bankName" style="width: 150px;"
					styleClass="cs2 form-control">
				</html:select>
			</div>
		</div>
	</div>
<div id='payment'>
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Payment</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Payee.Name" />  </label>
				<html:text property="payeeName" styleId="payeeName"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Bill.No" /> </label>
				<html:text property="billNo" styleId="billNo" style="width: 150px;"
					styleClass="cs2 form-control">
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
						bundle="finance" key="Payment.Type" />  </label>
				<html:select property="paymentType" styleId="paymentType"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Amount" />  </label>
				<html:text property="amount" styleId="amount" style="width: 150px;"
					styleClass="cs2 form-control" onkeypress="return validateKey1(event,	this,'9@10@3')">
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
						bundle="finance" key="Payment.Remark" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="paymentRemark" styleId="paymentRemark"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
		</div>
	</div>
</div>

<div id='receipt'>
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Receipt</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Receipt.Type" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="receiptType" styleId="receiptType"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Amount" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="receiptAmount" styleId="receiptAmount"
					style="width: 150px;" styleClass="cs2 form-control" onkeypress="return validateKey1(event,	this,'9@10@3')">
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
						bundle="finance" key="Receipt.Remark" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="receiptRemark" styleId="receiptRemark"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
		</div>
	</div>
</div>
	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="gpwscRegisterDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
							<th>Transaction Number</th>
							<th>Transaction Type</th>
							<th>Transaction Date</th>
							<th>Amount</th>
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
	$(document).ready(
			function() {

				ajaxFunction(
						'GetFilterValues.to?parentComboId=196&method=getCombo',
						'transactionType');
				ajaxFunction(
						'GetFilterValues.to?bankName=bankName&method=getCombo',
						'bankName');
				hide_ctrl('payment~receipt',true);
				
				document.getElementById("datatable_sh").style.display = '';
				gpwscRegister.gpwscRegisterFun();
					
				
			});
</script>
<script type='text/javascript'>
$('#transactionDate,#defaultInline').datepick();

function showDiv(e){
	if(e.value==='197'){
		ajaxFunction(
				'GetFilterValues.to?parentComboId=199&method=getCombo',
				'paymentType');
		hide_ctrl('payment',false);
		hide_ctrl('receipt',true);
	}else if(e.value==='198'){
		ajaxFunction(
				'GetFilterValues.to?parentComboId=199&method=getCombo',
				'receiptType');
		hide_ctrl('receipt',false);
		hide_ctrl('payment',true);
	}
	var transactionType = $("#transactionType option:selected").text();
	
	var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST",
				"gpwscRegisterAction.do?method=fetchTransactionNumber&transactionType="+transactionType,
				false);
		xmlHttp.send(null);
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				document.getElementById('transactionNo').value = optionContent;
			}
		}
}


function validateFields() {
	var division = document.getElementById('division').value;
	var subDivision = document.getElementById('subDivision').value;
	var village = document.getElementById('village').value;
	var gPWSC = document.getElementById('gPWSC').value;
	var schemeName = document.getElementById('schemeName').value;
	var transactionDate = document.getElementById('transactionDate').value;
	var transactionType = document.getElementById('transactionType').value;
/* 	var bankName = document.getElementById('bankName').value;
	var payeeName = document.getElementById('payeeName').value;
	var billNo = document.getElementById('billNo').value;
	var paymentType = document.getElementById('paymentType').value;
	
	var amount = document.getElementById('amount').value;
	var paymentRemark = document.getElementById('paymentRemark').value;
	 */
	
	
	if(division===''){
		alert('Please select Division');
		return false;
	}if(subDivision===''){
		alert('Please select Sub Division');
		return false;
	}if(village===''){
		alert('Please select Village');
		return false;
	}
	if(gPWSC===''){
		alert('Please select GPWSC');
		return false;
	}
	if(schemeName===''){
		alert('Please select Scheme Name');
		return false;
	}
	if(transactionType===''){
		alert('Please select Transaction Type');
		return false;
	}
	
	if(transactionType===''){
		alert('Please select GPWSC');
		return false;
	}
	
	
	
	
/* 	
	if(centerShare===''){
		alert('Center share field could not be left blank');
		return false;
	}if(stateShare===''){
		alert('State Share field could not be left blank');
		return false;
	} */
	return true;
}
</script>
</html:html>