var findBeneficiary = {
	findBeneficiarys : function() {
		var baseLineForm = $("#validateBeneficiaryForm");
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='col-lg-9 col-md-9 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='col-lg-3 col-md-3 sm-hidden xs-hidden'>";
		html += "<div class='form-inline pull-right'> <input type='text' class='search form-control' id='myInput' onkeyup='search()' placeholder='What you looking for?'></div></div>";
		html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Name </th>";
		/*
		 * html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Father/Husband
		 * Name</th>";
		 */
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Aadhar Card Number</th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Electric Connection No</th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Status</th>";
		html += "<th class='col-lg-3 col-xs-3' style='text-align:center'>Remarks</th>";
		html += "<th class='col-lg-1 col-xs-1' style='text-align:center'><input type='checkbox' class='checkbox' id='select-all' name='select-all' onclick='handleCheckbox(this)'></th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : "POST",
					url : "validateBeneficiaryAction.do?method=find&d__mode="
							+ d__mode + "&menuId=SNT011",
					data : baseLineForm.serialize(),
					success : function(data) {
						var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:16%'>";
							html += " <a  data-toggle='modal' href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1("
									+ parsed[i].beneficiaryId
									+ ")'><span> "
									+ parsed[i].name + " </span></a>";
							/*
							 * html += "<td class='col-lg-2 col-xs-2' style='text-align:center'>";
							 * html += " <span>" + parsed[i].fatherSpouseName + "</span> ";
							 */
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:17%' >";
							html += " <span>" + parsed[i].aadharNo + "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:19%'>";
							html += " <span>" + parsed[i].electConnNumber
									+ "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:15%'>";
							html += "<select class='form-control'  id='status"
									+ i + "'>";
							html += "<option value=''>Please select</option>";
							html += "<option value='APB'>As per Baseline</option>";
							html += "<option value='RE'>Remove</option>";
							html += "</select>";
							html += "<td class='col-lg-3 col-xs-3' style='text-align:center'>";
							html += "<div class='form-inline'>";
							html += "<input type='text' class='form-control input-sm' name='remarks'  style='width:90%;' id='remarks"
									+ i + "'>";
							html += "<td style='display:none;'><span>"
									+ parsed[i].beneficiaryId + "</span></td>";
							html += "<td class='col-lg-1 col-xs-1' style='text-align:center'>";
							html += "<input type='checkbox' id='" + i
									+ "' name='select'>";
							html += "</tr>";
						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='col-lg-10 col-md-10 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='if(validateStatus()){addDataTScndTable.addData()}'>Add</button></div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-2 col-md-3 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='addNewBeneficiary.addNewBeneficiarys();'>ADD New Beneficiary </button></div>";
						html += "<div class='col-lg-2 col-md-3 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='validateBeneficiary.validateBeneficiarys();'>Validate Beneficiary </button></div>";
						html += "<div class='col-lg-7 col-md-5 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "</div>";
						html += "</div>";

						document.getElementById('container').innerHTML = html;
						$("#container").show();
					}
				});

	}
}

function validateStatus() {
	var status1 = true;
	$('#surveyTable input[type=checkbox]:checked').each(function() {

		var row = $(this).parent().parent();

		var i = $(this).attr("id");
		var status = document.getElementById('status' + i).value;
		if (status === '') {
			alert('please select status');
			status1 = false;
			return false;
		}

	});
	return status1;
}
var beneficiaryEntryDetails1 = {
	beneficiaryEntryDetail1 : function(data) {

		$
				.ajax({
					type : "POST",
					url : "benifecieryEntryViewAction.do?method=getBeneficiaryDetails&d__mode="
							+ d__mode + "&menuId=SNT004",
					data : {
						beneficiaryId : data
					},
					success : function(data) {
						var parsed = JSON.parse(data);
						displayBeneficiaryDetails(parsed);
						$("#myModal").modal('show');
						// event.preventDefault();
					},
					error : function(error) {

					}
				});

	}
}

