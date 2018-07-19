var financeHeads;
var financeHead = {
	financeHeadType : function() {
		financeHeads = $("#financeHeadDT")
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'financialHeadsAction.do?method=populateFinanceHead&d__mode="+d__mode+"',
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
							} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "head_type",
								"defaultContent" : ""
							}, {
								"data" : "headName",
								"defaultContent" : ""
							}, {
								"data" : "headNumber",
								"defaultContent" : ""
							}, {
								"data" : "parent_head_name",
								"defaultContent" : ""
							}, {
								"data" : "headId",
								"defaultContent" : ""
							}, {
								"data" : "headType",
								"defaultContent" : ""
							}, {
								"data" : "parent_head_id",
								"defaultContent" : ""
							} ]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

		$('#financeHeadDT tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify') {
				var data = financeHeads.row(this).data();
				financeHeadss.populateHeadTypeData(data);
			}
		});
	}
}

var financeHeadss = {
	populateHeadTypeData : function(data) {
		document.getElementById('headType').value = data.headType;
		triggerEvent(document.getElementById('headType'), 'onchange');
		document.getElementById('description').value = data.headName;
		document.getElementById('number').value = data.headNumber;
		document.getElementById('parent').value = data.parent_head_id;
		document.getElementById('financeHeadId').value = data.headId;
	}
}

var financeHeadStructure1;
var financeHeadStr = {
	financeHeadStructure : function() {
		financeHeadStructure1 = $("#financeHeadDT")
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
										columns : [ 1, 2, 3 ]
									}
								}, {
									extend : 'excel',
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'financialHeadStructureAction.do?method=populateFinanceStructureHead&d__mode="+d__mode+"',
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
							}, {
								"targets" : [ 13 ],
								"visible" : false,
								"searchable" : false
							}, {
								"targets" : [ 14 ],
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
								"data" : "headStructureName",
								"defaultContent" : ""
							}, {
								"data" : "financialYear",
								"defaultContent" : ""
							}, {
								"data" : "Hoa",
								"defaultContent" : ""
							}, {
								"data" : "financialYears",
								"defaultContent" : ""
							}, {
								"data" : "demandNumber",
								"defaultContent" : ""
							}, {
								"data" : "majorHeads",
								"defaultContent" : ""
							}, {
								"data" : "subMajorHeads",
								"defaultContent" : ""
							}, {
								"data" : "minorHeads",
								"defaultContent" : ""
							}, {
								"data" : "subHeads",
								"defaultContent" : ""
							}, {
								"data" : "detailedHead",
								"defaultContent" : ""
							}, {
								"data" : "soeNumber",
								"defaultContent" : ""
							}, {
								"data" : "planNonplan",
								"defaultContent" : ""
							}, {
								"data" : "votedChargeds",
								"defaultContent" : ""
							}, {
								"data" : "headStructureId",
								"defaultContent" : ""
							} ]

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

		$('#financeHeadDT tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify') {
				var data = financeHeadStructure1.row(this).data();
				financeHeadStructure.populateHeadStructureData(data);
			}
		});

	}
}

var financeHeadStructure = {
	populateHeadStructureData : function(data) {
		document.getElementById('description').value = data.headStructureName;
		document.getElementById('financialYear').value = data.financialYears;
		document.getElementById('dimandNo').value = data.demandNumber;
		triggerEvent(document.getElementById('dimandNo'), 'onchange');

		document.getElementById('majorHead').value = data.majorHeads;
		triggerEvent(document.getElementById('majorHead'), 'onchange');

		document.getElementById('subMajorHead').value = data.subMajorHeads;
		triggerEvent(document.getElementById('subMajorHead'), 'onchange');

		document.getElementById('minorHead').value = data.minorHeads;
		triggerEvent(document.getElementById('minorHead'), 'onchange');

		document.getElementById('subHead').value = data.subHeads;
		triggerEvent(document.getElementById('subHead'), 'onchange');

		document.getElementById('detailedHead').value = data.detailedHead;
		triggerEvent(document.getElementById('detailedHead'), 'onchange');

		document.getElementById('SOE').value = data.soeNumber;
		triggerEvent(document.getElementById('SOE'), 'onchange');

		document.getElementById('planNonPlan').value = data.planNonplan;
		triggerEvent(document.getElementById('planNonPlan'), 'onchange');

		document.getElementById('votedCharged').value = data.votedChargeds;
		document.getElementById('headStructureId').value = data.headStructureId;
	}

}
var financeComponents;
var component = {
	financeComponent : function() {
		financeComponents = $("#componentDT")
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
										columns : [ 1, 2, 3 ]
									}
								}, {
									extend : 'excel',
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'componentAction.do?method=populateComponentDetails&d__mode="+d__mode+"',
							columnDefs : [ {
								orderable : false,
								className : 'select-checkbox',
								targets : 0,
								"orderable" : false,
								'render' : function(data, type, full, meta) {
									return '';
								}
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
							} ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "componentName",
								"defaultContent" : ""
							}, {
								"data" : "componentType",
								"defaultContent" : ""
							}, {
								"data" : "parentComponentName",
								"defaultContent" : ""
							}, {
								"data" : "description",
								"defaultContent" : ""
							}, {
								"data" : "componentId",
								"defaultContent" : ""
							}, {
								"data" : "componentDetailsId",
								"defaultContent" : ""
							}, {
								"data" : "parentCompoId",
								"defaultContent" : ""
							}, {
								"data" : "componentTypes",
								"defaultContent" : ""
							}, {
								"data" : "activeFlag",
								"defaultContent" : ""
							} ]

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
		$('#componentDT tbody').on('click', '.select-checkbox', function() {
			if (d__mode == 'ent_modify') {
				var data = financeComponents.row(this).data();
				financeComponentss.populateComponent(data);
			}
		});

	}
}

