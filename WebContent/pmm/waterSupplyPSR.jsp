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
<!--<script language="javascript1.2" src="js/codethatcalendarstd.js"></script>
-->
<script type="text/javascript">
		function de_find(){		
			document.waterSupplyForm.action="waterSupplyAction.do?method=find&d__mode="+d__mode+"&menuId=PMM009";
			document.waterSupplyForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.waterSupplyForm.action="waterSupplyAction.do?method=update&d__mode="+d__mode+"&menuId=PMM009";
				document.waterSupplyForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.waterSupplyForm.action="waterSupplyAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM009";
				document.waterSupplyForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.waterSupplyForm.action="waterSupplyAction.do?method=post&d__mode="+d__mode+"&menuId=PMM009";
				document.waterSupplyForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.waterSupplyForm.action="waterSupplyAction.do?method=save&d__mode="+d__mode+"&menuId=PMM009";
				document.waterSupplyForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="waterSupplyAction" method="post">
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
	<fieldset><legend>General Parameters</legend>
	<center>
	<table>
		<tr>
			<td><label>Date of Transaction</label></td>
			<td><html:text property="dateOfTransaction"
				styleId="transactionIdDate" styleClass="ci5" readonly="readonly"></html:text>
			<!--<input	class=ci4 type=button onclick="c1.innerpopup('dateOfTransactionId','calendar_frame');" value="..." />--></td>
		</tr>
		<tr>
			<td><label>Total Households</label></td>
			<td><html:text property="houseHolds" styleClass="ci5"></html:text></td>
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

			<td><label>Average road width</label></td>
			<td><html:text property="widthOfRoad" styleClass="ci5"></html:text></td>
			<td><label>Types of Roads</label></td>
			<td>
			<div class="selectBorder"><html:select property="typeOfRoads"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="BITUMINOUS" styleClass="ci5">Bituminous</html:option>
				<html:option value="CEMENTED" styleClass="ci5">Cemented</html:option>
				<html:option value="BRICK-PAVED" styleClass="ci5">Brick paved</html:option>
				<html:option value="KACHCHA" styleClass="ci5">Kachcha</html:option>
				<html:option value="OTHER" styleClass="ci5">Other</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Average Cattle Population</label></td>
			<td><html:text property="cattlePopulation" styleClass="ci5"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Water Supply Parameters</legend>
	<center>
	<table>
		<tr>
			<td><label>Types of water supply</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="typeWaterSupply" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="INDIVIDUAL-SOURCE">Individual Source </html:option>
				<html:option value="DEPARTMENTAL-SUPPLY">Departmental supply</html:option>
				<html:option value="OTHER">Other</html:option>
			</html:select></div>
			</td>
			<td><label>Service Level of Water Supply in lpcd</label></td>
			<td><html:text property="waterLevel" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Water Quality as per Test Report</label></td>
			<td><html:text property="waterQuality" styleClass="ci5"></html:text></td>

			<td><label>Groundwater Level</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="groundwaterLevel" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="SHALLOW">Shallow</html:option>
				<html:option value="DEEP">Deep</html:option>
			</html:select></div>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Existing Sewerage And Sanitation
	Parameters</legend>
	<center>
	<table>
		<tr>
			<td><label>Approximate Quantity of Wastewater(mld)</label></td>
			<td><html:text property="wasteWaterQuantity" styleClass="ci5"></html:text></td>
			<td><label>Current Sanitation Practices</label></td>
			<td><html:textarea property="currentSanitation" styleClass="ci5"></html:textarea></td>
		</tr>
		<tr>
			<td><label>Existing Ponds are being used for Sewage
			Discharge</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="existingPondsSewageDisharge" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>In case of New STP</legend>
	<center>
	<table>
		<tr>
			<td><label>Approximate Land Requirement(ha)</label></td>
			<td><html:text property="landRequirment" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Availability of land</label></td>
			<td><html:text property="availabilityOfLand" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Ownership of the land for proposed STP site</label></td>
			<td><html:text property="landOwnership" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Distance from the nearest settlements(mtr)</label></td>
			<td><html:text property="settlementsDistance" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Type of nearest water body</label></td>
			<td><html:text property="waterBody" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>The usage of the Water body</label></td>
			<td><html:text property="usageWaterBody" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Distance from the nearest water body</label></td>
			<td><html:text property="waterBodyDistance" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Present Land use around STP site</label></td>
			<td><html:text property="landuseStpSite" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>If the existing pond to be used as STP,
			Please specify the method of disposal/storage its wastewater during
			construction</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="disposalLocation" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="OTHER-PONDS">Other Ponds</html:option>
				<html:option value="NEARBY-DRAIN">Nearby Drain</html:option>
				<html:option value="RIVER">River</html:option>
				<html:option value="LOW-LYING-AREAS">Low lying areas</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Location of any Sensitive Receptors and its
			approximate distance:Wildlife Sanctuary</label></td>
			<td><html:text property="receptorsWildlifeDistance"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Primary Health Center</label></td>
			<td><html:text property="receptorsHealthcenterDistance"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Religious Structure</label></td>
			<td><html:text property="receptorsReligiousStrDistance"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>School</label></td>
			<td><html:text property="receptorsSchoolDistance"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>In case existing STP of Municipal
			corporation to be used, then the distance from Sewer pipe line from
			the village</label></td>
			<td><html:text property="municipalCorporataion"
				styleClass="ci5"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Environment Health</legend>
	<center>
	<table>
		<tr>
			<td><label>Number of incidences of spread of any water
			born disease in the recent past</label></td>
			<td><html:text property="noWaterbornDisease" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Name of disease</label></td>
			<td colspan="4"><html:text property="nameOfDisease"
				styleClass="ci5"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Provision Required From Generic
	Emf In The Sewerage Scheme Village</legend>
	<center>
	<table>
		<tr>
			<td><label>Provision of Lawns and Tree Plantation at
			the STP Site</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="provisionLawnPlantation" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Provision of Rainwater harvesting tanks</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="rainWaterHarvesting" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Repair and Cleaning of Drains</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="repairCleaning" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Solid Waste Removal from the Streets</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="solidWasteRemoval" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Need of Public Awareness on health and
			hygiene</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="publicAwareness" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
		<tr>
			<td><label>Whether cleaning of Pond is required</label></td>
			<td>
			<div class="selectBorder"><html:select
				property="cleaningPondRequired" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">Yes</html:option>
				<html:option value="NO">No</html:option>
			</html:select></div>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>

	<script language="javascript">
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
	</script>
</html:form>
</html:html>