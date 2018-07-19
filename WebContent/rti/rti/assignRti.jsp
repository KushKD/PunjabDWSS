<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/form.css" rel="stylesheet" type="text/css"> 
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" type="text/css" href="css/common.css"> -->
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<!-- <script rel="stylesheet" href="css/bootstrap.min.css"></script> -->
<!-- <script type="text/javascript" src="js/jquery-1.12.4.js"></script> -->
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script>
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script type="text/javascript" src="js/buttons.colVis.min.js"></script> 
<script type="text/javascript" src="js/rti.js"></script>
<style type="text/css">
/* th {
	background: #d2c8c8;
} */
.modal-header {
	background-color: #000000;
	padding: 6px 6px;
	color: #E8EAF6;
	border-bottom: 2px dashed #000000;
}

.modal-title {
	text-align: center;
}

.close {
	color: #ffffff
}

.close {
	color: #fff;
	opacity: 1;
}

.modal-dialog {
	overflow-y: initial !important
}

.modal-body {
	height: auto;
	overflow-y: auto;
}

.modal-dialog {
	width: 971px;
	height: 700px overflow-y: initial !important
}


/* .modal-content {
	width: 400px;
} */

/* .modal-footer {
	margin: -10px;
} */
</style>

<script type="text/javascript">

 function show(){
	 var rtiData=localStorage.getItem("rtidata");
	 var parsed = JSON.parse(rtiData);
	 var yes = "Yes";
	 var no = "No";
	 var headoffice = "Head Office";
	 var fieldoffice = "Field Office";
	// alert(rtiData);
	
	 document.getElementById('rtiID').value=parsed.rtiID;
	 $("#nameId").text(parsed.name);
	 $("#addressId").text(parsed.address);
	 $("#mobileId").text(parsed.mobile);
	 $("#landlineId").text(parsed.landline);
	 $("#emailId").text(parsed.email);
	 $("#literateId").text(parsed.literate);
	 if(parsed.poverty == 1){
		 $("#povertyId").text(yes);
	 }else{
		 $("#povertyId").text(no);
	 }
	 $("#rtiRefNoId").text(parsed.rtiRefNo);
	 if(parsed.office == 1){
		 $("#officeId").text(headoffice);
	 }else{
		 $("#officeId").text(fieldoffice);
	 }
	 $("#shortnameId").text(parsed.shortname);
	 $("#detailsId").text(parsed.details);
	 $("#applicationFileNameId").text(parsed.applicationFileName);
	 $("#documentsFileNameId").text(parsed.documentsFileName);
	 $("#amountId").text(parsed.amount);
	 $("#datePaymentId").text(parsed.datePayment);
	 $("#remarksId").text(parsed.remarks);
 }
 
	  function de_add() {
		var result = validateFields();
		if (result) {
			 $.ajax({
				 type : "POST",
				url : "assignrtiOnlineAction.do?method=saveRTIAssignDetail&d__mode="
						+ d__mode + "&menuId=RTI002",
						data : {
							designation : document.getElementById("designation").value,
							employee : document.getElementById("employee").value,
							dueDate : document.getElementById("dueDate").value,
							assignRemarks: document.getElementById("assignRemarks").value,
							rtiID: document.getElementById("rtiID").value
						},
				success : function(data) {
					location.reload();
					//alert(data);
				},
				error : function(error) {

				}
			})
			 /* document.assignRtiForm.action = "assignrtiOnlineAction.do?method=saveRTIAssignDetail&d__mode="
					+ d__mode + "&menuId=RTI002";
			document.assignRtiForm.submit();
			alert('hiiii'); */
		}
	}
	  
	  function downloadApplicationFile(){
			 $.ajax({
				 type : "POST",
				url : "assignrtiOnlineAction.do?method=downloadApplicationFile&rtiID="
						+ document.getElementById("rtiID").value + "&menuId=RTI002",
						data : {
							rtiID: document.getElementById("rtiID").value
						},
				success : function(data) {
					//alert(data);
				},
				error : function(error) {

				}
			})
		 }
		 
		 function downloadDocumentsFile(){
			 $.ajax({
				 type : "POST",
				url : "assignrtiOnlineAction.do?method=downloadDocumentsFile&rtiID="
						+ document.getElementById("rtiID").value + "&menuId=RTI002",
						data : {
							rtiID: document.getElementById("rtiID").value
						},
				success : function(data) {
					//alert(data);
				},
				error : function(error) {

				}
			})
		 }
	  
	   function validateFields(){
			
			var designation = document.getElementById("designation").value;
			var employee = document.getElementById("employee").value;
			var dueDate = document.getElementById("dueDate").value;
		  	
		 	 if(designation == "" || designation == null) {
					alert("Please Select Designation.");
					return false;
				}
		 	 else if(employee == "" || employee == null){
					alert("Please Select Employee.");
					return false;
				}
		 	 else if(dueDate == "" || dueDate == null){
					alert ("Please Select Due Date.");
					return false;
				}
		 	  return true;
		 	 
		}  
	 