function displayBeneficiaryDetails(data) {
	var ctx = window.location.origin;
	var html = "";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Person Details</h4>";
	html += "<div>";
	html += "<div class='row col-lg-8'>";
	html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
	html += "<label class='text-left labledesign'>Name</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.casteName
			+ "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
	html += "<label class='text-left labledesign'>Gender</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.gender
			+ "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
	html += "<label class='text-left labledesign'>B. Category</label>";
	html += "<label	class='text-right' style='width: 150px;'>" + data.category
			+ "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
	html += "<label class='text-left labledesign'>Religion</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.religion
			+ "</label>";
	html += "</div>";
	html += "</div>";
	html += "<div class='row col-lg-4'>";
	html += "<img alt='Image' align='right' width='80%' height='28%'	src='data:image/jpeg;base64,"
			+ data.image + "'>";
	html += "</div>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Father/Spouse Name</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.fatHusName + "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Phone Number</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.phoneNo
			+ "</label>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Cast</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.fatHusName + "</label>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Location Details</h4>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>DISTRICT</label> ";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.districtName + "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Block</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.blockName
			+ "</label>";
	html += "</div>";
	html += "</div>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Gram Panchayat</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.gramPanchayatId + "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Village</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.villageName + "</label>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Identity Details</h4>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>POI Type</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.poiType
			+ "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>POI Number</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.poiId
			+ "</label>";
	html += "</div>";
	html += "</div>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Aadhar Card Number</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.aadhaarNumber + "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Electricity Connection Act. No.</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.electConnNumber + "</label>";
	html += "</div>";
	html += "</div>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Electricity Bill </label> ";
	html += "<label class='text-right' style='width: 250px; height: 50px;'>";
	html += "<a href='"
			+ ctx
			+ "/PRWSS_MIS/benifecieryEntryViewAction.do?method=downloadElectricityBill&beneficiaryId="
			+ data.beneficiaryId
			+ " ' target='_blank' onclick='javascript: setTimeout(window.close, 2);'>"
			+ data.electBill + "</a></label>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Bank Details</h4>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Name of Bank</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.bankName
			+ "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Branch</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.branchName + "</label>";
	html += "</div>";
	html += "</div>";
	html += "<div class='row col-lg-12'>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>Account Number</label>";
	html += "<label class='text-right' style='width: 150px;'>"
			+ data.accountNumber + "</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-6 col-sm-6'>";
	html += "<label class='text-left labledesign'>IFSC Code</label>";
	html += "<label class='text-right' style='width: 150px;'>" + data.ifscCode
			+ "</label>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	html += "<div class='modal-footer'>";
	html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
	html += "</div>";

	document.getElementsByClassName("modal-body")[0].innerHTML = html;

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

var addDataTScndTable = {
	addData : function() {
		var beneficiarys = [];
		var row1 = [];
		var beneficiaryId;
		var row2 = [];
		$('#surveyTable input[type=checkbox]:checked')
				.each(
						function() {

							var row = $(this).parent().parent();

							var i = $(this).attr("id");
							var ss = $(row).closest('tr').find('th');
							if (ss.length < 1) {
								row2.push(row);
								$(row).closest('tr').find('td:nth-child(7)')
										.remove();
								$(row)
										.closest('tr')
										.find('td:nth-child(6)')
										.after(
												"<td class='col-lg-1 col-xs-1'> <button title='Remove' type='button'  class='btn btn-danger remove show_tip' id='removeRow' data-original-title='Remove from list' onclick='addRwTFrstTable.addRwTFrstTables()()'><i class='fa fa-trash-o'></i></button></td>");
								var status = document.getElementById('status'
										+ i).value;
								if (status === 'RE') {
									$(row).closest('tr')
											.find('td:nth-child(4)').remove();
									$(row)
											.closest('tr')
											.find('td:nth-child(3)')
											.after(
													"<td class='col-lg-2 col-xs-2' style='text-align:center;width:15%'><span>Remove</span></td>")
								} else if (status === 'APB') {
									$(row).closest('tr')
											.find('td:nth-child(4)').remove();
									$(row)
											.closest('tr')
											.find('td:nth-child(3)')
											.after(
													"<td class='col-lg-2 col-xs-2' style='text-align:center;width:15%'><span>As per Baseline</span></td>")
								} else {
									$(row).closest('tr')
											.find('td:nth-child(4)').remove();
									$(row)
											.closest('tr')
											.find('td:nth-child(3)')
											.after(
													"<td class='col-lg-2 col-xs-2' style='text-align:center;width:15%'></td>")
								}
								var remarks = $("#remarks" + i).val();
								if (remarks != '') {
									$(row).closest('tr')
											.find('td:nth-child(5)').remove();
									$(row)
											.closest('tr')
											.find('td:nth-child(4)')
											.after(
													"<td class='col-lg-3 col-xs-3' style='text-align:center'><input type='text' class='form-control input-sm' name='remarks' style='width:90%;' value='"
															+ remarks
															+ "' id='remarks"
															+ i + "'></td>");
								}
								row1.push($(row).html());
								beneficiarys.push($(row).closest('tr').find(
										'td:nth-child(6)').text());
								beneficiaryId = beneficiarys.join();
							}
						});
		addToScndTable(row1, row2);
		showHideFrstTable1(row1);
	}
}

function addToScndTable(row1, row2) {
	if (row1.length != 0) {
		createTable(row1);
		for ( var i in row2) {
			$(row2[i]).remove();
		}
	}

}

function showHideFrstTable1(row1) {

	if ($("#surveyTable td").closest("tr").length === 0) {
		$("#container").hide();
	}
}

function createTable(data) {
	$("#scndContainer").show();
	var html = "";
	for ( var i in data) {
		html += "<tr class='row'>";
		html += data[i];
		html += "</tr>";
	}
	$('#surveyTable1 > tbody:last-child').append(html);
}
var addRwTFrstTable = {
	addRwTFrstTables : function() {
		$("#container").show();
		var beneficiarys = [];
		var row1 = [];
		var beneficiaryId1;
		var row2;
		$('#surveyTable1 tbody')
				.on(
						'click',
						'#removeRow',
						function() {

							var row = $(this).parent().parent();
							row2 = row;
							var lngth = $('#surveyTable tr').length;
							// alert(lngth);
							$(row).closest('tr').find('td:nth-child(4)')
									.remove();
							$(row)
									.closest('tr')
									.find('td:nth-child(3)')
									.after(
											"<td class='col-lg-2 col-xs-2' style='text-align:center;width:15%'><select class='form-control' id='status"
													+ lngth
													+ "'><option value=''>Please select</option><option value='APB'>As per Baseline</option><option value='RE'>Remove</option></select></td>");

							$(row).closest('tr').find('td:nth-child(5)')
									.remove();
							$(row)
									.closest('tr')
									.find('td:nth-child(4)')
									.after(
											"<td class='col-lg-2 col-xs-2' style='text-align:center'><input type='text' style='width:90%' class='form-control input-sm' name='remarks'  id='remarks"
													+ lngth + "'></div>");

							$(row).closest('tr').find('td:nth-child(7)')
									.remove();
							$(row)
									.closest('tr')
									.find('td:nth-child(6)')
									.after(
											"<td class='col-lg-1 col-xs-1' style='text-align:center'> <input type='checkbox' id='"
													+ lngth + "'></td>");
							row1.push($(row).html());

							crtSCndTable.crtSCndTables(row1, row2);

							checkScndTable();
							row1 = [];
							row2 = "";
							beneficiaryId1 = "";

						});

	}
}
function checkScndTable() {
	var rowLength = $("#surveyTable1 tr").length - 1;
	if (rowLength == 0) {
		$("#scndContainer").hide();
	}
}
var crtSCndTable = {
	crtSCndTables : function(row1, row2) {
		if (row1.length != 0) {
			var html = "";
			// for(var j in row1){
			html += "<tr class='row'>";
			html += row1;
			html += "</tr>";
			// }
			$('#surveyTable > tbody:last-child').append(html);
			for ( var i in row2) {
				$(row2[i]).remove();

			}
		}

	}
}

var saveValidateBeneficiayr = {
	saveValidateBeneficiayrs : function() {

		var row1 = [];
		$('#surveyTable1 > tbody  > tr').each(function() {
			row1.push($(this).html());
		});
		var output = [];

		// Loop through grabbing everything
		var $rows = $("#surveyTable1 tbody tr").each(
				function(index) {
					var obj = {};
					obj.status = $(this).closest('tr').find('td:nth-child(4)')
							.text();
					obj.remarks = $(":input[name=remarks]", this).val();
					obj.beneficiary = $(this).closest('tr').find(
							'td:nth-child(6)').text();
					output.push(obj);
				});

		// Let's put this in the object like you want and convert to JSON (Note:
		// jQuery will also do this for you on the Ajax request)
		var surveyDetails = JSON.stringify(output);
		// alert(surveyDetails);
		var surveyId = $("#surveyId").val();
		$.ajax({
			type : "POST",
			url : "validateBeneficiaryAction.do?method=save&d__mode=" + d__mode
					+ "&menuId=SNT011",
			data : {
				surveyId : surveyId,
				jsonArr : surveyDetails
			},
			success : function(data) {
				// alert(data);
				displayMessage(true);
				document.getElementById("p1").innerHTML = data;
				$("#myModal").modal('show');
				if (row1.length != 0) {
					$('#surveyTable1 > tbody > tr').remove();
					$('#scndContainer').hide();
				}
			},
			error : function(e) {
				alert('inside error');
				displayMessage(true);
				document.getElementById("p1").innerHTML = e;
				$("#myModal").modal('show');
			}
		});

	}
}

var addNewBeneficiary = {
	addNewBeneficiarys : function() {
		var surveyIds = $("#surveyId").val();
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='form-inline col-lg-11 col-md-12 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable3' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-2 col-xs-2' style='style='text-align:center'>Name </th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Aadhar Card Number</th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Electric Connection No</th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Status</th>";
		html += "<th class='col-lg-3 col-xs-3' style='text-align:center'>Remarks</th>";
		html += "<th class='col-lg-1 col-xs-1' style='text-align:center'></th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : "POST",
					url : "validateBeneficiaryAction.do?method=getBeneficiary&d__mode="
							+ d__mode + "&menuId=SNT011",
					data : {
						surveyId : surveyIds
					},
					success : function(data) {
						var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:16%'>";
							html += " <a href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1("
									+ parsed[i].beneficiaryId
									+ ")'><span style='color:#2196F3'> "
									+ parsed[i].name + " </span></a>";
							/*
							 * html += "<td class='col-xs-2' style='font-size:0.9em;width:17.9%'>";
							 * html += " <span>" + parsed[i].fatherSpouseName + "</span> ";
							 */
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:17%'>";
							html += " <span>" + parsed[i].aadharNo + "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:19%'>";
							html += " <span>" + parsed[i].electConnNumber
									+ "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:15%'>";
							html += "<select class='form-control' id='status"
									+ i + "'>";
							html += "<option value=''>Please select</option>";
							html += "<option value='APB'>As per Baseline</option>";
							html += "<option value='RE'>Remove</option>";
							html += "</select>";
							html += "<td class='col-lg-3 col-xs-3' style='text-align:center'>";
							html += "<div class='form-inline'>";
							html += "<input type='text' class='form-control input-sm' name='remarks' style='width:90%'  id='remarkssss"
									+ i + "'>";
							html += "<td style='display:none;'><span>"
									+ parsed[i].beneficiaryId + "</span></td>";
							html += "<td class='col-xs-1'>";
							html += "<input type='checkbox' id='" + i
									+ "' name='select'>";
							html += "</tr>";
						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='col-lg-10 col-md-10 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='saveValidateBeneficiaysss.saveValidateBeneficiayrss()'>Add</button></div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "</div>";
						html += "</div>";

						document.getElementsByClassName("modal-body1")[0].innerHTML = html;
						$("#myModal1").modal('show');
					},
					error : function(e) {

					}
				});
	}
}

