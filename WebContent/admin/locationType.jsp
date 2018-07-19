<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
 <link href="css/displaytag.css" rel="stylesheet" type="text/css" /> 

<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">

<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="js/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>
<script src="js/jquery.toastmessage.js" type="text/javascript"></script>
<link href="css/jquery.toastmessage.css" rel="stylesheet"
	type="text/css" />
	
	
<script type="text/javascript" src="js/locationMaster.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>  
<script type="text/javascript">
	hide_ctrl('modalPeriod', true);

	function de_find() {
		document.schemeMasterForm.action = "locationTypeAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=ADM015";
		//locationMaster.location1("locationMasterAction.do?method=populate&d__mode="+d__mode+"&menuId=ADM014");
		document.schemeMasterForm.submit();
		

	}

	function de_add() {
		//var result = true /* isCheckedWaterWorksLocation() */;
		var result=validateEndDate();
		if (result) {
			document.locationTypeForm.action = "locationTypeAction.do?method=save&d__mode="
					+ d__mode + "&menuId=ADM015";
			document.locationTypeForm.submit();

			/* locationMaster1.location("locationMasterAction.do?method=save&d__mode="+d__mode+"&menuId=ADM014");
			document.getElementById("datatable_sh").disabled =false; */

		}
	}
</script>

 


</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<%-- <div id="modalPeriod"
			style="position: absolute; width: 600px; border: 3px solid black; background-color: #00A2E2; font-size: 150%; text-align: Left; display: none;">
			<table width="600px">
				<tr>
					<td><font size="3" color="white"><b><html:errors
									bundle="Waterquality" /></b></font></td>
				</tr>
				<tr>
					<td align="center"><input type="button" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;"></td>
				</tr>
			</table>
		</div> --%>
		<div class="panel panel-body panel-danger" id="modalPeriod" style="position: absolute; min-width:300px; ; z-index:121;   background-color: #646b71; font-size: 150%;  display: none;">
			
			<div class="row" >
			<div class="col-lg-12">
			<font size="4" color="#FFFFFF"><b><html:errors
									bundle="Waterquality" /></b></font></div>   <br>
									
									<div class="col-lg-12 col-sm-12 col-xs-12 col-md-12 ">
				<input type="button" class="navbar-footer btn btn-success col-lg-12" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;"></div>
						
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
<html:form action="locationTypeAction" method="post" styleId="locationTypeForm ">

	
		<div class="panel panel-danger">
    <div class="panel-body">
      <h4 class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"> Location Type Master </h4>
     <div class="line"></div>
      <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div>
      <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" >Location Type</label>
				<html:text property='locationType' styleId='locationType' styleClass="ci5 form-control"   style="width: 150px;"
					onchange="ajaxFunction('locationMasterAction.do?locationName='+this.value+'&method=fetchSublocationType', 'locationType')"></html:text>
      </div>
								
								
								
					 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign">Status</label>
						
							<html:select property='status' styleId='status' styleClass="ci5 form-control"  style="width:150px" >
								<html:option value="1">Active</html:option>
								<html:option value="0">InActive</html:option>

							</html:select>

						
					</div>
					 <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div> 
					  <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div> 
					   <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div> 
				
					  
					   
					  <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign" >Start date</label>
						<input type="text" name="startDate" style="width:150px" id="startDate" class="ci5 form-control" value = "<fmt:formatDate value="${dateVar}" pattern="MM-dd-yyyy"  />"/>
					</div>


					<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right labledesign">End date</label>
							<input type ='text' name="endDate" style="width:150px" id="endDate" class="ci5 form-control"/>
					</div>
					 <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp; &nbsp;</div> 
					 <input type='hidden' name='locationTypeId' id='locationTypeId'>    
      </div>
      </div>
	
				<!-- Table -->
				
				<div class="panel panel-danger">
     <div class="panel-body">
    <div id="datatable_sh"  style="display:none" class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body" >
				<table id="locationTypeDT" class="display table-responsive table-bordered table-striped table-hover nowrap" cellspacing="0" width="100%">

		<thead>
			<tr>
				<th></th>
				<th>Location Type</th>
				<th>Status</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>id</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	</div>
	</div>
	</div>
			
				
	

	
