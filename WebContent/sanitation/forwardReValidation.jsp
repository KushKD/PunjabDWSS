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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sanitation-workflow.js"></script>

<script type='text/javascript'>
	function de_forward() {

		document.forwardBeneficiaryForm.action = "forwardBeneficiaryValidationAction.do?method=save&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.forwardBeneficiaryForm.submit();
	}
	function de_back() {
		document.forwardBeneficiaryForm.action = "forwardBeneficiaryValidationAction.do?method=saveReturn&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.forwardBeneficiaryForm.submit();
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
	font-size: .7em;
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
	padding: 7px 9px;
	color: #E8EAF6;
	border-bottom: 2px dashed #283D4B;
}

.modal-title {
	text-align: center;
}

.close {
	color: #ffffff
}

.modal-content {
	margin: 5px 5px 5px 83px;
}
</style>

</head>


<html:html>
<html:form action="forwardBeneficiaryValidationAction" method="post"
	styleId="forwardBeneficiaryForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-10 col-md-5 col-xs-12 col-sm-12">

				<div class="table-responsive">
					<table class="table table-bordered table-striped table-highlight"
						id="surveyTable" style='display: block;'>
						<thead style='display: block;' class='thead-dark'>
							<tr class="row">
								<th class='col-xs-2' style='width: 9.899999995%'>Name</th>
								<th class='col-xs-2' style='width: 16%'>Father/Husband Name</th>
								<th class='col-xs-2' style='width: 14.3%'>Electric
									Connection No</th>
								<th class='col-xs-2' style='width: 14.5%'>Aadhar Card
									Number</th>
								<th class='col-xs-2' style='width: 14.8%'>Updated Status</th>
								<th class='col-xs-2' style='width: 20.8%'>Remarks</th>
							</tr>
						</thead>

						<c:if test="${not empty beneficiaryDtos}">

							<tbody id='fnlTable' style='display: block;'>
								<logic:iterate name="beneficiaryDtos" id="beneficiaryLsts"
									property="beneficiaryDtos" indexId="rowindex"
									type="com.prwss.min.sanitation.bean.BeneficiaryDto">

									<tr class="row">
										<td class='col-xs-2'
											style='font-size: 0.9em; width: 9.899999995%'><a
											href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1(<bean:write
												name="beneficiaryLsts" property="beneficiaryId" />)'>
												<span style='color: #2196F3'> <bean:write
														name="beneficiaryLsts" property="name" /></span>
										</a></td>
										<td class='col-xs-2' style='font-size: 0.9em; width: 15.9%'><bean:write
												name="beneficiaryLsts" property="fatherSpouseName" /></td>
										<td class='col-xs-2'
											style='font-size: 0.9em; width: 14.499999995%'><bean:write
												name="beneficiaryLsts" property="electConnNumber" /></td>
										<td class='col-xs-2'
											style='font-size: 0.9em; width: 14.499999995%'><bean:write
												name="beneficiaryLsts" property="aadharNo" /></td>
										<td class='col-xs-2'
											style='font-size: 0.9em; width: 14.499999995%'><bean:write
												name="beneficiaryLsts" property="action" /></td>
										<html:hidden name="beneficiaryLsts"
											property="validationRequestId" indexed="true" />
										<td class='col-xs-2' style='width: 14.499999995%'>
											<div class='form-inline'>
												<html:text name="beneficiaryLsts" property="remarks"
													styleClass='form-control input-sm' readonly="true"></html:text>
											</div> <html:hidden name="beneficiaryLsts" property="beneficiaryId"
												indexed="true" />
									</tr>

								</logic:iterate>
							</tbody>
						</c:if>
					</table>
				</div>
			</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

		</div>
	</div>
</html:form>

</body>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>





</html:html>