var financeComponentss = {
	populateComponent : function(data) {
		document.getElementById('componentName').value = data.componentName;
		document.getElementById('componentType').value = data.componentTypes;
		triggerEvent(document.getElementById('componentType'), 'onchange');
		document.getElementById('parentComponent').value = data.parentCompoId;
		document.getElementById('description').value = data.description;
		document.getElementById('componentId').value = data.componentId;
		document.getElementById('componentDetailsId').value = data.componentDetailsId;
		document.getElementById('status').value = data.activeFlag;

	}
}

var financeDivisionBudget;
var divisionBudget = {
	divisionBudgetFun : function() {
		financeDivisionBudget = $("#divisionBudgetDT")
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
										columns : [ 1, 2, 3 ]
									}
								}, {
									extend : 'excel',
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'divisionBudgetAction.do?method=populateBudgetDivisionDetails&d__mode="+d__mode+"',
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
							}, {
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
							}  ],
							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : 'render '

							}, {
								"data" : "financialYear",
								"defaultContent" : ""
							}, {
								"data" : "componentName",
								"defaultContent" : ""
							}, {
								"data" : "division",
								"defaultContent" : ""
							}, {
								"data" : "budgetRefNo",
								"defaultContent" : ""
							}, {
								"data" : "estimatedCost",
								"defaultContent" : ""
							}, {
								"data" : "reqNxtYear",
								"defaultContent" : ""
							}, {
								"data" : "alreadySpent",
								"defaultContent" : ""
							}, {
								"data" : "qtr1",
								"defaultContent" : ""
							}, {
								"data" : "qtr1",
								"defaultContent" : ""
							}, {
								"data" : "qtr2",
								"defaultContent" : ""
							}, {
								"data" : "qtr3",
								"defaultContent" : ""
							}, {
								"data" : "qtr4",
								"defaultContent" : ""
							}, {
								"data" : "componentId",
								"defaultContent" : ""
							}, {
								"data" : "finYear",
								"defaultContent" : ""
							}, {
								"data" : "divisionId",
								"defaultContent" : ""
							}, {
								"data" : "schemeId",
								"defaultContent" : ""
							}, {
								"data" : "subComponentId",
								"defaultContent" : ""
							}, {
								"data" : "subComponentLevel3Id",
								"defaultContent" : ""
							}, {
								"data" : "activityId",
								"defaultContent" : ""
							}, {
								"data" : "subSubComponentId",
								"defaultContent" : ""
							},{
								"data":"divAnnBudgId",
								"defaultContent" : ""
							} ]

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
		$('#divisionBudgetDT tbody').on('click', '.select-checkbox',
				function() {
					if (d__mode == 'ent_modify') {
						var data = financeDivisionBudget.row(this).data();
						populateDivisionBudget(data);
					}
				});

	}
}

	function populateDivisionBudget(data) {
	
		document.getElementById('financialYear').value=data.finYear;
		document.getElementById('division').value=data.divisionId;
		
		triggerEvent(document.getElementById('division'), 'onchange');
		document.getElementById('scheme').value=data.schemeId;
		
		document.getElementById('component').value=data.componentId;
		
		triggerEvent(document.getElementById('component'), 'onchange');
		document.getElementById('subComponent').value=data.subComponentId;
		document.getElementById('activity').value=data.activityId;
		
		triggerEvent(document.getElementById('subComponent'), 'onchange');
		document.getElementById('subSubComponent').value=data.subSubComponentId;
		
		triggerEvent(document.getElementById('subSubComponent'), 'onchange');
		document.getElementById('subSubComponentLevel3').value=data.subComponentLevel3Id;
		
		triggerEvent(document.getElementById('subSubComponentLevel3'), 'onchange');
		document.getElementById('activity').value=data.activityId;
		
		/*
		 * triggerEvent(document.getElementById('component'), 'onchange');
		 * document.getElementById('activity').value=data.activityId;
		 */
		
		
		document.getElementById('estimatedCosts').value=data.estimatedCost;
		document.getElementById('alreadyExpended').value=data.alreadySpent;
		document.getElementById('nextYearReq').value=data.reqNxtYear;
		document.getElementById('quarter1').value=data.qtr1;
		document.getElementById('quarter2').value=data.qtr2;	
		document.getElementById('quarter3').value=data.qtr3;
		document.getElementById('quarter4').value=data.qtr4;
		document.getElementById('divAnnBudgId').value=data.divAnnBudgId;
	}

