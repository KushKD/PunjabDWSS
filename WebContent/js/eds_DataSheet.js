/**
 * Dr. KD
 */
var edsMaster;
var EdsMaster = {
		EdsMasterType : function() {
		edsMaster = $("#edsDT")
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
								columns : [ 1, 2, 3,4,5]
							}
						}, {
							extend : 'excel',
							exportOptions : {
								columns : [ 1, 2, 3,4,5]
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
							'sAjaxSource' : 'environmentDataCollectionAction.do?method=populateDataEDS',
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

							},  
							{
								"data" : "schemeName",
								"defaultContent" : ""
							}, 
							{
								"data" : "schemeTypeName",
								"defaultContent" : ""
							}
							, {
								"data" : "districtName",
								"defaultContent" : ""
							},
							{
								"data" : "blockName",
								"defaultContent" : ""
							},
							{
								"data" : "villageName",
								"defaultContent" : ""
							}]

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

		$('#edsDT tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify') {
				var data = edsMaster.row(this).data();
				edsMasterData.populateEdsTypeData(data);
			}
		});
	}
};

var edsMasterData = {
		populateEdsTypeData : function(data) {
			
			document.getElementById('district').value = data.district;
			triggerEvent(document.getElementById('district'), 'onchange');
			
			document.getElementById('block').value = data.block;
			triggerEvent(document.getElementById('block'), 'onchange');
			
			document.getElementById('villageId').value = data.villageId;
			triggerEvent(document.getElementById('villageId'), 'onchange');
			
			document.getElementById('vil').value = data.vi;
			triggerEvent(document.getElementById('vil'), 'onchange');
			
			document.getElementById('zone').value = data.zone;
			
			document.getElementById('schemeType').value = data.schemeType;
			
			document.getElementById('schemeCategory').value = data.schemeCategory;
			
			document.getElementById('schemeId').value = data.schemeId;
			
			document.getElementById('edsId').value = data.edsId;
			//alert("Done");
		    /*  localStorage.setItem("edsId",  data.edsId);
				var sucessurl = "environmentDataCollectionBaseLineEnvironmentAction.do";
				window.location.href = sucessurl;*/
	}
}