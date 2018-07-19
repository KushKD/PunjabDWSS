<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"%>
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
	function de_add() {
		var result = validateFields();
		var status_d = validatepayDate();
		if (status_d) {
		if (result) {
			document.submitRtiForm.action = "rtiOnlineAction.do?method=save&d__mode="
					+ d__mode + "&menuId=RTI001";
		
			document.submitRtiForm.submit();
		}
		}
	}
</script>

</head>


<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="RTI1" style='display: none;'>
			<html:errors bundle="RTI" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('RTI1');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				alert(text);
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>

</logic:messagesPresent> 
<html:form action="rtiOnlineAction" method="post"
	styleId="submitRtiForm" enctype="multipart/form-data">



<div class="panel panel-danger">
		<div class="panel-body">

			<!-- Urban and Rural -->
			<div id='urban_rural'>

				<h4	class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Please
					Select Head/Field Office<span class="text-danger"> &nbsp;*</span></h4>
					
				<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-2 col-md-5 col-xs-12 col-sm-6">
						<html:radio  property="office" value="1" styleId="hoffice" onclick="hofficeselection();" >Head Office </html:radio>
				</div>

				<div class=" form-inline col-lg-2 col-md-5 col-xs-12 col-sm-6">
						<html:radio property="office" value="2" styleId="foffice" onclick="fofficeselection();">Field Office </html:radio>
				</div>
				
				<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
		</div>
	</div>