var budgetGrid = {
	budgetGridView : function() {
		$("#budgetView")
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
										columns : [ 0, 1, 2,3 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 0, 1, 2,3 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'viewDivisionBudgetAction.do?method=populateBudget&d__mode="+d__mode+"',

							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "budgetRefNo",
								"defaultContent" : ""
							}, {
								"data" : "financialYear",
								"defaultContent" : ""
							}, {
								"data" : "division",
								"defaultContent" : ""
							}, {
								"data" : "forward",
								"defaultContent" : ""
							} ]

						});
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

	}
}

var divisionBudgedView = {
	divisionBudgedViews : function(division, finance) {

		$("#divisionBudgetView")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [
										{
											extend : 'pdf',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4, 5,
														6, 7, 8, 9, 10 ]
											}
										},
										{
											extend : 'excel',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4, 5,
														6, 7, 8, 9, 10 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'divisionBudgetViewAction.do?division='
									+ division
									+ '&finance='
									+ finance
									+ '&method=populateDivisionBudget&d__mode="+d__mode+"',

							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "componentName",
								"defaultContent" : ""
							}, {
								"data" : "subComponentName",
								"defaultContent" : ""
							}, {
								"data" : "activityComponentName",
								"defaultContent" : ""
							}, {
								"data" : "subSubComponentName",
								"defaultContent" : ""
							}, {
								"data" : "estimatedCost",
								"defaultContent" : ""
							}, {
								"data" : "reqNxtYear",
								"defaultContent" : ""
							}, {
								"data" : "alreadySpent",
								"defaultContent" : ""
							}, {
								"data" : "qtr1",
								"defaultContent" : ""
							}, {
								"data" : "qtr2",
								"defaultContent" : ""
							}, {
								"data" : "qtr3",
								"defaultContent" : ""
							}, {
								"data" : "qtr4",
								"defaultContent" : ""
							} ]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

	}
}
var nodalDivision;
var nodalDivisionBudgedView = {
	divisionBudgedViews1 : function(division, finance) {
		nodalDivision=$("#nodalDivisionBudgetView")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [
										{
											extend : 'pdf',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4, 5,
														6, 7, 8, 9, 10 ]
											}
										},
										{
											extend : 'excel',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4, 5,
														6, 7, 8, 9, 10 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns:0,
								rightColumns : 1
							},
							
							'sAjaxSource' : 'divisionBudgetViewAction.do?division='
									+ division
									+ '&finance='
									+ finance
									+ '&divisionType=nodal&method=populateDivisionBudget&d__mode="+d__mode+"',

									columnDefs : [
													{
														"targets" : 19,
														className : 'divAnnBudgId',
														"data" : 'divAnnBudgId',
														mRender: function (data, type, row) {
											                return '<a class="table-edit" data-id="' + row[0] + '" href="updateBudgetAction.do?menuId=FN014&divAnnBudgt='+data+'" ><button type="button" class="btn btn-info">EDIT</button></a>'
											            }
													}, {
														"targets" : [ 11 ],
														"visible" : false,
														"searchable" : false
													}, {
														"targets" : [ 12 ],
														"visible" : false,
														"searchable" : false
													}, {
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
													}],
													select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "componentName",
								"defaultContent" : ""
							}, {
								"data" : "subComponentName",
								"defaultContent" : ""
							}, {
								"data" : "activityComponentName",
								"defaultContent" : ""
							}, {
								"data" : "subSubComponentName",
								"defaultContent" : ""
							}, {
								"data" : "estimatedCost",
								"defaultContent" : ""
							}, {
								"data" : "reqNxtYear",
								"defaultContent" : ""
							}, {
								"data" : "alreadySpent",
								"defaultContent" : ""
							}, {
								"data" : "qtr1",
								"defaultContent" : ""
							}, {
								"data" : "qtr2",
								"defaultContent" : ""
							}, {
								"data" : "qtr3",
								"defaultContent" : ""
							}, {
								"data" : "qtr4",
								"defaultContent" : ""
							},{
								"data" : "componentId",
								"defaultContent" : ""
							},{
								"data" : "finYear",
								"defaultContent" : ""
							},{
								"data" : "divisionId",
								"defaultContent" : ""
							},{
								"data" : "schemeId",
								"defaultContent" : ""
							},{
								"data" : "subComponentId",
								"defaultContent" : ""
							},{
								"data" : "subComponentLevel3Id",
								"defaultContent" : ""
							},{
								"data" : "activityId",
								"defaultContent" : ""
							},{
								"data" : "subSubComponentId",
								"defaultContent" : ""
							},{
								"data" : "divAnnBudgId",
								"defaultContent" : ""
							}]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});
		$('#nodalDivisionBudgetView tbody').on('click','.divAnnBudgId',function() {
			var data = nodalDivision.row(this).data();
			localStorage.setItem("financialYear",data.finYear);
			localStorage.setItem("componentName",data.componentId);
			localStorage.setItem("divisionId",data.divisionId);
			localStorage.setItem("schemeId",data.schemeId);
			localStorage.setItem("activityId",data.activityId);
			localStorage.setItem("subSubComponentId",data.subSubComponentId);
			localStorage.setItem("subComponentId",data.subComponentId);
			localStorage.setItem("subComponentLevel3Id",data.subComponentLevel3Id);
			localStorage.setItem("estimatedCost",data.estimatedCost);
			localStorage.setItem("reqNxtYear",data.reqNxtYear);
			localStorage.setItem("alreadySpent",data.alreadySpent);
			localStorage.setItem("reqNxtYear",data.reqNxtYear);
			localStorage.setItem("qtr1",data.qtr1);
			localStorage.setItem("qtr2",data.qtr2);
			localStorage.setItem("qtr3",data.qtr3);
			localStorage.setItem("qtr4",data.qtr4);
			localStorage.setItem("divAnnBudgId",data.divAnnBudgId);
		});
	}
}


