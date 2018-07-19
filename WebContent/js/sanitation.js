var viewRegistration_gm1;
var viewRegistration_gm = {
	viewRegistration_gm_Type : function(district, block, villageId) {
		if ($.fn.dataTable.isDataTable('#viewRegisterDT')) {
			viewRegistration_gm1.destroy();
		}
		viewRegistration_gm1 =

		$("#viewRegisterDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								},

								]
							} ],

							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"sScrollX" : true,
							"scrollY" : "170px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'viewRegistrationsAction.do?district='
									+ district
									+ '&block='
									+ block
									+ '&village='
									+ villageId
									+ '&method=populateGramPanchayatData&d__mode="+d__mode+"&menuId=SAN002',

							"columns" : [ {
								"data" : "villageName",
								"defaultContent" : ""
							}, {
								"data" : "habitationName",
								"defaultContent" : ""
							}, {
								"data" : "familyId",
								"defaultContent" : ""
							}, {
								"data" : "familyheadName",
								"defaultContent" : ""
							} /*
								 * , { "data" : "fatherHusbandName",
								 * "defaultContent" : "" }, { "data" :
								 * "cardType", "defaultContent" : "" }, { "data" :
								 * "aadhaarNumber", "defaultContent" : "" }, {
								 * "data" : "gender", "defaultContent" : "" }, {
								 * "data" : "category", "defaultContent" : "" }, {
								 * "data" : "subcategory", "defaultContent" : "" }, {
								 * "data" : "caste", "defaultContent" : "" }, {
								 * "data" : "hadToiletBefore", "defaultContent" : "" }, {
								 * "data" : "collDate", "defaultContent" : "" },
								 */
							/*
							 * { "data" : "functionalToilet", "defaultContent" : "" }, {
							 * "data" : "havingFunctionalToilet",
							 * "defaultContent" : "" }, { "data" :
							 * "waterfacility", "defaultContent" : "" }, {
							 * "data" : "isCovered", "defaultContent" : "" }, {
							 * "data" : "toiletType", "defaultContent" : "" }, {
							 * "data" : "remarks", "defaultContent" : "" }
							 */

							]

						});

	}
}

