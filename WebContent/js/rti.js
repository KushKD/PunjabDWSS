var updateRti; 
var updateRtis = {
	updateRtisType : function(district, block, villageId) {

		if ($.fn.dataTable.isDataTable('#updateRtiDT')) {
			updateRti.destroy();
		}
		updateRti =

		$("#updateRtiDT")
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
							"scrollY" : "400px",
							scrollCollapse : true,
							"bProcessing" : true,
							"bPaginate" : true,
							"serverSide" : true,
							"bSort" : true,
							'sAjaxSource' : 'updateRtiAction.do?method=populateRtiData&d__mode="+d__mode+"&menuId=RTI003',
							columnDefs : [ {
								"targets" : 0,
								className : 'resultd',
								"data" : 'personName',
								"defaultContent": "-", 
								"targets": "_all", 
								"render" : function(data, type, row, meta) {
									return data;
								}
							}/*, {
								"targets" : [ 4 ],
								"visible" : false,
								"searchable" : false
							}*/ ],
							
							"columns" : [ {
								"data" : "rtiRefNo",
								"defaultContent" : ""
							}, {
								"data" : "shortname",
								"defaultContent" : ""
							}, {
								"data" : "name",
								"defaultContent" : ""
							}, {
								"data" : "details",
								"defaultContent" : ""
							}, {
								"data" : "userID",
								"defaultContent" : ""
							}, {
								"data" : "viewDetails",
								"defaultContent" : ""
							}	 ]

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

		$('#updateRtiDT tbody').on('click', '.resultd', function() {
			var data = updateRti.row(this).data();
			rtiuDetails.rtiuDetail(data.rtiID);
			console.warn("ID"+ data.rtiID);
		});
	}
}
var rtiuDetails = {
	rtiuDetail : function(data) {
		
		$
				.ajax({
					type : "POST",
					url : "updateRtiAction.do?method=getRtiDetails&d__mode="
							+ d__mode + "&menuId=RTI003",
					data : {
						Id : data
					},
					success : function(data) {
						console.warn(data);
						localStorage.setItem("rtidata",data);
						show();
						//displayRtiUDetails(parsed);
						$("#myModal").modal('show');
						// event.preventDefault();
					},
					error : function(error) {

					}
				});

	}
}



