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



<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>

<style>
.designlable {
	min-width: 200px;
	max: width: 200px;
}
</style>





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
				//localStorage.setItem("edsId", text);
				//alert(text);
				var sucessurl = "environmentDataCollectionSocialAction.do";
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

<html:form action="environmentDataCollectionBaseLineEnvironmentAction" method="post"
	styleId="environmentDataCollectionBaseLineEnvironmentForm">  

	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Baseline Environment</h4>
			<div class="line"></div>

			<!-- Topography and Type of Soil -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.topography" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="topography" styleId="topography"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value=""> Select Topography</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.soil" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="soil" styleId="soil" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select Soil</html:option>

					</html:select>
				</div>



			</div>
			<!-- Topography and Type of Soil -->


			<!-- Rainfall and Temperatur -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.rainfall" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="rainfall" styleId="rainfall"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value=""> Select Rainfall</html:option>
					</html:select>
				</div>

				<%-- <div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.temperature" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="temperature" styleId="temperature"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select Temperature</html:option>

					</html:select>
				</div> --%>


			</div>
			<!-- Rainfall and   -->
			
			
			<!-- Minimum and Maximum Temperature -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.temperatureMin" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:text property="temperatureMin" styleId="temperatureMin"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:text>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.temperatueMax" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:text property="temperatueMax"
						styleId="temperatueMax" style="width: 200px;"
						styleClass="cs2 form-control">


					</html:text>
				</div>



			</div>
				<!-- Minimum and Maximum Temperature -->



			<!-- Scope of Land and Predominent Wind Direction -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.scopeOfLand" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:textarea property="scopeOfLand" styleId="scopeOfLand"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:textarea>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.predominentWindDirection" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="predominentWindDirection"
						styleId="predominentWindDirection" style="width: 200px;"
						styleClass="cs2 form-control">

                         <html:option value="">Select</html:option>

					</html:select>
				</div>



			</div>
			<!-- Scope of Land and Predominent Wind Direction  -->


			<!--WaterTable (Depth Below Ground Level and Existing water body within village -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterTable" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="waterTable" styleId="waterTable"
						styleClass="form-control  " style="width: 200px;">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				



			</div>
			<!-- Scope of Land and Predominent Wind Direction  -->
			
			
			
			
			
			<!-- existingWaterBodyWithinVillage and others eason  -->
		<div class="col-lg-12" style="margin-top: 5px;">
			
			<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.existingWaterBodyWithinVillage" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="existingWaterBodyWithinVillage"
						styleId="existingWaterBodyWithinVillage" styleClass="form-control"
						style="width: 200px;" onchange="hideTextOthers(this.value);">
						<html:option value="">Select </html:option>

					</html:select>
				</div>
				
				<div class=" form-inline col-lg-6" style="display: none;" id="othersOption">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.existingWaterBodyWithinVillageOthers" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:textarea property="existingWaterBodyWithinVillageOthers"
						styleId="existingWaterBodyWithinVillageOthers" style="width: 200px;"
						styleClass="cs2 form-control">


					</html:textarea>
				</div>
			
			</div>
			
			
			<!-- existingWaterBodyWithinVillage and others reason-->
			


			<!--if Pond and Water Logging Problems Yes/No-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.ifPondCurrentUse" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select multiple="multiple" property="ifPondCurrentUse" styleId="ifPondCurrentUse"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterLoggingProblemsYesNo" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="waterLoggingProblemsYesNo"
						styleId="waterLoggingProblemsYesNo" style="width: 200px;"
						styleClass="cs2 form-control" onchange="toggle(this.value);">
						<html:option value="">Select </html:option>

					</html:select>
				</div>

			</div>
			<!--if Pond and Water Logging Problems Yes/No-->



			<!--  IF Yes Water Loggin Problem within village/villages   

NAme of Area
                        Area Under Water Logging 
                        Period of Water Logging 
                        Population affectd by water Logging
                        contamination of Drinking Water Sources from water Logging -->



			<div id="waterLoggingProblemsYes" style="display:none;">

				<!--Name of Areas and Area Under Water Logging-->
				<div class="col-lg-12" style="margin-top: 5px;">

					<div class=" form-inline col-lg-6">
						<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
								bundle="env" key="environment.nameOfAreas" /><span
							class="text-danger">&nbsp;*</span></label>
						<html:textarea property="nameOfAreas" styleId="nameOfAreas"
							style="width: 200px;" styleClass="cs2 form-control">

						</html:textarea>
					</div>

					<div class=" form-inline col-lg-6">
						<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
								bundle="env" key="environment.areaUnderWaterLogging" /><span
							class="text-danger">&nbsp;*</span></label>
						<html:textarea property="areaUnderWaterLogging"
							styleId="areaUnderWaterLogging" style="width: 200px;"
							styleClass="cs2 form-control">


						</html:textarea>
					</div>

				</div>
				<!--if Pond and Water Logging Problems Yes/No-->


				<!--Population and Period-->
				<div class="col-lg-12" style="margin-top: 5px;">

					<div class=" form-inline col-lg-6">
						<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
								bundle="env" key="environment.periodOfWaterLogging" /><span
							class="text-danger">&nbsp;*</span></label>
						<html:textarea property="periodOfWaterLogging"
							styleId="periodOfWaterLogging" style="width: 200px;"
							styleClass="cs2 form-control">

						</html:textarea>
					</div>

					<div class=" form-inline col-lg-6">
						<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
								bundle="env" key="environment.populationEffected" /><span
							class="text-danger">&nbsp;*</span></label>
						<html:textarea property="populationEffected"
							styleId="populationEffected" style="width: 200px;"
							styleClass="cs2 form-control">


						</html:textarea>
					</div>



				</div>
				<!--Population and Period-->

				<!--Contamination and Extra -->
				<div class="col-lg-12" style="margin-top: 5px;">

					<div class=" form-inline col-lg-6">
						<label class="text-right col-lg-6" style="font-size: 13px;">
							<bean:message bundle="env" key="environment.contamination" /><span
							class="text-danger">&nbsp;*</span>
						</label>
						<html:textarea property="contamination" styleId="contamination"
							style="width: 200px;" styleClass="cs2 form-control">

						</html:textarea>
					</div>


				</div>
				<!--Population and Period-->
			</div>
			<!--if Pond and Water Logging Problems Yes/No Ends-->




			<!--Maximum and Minimum width of Village Roads-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.maximumWidth" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:text property="maximumWidth" styleId="maximumWidth"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:text>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.minimumWidth" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:textarea property="minimumWidth" styleId="minimumWidth"
						style="width: 200px;" styleClass="cs2 form-control">


					</html:textarea>
				</div>


			</div>
			<!--Maximum and Minimum width of Village Roads-->


			<!-- Existing Roads in the Villages and Current water disposable waste-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.existingRoads" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select property="existingRoads" styleId="existingRoads"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.solidWasteDisposable" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:select multiple="multiple" property="solidWasteDisposable"
						styleId="solidWasteDisposable" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select </html:option>

					</html:select>
				</div>


			</div>
			<!-- Existing Roads in the Villages and Current water disposable waste-->


			<!-- Local Vegitation and Extra-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.localVegitation" /><span
						class="text-danger">&nbsp;*</span></label>
					<html:text property="localVegitation" styleId="localVegitation"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>




			</div>
			<!-- Local Vegitation and Extra-->
			
			<div class="col-lg-12" style="margin-top: 5px;">
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="edsId" styleId="edsId"  styleClass="ci5 form-control" style="width: 200px; visibility: hidden;" >

					</html:text>
				</div>
				
				<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="edsBaselineId" styleId="edsBaselineId"  styleClass="ci5 form-control" style="width: 200px; visibility: hidden;">

					</html:text>
				</div>
				</div>
			
			
	<!-- Proceed Button -->
	<div class="row" style="margin-top: 10px;">
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>
		
		<div class="col-lg-10 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning pull-right" style="width: 150px;"
				onclick="de_add()">Save and Continue</button>
		</div>

		<!-- <div class="col-lg-5 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning " style="width: 150px;"
				onclick="de_add_coninue()">Save and Continue</button>
		</div> -->



		
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>



	</div>


	<!-- Proceed Button -->



		</div>
	</div>


</html:form>

</body>
<script type='text/javascript'>

	$(window).load( function() {
		
		refreshData();
		
		var appId = localStorage.getItem("edsId");
		//alert("App Id Inside Other Page"+appId);
		document.getElementById("edsId").value = appId;
		
		
		ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'waterLoggingProblemsYesNo');	
										ajaxFunction('GetFilterValues.to?parentComboId=1004&method=getCombo', 'topography');
										ajaxFunction('GetFilterValues.to?parentComboId=2000&method=getCombo', 'soil');
										ajaxFunction('GetFilterValues.to?parentComboId=2007&method=getCombo', 'rainfall');
										ajaxFunction('GetFilterValues.to?parentComboId=2011&method=getCombo', 'waterTable');
										ajaxFunction('GetFilterValues.to?parentComboId=2016&method=getCombo', 'existingWaterBodyWithinVillage');
										ajaxFunction('GetFilterValues.to?parentComboId=2023&method=getCombo', 'ifPondCurrentUse');
										ajaxFunction('GetFilterValues.to?parentComboId=2029&method=getCombo', 'existingRoads');
										ajaxFunction('GetFilterValues.to?parentComboId=2033&method=getCombo', 'solidWasteDisposable');  
										ajaxFunction('GetFilterValues.to?parentComboId=2112&method=getCombo', 'predominentWindDirection');
										
										
										
		//Run Ajax to Check weather the data exis or not
										//Call Ajax and send Random Number to the corresponding Mobile Number
										 $.ajax({
									type : "POST",
									async: true,
									url : "environmentDataCollectionBaseLineEnvironmentAction.do?method=checkData",
									data : {
										appId : appId
												
											},
									success : function(data) {
										
										
											if(data.length>50){
												jsonObject = JSON.parse(data);
												//Parde the object and set the object
												setDataToFom(jsonObject);
											}
												
									
										
										
										
									}
								
								});

		
					
										
	
	
	});
	
	function refreshData(){
		document.getElementById("topography").value= "";
		document.getElementById("soil").value= "";
		document.getElementById("rainfall").value= "";
		document.getElementById("temperatureMin").value= "";
		document.getElementById("temperatueMax").value= "";
		document.getElementById("scopeOfLand").value= "";
		document.getElementById("predominentWindDirection").value= "";
		document.getElementById("existingWaterBodyWithinVillage").value= "";
		document.getElementById("maximumWidth").value= "";
		document.getElementById("minimumWidth").value= "";
		document.getElementById("existingRoads").value= "";
		document.getElementById("localVegitation").value= "";
		document.getElementById("waterLoggingProblemsYesNo").value = "";
		document.getElementById("waterTable").value = "";
		document.getElementById("edsId").value = "";
		document.getElementById("edsBaselineId").value = "";
	}
	
	
	function setDataToFom(data){
		
		document.getElementById("topography").value= data.topography;
		document.getElementById("soil").value= data.soil;
		document.getElementById("rainfall").value= data.rainfall;
		document.getElementById("temperatureMin").value= data.temperatureMin;
		document.getElementById("temperatueMax").value= data.temperatueMax;
		document.getElementById("scopeOfLand").value= data.scopeOfLand;
		document.getElementById("predominentWindDirection").value= data.predominentWindDirection;
		document.getElementById("existingWaterBodyWithinVillage").value= data.existingWaterBodyWithinVillage;
		document.getElementById("maximumWidth").value= data.maximumWidth;
		document.getElementById("minimumWidth").value= data.minimumWidth;
		document.getElementById("existingRoads").value= data.existingRoads;
		document.getElementById("localVegitation").value= data.localVegitation;
		document.getElementById("waterLoggingProblemsYesNo").value = data.waterLoggingProblemsYesNo;
		document.getElementById("waterTable").value = data.waterTable;
		document.getElementById("edsId").value = data.edsId;
		document.getElementById("edsBaselineId").value = data.edsBaselineId;
	}
	
	function de_add() {
		
		//var jsonObject
		if(jsonObject==null){
			//add
			if (true) {
			
			document.environmentDataCollectionBaseLineEnvironmentForm.action = "environmentDataCollectionBaseLineEnvironmentAction.do?method=save&edsID="
					+ appId ;
			document.environmentDataCollectionBaseLineEnvironmentForm.submit();
	
				}
		}else{
			//Modify
			//alert("Modifing in Progress");
			 if (true) {
				 
				document.environmentDataCollectionBaseLineEnvironmentForm.action = "environmentDataCollectionBaseLineEnvironmentAction.do?method=update&edsID="
						+ appId ;
				document.environmentDataCollectionBaseLineEnvironmentForm.submit();
		
					}
			 
		}
		
		
}

