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
			document.dsrTubewellForm.action="dsrTubewellAction.do?method=find&d__mode="+d__mode+"&menuId=PMM007";
			document.dsrTubewellForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.dsrTubewellForm.action="dsrTubewellAction.do?method=update&d__mode="+d__mode+"&menuId=PMM007";
				document.dsrTubewellForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.dsrTubewellForm.action="dsrTubewellAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM007";
				document.dsrTubewellForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.dsrTubewellForm.action="dsrTubewellAction.do?method=post&d__mode="+d__mode+"&menuId=PMM007";
				document.dsrTubewellForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.dsrTubewellForm.action="dsrTubewellAction.do?method=save&d__mode="+d__mode+"&menuId=PMM007";
				document.dsrTubewellForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="dsrTubewellAction" method="post">
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
	<fieldset><legend>Tube Well</legend>
	<center>
	<table border="1">
		<tr>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b> Tube Well 1</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Size</label></td>
					<td><html:text property="tubewellSize" styleClass="ci5"></html:text></td>
					<td><label>Depth</label></td>
					<td><html:text property="tubewellDepth" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td><html:text property="tubewellCost" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
			</td>
			<td></td>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b> Tube Well 2</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Size</label></td>
					<td><html:text property="tubewellSize1" styleClass="ci5"></html:text></td>
					<td><label>Depth</label></td>
					<td><html:text property="tubewellDepth1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td><html:text property="tubewellCost1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Pump Chamber</legend>
	<center>
	<table>
		<tr>
			<td><label>Select</label></td>
			<td><html:select property="pumpSelect" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="PUMP-CHAMBER">Pump Chamber</html:option>
				<html:option value="STAFF-QUARTER">Staff Quarter</html:option>
				<html:option value="PUMP-CHAMBER-CUM-STAFF-QUARTER">Pump Chamber cum Staff Quarter </html:option>
				<html:option value="BOTH">Both</html:option>
			</html:select></td>
			<td><label>Quantity</label></td>
			<td><html:text property="pumpQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Total Cost</label></td>
			<td><html:text property="pumpTotalCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
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
					<center><font size="4"><b> Pumping Machinery 1</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Centrifugal</label></td>
					<td><html:text property="pumpingCentrifugal" styleClass="ci5"></html:text></td>
					<td><label>Discharge(lpm)</label></td>
					<td><html:text property="pumpingDischarge" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="pumpingQuantity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Submersible</label></td>
					<td><html:text property="pumpingSubmersible" styleClass="ci5"></html:text></td>
					<td><label>Head(mtr)</label></td>
					<td><html:text property="pumpingHead" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td><html:text property="pumpingCost" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="2"><b>Cost of Electric
					Connection Provision:</b></font></td>
					<td colspan="3"><html:text property="costOfElectricConnection"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="2"><b>Cost of C.I
					fitting inside pump chamber:</b></font></td>
					<td colspan="3"><html:text property="costOfCIFitting"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="6"><font size="3"><b>V-Notch Houdies:</b></font></td>
				</tr>
				<tr>
					<td><label>Quantity</label></td>
					<td><html:text property="vnotchQuantity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td colspan="3"><html:text property="vnotchCost"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
			</td>
			<td></td>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b> Pumping Machinery 2</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Centrifugal</label></td>
					<td><html:text property="pumpingCentrifugal1" styleClass="ci5"></html:text></td>
					<td><label>Discharge(lpm)</label></td>
					<td><html:text property="pumpingDischarge1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="pumpingQuantity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Submersible</label></td>
					<td><html:text property="pumpingSubmersible1" styleClass="ci5"></html:text></td>
					<td><label>Head(mtr)</label></td>
					<td><html:text property="pumpingHead1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td><html:text property="pumpingCost1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="2"><b>Cost of Electric
					Connection Provision:</b></font></td>
					<td colspan="3"><html:text
						property="costOfElectricConnection1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="2"><b>Cost of C.I
					fitting inside pump chamber:</b></font></td>
					<td colspan="3"><html:text property="costOfCIFitting1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="6"><font size="3"><b>V-Notch Houdies:</b></font></td>
				</tr>
				<tr>
					<td><label>Quantity</label></td>
					<td><html:text property="vnotchQuantity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td colspan="3"><html:text property="vnotchCost1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	</center>
	</fieldset>
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
			<td><label>Quanitity</label></td>
			<td><html:text property="disinfectionQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="disinfectionCost" styleClass="ci5"
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
					<center><font size="4"><b> RCC OHSR 1</b></font></center>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Capacity(ltr)</label></td>
					<td><html:text property="rccCapacity" styleClass="ci5"></html:text></td>
					<td><label>FSL(mtr)</label></td>
					<td><html:text property="rccFsl" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="rccQuantity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Cost</label></td>
					<td colspan="3"><html:text property="rccCost" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="6"><font size="3"><b>Electronic Water
					Level Control Device:</b></font></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Quantity</label></td>
					<td><html:text property="electronicQuantity" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td colspan="3"><html:text property="electronicCost"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>

				<tr>
					<td colspan="6"><font size="3"><b>Plinth Protection
					around OHSR:</b></font></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Cost</label></td>
					<td colspan="5"><html:text property="plinthCost"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>

			</table>
			</td>
			<td></td>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b> RCC OHSR 2</b></font></center>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Capacity(ltr)</label></td>
					<td><html:text property="rccCapacity1" styleClass="ci5"></html:text></td>
					<td><label>FSL(mtr)</label></td>
					<td><html:text property="rccFsl1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Quantity</label></td>
					<td><html:text property="rccQuantity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Cost</label></td>
					<td colspan="3"><html:text property="rccCost1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="6"><font size="3"><b>Electronic Water
					Level Control Device:</b></font></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Quantity</label></td>
					<td><html:text property="electronicQuantity1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td><label>Cost</label></td>
					<td colspan="3"><html:text property="electronicCost1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>

				<tr>
					<td colspan="6"><font size="3"><b>Plinth Protection
					around OHSR:</b></font></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label>Cost</label></td>
					<td colspan="5"><html:text property="plinthCost1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>

			</table>

			</td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Rising Main(TW to OHSR):</legend>
	<center>
	<table border="1">
		<tr>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b>Rising Main(TW to
					OHSR) 1</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Size</label></td>
					<td><html:text property="risingSize" styleClass="ci5"></html:text></td>
					<td><label>Type</label></td>
					<td><html:text property="risingType" styleClass="ci5"></html:text></td>
					<td><label>Length</label></td>
					<td><html:text property="risingLength" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Cost</label></td>
					<td colspan="3"><html:text property="risingCost"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="3"><b>Total Cost of
					Development of Water Works Site:</b></font></td>
					<td colspan="3"><html:text
						property="totalCostDevelopmentWater" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="3"><b>Total Cost of
					Tropographical Survey:</b></font></td>
					<td colspan="3"><html:text property="totalCostTopographical"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
			</td>
			<td></td>
			<td>
			<table>
				<tr>
					<td colspan="4">
					<center><font size="4"><b>Rising Main(TW to
					OHSR) 2</b></font></center>
					</td>
				</tr>
				<tr>
					<td><label>Size</label></td>
					<td><html:text property="risingSize1" styleClass="ci5"></html:text></td>
					<td><label>Type</label></td>
					<td><html:text property="risingType1" styleClass="ci5"></html:text></td>
					<td><label>Length</label></td>
					<td><html:text property="risingLength1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td><label>Cost</label></td>
					<td colspan="3"><html:text property="risingCost1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="3"><b>Total Cost of
					Development of Water Works Site:</b></font></td>
					<td colspan="3"><html:text
						property="totalCostDevelopmentWater1" styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
				<tr>
					<td colspan="3"><font size="3"><b>Total Cost of
					Tropographical Survey:</b></font></td>
					<td colspan="3"><html:text property="totalCostTopographical1"
						styleClass="ci5"
						onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
			</td>

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
	<fieldset><legend>Distribution System</legend>
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
				property="dsrTubeWellDistributionGrid" selectionAllowed="true"
				multipleSelectionAllowed="false" model="datagrid">
				<layout:datagridColumn property="distributionType"
					title="Type of Pipe"></layout:datagridColumn>
				<layout:datagridColumn property="distributionSize" title="Size"></layout:datagridColumn>
				<layout:datagridColumn property="distributionLength" title="Length"></layout:datagridColumn>
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('dsrTubeWellDistributionGrid','typeofpipeId~sizeId~lenghtId')"
				width="20px" /><br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('dsrTubeWellDistributionGrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	<table>
		<tr>
			<td><font size="3"><b>Total Cost:</b></font></td>
			<td colspan="3"><html:text property="distributionTotalCost"
				styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Slueice Value:</b></font></td>
			<td><label>Size</label></td>
			<td><html:text property="slueiceSize" styleClass="ci5"></html:text></td>
			<td><label>Cost</label></td>
			<td><html:text property="slueiceCost" styleClass="ci5"
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
			<td><html:text property="totalCostStructure" styleClass="ci5"
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
</html:form>
<script language="javascript">
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		triggerEvent(document.getElementById('blockId'), 'onchange');
		document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
		triggerEvent(document.getElementById('villageId'), 'onchange');
		document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
	</script>
</html:html>