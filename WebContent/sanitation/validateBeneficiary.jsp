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
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sanitation-workflow.js"></script>

<script type="text/javascript">
	//hide_ctrl('modalPeriod', true);
	
	 function de_find() {
		var status = validateField();
		if (status) {
			//baseLineSurveyFind.baseLineSurveyFinds();
		}
	}
	function de_add() {
		saveValidateBeneficiayr.saveValidateBeneficiayrs();
	} 
</script>
<style type="text/css">
body {
	background-color: #bdc3c7;
}

tbody {
	max-height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
	overflow-x: hidden; /* Hide the horizontal scroll */
}
.table>thead>tr>th, .table>thead>tr>td {
	font-size: .7em;
	border-bottom: 0;
	letter-spacing: 1px;
	vertical-align: center;
	padding: 2px;
	background: #000000;
	text-transform: uppercase;
	color: #ffffff;
}
.modal-header {
	background-color: #283D4B;
	padding: 7px 9px;
	color: #E8EAF6;
	border-bottom: 2px dashed #283D4B;
}

.modal-title {
	text-align: center;
}

.close {
	color: #ffffff
}
#myInput {
  background-image: url('/PRWSS_MIS/css/searchicon.png');
  background-position: 9px 6px;
  background-repeat: no-repeat;
  width: 100%;
  font-size: 16px;
  padding: 5px 5px 5px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

.modal-content{
margin: 5px 5px 5px 83px;
}
</style>

</head>


<html:html>

<html:form action="validateBeneficiaryAction" method="post"
	styleId="validateBeneficiaryForm">
	
	
	<div id="modalContainer"></div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"> Beneficiary Validation</h4>
			<div class="line"></div>
			<div class="col-lg-3 col-md-3 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">Survey</label>
				<html:select property="surveyId" styleId="surveyId"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class="col-lg-5 col-md-4 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-6 col-md-4 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-3 col-md-6 sm-hidden xs-hidden"> <button type="button" class="btn btn-primary" onclick='findBeneficiary.findBeneficiarys();dynamicTableData()'>Search</button></div>
				<div class="col-lg-3 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
		
		</div>
	</div>
	<div id='container'></div>
	 <div id='scndContainer'>
	
	<div class='panel panel-danger'>
	 <div class='panel-body'>
<div class='col-lg-9 col-md-9 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>
<div class='col-lg-3 col-md-3 sm-hidden xs-hidden'>
<div class='form-inline pull-right'> <input type='text' class='search form-control' id='myInput' onkeyup='search()' placeholder='What you looking for?'></div></div>
	
	 <div class='col-lg-1 col-md-1 sm-hidden xs-hidden'> </div>
	 <div class=' form-inline col-lg-12 col-md-12 col-xs-12 col-sm-12'>
	 <div class='table-responsive'>
	 <table class='table table-bordered table-striped table-highlight' id='surveyTable1'>
	 <thead style='display: block;'>
	 <tr class='row'>
	    <th class='col-lg-2 col-xs-2' style='text-align:center'>Name </th>
		<th class='col-lg-2 col-xs-2' style='text-align:center'>Aadhar Card Number</th>
		<th class='col-lg-2 col-xs-2' style='text-align:center'>Electric Connection No</th>
		<th class='col-lg-2 col-xs-2' style='text-align:center'>Status</th>
		<th class='col-lg-3 col-xs-3' style='text-align:center'>Remarks</th>
	 <th  class='col-lg-1 col-xs-1' style='text-align:center'></th>
	 </tr>
	 </thead>
	 <tbody id='fnlTable' style='display: block;'>
	  </tbody>
	 </table>
	 </div>
	 </div>
	 <div class='col-lg-10 col-md-10 sm-hidden xs-hidden'>&nbsp;&nbsp;</div>
		<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'><button type='button' class='btn btn-primary' id='saveData' onclick='de_add()'>Save</button></div>
	
	<div class='col-lg-1 col-md-1 sm-hidden xs-hidden'></div>
	 </div>
	 </div>
	 </div> 
 <div class="modal fade" id="myModal" role="dialog" >
		<div class="modal-dialog" style='width:976px;overflow-y: initial !important'>

			<div class="modal-content" style='width:976px;'>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Beneficiary Entry Details</h4>
				</div>
				<div class="modal-body" style='height:600px;overflow-y: auto;'>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>
	
	 <div id='modalPopup'>
	
	<div class="modal fade" id="myModal1" role="dialog">
		<div class="modal-dialog" style='width:1116px'>
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add New Beneficiary</h4>
				</div>
				
				<div class="modal-body1">
					
				</div>

			</div>
		</div>

	</div>
	</div> 
	
	
	 <div id='modalPopup1s'>
	
	<div class="modal fade" id="myModal11" role="dialog">
		<div class="modal-dialog" style='width:1116px'>
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Validate Beneficiary</h4>
				</div>
				
				<div class="modal-body11">
					
				</div>

			</div>
		</div>

	</div>
	</div> 
</html:form>

</body>
<script type="text/javascript">
	 $(document)
			.ready(
					function() {
						ajaxFunction(
								'GetFilterValues.to?surveyIds=surveyId&method=getForwardSurvey',
								'surveyId');
						Req = "ent_frwrd";
						de_init('ent_frwrd', "test");
						
					}); 
</script>
<script type="text/javascript">
$("#scndContainer").hide();
function handleCheckbox(toggle){
   if(toggle.checked) {
        // Iterate each checkbox
        $(':checkbox').each(function() {
            this.checked = true;                        
        });
    }else{
        $(':checkbox').each(function() {
            this.checked = false;                        
        });
    } 
    
}

function hndlCheckBox(toggle){
	 if(toggle.checked) {
	        // Iterate each checkbox
	        $(':checkbox').each(function() {
	            this.checked = true;                        
	        });
	    }else{
	        $(':checkbox').each(function() {
	            this.checked = false;                        
	        });
	    }
}
</script>
<script type="text/javascript">
	function validateField() {
		
		var surveyId = $('#surveyId').val();
		
		if (surveyId === '' || surveyId === null) {
			alert("Please Select survey");
			return false;
		}
		return true;
	}
	function dynamicTableData() {
		$("#scndContainer").hide();
		$('#surveyTable1 > tbody  > tr').each(function() {
			$(this).closest('tr').remove();
		});
	}
</script>
</html:html>