function de_add_coninue() {
	if (true) {
	
		document.environmentDataCollectionBaseLineEnvironmentForm.action = "environmentDataCollectionBaseLineEnvironmentAction.do?method=saveContinue&edsID="
				+ appId;
		document.environmentDataCollectionBaseLineEnvironmentForm.submit();
	}
}
	
	
	function hideTextOthers(value){
		
		if( value === '2022'){
				document.getElementById("othersOption").style.display= "block";
		}else{
			document.getElementById("othersOption").style.display= "none";
		}
	}
	
	
	function toggle(value){
		if( value === '6'){
			document.getElementById("waterLoggingProblemsYes").style.display= "block";
	}else{
		document.getElementById("waterLoggingProblemsYes").style.display= "none";
	}
	}
	
	

	function getGamPanchayat(id) {
		ajaxFunction(
				"environmentDataCollectionAction.do?method=getGramPanchayat&villageId="
						+ id, 'vil');
	}

	$('#itemEndOfLife,#defaultInline').datepick();
	$('#itemNextMaintenance,#defaultInline').datepick();

	function validateEndDate() {
		var startDate = new Date($('#startDate').val());
		var endDate = new Date($('#endDate').val());
		if (endDate != '' && typeof endDate != 'undefined'
				&& endDate != 'Invalid Date') {
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

			var EffectiveDate = $.trim($("[id$='endDate']").val());

			if (EffectiveDate < today) {
				alert('end date could not less than current date');
				return false;
			}
			if (endDate < startDate) {
				alert('end date could not less than start date');
				return false;
			}
		}

		return true;
	}

	$(document).ready(function() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!

		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd;
		}
		if (mm < 10) {
			mm = '0' + mm;
		}
		var today = mm + '/' + dd + '/' + yyyy;
		document.getElementById("startDate").value = today;

		if (document.getElementById('endDate') === 'undefined') {
			document.getElementById('endDate').value = '';
		}

	});

	function validateFields() {

		var zone = document.getElementById('zone').value;
		var district = document.getElementById('district').value;
		var block = document.getElementById('block').value;
		var villageId = document.getElementById('villageId').value;
		var vil = document.getElementById('vil').value;

		var schemeType = document.getElementById('schemeType').value;
		var schemeCategory = document.getElementById('schemeCategory').value;
		var schemeId = document.getElementById('schemeId').value;

		if (zone == "" || zone == null) {
			alert("Please Select Zone");
			return false;
		} else if (district == "" || district == null) {
			alert("Please Select District");
			return false;
		} else if (block == "" || block == null) {
			alert("Please Select Block");
			return false;
		} else if (villageId == "" || villageId == null) {
			alert("Please Select VillageID");
			return false;
		} else if (vil == "" || vil == null) {
			alert("Please Select Gram Panchayat");
			return false;
		} else if (schemeType == "" || schemeType == null) {
			alert("Please Select Type of Scheme");
			return false;
		} else if (schemeCategory == "" || schemeCategory == null) {
			alert("Please Select Category of Scheme ");
			return false;
		} else if (schemeId == "" || schemeId == null) {
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