var beneficiaryRegister;
var viewBeneficiary = {
	viewBeneficiaryType : function(district, block, villageId) {

		if ($.fn.dataTable.isDataTable('#beneficiaryRegisterDT')) {
			beneficiaryRegister.destroy();
		}
		beneficiaryRegister =

		$("#beneficiaryRegisterDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3 ]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3 ]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3 ]
									}
								},

								]
							} ],

							"language" : {
								"lengthMenu" : "Display _MENU_ records per page",
								"zeroRecords" : "Nothing found - sorry",
								"info" : "Showing page _PAGE_ of _PAGES_",
								"infoEmpty" : "No records available",
								"infoFiltered" : "(filtered from _MAX_ total records)"
							},

							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"sScrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'benifecieryEntryViewAction.do?district='
									+ district
									+ '&block='
									+ block
									+ '&village='
									+ villageId
									+ '&method=populateBeneficiaryData&d__mode="+d__mode+"&menuId=SNT004',
							columnDefs : [ {
								"targets" : 0,
								className : 'resultd',
								"data" : 'personName',
								"render" : function(data, type, row, meta) {
									return data;
								}
							}, {
								"targets" : [ 4 ],
								"visible" : false,
								"searchable" : false
							} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "personName",
								"defaultContent" : ""
							}, {
								"data" : "fatherSpouseName",
								"defaultContent" : ""
							}, {
								"data" : "religion",
								"defaultContent" : ""
							}, {
								"data" : "adharNumber",
								"defaultContent" : ""
							}, {
								"data" : "beneficiaryId",
								"defaultContent" : ""
							} ]

						});
		$('.datatable').each(
				function() {
					var datatable = $(this);
					// SEARCH - Add the placeholder for Search and Turn this
					// into in-line form control
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					// LENGTH - Inline-Form control
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

		$('#beneficiaryRegisterDT tbody').on('click', '.resultd', function() {
			var data = beneficiaryRegister.row(this).data();
			beneficiaryEntryDetails.beneficiaryEntryDetail(data);
		});
	}
}
var beneficiaryEntryDetails = {
	beneficiaryEntryDetail : function(data) {
		$
				.ajax({
					type : "POST",
					url : "benifecieryEntryViewAction.do?method=getBeneficiaryDetails&d__mode="
							+ d__mode + "&menuId=SNT004",
					data : {
						beneficiaryId : data.beneficiaryId
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
	//var ctx =  window.location.origin;
	var re = new RegExp(/^.*\//);
	ctx=re.exec(window.location.href);
	var html = "";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Person Details</h4>";
	html += "<div>";
	html += "<div class='row col-lg-8'>";
	html += "<div class='form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>";
	html += "<label class='text-left labledesign'>Caste Name</label>";
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
	html += "<label class='text-left' style='width: 250px; height: 50px;margin-left: 13%;'>";
	html += "<a href='"+ctx+"benifecieryEntryViewAction.do?method=downloadElectricityBill&beneficiaryId="
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

var surveyMaster;
var SurveyMaster = {
	SurveyMasterType : function() {
		surveyMaster = $("#surveyMasterDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3, 4, 5, 6, 7 ]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4, 5, 6, 7 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4, 5, 6, 7 ]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3, 4, 5, 6, 7 ]
									}
								},

								]
							} ],
							"language" : {
								"lengthMenu" : "Display _MENU_ records per page",
								"zeroRecords" : "Nothing found - sorry",
								"info" : "Showing page _PAGE_ of _PAGES_",
								"infoEmpty" : "No records available",
								"infoFiltered" : "(filtered from _MAX_ total records)"
							},
							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"scrollY" : "170px",
							"sScrollX" : true,
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'surveyMasterAction.do?method=populateSurveyMasterData&d__mode="+d__mode+"&menuId=SAN003',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
							},

							{
								"targets" : [ 7 ],
								"visible" : false,
								"searchable" : false
							},

							{
								"targets" : [ 8 ],
								"visible" : false,
								"searchable" : false
							} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "render"

							}, {
								"data" : "surveyName",
								"defaultContent" : ""
							}, {
								"data" : "surveyStatus",
								"defaultContent" : ""
							}, {
								"data" : "plannedStartDate",
								"defaultContent" : ""
							}, {
								"data" : "plannedEndDate",
								"defaultContent" : ""
							}, {
								"data" : "actualStartDate",
								"defaultContent" : ""
							}, {
								"data" : "actualEndDate",
								"defaultContent" : ""
							}, {
								"data" : "purpose",
								"defaultContent" : ""
							}, {
								"data" : "surveyID",
								"defaultContent" : ""
							}

							]

						});
		$('.datatable').each(
				function() {
					var datatable = $(this);
					// SEARCH - Add the placeholder for Search and Turn this
					// into in-line form control
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					// LENGTH - Inline-Form control
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

		$('#surveyMasterDT tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify')
				var data = surveyMaster.row(this).data();
			surveyMasterData.populateSurveyTypeData(data);
		});

	}
}

var surveyMasterData = {
	populateSurveyTypeData : function(data) {

		document.getElementById('surveyName').value = data.surveyName;

		document.getElementById('surveyStatus').value = data.surveyStatus;

		if (typeof data.plannedStartDate != 'undefined') {
			document.getElementById('plannedStartDate').value = data.plannedStartDate;
		}
		if (typeof data.plannedEndDate != 'undefined') {
			document.getElementById('plannedEndDate').value = data.plannedEndDate;
		}
		if (typeof data.actualStartDate != 'undefined') {
			document.getElementById('actualStartDate').value = data.actualStartDate;
		}
		if (typeof data.actualEndDate != 'undefined') {
			document.getElementById('actualEndDate').value = data.actualEndDate;
		}

		document.getElementById('purpose').value = data.purpose;

	}
}