/*function displayRtiUDetails(data) {
	var ctx =  window.location.origin;
	var html = "";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Person Details</h4>";
	html += "<div>";
	
	//Name
	html += "<div class='row col-lg-12' '>";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Name</label>";
	html += "<label class='text-left col-lg-8'>" + data.name+ "</label>";
	html += "</div>";
	
	//Addess
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Address</label>";
	html += "<label class='text-left col-lg-8'>" + data.address + "</label>";
	html += "</div>";
	
	//Mobile
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Mobile Number</label>";
	html += "<label class='text-left col-lg-8'>" + data.mobile + "</label>";
	html += "</div>";
	
	//landline
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Landline Number</label>";
	if(data.landline === 0 || data.landline == null || data.landline == undefined || data.landline == ''){
		html += "<label class='text-left col-lg-8'>"+""+"</label>";
	} else{
	html += "<label class='text-left col-lg-8'>" + data.landline + "</label>";
	}
	html += "</div>";
	
	//Email
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Email Address</label>";
	if(data.email === 0 || data.email == null || data.email == undefined || data.email == ''){
		html += "<label class='text-left col-lg-8'>"+""+"</label>";
	} else{
	html += "<label class='text-left col-lg-8'>" + data.email + "</label>";
	}
	html += "</div>";
	
	//Literate
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Literate/Iliterate</label>";
	html += "<label class='text-left col-lg-8'>" + data.literate + "</label>";
	html += "</div>";
	
	//Poverty
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Is Applicant Below Poverty Line</label>";
	if(data.poverty === 1){
		html += "<label class='text-left col-lg-8'>" + "Yes" + "</label>";
	} else{
	html += "<label class='text-left col-lg-8'>" + "No" + "</label>";
	}
	html += "</div>";
	
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//RTI Details
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>RTI Details</h4>";
	html += "<div>";
	
	//RTI Ref Number
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Reference Num.</label>";
	html += "<label class='text-left col-lg-6'>" + data.rtiRefNo + "</label>";
	html += "</div>";
	
	//RTI Office 
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>RTI  Office</label>";
	
	if(data.office === 1){
		html += "<label class='text-left col-lg-6'>" + "Head Office" + "</label>";
	}else{
		html += "<label class='text-left col-lg-6'>" + "Field Office" + "</label>";
	}
	html += "</div>";
	
	//Short Name
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>RTI Short Name</label>";
	html += "<label class='text-left col-lg-8'>" + data.shortname + "</label>";
	html += "</div>";
	
	
	//details
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'> RTI Details</label>";
	html += "<label class='text-left col-lg-8'>" + data.details + "</label>";
	html += "</div>";
	
	//applicationFileName
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-5 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'> Application Name</label>";
	if(data.applicationFileName === 0 || data.applicationFileName == null || data.applicationFileName == undefined || data.applicationFileName == ''){
		html += "<label class='text-left col-lg-5'>" + "" + "</label>";
	} else{
	html += "<label class='text-left col-lg-5'>" + data.applicationFileName + "</label>";
	html += "<a href='http://localhost:8080/PRWSS_MIS/updateRtiAction.do?method=downloadApplicationFile&rtiID="
		+ data.rtiID
		+ " ' target='_blank' onclick='javascript: setTimeout(window.close, 1);'>"
		+ "<span class='btn btn-primary text-left col-lg-2'>Download </span>" + "</a>"
	}
	html += "</div>";
	
	//documentsFileName
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-5 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'> Documents  Name</label>";
	if(data.documentsFileName === 0 || data.documentsFileName == null || data.documentsFileName == undefined || data.documentsFileName == ''){
		html += "<label class='text-left col-lg-5'>" + "" + "</label>";
	} else{
	html += "<label class='text-left col-lg-5'>" + data.documentsFileName + "</label>";
	html += "<a href='http://localhost:8080/PRWSS_MIS/updateRtiAction.do?method=downloadDocumentsFile&rtiID="
		+ data.rtiID
		+ " ' target='_blank' onclick='javascript: setTimeout(window.close, 1);'>"
		+ "<span class='btn btn-primary text-left col-lg-2'>Download </span>" + "</a>"
	}
	html += "</div>";
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//Payment Details
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Payment Details</h4>";
	html += "<div>";
	
	//Payment Amount
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Amount</label>";
	if(data.amount === 0 || data.amount == null || data.amount == undefined || data.amount == ''){
		html += "<label class='text-left col-lg-6'>" + "" + "</label>";
	} else{
	html += "<label class='text-left col-lg-6'>" + data.amount + "</label>";
	}
	html += "</div>";
	
	//datePayment
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Payment Date</label>";
	if(data.datePayment === 0 || data.datePayment == null || data.datePayment == undefined || data.datePayment == ''){
		html += "<label class='text-left col-lg-6'>" + "" + "</label>";
	} else{
	html += "<label class='text-left col-lg-6'>" + data.datePayment + "</label>";
	}
	html += "</div>";
	
	//remarks
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Remarks</label>";
	if(data.remarks === 0 || data.remarks == null || data.remarks == undefined || data.remarks == ''){
		html += "<label class='text-left col-lg-6'>" + "" + "</label>";
	} else{
	html += "<label class='text-left col-lg-6'>" + data.remarks + "</label>";
	}
	html += "</div>";
	
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//Update Response
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Update Response</h4>";
	html += "<div>";
	
	//Payment Amount
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-3 col-md-4 col-xs-12 col-sm-12 text-left  text-danger'>Remarks</label>";
	html += "<input type='text' name='responseRemarks' Id='responseRemarks' styleClass='form-control ci5' class='text-left col-lg-8 col-md-8'></input>";
	html += "</div>";
	
	//datePayment
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Upload Response Document/ Letter</label>";
	html += "<input type='file' name='responseFile' Id ='response' label class='text-left col-lg-8 col-md-8'></input>";
	html += "</div>";
	
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	//Footer
	html += "<div class='modal-footer'>";
	html += "<button type='button' class='btn btn-success col-lg-5' id='submit'>Submit</button>";
	html += "<button type='button' class='btn btn-default col-lg-5' data-dismiss='modal'>Close</button>";
	html += "</div>";
	document.getElementsByClassName("modal-body")[0].innerHTML = html;

	
	 //updateRecord
	 var submit = document.getElementById("submit");
	 submit.addEventListener("click", function(e) {

        var responseRemarks =  document.getElementById("responseRemarks").value;
        var response = document.getElementById("response").value;
        var rtiID = data.rtiID;
        
        
	if(validateInputFields()){
		 $.ajax({
				type : "POST",
				url : "updateRtiAction.do?method=saveRTIUpdateDetails&d__mode="
						+ d__mode + "&menuId=RTI003",
				data : {

					responseRemarks : responseRemarks,
					response : response,
					rtiID: rtiID,
				},
				success : function(data) {
					//var parsed = JSON.parse(data);
					alert(data);
					//displayBeneficiaryDetails(parsed);
					//$("#myModal").modal('show');
					// event.preventDefault();
				},
				error : function(error) {
					
				}
				
			})
		
	}
        
       
       function validateInputFields(){
       	
       	 if(response == "" || response == null) {
   			alert("Please Upload Required Document.");
   			return false;
   		}
       	 
       	 return true;
       } 
           			
	 });
	
	
}*/


