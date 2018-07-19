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




<script type="text/javascript">

var arrayObj =  new Array();
var  trueFalseObj =  null;
var clearArray = null;

	function de_find() {
		document.environmentDataCollectionWaterForm.action = "environmentDataCollectionWaterSupplyAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=ENV001";

		document.environmentDataCollectionWaterForm.submit();

	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			var result = true;

			if (result) {
				document.environmentDataCollectionWaterForm.action = "environmentDataCollectionWaterSupplyAction.do?method=update&d__mode="
						+ d__mode + "&menuId=ENV001";
				document.environmentDataCollectionWaterForm.submit();
			}
		}
	}

	
	
function de_add() {
		
		//var jsonObject
		if(jsonObject==null){
			//add
			if (true) {
			//	alert(localStorage.getItem("edsId"));
				document.environmentDataCollectionWaterForm.action = "environmentDataCollectionWaterSupplyAction.do?method=save&edsID="
					+ localStorage.getItem("edsId");
			document.environmentDataCollectionWaterForm.submit();
			}
			
			
		
	
		}else{
			
			 if (true) {
				 
				 document.environmentDataCollectionWaterForm.action = "environmentDataCollectionWaterSupplyAction.do?method=update&edsID="
						+ appId;
				document.environmentDataCollectionWaterForm.submit();
		
					}
			 
		}
}
</script>



</head>


<html:html>

<logic:messagesPresent>
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
</logic:messagesPresent>

<c:set var="parameter">
	<bean:message bundle="env" key="environment.waterAvailabilityParameterIssue" />
</c:set>
<c:set var="issue">
	<bean:message bundle="env" key="environment.waterSanitationParameterMitigationMeasure" />
</c:set>
<c:set var="measure">
	<bean:message bundle="env" key="environment.waterQualityParameterParameterIssue" />
</c:set>
<h4
	class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"
	style="visibility: hidden;">Lorem ipsum dolor sit amet,
	consectetur adipiscing elit. Suspendisse a dui erat. Vivamus in maximus
	neque, et elementum quam. Vivamus in maximus neque, et elementum quam.
</h4>

<html:form action="environmentDataCollectionWaterSupplyAction" method="post"
	styleId="environmentDataCollectionWaterForm">

	




	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Water Supply Scheme</h4>
			
			
			
			<input type='hidden' name='parameterEnvironmentHidden' id="parameterEnvironmentHidden">
			<input type='hidden' name='parameterMeasuresHidden' id="parameterMeasuresHidden">
			<input type='hidden' name='parameterIssuesHidden' id="parameterIssuesHidden">

			<!-- Topography and Type of Soil -->
			<div class="col-lg-12" style="margin-top: 5px;">

			<%-- 	<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.schemeType" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="schemeType" styleId="schemeType"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value=""> Select Scheme Type</html:option>
					</html:select>
				</div> --%>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.proposedWaterSupplyScheme" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="proposedWaterSupplyScheme" styleId="proposedWaterSupplyScheme" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select Proposed water Scheme</html:option>

					</html:select>
				</div>



			</div>
			<!-- Topography and Type of Soil -->


			<!-- Rainfall and Temperatur -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.currentDrinkingwaterSituation" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="currentDrinkingwaterSituation" styleId="currentDrinkingwaterSituation" rows="3"
						style="width: 200px;" styleClass="cs2 form-control"> </html:textarea>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.sourceDrinkingWater" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="sourceDrinkingWater" styleId="sourceDrinkingWater"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Water Source</html:option>

					</html:select>
				</div>


			</div>
			
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterAvailabilityLpcd" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="waterAvailabilityLpcd" styleId="waterAvailabilityLpcd"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:textarea>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.availabilityLandIntakeOrWTPSite" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="availabilityLandIntakeOrWTPSite"
						styleId="availabilityLandIntakeOrWTPSite" style="width: 200px;"
						styleClass="cs2 form-control">

