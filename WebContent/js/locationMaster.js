var locationMaster1 = {

	location : function(url1) {
		var locationData = $("#locationMasterForm").serialize();
		$.ajax({
			type : "POST",
			url : url1,
			data : locationData,
			success : function(data) {
				showStickySuccessToast(data);
			},
			error : function(e) {
				showStickyErrorToast(e);
			}
		});
	}
}
var locationTable;
var locationMaster = {
	location1 : function() {
		locationTable = $("#location")
				.DataTable(
						{
							"language": {
					            "lengthMenu": "Display _MENU_ records per page",
					            "zeroRecords": "Nothing found - sorry",
					            "info": "Showing page _PAGE_ of _PAGES_",
					            "infoEmpty": "No records available",
					            "infoFiltered": "(filtered from _MAX_ total records)"
					        },
							 "scrollX": true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : true,
							"sScrollX": true,
							"scrollY" : "170px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'locationMasterAction.do?method=populate11&d__mode="+d__mode+"&menuId=ADM014',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
							},{
				                "targets": [ 7 ],
				                "visible": false,
				                "searchable": false
				            } ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '
							}, {
								"data" : "locationName",
								"defaultContent" : ""
							}, {
								"data" : "locationType",
								"defaultContent" : ""
							}, {
								"data" : "parentLocation",
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
							},
							{
								"data" : "locationId",
								"defaultContent" : ""
							}

							]

						});
		$('.datatable').each(function(){
			var datatable = $(this);
			// SEARCH - Add the placeholder for Search and Turn this into in-line form control
			var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
			search_input.attr('placeholder', 'Search');
			search_input.addClass('form-control input-sm');
			// LENGTH - Inline-Form control
			var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
			length_sel.addClass('form-control input-sm');
		});
		$('#location tbody').on('click', 'tr', function() {
			var datat = locationTable.row(this).data();
			LocationMasterData.populateMasterData(datat);
		});

	}

}

var LocationMasterData = {
	populateMasterData : function(datatff) {
		console.log(datatff);
		
		//document.getElementById('status').value = datatff.status;
			//document.getElementById('startDate').value = datatff.startDate;
			
			/*if(typeof datatff.endDate!='undefined'){
				document.getElementById('endDate').value = datatff.endDate;

			}*/
			/*if(typeof data.startDate!='undefined'){
				document.getElementById('startDate').value = data.startDate;
				}*/
		
		document.getElementById('locationId').value = datatff.locationId;

		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById('locationName').value = datatff.locationName;
		
		triggerEvent(document.getElementById('locationName'), 'onchange');
		document.getElementById('locationType').value = datatff.locationType;
		
		triggerEvent(document.getElementById('locationType'), 'onchange');
		document.getElementById('parentLocation').value = datatff.parentLocation;

		

		document.getElementById('locationType').value = datatff.locationType;
		document.getElementById('parentLocation').value = datatff.parentLocation;
		document.getElementById('locationName').value = datatff.locationName;



	}
}

// Location Type Master

var locTypeMaster;
var locationTypeMaster = {
	locationType : function() {
		locTypeMaster = $("#locationTypeDT")
				.DataTable(
						{
							"language": {
					            "lengthMenu": "Display _MENU_ records per page",
					            "zeroRecords": "Nothing found - sorry",
					            "info": "Showing page _PAGE_ of _PAGES_",
					            "infoEmpty": "No records available",
					            "infoFiltered": "(filtered from _MAX_ total records)"
					        },
							"deferRender" : true,
							fixedHeader : true,
							"bJQueryUI" : true,
							"bAutoWidth" : false,
							"sScrollX": "100%",

							"scrollY" : "170px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'locationTypeAction.do?method=populate11&d__mode="+d__mode+"&menuId=ADM015',
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
				                "targets": [ 5 ],
				                "visible": false,
				                "searchable": false
				            }],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '
									
							}, {
								"data" : "locationType",
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
							},
							{
								"data" : 'locationTypeId ',
									"defaultContent" : ""
							}]

						});
		$('.datatable').each(function(){
			var datatable = $(this);
			// SEARCH - Add the placeholder for Search and Turn this into in-line form control
			var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
			search_input.attr('placeholder', 'Search');
			search_input.addClass('form-control input-sm');
			// LENGTH - Inline-Form control
			var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
			length_sel.addClass('form-control input-sm');
		});
		$('#locationTypeDT tbody').on('click', 'tr', function() {
			var data = locTypeMaster.row(this).data();
			locationTypeData.populateLocationTypeData(data);
		});

	}
}
var locationTypeData = {
	populateLocationTypeData : function(data) {
		document.getElementById('locationType').value = data.locationType;
		if(typeof data.startDate!='undefined'){
		document.getElementById('startDate').value = data.startDate;
		}
		if(typeof data.endDate!='undefined'){
			document.getElementById('endDate').value = data.endDate;
		}
		document.getElementById('status').value = data.status;
		
		document.getElementById('locationTypeId').value = data.locationTypeId;

	}
}
