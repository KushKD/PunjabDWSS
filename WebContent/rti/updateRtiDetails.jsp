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

<script type="text/javascript" src="js/jquery.plugin.js"></script>

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

 <script type="text/javascript">
	var rtiData=localStorage.getItem("rtidata");
 alert(rtiData)
	 function de_add() {
		var result = validateFields();
		if (result) {
			document.submitRtiForm.action = "updateRtiDetailsAction.do?method=save&d__mode="
					+ d__mode + "&menuId=RTI001";
		
			document.submitRtiForm.submit();
		}
	} 
</script> 

</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">

		<div class="panel panel-body panel-danger" id="modalPeriod"
			style="position: absolute; min-width: 300px;; z-index: 121; background-color: #646b71; font-size: 150%; display: none;">

			<div class="row">
				<div class="col-lg-12">
					<font size="4" color="#FFFFFF"><b><html:errors
								bundle="sanitation" /></b></font>
				</div>
				<br>

				<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 ">
					<input type="button"
						class="navbar-footer btn btn-success col-lg-12" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;">
				</div>

			</div>
		</div>
		<script type="text/javascript">
			var alertObj = document.getElementById("modalPeriod");
			// center the alert box
			if (document.all && !window.opera)
				alertObj.style.top = document.documentElement.scrollTop + 50
						+ "px";
			alertObj.style.left = (document.documentElement.scrollWidth - alertObj.offsetWidth)
					/ 4 + "px";
			//alert(alertObj.style.left+":"+document.documentElement.scrollWidth+":"+ alertObj.offsetWidth);
			hide_ctrl('modalPeriod', false);
		</script>
</logic:messagesPresent> 


<html:form action="updateRtiDetailsAction" method="post"
	styleId="submitRtiForm" enctype="multipart/form-data">



<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Person Details</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Name</label>
				<label class="text-left labledesign">data.name</label>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Address</label>
				<label class="text-left labledesign">data.address</label>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Mobile Number</label>
				<label class="text-left labledesign">data.mobile</label>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Landline Number</label>
				<%-- <% if(data.landline == 0 || data.landline == null || data.landline == undefined || data.landline == ""){
				%>
					<label class="text-left labledesign"></label>;
				<% } else{%>
				<label class="text-left labledesign">data.landline</label>;
				<% }%> --%>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

		</div>
	</div>

<div class="panel panel-danger">
		<div class="panel-body">
		<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Update RTI</h4>
			<div class="line"></div>
			<div id="datatable_sh" style="display: none"
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
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">RTI Details</h4>
				</div>
				<div class="modal-body">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>

</html:form>

<script>

updateRtis.updateRtisType();
document.getElementById("datatable_sh").style.display = '';

	/* $(window)
			.load(
					function() {
						 ajaxFunction('GetFilterValues.to?gender=gender&method=getCombo', 'gender');
						 $('input.float').on('input', function() {
							  this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
							}); 
						// ajaxFunction('GetFilterValues.to?toilet_type=toilet_type&method=getCombo', 'toiletType');
						ajaxFunction(
								'GetFilterValues.to?parentZone=-1&method=fetchZone',
								'zone'); 
						document.getElementById('hoffice').checked=true;	
						document.getElementById('fofficedisplay').style.display = 'none';
					});
	
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
	
	
	 
	
	
</script>

</body>

</html:html>