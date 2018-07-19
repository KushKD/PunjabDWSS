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
			document.dsrCanalForm.action="dsrCanalAction.do?method=find&d__mode="+d__mode+"&menuId=PMM004";
			document.dsrCanalForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.dsrCanalForm.action="dsrCanalAction.do?method=update&d__mode="+d__mode+"&menuId=PMM004";
				document.dsrCanalForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.dsrCanalForm.action="dsrCanalAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM004";
				document.dsrCanalForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.dsrCanalForm.action="dsrCanalAction.do?method=post&d__mode="+d__mode+"&menuId=PMM004";
				document.dsrCanalForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.dsrCanalForm.action="dsrCanalAction.do?method=save&d__mode="+d__mode+"&menuId=PMM004";
				document.dsrCanalForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="dsrCanalAction" method="post">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors
			bundle="pmm" /></div>
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
	<fieldset><legend>Disinfection Unit</legend>
	<center>
	<table>
		<tr>
			<td><label>Type</label></td>
			<td><html:select property="disinfectionType"
				style="width:300px;">
				<html:option value="">Select</html:option>
				<html:option value="<%=MISConstants.SILVER_IONIZATION_UNIT%>">Silver Ionization Unit</html:option>
				<html:option value="<%=MISConstants.GAS_CLORINATION%>">Gas Clorination</html:option>
				<html:option value="<%=MISConstants.BLEACHING_POWDER%>">Bleaching powder</html:option>
				<html:option value="<%=MISConstants.HYPO%>">Hypo</html:option>
			</html:select></td>
			<td><label>Cost</label></td>
			<td><html:text property="disinfectionCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="disinfectionQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Pumping Chamber</legend>
	<center>
	<table>
		<tr>
			<td><label>Select</label></td>
			<td>
			<div class="selectBorder"><html:select property="pumpSelect"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="PUMP-CHAMBER">Pump Chamber</html:option>
				<html:option value="STAFF-QUARTER">Staff Quarter</html:option>
				<html:option value="PUMP-CHAMBER-CUM-STAFF-QUARTER">Pump Chamber cum Staff Quarter </html:option>
				<html:option value="BOTH">Both</html:option>
			</html:select></div>
			</td>
			<td><label>Quantity</label></td>
			<td><html:text property="pumpQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="pumpCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>RCC OHSR</legend>
	<center>
	<table border="1">
		<tr>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b>RCC OHSR 1</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Capacity(ltr)</label></td>
					<td><html:text property="rccCapacity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>FSL(mtr)</label></td>
					<td><html:text property="rccFsl" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="rccQuantity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Cost</label></td>
					<td><html:text property="rccCost" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
			</td>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b>RCC OHSR 2</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Capacity(ltr)</label></td>
					<td><html:text property="rccCapacity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>FSL(mtr)</label></td>
					<td><html:text property="rccFsl1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="rccQuantity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Cost</label></td>
					<td><html:text property="rccCost1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Pumping Machinery</legend>
	<center>
	<table border="1">
		<tr>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b>Pumping Machinery 1</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Centrifugal</label></td>
					<td><html:text property="pumpingCentrifugal" styleClass="ci5"></html:text></td>
					<td><label>Submersible</label></td>
					<td><html:text property="pumpingSubmersible" styleClass="ci5"></html:text></td>
					<td><label>Head(mtr)</label></td>
					<td><html:text property="pumpingHead" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Discharge</label></td>
					<td><html:text property="pumpingDischarge" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="pumpQuantity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td><html:text property="pumpCost" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
			</td>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b>Pumping Machinery 2</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Centrifugal</label></td>
					<td><html:text property="pumpingCentrifugal1" styleClass="ci5"></html:text></td>
					<td><label>Submersible</label></td>
					<td><html:text property="pumpingSubmersible1" styleClass="ci5"></html:text></td>
					<td><label>Head(mtr)</label></td>
					<td><html:text property="pumpingHead1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Discharge</label></td>
					<td><html:text property="pumpingDischarge1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="pumpQuantity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td><html:text property="pumpCost1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>

	</center>
	</fieldset>
	<fieldset><legend>Pipe Line Head Works</legend>
	<center>
	<table>
		<tr>
			<td><label>Size</label></td>
			<td><html:text property="piplineSize" styleClass="ci5"></html:text></td>
			<td><label>Type</label></td>
			<td><html:text property="piplineType" styleClass="ci5"></html:text></td>
			<td><label>Length</label></td>
			<td><html:text property="piplineLength" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Cost</label></td>
			<td colspan="5"><html:text property="piplineCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td colspan="3"><font size="3"><b>Total Cost of Site
			Laying at Head Works:</b></font></td>
			<td><html:text property="totalCostSiteLaying" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Distribution System</legend>
	<center>
	<table>
		<tr>

			<td><label>Type of Pipe</label></td>
			<td><input type="text" id="typeofpipeId" class="ci5"></td>
			<td><label>Size</label></td>
			<td><input type="text" id="sizeId" Class="ci5"></td>
			<td><label>Length</label></td>
			<td><input type="text" id="lenghtId" Class="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="dsrCanalDistributionDatagrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="id" title="Id" mode="N,N,N"></layout:datagridColumn>
				<layout:datagridColumn property="distributionTypeOfPipe"
					title="Type of Pipe"></layout:datagridColumn>
				<layout:datagridColumn property="distributionSize" title="Size"></layout:datagridColumn>
				<layout:datagridColumn property="distributionLength" title="Length"></layout:datagridColumn>
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('dsrCanalDistributionDatagrid','typeofpipeId~sizeId~lenghtId')"
				width="20px" /><br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('dsrCanalDistributionDatagrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	<table align="left">
		<tr>
			<td colspan="3"><font size="3"><b>Total Distribution
			System Cost:</b></font></td>
			<td><html:text property="distributionCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset>
	<center>
	<table>
		<tr>
			<td><b>Slueice Value:Size</b></td>
			<td><html:text property="slueiceSize" styleClass="ci5"></html:text></td>
			<td><b>Slueice Value:Cost</b></td>
			<td><html:text property="slueiceCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><b>Cost of Electric Connection Provision:</b></td>
			<td><html:text property="costOfElectricConnectionProvision"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><b>Total Cost of Topographical Survey:</b></td>
			<td><html:text property="totalCostOfTopographicalSurvey"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>Storage & Sendimentation Tank</legend>
	<center>
	<table>
		<tr>
			<td><label>Size (A X B)</label></td>
			<td><html:text property="storageSize" styleClass="ci5"></html:text></td>
			<td><label>Depth(mtr)</label></td>
			<td><html:text property="storageDepth" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="storageQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Cost</label></td>
			<td colspan="5"><html:text property="storageCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Suction Cum Scourwell</legend>
	<center>
	<table>
		<tr>
			<td><label>Diameter(mtr)</label></td>
			<td><html:text property="suctionDimeter" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="suctionCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="suctionQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>High Level Tank</legend>
	<center>
	<table>
		<tr>
			<td><label>Type</label></td>
			<td><html:text property="highType" styleClass="ci5"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="highQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Size</label></td>
			<td><html:text property="highSize" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Cost</label></td>
			<td colspan="5"><html:text property="highCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>Filter Beds</legend>
	<center>
	<table>
		<tr>
			<td><label>Type</label></td>
			<td><html:text property="filterType" styleClass="ci5"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="filterQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Size</label></td>
			<td><html:text property="filterSize" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Cost</label></td>
			<td colspan="5"><html:text property="filterCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>Clear Water Tank</legend>
	<center>
	<table>
		<tr>
			<td><label>Type</label></td>
			<td><html:text property="clearType" styleClass="ci5"></html:text></td>
			<td><label>Quantity</label></td>
			<td><html:text property="clearQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Size</label></td>
			<td><html:text property="clearSize" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Cost</label></td>
			<td colspan="5"><html:text property="clearCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>Bulk Water Meter</legend>
	<center>
	<table>
		<tr>
			<td><label>Size</label></td>
			<td><html:text property="bulkSize" styleClass="ci5"></html:text></td>
			<td><label>Count</label></td>
			<td><html:text property="bulkCount" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Total Cost</label></td>
			<td><html:text property="bulkTotalCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Inlet Channel</legend>
	<center>
	<table>
		<tr>

			<td><label>Type of Pipe</label></td>
			<td><input type="text" id="typeofpipeId2" Class="ci5"></td>
			<td><label>Size</label></td>
			<td><input type="text" id="sizeId2" Class="ci5"></td>
			<td><label>Length</label></td>
			<td><input type="text" id="lenghtId2" Class="ci5"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID"
				property="dsrCanalInletDatagrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="id" title="Id" mode="N,N,N"></layout:datagridColumn>
				<layout:datagridColumn property="inletType" title="Type of Pipe"></layout:datagridColumn>
				<layout:datagridColumn property="inletSize" title="Size"></layout:datagridColumn>
				<layout:datagridColumn property="inletLength" title="Length"></layout:datagridColumn>
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('dsrCanalInletDatagrid','typeofpipeId2~sizeId2~lenghtId2')"
				width="20px" /><br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('dsrCanalInletDatagrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	<table align="left">
		<tr>
			<td colspan="3"><font size="3"><b>Total Inlet System
			Cost:</b></font></td>
			<td><html:text property="inletTotalCost"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Pumping Station</legend>
	<center>
	<table>
		<tr>
			<td><font size="3"><b>If Applicable:</b></font></td>
			<td>
			<div class="selectBorder"><html:select
				property="pumpingMachineryApplicable" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="true">Yes</html:option>
				<html:option value="false">No</html:option>
			</html:select></div>
			</td>

		</tr>
		<tr>
			<td><label>Size</label></td>
			<td><html:text property="pumpingMachinerySize" styleClass="ci5"></html:text></td>
			<td><label>Power</label></td>
			<td><html:text property="pumpingPower" styleClass="ci5"></html:text></td>
			<td><label>Discharge</label></td>
			<td><html:text property="pumpingMachineryDischarge"
				styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Cost</label></td>
			<td colspan="3"><html:text property="pumpingMachineryCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>

	</table>
	</center>
	</fieldset>
	<fieldset><legend>Summary</legend>
	<center>
	<table>
		<tr>
			<td><label>Total Cost of Structure</label></td>
			<td><html:text property="totalCostOfStructure" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Percapita Cost</label></td>
			<td><html:text property="percapitaCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Environment Cost</label></td>
			<td><html:text property="environmentCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Contigency Charges</label></td>
			<td><html:text property="contigencyCharges" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Grand Total</label></td>
			<td colspan="3"><html:text property="grandTotal"
				styleClass="ci5"
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