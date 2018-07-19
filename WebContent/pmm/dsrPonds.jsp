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
			document.dsrPondsForm.action="dsrPondsAction.do?method=find&d__mode="+d__mode+"&menuId=PMM005";
			document.dsrPondsForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.dsrPondsForm.action="dsrPondsAction.do?method=update&d__mode="+d__mode+"&menuId=PMM005";
				document.dsrPondsForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.dsrPondsForm.action="dsrPondsAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM005";
				document.dsrPondsForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.dsrPondsForm.action="dsrPondsAction.do?method=post&d__mode="+d__mode+"&menuId=PMM005";
				document.dsrPondsForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.dsrPondsForm.action="dsrPondsAction.do?method=save&d__mode="+d__mode+"&menuId=PMM005";
				document.dsrPondsForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
<html:form action="dsrPondsAction" method="post">
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
	<fieldset><legend>Removal of Sullage and
	Construction of Embankment</legend> <left>
	<table colspan="6">
		<tr>
			<td><font size="3"><b>Cost of Hiring JCB:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Hours</label></td>
			<td><html:text property="jcbHours" styleClass="ci5"></html:text></td>

			<td><label>Total Cost</label></td>
			<td><html:text property="jcbHoursCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Cost of Hiring Tractors:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Hours</label></td>
			<td><html:text property="tractorsHours" styleClass="ci5"></html:text></td>

			<td><label>Total Cost</label></td>
			<td><html:text property="tractorsHoursCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><font size="3"><b>Manual Labour:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Man-Days</label></td>
			<td><html:text property="labourMandays" styleClass="ci5"></html:text></td>

			<td><label>Total Cost</label></td>
			<td><html:text property="labourMandaysCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>

		</tr>
		<tr>
			<td nowrap>&nbsp;&nbsp;&nbsp;&nbsp;<label>Unforseen
			Items Cost</label></td>
			<td><html:text property="unforeseenCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>

	<table>
		<tr>
			<td><font size="3"><b>Construction of Haudis:</b></font></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Quantity</label></td>
			<td><html:text property="haudisQuantity" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>

			<td><label>Total Cost</label></td>
			<td><html:text property="haudisCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	<table>

		<tr>
			<td><font size="3"><b>Pipelines:Pipelines:</b></font></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;<label>Length (mtr)</label></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:text property="pipelineLength" styleClass="ci5"></html:text></td>

			<td><label>Total Cost</label></td>
			<td><html:text property="pipelineCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>&nbsp;&nbsp;&nbsp;<label>Cost of
			Grass,plants,fertilizer on Embankment, other Allied jobs</label></td>
			<td><html:text property="otheralliedCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td nowrap>&nbsp;&nbsp;&nbsp;<label>Cost of drains
			arround Ponds</label></td>
			<td><html:text property="drainsCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>

			<td><font size="3"><b>Total DSR Cost</b></font></td>
			<td><html:text property="totalCost" styleClass="ci5"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
	</table>
	</left></fieldset>
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