<div id="hofficedisplay" class="panel panel-danger">
		<div class="panel-body">
			<h4	class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Select
				Department</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>

         <!-- Wings and POI -->
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Wings/HOD<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="wings" styleId="wings"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Please Select</html:option>
					<html:option value="1">Sanitation Wing</html:option>
					<html:option value="2">Water Quality Wing</html:option>
					<html:option value="3">MIS Wing</html:option>
					<html:option value="4">Construction Wing</html:option>

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">PIO<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="pio" styleId="pio"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Please Select</html:option>
					<html:option value="1">Mr. test</html:option>
					<html:option value="2">Mr. test 1</html:option>
					<html:option value="3">Mrs. test 2</html:option>
					<html:option value="4">Mrs. test 3</html:option>

				</html:select>
			</div>
			
			



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

         
         <!--  Wings and POI -->

			


			
		</div>
	</div>


	<div id="fofficedisplay" class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Location
				Details</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>

			<!-- First Zone and Circle Start -->
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Zone<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="zone" styleId="zone" style="width: 150px;"
					styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?parentCircle='+this.value+'&method=fetchCircle', 'circle');">
					<html:option value=""> Select Zone</html:option>
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Circle<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="circle" styleId="circle"
					styleClass="cs2 form-control" style="width: 150px;" value=""
					onchange="ajaxFunction('GetFilterValues.to?parentDistrict='+this.value+'&method=fetchDistrict', 'district');">
					<html:option value=""> Select Circle</html:option>
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<!-- First Zone and Circle End -->





			<!-- Second District and Division Start -->
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="
					ajaxFunction('GetFilterValues.to?division='+this.value+'&method=fetchDivision', 'division');
					">
					<html:option value="">Select District</html:option>

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Division<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?subdivision='+this.value+'&method=fetchSubDivision', 'subdivision');">
					<html:option value="">Select Division</html:option>

				</html:select>
			</div>



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<!--Second District and Division Start -->




		<%-- 	<!-- Sub Division and Block -->
			 <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div> 
			 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Sub Division</label>
				<html:select property="subdivision" styleId="subdivision"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Select Sub Division</html:option>

				</html:select>
			</div> 
			
			 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="visibility: hidden;">
				<label class="text-right labledesign">Sub Division</label>
				<html:select property="subdivision" styleId="subdivision"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Select Sub Division</html:option>

				</html:select>
			</div> 

			
			 <div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div> 
			<!--  Sub Division and Block --> --%>



         <!-- Wings and POI -->
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">PIO<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="pio" styleId="pio"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Please Select</html:option>
					<html:option value="1">Mr. test</html:option>
					<html:option value="2">Mr. test 1</html:option>
					<html:option value="3">Mrs. test 2</html:option>
					<html:option value="4">Mrs. test 3</html:option>

				</html:select>
			</div>
			
			 <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				&nbsp;<br>&nbsp;
			</div> 



			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

         
         <!--  Wings and POI -->

			


			
		</div>
	</div>
	
	
	
	<!-- Applicant Details -->
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Applicant
				Details</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>
			
			
			
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Name<span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property='name' styleId='name'
					styleClass="form-control ci5" style="width:150px;"></html:text>
				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Gender<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="gender" styleId="gender"
					style="width: 150px;" styleClass="form-control cs5 " value="">
					
					<html:option value="">Select</html:option>

				</html:select>

			</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			
			
			<!-- Mobile and LandLine -->
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Mobile<span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property='mobile' styleId='mobile'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return validateKey1(event,	this,'9@10@3')"
							onchange="if(this.value.toString().length<10){
								alert('Mobile number should not be less than 10');
							}"></html:text>
				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Land Line</label>
				<html:text property='landline' styleId='landline'
					styleClass="form-control ci5" style="width:150px;"
					onkeypress="return validateKey1(event,	this,'9@10@3')"
							onchange="if(this.value.toString().length<10){
								alert('Land Line number should not be less than 10');
							}"></html:text>

			</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			
			
			<!-- District and Block -->
			<div class="row">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">District<span
						class="text-danger"> &nbsp;*</span></label>
				<html:select property="districta" styleId="districta"
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
				</div>
				
				
				
				<!-- Village -->
				<div class="row">
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

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="visibility: hidden;">
				<label class="text-right labledesign">x</label>
				<html:select property="vi" styleId="vil" value=""
					styleClass="ci5 form-control" style="width: 150px;"
					onchange="ajaxFunction('GetFilterValues.to?habitation=habitation&method=getCombo', 'habitation');">
							  
					
					<html:option value="">Select Village</html:option>
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				</div>
				
				<!-- Village -->
			
			
			<!-- Email and Address -->
	<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Email</label>
					<html:text property='email' styleId='email'
					styleClass="form-control ci5" style="width:150px;" onblur="isEmail(this)"></html:text>
				</div>



				<div class=" form-inline col-lg-6 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Address<span
						class="text-danger"> &nbsp;*</span></label>
				<html:text property='address' styleId='address'
					styleClass="form-control ci5" style="width:250px;"></html:text>

			</div>
			
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				
			</div>
			<!-- Email and Address -->
			
			
			<!-- Other Options -->
			
			<div class="row">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Education <span
						class="text-danger"> &nbsp;*</span></label>
					<html:radio property='literate' styleClass='literate' value="Literate">Literate</html:radio> &nbsp; &nbsp; &nbsp;
					<html:radio property='literate' styleClass='literate' value="Illiterate">Illiterate</html:radio>
				</div>
			
			</div>
			
			
			<div class="row" style="margin-top: 10px;">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-6" >
					
					<html:checkbox property='poverty' styleId='poverty' value="1" onclick="belowpoverty();">Is Applicant below poverty line ?</html:checkbox> &nbsp; &nbsp; &nbsp;
					</div>
			</div>
	</div>
	</div>
	
	<!-- Applicant Details -->
	
	
	<!-- RTI REQUEST DETAILS -->
	
	<!-- Applicant Details -->
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">RTI Request
				Details</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>
			
			<div class="row">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">RTI Title <span
						class="text-danger"> &nbsp;*</span></label>
						
						<html:text property='shortname' styleId='shortname'
					styleClass="form-control ci5" style="width:250px;"></html:text>
					</div>
			
			</div>
			
			<div class="row" style="margin-top: 10px;">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">RTI Details <span
						class="text-danger"> &nbsp;*</span></label>
						
						<html:textarea property='details' styleId='details'
					styleClass="form-control ci5" style="width:250px; height:60px;"></html:textarea>
					</div>
			
			</div>
			
			
			<!-- RTI Application -->
			 <div class="row" style="margin-top: 10px;">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">RTI Application</label>
						
						<html:file property='applicationFile'  styleId="applicationFile" 
					styleClass="form-control ci5" style="width:250px;"></html:file>
					
					
					</div>
			
			</div> 
			<!-- RTI Application -->
			
			<!-- Supporting Documents -->
			 <div class="row" style="margin-top: 10px;">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Supporting Documents</label>
						
						<html:file property='documentsFile' styleId="documentsFile"
					styleClass="form-control ci5" style="width:250px; "></html:file>
					</div>
			
			</div> 
			<!-- Supporting Documents -->
			
			
			</div>
			</div>
			
			
	
	<!-- RTI REQUEST DETAILS -->
	
	
	<!-- Payment Details -->
	
	<!-- Applicant Details -->
	<div id="paymentsection" class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Payment
				Details</h4>
			<div class="line"></div>
			<div id="alert_placeholder"></div>
			
			
			
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Payment Date</label> <input
					type="text" name="datePayment" style="width: 150px" id="datePayment"
					class="ci5 form-control" ></input>
			</div>	
			
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<%-- <label class="text-right labledesign">Payment Mode</label>
				<html:select property="paymentMode" styleId="paymentMode" 
					style="width: 150px;" styleClass="form-control cs5 " value="">
					
					<html:option value="">Select</html:option>
					<html:option value="">Online</html:option>
					<html:option value="">Offline</html:option>

				</html:select> --%>
				&nbsp;<br>&nbsp;
			</div>
			
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			
			
			<!-- Mobile and LandLine -->
			<div class="row">
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Payment Amount</label>
					<html:text property='amount' styleId='amount'
					styleClass="form-control ci5" style="width:150px;"></html:text>
				</div>



				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Remarks</label>
				<html:textarea property='remarks' styleId='remarks'
					styleClass="form-control ci5" style="width:150px; height:60px; margin-bottom:10px;"></html:textarea>

			</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>
			</div>
			
			
			<!-- District and Block -->
			<div class="row">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Payment Via</label>
				<html:select property="paymentVia" styleId="paymentVia"
					style="width: 150px;" styleClass="cs2 form-control">
					<html:option value="">Select Payment Type</html:option>
					<html:option value="1">Demand Draft</html:option>
					<html:option value="2">IPO</html:option>
					<html:option value="3">Cash</html:option>
					
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
					<label class="text-right labledesign">Referance Number</label>
					<html:text property='number' styleId='number'
					styleClass="form-control ci5" style="width:150px;"></html:text>
				</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				</div>
				
				
				
			
		
			
			</div>
			</div>
	
	<!-- Payment Details -->
	
	
	



