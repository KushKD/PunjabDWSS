<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
-->
<script type="text/javascript">
		function de_find(){		
			document.dsrSewerageForm.action="dsrSewerageAction.do?method=find&d__mode="+d__mode+"&menuId=PMM006";
			document.dsrSewerageForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.dsrSewerageForm.action="dsrSewerageAction.do?method=update&d__mode="+d__mode+"&menuId=PMM006";
				document.dsrSewerageForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.dsrSewerageForm.action="dsrSewerageAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM006";
				document.dsrSewerageForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.dsrSewerageForm.action="dsrSewerageAction.do?method=post&d__mode="+d__mode+"&menuId=PMM006";
				document.dsrSewerageForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.dsrSewerageForm.action="dsrSewerageAction.do?method=save&d__mode="+d__mode+"&menuId=PMM006";
				document.dsrSewerageForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="dsrSewerageAction" method="post">
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
	<fieldset><legend>Sewerage System</legend>
	<center>
	<table>
		<tr>

			<td><label>Type of Pipe</label></td>
			<td><input type="text" id="typeofpipeId" Class="ci5"></td>
			<td><label>Size</label></td>
			<td><input type="text" id="sizeId" Class="ci5"></td>
			<td><label>Length</label></td>
			<td><input type="text" id="lenghtId" Class="ci5"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="sewerageSystemDatagrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="sewerType" title="Type of Pipe"></layout:datagridColumn>
				<layout:datagridColumn property="sewerSize" title="Size"></layout:datagridColumn>
				<layout:datagridColumn property="sewerLength" title="Lenght"></layout:datagridColumn>
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('sewerageSystemDatagrid','typeofpipeId~sizeId~lenghtId')"
				width="20px" /><br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('sewerageSystemDatagrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	</center>
	<table align="left">
		<tr>
			<td colspan="3"><font size="3"><b>Total Cost of Pipe
			Sewer:</b></font></td>
			<td><html:text property="totalCostPipeSewer" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</fieldset>
	<fieldset><legend>Man-Hole:</legend>
	<center>
	<table>
		<tr>
			<td><label>Size</label></td>
			<td><html:text property="manholeSize" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="manholeCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="manholeQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

		<tr>
			<td><label>Depth: Min:</label></td>
			<td><html:text property="manholeDepthMin" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Max:</label></td>
			<td><html:text property="manholeDepthMax" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td />
			<td />
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Pumping Station</legend>
	<center>
	<table>
		<tr>
			<td><label>Discharge</label></td>
			<td><html:text property="pumpingDischarge" styleClass="ci5"></html:text></td>
			<td><label>Head</label></td>
			<td><html:text property="pumpingHead" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="pumpingCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Collection Tank at STP</legend>
	<center>
	<table>
		<tr>
			<td><label>Diameter(mtr)</label></td>
			<td><html:text property="collectionStpDiameter" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td colspan="3"><html:text property="collectionStpCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Collection Tank at IPS</legend>
	<center>
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Diameter(mtr)</label></td>
			<td><html:text property="collectionIpsDimeter" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="collectionIpsCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="collectionIpsQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Machinery:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Type</label></td>
			<td><html:text property="machineryType" styleClass="ci5"></html:text></td>
			<td><label>Head(mtr)</label></td>
			<td><html:text property="machineryHead" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="machineryCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Quantity</label></td>
			<td><html:text property="machineryQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Discharge(lpm)</label></td>
			<td><html:text property="machineryDischarge" styleClass="ci5"></html:text></td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>Genset cum Pannel Room</legend>
	<center>
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Quantity</label></td>
			<td><html:text property="gensetPannelQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="gensetPannelCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Genset:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Capacity</label></td>
			<td><html:text property="gensetCapacity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="gensetQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td>&nbsp;&nbsp;&nbsp;<label>Electric Connection Cost</label></td>
			<td><html:text property="gensetElectricConnectionCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Electric Connection Load</label></td>
			<td><html:text property="gensetElectricConnectionLoad"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Rest Room for Operator:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Quantity</label></td>
			<td><html:text property="restroomQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="restroomCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Disposal Works</legend>
	<center>
	<table>
		<tr>
			<td><font size="3"><b>Sewerage Treatment Plant:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Technology</label></td>
			<td><html:text property="sewerageTechnology" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="sewerageCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td />
			<td />
		</tr>
		<tr>
			<td><font size="3"><b>Site Development:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Cost</label></td>
			<td><html:text property="siteDevelopmentCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Rising Main:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Type</label></td>
			<td><html:text property="risingType" styleClass="ci5"></html:text></td>
			<td><label>Size</label></td>
			<td><html:text property="risingSize" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="risingCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Pumping Machinery (If
			applicable):</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Discharge</label></td>
			<td><html:text property="pumpingMachineryDischarge"
				styleClass="ci5"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Sludge Disposal</legend>
	<center>
	<table>
		<tr>
			<td><label>Sludge Drying Bed Cost</label></td>
			<td><html:text property="sludgeDryingCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Sludge Curing Platform Cost</label></td>
			<td><html:text property="sludgeCuringCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Composition Pit</label></td>
			<td><html:text property="compositionCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Summary</legend>
	<center>
	<table>
		<tr>
			<td><label>Provision for Environment Activities(Total
			Cost)</label></td>
			<td><html:text property="provisionEnvironmentActivitiesCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Total Cost of Structure</label></td>
			<td><html:text property="totalCostOfStructure" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>O & M cost for 7 years</label></td>
			<td><html:text property="OMcostForSevenYears" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Environment Cost</label></td>
			<td><html:text property="environmentCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Contigency Charges</label></td>
			<td><html:text property="contigencyCharges" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Grand Total</label></td>
			<td><html:text property="grandTotal" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
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
		triggerEvent(document.getElementById('villageId'), 'onchange');
		document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
	</script>
</html:form>
</html:html>