var stateBudget;
var stateBudgedView = {
	stateBudgedViews : function(finance) {

		$("#stateBudgetView")
				.DataTable(
						{
							"dom" : 'lBfrtip',
							buttons : [ {
								extend : 'collection',
								text : 'Export Data',
								autoClose : true,
								buttons : [
										{
											extend : 'pdf',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4, 5,
														6, 7, 8, 9, 10 ]
											}
										},
										{
											extend : 'excel',
											exportOptions : {
												columns : [ 0, 1, 2, 3, 4, 5,
														6, 7, 8, 9, 10 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns:0,
								rightColumns : 1
							},
							
							'sAjaxSource' : 'divisionBudgetViewAction.do?finance='+ finance+ '&divisionType=nodal&method=populateDivisionBudget&d__mode="+d__mode+"',

									columnDefs : [
													{
														"targets" : 19,
														className : 'divAnnBudgId',
														"data" : 'divAnnBudgId',
														mRender: function (data, type, row) {
											                return '<a class="table-edit" data-id="' + row[0] + '" href="updateStateBudgetAction.do?menuId=FN019&divAnnBudgt='+data+'" ><button type="button" class="btn btn-info">EDIT</button></a>'
											            }
													}, {
														"targets" : [ 11 ],
														"visible" : false,
														"searchable" : false
													}, {
														"targets" : [ 12 ],
														"visible" : false,
														"searchable" : false
													}, {
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
													}],
													select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "componentName",
								"defaultContent" : ""
							}, {
								"data" : "subComponentName",
								"defaultContent" : ""
							}, {
								"data" : "activityComponentName",
								"defaultContent" : ""
							}, {
								"data" : "subSubComponentName",
								"defaultContent" : ""
							}, {
								"data" : "estimatedCost",
								"defaultContent" : ""
							}, {
								"data" : "reqNxtYear",
								"defaultContent" : ""
							}, {
								"data" : "alreadySpent",
								"defaultContent" : ""
							}, {
								"data" : "qtr1",
								"defaultContent" : ""
							}, {
								"data" : "qtr2",
								"defaultContent" : ""
							}, {
								"data" : "qtr3",
								"defaultContent" : ""
							}, {
								"data" : "qtr4",
								"defaultContent" : ""
							},{
								"data" : "componentId",
								"defaultContent" : ""
							},{
								"data" : "finYear",
								"defaultContent" : ""
							},{
								"data" : "divisionId",
								"defaultContent" : ""
							},{
								"data" : "schemeId",
								"defaultContent" : ""
							},{
								"data" : "subComponentId",
								"defaultContent" : ""
							},{
								"data" : "subComponentLevel3Id",
								"defaultContent" : ""
							},{
								"data" : "activityId",
								"defaultContent" : ""
							},{
								"data" : "subSubComponentId",
								"defaultContent" : ""
							},{
								"data" : "divAnnBudgId",
								"defaultContent" : ""
							}]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});
		$('#stateBudgetView tbody').on('click','.divAnnBudgId',function() {
			var data = stateBudget.row(this).data();
			localStorage.setItem("financialYear",data.finYear);
			localStorage.setItem("componentName",data.componentId);
			localStorage.setItem("divisionId",data.divisionId);
			localStorage.setItem("schemeId",data.schemeId);
			localStorage.setItem("activityId",data.activityId);
			localStorage.setItem("subSubComponentId",data.subSubComponentId);
			localStorage.setItem("subComponentId",data.subComponentId);
			localStorage.setItem("subComponentLevel3Id",data.subComponentLevel3Id);
			localStorage.setItem("estimatedCost",data.estimatedCost);
			localStorage.setItem("reqNxtYear",data.reqNxtYear);
			localStorage.setItem("alreadySpent",data.alreadySpent);
			localStorage.setItem("reqNxtYear",data.reqNxtYear);
			localStorage.setItem("qtr1",data.qtr1);
			localStorage.setItem("qtr2",data.qtr2);
			localStorage.setItem("qtr3",data.qtr3);
			localStorage.setItem("qtr4",data.qtr4);
			localStorage.setItem("divAnnBudgId",data.divAnnBudgId);
		});
	}
}

var fundSourceMst = {
	fundSourceMstFun : function() {
		$("#fundSourceMasterDT")
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
										columns : [ 0, 1, 2, 3 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 0, 1, 2, 3 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'fundSourceMasterAction.do?method=populateFundSourceMaster&d__mode="+d__mode+"',

							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "scheme",
								"defaultContent" : ""
							}, {
								"data" : "fundSourceMst",
								"defaultContent" : ""
							}, {
								"data" : "stateShare",
								"defaultContent" : ""
							}, {
								"data" : "centerShare",
								"defaultContent" : ""
							} ]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

	}
}

