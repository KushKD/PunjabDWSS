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
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery.plugin.js"></script>


<script type="text/javascript">
	hide_ctrl('modalPeriod', true);

	function de_add() {
		var status = validateFields();
		if (status) {
			document.gramPanchayatRegisterForm.action = "gramPanchayatRegisterAction.do?method=save&d__mode="
					+ d__mode + "&menuId=SAN001";
			document.gramPanchayatRegisterForm.submit();
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
<html:form action="gramPanchayatRegisterAction" method="post"
	styleId="gramPanchayatRegisterForm">


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Location
				Details</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District</label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?destrict='+this.value+'&method=fetchBlock', 'block');">
					<html:option value="">Select Location</html:option>
					<html:options collection="districtLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Block</label>
				<html:select property="block" styleId="block"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?block='+this.value+'&method=fetchVillage', 'villageId');">
					<html:option value="">Please Select Block</html:option>
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Village</label>
				<html:select property="villageId" styleId="villageId" value=""
					styleClass="ci5 form-control" style="width: 150px;"
					onchange="ajaxFunction('GetFilterValues.to?habitation=habitation&method=getCombo', 'habitation');">
							  
					
					<html:option value="">Select Village</html:option>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Habitation</label> 
				<html:select property="habitation" styleId="habitation"
					style="width: 150px;" styleClass="form-control cs5 " value="" >
					<html:option value="">Select Habitation</html:option>

				</html:select>

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
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Family
				Details</h4>
			<div class="line"></div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Family ID</label>
				<html:text property='familyId' styleId='familyId'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Family Head </label>
				<html:text property='familyheadName' styleId='familyheadName'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"></html:text>


			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Father/Husband </label>
				<html:text property='fatherHusbandName' styleId='fatherHusbandName'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return lettersOnly()"  
					></html:text>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Gender</label>
				<html:select property="gender" styleId="gender"
					style="width: 150px;" styleClass="form-control cs5 " value=""
					onchange="ajaxFunction('GetFilterValues.to?category=category&method=getCombo', 'category');">
					
					<html:option value="">Select</html:option>

				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Category</label>
				<html:select property="category" styleId="category"
					style="width: 150px;" styleClass="form-control cs5 " value="" 
					
					onchange="
					    if(this.value == 12){
					    ajaxFunction('GetFilterValues.to?apl=apl&method=getCombo', 'subcategory');
					     ajaxFunction('GetFilterValues.to?apl_card_combo=apl_card_combo&method=getCombo', 'cardType');
					    }else{
					    ajaxFunction('GetFilterValues.to?bpl=bpl&method=getCombo', 'subcategory');
					     ajaxFunction('GetFilterValues.to?bpl_card_combo=bpl_card_combo&method=getCombo', 'cardType');
					    }
					">
					
					<html:option value="">Select </html:option>

				</html:select>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Sub Category</label>
				<html:select property="subcategory" styleId="subcategory" value=""
					styleClass="ci5 form-control" style="width: 150px;" >
					<html:option value="">Select </html:option>
				</html:select>
			</div>

			

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Card Type</label>
				<html:select property="cardType" styleId="cardType"
					style="width: 150px;" styleClass="form-control cs5 " value=""
					
					>
					<html:option value="">Select </html:option>

				</html:select>

			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Aadhaar Number</label>
				<html:text property='aadhaarNumber' styleId='aadhaarNumber'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return validateKey1(event,	this,'9@12@3')"></html:text>


			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Caste</label>
				<html:select property="caste" styleId="caste" value=""
					styleClass="ci5 form-control" style="width: 150px;" >
					<html:option value="">Select Caste</html:option>
				</html:select>
			</div>



		</div>
	</div>


	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">As
				Per Baseline Survey</h4>
			<div class="line"></div>

			<div class="row">
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
				<div class="form-inline col-lg-5">
					<label class="col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Had
						Toilet Before Survey </label>
						<html:select property="hadToiletBefore" styleId="hadToiletBefore"
					style="width: 150px;" styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 "  >
					<html:option value="">Select</html:option>

				</html:select>
					

				</div>

				<div class="form-inline col-lg-5">
					<label class=" col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Toilet
						Constructed From </label>
						<html:select property="collDate" styleId="collDate"
					style="width: 150px;" styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 " >
					<html:option value="">Select</html:option>

				</html:select>
						

				</div>
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
			</div>


			<div class="row" style="margin-top: 5px;">
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
				<div class="form-inline col-lg-5">
					<label class="col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Had
						Functional Toilet </label>
					<html:select property='functionalToilet' styleId='functionalToilet' style="width: 150px;"
						styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 "
						onkeypress="return lettersOnly()">
						
						<html:option value="">Select </html:option>
						</html:select>

				</div>

				<div class="form-inline col-lg-5">
					<label class=" col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Is
						Having Toilet Functional Toilet Used </label>
					<html:select property='havingFunctionalToilet'
						styleId='havingFunctionalToilet'
						styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 pull-left" style="width: 150px;"
						onkeypress="return lettersOnly()"
						>
						<html:option value="">Select </html:option>
					
						</html:select>


				</div>
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
			</div>

			<div class="row" style="margin-top: 5px;">
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
				<div class="form-inline col-lg-5">
					<label class="col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Is
						Water Facility Available </label>
					<html:select property='waterfacility' styleId='waterfacility'
						styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 " style="width: 150px;"
						onkeypress="return lettersOnly()" >
						<html:option value="">Select </html:option>
					
						</html:select>

				</div>

				<div class="form-inline col-lg-5">
					<label class=" col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Is
						Covered </label>
					<html:select property='isCovered' styleId='isCovered'
						styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 pull-left" style="width: 150px;"
						onkeypress="return lettersOnly();" >
						
						<html:option value="0">Select </html:option>
						</html:select>


				</div>
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
			</div>

			<div class="row" style="margin-top: 5px;">
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
				<div class="form-inline col-lg-5">
					<label class="col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Toilet
						Type </label>
					<html:select property='toiletType' styleId='toiletType'
						styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 " style="width: 150px;"
						onkeypress="return lettersOnly()">
						<html:option value="">Select </html:option>
						</html:select>

				</div>


			</div>
			<div class="row" style="margin-top: 5px;">
				<div class="col-lg-1 hidden-xs hidden-sm col-md-1"></div>
				<div class="form-inline col-lg-5">
					<label class="col-lg-6 col-md-6 col-xs-12 col-sm-12 text-right">Remarks
					</label>
					<html:textarea property='remarks' styleId='remarks'
						style="min-height:40px; min-width:200px;"
						styleClass="form-control col-lg-6 col-md-6 col-xs-12 col-sm-12 "
						onkeypress="return lettersOnly()"></html:textarea>

				</div>


			</div>



		</div>
	</div>

</html:form>

<script>

$(window).load(function() {
    ajaxFunction('GetFilterValues.to?toilet_type=toilet_type&method=getCombo', 'toiletType');
    ajaxFunction('GetFilterValues.to?gender=gender&method=getCombo', 'gender');
	ajaxFunction('GetFilterValues.to?caste_combo=caste_combo&method=getCombo', 'caste');
	
	ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'hadToiletBefore');
	ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'functionalToilet');
	ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'isCovered');
	ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'waterfacility');
	ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'havingFunctionalToilet');
	ajaxFunction('GetFilterValues.to?toilet_constructed=toilet_constructed&method=getCombo', 'collDate');
});