<html:option value="">Select </html:option>
					</html:select>
				</div>



			</div>
			


			<!-- Has the Source of Water been assessed if Yes and Nature of Quality of Form-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.assessedWaterQuality" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="assessedWaterQuality" styleId="assessedWaterQuality"
						styleClass="form-control  " style="width: 200px;">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.natureQualityProblem" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select multiple="multiple" property="natureQualityProblem"
						styleId="natureQualityProblem" styleClass="form-control"
						style="width: 200px;">
						<html:option value="">Select </html:option>

					</html:select>
				</div>



			</div>
			<!-- Has the Source of Water been assessed if Yes and Nature of Quality of Form-->


		
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.riskContamination" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="riskContamination" styleId="riskContamination"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.correctiveActionsTaken" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="correctiveActionsTaken"
						styleId="correctiveActionsTaken" style="width: 200px;" rows="3"
						styleClass="cs2 form-control">

					</html:textarea>
				</div>

			</div>
		






			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.provitionBoreWell" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="provitionBoreWell" styleId="provitionBoreWell"
						style="width: 200px;" styleClass="cs2 form-control">
					<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.provitionBoreWellSanctioned" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="provitionBoreWellSanctioned" styleId="provitionBoreWellSanctioned"
						style="width: 200px;" styleClass="cs2 form-control">


					</html:textarea>
				</div>


			</div>


			
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.canalSourceTreatment" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="canalSourceTreatment" styleId="canalSourceTreatment"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.groundwaterTechnology" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="groundwaterTechnology"
						styleId="groundwaterTechnology" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select </html:option>

					</html:select>
				</div>


			</div>
			


		
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.hasDisinfectionSystemProvided" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="hasDisinfectionSystemProvided" styleId="hasDisinfectionSystemProvided"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6 " style="font-size: 13px;"><bean:message
							bundle="env" key="environment.hasDisinfectionSystemProvidedType" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="hasDisinfectionSystemProvidedType" styleId="hasDisinfectionSystemProvidedType" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select </html:option>

					</html:select>
				</div>



			</div>
			



		</div>
	</div>


	<!--  Section III Social Envronment -->
	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Provosion Required from Generic EMF in the water supply scheme village</h4>
			

			<!-- Population and  and Number of HouseHolds -->
			<div class="col-lg-12" >

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.airWaterNoisTesting" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="airWaterNoisTesting" styleId="airWaterNoisTesting"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.lawnsTreesPlantation" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="lawnsTreesPlantation" styleId="lawnsTreesPlantation"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>



			</div>
			

			


			

		</div>
	</div>
	<!--  Section II Ends -->


	<!-- Section III -->
	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
			Antcipated Environmental Issues and Mitigation Measures during Implementation</h4>
			
			
			 <div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3"  style="font-size: 13px;"><bean:message
							bundle="env" key="environment.parameter" /><span
						class="text-danger"> &nbsp;*</span></label>
						<html:select property="parameterEnvironment" styleId="parameterEnvironment"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
						
					<%-- <html:text property="issues" styleId="issues"
						style="width: 200px;" styleClass="cs2 form-control" value="Issues" >
					</html:text>
					<html:text property="measures" styleId="measures"
						style="width: 200px;" styleClass="cs2 form-control" value="Measures" >
					</html:text> --%>
				</div>
			</div> 
			
			
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterQualityParameterParameterIssue" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="parameterIssues" styleId="parameterIssues"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					
				</div>

				
<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterSanitationParameterMitigationMeasure" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="parameterMeasures" styleId="parameterMeasures"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					
				</div>

			</div>  
			
	
	
			
			<!-- data grid addition -->
			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden"  style= "margin-left: -20px; margin-top: 10px;">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="waterSupplyGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="parameter"
										title="${parameter}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="issue"
										title="${issue}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="measure"
										title="${measure}" mode="I,I,I"></layout:datagridColumn>
									
									

								</layout:datagrid>

							</div>
						</td>
						<td><button title='Add' type='button'
								class="btn btn-primary active" style="width: 50px;"
								onclick="addDataGrid()">
								<span class="glyphicon glyphicon-plus-sign"></span>
							</button> <br>
							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="emptyDataGrid();">
								<i class='fa fa-trash-o'></i>
							</button>
						</td>
					</tr>
				</table>
			</div>
			<!-- data grid aDDITION -->
			
				<div class="col-lg-12" style="margin-top: 5px;">
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6" style="margin-top: 5px; ">
					
					<html:text property="eds_id" styleId="edsId"  styleClass="ci5 form-control" style="width: 200px; visibility: visible;" >

					</html:text>
					
					<html:text property="id" styleId="id"  styleClass="ci5 form-control" style="width: 200px; visibility: visible;" >

					</html:text>
				</div>
				
				
				
				
				</div>
			
			
			<!-- Proceed Button -->
	<div class="row" style="margin-top: 10px;">
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>
		
		<div class="col-lg-10 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning pull-right" style="width: 150px;"
				onclick="de_add();">Save and Continue</button>
		</div>

		<!-- <div class="col-lg-5 col-md-1 sm-hidden xs-hidden ">
			<button type="button" class="btn btn-warning " style="width: 150px;"
				onclick="de_add_coninue()">Save and Continue</button>
		</div> -->



		
		<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;</div>



	</div>
			

		</div>
	</div>