var gpwscRegister = {
	gpwscRegisterFun : function() {
		$("#gpwscRegisterDT")
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
										columns : [ 0, 1, 2, 3 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 0, 1, 2, 3 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'gpwscRegisterAction.do?method=populateGPWSCRegister&d__mode="+d__mode+"',

							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "transactionNo",
								"defaultContent" : ""
							}, {
								"data" : "transactionType",
								"defaultContent" : ""
							}, {
								"data" : "transactionDate1",
								"defaultContent" : ""
							}, {
								"data" : "paymentAmt",
								"defaultContent" : ""
							} ]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

	}
}

var ddoMaster = {
	ddoMasterFn : function() {
		$("#ddoMasterDT")
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
										columns : [ 0, 1, 2 ]
									}
								}, {
									extend : 'excel',
									exportOptions : {
										columns : [ 0, 1, 2 ]
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
							"scrollX" : true,
							"scrollY" : "200px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							fixedColumns : {
								leftColumns : 1,
								rightColumns : 1
							},
							'sAjaxSource' : 'ddoMasterAction.do?method=populateDdoMaster&d__mode="+d__mode+"',

							select : {
								style : 'os',
								selector : 'td:first-child'
							},
							"columns" : [ {
								"data" : "ddoNumber",
								"defaultContent" : ""
							}, {
								"data" : "ddoName",
								"defaultContent" : ""
							}, {
								"data" : "division",
								"defaultContent" : ""
							} ]

						})
		$('.datatable').each(
				function() {
					var datatable = $(this);
					var search_input = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_filter] input');
					search_input.attr('placeholder', 'Search');
					search_input.addClass('form-control input-sm');
					var length_sel = datatable.closest('.dataTables_wrapper')
							.find('div[id$=_length] select');
					length_sel.addClass('form-control input-sm');
				});

	}
}