var saveValidateBeneficiaysss = {
	saveValidateBeneficiayrss : function() {

		var output = [];

		// Loop through grabbing everything
		$.each($("#surveyTable3 input[type=checkbox]:checked"), function() {

			var i = $(this).attr("id");
			var obj = {};
			obj.status = $(this).closest('tr').find(
					'td:nth-child(4) option:selected').text();

			obj.remarks = $('#remarkssss' + i).val();

			obj.beneficiary = $(this).closest('tr').find('td:nth-child(6)')
					.text();
			output.push(obj);
		});
		// Let's put this in the object like you want and convert to JSON (Note:
		// jQuery will also do this for you on the Ajax request)
		var surveyDetails = JSON.stringify(output);
		alert('json-----' + surveyDetails)
		var surveyId = $("#surveyId").val();
		$.ajax({
			type : "POST",
			url : "validateBeneficiaryAction.do?method=save&d__mode=" + d__mode
					+ "&menuId=SNT011",
			data : {
				surveyId : surveyId,
				jsonArr : surveyDetails
			},
			success : function(data) {
				$("#myModal1").modal('hide');
				displayMessage(true);
				document.getElementById("p1").innerHTML = data;
				$("#myModal").modal('show');

			},
			error : function(e) {
				displayMessage(true);
				document.getElementById("p1").innerHTML = e;
				$("#myModal").modal('show');
			}
		});

	}
}

