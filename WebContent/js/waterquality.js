/**
 * Dr. KD
 */
var parsed;
var labMaster;
var LabMaster = {
	LabMasterType : function() {
		labMaster = $("#labMasterDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [{
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3]
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
							"scrollX": true,
							"scrollY" : "200px",
							scrollCollapse: true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns:   {
								 leftColumns: 1,
						         rightColumns: 1
						        },
							'sAjaxSource' : 'labMasterAction.do?method=populateLabMaster&d__mode="+d__mode+"&menuId=WQ002',
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
								"targets" : [ 10 ],
								"visible" : false,
								"searchable" : false
							},
							{
								"targets" : [ 9 ],
								"visible" : false,
								"searchable" : false
							},
							{
								"targets" : [ 8 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 4 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 7 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 5 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 6 ],
								"visible" : false,
								"searchable" : false
							} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "labName",
								"defaultContent" : ""
							}, 
							{
								"data" : "districtName",
								"defaultContent" : ""
							}, 
							{
								"data" : "blockName",
								"defaultContent" : ""
							}
							, {
								"data" : "villageId",
								"defaultContent" : ""
							}, {
								"data" : "startDate",
								"defaultContent" : ""
							}, {
								"data" : "endDate",
								"defaultContent" : ""
							}, {
								"data" : "status",
								"defaultContent" : ""
							}, {
								"data" : "labId",
								"defaultContent" : ""
							},{
								"data" : "district",
								"defaultContent" : ""
							},
							{
								"data" : "block",
								"defaultContent" : ""
							}]

						})
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

		$('#labMasterDT tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify') {
				var data = labMaster.row(this).data();
				labMasterData.populateLabTypeData(data);
			}
		});
	}
}
var labMasterData = {
	populateLabTypeData : function(data) {

		document.getElementById('labId').value = data.labId;

		document.getElementById('labName').value = data.labName;

		document.getElementById('district').value = data.district;
			triggerEvent(document.getElementById('district'), 'onchange');
			document.getElementById('block').value = data.block;
			
			document.getElementById('division').value = data.division;
		
		/*triggerEvent(document.getElementById('block'), 'onchange');
		document.getElementById('villageId').value = data.villageId;*/
		document.getElementById('phoneNo').value = data.phoneNo;
		document.getElementById('address').value = data.address;
		document.getElementById('labLevel').value = data.labLevel;
		document.getElementById('contactPerson').value = data.contactPerson;
		document.getElementById('mobileNo').value = data.mobileNo;
		
		document.getElementById('status').value = data.status;

		if (typeof data.startDate != 'undefined') {
			document.getElementById('startDate').value = data.startDate;
		}
		if (typeof data.endDate != 'undefined') {
			document.getElementById('endDate').value = data.endDate;

		}

	}
}


var FreezeSampleAction;
var freezeSample = {
		freezeSampleView : function(lab,fromDate,toDate) {

		if ($.fn.dataTable.isDataTable('#FreezeSampleView')) {
			FreezeSampleAction.destroy();
		}
		FreezeSampleAction =
		$("#FreezeSampleView")
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
										columns : [ 0]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [0]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [0]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [0]
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
							'sAjaxSource' : 'freezeUnFreezeAction.do?lab='
									+ lab
									+ '&fromDate='
									+ fromDate
									+ '&toDate='
									+toDate
									+ '&method=populateSampleDetails',
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "sampleNumber",
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

		
	}
}


