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
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>-->

<script type="text/javascript">
		function de_find(){		
			document.instPlantsForm.action="instPlantsAction.do?method=find&d__mode="+d__mode+"&menuId=PMM012";
			document.instPlantsForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') {
				var status =  validateFields();
				if(status)
				{
				document.instPlantsForm.action="instPlantsAction.do?method=update&d__mode="+d__mode+"&menuId=PMM012";
				document.instPlantsForm.submit();
			}}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.instPlantsForm.action="instPlantsAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM012";
				document.instPlantsForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.instPlantsForm.action="instPlantsAction.do?method=post&d__mode="+d__mode+"&menuId=PMM012";
				document.instPlantsForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				var status =  validateFields();
				if(status)
				{
				document.instPlantsForm.action="instPlantsAction.do?method=save&d__mode="+d__mode+"&menuId=PMM012";
				document.instPlantsForm.submit();
			}
			}
		}
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="instPlantsAction" method="post">
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
	<fieldset><legend>Status of RO Plant Installation</legend>
	<center>
	<table>
		<tr>
			<td><label>Date of Transaction</label></td>
			<td nowrap><html:text property="transactionDate"
				styleId="transactionDateId" styleClass="ci5" readonly="readonly" />
			</td>
		</tr>

		<tr>
			<td><label>Select Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				onchange="ajaxFunction('GetSchemeFilterValues.to?locationId='+this.value, 'blockId');triggerEvent(document.getElementById('blockId'), 'onchange');"
				styleClass="cs1">

				<html:options collection="userLocations" labelProperty="label"
					property="value" />
			</html:select></td>

			<td><label>Select Block</label></td>
			<td><html:select property="blockId" styleId="blockId"
				onchange="ajaxFunction('GetSchemeFilterValues.to?blockId='+this.value, 'villageId');"
				styleClass="cs1">
				<html:option value="Select Block"></html:option>
			</html:select></td>
			<td><label>Select Village</label></td>
			<td><html:select property="villageId" styleId="villageId"
				styleClass="cs1">
				<html:option value="Select Village"></html:option>
			</html:select></td>
	</tr>
	
	
	
	
			<SCRIPT LANGUAGE="JavaScript">
triggerEvent(document.getElementById('locationId'), 'onchange');
triggerEvent(document.getElementById('blockId'), 'onchange');
</script>
		<tr>
		<td><label>Stipulated Date of Completion</label></td>
			<td><html:text property="stipulatedDateCompletion"
				styleId="stipulatedDateCompletionId" styleClass="ci5"
				readonly="readonly" /></td>
		 <td><label>Type of plant</label></td>
			<td><html:select property="typePlant" styleId="typePlantID" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="ULTRA-FILTERATION">Ultra Filteration</html:option>
				<html:option value="RO">RO</html:option>
			</html:select></td>
			<td><label>Cluster Number</label></td>
			<td><html:text property="clustorNumber" styleId="clustorNumberId" styleClass="ci5"
			onkeypress="return validateKey(event,	this,'9@20')" /></td>
		</tr>
		<tr>
			<td><label>Site Selection</label></td>
			<td><html:select property="siteSelection" styleId="siteSelectionId" styleClass="cs1">
			<html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
			</td>
			<td><label>Electrict Connection Applied Date</label></td>
				<td>
				<html:text  property="electricConnection" styleId="asOnDateId22" styleClass="ci5" readonly="readonly" />
				
			</td>
			<td><label>Villages Covered</label></td>
			<td><html:text property="villagesCovered" styleId="villagesCoveredID" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20')" /></td>
		</tr>
	
		<tr>
			<td><label>Date (if TubeWell is Alloted)</label></td>
				
			<td><html:text  property="tubewellAllotted" styleId="asOnDateId222" styleClass="ci5" readonly="readonly" />
			
			</td>
			
			
			<td><label>TubeWell work In-Progress</label></td>
			<td><html:text property="tubewellInprogress" styleId="tubewellInprogressID"  styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20')" /></td>

			<td><label>TubeWell work Completion Date</label></td>
			<td>
			<html:text  property="tubewellCompleted" styleId="asOnDateId2222" styleClass="ci5" readonly="readonly" />
			
			</td>
		</tr>
		<tr>
			<td><label>Platform Completed</label></td>
			<td><html:select property="platformCompleted" styleId="platformCompletedID" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
			
			<td><label>Machinery Arranged</label></td>
			<td><html:select property="machineryArranged" styleId="machineryArrangedId" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
			<td><label>Machinery Installed</label></td>
			<td><html:select property="machineryInstalled" styleId="machineryInstalledId" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td><label>Housing Structure Completed</label></td>
			<td><html:select property="tubewellHousingCompleted"  styleId="tubewellHousingCompletedid"
				styleClass="cs1"><html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
			<td><label>Plant Arranged</label></td>
			<td><html:select property="tubewellPlantArranged" styleId="tubewellPlantArrangedId" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
			<td><label>Plant Installed</label></td>
			<td><html:select property="tubewellPlantInstalled" styleId="tubewellPlantInstalledID"
				styleClass="cs1"> <html:option value="">Select</html:option>
				<html:option value="YES">YES</html:option>
				<html:option value="NO">NO</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td><label>Date of Commissioning Plant</label></td>
		
				<td>