// validate -----
var validateBeneficiary = {
	validateBeneficiarys : function() {

		var surveyIds = $("#surveyId").val();

		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable4' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th  class='col-lg-2 col-xs-2' style='text-align:center'>Name </th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Aadhar Card Number</th>";
		html += "<th class='col-lg-2 col-xs-2' style='text-align:center'>Electric Connection No</th>";
		html += "<th class='col-lg-3 col-xs-3' style='text-align:center'>Status</th>";
		html += "<th class='col-lg-3 col-xs-3' style='text-align:center'>Remarks</th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : "POST",
					url : "validateBeneficiaryAction.do?method=getAddedBeneficiary&d__mode="
							+ d__mode + "&menuId=SNT011",
					data : {
						surveyId : surveyIds
					},
					success : function(data) {

						var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:17.6%'>";
							html += " <a  data-toggle='modal' href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1("
									+ parsed[i].beneficiaryId
									+ ")'><span style='color:#2196F3'> "
									+ parsed[i].name + " </span></a>";

							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:17.3%'>";
							html += " <span>" + parsed[i].aadharNo + "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='text-align:center;width:19%'>";
							html += " <span>" + parsed[i].electConnNumber
									+ "</span> ";
							html += "<td class='col-lg-3 col-xs-3' style='text-align:center;width:22%'>";
							if (parsed[i].action == 'RE') {
								html += " <span>Remove</span> ";
							} else if (parsed[i].action == 'AP') {
								html += " <span>As per Baseline</span> ";
							}

							html += "<td class='col-lg-3 col-xs-3' style='text-align:center'>";
							html += "<div class='form-inline'>";
							html += "<input type='text' class='form-control style='width:90%' input-sm' name='remarks' value='"
									+ parsed[i].remarks + "' readonly>";
							html += "<td style='display:none;'><span>"
									+ parsed[i].validationRequestId
									+ "</span></td>";

							/*
							 * html += "<input type='checkbox' id='" + i + "'
							 * name='select'>";
							 */
							html += "</tr>";
						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='col-lg-10 col-md-10 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='forwardBeneficiayRequest.forwardBeneficiayRequests()' id='forwardBeneficiay'>Forward</button></div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "</div>";
						html += "</div>";

						document.getElementsByClassName("modal-body11")[0].innerHTML = html;
						$("#myModal11").modal('show');
					},
					error : function(e) {

					}
				});
	}
}