var sampleCollection;
var ReceiveSample = {
	sample : function() {

		sampleCollection1 = $("#sampleCollection")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}

								]
							} ],
							responsive : true,
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
							"sScrollX" : false,

							"scrollY" : "170px",
							scrollCollapse : true,
							"bProcessing" : false,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'receiveSampleAction.do?method=populateSampleCollection&d__mode="+d__mode+"&menuId=WQ002',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								},
							}, {
								"targets" : [ 4 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 5 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 6 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 7 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 8 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 9 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 10 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 11 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 12 ],
								"visible" : false,
								"searchable" : false
							},

							{
								"targets" : [ 13 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 14 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 15 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 16 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 17 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 18 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 19 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 20 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 21 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 22 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 23 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 24 ],
								"visible" : false,
								"searchable" : false
							} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "sampleNumber",
								"defaultContent" : ""
							}, {
								"data" : "labName",
								"defaultContent" : ""
							}, {
								"data" : "waterSourceName",
								"defaultContent" : ""
							}, {
								"data" : "lab",
								"defaultContent" : ""
							}, {
								"data" : "waterSource",
								"defaultContent" : ""
							}, {
								"data" : "habitation",
								"defaultContent" : ""
							}, {
								"data" : "mobileNo",
								"defaultContent" : ""
							},

							{
								"data" : "district",
								"defaultContent" : ""
							}, {
								"data" : "block",
								"defaultContent" : ""
							}, {
								"data" : "villageId",
								"defaultContent" : ""
							}, {
								"data" : "gramPanchayat",
								"defaultContent" : ""
							}, {
								"data" : "schemeName",
								"defaultContent" : ""
							}, {
								"data" : "collectionType",
								"defaultContent" : ""
							}, {
								"data" : "testType",
								"defaultContent" : ""
							}, {
								"data" : "poiType",
								"defaultContent" : ""
							}, {
								"data" : "createdBy",
								"defaultContent" : ""
							}, {
								"data" : "email",
								"defaultContent" : ""
							}, {
								"data" : "sampleId",
								"defaultContent" : ""
							}, {
								"data" : "landMark",
								"defaultContent" : ""
							}, {
								"data" : "letterRefNum",
								"defaultContent" : ""
							}, {
								"data" : "quantity",
								"defaultContent" : ""
							}, {
								"data" : "depth",
								"defaultContent" : ""
							}, {
								"data" : "rural_urban",
								"defaultContent" : ""
							}, {
								"data" : "sampleotherdetails",
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

		$('#sampleCollection tbody').on('click', '.select-checkbox',
				function() {
					if (d__mode == 'ent_modify' || d__mode == 'ent_qrcode') {
						var data1 = sampleCollection1.row(this).data();

						collectionData.populateCollectionData(data1);
					}
				});

	}
}
var collectionData = {
	populateCollectionData : function(data) {
		if (d__mode == 'ent_qrcode') {
			document.getElementById('sampleNumber').readOnly = true;
		}
		document.getElementById('lab').value = data.lab;
		document.getElementById('collectionType').value = data.collectionType;
		document.getElementById('landMark').value = data.landMark;

		if (data.collectionType != '') {
			if (data.collectionType === '1') {
				triggerEvent(document.getElementById('collectionType'),
						'onchange');
				document.getElementById('designation').value = data.designation;

				triggerEvent(document.getElementById('designation'), 'onchange');
				document.getElementById('collectedBy').value = data.collectedBy;

				//triggerEvent(document.getElementById('collectedBy'), 'onchange');
				//Receivesample.collectionSample();
				document.getElementById('mobileNoDwss').value = data.mobileNo;

				document.getElementById('emailDwss').value = data.email;

			}
			if (data.collectionType === '2') {
				triggerEvent(document.getElementById('collectionType'),
						'onchange');

				document.getElementById('mobileNo').value = data.mobileNo;

				document.getElementById('email').value = data.email;
				document.getElementById('poiId').value = data.poiId;
				document.getElementById('poiType').value = data.poiType;
			}

			if (data.urban === '0') {
				document.getElementById("checkurban").checked = true;
				document.getElementById("checkrural").checked = false;
				if(document.getElementById("city").value===''){
					document.getElementById("city").value = data.city;
				}else{
					document.getElementById("city").value = data.city;
				}
				
				document.getElementById("cityDiv").style.visibility = 'visible'; 
				document.getElementById("villageHavitation").style.visibility = 'hidden';
				document.getElementById("urban_schemeDetails").style.display = 'block';
				document.getElementById("rural_schemeDetails").style.display = 'none';
				document.getElementById("villageHavitation").value = '';
				document.getElementById('district').value = data.district;

				triggerEvent(document.getElementById('district'), 'onchange');
				document.getElementById('block').value = data.block;
				
				document.getElementById("snu").style.display = 'none';
				document.getElementById("snu").value = ""; 
				document.getElementById("waterSourceUrban").value = data.waterSource; 
				//data.waterSource
				

			} else {
				document.getElementById("checkurban").checked = false;
				document.getElementById("checkrural").checked = true;
				document.getElementById("city").value = "";
				document.getElementById("cityDiv").style.visibility = 'hidden';
				
				document.getElementById("villageHavitation").style.visibility = 'visible';
				document.getElementById("urban_schemeDetails").style.display = 'none';
				document.getElementById("rural_schemeDetails").style.display = 'block';
				
				document.getElementById('district').value = data.district;

				triggerEvent(document.getElementById('district'), 'onchange');
				document.getElementById('block').value = data.block;

				triggerEvent(document.getElementById('block'), 'onchange');
				document.getElementById('villageId').value = data.villageId;
				triggerEvent(document.getElementById('villageId'), 'onchange');
				document.getElementById('schemeName').value = data.schemeName;
				triggerEvent(document.getElementById('schemeName'), 'onchange');
				
				document.getElementById('waterSource').value = data.waterSource;
				
				
				
			}

		}

		// enteredBy
		document.getElementById('sampleId').value = data.sampleId;

		console.log("data.data.collDate-------------" + data.collDate);
		// alert(data.destrict);
		

		document.getElementById('testType').value = data.testType;
		document.getElementById('sampleNumber').value = data.sampleNumber;
		document.getElementById('letterRefNum').value = data.letterRefNum;
		document.getElementById('depth').value = data.depth;
		document.getElementById('quantity').value = data.quantity;
		document.getElementById('bottleType').value = data.bottleType;
		document.getElementById('bottleType').value = data.bottleType;
		document.getElementById('sampleotherdetails').value = data.sampleotherdetails;
		// sampleotherdetails

		if (typeof data.collDate != 'undefined') {
			document.getElementById('collDate').value = data.collDate;

		}

	}
}

