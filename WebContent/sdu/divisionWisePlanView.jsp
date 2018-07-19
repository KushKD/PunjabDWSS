<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"%>

<!DOCTYPE html>
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet" type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/sdu.js"></script>
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
 
 function de_find() {
	 
	 var status = validateFields();
	 
		var financialYear=document.getElementById("financialYear").value;
		var division=document.getElementById("divisionId").value;
		
	if(status){
		divisionWisePlanView.divisionWisePlanView_Type(financialYear, division);
      	document.getElementById('datatable_sh').style.display = '';
	}
	}
</script> 

</head>

<html:html>

<c:set var="financialYear">
	<bean:message bundle="SDU" key="financial.year" />
</c:set>
<c:set var="division">
	<bean:message bundle="SDU" key="division" />
</c:set>
<c:set var="category">
	<bean:message bundle="SDU" key="category" />
</c:set>
<c:set var="noOfVillages">
	<bean:message bundle="SDU" key="no.of.villages" />
</c:set>
<c:set var="stage">
	<bean:message bundle="SDU" key="stage" />
</c:set>
<c:set var="component">
	<bean:message bundle="SDU" key="component" />
</c:set>
<c:set var="village">
	<bean:message bundle="SDU" key="village" />
</c:set>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="SDU1" style='display: none;'>
			<html:errors bundle="SDU" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('SDU1');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				alert(text);
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>

</logic:messagesPresent> 
<html:form action="divisionWisePlanViewAction" 
	styleId="divisionWisePlanViewForm">

<div class="panel panel-danger">
		<div class="panel-body">
		<h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Division Wise Plan View</h4>
			<div class="line"></div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${financialYear}<span
					class="text-danger"> &nbsp;*</span></label>
				<html:select property="financialYear" styleId="financialYear"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${division}<span
					class="text-danger"> &nbsp;*</span></label>
				<html:select property="divisionId" styleId="divisionId"
					styleClass="cs2 form-control" style="width: 150px;">
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" style="width: 150px;"
					onclick="de_find()">Populate</button>
			</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" style="width: 150px;"
					onclick="">Download</button>
			</div>
			</div>
			</div>
			
			
	<div class="panel panel-danger" id="datatable_sh" style="display: none">
		<div class="panel-body">		
			<div  
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="divisionWisePlanViewDT"
					class="display nowrap table-responsive table-bordered table-striped table-hover "
					style="width: 100%">
					<thead>
						<tr>
							<th>${category}</th>
							<th>${stage}</th>
							<th>${component}</th>
							<th>${village}</th>
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
					<h4 class="modal-title">Activity Details</h4>
				</div>
				<div class="modal-body">
					<div class="modal-footer">
					<div class="col-lg-7">&nbsp;</div>
						<button type='button' class='btn btn-default col-lg-2' data-dismiss='modal'><bean:message bundle="RTI" key="close" /></button>
					</div>
				</div>
			</div>
		</div>
	</div> 

</html:form>

<script>

var username = '<%=request.getSession().getAttribute("username")%> ';

if (username === "") {
	alert("Something Bad happened. Please try again!");
} else {
	//call Ajax
	ajaxFunction("divisionWisePlanViewAction.do?method=getDivisionsBasedOnUserId&username_=" + username, 'divisionId');
}

 $(document)
	.ready(
	function() {
		ajaxFunction(
				'GetFilterValues.to?parentComboId=106&method=getCombo',
				'financialYear');
		
	Req="ent_frwrd";
	de_init('ent_frwrd', "test");
	
	});
	
 
 function validateFields() {
		var financialYear = document.getElementById('financialYear').value;
		var divisionId = document.getElementById('divisionId').value;
		

		if (financialYear === "" || financialYear == null) {
			alert("Please Select Financial Year.")
			return false;
		} else if (divisionId == "" || divisionId == null) {
			alert("Please Select Division.");
			return false;
		} 
		return true;
	}
</script>

</body>

</html:html>