var forwardBeneficiayRequest = {
	forwardBeneficiayRequests : function() {
		var validationRequestArr = [];
		var validationRequestId;

		$("#surveyTable4 > tbody > tr").each(
				function() {
					validationRequestArr.push($(this).closest('tr').find(
							'td:nth-child(6)').text());
					validationRequestId = validationRequestArr.join();
				});

		$
				.ajax({
					type : 'POST',
					url : "validateBeneficiaryAction.do?method=updateValidationRequest&d__mode="
							+ d__mode + "&menuId=SNT011",
					data : {
						validationRequestId : validationRequestId,
					},
					success : function(data) {
						$("#myModal11").modal('hide');
						displayMessage(true);
						document.getElementById("p1").innerHTML = data;
						$("#myModal").modal('show');
					},
					error : function(e) {
						alert(e);
					}
				})
	}
}

var getValidateData = {
	getValidateDatas : function() {
		var surveyId = $("#surveyId").val();
		$.ajax({
			type : 'POST',
			url : "reValidateBeneficiaryAction.do?method=finds&d__mode="
					+ d__mode + "&menuId=SNT012",
			cache : false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8;",
			data : {
				surveyId : surveyId,
			},
			success : function(data) {
				alert(data);
			},
			error : function(e) {
				alert(e);
			}
		})
	}
}

var progressStage = {
	progressStageDetails : function(progressId) {
		var html = "";
		$
				.ajax({
					type : 'POST',
					url : "paymentRequestAction.do?method=progressStageDetails&d__mode="
							+ d__mode + "&menuId=SNT012",
					data : {
						progressId : progressId
					},
					success : function(data) {
						var parsed = JSON.parse(data);
						html += "<div class='panel panel-danger'>";
						html += "<div class='panel-body'>";
						html += "<div>";
						html += "<div class='row col-lg-8'>";
						html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
						html += "<label class='text-left labledesign'>Stage Name</label>";
						html += "<label class='text-right' style='width: 150px;'>"
								+ parsed.stageName + "</label>";
						html += "</div>";
						html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
						html += "<label class='text-left labledesign'>Creation Date</label>";
						html += "<label class='text-right' style='width: 150px;'>"
								+ parsed.creationDate + "</label>";
						html += "</div>";
						html += "</div>";
						html += "<div class='row col-lg-4'>";
						html += "<img alt='Image' align='right' width='80%' height='28%'	src='data:image/jpeg;base64,"
								+ parsed.image + "'>";
						html += "</div>";
						html += "</div>";
						html += "</div>";
						html += "<div class='modal-footer'>";
						html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
						html += "</div>";

						document.getElementsByClassName("modal-body1")[0].innerHTML = html;

						$("#myModal1").modal('show');
					}
				});
	}
}

