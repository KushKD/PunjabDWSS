<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>


<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<link rel="stylesheet" type="text/css" href="css/common.css">
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/sanitation.js"></script>
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
	hide_ctrl('modalPeriod', true);
	
	function de_add() {
		var status = validateFields();
		
		if (status) {
			document.hallofFameForm.action = "hallofFameAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT009";
			document.hallofFameForm.submit();
		}
	}
</script>

</head>


<html:html>

	<logic:messagesPresent>
		<body bgcolor="#6699FF">
			<div id="modalContainer"></div>
			<p id="sanitation1" style='display: none;'>
				<html:errors bundle="sanitation" />
			</p>
			<script type="text/javascript">
				displayMessage(true);
				var para = document.getElementById('sanitation1');
				var text = para.firstChild.nodeValue;
				if (text != "") {
					document.getElementById("p1").innerHTML = text;
					$("#myModal").modal('show');
				}
			</script>
	</logic:messagesPresent>
<html:form action="hallofFameAction" method="post"
	styleId="hallofFameForm" enctype="multipart/form-data">


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Hall of Fame</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Name of Activity</label>
				<html:text property='nameofActivity' styleId='nameofActivity'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>
			</div>
			

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Type</label>
				<html:select property="type" styleId="type"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
					<html:option value="">Select</html:option>
					<html:option value="1">test1</html:option>
					<html:option value="2">test2</html:option>
					<html:option value="3">test3</html:option>
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Lead By</label>
				<html:text property='leadBy' styleId='leadBy'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>
			</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"></div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6"></div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-8 col-md-10 col-xs-12 col-sm-12">
				<label class="text-right labledesign" style="vertical-align:top;">Description</label>
				<html:textarea property='description' styleId='description'
					styleClass="form-control ci5" style="width:50%;min-height:11%"
					onkeypress="return lettersOnly()"></html:textarea>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
				
			<div class=" form-inline col-lg-5 col-md-6 col-xs-12 col-sm-12">
				<label class="text-right  labledesign">Attachment</label>
						<html:file property="attachment" styleId="attachment"
							styleClass="form-control"></html:file>
					
			<div class="col-lg-3 col-md-4 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


		</div>
	</div>
</div>
<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" style="width:auto"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Hall of Fame Entry Details</h4>
				</div>
				<div class="modal-body">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div> 
		</div>

	</div>
<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="hallofFameDT"
					class="display nowrap table-responsive table-bordered table-striped table-hover "
					style="width: 100%">

					<thead>
						<tr>
							<th>Name of Activity</th>
							<th>Type</th>
							<th>Lead By</th>
							<th>Description</th>
							
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</html:form>

<script type='text/javascript'>

hallofFames.HallofFameType();
document.getElementById("datatable_sh").style.display = '';

function validateFields() {
	
	var nameofActivity = document.getElementById('nameofActivity').value;
	var type = document.getElementById('type').value;
	var leadBy = document.getElementById('leadBy').value;
	var description = document.getElementById('description').value;
	var attachment = document.getElementById('attachment').value;
	
	if (nameofActivity == "" || nameofActivity == null) {
		alert("Enter Activity Name")
		return false;
	} 
	else if (type == "" || type == null) {
		alert("Please Select Activity Type");
		return false;
	}
	else if (leadBy == "" || leadBy == null) {
		alert("Please Enter Who Lead the Activity");
		return false;
	}
	else if(description == "" || description == null) {
		alert("Please Enter Description");
		return false;
	}
	else if(attachment == "" || attachment == null) {
		alert("Please Provide Attachment");
		return false;
	} 		
	else return true;	
}



</script>

</body>

</html:html>