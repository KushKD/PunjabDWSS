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
<script type="text/javascript">
		function de_find(){		
			document.sustAssessmentForm.action="sustAssessmentAction.do?method=find&d__mode="+d__mode+"&menuId=PMM011";
			document.sustAssessmentForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				var status = validateDate();
				
				if(status){
				document.sustAssessmentForm.action="sustAssessmentAction.do?method=update&d__mode="+d__mode+"&menuId=PMM011";
				document.sustAssessmentForm.submit();
				}
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.sustAssessmentForm.action="sustAssessmentAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM011";
				document.sustAssessmentForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.sustAssessmentForm.action="sustAssessmentAction.do?method=post&d__mode="+d__mode+"&menuId=PMM011";
				document.sustAssessmentForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				var status = validateDate();
				
				if(status){
				document.sustAssessmentForm.action="sustAssessmentAction.do?method=save&d__mode="+d__mode+"&menuId=PMM011";
				document.sustAssessmentForm.submit();
				}
			}
		}
		function getSustAssessment(schemeId,villageId,assessmentDate,status){
			document.sustAssessmentForm.action="sustAssessmentAction.do?method=populate&schemeId="+schemeId+"&villageId="+villageId+"&assessmentDate="+assessmentDate+"&status="+status+"&d__mode="+d__mode+"&menuId=PMM011";
			document.sustAssessmentForm.submit();
		}		
		function addScore(){
			document.getElementById('totalScore').value=parseInt(document.getElementById('sourcefailSummer').value)+
			parseInt(document.getElementById('disinfectionDaily').value)+
			parseInt(document.getElementById('sourceRemainedPotable').value)+
			parseInt(document.getElementById('properProtectionArrangement').value)+
			parseInt(document.getElementById('yearInclusiveMaintenanceShut').value)+
			parseInt(document.getElementById('supplyWaterLessThan50Percent').value)+
			parseInt(document.getElementById('totalHouseholdPitTaps').value)+
			parseInt(document.getElementById('supplyWaterLessThan120Percent').value)+
			parseInt(document.getElementById('electricConsumGPWSC').value)+
			parseInt(document.getElementById('revenueCollectedMoreThan50Percent').value)+
			parseInt(document.getElementById('revenueCollectedMoreThan90Percent').value)+
			parseInt(document.getElementById('revenueCollectedMoreThanOMExpenditure').value)+
			parseInt(document.getElementById('preparedAnnualOMPlan').value)+
			parseInt(document.getElementById('annualMeetingsHeld').value)+
			parseInt(document.getElementById('preparedApprovedSOA').value)+
			parseInt(document.getElementById('womenMoreThan33Percent').value);
			
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">

<html:form action="sustAssessmentAction" method="post">
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
	<fieldset>
	<center><label style="font-size: 20px; font-family: times;">Data
	pertaining to this screen should be entered by 10th of following month
	</label></center>
	</fieldset>

	<table class="form2">
		<tr class="form2TH">
			<td><label>Select Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				onchange="ajaxFunction('GetSchemeFilterValues.to?locationId='+this.value, 'blockId');triggerEvent(document.getElementById('blockId'), 'onchange');"
				styleClass="cs3">

				<html:options collection="userLocations" labelProperty="label"
					property="value" />
			</html:select></td>

			<td><label>Select Block</label></td>
			<td><html:select property="blockId" styleId="blockId"
				onchange="ajaxFunction('GetSchemeFilterValues.to?blockId='+this.value, 'villageId');"
				styleClass="cs3">
				<html:option value="Select Block"></html:option>
			</html:select></td>
		</tr>
		<tr class="form2TH">
			<td><label>Select Village</label></td>
			<td><html:select property="villageId" styleId="villageId"
				onchange="ajaxFunction('GetSchemeFilterValues.to?type=PIPED_SWAP_MH&villageId='+this.value, 'schemeId');"
				styleClass="cs3">
				<html:option value="Select Village"></html:option>
			</html:select></td>

			<td><label>Scheme Code</label></td>
			<td><html:select property="schemeId" styleId="schemeId"
				styleClass="cs3">
				<html:option value="Select Scheme"></html:option>
			</html:select></td>
		</tr>
	</table>
	<SCRIPT LANGUAGE="JavaScript">
triggerEvent(document.getElementById('locationId'), 'onchange');
triggerEvent(document.getElementById('blockId'), 'onchange');
triggerEvent(document.getElementById('villageId'), 'onchange');
</script>
	<fieldset><legend>Operation Sustainability
	Assessment Scoring</legend> Date of Assessment: <html:text
		property="assessmentDate" styleClass="ci5" styleId="assessmentDate"
		readonly="readonly" />
	<center>
	<table class="form2" style="margin: 0 1em 1em 0;">
		<tr>
			<th class="form2TH"><label>Characteristics</label></th>
			<th class="form2TH"><label>Score</label></th>
		</tr>
		<tr>
			<td><label><font size="4"><b>Technical
			Sustainability</b></font></label><I><font color="red" size="2"> (Max. Weight:
			50)</font></I></td>

		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="3"><b>Source
			Sustainability</b></font></label><I><font color="red" size="2"> (Max. Weight:
			10)</font></I></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Source
			did not totally fail in summer</label><I><font color="red" size="2">
			(Max. Weight: 10)</font></I></td>
			<td><html:text property="sourcefailSummer"
				styleId="sourcefailSummer" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="3"><b>Quality
			Sustainability</b></font></label><I><font color="red" size="2"> (Max. Weight:
			15)</font></I></td>

		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Disinfection
			is done daily</label><I><font color="red" size="2"> (Max. Weight:
			5)</font></I></td>

			<td><html:text property="disinfectionDaily" styleClass="ci5"
				styleId="disinfectionDaily"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Source
			remained potable throughout the year</label><I><font color="red" size="2">
			(Max. Weight: 5)</font></I></td>

			<td><html:text property="sourceRemainedPotable" styleClass="ci5"
				styleId="sourceRemainedPotable"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Proper
			protection arrangement in place for the sources/drwal points</label><I><font
				color="red" size="2"> (Max. Weight: 5)</font></I></td>

			<td><html:text property="properProtectionArrangement"
				styleClass="ci5" styleId="properProtectionArrangement"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="3"><b>Operational
			Sustainability</b></font></label><I><font color="red" size="2"> (Max. Weight:
			25)</font></I></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Downtime
			in Water supply occured for less than 24 days year inclusive of
			maintenance shut down</label><I><font color="red" size="2"> (Max.
			Weight: 5)</font></I></td>
			<td><html:text property="yearInclusiveMaintenanceShut"
				styleClass="ci5" styleId="yearInclusiveMaintenanceShut"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Supply
			of water achieved for at least for 50% of supply hours</label><I><font
				color="red" size="2"> (Max. Weight: 5)</font></I></td>

			<td><html:text property="supplyWaterLessThan50Percent"
				styleClass="ci5" styleId="supplyWaterLessThan50Percent"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Less
			than 5% of tatl households with pit-taps</label><I><font color="red"
				size="2"> (Max. Weight: 5)</font></I></td>

			<td><html:text property="totalHouseholdPitTaps" styleClass="ci5"
				styleId="totalHouseholdPitTaps"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Supply
			of water is less than 120% of estimated actual demand</label><I><font
				color="red" size="2"> (Max. Weight: 5)</font></I></td>
			<td><html:text property="supplyWaterLessThan120Percent"
				styleClass="ci5" styleId="supplyWaterLessThan120Percent"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td style="border-bottom: 1px solid #000000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Electric
			consumption of the GPWSC is less than 120% of the estimated
			consumption as per the O&M plan</label><I><font color="red" size="2">
			(Max. Weight: 5)</font></I></td>

			<td style="border-bottom: 1px solid #000000;"><html:text
				property="electricConsumGPWSC" styleId="electricConsumGPWSC"
				styleClass="ci5" onblur=" addScore();"></html:text></td>

		</tr>

		<tr>
			<td><label><font size="4"><b>Finanacial
			Sustainability</b></font></label><I><font color="red" size="2"> (Max. Weight:
			30)</font></I></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>GPWSCs
			where revenue collected from tariff is more than 50% of planned O&M
			expenditure over the past 12 months</label><I><font color="red" size="2">
			(Max. Weight: 5)</font></I></td>
			<td><html:text property="revenueCollectedMoreThan50Percent"
				styleClass="ci5" styleId="revenueCollectedMoreThan50Percent"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>GPWSCs
			where revenue collection is more than 90% of planned O&M expenditure
			in the past 12 months</label><I><font color="red" size="2"> (Max.
			Weight: 5)</font></I></td>

			<td><html:text property="revenueCollectedMoreThan90Percent"
				styleClass="ci5" styleId="revenueCollectedMoreThan90Percent"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>GPWSCs
			where revenue collected is greater than O&M expenditure</label><I><font
				color="red" size="2"> (Max. Weight: 10)</font></I></td>

			<td><html:text property="revenueCollectedMoreThanOMExpenditure"
				styleClass="ci5" styleId="revenueCollectedMoreThanOMExpenditure"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td style="border-bottom: 1px solid #000000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>GPWSCs
			has prepared an annual O&M plan at the beginning of financial year</label><I><font
				color="red" size="2"> (Max. Weight: 10)</font></I></td>

			<td style="border-bottom: 1px solid #000000;"><html:text
				property="preparedAnnualOMPlan" styleId="preparedAnnualOMPlan"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr style></tr>
		<tr>
			<td><label><font size="4"><b>Institutional
			Sustainability</b></font></label> <I><font color="red" size="2"> (Max.
			Weight: 20)</font></I></td>

		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>GPWSCs
			has held annual/semi annual meetings</label><I><font color="red" size="2">
			(Max. Weight: 5)</font></I></td>
			<td><html:text property="annualMeetingsHeld" styleClass="ci5"
				styleId="annualMeetingsHeld"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>GPWSCs
			has prepared approved and displayed annual/semi annual statement of
			accounts and submitted these to the DPMC</label><I><font color="red"
				size="2"> (Max. Weight: 10)</font></I></td>

			<td><html:text property="preparedApprovedSOA" styleClass="ci5"
				styleId="preparedApprovedSOA"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>Where
			GPWSC has more than 33% women members</label><I><font color="red"
				size="2"> (Max. Weight: 5)</font></I></td>

			<td><html:text property="womenMoreThan33Percent"
				styleClass="ci5" styleId="womenMoreThan33Percent"
				onkeypress="return validateKey(event,	this,'9@20@2')"
				onblur=" addScore();"></html:text></td>
		</tr>
		<tr>
			<td><label><font size="4"><b>Total Score</b></font></label><I><font
				color="red" size="2"> </font></I></td>

			<td><input type="text" styleClass="ci5" id="totalScore"
				readonly="readonly" /></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<div id="dispTag"><logic:present name="sustainabilityList"
		scope="request">
		<logic:notEmpty name="sustainabilityList" scope="request">
			<center><display:table name="sustainabilityList"
				id="sustAssessment" class="mars" style="margin:0 1em 1em 0;"
				pagesize="10" requestURI="/sustAssessmentAction.do" export="true">
				<display:column title="Village Id" media="html">
					<a
						href="javascript:getSustAssessment('${sustAssessment.schemeId}','${sustAssessment.villageId}','${sustAssessment.assessmentDate}','${sustAssessment.misAuditBean.status}')">${sustAssessment.villageId}</a>
				</display:column>
				<display:column property="assessmentDate" title="Assessment Date"
					sortable="true"
					decorator="com.prwss.mis.common.util.DateColumnDecorator" />
				<display:column property="locationId" title="Location Id"
					sortable="true" />
				<display:column property="schemeId" title="Scheme Code"
					sortable="true" />
				<display:column property="misAuditBean.status" title="Status"
					sortable="true" />

				<display:setProperty name="export.excel.filename"
					value="sustainabilityList.xls" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.csv" value="false" />
			</display:table></center>

		</logic:notEmpty>
	</logic:present></div>

	<script>
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
		triggerEvent(document.getElementById('villageId'), 'onchange');
		document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
				triggerEvent(document.getElementById('womenMoreThan33Percent'), 'onblur');
				
		function validateDate(){		
				var d1 = document.getElementById("assessmentDate").value;
				
				var d2 = new Date();
				
				 var x = d1.split("-");
				 var dt1  = d2.getDate(); 
				 var mon1 = d2.getMonth()+1; 
				 var yr1  = d2.getFullYear(); 
				 var dt2 = 7;
				 var mon2 = d2.getMonth()+1;
				 var yr2 = d2.getFullYear();
					d2.setDate(1);
				 var dt3 = d2.getDate();
				 var mon3 = d2.getMonth()+1;
				 var yr3 = d2.getFullYear();
				 var dt4 = d2.getDate();
				 var mon4 = d2.getMonth()+1;
				 var yr4 = d2.getFullYear();					 
				
				var asOnDate = new Date(x[2],(x[1]-1),x[0]);					
				var  prDate= new Date(yr1, mon1-1, dt1);
				var tenDate = new Date(yr2,mon2-1,dt2);
				var fcMonthDate = new Date(yr3, mon3-1,dt3);
				var flMonthDate = new Date(yr4,mon4-2,dt4);
				
				if(prDate<asOnDate){
					alert("Assessment Date should be before today's Date");
					return false;
				}					
				else if(prDate<=tenDate){
					if(asOnDate<tenDate){					
						if((asOnDate>flMonthDate) && (asOnDate<tenDate)){
								return true;
							}
							else{
								alert("Assessment Date should be greater than"+" ("+flMonthDate+") "+"and"+" less than or equal("+tenDate+")");
								return false;
							}
						}
						else if(asOnDate>tenDate){
							 if((asOnDate>=fcMonthDate) && (asOnDate<=prDate)){
								return true;
							}					
							else {
								alert("Assessment Date should be between"+" ("+fcMonthDate+") "+"and"+" ("+prDate+")");
								return false;
							}
						}
						
						else{
								return true;
						}
				}
				else if(prDate>tenDate){
					if(asOnDate>tenDate){					
						if((asOnDate>flMonthDate) ){
								return true;
							}
							else{
								alert("Assessment Date should be greater than"+" ("+flMonthDate+") "+"and"+" less than or equal("+tenDate+")");
								return false;
							}
						}
						else if(asOnDate<tenDate){
							 if((asOnDate>=fcMonthDate) && (asOnDate<=prDate)){
								return true;
							}					
							else {
								alert("Assessment Date should be between"+" ("+fcMonthDate+") "+"and"+" ("+prDate+")");
								return false;
							}
						}
						
						else{
								return true;
						}
				}
				else{
							return true;
				}

			
		}
		
				
	</script>
</html:form>
</html:html>