var paymentDetail = {
	getPaymentDetails : function() {
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='form-inline col-lg-2 col-md-2 col-xs-2 col-sm-2'></div>";
		html += "<div class='form-inline col-lg-8 col-md-8 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable4' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-4 col-xs-4'>Bill No.</th>";
		html += "<th class='col-lg-4'>Amount</th>";
		html += "<th class='col-lg-2'>Total Beneficiary</th>";
		html += "<th class='col-lg-2'>View</th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : 'POST',
					url : "verifyPaymentAction.do?method=getPaymentDetails&d__mode="
							+ d__mode + "&menuId=SNT016",
					success : function(data) {
						var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-4 col-xs-4' style='text-align:center;font-size:.9em;'>";
							html += "<span> " + parsed[i].billno
									+ " </span></a>";
							html += "<td class='col-lg-4 col-xs-4' style='text-align:center;font-size:.9em;'>";
							html += " <span>" + parsed[i].amount + "</span> ";
							html += "<td class='col-lg-2 col-xs-2'>";
							html += " <span>" + parsed[i].totalBeneficiary
									+ "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='color:#2196F3' style='text-align:center;font-size: .9em;'>";
							html += " <span> <a  data-toggle='modal' href='javascript:paymentDetailss.getPaymentDetailss("
									+ parsed[i].paymetRequestId
									+ ")'>View</a></span> ";

							html += "</tr>";
						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='form-inline col-lg-2 col-md-2 col-xs-2 col-sm-2'></div>";
						html += "</div>";
						html += "</div>";
						document.getElementById('container').innerHTML = html;
					}

				});
	}
}

var paymentReviewerDetail = {
	getReviewPaymentDetails : function() {
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='form-inline col-lg-2 col-md-2 col-xs-2 col-sm-2'></div>";
		html += "<div class='form-inline col-lg-8 col-md-8 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable4' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-4 col-xs-4'>Bill No.</th>";
		html += "<th class='col-lg-4'>Amount</th>";
		html += "<th class='col-lg-2'>Total Beneficiary</th>";
		html += "<th class='col-lg-2'>View</th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : 'POST',
					url : "approvePaymentAction.do?method=getPaymentDetails&d__mode="
							+ d__mode + "&menuId=SNT016",
					success : function(data) {
						var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-4 col-xs-4' style='text-align:center;font-size:.9em;'>";
							html += "<span> " + parsed[i].billno
									+ " </span></a>";
							html += "<td class='col-lg-4 col-xs-4' style='text-align:center;font-size:.9em;'>";
							html += " <span>" + parsed[i].amount + "</span> ";
							html += "<td class='col-lg-2 col-xs-2'>";
							html += " <span>" + parsed[i].totalBeneficiary
									+ "</span> ";
							html += "<td class='col-lg-2 col-xs-2' style='color:#2196F3' style='text-align:center;font-size: .9em;'>";
							html += " <span> <a  data-toggle='modal' href='javascript:paymentDetailsss.getPaymentDetailsss("
									+ parsed[i].paymetRequestId
									+ ")'>View</a></span> ";

							html += "</tr>";
						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='form-inline col-lg-2 col-md-2 col-xs-2 col-sm-2'></div>";
						html += "</div>";
						html += "</div>";
						document.getElementById('container').innerHTML = html;
					}

				});
	}
}

