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
			document.dsrBuildingForm.action="dsrBuildingAction.do?method=find&d__mode="+d__mode+"&menuId=PMM003";
			document.dsrBuildingForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.dsrBuildingForm.action="dsrBuildingAction.do?method=update&d__mode="+d__mode+"&menuId=PMM003";
				document.dsrBuildingForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.dsrBuildingForm.action="dsrBuildingAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM003";
				document.dsrBuildingForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.dsrBuildingForm.action="dsrBuildingAction.do?method=post&d__mode="+d__mode+"&menuId=PMM003";
				document.dsrBuildingForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.dsrBuildingForm.action="dsrBuildingAction.do?method=save&d__mode="+d__mode+"&menuId=PMM003";
				document.dsrBuildingForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/tab_pane.css" type="text/css"
	media="screen" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="dsrBuildingAction" method="post">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; min-height: 13px;">
		<html:errors bundle="pmm" /></div>
	</logic:messagesPresent>
	<logic:messagesPresent message="true">
		<div id="successDiv" class="success diplaynone" style="width: 470px;">
		<html:messages id="message" bundle="pmm" message="true">
			<li><bean:write name="message" /></li>
		</html:messages></div>
	</logic:messagesPresent>


	<%@include file="../JSPF/blockVillageSchemeFilter.jspf"%>
	<table>
		<tr>
			<td><font size="3">DSR Date of Transaction</font></td>
			<td><html:text property="dsrDate" styleClass="ci5"
				styleId="dsrDateId" readonly="readonly"></html:text></td>
		</tr>
	</table>
	<div id="tabbed_box_1" class="tabbed_box">

	<div class="tabbed_area">
	<ul class="tabs">
		<li><a href="javascript:tabSwitch_2(1, 4, 'tab_', 'content_');"
			id="tab_1" class="active">Estate Water Supply</a></li>
		<li><a href="javascript:tabSwitch_2(2, 4, 'tab_', 'content_');"
			id="tab_2">Internal Water Supply</a></li>
		<li><a href="javascript:tabSwitch_2(3, 4, 'tab_', 'content_');"
			id="tab_3">Air-Conditioning</a></li>
		<li><a href="javascript:tabSwitch_2(4, 4, 'tab_', 'content_');"
			id="tab_4">Total Charges</a></li>
	</ul>

	<div id="content_1" class="content">
	<table>
		<tr>
			<td colspan="3"><font size="3"><b>Pipeline:</b></font></td>
		</tr>
		<tr>
			<td><label>Length (mtr)</label></td>
			<td><html:text property="estatePipelineLength" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="estatePipelineCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td colspan="3"><font size="3"><b>Head Works (if
			any):</b></font></td>
		</tr>
		<tr>
			<td><label>Pump Chamber Cost</label></td>
			<td><html:text property="headworksPumpchamberCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Head (mtr)</label></td>
			<td><html:text property="headworksHead" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Discharge (lpm)</label></td>
			<td><html:text property="headworksDischarge" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td colspan="3"><font size="3"><b>OHSR/UGSR:</b></font></td>
		</tr>
		<tr>
			<td><label>Capacity (ltr)</label></td>
			<td><html:text property="ohsrCapacity" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="ohsrCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</div>
	<div id="content_2" class="content">
	<table>
		<tr>
			<td colspan="3"><font size="3"><b>Pipeline:</b></font></td>
		</tr>
		<tr>
			<td><label>Length (mtr)</label></td>
			<td><html:text property="internalPipelineLength"
				styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="internalPipelineCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

		<tr>
			<td colspan="3"><font size="3"><b>Sanitary Fixtures:</b></font></td>
		</tr>
		<tr>
			<td><label>OWC/EWC Quantity</label></td>
			<td><html:text property="sanitaryOwcEwcQuantity"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>OWC/EWC Cost</label></td>
			<td><html:text property="sanitaryOwcEwcCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Wash Basin Quantity</label></td>
			<td><html:text property="sanitaryWashbasinQuantity"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Wash Basin Cost</label></td>
			<td><html:text property="sanitaryWashbasinCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Urinal Quantity</label></td>
			<td><html:text property="sanitaryUrinalQuantity"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Urinal Cost</label></td>
			<td><html:text property="sanitaryUrinalCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td colspan="3"><font size="3"><b>Sewerage System and
			Disposal Works:</b></font></td>
		</tr>
		<tr>
			<td><label>Length (mtr)</label></td>
			<td><html:text property="sewerageLength" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="sewerageCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td colspan="3"><font size="3"><b>Storm Sewer:</b></font></td>
		</tr>
		<tr>
			<td><label>Length (mtr)</label></td>
			<td><html:text property="stormSewerageLength" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="stormSewerageCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td nowrap><label>Rain Water Harvesting System Cost</label></td>
			<td><html:text property="rainwaterCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td nowrap><label>Fire Fighting System Cost</label></td>
			<td><html:text property="firesystemCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</div>
	<div id="content_3" class="content">
	<table>
		<tr>
			<td><label>Window AC Quantity</label></td>
			<td><html:text property="windowacQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Window AC Cost</label></td>
			<td><html:text property="windowacCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Split AC Quantity</label></td>
			<td><html:text property="splitacQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Split AC Cost</label></td>
			<td><html:text property="splitacCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label> AC Plant Cost</label></td>
			<td colspan="2"><html:text property="acplantCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</div>
	<div id="content_4" class="content">
	<table>
		<tr>
			<td><label>Departmental Charges</label></td>
			<td><html:text property="departmentalCharges" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Contigency Charges</label></td>
			<td><html:text property="contigencyCharges" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Total Cost</label></td>
			<td><html:text property="totalCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	<script language="javascript">
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
		triggerEvent(document.getElementById('villageId'), 'onchange');
		document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
	</script>
</html:form>
</html:html>