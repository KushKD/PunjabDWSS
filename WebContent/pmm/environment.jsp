<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
-->
<script type="text/javascript">
		function de_find(){		
			document.environmentForm.action="environmentAction.do?method=find&d__mode="+d__mode+"&menuId=PMM008";
			document.environmentForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.environmentForm.action="environmentAction.do?method=update&d__mode="+d__mode+"&menuId=PMM008";
				document.environmentForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.environmentForm.action="environmentAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM008";
				document.environmentForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.environmentForm.action="environmentAction.do?method=post&d__mode="+d__mode+"&menuId=PMM008";
				document.environmentForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.environmentForm.action="environmentAction.do?method=save&d__mode="+d__mode+"&menuId=PMM008";
				document.environmentForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="environmentAction" method="post">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors />
		</div>
	</logic:messagesPresent>
	<logic:messagesPresent message="true">
		<div id="successDiv" class="success diplaynone" style="width: 470px;">
		<html:messages id="message" message="true">
			<li><bean:write name="message" /></li>
		</html:messages></div>
	</logic:messagesPresent>
	<%@include file="../JSPF/blockVillageFilter.jspf"%>
	<fieldset><legend>BaseLine Environment</legend> <left>
	<div class="indented">
	<table>
		<tr>
			<td><label>Date of Transaction</label></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:text property="dateOfTransaction" styleClass="ci5"
				styleId="transactionDate" readonly="readonly" /> <!--<input class=ci4 type=button onclick="c1.innerpopup('dateOfTransaction','calendar_frame');" value="..."/>-->
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><label>Topography</label></td>
			<td>
			<div class="selectBorder"><html:select property="topography"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="PLAIN">Plain</html:option>
				<html:option value="HILLY">Hilly</html:option>
				<html:option value="ROLLING">Rolling</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Type of Soil</label></td>
			<td>
			<div class="selectBorder"><html:select property="typeOfSoil"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="Alluvial">Alluvial</html:option>
				<html:option value="SLIT">Slit </html:option>
				<html:option value="SLIT-CLAY">Slit Clay </html:option>
				<html:option value="SANDY">Sandy</html:option>
				<html:option value="SANDY-CLAY">Sandy Clay</html:option>
				<html:option value="OTHER">Other</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Intensity of Rainfall</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="intensityOfRainfall" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="LOW">Low</html:option>
				<html:option value="MODERATE">Moderate</html:option>
				<html:option value="HIGH">HIGH </html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Temperature:Min</label></td>
			<td><html:text property="tempMin" styleClass="ci5" /></td>
		<tr>
		</tr>
		<td><label>Temperature:Max</label></td>
		<td><html:text property="tempMax" styleClass="ci5" /></td>
		</tr>
		<tr>
			<td><label>Natural Slope of the Land:</label></td>
			<td><html:text property="slopeOfLand" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Predominant wind direction</label></td>
			<td><html:text property="windDirection" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Water Level</label></td>
			<td>
			<div class="selectBorder"><html:select property="waterTable"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="SHALLOW">Shallow(0-5m)</html:option>
				<html:option value="MODERATE" styleClass="ci5">Moderate(5-10m)</html:option>
				<html:option value="DEEP-AQUIFER" styleClass="ci5">Deep aquifer(10-20m)</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Existing water body in village</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="existingWaterBody" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="RIVER">River</html:option>
				<html:option value="CANAL" styleClass="ci5">Canal</html:option>
				<html:option value="POND-LAKE" styleClass="ci5">Pond/Lake</html:option>
				<html:option value="OTHER" styleClass="ci5">Other</html:option>
			</html:select></div>
			</td>
			<td><label>If 'Other' Please mention here</label></td>
			<td><html:text property="existingWaterBodyOther"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>If Pond/Lake, current use of it</label></td>
			<td>
			<div class="selectBorder"><html:select property="pondUse"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="DRINKING">Drinking</html:option>
				<html:option value="IRRIGATION">Irrigation</html:option>
				<html:option value="CATTLE-WASHING">Cattle Washing</html:option>
				<html:option value="SEWAGE-DISPOSAL">Sewage Disposal</html:option>
				<html:option value="OTHERS">Others</html:option>
			</html:select></div>
			</td>
			<td><label>If 'Other' Please mention here</label></td>
			<td><html:text property="existingWaterBodyOther"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Water logging problems within
			village/villages:</b></font></td>
		</tr>
		<tr>
			<td><label>Name of area/areas</label></td>
			<td><html:text property="waterLoggingAreaName" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Area under water logging</label></td>
			<td><html:text property="waterLoggingArea" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Period of water logging(annually)</label></td>
			<td><html:text property="waterLoggingPeriod" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Population affected by water logging</label></td>
			<td><html:text property="populationEffectedWaterLogging"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Contamination of drinking water sources from
			water logging</label></td>
			<td colspan="4"><html:text property="contaminationDrinkingWater"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Maximum and Minimum width of village road</label></td>
			<td><html:text property="roadWidthMaxMin" styleClass="ci5"></html:text></td>
		<tr>
		</tr>
		<td><label>Exisiting roads in village</label></td>
		<td><html:text property="existingRoads" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Local Vegetation(Mention species)</label></td>
			<td><html:text property="localVegetation" styleClass="ci5"></html:text></td>
		</tr>
	</table>
	</div>
	</left></fieldset>
	<fieldset><legend>Social Environment</legend> <left>
	<div class="indented">
	<table>
		</div>
		<tr>
			<td><label>Population</label></td>
			<td><html:text property="population" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20')"></html:text></td>
		</tr>
		<tr>
			<td><label>Number of household</label></td>
			<td><html:text property="numberHousehold" styleClass="ci5"
				onkeypress="return validateKey(event,this,'9@20')"></html:text></td>
		</tr>
		<tr>
			<td><label>Land use Pattern of village</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="landUsePattern" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="RURAL">Rural</html:option>
				<html:option value="URBANIZED-RURAL">Urbanized Rural</html:option>
				<html:option value="ROLLING">Rolling</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Historical/Relegious Importance </label></td>
			<td>
			<div class="selectBorder"><html:select
				property="historicalImportance" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Major source of income</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="majorSourceIncome" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="AGRICULTURE">Agriculture</html:option>
				<html:option value="SERVICE">Service</html:option>
				<html:option value="BUSINESS">Business</html:option>
				<html:option value="LABOUR">Labour</html:option>
				<html:option value="OTHER">Other</html:option>
			</html:select></div>
			</td>
		</tr>
	</table>
	</left>
	</fieldset>
	<fieldset><legend>Public Health Issue</legend> <left>
	<div class="indented">
	<table>
		<tr>
			<td><label>Any incident of waterborn epidemic in the
			recent past</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="incidentWaterborn" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>If yes, Name of the water born disease</label></td>
			<td>
			<div class="selectBorder"><html:select property="diseaseName"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="DIARRHEA">Diarrhea</html:option>
				<html:option value="GASTRO-ENTITIES">Gastro Entities</html:option>
				<html:option value="TYPHOID">Typhoid</html:option>
				<html:option value="OTHER">Other</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Is there any vector born disease</label></td>
			<td>
			<div class="selectBorder"><html:select property="vectorDisease"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="MALARIA">Malaria</html:option>
				<html:option value="DENGUE">Dengue</html:option>
				<html:option value="FILARIA">Filaria</html:option>
			</html:select></div>
			</td>
		</tr>
	</table>
	</div>
	</left></fieldset>
	<fieldset><legend>Water Supply Scheme</legend>
	<center>
	<table>
		<tr>
			<td><label>Type of Scheme</label></td>
			<td>
			<div class="selectBorder"><html:select property="schemeType"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="EXISITNG">Existing</html:option>
				<html:option value="NEW">New</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Source of drinking water</label></td>
			<td><html:text property="sourceOfDrinkingWater" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Water availability in 1pcd</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="waterAvailability" styleClass="cs1">
				<html:option value="0">Select</html:option>
				<html:option value="40">40</html:option>
				<html:option value="70">70</html:option>
				<html:option value="90">90</html:option>
				<html:option value="135">135</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Source of water quality been assessed</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="sourceWaterAssessed" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Mention the appropriate nature of the quality
			problem</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="sourceWaterAssessed" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="FLUORIDE">Fluoride</html:option>
				<html:option value="IRON">Iron</html:option>
				<html:option value="HEAVY-METAL">Heavy Metals</html:option>
				<html:option value="BACTERIOLOGICAL">Bacterialogical</html:option>
				<html:option value="TDS">TDS</html:option>
				<html:option value="NO">Iron</html:option>
				<html:option value="PESTICIDE">Pesticide</html:option>
				<html:option value="NITRATE">Nitrate</html:option>
				<html:option value="OTHERS">Other</html:option>
				<html:option value="NO-PROBLEMS">No-Problems</html:option>
			</html:select></div>
			</td>
			<td><label>If 'Other' Please mention here</label></td>
			<td><html:text property="sourceWaterAssessed" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Potential Risk of contamination of source</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="riskContamination" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Proposed project likely to affect any natural
			habitats</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="affectNaturalHabitats" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Proposed project likely to infringe on rights
			of the locals</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="infringeLocalRights" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>If canal source,indicate the Treatment
			technology</label></td>
			<td><html:text property="treatmentTechnology" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Indicate the Treatment proposed</label></td>
			<td><html:text property="treamentProposed" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Is disinfection system exist?</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="disinfectionExists" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>If yes type chlorinator or silver ionisation
			plant</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="ionisationPlant" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Anticipated Environment Issue and
	Mitigation Measures</legend> <left>
	<div class="indented">
	<table>
		<tr>
			<th><label>Parameter</label></th>
			<th><label>Issue</label></th>
			<th><label>Mitigation measure</label></th>
		</tr>
		<tr>
			<td><label>Water availbility</label></td>
			<td><html:text property="antWaterAvlbIssue" styleClass="ci5"></html:text></td>
			<td><html:text property="antWaterAvlbMitigation"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Water quality</label></td>
			<td><html:text property="antWaterQultIssue" styleClass="ci5"></html:text></td>
			<td><html:text property="antWaterQultMitigation"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Sanitation</label></td>
			<td><html:text property="sanitationIssue" styleClass="ci5"></html:text></td>
			<td><html:text property="sanitationMitigation" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Construction</label></td>
			<td><html:text property="constructionIssue" styleClass="ci5"></html:text></td>
			<td><html:text property="constructionMitigation"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Disposal of construction waste</label></td>
			<td><html:text property="constructionWastesIssue"
				styleClass="ci5"></html:text></td>
			<td><html:text property="constructionWastesMitigation"
				styleClass="ci5"></html:text></td>
		</tr>
	</table>
	</div>
	</left></fieldset>
	<fieldset><legend>Sewage and Sanitation Scheme</legend> <left>
	<div class="indented">
	<table>

		<tr>
			<td><label>Type of Scheme</label></td>
			<td><html:text property="sewageSchemeType" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Current Sanitation practice</label></td>
			<td><html:text property="sewagePractices" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Current Drainage Pattern</label></td>
			<td><html:text property="sewagePattern" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Does the wastewater from cattle sheds
			discharged into the open drains</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="wastewaterCattle" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Does the grey water and black water mix</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="greyBlackWaterMix" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Villagers feedback about current sanitation
			practice</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="sanitationFeedback" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Approximate Wastewater Quantity in mld</label></td>
			<td><html:text property="wastewaterQuantity" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Method of treatment to be Provided</label></td>
			<td><html:text property="methodTreatment" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Whether existing Ponds will be used as STP</label></td>
			<td>
			<div class="selectBorder"><html:select property="pondsStp"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Pond's distance from nearest settlement(mtr)</label></td>
			<td><html:text property="pondsDistanceSettlement"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Distance from the nearest schools/primary
			health center/religious</label></td>
			<td><html:text property="pondsDistanceSchool" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Water Quality of Pond by visual inspection</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="pondsWaterQuality" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="GOOD">Good</html:option>
				<html:option value="VERY-BAD">Very Bad</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Any requirement of expansion of pond</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="pondsExpansionRequired" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Is there sufficient land available for
			expansion of the pond </label></td>
			<td>
			<div class="selectBorder"><html:select
				property="pondsExpansionLand" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>If yes, current land use</label></td>
			<td>
			<div class="selectBorder"><html:select property="landUse"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="AGRICULTURE" styleClass="ci5">Agriculture</html:option>
				<html:option value="BARREN-LAND" styleClass="ci5">Barren Land</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Is there plantation around pond site</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="pondsSitePlantation" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Contamination of Drinking Water Source from
			the Pond</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="contaminationDrinkingWaterSource" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Current Domestic solid waste disposal</label></td>
			<td><html:text property="domesticSolidWaste" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Is there any landfill site for disposal of
			solid waste</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="landfillSiteSolidWaste" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>

		</tr>

	</table>
	</div>
	</left></fieldset>
	<script language="javascript">
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
	</script>
</html:form>
</html:html>