var paymentDetailsss = {
	getPaymentDetailsss : function(e) {
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='form-inline col-lg-1 col-md-1 col-xs-12 col-sm-12'></div>";
		html += "<div class='form-inline col-lg-10 col-md-10 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='verifyPaymentEntry' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-2 col-xs-2'>Name </th>";
		html += "<th class='col-lg-2 col-xs-2'>Electric Connection No</th>";
		html += "<th class='col-lg-2 col-xs-2'>Aadhar Card Number</th>";
		html += "<th class='col-lg-2 col-xs-2'>Construction	Status</th>";
		html += "<th class='col-lg-2 col-xs-2'>Amount</th>";
		html += "<th class='col-lg-2 col-xs-2'></th>";
		/* html += "<th class='col-lg-1 col-xs-2'>Remove</th>"; */
		// html += "<th class='col-lg-1 col-xs-2'>Remove</th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : 'POST',
					url : "approvePaymentAction.do?method=viewPaymentDetails&d__mode="
							+ d__mode + "&menuId=SNT016",
					data : {
						paymetRequestId : e
					},
					success : function(data) {
						var parsed = JSON.parse(data);
						if (parsed != null) {
							for ( var i in parsed) {
								html += "<tr class='row'>";
								html += "<td class='col-lg-2 col-xs-2'>";
								html += "<span style='color:#2196F3'> "
										+ parsed[i].name + " </span></a>";
								html += "<td class='col-lg-2 col-xs-2' style='text-align:center;font-size: .9em'>";
								html += " <span>" + parsed[i].electConnNumber
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2' style='text-align:center;font-size: .9em'>";
								html += " <span>" + parsed[i].aadharNo
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2'  style='text-align:center;font-size: .9em'>";
								html += " <span> " + parsed[i].stageName
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2'  style='text-align:center;font-size: .9em'>";
								html += " <span> " + parsed[i].amount
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2' style='color:#2196F3;font-size: .9em'>";
								html += " <span> <a  data-toggle='modal' href='javascript:progressStage.progressStageDetails("
										+ parsed[i].progressStageId
										+ ")'>View Status</a></span> ";
								html += "<td style='display:none;'><span>"
										+ parsed[i].validationRequestId
										+ "</span></td>";
								html += "<td style='display:none;'><span>"
										+ parsed[i].paymetRequestId
										+ "</span></td>";
								/*
								 * html += "<td class='col-lg-1 col-xs-2'>";
								 * html += " <button title='Remove'
								 * type='button' class='btn btn-danger remove
								 * show_tip' id='removeRow1'
								 * data-original-title='Remove from list'
								 * onclick='deletePaymentDetails.deletePaymentDetail(" +
								 * parsed[i].requestDetailId + ")'><i class='fa
								 * fa-trash-o'></i></button>";
								 */
								html += "</tr>";
							}
							html += "</tbody>";
							html += "</table>";
							html += "</div>";
							html += "</div>";
							html += "<div class='col-lg-4 col-md-4 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='returnToBeneficiarys()' id='returnBeneficiary'>Send Back</button></div>";
							html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='forwrdBeneficiary.approveBeneficiarys()' id='forwardBeneficiay1'>Approve</button></div>";
							html += "<div class='col-lg-4 col-md-4 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "</div>";
							html += "</div>";
						} else {
							html += "<tr class='row'>";
							html += "<td class='col-lg-12 col-xs-12' style='text-align:center'>";
							html += " <span>No Record Found</span> ";
							html += "</td>";
							html += "</tr>";
							html += "</tbody>";
							html += "</table>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
						}
						document.getElementById('approverContainer').innerHTML = html;

					}
				});
	}
}