<html:text  property="tubewellCommissioningPlant" styleId="asOnDateId22222" styleClass="ci5" readonly="readonly" />
			
			</td>
				
			<td><label>Date of Release Electric Connection</label></td>
		
				<td>
				<html:text  property="tubewellReleaseElectric" styleId="asOnDateId222222" styleClass="ci5" readonly="readonly" />
			
			</td>
			
			<td nowrap><label>Physical Completion(100%)</label></td>
			<td><html:text property="tubewellPhysicalCompletion" styleId="tubewellPhysicalCompletionid"
				styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20')" /></td>
		</tr>
		<tr>
			<td><label>Date of Start</label></td>
			<td nowrap><html:text property="tubewellDateStart"
				styleId="tubewellDateStartId" styleClass="ci5" readonly="readonly" />
			</td>
			
			<td><label>Actual Date of Completion</label></td>
			<td><html:text property="actualDateCompletion"
				styleId="actualDateCompletionId" styleClass="ci5"
				readonly="readonly" /></td>
		</tr>
	</table>
	</center>
	</fieldset>
	
		<script>
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
		triggerEvent(document.getElementById('villageId'), 'onchange');
		document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
				triggerEvent(document.getElementById('womenMoreThan33Percent'), 'onblur');
				</script>
				
	<script>
	
		function validateFields(){
			var tranDate=document.getElementById('transactionDateId').value;
			var locationID=document.getElementById('locationId').value;
			var blockID=document.getElementById('blockId').value;
			var villageID=document.getElementById('villageId').value;
            

			var status1=document.getElementById('stipulatedDateCompletionId').value;
			var typeofplant=document.getElementById('typePlantID').value;
			var status11=document.getElementById('clustorNumberId').value;
			var status111=document.getElementById('asOnDateId22').value;
			var siteSelction=document.getElementById('siteSelectionId').value;
            var villagecover=document.getElementById('villagesCoveredID').value;
			var tubewellalloted=document.getElementById('asOnDateId222').value;
			var worktubewellprog=document.getElementById('tubewellInprogressID').value;
			var tubewellComp=document.getElementById('asOnDateId2222').value;
			var plateformComp=document.getElementById('platformCompletedID').value;
			var machArranged=document.getElementById('machineryArrangedId').value;
			var machInstalled=document.getElementById('machineryInstalledId').value;

			var houseStr=document.getElementById('tubewellHousingCompletedid').value;
			var	planArranged=document.getElementById('tubewellPlantArrangedId').value;
			var	plantInstalled=document.getElementById('tubewellPlantInstalledID').value;

			var complant=document.getElementById('asOnDateId22222').value;
			var releaseElectriccon=document.getElementById('asOnDateId222222').value;

			var phycompl=document.getElementById('tubewellPhysicalCompletionid').value;
			var	dateofstart=document.getElementById('tubewellDateStartId').value;
			var	dateofComp=document.getElementById('actualDateCompletionId').value;


             if(tranDate=='' || tranDate==""){
				alert("Please Select Date of Transaction.");
				return false;
			}
          else if(locationID=='' || locationID==""){
				alert("Please Select Location.");
				return false;
			}
           else if(blockID=='' || blockID==""){
				alert("Please Select Block.");
				return false;
			}
 else if(villageID=='' || villageID==""){
				alert("Please Select Village.");
				return false;
			}




	      else if(status1=='' || status1==""){
				alert("Please Select Stipulated Date of Completion.");
				return false;
			}
			
			else if(typeofplant=='' || typeofplant=="")
			{
				alert('Please Select Type of plant.');
			}
			else if(status11=='' || status11=="")
			{
				alert('Please Enter Cluster Number.');
			}
			else if(siteSelction=='' || siteSelction=="")
			{
				alert('Please Select Site Selection.');
			}
			
			else if(status111=='' || status111=="")
			{
				alert('Please Select Electrict Connection Applied Date.');
			}
			else if(villagecover=='' || villagecover=="")
			{
				alert('Please Enter Villages Covered.');
			}
			else if(tubewellalloted=='' || tubewellalloted=="")
			{
				alert('Please Select Date if TubeWell is Alloted.');
			}
			else if(worktubewellprog=='' || worktubewellprog=="")
			{
				alert('Please Enter TubeWell work In-Progress.');
			}
			else if(tubewellComp=='' || tubewellComp=="")
			{
				alert('Please Select TubeWell work Completion Date.');
			}
			else if(plateformComp=='' || plateformComp=="")
			{
				alert('Please Select Platform Completed.');
			}
			else if(machArranged=='' || machArranged=="")
			{
				alert('Please Select Machinery Arranged.');
			}
			else if(machInstalled=='' || machInstalled=="")
			{
				alert('Please Select Machinery Installed.');
			}
			else if(houseStr=='' || houseStr=="")
			{
				alert('Please Select Housing Structure Completed.');
			}
			else if(planArranged=='' || planArranged=="")
			{
				alert('Please Select Plant Arranged. ');
			}
			else if(plantInstalled=='' || plantInstalled=="")
			{
				alert('Please Select Plant Installed.');
			}




			else if(complant=='' || complant=="")
			{
				alert('Please Select Date of Commissioning Plant.');
			}
			else if(releaseElectriccon=='' || releaseElectriccon=="")
			{
				alert('Please Select Date of Release Electric Connection. ');
			}
			else if(phycompl=='' || phycompl=="")
			{
				alert('Please Enter Physical Completion(100%).');
			}

			else if(dateofstart=='' || dateofstart=="")
			{
				alert('Please Select Date of Start. ');
			}
			else if(dateofComp=='' || dateofComp=="")
			{
				alert('Please SelectActual Date of Completion.');
			}
			
			
			else
			{
					
	return true;
			}
	
		}
		</script>
</html:form>
</html:html>