function validateFields() {
	
	var district = document.getElementById('district').value;
	var block = document.getElementById('block').value;
	var villageId = document.getElementById('villageId').value;
	var habitation = document.getElementById('habitation').value;
	var familyId = document.getElementById('familyId').value;
	var familyheadName = document.getElementById('familyheadName').value;
	var fatherHusbandName = document.getElementById('fatherHusbandName').value;
	var gender = document.getElementById('gender').value;
	var category = document.getElementById('category').value;
	var subcategory = document.getElementById('subcategory').value;
	var cardType = document.getElementById('cardType').value;
	var aadhaarNumber = document.getElementById('aadhaarNumber').value;
	var caste = document.getElementById('caste').value;
	var hadToiletBefore = document.getElementById('hadToiletBefore').value;
	var collDate = document.getElementById('collDate').value;
	var functionalToilet = document.getElementById('functionalToilet').value;
	var havingFunctionalToilet = document.getElementById('havingFunctionalToilet').value;
	var waterfacility = document.getElementById('waterfacility').value;
	var isCovered = document.getElementById('isCovered').value;
	var toiletType = document.getElementById('toiletType').value;
	var remarks = document.getElementById('remarks').value;
	
	
	
	
	

	if (district === "" || district == null) {
		alert("Enter District ")
		return false;
	} else if (block == "" || block == null) {
		alert("Please Select Block");
		return false;
	}else if (villageId == "" || villageId == null) {
		alert("Please Select village");
		return false;
	}else if (habitation == "" || habitation == null) {
		alert("Please Select Habitation ");
		return false;
	}else if (familyId == "" || familyId == null) {
		alert("Please Enter  Family ID");
		return false;
	} else if (familyheadName == "" || familyheadName == null) {
		alert("Please enter Family Head Name");
		return false;
	} else if (fatherHusbandName == "" || fatherHusbandName == null) {
		alert("Please Select Father/Husband Name");
		return false;
	}else if(gender =="" || gender ==null) {
		alert("Please Select Gender");
		return false;
	} else if (category == "" || category ==null) {
		alert("Please select Category");
		return false;
	} else if (subcategory == "" || subcategory ==null) {
		alert("Please select Sub Category");
		return false;
	} else if (cardType == "" || cardType ==null) {
		alert("Please select Card Type");
		return false;
	} else if (aadhaarNumber == "" || aadhaarNumber ==null) {
		alert("Please select Aadhaar Number");
		return false;
	} else if (caste == "" || caste ==null) {
		alert("Please select caste");
		return false;
	} else if (hadToiletBefore == "" || hadToiletBefore ==null) {
		alert("Please select Had Toilet Before");
		return false;
	} 
	
	
	else if (collDate == "" || collDate ==null) {
		alert("Please select Toilet Constructed from");
		return false;
	}
	else if (functionalToilet == "" || functionalToilet ==null) {
		alert("Please select , Is the toilet functionl");
		return false;
	}
	else if (havingFunctionalToilet == "" || havingFunctionalToilet ==null) {
		alert("Please select , Weather the toilet is functional or not.");
		return false;
	}
	
	else if (waterfacility == "" || waterfacility ==null) {
		alert("Please select , Is there water facility in the toilet");
		return false;
	}
	else if (isCovered == "" || isCovered ==null) {
		alert("Please select , Is the toilet covered.");
		return false;
	}
	else if (toiletType == "" || toiletType ==null) {
		alert("Please select Toilet Type");
		return false;
	}
	
	else if (remarks == "" || remarks ==null) {
		alert("Please enter remarks");
		return false;
	}
	return true;
}
</script>

</body>

</html:html>