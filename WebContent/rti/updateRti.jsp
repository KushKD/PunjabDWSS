<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>



<!DOCTYPE html>
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />



<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/rti.js"></script>
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
	 alert(rtiData);
	
	 document.getElementById('rtiID').value=parsed.rtiID;
	 $("#nameId").text(parsed.name);
	 $("#addressId").text(parsed.address);
	 $("#mobileId").text(parsed.mobile);
	 $("#landlineId").text(parsed.landline);
	 $("#emailId").text(parsed.email);
	 $("#literateId").text(parsed.literate);
	 $("#povertyId").text(parsed.poverty);
	 $("#rtiRefNoId").text(parsed.rtiRefNo);
	 $("#officeId").text(parsed.office);
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
			alert('hi');
			document.submitRtiForm.action = "updateRtiAction.do?method=saveRTIUpdateDetails&d__mode="
					+ d__mode + "&menuId=RTI003";
			alert('hi');
			document.submitRtiForm.submit();
		}
	} 
</script> 

</head>


<html:html>


<html:form action="updateRtiAction" method="post"
	styleId="submitRtiForm" enctype="multipart/form-data">



<div class="panel panel-danger" id="datatable_sh" style="display: none">
		<div class="panel-body">
		<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Update RTI</h4>
			<div class="line"></div>
			<div 
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="updateRtiDT"
					class="display nowrap table-responsive table-bordered table-striped table-hover "
					style="width: 100%">

					<thead>
						<tr>
							<th>RTI Ref. No.</th>
							<th>RTI Title</th>
							<th>Applicant Name</th>
							<th>Short Description</th>
							<th>Assigned By</th>
							<th>View Details</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog" style="max-width: 75%;margin-left: auto;">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">RTI Details</h4>
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
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Literate/Iliterate</label>
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
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Reference Num.</label>
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
				<label class='text-left col-lg-7' id='applicationFileNameId'></label>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Documents</label>
				<label class='text-left col-lg-7' id='documentsFileNameId'></label>
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
				<h4 class='text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center'>Update Response</h4>
				<div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Remarks</label>
				<html:textarea property='responseRemarks' styleId='responseRemarks' styleClass="form-control ci5" style="max-width:275px; height:60px;"></html:textarea>
				</div>
				<div class='row col-lg-6'>
				<label class='form-inline col-lg-4 col-md-12 col-xs-12 col-sm-12 text-right  text-danger'>Upload Response Document/ Letter</label>
				<html:file property='responseFile'  styleId="responseFile" styleClass="form-control ci5" style="max-width:275px;"></html:file>
				</div>
				
				</div>
				</div>
				</div>
					<div class="modal-footer">
					
						<button type='button' class='btn btn-success col-lg-5' id='submit' onclick="de_add();">Submit</button>
						<button type='button' class='btn btn-default col-lg-5' data-dismiss='modal'>Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>

</html:form>

<script>

updateRtis.updateRtisType();
document.getElementById("datatable_sh").style.display = '';

  function validateFields(){
	  
	  var responseFile = document.getElementById("responseFile").value;
  	
  	 if(responseFile == "" || responseFile == null) {
			alert("Please Upload Required Document.");
			return false;
		}
  	 
  	 return true;
  } 
      			

	
	 /*
	$('#datePayment,#defaultInline').datepick();
	
	
	 function hofficeselection() {
	    if (document.getElementById('hoffice').checked) {
	    	document.getElementById('hofficedisplay').style.display = 'block';
	    	document.getElementById('fofficedisplay').style.display = 'none';
	    } 
	//    else document.getElementById('adjurst').style.display = 'none'; 

	} 
	 
	 function fofficeselection() {
		    if (document.getElementById('foffice').checked) {
		    	document.getElementById('fofficedisplay').style.display = 'block';
		        document.getElementById('hofficedisplay').style.display = 'none';
		    } 
		 //   else document.getElementById('adjurst').style.display = 'none'; 

		} 
	 
	 function belowpoverty() {
		    if (document.getElementById('poverty').checked) {
		    	document.getElementById('paymentsection').style.display = 'none';		        
		    }
		    else document.getElementById('paymentsection').style.display = 'block'; 

		}  */
	
	
	 
		 $(document)
			.ready(
					function() {
					Req="ent_frwrd";
						de_init('ent_frwrd', "test");
					});
		 
	
</script>

</body>

</html:html>