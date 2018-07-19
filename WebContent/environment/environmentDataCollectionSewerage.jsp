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
	hide_ctrl('modalPeriod', true);

	function de_find() {
		document.environmentDataCollectionSewerageForm.action = "environmentDataCollectionSewerageAction.do?method=populate&d__mode="
				+ d__mode + "&menuId=ENV001";

		document.environmentDataCollectionSewerageForm.submit();

	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			var result = true;

			if (result) {
				document.environmentDataCollectionSewerageForm.action = "environmentDataCollectionSewerageAction.do?method=update&d__mode="
						+ d__mode + "&menuId=ENV001";
				document.environmentDataCollectionSewerageForm.submit();
			}
		}
	}

	function de_add() {
		var result = true
		var status = validateFields();

		if (status) {
			document.environmentDataCollectionSewerageForm.action = "environmentDataCollectionSewerageAction.do?method=save&d__mode="
					+ d__mode + "&menuId=ENV001";
			document.environmentDataCollectionSewerageForm.submit();
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
<h4
	class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center"
	style="visibility: hidden;">Lorem ipsum dolor sit amet,
	consectetur adipiscing elit. Suspendisse a dui erat. Vivamus in maximus
	neque, et elementum quam. Vivamus in maximus neque, et elementum quam.
</h4>

<html:form action="environmentDataCollectionSewerageAction" method="post"
	styleId="environmentDataCollectionSewerageForm">

	

	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Sewerage Scheme</h4>
			

			<!-- Topography and Type of Soil -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.proposedSewergeScheme" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="proposedSewergeScheme" styleId="proposedSewergeScheme"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value=""> Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.sanitationPractices" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="sanitationPractices" styleId="sanitationPractices" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select </html:option>

					</html:select>
				</div>



			</div>
			<!-- Topography and Type of Soil -->


			<!-- Rainfall and Temperatur -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.usageofToiletsNumbersAndPercentage" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="usageofToiletsNumbersAndPercentage" styleId="usageofToiletsNumbersAndPercentage"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value=""> Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.householdsWthSepticTanks" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="householdsWthSepticTanks" styleId="householdsWthSepticTanks"
						style="width: 200px;" styleClass="cs2 form-control">
						

					</html:text>
				</div>


			</div>
			<!-- Rainfall and Temperatur  -->



			<!-- Scope of Land and Predominent Wind Direction -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterSupplyInToilets" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="waterSupplyInToilets" styleId="waterSupplyInToilets"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:textarea>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.drainagePattren" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="drainagePattren"
						styleId="drainagePattren" style="width: 200px;"
						styleClass="cs2 form-control">


					</html:textarea>
				</div>



			</div>
			<!-- Scope of Land and Predominent Wind Direction  -->


			<!-- WaterTable (Depth Below Ground Level and Existing water body within village -->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.wasteWaterFromCattleShed" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="wasteWaterFromCattleShed" styleId="wasteWaterFromCattleShed"
						styleClass="form-control  " style="width: 200px;">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.mixGreyDarkWater" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="mixGreyDarkWater"
						styleId="mixGreyDarkWater" styleClass="form-control"
						style="width: 200px;">
						<html:option value="">Select </html:option>

					</html:select>
				</div>



			</div>
			<!-- Scope of Land and Predominent Wind Direction  -->


			<!--if Pond and Water Logging Problems Yes/No-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.villagersFeedBackCurrentSanitaion" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="villagersFeedBackCurrentSanitaion" styleId="villagersFeedBackCurrentSanitaion"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select </html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.approxWasteWaterQuantity" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="approxWasteWaterQuantity"
						styleId="approxWasteWaterQuantity" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select </html:option>

					</html:select>
				</div>

			</div>
			





			<!--Maximum and Minimum width of Village Roads-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.methordOfTreatmentToBeProvided" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="methordOfTreatmentToBeProvided" styleId="methordOfTreatmentToBeProvided"
						style="width: 200px;" styleClass="cs2 form-control">

					</html:text>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.existingPondsUsedasSTP" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="existingPondsUsedasSTP" styleId="existingPondsUsedasSTP"
						style="width: 200px;" styleClass="cs2 form-control">

<html:option value="">Select</html:option>
					</html:select>
				</div>


			</div>
			<!--Maximum and Minimum width of Village Roads-->


			<!-- Existing Roads in the Villages and Current water disposable waste-->
			<div class="col-lg-12" style="margin-top: 5px;">

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.pondsDistance" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="pondsDistance" styleId="pondsDistance"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.distanceMorethan200Meters" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="distanceMorethan200Meters"
						styleId="distanceMorethan200Meters" style="width: 200px;"
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
							bundle="env" key="environment.waterQualityPond" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="waterQualityPond" styleId="waterQualityPond"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6 ">
					<label class="text-right col-lg-6 " style="font-size: 13px;"><bean:message
							bundle="env" key="environment.ponduseSTP" /><span class="text-danger">
							&nbsp;*</span></label>
					<html:select property="ponduseSTP" styleId="ponduseSTP" style="width: 200px;"
						styleClass="cs2 form-control">
						<html:option value="">Select </html:option>

					</html:select>
				</div>



			</div>
			<!-- Local Vegitation and Extra-->

<!-- Population and  and Number of HouseHolds -->
			<div class="col-lg-12" >

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.requirementofExpantion" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="requirementofExpantion" styleId="requirementofExpantion"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>

				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.sufficientLandexpantion" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="sufficientLandexpantion" styleId="sufficientLandexpantion"
						style="width: 200px;" styleClass="cs2 form-control">
                       <html:option value="">Select</html:option>
					</html:select>
				</div>



			</div>
			
			
			<!--  Start Here 1-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.currentLandUse" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="currentLandUse" styleId="currentLandUse"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.plantationPondSite" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="plantationPondSite" styleId="plantationPondSite"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
			</div>
			<!-- Start Here 1-->
			
			<!--  Start Here 2-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.landArea" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="landArea" styleId="landArea"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.landPattren" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="landPattren" styleId="landPattren"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>
			</div>
			<!-- Start Here 2-->
			
			<!--  Start Here 3-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.nearsetDistanceWaterBody" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="nearsetDistanceWaterBody" styleId="nearsetDistanceWaterBody"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.areaAccessibility" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="areaAccessibility" styleId="areaAccessibility"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>
			</div>
			<!-- Start Here 3-->
			
			<!--  Start Here 4-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.waterStagnantation" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="waterStagnantation" styleId="waterStagnantation"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.contaminationWaterPondSTP" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="contaminationWaterPondSTP" styleId="contaminationWaterPondSTP"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
			</div>
			<!-- Start Here 4-->
			
			<!--  Start Here 5-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.disposableTreatedEffluent" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="disposableTreatedEffluent" styleId="disposableTreatedEffluent"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.riskContaminationWater" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="riskContaminationWater" styleId="riskContaminationWater"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
			</div>
			<!-- Start Here 5-->
			
			<!--  Start Here 6-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.lawnAndTreePlantation" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="lawnAndTreePlantation" styleId="lawnAndTreePlantation"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.rainWaterHarvesting" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="rainWaterHarvesting" styleId="rainWaterHarvesting"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
			</div>
			<!-- Start Here 6-->
			
			<!--  Start Here 7-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.repairCleaningDraoins" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="repairCleaningDraoins" styleId="repairCleaningDraoins"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.ariNoiseWaterTesting" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="ariNoiseWaterTesting" styleId="ariNoiseWaterTesting"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
			</div>
			<!-- Start Here 7-->
			
			
			<!--  Start Here 8-->
			<div class="col-lg-12" >
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.publicAwareness" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:select property="publicAwareness" styleId="publicAwareness"
						style="width: 200px;" styleClass="cs2 form-control">
						<html:option value="">Select</html:option>
					</html:select>
				</div>
				<div class=" form-inline col-lg-6">
					<label class="text-right col-lg-6" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.nearestDistanceSettlement" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="nearestDistanceSettlement" styleId="nearestDistanceSettlement"
						style="width: 200px;" styleClass="cs2 form-control">
					</html:text>
				</div>
			</div>
			<!-- Start Here 8-->

		</div>
	</div>


	<!--  Section III Social Envronment -->
	<div class="panel panel-danger">

		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Anticipated Environmental Issues and MitigationMeasures During Implementation</h4>
			

			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.parameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:text property="issues" styleId="issues"
						style="width: 200px;" styleClass="cs2 form-control" value="Issues" >
					</html:text>
					<html:text property="measures" styleId="measures"
						style="width: 200px;" styleClass="cs2 form-control" value="Measures" >
					</html:text>
				</div>
			</div>


			
			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.siteSelectionParameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="siteSelectionParameterIssue" styleId="siteSelectionParameterIssue"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					<html:textarea property="siteSelectionParameterMitigationMeasure" styleId="siteSelectionParameterMitigationMeasure"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
				</div>
			</div>
			
			
			
			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.seerageTreatmentTechParameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="seerageTreatmentTechParameterIssue" styleId="seerageTreatmentTechParameterIssue"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					<html:textarea property="seerageTreatmentTechParameterMitigationMeasure" styleId="seerageTreatmentTechParameterMitigationMeasure"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
				</div>
			</div>
			
			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.disposableeffluentParameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="disposableeffluentParameterIssue" styleId="disposableeffluentParameterIssue"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					<html:textarea property="disposableeffluentParameterMitigationMeasure" styleId="disposableeffluentParameterMitigationMeasure"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
				</div>
			</div>
			
			
			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.disposableSludgeParameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="disposableSludgeParameterIssue" styleId="disposableSludgeParameterIssue"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					<html:textarea property="disposableSludgeParameterMitigationMeasure" styleId="disposableSludgeParameterMitigationMeasure"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
				</div>
			</div>
			
			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.ConstructionPhaseImpactsParameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="constructionPhaseImpactsParameterIssue" styleId="constructionPhaseImpactsParameterIssue"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					<html:textarea property="constructionPhaseImpactsParameterMitigationMeasure" styleId="constructionPhaseImpactsParameterMitigationMeasure"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
				</div>
			</div>
			
			<div class="col-lg-12" style="margin-top: 5px;">
				<div class=" form-inline col-lg-12">
					<label class="text-right col-lg-3" style="font-size: 13px;"><bean:message
							bundle="env" key="environment.disposableConstructionDebrisParameter" /><span
						class="text-danger"> &nbsp;*</span></label>
					<html:textarea property="disposableConstructionDebrisParameterIssue" styleId="disposableConstructionDebrisParameterIssue"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
					<html:textarea property="disposableConstructionDebrisParameterMitigationMeasure" styleId="disposableConstructionDebrisParameterMitigationMeasure"
						style="width: 200px;" styleClass="cs2 form-control" rows="3">
					</html:textarea>
				</div>
			</div>
			
			

		</div>
	</div>

	<!-- Section III -->


</html:form>

</body>
<script type='text/javascript'>
	$(window).load(
			function() {
				
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'lawnAndTreePlantation');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'rainWaterHarvesting');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'repairCleaningDraoins');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'ariNoiseWaterTesting');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'publicAwareness');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'riskContaminationWater');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'contaminationWaterPondSTP');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'plantationPondSite');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'sufficientLandexpantion');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'requirementofExpantion');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'distanceMorethan200Meters');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'existingPondsUsedasSTP');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'mixGreyDarkWater');
				ajaxFunction('GetFilterValues.to?truefalse=truefalse&method=getCombo', 'wasteWaterFromCattleShed');
				//wasteWaterFromCattleShed
				
				
				ajaxFunction('GetFilterValues.to?parentComboId=2088&method=getCombo', 'disposableTreatedEffluent');
				ajaxFunction('GetFilterValues.to?parentComboId=2091&method=getCombo', 'currentLandUse');
				ajaxFunction('GetFilterValues.to?parentComboId=2095&method=getCombo', 'ponduseSTP');
				ajaxFunction('GetFilterValues.to?parentComboId=2095&method=getCombo', 'waterQualityPond');
				ajaxFunction('GetFilterValues.to?parentComboId=2095&method=getCombo', 'villagersFeedBackCurrentSanitaion');
				
				ajaxFunction('GetFilterValues.to?parentComboId=2061&method=getCombo', 'proposedSewergeScheme');
			/* 	ajaxFunction('GetFilterValues.to?parentComboId=2095&method=getCombo', 'sanitationPractices');
				ajaxFunction('GetFilterValues.to?parentComboId=2095&method=getCombo', 'usageofToiletsNumbersAndPercentage'); */
				
				
				
				

			});

	

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
</script>
<script type='text/javascript'>
	$(document).ready(function() {
		$('.select-checkbox').prop('disabled', true);
	});
</script>
</html:html>