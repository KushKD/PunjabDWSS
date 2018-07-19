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
<link href="css/form.css" rel="stylesheet" type="text/css"><!-- 
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" /> -->



<link href="css/datatables.min.css" rel="stylesheet" type="text/css" /><!-- 
<link rel="stylesheet" type="text/css" href="css/common.css"> -->
<script rel="stylesheet" href="css/bootstrap.min.css"></script>
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>
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
<html:form action="assignrtiOnlineAction" method="get"
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
				
					
					<div class="modal-footer">
					<button type="button" class="btn btn-success" >Submit</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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


	
</script>

</html:html>