</html:form>

<script>
	$(window)
			.load(
					function() {
						 ajaxFunction('GetFilterValues.to?gender=gender&method=getCombo', 'gender');
						/* $('input.float').on('input', function() {
							  this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
							}); */
						// ajaxFunction('GetFilterValues.to?toilet_type=toilet_type&method=getCombo', 'toiletType');
						ajaxFunction(
								'GetFilterValues.to?parentZone=-1&method=fetchZone',
								'zone'); 
						document.getElementById('hoffice').checked=true;	
						document.getElementById('fofficedisplay').style.display = 'none';
					});
	
	$('#datePayment,#defaultInline').datepick();
	
	function isEmail(email) {
		var email = document.getElementById("email").value.trim();
		var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (!regex.test(String(email).toLowerCase())) {
			alert('Please enter valid email id');
			return false;
		}
		return true;
	}
	
	
	 function hofficeselection() {
	    if (document.getElementById('hoffice').checked) {
	    	document.getElementById('hofficedisplay').style.display = 'block';
	    	document.getElementById('fofficedisplay').style.display = 'none';
	    }/* 
	    else document.getElementById('adjurst').style.display = 'none'; */

	} 
	 
	 function fofficeselection() {
		    if (document.getElementById('foffice').checked) {
		    	document.getElementById('fofficedisplay').style.display = 'block';
		        document.getElementById('hofficedisplay').style.display = 'none';
		    }/* 
		    else document.getElementById('adjurst').style.display = 'none'; */

		} 
	 
	 function belowpoverty() {
		    if (document.getElementById('poverty').checked) {
		    	document.getElementById('paymentsection').style.display = 'none';		        
		    }
		    else document.getElementById('paymentsection').style.display = 'block'; 

		} 
	
	
	 
	function validateFields() {
		
		var zone = document.getElementById('zone').value;
		var circle = document.getElementById('circle').value;
		var district = document.getElementById('district').value;
		var division = document.getElementById('division').value;
	//	var subdivision = document.getElementById('subdivision').value;
	//  var wings = document.getElementById('wings').value;
	//	var pio = document.getElementById('pio').value; 
		var name = document.getElementById('name').value.trim();
		var gender = document.getElementById('gender').value;
		var mobile = document.getElementById('mobile').value;
		var districta = document.getElementById('districta').value;
		var address = document.getElementById('address').value.trim();
		var shortname = document.getElementById('shortname').value.trim();
		var details = document.getElementById('details').value.trim();
		
		/* if ($('#hoffice:checked').length > 0){
			alert('hi');
			if (wings == "" || wings ==null){
			alert("Please Select Wings/HOD.");
			return false;
			}
			if (pio == "" || pio ==null){
				alert("Please Select PIO.");
				return false;
			}
		}
		else */  if ($('#foffice:checked').length > 0) {
			if (zone == "" || zone ==null){
			alert("Please Select Zone.");
			return false;
			}
			if (circle == "" || circle ==null){
				alert("Please Select Circle.");
				return false;
			}
			if (district == "" || district ==null){
				alert("Please Select District.");
				return false;
			}
			if (division == "" || division ==null){
				alert("Please Select Division.");
				return false;
			}
			if (pio == "" || pio ==null){
				alert("Please Select PIO.");
				return false;
			}
		}
		if(name == "" || name == null) {
			alert("Please Enter Name of Applicant.");
			return false;
		} 
		else if(gender == "" || gender == null) {
			alert("Please Select Gender of Applicant.");
			return false;
		} 
		else if(mobile == "" || mobile == null) {
			alert("Please Enter Mobile Number of Applicant.");
			return false;
		} 
		else if(districta == "" || districta == null) {
			alert("Please Select District in which Applicant Resides.");
			return false;
		} 
		else if(address == "" || address == null) {
			alert("Please Enter Proper Address of Applicant.");
			return false;
		} 
		else if($('.literate:checked').length==0) {
			alert("Please Specify Whether Applicant is Literate or Illiterate.");
			return false;
		}
		else if(shortname == "" || shortname == null) {
			alert("Please Enter Title of RTI.");
			return false;
		} 
		else if(details == "" || details == null) {
			alert("Please Enter RTI Details.");
			return false;
		} 
		else return true;	
	} 
	
	
	function validatepayDate() {
		//var startDate = new Date($('#startDate').val());
		var datePayment = new Date($('#datePayment').val());
		if (datePayment != '' && typeof datePayment != 'undefined'
				&& datePayment != 'Invalid Date') {
			 var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth() + 1; //January is 0!
			var yyyy = today.getFullYear();

			if (dd < 10) {
				dd = '0' + dd
			}

			if (mm < 10) {
				mm = '0' + mm
			}

			today = mm + '/' + dd + '/' + yyyy;

			var EffectiveDate = $.trim($("[id$='datePayment']").val());

			if (EffectiveDate > today) {
				alert('Payment date could not be greather than current date');
				return false;
			} 
			/* if (endDate < startDate) {
				alert('end date could not less than start date');
				return false;
			} */
		}

		return true;
	}
	
</script>

</body>

</html:html>