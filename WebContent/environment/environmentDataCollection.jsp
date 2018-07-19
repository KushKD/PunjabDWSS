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





<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">

<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>

<script type="text/javascript" src="js/eds_DataSheet.js"></script>
<script type="text/javascript" src="js/buttons.flash.min.js"></script>
<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script>
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script type="text/javascript" src="js/buttons.colVis.min.js"></script>




<style>
.designlable {
	min-width: 200px;
	max: width: 200px;
}
</style>




<script type="text/javascript">
	hide_ctrl('modalPeriod', true);


	function de_modify() {
		if (d__mode == 'ent_modify') {
			var status = validateFields();
			 if (status){
				 var eds_value = document.getElementById("edsId").value;
				 //alert(eds_value);
				document.environmentDataCollectionForm.action = "environmentDataCollectionAction.do?method=update&d__mode="
						+ d__mode + "&menuId=ENV001";
				document.environmentDataCollectionForm.submit();
			}
		}
	}

	function de_add() {
		var status = validateFields();
		 if (status) {
			document.environmentDataCollectionForm.action = "environmentDataCollectionAction.do?method=save&d__mode="
					+ d__mode + "&menuId=ENV001";
			document.environmentDataCollectionForm.submit();
		} 
	}
	

</script>



</head>


<html:html>

 <%-- <logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="environment" style='display: none;'>
			<html:errors bundle="env" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('environment');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent> --%>

 <logic:messagesPresent>
		<body bgcolor="#6699FF">

			<p id="environment" style='display: none;'>
				<html:errors bundle="env" />
			</p>
			<script type="text/javascript">
				var para = document.getElementById('environment');
				var text = para.firstChild.nodeValue;
				//alert(text);
				if (text != "") {
					alert("Records Saved Successfully.");
					localStorage.setItem("edsId", text);
					//alert("edsId"+text); 
					var sucessurl = "environmentDataCollectionBaseLineEnvironmentAction.do";
					window.location.href = sucessurl;
				}
			</script>
	</logic:messagesPresent> 
<h4
	class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"
	style="visibility: hidden;">Lorem ipsum dolor sit amet,
	consectetur adipiscing elit. Suspendisse a dui erat. Vivamus in maximus
	neque, et elementum quam. Vivamus in maximus neque, et elementum quam.
</h4>

<html:form action="environmentDataCollectionAction" method="post"
	styleId="environmentDataCollectionForm">

	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"
				style="margin-top: -20px;">General</h4>
			<div class="line"></div>




			<!-- First Zone and Circle Start -->
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
					
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.district" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="district" styleId="district"
						style="width: 200px;" styleClass="cs2 form-control"
						onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
						<html:option value="">Select Location</html:option>
						<html:options collection="districtLocations" labelProperty="label"
							property="value"></html:options>
					</html:select>
				</div>
				
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.block" /></label>
					<html:select property="block" styleId="block"
						styleClass="cs2 form-control" style="width: 200px;" value=""
						onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
						<html:option value="">Please Select Block</html:option>
					</html:select>

				</div>

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>



			<!-- District and Block -->
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>

				

				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.village" /></label>
					<html:select property="villageId" styleId="villageId" value=""
						styleClass="ci5 form-control" style="width: 200px;"
						onchange="getGamPanchayat(this.value);ajaxFunction('receiveSampleAction.do?villageId='+this.value+'&method=fetchScheme', 'schemeId');
						">


						<html:option value="">Select Village</html:option>
					</html:select>
				</div>
				
				
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.gramPanchayat" /></label>
					<html:select property="vi" styleId="vil" value=""
						styleClass="ci5 form-control" style="width: 200px;">


						<html:option value="">Select Gram Panchayat</html:option>
					</html:select>
				</div>


				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			
			
				<!-- Scheme Type  and Scheme Category -->
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.zone" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="zone" styleId="zone" style="width: 200px;"
						styleClass="cs2 form-control"
						onchange="ajaxFunction('GetFilterValues.to?parentCircle='+this.value+'&method=fetchCircle', 'circle');">
						<html:option value=""> Select Zone</html:option>
					</html:select>
				</div>


				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.schemeType" /></label>
					<html:select property="schemeType" styleId="schemeType"
						styleClass="cs2 form-control" style="width: 200px;" value="">
						<html:option value=""> Select Scheme Type</html:option>
					</html:select>

				</div>

				

				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
				<!-- Scheme Type  and Scheme Category -->
			
			

			<!-- gram Panchayat and scheme Id -->
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.schemeCategory" /></label>
					<html:select property="schemeCategory" styleId="schemeCategory" value=""
						styleClass="ci5 form-control" style="width: 200px;">


						<html:option value="">Select Category</html:option>
					</html:select>
				</div>


			
			
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px;">
					<label class="text-right labledesign"><bean:message
							bundle="env" key="environment.schemeId" /></label>
					<html:select property="schemeId" styleId="schemeId" value=""
						styleClass="ci5 form-control" style="width: 200px;">


						<html:option value="">Select Scheme</html:option>
					</html:select>
				</div>
			
			</div>

			<!-- Gram Panchayat and scheme ID -->
			
			
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; visibility: hidden;">
					
					<html:text property="edsId" styleId="edsId"  styleClass="ci5 form-control" style="width: 200px;">

					</html:text>
				</div>
			
			<!-- Proceed Button -->
	<!-- <div class="row" style="margin-top: 10px;">
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>


		<div class="col-lg-4 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-primary pull-right" style="width: 150px;"
				onclick="de_add()">Save</button>
		</div>
		
		<div class="col-lg-4 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning " style="width: 150px;"
				onclick="de_add()">Save and Continue</button>
		</div>

		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>



	</div> -->


	<!-- Proceed Button -->


