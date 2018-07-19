<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
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
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/sanitation-workflow.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);

	function de_find() {
		var status = validateField();
		if (status) {
			document.paymentRequestForm.action = "paymentRequestAction.do?method=find&d__mode="
					+ d__mode + "&menuId=SNT012";
			document.paymentRequestForm.submit();
		}
	}
	function de_add() {
		document.paymentRequestForm.action = "paymentRequestAction.do?method=save&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.paymentRequestForm.submit();
	}
	function de_forward() {

		document.paymentRequestForm.action = "paymentRequestAction.do?method=forward&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.paymentRequestForm.submit();
	}
	function de_back() {
		document.paymentRequestForm.action = "paymentRequestAction.do?method=saveReturn&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.paymentRequestForm.submit();
	}
</script>
<style type="text/css">
body {
	background-color: #bdc3c7;
}

tbody {
	max-height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
	overflow-x: hidden; /* Hide the horizontal scroll */
}

.table>thead>tr>th, .table>thead>tr>td {
	font-size: .6em;
	border-bottom: 0;
	letter-spacing: 1px;
	vertical-align: center;
	padding: 2px;
	background: #000000;
	text-transform: uppercase;
	color: #ffffff;
}

.modal-header {
	background-color: #283D4B;
	padding: 6px 6px;
	color: #E8EAF6;
	border-bottom: 2px dashed #283D4B;
}

.modal-title {
	text-align: center;
}

.close {
	color: #ffffff
}

#myInput {
	background-image: url('/PRWSS_MIS/css/searchicon.png');
	background-position: 9px 6px;
	background-repeat: no-repeat;
	width: 100%;
	font-size: 16px;
	padding: 5px 5px 5px 40px;
	border: 1px solid #ddd;
	margin-bottom: 12px;
}
</style>
<script type="text/javascript">
	function returnToBeneficiarys(){
			var validationRequestId;
			var row2 = [];
			$('#verifyPaymentEntry > tbody  > tr').each(
					function() {
						var row = $(this).html();
						row2.push($(this).closest('tr').find('td:nth-child(8)')
								.text());
						validationRequestId = row2.join();
					});
			document.getElementById('validationRequestId').value = validationRequestId;

			document.approvePaymentForm.action = "approvePaymentAction.do?method=back&d__mode="
					+ d__mode + "&menuId=SNT012&request="+validationRequestId;
			document.approvePaymentForm.submit();
		}
	
	
	var forwrdBeneficiary={
			approveBeneficiarys:function(){
				var beneficiaryId;
				var row2 = [];
				$('#verifyPaymentEntry > tbody  > tr').each(
						function() {
							var row = $(this).html();
						
							row2.push($(this).closest('tr').find(
									'td:nth-child(8)').text());
							beneficiaryId = row2.join();
						});
				document.getElementById('validationRequestId').value = validationRequestId;

				document.approvePaymentForm.action = "approvePaymentAction.do?method=forward&d__mode="
						+ d__mode + "&menuId=SNT012&request="+beneficiaryId;
				document.approvePaymentForm.submit();
				
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



	<html:form action="approvePaymentAction" method="post"
		styleId="approvePaymentForm">
		<div id="modalContainer"></div>
		<div id="container"></div>
		
		<div class="modal fade" id="myModal1" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Progress Stage</h4>
					</div>
					<div class="modal-body1">
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>
		
		<div id="approverContainer"></div>
		<html:hidden property="validationRequestId"
			styleId='validationRequestId' />
	</html:form>
</body>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		paymentReviewerDetail.getReviewPaymentDetails();
	});
</script>

</html:html>