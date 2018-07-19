var divisionWisePlanView1; 
var divisionWisePlanView = {
		divisionWisePlanView_Type : function(financialYear, division) {

		if ($.fn.dataTable.isDataTable('#divisionWisePlanViewDT')) {
			divisionWisePlanView1.destroy();
		}
		divisionWisePlanView1 =$("#divisionWisePlanViewDT")
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
										columns : [ 0, 1, 2, 3]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 0, 1, 2, 3]
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
							"scrollY" : "400px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,	
							'sAjaxSource' : 'divisionWisePlanViewAction.do?financialYear='+financialYear+ '&division='+division+'&method=populatePlanData',
							columnDefs : [ {
								"targets" : 0,
								className : 'villageName',
								"data" : 'villageName',
								"defaultContent": "-", 
								"targets": "_all", 
								"render" : function(data, type, row, meta) {
									return data;
								}
							}, {
								"targets" : [ 4 ],
								"visible" : false,
								"searchable" : false
							} ],
							
							"columns" : [ {
								"data" : "categoryName",
								"defaultContent" : ""
							}, {
								"data" : "stageName",
								"defaultContent" : ""
							}, {
								"data" : "componentName",
								"defaultContent" : ""
							}, {
								"data" : "villageName",
								"defaultContent" : ""
							},{
								"data" : "activityVillageId",
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

		$('#divisionWisePlanViewDT tbody').on('click', '.villageName', function() {
			var data = divisionWisePlanView1.row(this).data();
			activityDetails.planDetail(data.activityVillageId);
			console.warn("ID"+ data.activityVillageId);
		});
	}
}

var activityDetails = {
		planDetail : function(data) {
			$.ajax({
						type : "POST",
						url : "divisionWisePlanViewAction.do?method=getPlanDetails&d__mode="
								+ d__mode + "&menuId=SDU006",
						data : {
							activityVillageId : data
						},
						success : function(data) {
							var parsed = JSON.parse(data);
							showActivity(parsed);
							//displayRtiUDetails(parsed);
							$("#myModal").modal('show');
							// event.preventDefault();
						},
						error : function(error) {

						}
					});

		}
	}

function showActivity(data){
	var html = "";
	html += "<div class='panel panel-danger' style='border: none; box-shadow: none'>";
	html += "<div class='panel-body'>";
	html += "<div>";
	
	html += "<div class='bg-success  col-lg-12 col-md-12'>";
	html += "<div class='form-inline col-lg-4 col-md-4 col-xs-12 col-sm-12 bg-success' >";
	html += "<label class='labledesign text-center' style='font-size: 1.1em;'>Campaign</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-4 col-md-4 col-xs-12 col-sm-12'>";
	html += "<label class=' labledesign text-center' style='font-size: 1.1em;'>Activities</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
	html += "<label class='text-center labledesign' style='font-size: 1.1em;'>Expected Start Date</label>";
	html += "</div>";
	html += "<div class='form-inline col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
	html += "<label class='text-center labledesign' style='font-size: 1.1em;'>Expected End Date</label>";
	html += "</div>";
	html += "</div>";
	
			for(var i in data){
				
				html += "<div class=' col-lg-4 col-md-4 col-xs-12 col-sm-12'>";
				html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].campaignName+"</text:area>";
				html += "</div>";
				html += "<div class=' col-lg-4 col-md-4 col-xs-12 col-sm-12'>";
				html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].activityName+"</text:area>";
				html += "</div>";
				html += "<div class=' col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
				html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].startDate1+"</text:area>";
				html += "</div>";
				html += "<div class=' col-lg-2 col-md-2 col-xs-12 col-sm-12'>";
				html += "<text:area class='text-justify ' style='border:none; box-shadow:none; margin:0px'>"+data[i].endDate1+"</text:area>";
				html += "</div>"; 
				html += "<div class=' col-lg-12 col-md-12 col-xs-12 col-sm-12'>&nbsp;</div>";
			}
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	html += "<div class='modal-footer' style='margin:auto'>";
	html += "<button type='button' class='btn btn-danger col-lg-4 ' data-dismiss='modal'>Close</button>";
	html += "</div>";
	document.getElementsByClassName("modal-body")[0].innerHTML = html;

}