<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_eds" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="edsDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">

					<thead>
						<tr>
							<th></th>
							<th>Scheme Name</th>
							<th>Scheme Type</th>
							<th>District</th>
							<th>Block</th>
							<th>Village</th>
							<!-- <th>Village</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>status</th>
							<th>Lab Id</th> -->

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





</html:form>

</body>
<script type='text/javascript'>

$(document).ready(function() {
	ajaxFunction( 'GetFilterValues.to?parentZone=-1&method=fetchZone', 'zone');
	ajaxFunction('GetFilterValues.to?parentComboId=2058&method=getCombo', 'schemeType');
	ajaxFunction('GetFilterValues.to?parentComboId=2103&method=getCombo', 'schemeCategory');
	document.getElementById("datatable_eds").style.display = '';
	EdsMaster.EdsMasterType();
});


	
	
	
	function getGamPanchayat(id){
		ajaxFunction("environmentDataCollectionAction.do?method=getGramPanchayat&villageId="+id, 'vil');
	}

	
	
	
	
	function validateFields(){
		
		var zone = document.getElementById('zone').value;
		var district = document.getElementById('district').value;
		var block = document.getElementById('block').value;
		var villageId = document.getElementById('villageId').value;
		var vil = document.getElementById('vil').value;
		
		var schemeType = document.getElementById('schemeType').value;
		var schemeCategory = document.getElementById('schemeCategory').value;
		var schemeId = document.getElementById('schemeId').value;
		
		
		
		if(zone == "" || zone == null){
			alert("Please Select Zone");
			return false;
		}
		else if(district == "" || district == null){
			alert("Please Select District");
			return false;
		}
		else if(block == "" || block == null){
			alert("Please Select Block");
			return false;
		}
		else if(villageId == "" || villageId == null){
			alert("Please Select VillageID");
			return false;
		}
		else if(vil == "" || vil == null){
			alert("Please Select Gram Panchayat");
			return false;
		}
		else if(schemeType == "" || schemeType == null){
			alert("Please Select Type of Scheme");
			return false;
		}
		else if(schemeCategory == "" || schemeCategory == null){
			alert("Please Select Category of Scheme ");
			return false;
		}
		else if(schemeId == "" || schemeId == null){
			alert("Please Select Scheme Name");
			return false;
		}
		
		return true;
	}
	
</script>
<script type='text/javascript'>
	$(document).ready(function() {
		$('.select-checkbox').prop('disabled', true);
	});
</script>
</html:html>