// to fetch mobile no and emailid

// to fetch mobile no and emailid

var Receivesample = {

	collectionSample : function() {
		// alert('hiihiii');
		var locationData = $("#collectedBy").serialize();

		console.log("locationDatalocationDatalocationData----------"
				+ locationData);
		$
				.ajax({
					type : "POST",
					url : "receiveSampleAction.do?method=fetchMobileNoAndEmailId&d__mode="
							+ d__mode + "&menuId=WQ002",
					data : locationData,
					success : function(data) {
						setMobileAndEmail(data);
					},
					error : function(e) {
						// showStickyErrorToast(e);
					}
				});
	}
}

function setMobileAndEmail(data) {
	// alert(data);
	var dataArr = data.split(',');
	$("#emailId").text(dataArr[0]);
	document.getElementById('emailDwss').value = dataArr[0];
	$("#mobileNo1").text(dataArr[1]);
	document.getElementById('mobileNoDwss').value = dataArr[1];
}

// Sample distribution

var sampleDistribution;
var SampleDistribution = {
	SampleDistributionType : function() {
		sampleDistribution = $("#sampleDistributionDT")
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
							responsive : true,
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
							"sScrollX" : false,

							"scrollY" : "170px",
							scrollCollapse : true,
							"bProcessing" : false,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'sampleDistributionAction.do?method=populateSampleDistribution&d__mode="+d__mode+"&menuId=WQ003',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
							}, {
								"targets" : [ 5 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 6 ],
								"visible" : false,
								"searchable" : false
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
								"data" : 'render '

							}, {
								"data" : "sampleNum",
								"defaultContent" : ""
							}, {
								"data" : "samplePartNum",
								"defaultContent" : ""
							}, {
								"data" : "labName",
								"defaultContent" : ""
							}, {
								"data" : "tests",
								"defaultContent" : ""
							}, {
								"data" : "distributionDate",
								"defaultContent" : ""
							}, {
								"data" : "requiredBy",
								"defaultContent" : ""
							},

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
		$('#sampleDistributionDT tbody').on('click', '.select-checkbox',
				function() {
					if (d__mode == 'ent_modify'|| d__mode == 'ent_qrcode') {

						var data = sampleDistribution.row(this).data();
						labMasterData2.populateLabTypeData(data);
					}
				});

	}
}
var labMasterData2 = {

	populateLabTypeData : function(data) {

		

		document.getElementById('samplePartNum').value = data.samplePartNum;

		document.getElementById('labName').value = data.labId;
		document.getElementById('distributionId').value = data.distributionId;

		triggerEvent(document.getElementById('labName'), 'onchange');
		document.getElementById('sampleNum').value = data.sampleNum;
		document.getElementById('technician').value = data.technician;

		document.getElementById('tests').value = data.tests;

		//document.getElementById('locationname').value = data.locationName;

		document.getElementById('distributionDate').value = data.distributionDate;
		//document.getElementById('requiredBy').value = data.requiredBy;
		//document.getElementById('locationname').value = data.locationName;
		document.getElementById('sampleType').value = data.sampleType;
		

	}
}
var resultEntry1;
var ResultEntryAction = {
	ResultEntryAct : function() {
		// alert('sdfdss');
		resultEntry1 = $("#resultEntry")
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
										columns : [ 1, 2]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2]
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
							"scrollY" : "170px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'resultEntryAction.do?method=resultEntry&d__mode="+ d__mode + "&menuId=WQ002',
							columnDefs : [
									{
										orderable : false,
										className : 'select-checkbox',
										targets : 0,
										"orderable" : false,
										'render' : function(data, type, full,
												meta) {
											return '';
										}
									},

									{
										"targets" : 1,
										className : 'resultd',
										"data" : 'samplenumber',
										"render" : function(data, type, row,
												meta) {
											return '<a href="resultDisplayAction.do?menuId=WQ005">'
													+ data + '</a>';
										}
									}, {
										"targets" : [ 4 ],
										"visible" : false,
										"searchable" : false

									}, {
										"targets" : [ 3 ],
										"visible" : false,
										"searchable" : false

									} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "samplepartno",
								"defaultContent" : ""
							}, {
								"data" : "completionDate",
								"defaultContent" : ""
							},{
								"data" : "sampleid",
								"defaultContent" : ""
							}, {
								"data" : "testResultId",
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
		$('#resultEntry tbody').on('click', '.resultd', function() {
			var data = resultEntry1.row(this).data();
			localStorage.setItem("resultEntry",data.testResultId);
			localStorage.setItem("requestlevel",data.requestlevel);
			localStorage.setItem("lyingWithUser",data.lyingWithUser);
			localStorage.setItem("resultEntry11",data.testResultId);
		});
		$('#resultEntry tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify') {
				var data = resultEntry1.row(this).data();
				updateResult.updateResultEntry(data);
			}
		});

	}
}
var updateResult = {
	updateResultEntry : function(data) {
		document.getElementById('comDate').value = data.completionDate;
		document.getElementById('resultEntryId').value = data.testResultId;

		document.getElementById('labname').value = data.labId;
		
		triggerEvent(document.getElementById('labname'), 'onblur');
		document.getElementById('partno').value = data.samplepartno;
		
		triggerEvent(document.getElementById('partno'), 'onchange');
		

	}
}

