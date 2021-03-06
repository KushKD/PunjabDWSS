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
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sanitation-workflow.js"></script>
<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);

	function de_find() {
		var status = validateFields();
		if (status) {
			document.reValidateBeneficiary.action = "reValidateBeneficiaryAction.do?method=find&d__mode="
					+ d__mode + "&menuId=SNT012";
			document.reValidateBeneficiary.submit();
		}
	}

	function de_add() {
		var result = validateField();
		if (result) {

			document.reValidateBeneficiary.action = "reValidateBeneficiaryAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT012";
			document.reValidateBeneficiary.submit();

		}
	}

	function de_forward() {

		document.reValidateBeneficiary.action = "reValidateBeneficiaryAction.do?method=forward&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.reValidateBeneficiary.submit();
	}
	function de_back() {
		document.reValidateBeneficiary.action = "reValidateBeneficiaryAction.do?method=saveReturn&d__mode="
				+ d__mode + "&menuId=SNT012";
		document.reValidateBeneficiary.submit();
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



	<html:form action="reValidateBeneficiaryAction" method="post"
		styleId="reValidateBeneficiary">


		<div class="panel panel-danger">
			<div class="panel-body">
				<h4
					class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Location
					Details</h4>
				<div class="line"></div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Select Survey</label>
					<html:select property="surveyId" styleId="surveyId"
						style="width: 150px;" styleClass="cs2 form-control">
						<html:option value="">Select Survey</html:option>
						<html:options collection="surveyId" labelProperty="label"
							property="value"></html:options>
					</html:select>
				</div>
				<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<button type="button" class="btn btn-primary"
						onclick="de_find();show()">Search</button>
				</div>
				<div class="col-lg-1 md-hidden sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
		</div>
		<c:if test="${not empty beneficiaryDto}">
			<div class="panel panel-danger" id="revalidate">
				<div class="panel-body">

					<div class="col-lg-9 col-md-2 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-3 col-md-3 sm-hidden xs-hidden">
						<div class="form-group pull-right">
							<input type="text" class="search form-control"
								placeholder="What you looking for?" onkeyup='search()'
								id='myInput'>
						</div>
					</div>
					<div class="col-lg-1 col-md-2 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>


					<div class=" form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-highlight"
								id="surveyTable" style='display: block;'>
								<thead style='display: block;' class='thead-dark'>
									<tr class="row">
										<th class='col-lg-1 col-xs-1' style='text-align:center'>Name</th>
										<th class='col-lg-2 col-xs-2' style='text-align:center'>Electric
											Connection No</th>
										<th class='col-lg-2 col-xs-2' style='text-align:center'>Aadhar Card
											Number</th>
										<th class='col-lg-2 col-xs-2' style='text-align:center'>Current Status</th>
										<th class='col-lg-2 col-xs-2' style='text-align:center'>Update status</th>
										<th class='col-lg-2 col-xs-2' style='text-align:center'>Remarks</th>
										<th class='col-lg-1 col-xs-1' style='text-align:center'><input
											type="checkbox" class="selectall" name="myListI"></th>
									</tr>
								</thead>


								<tbody id='fnlTable' style='display: block;'>
									<logic:iterate name="beneficiaryDto" id="beneficiaryLst"
										property="beneficiaryDto" indexId="rowindex"
										type="com.prwss.min.sanitation.bean.BeneficiaryDto">

										<tr class="row">
											<td class='col-lg-1 col-xs-1'
												style='font-size: 0.8em; width:8%'><a
												href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1(<bean:write
												name="beneficiaryLst" property="beneficiaryId" />)'>
													<span style='color: #2196F3'> <bean:write
															name="beneficiaryLst" property="name" /></span>
											</a></td>
											<td class='col-lg-2 col-xs-2'
												style='font-size: 0.8em; width: 18.499999995%'><bean:write
													name="beneficiaryLst" property="electConnNumber" /></td>
											<td class='col-lg-2 col-xs-2'
												style='font-size: 0.8em; width: 17%'><bean:write
													name="beneficiaryLst" property="aadharNo" /></td>
											<td class='col-lg-1 col-xs-2'
												style='font-size: 0.8em; width: 16.5%'><bean:write
													name="beneficiaryLst" property="action" /></td>
											<td class='col-lg-1 col-xs-2' style='width:16.4%'>
												<div class='form-inline'>
													<html:select name="beneficiaryLst" property="updateStatus"
														styleClass='form-control input-sm' indexed="true">
														<html:option value="">Select</html:option>
														<html:option value="Verify">Verify</html:option>
														<html:option value="RE">Remove</html:option>
													</html:select>
												</div> <html:hidden name="beneficiaryLst"
													property="validationRequestId" indexed="true" />
											<td class='col-lg-2 col-xs-2' style='width: 15.5%'>
												<div class='form-inline'>
													<html:text name="beneficiaryLst" property="remarks" style='width: 100%'
														styleClass='form-control input-sm'></html:text>
												</div> <html:hidden name="beneficiaryLst" property="beneficiaryId"
													indexed="true" />
											<td class='col-lg-1 col-xs-1' style='text-align:center'><html:checkbox
													name="beneficiaryLst" property="isSelected" indexed="true" />
											</td>
										</tr>

									</logic:iterate>
								</tbody>

							</table>
						</div>
					</div>
					<div class="col-lg-1 col-md-2 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-10 col-md-9 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class=" form-inline col-lg-2 col-md-3 col-xs-12 col-sm-6">
						<button type="button" class="btn btn-primary" onclick="de_add()">Save</button>
					</div>

					<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

					<div class=" form-inline col-lg-5 col-md-3 sm-hidden xs-hidden">
						<button type="button" class="btn btn-primary"
							onclick='showModal()'>Forward Beneficiary Request</button>
					</div>
					<div class="col-lg-3 col-md-4 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

				</div>
			</div>
		</c:if>
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



	</html:form>

	<div id='modalPopup1s'>

		<div class="modal fade" id="myModal11" role="dialog">
			<div class="modal-dialog" style='width: 1116px'>
				<div class="modal-content" style='width: 1116px'>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Validate Beneficiary</h4>
					</div>

					<div class="modal-body11">

						<div class="panel panel-danger">
							<div class="panel-body">
								<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
									&nbsp;</div>
								<div class=" form-inline col-lg-10 col-md-5 col-xs-12 col-sm-12">

									<div class="table-responsive">
										<table
											class="table table-bordered table-striped table-highlight"
											id="surveyTable" style='display: block;'>
											<thead style='display: block;' class='thead-dark'>
												<tr class="row">
													<th class='col-lg-2 col-xs-2' style='text-align:center'>Name</th>
													<th class='col-lg-2 col-xs-2' style='text-align:center'>Electric
														Connection No</th>
													<th class='col-lg-2 col-xs-2' style='text-align:center'>Aadhar Card
														Number</th>
													<th class='col-lg-3 col-xs-3' style='text-align:center'>Updated
														Status</th>
													<th class='col-lg-3 col-xs-3' style='text-align:center'>Remarks</th>
												</tr>
											</thead>

											<c:if test="${not empty beneficiaryDtos}">

												<tbody id='fnlTable' style='display: block;'>
													<logic:iterate name="beneficiaryDtos" id="beneficiaryLsts"
														property="beneficiaryDtos" indexId="rowindex"
														type="com.prwss.min.sanitation.bean.BeneficiaryDto">

														<tr class="row">
															<td class='col-lg-2 col-xs-2'
																style='font-size: 0.9em; width: 14.9%'><a
																href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1(<bean:write
												name="beneficiaryLsts" property="beneficiaryId" />)'>
																	<span style='color: #2196F3'> <bean:write
																			name="beneficiaryLsts" property="name" /></span>
															</a></td>
															
															<td class='col-lg-2 col-xs-2'
																style='font-size: 0.9em; width: 20.5%'><bean:write
																	name="beneficiaryLsts" property="electConnNumber" /></td>
															<td class='col-xs-2'
																style='font-size: 0.9em; width: 18.5%'><bean:write
																	name="beneficiaryLsts" property="aadharNo" /></td>
															<td class='col-lg-3 col-xs-2'
																style='font-size: 0.9em; width: 23.5%'><bean:write
																	name="beneficiaryLsts" property="action" /></td>
															<html:hidden name="beneficiaryLsts"
																property="validationRequestId" indexed="true" />
															<td class='col-lg-3 col-xs-2' style='width: 14.499999995%'>
																<div class='form-inline'>
																	<html:text name="beneficiaryLsts" property="remarks"
																		styleClass='form-control input-sm' readonly="true"></html:text>
																</div> <html:hidden name="beneficiaryLsts"
																	property="beneficiaryId" indexed="true" />
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
							<button type="button" class="btn btn-primary" onclick="de_back()">Return</button>
							<button type="button" class="btn btn-primary"
								onclick="de_forward()">Forward</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>

						</div>
					</div>

				</div>
			</div>

		</div>
	</div>

</body>
<script type="text/javascript">
	
	$(document).ready(function() {
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
	/* function show() {
		document.getElementById("revalidate").style.display = '';
	} */
	function validateFields() {
		var surveyId = document.getElementById('surveyId').value;
		if (surveyId === '') {
			alert('Please select Survey');
			return false;
		}
		return true;
	}
	function validateField() {
		if ($("#reValidateBeneficiary input[type='checkbox']:checked").length <= 0) {
			alert('please check checkbox')
			return false;
		}
		return true;
	}
	function showModal() {
		$("#myModal11").modal('show');
	}

	function search() {
		var searchText = document.getElementById('myInput').value;
		var targetTable = document.getElementById('surveyTable');
		var targetTableColCount;

		// Loop through table rows
		for (var rowIndex = 0; rowIndex < targetTable.rows.length; rowIndex++) {
			var rowData = '';

			// Get column count from header row
			if (rowIndex == 0) {
				targetTableColCount = targetTable.rows.item(rowIndex).cells.length;
				continue; // do not execute further code for header row.
			}

			// Process data rows. (rowIndex >= 1)
			for (var colIndex = 0; colIndex < targetTableColCount; colIndex++) {
				rowData += targetTable.rows.item(rowIndex).cells.item(colIndex).innerText;
			}

			// If search term is not found in row data
			// then hide the row, else show
			if (rowData.indexOf(searchText) == -1)
				targetTable.rows.item(rowIndex).style.display = 'none';
			else
				targetTable.rows.item(rowIndex).style.display = 'table-row';
		}
	}
</script>


</html:html>