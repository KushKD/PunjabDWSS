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



	<html:form action="paymentRequestAction" method="post"
		styleId="paymentRequestForm">
		<div id="modalContainer"></div>

		<div class="panel panel-danger">
			<div class="panel-body">
				<h4
					class="text-on-pannel text-primary col-lg-12 col-xs-12 col-sm-12 col-md-12 center-block text-center">
					Payment Request</h4>
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
						onchange="ajaxFunction('GetFilterValues.to?villages='+this.value+'&method=fetchGramPanchayats', 'gramPanchayatId');">
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
				<div class="col-lg-6 col-md-5 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<button type="button" class="btn btn-primary"
						onclick="de_find();dynamicTableData()">Search</button>
				</div>
			</div>
		</div>
		<c:if test="${not empty beneficiaryDto}">
			<div class="panel panel-danger" id="revalidate">
				<div class="panel-body">

					<div class="col-lg-9 col-md-2 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">
						<div class="form-group pull-right">
							<input type="text" class="search form-control"
								placeholder="What you looking for?" onkeyup='search()'
								id='myInput'>
						</div>
					</div>



					<div class=" form-inline col-lg-12 col-md-5 col-xs-12 col-sm-12">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-highlight"
								id="surveyTable" style='display: block;'>
								<thead style='display: block;' class='thead-dark'>
									<tr class="row">
										<th class='col-lg-1 col-xs-1'>Name</th>
										<th class='col-lg-2 col-xs-2' style='font-size: 0.6em'>Electric
											Connection No</th>
										<th class='col-lg-2 col-xs-2'>Aadhar Card Number</th>
										<th class='col-lg-2 col-xs-2' style='font-size: 0.6em'>Construction
											Status</th>
										<th class='col-lg-2 col-xs-1'>Amount</th>
										<th class='col-lg-2 col-xs-2'></th>
										<th class='col-lg-1 col-xs-1'><input type="checkbox"
											class="selectall" name="myListI"></th>
									</tr>
								</thead>


								<tbody id='fnlTable' style='display: block;'>
									<logic:iterate name="beneficiaryDto" id="beneficiaryLst"
										property="beneficiaryDto" indexId="rowindex"
										type="com.prwss.min.sanitation.bean.BeneficiaryDto">

										<tr class="row">
											<td class='col-lg-1 col-xs-1' style='font-size: 0.8em'><a
												href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1(<bean:write
												name="beneficiaryLst" property="beneficiaryId" />)'>
													<span style='color: #2196F3'> <bean:write
															name="beneficiaryLst" property="name" /></span>
											</a></td>
											<%-- 	<td class='col-lg-2 col-xs-2' style='font-size: 0.9em'><bean:write
													name="beneficiaryLst" property="fatherSpouseName" /></td> --%>
											<td class='col-lg-2 col-xs-2'
												style='font-size: 0.9em; text-align: center'><bean:write
													name="beneficiaryLst" property="electConnNumber" /></td>
											<td class='col-lg-2 col-xs-2'
												style='font-size: 0.9em; text-align: center'><bean:write
													name="beneficiaryLst" property="aadharNo" /></td>
											<td class='col-lg-2 col-xs-2'
												style='font-size: 0.9em; text-align: center'><bean:write
													name="beneficiaryLst" property="stageName" /></td>

											<td class='col-lg-2 col-xs-1'><bean:write
													name="beneficiaryLst" property="amount" /></td>
											<td class='col-lg-2 col-xs-1'><a
												href="javascript:progressStage.progressStageDetails(<bean:write
												name="beneficiaryLst" property="progressStageId" />)">
													<span style='color: #2196F3; font-size: 0.9em;'>View
														Status</span>
											</a></td>

											<html:hidden name="beneficiaryLst" property="beneficiaryId"
												indexed="true" />
											<html:hidden name="beneficiaryLst" property="progressStageId"
												indexed="true" />
											<td class='col-lg-1 col-xs-1'><html:checkbox
													name="beneficiaryLst" property="isSelected" indexed="true" /></td>
										</tr>

									</logic:iterate>
								</tbody>

							</table>
						</div>
					</div>
					<div class="col-lg-5 col-md-4 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class=" form-inline col-lg-5 col-md-5 col-xs-12 col-sm-6">
						<button type="button" class="btn btn-primary"
							onclick='showModal1()'>Create Bill</button>
					</div>
					<div class=" form-inline col-lg-2 col-md-3 col-xs-12 col-sm-6">
						<button type="button" class="btn btn-primary" onclick="de_add()">Save</button>
					</div>



				</div>
			</div>
		</c:if>

		<div id='modalPopup1s'>

			<div class="modal fade" id="myModal11" role="dialog">
				<div class="modal-dialog" style='width: 900px'>
					<div class="modal-content" style='width: 900px'>
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Validate Beneficiary</h4>
						</div>

						<div class="modal-body11">
							<c:if test="${not empty billNo}">
								<div class="panel panel-danger">
									<div class="panel-body">
										<div class='form-inline col-lg-8 col-md-8 col-xs-12 col-sm-12'>
											<label class='text-left labledesign'>Bill No.</label> <label
												class='text-right' style='width: 150px;'> ${billNo}
											</label>
										</div>
										<div class='form-inline col-lg-4 col-md-4 col-xs-12 col-sm-12'>
											<button type="button" class="btn btn-primary"
												onclick="de_forward()">Forward</button>
										</div>
							
							<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>
								<label class='text-left labledesign'>Total Amount</label> <label
									class='text-right' style='width: 150px;'> <c:if
										test="${not empty amount}">
												${amount}
											</c:if>
										</label>
							</div>
							<div>
								<div class='form-inline col-lg-6 col-md-6 col-xs-12 col-sm-12'>
									<label class='text-left labledesign'>Remarks</label>

									<html:textarea property="remarks" styleId="permissibleLimit"
										styleClass="form-control"></html:textarea>

								</div>

							</div>
							
						</div>
					</div>
					</c:if>
					<div class="panel panel-danger">
						<div class="panel-body">



							<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
								&nbsp;</div>
							<div class=" form-inline col-lg-10 col-md-5 col-xs-12 col-sm-12">

								<div class="table-responsive">
									<table
										class="table table-bordered table-striped table-highlight"
										id="surveyTable1" style='display: block;'>
										<thead style='display: block;' class='thead-dark'>
											<tr class="row">
												<th class='col-xs-2'>Name</th>
												<th class='col-xs-2'>Father/Husband Name</th>
												<th class='col-xs-2'>Electric Connection No</th>
												<th class='col-xs-2'>Aadhar Card Number</th>
												<th class='col-xs-2'>Construction Status</th>
												<th class='col-xs-2'>Amount</th>
											</tr>
										</thead>
										<c:if test="${not empty beneficiaryDtos}">
											<tbody id='fnlTable' style='display: block;'>
												<logic:iterate name="beneficiaryDtos" id="beneficiaryLsts"
													property="beneficiaryDtos" indexId="rowindex"
													type="com.prwss.min.sanitation.bean.BeneficiaryDto">

													<tr class="row">
														<td class='col-xs-2' style='font-size: 0.9em'><bean:write
																name="beneficiaryLsts" property="name" /></td>
														<td class='col-xs-2' style='font-size: 0.9em'><bean:write
																name="beneficiaryLsts" property="fatherSpouseName" /></td>
														<td class='col-xs-2' style='font-size: 0.9em'><bean:write
																name="beneficiaryLsts" property="electConnNumber" /></td>
														<td class='col-xs-2' style='font-size: 0.9em'><bean:write
																name="beneficiaryLsts" property="aadharNo" /></td>
														<td class='col-xs-2' style='font-size: 0.9em'><bean:write
																name="beneficiaryLsts" property="stageName" /></td>
														<td class='col-xs-2'><bean:write
																name="beneficiaryLsts" property="amount" /></td>
														<html:hidden name="beneficiaryLsts"
															property="beneficiaryId" indexed="true" />
														<html:hidden name="beneficiaryLsts"
															property="progressStageId" indexed="true" />
														<html:hidden name="beneficiaryLsts"
															property="paymetRequestId" indexed="true" />

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

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

					</div>
				</div>

			</div>
		</div>

		</div>
		</div>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog"
				style='width: 976px; overflow-y: initial !important'>

				<div class="modal-content" style='width: 976px;'>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Beneficiary Entry Details</h4>
					</div>
					<div class="modal-body" style='height: 600px; overflow-y: auto;'>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>
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
	</html:form>
