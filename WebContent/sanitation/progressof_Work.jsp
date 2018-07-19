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
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beneficiary Entry</title>
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript"  src="js/jquery-ui.min.js"></script>
<script type="text/javascript" 	src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>
		
	
<script type="text/javascript">

function de_add() {
	//	var result = validateField(); 
		if (true) {
			document.progressofWorkForm.action = "progressofWorkAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SNT016";
			document.progressofWorkForm.submit();
		}
	}

</script>

</head>
<body>
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
				document.getElementById("p1").innerHTML=text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

	<html:form action="progressofWorkAction" method="post"
		styleId="progressofWorkForm" enctype="multipart/form-data">

		<div class="form-group">
			<div class="panel panel-danger">
				<div class="panel-body">
					<h4
						class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
						Progress of Work</h4>
					<div class="line"></div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">District:</label>
						<html:select property="district" styleId="district"
							styleClass="form-control" onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
							<html:option value="">Select Location</html:option>
							<html:options collection="districtLocations" labelProperty="label"
							property="value"></html:options>
						</html:select>

			</div>
			
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-left  labledesign">Block:</label>
						<html:select property="block" styleId="block"
							styleClass="form-control" onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'village');"> 
							<html:option value="">Please select</html:option>
						</html:select>
			</div>
			
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
					
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-left  labledesign">Village:</label>
						<html:select property="village" styleId="village"
							styleClass="form-control" onchange="ajaxFunction('GetFilterValues.to?villageId4321='+this.value+'&method=getBeneficary', 'beneficiaryName');ajaxFunction('GetFilterValues.to?villageId='+this.value+'&method=fetchGramPanchayat', 'gramPanchayat');">
							<html:option value="">Please select</html:option>
						</html:select>
			</div>
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-right  labledesign">Gram Panchayat:</label>
						<html:select property="gramPanchayat" styleId="gramPanchayat"
							styleClass="form-control">
							<html:option value="">Please select</html:option>
						</html:select>
			</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
			
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>
					<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
						&nbsp;</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
						<label class="text-left labledesign">Beneficiary Name</label>
						<html:select property="beneficiaryId" styleId="beneficiaryName"
							styleClass="form-control">
							<html:option value="">Please select</html:option>
						</html:select>
			</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;
						&nbsp;</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;
						&nbsp;</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<label class=" labledesign" style="font-size:15px; color:#2D504A"><b style="font-weight:1000">Stage</b></label>
			</div>
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<label class=" labledesign" style="font-size:15px; color:#2D504A"><b style="font-weight:1000">Status</b></label>
			</div>
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<label class=" labledesign" style="font-size:15px; color:#2D504A"><b style="font-weight:1000">Picture</b></label>
			</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
			
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						&nbsp;</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<label class=" labledesign">Stage 1</label>
			</div>
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<!-- <label class="text-left labledesign"><b>Status</b></label> -->
						<html:select property="status1" styleId="status1"
					styleClass="cs2 form-control">
					<html:option value="0">Select Status</html:option>
				</html:select>
			</div>
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-12" style="text-align:center">
						<!-- <label class="text-left labledesign"><b>Picture</b></label> -->
						<html:file property="photograph1" styleId="photograph1"
							styleClass="form-control"></html:file>
			</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
			
			<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div> -->
						
<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->						
						
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<label class=" labledesign">Stage 2</label>
			</div>
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<!-- <label class="text-left labledesign"><b>Status</b></label> -->
						<html:select property="status2" styleId="status2"
					styleClass="cs2 form-control">
					<html:option value="0">Select Status</html:option>
				</html:select>
			</div>
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-12" style="text-align:center">
						<!-- <label class="text-left labledesign"><b>Picture</b></label> -->
						<html:file property="photograph2" styleId="photograph2"
							styleClass="form-control"></html:file>
			</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
						
				<!-- <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div> -->

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
																									
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<label class=" labledesign">Stage 3</label>
			</div>
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-6" style="text-align:center">
						<!-- <label class="text-left labledesign"><b>Status</b></label> -->
						<html:select property="status3" styleId="status3"
					styleClass="cs2 form-control">
					<html:option value="0">Select Status</html:option>
				</html:select>
			</div>
			
			<div class="form-inline col-lg-4 col-md-4 col-xs-12 col-sm-12" style="text-align:center">
						<!-- <label class="text-left labledesign"><b>Picture</b></label> -->
						<html:file property="photograph3" styleId="photograph3"
							styleClass="form-control"></html:file>
			</div>		

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
							
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div>