</html:form>

</body>
<script type='text/javascript'>


	$(window).load(
			function() {
				
				//refreshData();
				
				var appId = localStorage.getItem("edsId");
				//alert("App Id Inside last Page"+appId);
				document.getElementById("edsId").value = appId;
				
				
				//ajaxFunction('GetFilterValues.to?parentComboId=2058&method=getCombo', 'schemeType');
				ajaxFunction('GetFilterValues.to?parentComboId=2061&method=getCombo', 'proposedWaterSupplyScheme');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'assessedWaterQuality');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'riskContamination');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'provitionBoreWell');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'hasDisinfectionSystemProvided');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'airWaterNoisTesting');  
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'lawnsTreesPlantation');
			
				ajaxFunction('GetFilterValues.to?parentComboId=2064&method=getCombo', 'availabilityLandIntakeOrWTPSite');
				ajaxFunction('GetFilterValues.to?parentComboId=2068&method=getCombo', 'natureQualityProblem');
				ajaxFunction('GetFilterValues.to?parentComboId=2078&method=getCombo', 'canalSourceTreatment');
				ajaxFunction('GetFilterValues.to?parentComboId=2083&method=getCombo', 'groundwaterTechnology');
				ajaxFunction('GetFilterValues.to?parentComboId=2106&method=getCombo', 'sourceDrinkingWater');
				ajaxFunction('GetFilterValues.to?parentComboId=2118&method=getCombo', 'hasDisinfectionSystemProvidedType');
				ajaxFunction('GetFilterValues.to?parentComboId=2121&method=getCombo', 'parameterEnvironment');
				
				
				
				//Run Ajax to Check weather the data exis or not
				//Call Ajax and send Random Number to the corresponding Mobile Number
				 $.ajax({
			type : "POST",
			async: true,
			url : "environmentDataCollectionWaterSupplyAction.do?method=checkData",
			data : {
				appId : appId
						
					},
			success : function(data) {
				
				
					if(data.length>50){
						jsonObject = JSON.parse(data);
						//Parde the object and set the object
						setDataToFom(jsonObject);
						console.log(jsonObject);
					}
						
			
				
				
				
			}
		
		});
				

			});


	
	function changeRowColor() {

		$('#waterSupplyGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_SEL")) {
					//$('table tr').css('color','#c51717');
					$(this).css('color', '#c51717');
				} else {
					//$('table tr').css('color','#000000');
					$(this).css('color', '#000000');
				}
			}
		});
	}
	
	
function addDataGrid() {
		
		var objectComp = null;
		trueFalseObj =  new Array();
		
		var status = validateFields();
		
		if(status){
			
		var parameterEnvironmentHidden = $("#parameterEnvironment option:selected").text();
		var parameterEnvironmentHiddenValue = document.getElementById('parameterEnvironment').value;
		var NameId = parameterEnvironmentHidden + "(" + parameterEnvironmentHiddenValue + ")";
		document.getElementById('parameterEnvironmentHidden').value = NameId;
		
		
		var data = document.getElementById('parameterMeasures').value;
		document.getElementById('parameterMeasuresHidden').value = data;
		
		
		var NameDI = document.getElementById('parameterIssues').value;
		document.getElementById('parameterIssuesHidden').value = NameDI;
		
		
		

		//var status = validateGridField();
		//var status = true;
		if (status) {
			
			 objectComp = createObjectGrid(parameterEnvironmentHiddenValue,parameterMeasuresHidden,parameterIssuesHidden);
			 
			 if(arrayObj.length === 0){
					//Save the Object in an array
					arrayObj.push(objectComp);
					StrutsLayout
					.addDatagridLine(
							'waterSupplyGrid', 'parameterEnvironmentHidden~parameterMeasuresHidden~parameterIssuesHidden');
					
					
					
				}
			 else{
					
				 
					
				 for(var i = 0; i < arrayObj.length ; i++){
					
					 console.log("i is ",i );
					// alert(arrayObj[i]);
					 
					 if (typeof arrayObj[i] !== "undefined") {
						 var addArray = null;
						    addArray =  compareObject(arrayObj[i],objectComp);
						trueFalseObj.push(addArray);
						 console.warn("What To Do",trueFalseObj);
						}else{
							console.log("Something is Undefined");
						}
						
					 
					 
					 
				
				 }
				 
				/*  if(trueFalseObj.every(isFalse)) */
				// alert(trueFalseObj);
				  if(trueFalseObj.includes(true)){
					  alert("Data already exists !!");
				 }else{
					
					 arrayObj.push(objectComp);
					 StrutsLayout.addDatagridLine(
								'waterSupplyGrid', 'parameterEnvironmentHidden~parameterMeasuresHidden~parameterIssuesHidden');
						
					
					
				 } 
				 
		}
			 
			
		}
		
		
		}
	}

	
	function compareObject(o1, o2){
		for(var p in o1){
			if(o1[p] !== o2[p]){
				return false;
			}
		}
		for(var p in o2){
			if(o1[p] !== o2[p]){
				return false;
			}
		}
		return true;
	
}