var paymentDetailss = {
	getPaymentDetailss : function(e) {
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='form-inline col-lg-1 col-md-1 col-xs-12 col-sm-12'></div>";
		html += "<div class='form-inline col-lg-10 col-md-10 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='verifyPaymentEntry1' style='display: block;' >";
		html += "<thead style='display: block;' class='thead-dark'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-2 col-xs-2'>Name </th>";
		html += "<th class='col-lg-2 col-xs-2'>Electric Connection No</th>";
		html += "<th class='col-lg-2 col-xs-2'>Aadhar Card Number</th>";
		html += "<th class='col-lg-2 col-xs-2'>Construction	Status</th>";
		html += "<th class='col-lg-2 col-xs-2'>Amount</th>";
		html += "<th class='col-lg-1 col-xs-2'></th>";
		html += "<th class='col-lg-1 col-xs-2'>Remove</th>";
		// html += "<th class='col-lg-1 col-xs-2'>Remove</th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : 'POST',
					url : "verifyPaymentAction.do?method=viewPaymentDetails&d__mode="
							+ d__mode + "&menuId=SNT016",
					data : {
						paymetRequestId : e
					},
					success : function(data) {
						var parsed = JSON.parse(data);
						if (parsed != null) {
							for ( var i in parsed) {
								html += "<tr class='row'>";
								html += "<td class='col-lg-2 col-xs-2'>";
								html += "<span style='color:#2196F3'> "
										+ parsed[i].name + " </span></a>";
								html += "<td class='col-lg-2 col-xs-2' style='text-align:center;font-size: .9em'>";
								html += " <span>" + parsed[i].electConnNumber
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2' style='text-align:center;font-size: .9em'>";
								html += " <span>" + parsed[i].aadharNo
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2'  style='text-align:center;font-size: .9em'>";
								html += " <span> " + parsed[i].stageName
										+ "</span> ";
								html += "<td class='col-lg-2 col-xs-2'  style='text-align:center;font-size: .9em'>";
								html += " <span> " + parsed[i].amount
										+ "</span> ";
								html += "<td class='col-lg-1 col-xs-2' style='color:#2196F3;font-size: .9em'>";
								html += " <span> <a  data-toggle='modal' href='javascript:progressStage.progressStageDetails("
										+ parsed[i].progressStageId
										+ ")'>View Status</a></span> ";
								html += "<td style='display:none;'><span>"
										+ parsed[i].validationRequestId
										+ "</span></td>";
								html += "<td style='display:none;'><span>"
										+ parsed[i].paymetRequestId
										+ "</span></td>";
								html += "<td class='col-lg-1 col-xs-2'>";
								html += " <button title='Remove' type='button'  class='btn btn-danger remove show_tip' id='removeRow1' data-original-title='Remove from list' onclick='deletePaymentDetails.deletePaymentDetail("
										+ parsed[i].requestDetailId
										+ ")'><i class='fa fa-trash-o'></i></button>";
								html += "</tr>";
							}
							html += "</tbody>";
							html += "</table>";
							html += "</div>";
							html += "</div>";
							html += "<div class='col-lg-4 col-md-4 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='returnBeneficiarys()' id='returnBeneficiary'>Send Back</button></div>";
							html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='forwrdBeneficiary.forwrdBeneficiarys()' id='forwardBeneficiay1'>Verify</button></div>";
							html += "<div class='col-lg-4 col-md-4 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "</div>";
							html += "</div>";
						} else {
							html += "<tr class='row'>";
							html += "<td class='col-lg-12 col-xs-12' style='text-align:center'>";
							html += " <span>No Record Found</span> ";
							html += "</td>";
							html += "</tr>";
							html += "</tbody>";
							html += "</table>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
						}

						document.getElementById('verifyContainer').innerHTML = html;

					}
				});
	}
}

var deletePaymentDetails = {
	deletePaymentDetail : function(e) {
		var row;
		$('#verifyPaymentEntry1 tbody').on('click', '#removeRow1', function() {
			row = $(this).parent().parent();
		});
		$.ajax({
			type : 'POST',
			url : "verifyPaymentAction.do?method=delete&d__mode=" + d__mode
					+ "&menuId=SNT016",
			data : {
				requestDetailId : e
			},
			success : function(data) {
				if (data === "Successfully Removed") {
					$(row).closest('tr').remove();
				}
			}
		});
	}
}

/*function returnBeneficiarys() {
	alert('back')
	var validationRequestId;
	var row2 = [];
	$('#verifyPaymentEntry1 > tbody  > tr').each(function() {
		var row = $(this).html();
		row2.push(row);

		row2.push($(this).closest('tr').find('td:nth-child(6)').text());
		validationRequestId = row2.join();
	});
	alert(validationRequestId)
	$.ajax({
		type : 'POST',
		url : "verifyPaymentAction.do?method=back&d__mode=" + d__mode
				+ "&menuId=SNT016",
		data : {
			requestDetailId : e
		},
		success : function(data) {

		}
	});

}
var forwrdBeneficiary = {
	forwrdBeneficiarys : function() {
		alert('hiiii')
		var beneficiaryId;
		var row2 = [];
		$('#surveyTable1 > tbody  > tr').each(
				function() {
					var row = $(this).html();
					row2.push(row);

					row1.push($(this).html());
					beneficiarys.push($(this).closest('tr').find(
							'td:nth-child(5)').text());
					alert($(this).closest('tr').find('td:nth-child(5)').text())
					beneficiaryId = beneficiarys.join();
				});

		$.ajax({
			type : 'POST',
			url : "verifyPaymentAction.do?method=back&d__mode=" + d__mode
					+ "&menuId=SNT016",
			data : {
				requestDetailId : e
			},
			success : function(data) {
				if (data === "Successfully Removed") {
					$(row).closest('tr').remove();
				}
			}
		});
	}
}*/