</html:form>

</body>
<script type='text/javascript'>
	$('#startDate,#defaultInline').datepick();
 	$('#endDate,#defaultInline').datepick();
 
/*  document.getElementById("datatable_sh").style.display = '';
	locationTypeMaster.locationType(); */
/* 	function showStickySuccessToast(data) {
		$().toastmessage('showToast', {
			text : data,
			sticky : true,
			position : 'top-center',
			type : 'success',
			closeText : '',
			close : function() {

			}
		});

	}
	function showSuccessToast() {
		$().toastmessage('showSuccessToast',
				"Success Dialog which is fading away ...");
	}
	function showErrorToast() {
		$().toastmessage('showErrorToast',
				"Error Dialog which is fading away ...");
	}
	function showStickyErrorToast(data) {

		$().toastmessage('showToast', {
			text : 'Error Dialog which is sticky',
			sticky : true,
			position : 'center',
			type : 'error',
			closeText : '',
			close : function() {
				console.log("toast is closed ...");
			}
		});
	} */
<%-- <%if (request.getAttribute("success") != null) {%>
	locationMaster.location1();
	document.getElementById("datatable_sh").style.display = '';
<%}%> --%>
function validateEndDate(){
	var startDate = new Date($('#startDate').val());
	var endDate = new Date($('#endDate').val());
if(endDate!=''&&typeof endDate!='undefined'&&endDate!='Invalid Date'){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd < 10) {
	    dd = '0' + dd
	} 

	if(mm < 10) {
	    mm = '0' + mm
	} 

	today = mm + '/' + dd + '/' + yyyy;

	var EffectiveDate = $.trim($("[id$='endDate']").val());
	
	if (EffectiveDate < today){
		alert('end date could not less than current date')
		return false;
	}
	if (endDate < startDate){
		alert('end date could not less than start date')
		return false;
	}
}
	
	
	
	return true;
}

	$(document).ready(function(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!

		var yyyy = today.getFullYear();
		if(dd<10){
		    dd='0'+dd;
		} 
		if(mm<10){
		    mm='0'+mm;
		} 
		var today = mm+'/'+dd+'/'+yyyy;
		document.getElementById("startDate").value = today;
		
		if(document.getElementById('endDate')==='undefined'){
			document.getElementById('endDate').value='';
		}
		
	});
	 locationTypeMaster.locationType();
	 document.getElementById("datatable_sh").style.display = '';
	
	/* $(document).ready(function(){
	    $("#startDate").datepicker({
	        numberOfMonths:1,
	        onSelect: function(selected) {
	          $("#endDate").datepicker("option","minDate", selected)
	        }
	    });
	    $("#endDate").datepicker({ 
	        numberOfMonths: 1,
	        onSelect: function(selected) {
	           $("#startDate").datepicker("option","maxDate", selected)
	        }
	    });  
	});
 */
 
/*  locationTypeMaster.locationType();
 document.getElementById("datatable_sh").style.display = ''; */
/*  alert('njbjj')
 alert('dfdfdddfdf'+Req)
 if (Req=="ent_add"){
	 locationTypeMaster.locationType();
	 document.getElementById("datatable_sh").style.display = '';
	}
 */
<%--  <%
 String d__mode = (String) request.getAttribute("d__mode");		
 %>
	
 d__mode="<%=d__mode%>";
 alert('d__mode----'+d__mode);
 if(d__mode==='ent_add'){
	 alert('hiiiii');
	 locationTypeMaster.locationType();
		document.getElementById("datatable_sh").style.display = '';
 }
	 --%>
</script>
</html:html>