function createObjectGrid(parameterEnvironmentHiddenValue,parameterMeasuresHidden,parameterIssuesHidden){
	
	var gridObject = {
			
			parameterEnvironmentHiddenValue : parameterEnvironmentHiddenValue,
			parameterMeasuresHidden    : parameterMeasuresHidden,
			parameterIssuesHidden: parameterIssuesHidden
	}
	
	return gridObject;
}

function emptyDataGrid(){
	//alert(arrayObj);
	 clearArray = new Array();
	array = StrutsLayout.setDatagridLineStateNew('waterSupplyGrid','removed');
	//alert(array);
	remove11();
	clearArrayObj(array);
	//alert(arrayObj);
	
}

function clearArrayObj(arrayToBeCleared){
	
	for(var p in arrayToBeCleared){
		var x = arrayToBeCleared[p];
		delete arrayObj[x] ;  
	}
}

function remove11() {
	$('#waterSupplyGridJsId>tbody>tr').each(function() {
		var tableHdr = $(this).closest('tr').find('th');
		if (tableHdr.length < 1) {
			if ($(this).hasClass("DATAGRID_DEL")) {
				$(this).hide();
			}
		}
	});
}

function validateFields(){
	var parameterEnvironment = document.getElementById('parameterEnvironment').value;
	var parameterMeasures = document.getElementById('parameterMeasures').value;
	var parameterIssues = document.getElementById('parameterIssues').value;
	
	
	if (parameterEnvironment === '' || parameterEnvironment === null) {
		alert('Please Select Parameter.');
		return false;
	} else if (parameterMeasures === '' || parameterMeasures === null) {
		alert('Please Enter  Mitigation Measure.');
		return false;
	} else if (parameterIssues === '' || parameterIssues === null) {
		alert('Please enter Issue .');
		return false;
	} 
	return true;
}


function setDataToFom(data){
	
	document.getElementById("proposedWaterSupplyScheme").value= data.proposedWaterSupplyScheme;
	document.getElementById("currentDrinkingwaterSituation").value= data.currentDrinkingwaterSituation;
	document.getElementById("sourceDrinkingWater").value= data.sourceDrinkingWater;
	document.getElementById("waterAvailabilityLpcd").value= data.waterAvailabilityLpcd;
	document.getElementById("availabilityLandIntakeOrWTPSite").value= data.availabilityLandIntakeOrWTPSite;
	document.getElementById("assessedWaterQuality").value= data.assessedWaterQuality;
	document.getElementById("riskContamination").value= data.riskContamination;
	document.getElementById("correctiveActionsTaken").value= data.correctiveActionsTaken;
	document.getElementById("provitionBoreWell").value= data.provitionBoreWell;
	document.getElementById("canalSourceTreatment").value= data.canalSourceTreatment;
	document.getElementById("groundwaterTechnology").value= data.groundwaterTechnology;
	
	
	
	document.getElementById("hasDisinfectionSystemProvided").value= data.hasDisinfectionSystemProvided;
	document.getElementById("hasDisinfectionSystemProvidedType").value= data.hasDisinfectionSystemProvidedType;
	document.getElementById("airWaterNoisTesting").value= data.airWaterNoisTesting;
	document.getElementById("lawnsTreesPlantation").value= data.lawnsTreesPlantation;
	
	

	//document.getElementById("eds_id").value= data.eds_id;
	document.getElementById("id").value = data.id;
	   
	   
	   
	   
	  
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   


}

	
</script>

</html:html>