<!-- -------------------------------------------------------------------------------------------------------------------------------------------- -->
									
					
				
					
					

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
							<th>Village</th>
							<th>Beneficiary Name</th>
							<th>Details</th>
							
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
<script type="text/javascript">	
$(document).ready(function() {
    ajaxFunction('GetFilterValues.to?status=status&method=getCombo', 'status1');
    ajaxFunction('GetFilterValues.to?status=status&method=getCombo', 'status2');
    ajaxFunction('GetFilterValues.to?status=status&method=getCombo', 'status3');
});
 
/* hallofFames.HallofFameType();
document.getElementById("datatable_sh").style.display = '';
 
 function validateField(){
	 var personName=$('#personName').val();
	 var fatherSpouseName=$('#fatherSpouseName').val();
	 var benifCategory=$('#benifCategory').val();
	 var cast=$('#cast').val();
	 var religion=$('#religion').val();
	 var phoneNumber=$('#phoneNumber').val();
	 var gender=$('#gender').val();
	 var photograph=$('#photograph').val();
	 var district=$('#district').val();
	 var block=$('#block').val();
	 var village=$('#village').val();
	 var gramPanchayat=$('#gramPanchayat').val();
	 var poiType=$('#poiType').val();
	 var poiNumber=$('#poiNumber').val();
	 var adharNumber=$('#adharNumber').val();
	 var electricityCon=$('#electricityCon').val();
	 var electricityBill=$('#electricityBill').val();
	 var bankName=$('#bankName').val();
	 var branch=$('#branch').val();
	 var accountNo=$('#accountNo').val();
	 var ifsCode=$('#ifsCode').val();

	 if(personName===''||personName===null){
		 alert('Name field should not be blank');
		 return false;
	 }
	 if(fatherSpouseName==''||fatherSpouseName==null){
		 alert('Father/Spouse Name field should not be blank');
		 return false;
	 }
	 if(benifCategory==''||benifCategory==null){
		 alert('B. Category field should not be blank');
		 return false;
	 }
	 if(cast==''||cast==null){
		 alert('Cast field should not be blank');
		 return false;
	 }
	 if(religion==''||religion==null){
		 alert('Religion field should not be blank');
		 return false;
	 }
	 if(phoneNumber==''||phoneNumber==null){
		 alert('Phone Number field should not be blank');
		 return false;
	 }
	 if(phoneNumber.toString().length<10){
			alert('Mobile number should not be less than 10');
			return false;
		}
	 if(gender==''||gender==null){
		 alert('Gender field should not be blank');
		 return false;
	 }
	 if(photograph==''||photograph==null){
		 alert('Photograph field should not be blank');
		 return false;
	 }
	 if(district==''||district==null){
		 alert('District field should not be blank');
		 return false;
	 }
	 if(block==''||block==null){
		 alert('Block field should not be blank');
		 return false;
	 }
	 if(village==''||village==null){
		 alert('Village field should not be blank');
		 return false;
	 }
	 if(gramPanchayat==''||gramPanchayat==null){
		 alert('Gram Panchayat field should not be blank');
		 return false;
	 }
	 if(poiType==''||poiType==null){
		 alert('POI Type field should not be blank');
		 return false;
	 }
	 if(poiNumber==''||poiNumber==null){
		 alert('POI Number field should not be blank');
		 return false;
	 }
	 if(adharNumber==''||adharNumber==null){
		 alert('Aadhar Card Number field should not be blank');
		 return false;
	 }
	 if(adharNumber.toString().length<12){
			alert('Aadhar Number should not be less than 12');
			return false;
		}
	 if(electricityCon==''||electricityCon==null){
		 alert('Electricity Connection Act. No field should not be blank');
		 return false;
	 }
	 if(electricityBill==''||electricityBill==null){
		 alert('Electricity Bill field should not be blank');
		 return false;
	 }
	 if(bankName==''||bankName==null){
		 alert('Name of Bank field should not be blank');
		 return false;
	 }
	 if(branch==''||branch==null){
		 alert('Branch field should not be blank');
		 return false;
	 }
	 if(accountNo==''||accountNo==null){
		 alert('Account Number field should not be blank');
		 return false;
	 }
	 if(ifsCode==''||ifsCode==null){
		 alert('IFSC Code field should not be blank');
		 return false;
	 }
	
 
	 return true;
 } */
</script>
</html:html>