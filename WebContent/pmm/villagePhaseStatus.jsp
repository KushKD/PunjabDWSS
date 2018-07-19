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
			document.villagePhaseForm.action="villagePhaseAction.do?method=find&d__mode="+d__mode+"&menuId=PMM013";
			document.villagePhaseForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.villagePhaseForm.action="villagePhaseAction.do?method=update&d__mode="+d__mode+"&menuId=PMM013";
				document.villagePhaseForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.villagePhaseForm.action="villagePhaseAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM013";
				document.villagePhaseForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.villagePhaseForm.action="villagePhaseAction.do?method=post&d__mode="+d__mode+"&menuId=PMM013";
				document.villagePhaseForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.villagePhaseForm.action="villagePhaseAction.do?method=save&d__mode="+d__mode+"&menuId=PMM013";
				document.villagePhaseForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">

<html:form action="villagePhaseAction" method="post">
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
	<%@include file="../JSPF/blockVillageSchemeFilter.jspf"%>
	<fieldset><legend>Scheme Phase Status</legend>
	<center>
	<table>
		<tr>
			<td><label>Pre-Planning Phase(Date)</label></td>
			<td><html:text property="prePlanningDate"
				styleId="prePlanningDateId" styleClass="ci3" readonly="readonly" /></td>
			<td><!--<input class=ci4 type=button onclick="c1.innerpopup('prePlanningDateId','calendar_frame');" value="..."/>--></td>
		</tr>
		<tr>

			<td><label>Planning Phase(Date)</label></td>
			<td><html:text property="planningDate" styleId="planningDateId"
				styleClass="ci3" readonly="readonly" /></td>
			<td><!--<input class=ci4 type=button onclick="c1.innerpopup('planningDateId','calendar_frame');" value="..."/>--></td>
		</tr>
		<tr>

			<td><label>Implementation Phase(Date)</label></td>
			<td><html:text property="implementationDate"
				styleId="implementationDateId" styleClass="ci3" readonly="readonly" /></td>
			<td><!--<input class=ci4 type=button onclick="c1.innerpopup('implementationDateId','calendar_frame');" value="..."/>--></td>
		</tr>
		<tr>

			<td><label>O & M Phase(Date)</label></td>
			<td><html:text property="omDate" styleId="omDateId"
				styleClass="ci3" readonly="readonly" /></td>
			<td><!--<input class=ci4 type=button onclick="c1.innerpopup('omDateId','calendar_frame');" value="..."/>--></td>
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