</script> 

</head>


<html:html>


<html:form action="assignrtiOnlineAction" method="post"
	styleId="assignRtiForm" >



	<div class="panel panel-danger">
		<div class="panel-body">

			<h4
				class="text-on-pannel text-primary col-lg-12 col-xs-12 col-sm-12 col-md-12 center-block text-center">Assign RTI</h4>

			<div class="line"></div>
			<div class="panel panel-danger" id="datagrid">
		<div class="panel-body">
			<div id="datatable_sh_Rti"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12" style="display: none">
				<table id="AssignRTIDataTable"
					class="display table-responsive table-bordered  table-hover "
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>Id</th>
							<th>RTI Title</th>
							<th>RTI Reference Number</th>
							<th>Applicant Name</th>
							<th>Short Details </th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

		</div>
	</div>

<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"> SelectedRTI  Details</h4>
				</div>
				<div class="modal-body">
								<div class='panel panel-danger'>
				<div class='panel-body'>
				<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Person Details</h4>
				<div>
				<html:hidden property="rtiID" styleId='rtiID'/>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Name</label>
				<label class='text-left col-lg-7' id='nameId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Address</label>
				<label class='text-left col-lg-7' id='addressId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Mobile Number</label>
				<label class='text-left col-lg-7' id='mobileId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Landline Number</label>
				<label class='text-left col-lg-7' id='landlineId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Email Address</label>
				<label class='text-left col-lg-7' id='emailId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Literate/Illiterate</label>
				<label class='text-left col-lg-7' id='literateId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Is Applicant BPL</label>
				<label class='text-left col-lg-7' id='povertyId'></label>
				</div>
				
				</div>
				</div>
				</div>
				
				
				<div class='panel panel-danger'>
				<div class='panel-body'>
				<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>RTI Details</h4>
				<div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Reference No.</label>
				<label class='text-left col-lg-7' id='rtiRefNoId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>RTI  Office</label>
				<label class='text-left col-lg-7' id='officeId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>RTI Title</label>
				<label class='text-left col-lg-7' id='shortnameId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>RTI Details</label>
				<label class='text-left col-lg-7' id='detailsId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Application</label>
				<a target='_blank' onclick='downloadApplicationFile(); javascript: setTimeout(window.close, 1);'><label class='text-left col-lg-7' id='applicationFileNameId'></label></a>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Documents</label>
				<a target="_blank" onclick='downloadDocumentsFile(); javascript: setTimeout(window.close, 1);'><label class='text-left col-lg-7' id='documentsFileNameId'></label></a>
				</div>
				
				</div>
				</div>
				</div>
				
				
				<div class='panel panel-danger'>
				<div class='panel-body'>
				<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Payment Details</h4>
				<div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Amount</label>
				<label class='text-left col-lg-7' id='amountId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Payment Date</label>
				<label class='text-left col-lg-7' id='datePaymentId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Remarks</label>
				<label class='text-left col-lg-7' id='remarksId'></label>
				</div>
				
				</div>
				</div>
				</div>
				
				
				<div class='panel panel-danger'>
				<div class='panel-body'>
				<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Assign RTI Details</h4>
				<div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Designation</label>
				<html:select property='designation'  styleId="designation" value='' styleClass="form-control ci5" onchange="ajaxFunction('assignrtiOnlineAction.do?designationId='+this.value+'&method=getEmployee', 'employee');" style="max-width:275px;"><html:option value=""> Select Designation</html:option></html:select>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Employee</label>
				<html:select property='employee'  styleId="employee" styleClass="form-control ci5" style="max-width:275px;"><html:option value=""> Select Employee</html:option></html:select>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Due Date</label>
				<input type="text" name="dueDate" style="width: 150px" id="dueDate"	class="ci5 form-control" ></input>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Remarks</label>
				<html:textarea property='assignRemarks' styleId='assignRemarks' styleClass="form-control ci5" style="max-width:275px; height:60px;"></html:textarea>
				</div>
				
				</div>
				</div>
				</div>
					
					<div class="modal-footer">
					<div class="col-lg-7">&nbsp;</div>
					<button type='button' class='btn btn-success col-lg-2' id='updateRecord1' onclick="de_add();">Update</button>
						<button type='button' class='btn btn-default col-lg-2' data-dismiss='modal'>Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>
	






</html:form>


</body>


<script>

document.getElementById("datatable_sh_Rti").style.display = '';
viewRTI.viewRTIType();




$('#dueDate,#defaultInline').datepicker({
    format: 'mm/dd/yyyy',
    beforeShow: function () {
         setTimeout(function () {
             $('.ui-datepicker').css('z-index', 99999);
         }, 0);
     }
})


$(document)
.ready(
		function() {
		Req="ent_frwrd";
			de_init('ent_frwrd', "test");
		});
		
		


$(window)
.load(
		function() {
			 ajaxFunction('assignrtiOnlineAction.do?method=getDesignation', 'designation');
			 
		});
	
</script>

</html:html>