</body>
</body>
<script type="text/javascript">

function showModal1() {
	$("#myModal11").modal('show');
}
	$("#scndContainer").hide();
	$(document)
			.ready(
					function() {
						/* ajaxFunction(
								'GetFilterValues.to?surveyId=surveyId&method=getSurvey',
								'surveyId'); */
						Req = "ent_frwrd";
						de_init('ent_frwrd', "test");

					});
</script>
<script type="text/javascript">
	$('.selectall').click(function() {
		if ($(this).is(':checked')) {
			$('div input').attr('checked', true);
		} else {
			$('div input').attr('checked', false);
		}
		$('th input[type="checkbox"]').click(function() {
			if ($(this).is(':checked'))
				$('td input[type="checkbox"]').prop('checked', true);
			else
				$('td input[type="checkbox"]').prop('checked', false);
		})
	});
</script>
<script type="text/javascript">


	function validateField() {

		var surveyId = $('#surveyId').val();
		var district = $('#district').val();
		var block = $('#block').val();
		var villageId = $('#villageId').val();
		if (surveyId === '' || surveyId === null) {
			alert("Please Select survey");
			return false;
		}
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

	function dynamicTableData() {
		$("#scndContainer").hide();
		$('#surveyTable1 > tbody  > tr').each(function() {
			$(this).closest('tr').remove();
		});
	}
	
	triggerEvent(document.getElementById('district'), 'onchange');
	<%if (MisUtility.ifEmpty(request.getAttribute("block"))) {%>
	document.getElementById("block").value="<%=request.getAttribute("block")%>";
<%}%>
	triggerEvent(document.getElementById('block'), 'onchange');
	<%if (request.getAttribute("village") != null) {%>
	document.getElementById("villageId").value="<%=request.getAttribute("village")%>";
<%}%>
	
</script>

</html:html>