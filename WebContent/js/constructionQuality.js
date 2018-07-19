var monthlyPlanViewAction;
var monthlyPlan = {
		monthlyPlanView : function(yearlyPlan,month) {

		if ($.fn.dataTable.isDataTable('#monthlyPlanView')) {
			monthlyPlanViewAction.destroy();
		}
		monthlyPlanViewAction =
		$("#monthlyPlanView")
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
										columns : [ 0,1, 2, 3,4,5,6,7]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [0,1, 2, 3,4,5,6,7]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [0,1, 2, 3,4,5,6,7]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [0,1, 2, 3,4,5,6,7]
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
							'sAjaxSource' : 'monthlyPlanViewAction.do?yearlyPlan='
									+ yearlyPlan
									+ '&month='
									+ month
									+ '&method=populateMonthlyPlan',
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "component",
								"defaultContent" : ""
							}, {
								"data" : "visitPerMonth",
								"defaultContent" : ""
							}, {
								"data" : "villageToBeVisited",
								"defaultContent" : ""
							}, {
								"data" : "teamName",
								"defaultContent" : ""
							}, {
								"data" : "schemeName",
								"defaultContent" : ""
							} , {
								"data" : "district",
								"defaultContent" : ""
							}, {
								"data" : "division",
								"defaultContent" : ""
							}, {
								"data" : "constituency",
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



var monthlyReportView1;
var monthlyReport = {
		monthlyReportView : function() {
		if ($.fn.dataTable.isDataTable('#monthlyReportView')) {
			monthlyPlanViewAction.destroy();
		}
		monthlyReportView1 =
		$("#monthlyReportView")
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
										columns : [ 0,1, 2]
									}
								}, {
									extend : 'pdf',
									exportOptions : {
										columns : [0,1, 2]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [0,1, 2]
									}
								}, {
									extend : 'csv',
									exportOptions : {
										columns : [0,1, 2]
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
							'sAjaxSource' : 'monthlyReportViewAction.do?method=populateMonthlyReportPlan',
							columnDefs : [
											{
												"targets" : 0,
												className : 'financialYears',
												"data" : 'financialYear',
												"render" : function(data, type, row,
														meta) {
													return '<a href="approvedMonthlyReportAction.do?menuId=CQ011">'
															+ data + '</a>';
												}
											}, {
												"targets" : [ 3 ],
												"visible" : false,
												"searchable" : false

											},{
												"targets" : [ 4 ],
												"visible" : false,
												"searchable" : false

											},{
												"targets" : [ 5 ],
												"visible" : false,
												"searchable" : false

											} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [{
								"data" : "financialYear",
								"defaultContent" : ""
							}, {
								"data" : "month",
								"defaultContent" : ""
							}, {
								"data" : "yearlyPlanName",
								"defaultContent" : ""
							} , {
								"data" : "lyingWithUser",
								"defaultContent" : ""
							}, {
								"data" : "monthId",
								"defaultContent" : ""
							}, {
								"data" : "fnclYear",
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

		$('#monthlyReportView tbody').on('click','.financialYears',function() {
			var data = monthlyReportView1.row(this).data();
			localStorage.setItem("monthId",data.monthId);
			localStorage.setItem("fnclYear",data.fnclYear);
			localStorage.setItem("monthName",data.month);
			localStorage.setItem("financialYear",data.financialYear);
			localStorage.setItem("count",1);
		});
	}
}