// update level and lying with user of result entry

var resultEntry = {
	updateResultEntry : function(e) {
		event.preventDefault();
		var resultEntry = localStorage.getItem("resultEntry11");
		var requestlevel = localStorage.getItem("requestlevel");
		
		var lyingWithUser = localStorage.getItem("lyingWithUser");
		
		//var parsed = JSON.parse(foo);
		var comments;

		var comments = document.getElementById('comment').value;
		$
				.ajax({
					type : "POST",
					url : "resultDisplayAction.do?method=updateTestEntryResult&d__mode="
							+ d__mode + "&menuId=WQ005",
					data : {
						testResultId : resultEntry,
						requestLevel : requestlevel,
						comments : comments,
						flow : e
					},
					success : function(data) {
						alert(data)
						// alert(parsed[0]);
						// alert('before ----'+parsed[0].lyingWithUser);
						if (lyingWithUser == 0) {
							// alert('inside');
							var successUrl = "resultEntryAction.do?menuId=WQ004";
							window.location.href = successUrl;
						} else {
							var successUrl = "testResultEntryAR.do?menuId=WQ007";
							window.location.href = successUrl;
						}

					},
					error : function(error) {
					}

				});
	}

}

/**
 * Parameter Master
 */
var parameterDT1;
var parameterMaster = {
	parameter1 : function() {
		parameterDT1 = $("#parameterDT")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [ /*{
									extend : 'copy',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								},*/ {
									extend : 'pdf',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								},/* {
									extend : 'csv',
									exportOptions : {
										columns : [ 1, 2, 3, 4 ]
									}
								},*/

								]
							} ],
							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"sScrollX" : true,
							"scrollY" : "230px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'parameterMasterAction.do?method=populate11&d__mode="+d__mode+"&menuId=WQ006',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
							}, {
								"targets" : [ 5 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 6 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 7 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 8 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 9 ],
								"visible" : false,
								"searchable" : false
							} , {
								"targets" : [ 10 ],
								"visible" : false,
								"searchable" : false
							} ],

							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "parameterName",
								"defaultContent" : ""
							}, {
								"data" : "uom",
								"defaultContent" : ""
							}, {
								"data" : "acceptableLimit",
								"defaultContent" : ""
							}, {
								"data" : "permissibleLimit",
								"defaultContent" : ""
							}, {
								"data" : "wHOPermissibleLimit",
								"defaultContent" : ""
							}, {
								"data" : "wHOAcceptableLimit",
								"defaultContent" : ""
							}, {
								"data" : "status",
								"defaultContent" : ""
							}, {
								"data" : "startDate",
								"defaultContent" : ""
							}, {
								"data" : "endDate",
								"defaultContent" : ""
							}, {
								"data" : "uom1",
								"defaultContent" : ""
							} ]

				});
		
		 $('#parameterDT tbody').on('click', '.select-checkbox', function() {
			  if (d__mode == 'ent_modify') 
				var data = parameterDT1.row(this).data();
				parameterMasterDatas.populateParameterMasterData(data);
		});
	}
}

var parameterMasterDatas = {
	populateParameterMasterData : function(data) {
		document.getElementById('parameterId1').value = data.parameterName;
		document.getElementById('uom').value = data.uom1;
		document.getElementById('permissibleLimit').value = data.permissibleLimit;
		document.getElementById('acceptableLimit').value = data.acceptableLimit;
		/*document.getElementById('wHOPermissibleLimit').value = data.wHOPermissibleLimit;
		document.getElementById('wHOAcceptableLimit').value = data.wHOAcceptableLimit;*/
		document.getElementById('status').value = data.status;
		if (typeof data.startDate != 'undefined') {
			document.getElementById('startDate').value = data.startDate;
		}
		if (typeof data.endDate != 'undefined' && data.endDate!=null) {
			document.getElementById('endDate').value = data.endDate;
		}else{
			document.getElementById('endDate').value = '';
		}
	}
}