var RTI; 
var viewRTI = {
	viewRTIType : function(district, block, villageId) {

		if ($.fn.dataTable.isDataTable('#AssignRTIDataTable')) {
			RTI.destroy();
		}
		RTI =

		$("#AssignRTIDataTable")
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
							'sAjaxSource' : 'assignrtiOnlineAction.do?method=getRtiData&d__mode="+d__mode+"&menuId=RTI002',
							columnDefs : [ {
								"targets" : 0,
								className : 'resultd',
								"data" : 'personName',
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
								"data" : "rtiID",
								"defaultContent" : ""
							}, {
								"data" : "shortname",
								"defaultContent" : ""
							}, {
								"data" : "rtiRefNo",
								"defaultContent" : ""
							}, {
								"data" : "name",
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

		$('#AssignRTIDataTable tbody').on('click', '.resultd', function() {
			var data = RTI.row(this).data();
			beneficiaryEntryDetails.beneficiaryEntryDetail(data.rtiID);
		});
	}
}
var beneficiaryEntryDetails = {
	beneficiaryEntryDetail : function(data) {
		
		$
				.ajax({
					type : "POST",
					url : "assignrtiOnlineAction.do?method=getRTIDetails&d__mode="
							+ d__mode + "&menuId=RTI002",
					data : {
						Id : data
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
	var ctx =  window.location.origin;
	var html = "";
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Person Details</h4>";
	html += "<div>";
	
	//Name
	html += "<div class='row col-lg-12' '>";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Name</label>";
	html += "<label class='text-left col-lg-8'>" + data.name+ "</label>";
	html += "</div>";
	
	//Addess
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Address</label>";
	html += "<label class='text-left col-lg-8'>" + data.address + "</label>";
	html += "</div>";
	
	//Mobile
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Mobile Number</label>";
	html += "<label class='text-left col-lg-8'>" + data.mobile + "</label>";
	html += "</div>";
	
	//landline
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Landline Number</label>";
	html += "<label class='text-left col-lg-8'>" + data.landline + "</label>";
	html += "</div>";
	
	//Email
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Email Address</label>";
	html += "<label class='text-left col-lg-8'>" + data.email + "</label>";
	html += "</div>";
	
	//Literate
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Literate/Iliterate</label>";
	html += "<label class='text-left col-lg-8'>" + data.literate + "</label>";
	html += "</div>";
	
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//RTI Details
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>RTI Details</h4>";
	html += "<div>";
	
	//RTI Ref Number
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Reference Num.</label>";
	html += "<label id='refId' class='text-left col-lg-6'>" + data.rtiRefNo + "</label>";
	html += "</div>";
	
	//RTI Office 
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>RTI  Office</label>";
	
	if(data.office === 1){
		html += "<label class='text-left col-lg-6'>" + "Head Office" + "</label>";
	}else{
		html += "<label class='text-left col-lg-6'>" + "Field Office" + "</label>";
	}
	html += "</div>";
	
	//Short Name
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>RTI Short Name</label>";
	html += "<label class='text-left col-lg-8'>" + data.shortname + "</label>";
	html += "</div>";
	
	
	//details
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'> RTI Details</label>";
	html += "<label class='text-left col-lg-8'>" + data.details + "</label>";
	html += "</div>";
	
	//applicationFileName
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-5 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'> Application Name</label>";
	html += "<label class='text-left col-lg-5'>" + data.applicationFileName + "</label>";
	html += "<span class='btn btn-primary text-left col-lg-2'>Download </span>";
	html += "</div>";
	
	//documentsFileName
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-5 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'> Documents  Name</label>";
	html += "<label class='text-left col-lg-5'>" + data.documentsFileName + "</label>";
	html += "<span class='btn btn-primary text-left col-lg-2'>Download </span>";
	html += "</div>";
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//Payment Details
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Payment Details</h4>";
	html += "<div>";
	
	//Payment Amount
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Amount</label>";
	html += "<label class='text-left col-lg-6'>" + data.amount + "</label>";
	html += "</div>";
	
	//datePayment
	html += "<div class='row col-lg-6' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Payment Date</label>";
	html += "<label class='text-left col-lg-6'>" + data.datePayment + "</label>";
	html += "</div>";
	
	//remarks
	html += "<div class='row col-lg-12' >";
	html += "<label class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Remarks</label>";
	html += "<label class='text-left col-lg-6'>" + data.remarks + "</label>";
	html += "</div>";
	
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//Assign Details
	html += "<div class='panel panel-danger'>";
	html += "<div class='panel-body'>";
	html += "<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Assign RTI Details</h4>";
	html += "<div>";
	
	//Designation
	html += "<div class='row col-lg-12'>";  
	html += "<label  class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Designation<span class='text-danger'> &nbsp;*</span></label>";
	html += "<select  id='designation' style='width: 150px' onchange='ajaxFunction('assignrtiOnlineAction.do?designationId='+this.value+'&method=getEmployee', 'employee')'  class='col-lg-6 col-md-12 col-xs-12 col-sm-12 form-control' value=''> <option value=''>Select</option> </select>";
	html += "</div>";
	
	//Employee
	html += "<div class='row col-lg-12'>";  
	html += "<label  class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Employee<span class='text-danger'> &nbsp;*</span></label>";
	html += "<select  id='employee' style='width: 150px'  class='col-lg-6 col-md-12 col-xs-12 col-sm-12 form-control' value=''> <option value=''>Select</option> </select>";
	html += "</div>";
	
	
	//Due Date
	html += "<div class='row col-lg-12'>";
	html += "<label  class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Due Date</label>";
	html += "<input type='text' name='dueDate' style='width: 150px' id='dueDate' class='col-lg-6 col-md-12 col-xs-12 col-sm-12 form-control'> </input>";
	html += "</div>";
	
	
	//Remarks
	html += "<div class='row col-lg-12' >";
	html += "<label  class='form-inline col-lg-6 col-md-12 col-xs-12 col-sm-12 text-left  text-danger'>Remarks</label>";
	html += "<textarea rows='4' cols='50' id='remarks' type='text' class='text-left col-lg-6' '> </textarea>";
	html += "</div>";
	
	
	
	html += "</div>";
	html += "</div>";
	html += "</div>";
	
	
	//Footer
	html += "<div class='modal-footer'>";
	html += "<button type='button' class='btn btn-success col-lg-5' id='updateRecord'>Update</button>";
	html += "<button type='button' class='btn btn-default col-lg-5' data-dismiss='modal'>Close</button>";
	
	html += "</div>";
	document.getElementsByClassName("modal-body")[0].innerHTML = html;
	
	
	$('#dueDate,#defaultInline')
    .datepicker({
        format: 'mm/dd/yyyy',
         beforeShow: function () {
              setTimeout(function () {
                  $('.ui-datepicker').css('z-index', 99999);
              }, 0);
          }
    })
    .on('changeDate', function(e) {
        // Revalidate the date field
        $('#eventForm').formValidation('revalidateField', 'date');
    });
	
	
	// Ajax
	 ajaxFunction('assignrtiOnlineAction.do?method=getDesignation', 'designation');
	 
	
	   
	 var activities = document.getElementById("designation");
	 activities.addEventListener("change", function(e) {
		 ajaxFunction('assignrtiOnlineAction.do?designationId='+this.value+'&method=getEmployee', 'employee');
			
		 });
	 
	 //updateRecord
	 var updateRecord = document.getElementById("updateRecord");
	 updateRecord.addEventListener("click", function(e) {

         var designation =  document.getElementById("designation").value;
         var employee = document.getElementById("employee").value;
         var dateDue = document.getElementById("dueDate").value;
         var remarks = document.getElementById("remarks").value;
         var refID = data.rtiRefNo;
         
         
	if(validateInputFields()){
		
		 $.ajax({
				type : "POST",
				url : "assignrtiOnlineAction.do?method=saveRTIAssignDetails&d__mode="
						+ d__mode + "&menuId=RTI002",
				data : {
					designation : designation,
					employee : employee,
					dateDue : dateDue,
					remarks: remarks,
					refID: refID
				},
				success : function(data) {
					//var parsed = JSON.parse(data);
					alert(data);
					//displayBeneficiaryDetails(parsed);
					//$("#myModal").modal('show');
					// event.preventDefault();
				},
				error : function(error) {

				}
			})
      
		
	}
         
        
        function validateInputFields(){
        	
        	 if(designation == "" || designation == null) {
    			alert("Please Select designation of the employee to whome the RTI is being asigned.");
    			return false;
    		}
        	 else if(employee == "" || employee == null) {
    			alert("Please Select employee  to whome the RTI is being asigned.");
    			return false;
    		}
        	 
        	 else if(dateDue == "" || dateDue == null) {
     			alert("Please Select the Due Date ");
     			return false;
     		}
        	 
        	/* else if(remarks__ == "" || remarks__ == null) {
     			alert("Remarks are essential in case of assigning the  RTI");
     			return false;
     		} 
        	 else if(refID == "" || refID == null) {
     			alert("The System Found some trouble in finding  the RTI Reference Number. Please check logs");
     			return false;
     		}*/
        	 
        	 return true;
        } 
         
         
         
         
         
			
	 });

}