var baseLineSurveyFind = {
	baseLineSurveyFinds : function() {

		var baseLineForm = $("#baseLineForm");
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<div class='col-lg-8 col-md-8 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='col-lg-3 col-md-3 sm-hidden xs-hidden'>";
		html += "<div class='form-inline pull-right'> <input type='text' class='search form-control' id='myInput' onkeyup='search()' placeholder='What you looking for?'></div></div>";
		html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='form-inline col-lg-9 col-md-9 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable'>";
		html += "<thead style='display: block;' class='thead-inverse'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-2 col-xs-2'>Name</th>";
		html += "<th class='col-lg-3 col-xs-3'>Father/Husband Name</th>";
		html += "<th class='col-lg-3 col-xs-3'>Aadhar Card Number</th>";
		html += "<th class='col-lg-3 col-xs-3'>Electric Connection No</th>";
		html += "<th class='col-lg-1 col-xs-1'><input type='checkbox' class='checkbox' id='checkAll' name='select-all' onclick='handleCheckbox(this)'></th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : "POST",
					url : "baselineSurveyAction.do?method=find&d__mode="
							+ d__mode + "&menuId=SNT007",
					data : baseLineForm.serialize(),
					success : function(data) {
							var parsed = JSON.parse(data);
						if(parsed!="No Record Found"){
						//var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-2 col-xs-2'>";
							html += " <a href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1("
									+ parsed[i].beneficiaryId
									+ ")'><span style='color:#2196F3'> "
									+ parsed[i].name + " </span></a>";
							html += "<td class='col-lg-3 col-xs-3'>";
							html += " <span>" + parsed[i].fatherSpouseName
									+ "</span> ";
							html += "<td class='col-lg-3 col-xs-3'>";
							html += " <span>" + parsed[i].aadharNo + "</span> ";
							html += "<td class='col-lg-3 col-xs-3'>";
							html += " <span>" + parsed[i].electConnNumber
									+ "</span> ";
							html += "<td style='display:none;'><span>"
									+ parsed[i].beneficiaryId + "</span></td>";
							html += "<td class='col-lg-1 col-xs-1'>";
							html += "<input type='checkbox' id='checkItem' name='select'>";
							html += "</tr>";
						}
						
					}
//						else{
//						html += "<tr class='row' id='1'>";
//						html += "<td class='col-xs-12' style='text-align:center'>";
//						html += " <span  id='record' >" + "No Record Found"
//						+ "</span> ";
//					}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-6 col-md-6 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='baseLineSurveyFind1.baseLineSurveyFinds1()'>Added Survey</button></div>";
						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' onclick='addDataTScndTable.addData()'>Add</button></div>";
						html += "<div class='col-lg-3 col-md-3 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "</div>";
						html += "</div>";

						document.getElementById('container').innerHTML = html;
						$("#container").show();
					}
				});

	}
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
							var ss = $(row).closest('tr').find('th');
							if (ss.length < 1) {
								row2.push(row);
								$(row).closest('tr').find('td:nth-child(6)')
										.remove();
								$(row)
										.closest('tr')
										.find('td:nth-child(5)')
										.after(
												"<td class='col-lg-1 col-xs-1'> <button title='Remove' type='button'  class='btn btn-danger remove show_tip' id='removeRow' data-original-title='Remove from list' onclick='addRwTFrstTable.addRwTFrstTables()'><i class='fa fa-trash-o'></i></button></td>");
								row1.push($(row).html());
								beneficiarys.push($(row).closest('tr').find(
										'td:nth-child(5)').text());
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
var baseLineSurveySave = {
	addBaseLineSurvey : function() {

		var beneficiarys = [];
		var row1 = [];
		var beneficiaryId;
		var row2 = [];
		$('#surveyTable1 > tbody  > tr').each(
				function() {
					var row = $(this).html();
					row2.push(row);

					row1.push($(this).html());
					beneficiarys.push($(this).closest('tr').find(
							'td:nth-child(5)').text());
					beneficiaryId = beneficiarys.join();
				});
		var surveyId = $("#surveyId").val();
		var district = $("#district").val();
		var block = $("#block").val();
		var village = $("#villageId").val();
		var gramPanchayatId = $("#gramPanchayatId").val();
		var surveyName = $("#surveyId option:selected").text();
		$.ajax({
			type : "POST",
			url : "baselineSurveyAction.do?method=save&d__mode=" + d__mode
					+ "&menuId=SNT007",
			data : {
				surveyId : surveyId,
				district : district,
				block : block,
				village : village,
				gramPanchayatId : gramPanchayatId,
				beneficiary : beneficiaryId,
				surveyName:surveyName
			},
			success : function(data) {
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
							$(row).closest('tr').find('td:nth-child(6)')
									.remove();
							$(row)
									.closest('tr')
									.find('td:nth-child(5)')
									.after(
											"<td class='col-lg-1 col-xs-1'> <input type='checkbox' id='checkItem'></td>");
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
var baseLineSurveyDelete = {
	deleteSurvey : function() {
		var beneficiarys = [];
		var row1 = [];
		var beneficiaryId1;
		var row2;
		$('#surveyTable2 tbody')
				.on(
						'click',
						'#removeRow',
						function() {
							var row = $(this).parent().parent();
							row2 = row;
							$(row).closest('tr').find('td:nth-child(6)')
							.remove();
							$(row)
									.closest('tr')
									.find('td:nth-child(5)')
									.after(
											"<td class='col-lg-1 col-xs-1'> <input type='checkbox' id='checkItem'></td>");
							$(row).closest('tr').find('td:nth-child(7)')
									.remove();
							
							row1.push($(row).html());

							beneficiaryId1 = $(row).closest('tr').find(
									'td:nth-child(5)').text();
							deleteSurveyFun.dltSurvey(beneficiaryId1, row1,
									row2);
							checkScndTable1();

							row1 = [];
							row2 = "";
							beneficiaryId1 = "";

						});

	}
}

var deleteSurveyFun = {
	dltSurvey : function(data, row1, row2) {
		$.ajax({
			type : "POST",
			url : "baselineSurveyAction.do?method=delete&d__mode=" + d__mode
					+ "&menuId=SNT007",
			data : {
				beneficiary : data
			},
			success : function(data) {
				// displayMessage(true);
				// document.getElementById("p1").innerHTML = data;
				// $("#myModal").modal('show');
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
function checkScndTable1() {
	var rowLength = $("#surveyTable2 tr").length - 2;
	if (rowLength == 0) {
		$("#myModal1").modal('hide');
	}
}
var baseLineSurveyFind1 = {
	baseLineSurveyFinds1 : function() {

		// boolean status = validateField();
		// if (status) {
		var baseLineForm = $("#baseLineForm");
		var html = "";

		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";

		html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp &nbsp;</div>";
		html += "<div class=' form-inline col-lg-11 col-md-11 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-bordered table-striped' id='surveyTable2'>";
		html += "<thead style='display: block;'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-2 col-xs-2'>Name</th>";
		html += "<th class='col-lg-3 col-xs-3'>Father/Husband Name</th>";
		html += "<th class='col-lg-3 col-xs-3'>Aadhar Card Number</th>";
		html += "<th class='col-lg-3 col-xs-3'>Electric Connection No</th>";
		html += "<th class='col-lg-1 col-xs-1'></th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : "POST",
					url : "baselineSurveyAction.do?method=getAddedSurvey&d__mode="
							+ d__mode + "&menuId=SNT007",
					data : baseLineForm.serialize(),
					success : function(data) {

						if (data.length <= 2 || data == null) {
							html += "<tr class='row'>";
							html += "<td class='col-lg-12 col-xs-12' style='text-align:center'>";
							html += " <span >No Record Found</span> ";
							html += "</td>";
							html += "</tr>";
							html += "</tbody>";
							html += "</table>";
							html += "</div>";
							html += "</div>";
							html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "<div class='col-lg-9 col-md-9 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
							html += "<div class='modal-footer'>";
							html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
							html += "</div>";
						} else {
							var parsed = JSON.parse(data);

							for ( var i in parsed) {
								html += "<tr class='row'>";
								html += "<td class='col-lg-2 col-xs-2'>";
								html += " <a href='javascript:beneficiaryEntryDetails1.beneficiaryEntryDetail1("
										+ parsed[i].beneficiaryId
										+ ")'><span style='color:#2196F3'> "
										+ parsed[i].name + " </span></a>";
								html += "<td class='col-lg-3 col-xs-3'>";
								html += " <span>" + parsed[i].fatherSpouseName
										+ "</span> ";
								html += "<td class='col-lg-3 col-xs-3'>";
								html += " <span>" + parsed[i].aadharNo
										+ "</span> ";
								html += "<td class='col-lg-3 col-xs-3'>";
								html += " <span>" + parsed[i].electConnNumber
										+ "</span> ";
								html += "<td style='display:none;'><span>"
										+ parsed[i].beneficiaryId
										+ "</span></td>";
								html += "<td style='display:none;'><span>"
										+ parsed[i].baselineSurveyId
										+ "</span></td>";
								html += "<td class='col-lg-1 col-xs-1'>";
								html += " <button title='Remove' type='button'  class='btn btn-danger remove show_tip' id='removeRow' data-original-title='Remove from list' onclick='baseLineSurveyDelete.deleteSurvey()'><i class='fa fa-trash-o'></i></button>";
								html += "<td style='display:none;'><span>"
										+ parsed[i].surveyId + "</span></td>";
								html += "</tr>";
							}
							html += "</tbody>";
							html += "</table>";
							html += "</div>";
							html += "</div>";
							html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "<div class='col-lg-9 col-md-9 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
							html += "<div class='modal-footer'>";
							html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
							html += "<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='javascript:freezeBaseLineSurvey.freezeBaseLineSurveys()'>Freeze</button>";
							html += "</div>";
						
						}
						
						document.getElementsByClassName("modal-body1")[0].innerHTML = html;

						$("#myModal1").modal('show');
					}
				});

	}
// }
}
var freezeBaseLineSurvey = {
	freezeBaseLineSurveys : function() {
		var baseLineIds = [];
		var row1 = [];
		var baseLineId;
		var surveyId;
		var row2 = [];
		$('#surveyTable2 > tbody  > tr').each(
				function() {
					var row = $(this).html();
					row2.push(row);
					row1.push($(this).html());
					baseLineIds.push($(this).closest('tr').find(
							'td:nth-child(6)').text());
					baseLineId = baseLineIds.join();

					surveyId = $(this).closest('tr').find('td:nth-child(8)')
							.text()
				});
		$.ajax({
			type : "POST",
			url : "baselineSurveyAction.do?method=update&d__mode=" + d__mode
					+ "&menuId=SNT007",
			data : {
				baseLineId : baseLineId,
				surveyId : surveyId
			},
			success : function(data) {
				displayMessage(true);
				document.getElementById("p1").innerHTML = data;
				$("#myModal").modal('show');
				$("#myModal1").modal('hide');
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

var validateBeneficiary = {
	validateBeneficiarys : function() {
		var html = "";
		html += "<div class='panel panel-danger'>";
		html += "<div class='panel-body'>";
		html += "<h4	class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Freezed Survey List</h4>";
		html += "<div class='col-lg-9 col-md-9 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'>";
		html += "<div class='form-inline pull-right'> <input type='text' class='search form-control' id='myInput' onkeyup='search()' placeholder='What you looking for?'></div></div>";
		html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='col-lg-3 col-md-4 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
		html += "<div class='form-inline col-lg-8 col-md-8 col-xs-12 col-sm-12'>";
		html += "<div class='table-responsive'>";
		html += "<table class='table table-bordered table-striped' id='surveyTable'>";
		html += "<thead style='display: block;' class='thead-inverse'>";
		html += "<tr class='row'>";
		html += "<th class='col-lg-5 col-xs-5'>Survey Name</th>";
		html += "<th class='col-lg-7 col-xs-7'>Purpose</th>";
		html += "</tr>"
		html += "</thead>";
		html += "<tbody style='display: block;'>";
		$
				.ajax({
					type : "POST",
					url : "validateRequestAction.do?method=find&d__mode="
							+ d__mode + "&menuId=SNT008",
					success : function(data) {
						var parsed = JSON.parse(data);
						for ( var i in parsed) {
							html += "<tr class='row'>";

							html += "<td class='col-lg-5 col-xs-5'>";
							html += " <a href='javascript:displayFreezedSrvy.freezedSurvey("
									+ parsed[i].surveyId
									+ ")'><span style='color:#2196F3'> "
									+ parsed[i].surveyName + "</span> ";

							html += "<td class='col-lg-7 col-xs-7'>";
							html += "<span>" + parsed[i].purpose + "</span>";
							html += "</tr>";
						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='col-lg-3 col-md-2 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "</div>";
						html += "</div>";

						document.getElementById('container').innerHTML = html;
					}
				});
	}
}

var displayFreezedSrvy = {
	freezedSurvey : function(surveyId) {
		var html = "";
		$
				.ajax({
					type : 'POST',
					url : 'validateRequestAction.do?method=populateSurvey&d__mode="+ d__mode + "&menuId=SNT008',
					data : {
						surveyId : surveyId,
					},
					success : function(data) {
						var parsed = JSON.parse(data);
						html += "<div class='panel panel-danger'>";
						html += "<div class='panel-body'>";
						html += "<h4	class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Survey Details</h4>";
						html += "<div class='col-lg-12 col-md-12 sm-hidden xs-hidden'></div>";
						html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><label class='text-left labledesign'>Survey Name</label></div><div class='col-lg-2 col-md-2 sm-hidden xs-hidden'>"
								+ parsed[0].surveyName + "</div>";
						html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><label class='text-left labledesign'>Actual Start Date</label></div><div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><span>"
								+ parsed[0].actualStartDate + "</div></span>";
						html += "<div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><label class='text-left labledesign'>Actual End Date</label></div><div class='col-lg-2 col-md-2 sm-hidden xs-hidden'><span>"
								+ parsed[0].actEndDate + "</div></span>";

						html += "</div>";
						html += "</div>";
						html += "<div class='panel panel-danger'>";
						html += "<div class='panel-body'>";
						html += "<h4	class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Freezed Survey Details</h4>";

						html += "<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";

						html += "<div class='form-inline col-lg-11 col-md-11 col-xs-12 col-sm-12' style='overflow-x: auto'>";
						html += "<div class='table-responsive'>";
						html += "<table class='table table-bordered table-striped' id='surveyTable'>";
						html += "<thead style='display: block;' class='thead-inverse'>";
						html += "<tr class='row'>";
						html += "<th class='col-lg-2 col-xs-2'>Name</th>";
						html += "<th class='col-lg-3 col-xs-3'>Father/Husband Name</th>";
						html += "<th class='col-lg-3 col-xs-3'>Aadhar Card Number</th>";
						html += "<th class='col-lg-3 col-xs-3'>Electric Connection No</th>";
						html += "<th class='col-lg-1 col-xs-1'></th>";
						html += "</tr>"
						html += "</thead>";
						html += "<tbody style='display: block;'>";
						for ( var i in parsed) {

							html += "<tr class='row'>";
							html += "<td class='col-lg-2 col-xs-2'>";
							html += "<span style='color:#2196F3'> "
									+ parsed[i].name + " </span></a>";
							html += "<td class='col-lg-3 col-xs-3'>";
							html += " <span>" + parsed[i].fatherSpouseName
									+ "</span> ";
							html += "<td class='col-lg-3 col-xs-3'>";
							html += " <span>" + parsed[i].aadharNo + "</span> ";
							html += "<td class='col-lg-3 col-xs-3'>";
							html += " <span>" + parsed[i].electConnNumber
									+ "</span> ";
							html += "<td style='display:none;'><span>"
									+ parsed[i].beneficiaryId + "</span></td>";
							html += "<td style='display:none;'><span>"
									+ parsed[i].baselineSurveyId
									+ "</span></td>";
							html += "<td class='col-lg-1 col-xs-1'>";
							html += "<td style='display:none;'><span>"
									+ parsed[i].surveyId + "</span></td>";
							html += "</tr>";

						}
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
						html += "</div>";
						html += "<div class='col-lg-9 col-md-9 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
						html += "</div>";
						html += "</div>";
						html += "</div>";
						html += "<div class='modal-footer'>";
						html += "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>";
						html += "<button type='button' class='btn btn-danger' data-dismiss='modal' onclick='javascript:forwardSurveyFlw.forwardSurveyFlow("
								+ parsed[i].surveyId + ")'>Forward</button>";
						html += "</div>";
						document.getElementsByClassName("modal-body3")[0].innerHTML = html;
						$("#myModal3").modal('show');
					},
					error : function(e) {

					}

				});
	}
};

var forwardSurveyFlw = {
	forwardSurveyFlow : function(surveyId) {
		$
				.ajax({
					type : 'POST',
					url : 'validateRequestAction.do?method=saveEmployee&d__mode="+ d__mode + "&menuId=SNT008',
					data : {
						surveyId : surveyId
					},
					success : function(data) {
						displayMessage(true);
						document.getElementById("p1").innerHTML = data;
						$("#myModal").modal('show');
					},
					error : function(e) {
						alert(e)
					}
				})
	}
}

var gramPanchayatMaster;
var gramPanchayatMasters = {
	GramPanchayatType : function() {
		gramPanchayatMaster = $("#gramPanchayatMasterDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								},

								]
							} ],
							"language" : {
								"lengthMenu" : "Display _MENU_ records per page",
								"zeroRecords" : "Nothing found - sorry",
								"info" : "Showing page _PAGE_ of _PAGES_",
								"infoEmpty" : "No records available",
								"infoFiltered" : "(filtered from _MAX_ total records)"
							},
							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"scrollY" : "170px",
							"sScrollX" : true,
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'gramPanchayatMasterAction.do?method=populateGramPanchayatMasterData&d__mode="+d__mode+"&menuId=SAN013',
							columnDefs : [ /*{
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
							},*/

							],
							select : {
								style : 'os',
								//selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "nameofGramPanchayat",
								"defaultContent" : ""
							}, {
								"data" : "district",
								"defaultContent" : ""
							}, {
								"data" : "block",
								"defaultContent" : ""
							}, {
								"data" : "village",
								"defaultContent" : ""
							}

							]

						});
		$('.datatable').each(
				function() {
					var datatable = $(this);
					// SEARCH - Add the placeholder for Search and Turn this
					// into in-line form control
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					// LENGTH - Inline-Form control
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});
/*
		$('#surveyMasterDT tbody').on('click', '.select-checkbox', function() {
			alert('hi');
			if (d__mode == 'ent_modify')
				var data = surveyMaster.row(this).data();
			surveyMasterData.populateSurveyTypeData(data);
		});*/

	}
}
var hallofFame;
var hallofFames = {
	HallofFameType : function() {
		hallofFame = $("#hallofFameDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								},

								]
							} ],
							"language" : {
								"lengthMenu" : "Display _MENU_ records per page",
								"zeroRecords" : "Nothing found - sorry",
								"info" : "Showing page _PAGE_ of _PAGES_",
								"infoEmpty" : "No records available",
								"infoFiltered" : "(filtered from _MAX_ total records)"
							},
							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"scrollY" : "170px",
							"sScrollX" : true,
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'hallofFameAction.do?method=populateHallofFameData&d__mode="+d__mode+"&menuId=SNT009',
							columnDefs : [ {
								"targets" : 0,
								className : 'resultd',
								"data" : 'nameofActivity',
								"render" : function(data, type, row, meta) {
									return data;
								}
							},

							{
								"targets" : [ 4 ],
								"visible" : false,
								"searchable" : false
							}
								],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "nameofActivity",
								"defaultContent" : ""
							}, {
								"data" : "type",
								"defaultContent" : ""
							}, {
								"data" : "leadBy",
								"defaultContent" : ""
							}, {
								"data" : "description",
								"defaultContent" : ""
							}, {
								"data" : "activityId",
								"defaultContent" : ""
							}

							]

						});
		$('.datatable').each(
				function() {
					var datatable = $(this);
					// SEARCH - Add the placeholder for Search and Turn this
					// into in-line form control
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					// LENGTH - Inline-Form control
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

		$('#hallofFameDT tbody').on('click', '.resultd', function() {
			var data = hallofFame.row(this).data();
			hallofFameDetails.hallofFameDetail(data);
		});

	}
}

var hallofFameDetails = {
		hallofFameDetail : function(data) {
			$
					.ajax({
						type : "POST",
						url : "hallofFameAction.do?method=gethallofFameDetails&d__mode="
								+ d__mode + "&menuId=SNT009",
						data : {
							activityId : data.activityId
						},
						success : function(data) {
							var parsed = JSON.parse(data);
							displayHallofFameDetails(parsed);
							$("#myModal").modal('show');
							// event.preventDefault();
						},
						error : function(error) {

						}
					});

		}
	}

function displayHallofFameDetails(data) {
	var html = "";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Activity Details</h4>";
	html += "<div class='col-lg-12 col-md-12 col-sm-12 col-xs-12'>&nbsp;&nbsp;</div>";
	html += "<div class='row col-lg-12-col-xs-12 col-sm-12 col-md-12'>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6'>";
	html += "<label class='text-left labledesign'>Name of Activity</label>";
	html += "<text:area class='text-right form-control' style='width: 150px; border:none; box-shadow:none; margin:0px'>"
			+ data.nameofActivity + "</text:area>";
	html += "</div>";
	html += "<div class='form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6'>";
	html += "<label class='text-left labledesign'>Type of Activity</label>";
	html += "<text:area class='text-right form-control' style='width: 150px; border:none; box-shadow:none; margin:0px'>"
			+ data.type + "</text:area>";
	html += "</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6'>";
	html += "<label class='text-left labledesign'>Lead By</label>";
	html += "<text:area	class='text-right  form-control' style='width: 150px; border:none; box-shadow:none; margin:0px'>"
			+ data.leadBy + "</text:area>";
	html += "</div>";
	html += "<div class='col-lg-4 col-md-5 col-xs-12 col-sm-6'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-4 col-md-5 col-xs-12 col-sm-6'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='form-inline col-lg-10 col-md-10 col-xs-12 col-sm-10'>";
	html += "<label class='text-left labledesign'>Description</label>";
	html += "<text:area class='text-right form-control' style='min-width: 250px;border:none;box-shadow:none;margin:0px;text-align:justify;height:  auto'>"
			+ data.description + "</text:area>";
	html += "</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "<div class='form-inline col-lg-6 col-md-6 col-xs-12 col-sm-6'>";
	html += "<label class='text-left labledesign'>Attachment</label> ";
	html += "<label class='text-right form-control' style='width: 250px; border:none; box-shadow:none; margin:0px'>";
	html += "<a href='http://localhost:8080/PRWSS_MIS/hallofFameAction.do?method=downloadAttachment&activityId="
			+ data.activityId
			+ " ' target='_blank' onclick='javascript: setTimeout(window.close, 1);'>"
			+ data.attachmentName + "</a></label>";
	html += "</div>";
	html += "<div class='col-lg-4 col-md-5 xs-hidden col-sm-6'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-4 col-md-5 xs-hidden col-sm-6'>&nbsp;&nbsp;</div>";
	html += "<div class='col-lg-2 col-md-1 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	document.getElementsByClassName("modal-body")[0].innerHTML = html;

}



var latrineUsageDt;
var latrineUsage = {
		latrineUsageFn : function() {
		latrineUsageDt = $("#latrineUsageDt")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3, 4]
									}
								},

								]
							} ],
							"language" : {
								"lengthMenu" : "Display _MENU_ records per page",
								"zeroRecords" : "Nothing found - sorry",
								"info" : "Showing page _PAGE_ of _PAGES_",
								"infoEmpty" : "No records available",
								"infoFiltered" : "(filtered from _MAX_ total records)"
							},
							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"scrollY" : "170px",
							"sScrollX" : true,
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'latrineUsageAction.do?method=getLatrineUsageDetails&d__mode="+d__mode+"&menuId=SNT009',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
							},

							],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "beneficiaryName",
								"defaultContent" : ""
							}, {
								"data" : "districtName",
								"defaultContent" : ""
							}, {
								"data" : "blockName",
								"defaultContent" : ""
							}, {
								"data" : "villageName",
								"defaultContent" : ""
							}

							]

						});
		$('.datatable').each(
				function() {
					var datatable = $(this);
					// SEARCH - Add the placeholder for Search and Turn this
					// into in-line form control
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					// LENGTH - Inline-Form control
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

	/*	$('#hallofFameDT tbody').on('click', '.resultd', function() {
			var data = hallofFame.row(this).data();
			hallofFameDetails.hallofFameDetail(data);
		});*/

	}
}

