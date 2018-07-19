<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
hide_ctrl('modalPeriod',true);
		function de_find(){		
			document.operatingArrangementForm.action="operatingArrangementAction.do?method=find&d__mode="+d__mode+"&menuId=PMM015";
			document.operatingArrangementForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				var result = true;//valid.validate();
				if(result){
					document.operatingArrangementForm.action="operatingArrangementAction.do?method=update&d__mode="+d__mode+"&menuId=PMM015";
					document.operatingArrangementForm.submit();
					}
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.operatingArrangementForm.action="operatingArrangementAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM015";
				document.operatingArrangementForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.operatingArrangementForm.action="operatingArrangementAction.do?method=post&d__mode="+d__mode+"&menuId=PMM015";
				document.operatingArrangementForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				var result = true;//valid.validate();
				if(result){
					document.operatingArrangementForm.action="operatingArrangementAction.do?method=save&d__mode="+d__mode+"&menuId=PMM015";
					document.operatingArrangementForm.submit();
				}
			}
		}		    
	</script>
</head>


<html:html>
<body bgcolor="#6699FF">

	<logic:messagesPresent>
		<div id="modalPeriod"
			style="position: absolute; width: 600px; border: 3px solid black; background-color: #00A2E2; font-size: 150%; text-align: Left; display: none;">
			<table width="600px">
				<tr>
					<td><font size="3" color="white"><b><html:errors /></b></font></td>
				</tr>
				<tr>
					<td align="center"><input type="button" value="OK"
						id="period_ok"
						onClick="hide_ctrl('modalPeriod',true);return false;"></td>
				</tr>
			</table>

		</div>
		<script type="text/javascript">
var alertObj=document.getElementById("modalPeriod");
	// center the alert box
if(document.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop+50 + "px";
alertObj.style.left = (document.documentElement.scrollWidth - alertObj.offsetWidth)/4 + "px";
//alert(alertObj.style.left+":"+document.documentElement.scrollWidth+":"+ alertObj.offsetWidth);
hide_ctrl('modalPeriod',false);</script>
	</logic:messagesPresent>

	<html:form action="operatingArrangementAction" method="post">
		<fieldset>
			<center>
				<label style="font-size: 20px; font-family: times;">Data
					pertaining to this screen should be entered by 7th of following
					month</label>
			</center>
		</fieldset>
		<fieldset>
			<legend>Scheme Detail</legend>
			<center>
				<table>
					<tr>
						<td><label>Location</label></td>
						<td><html:select property="locationId" styleId="locationId"
								styleClass="cs2"
								onchange="ajaxFunction('GetSchemeCodeFilterValues.to?type=GET_SCHEME&locationId='+this.value, 'schemeId');">
								<html:option value="NOT SPECIFIED">Select Location</html:option>
								<html:options collection="userLocations" labelProperty="label"
									property="value"></html:options>
							</html:select></td>
						<td><label>Scheme &nbsp&nbsp</label></td>
						<td><html:select styleClass="wide" property="schemeCode"
								styleId="schemeId">
							</html:select></td>

					</tr>
					<tr>
						<td><label>Sub-Program</label></td>
						<td><html:select property="programId" styleId="programId"
								styleClass="cs2">
								<html:option value=""></html:option>
								<html:options collection="programIds" labelProperty="label"
									property="value"></html:options>


							</html:select></td>

						<td><label>Scheme Name</label></td>
						<td><html:text property="schemeName" styleClass="cs2"
								styleId="schemeName" /></td>
					</tr>
					<tr>
						<td><input type='hidden' name="waterWorksLocation" /></td>
					<tr>
					<tr>
						<td><label>Scheme Source</label></td>
						<td colspan="2"><html:select property="schemeSource"
								styleId="schemeSource" styleClass="cs2">
								<html:option value=""></html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL%>">Tube Well</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO%>">Tube Well with RO</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_CANNAL%>">Canal</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_LIFTING_WATER_RSD_LAKE%>">Lifting of water from RSD Lake</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_PERCULATION_WELL%>">Perculation Well</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_DISTRIBUTION%>">Distribution</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP%>">Hand Pump</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_PONDS%>">Ponds</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP%>">Roof Top Rain Harvesting</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_IMPROVEMENT%>">IMPROVEMENT</html:option>
								<html:option
									value="<%=MISConstants.VILLAGE_WATER_SOURCE_SEWERAGE%>">SEWERAGE</html:option>



							</html:select>
					</tr>
				</table>
			</center>
		</fieldset>
		<fieldset>
			<legend>Village Operating Arrangement Information</legend>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font><b>*Please
					Provide Village HandedOver Date In the Format --> DD-MM-YYYY<b></font>
			<center>
				<table>
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID"
									property="villageOperatingDatagrid" selectionAllowed="true"
									multipleSelectionAllowed="false" model="datagrid">
									<layout:datagridColumn property="villageId" title="Village Id"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="villageName"
										title="Village Name" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="habitationType"
										title="Habitation Type" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="supplyServiceLevel"
										title="Water Supply Service Level" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="villageCommissioningDate"
										title="Village Commissioned Date" mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="villagecategory"
										title="Village Category" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridSelect property="schemeOperatedBy"
										title="Scheme Operated By">
										<layout:option value="" />
										<layout:option
											value="<%=MISConstants.SCHEME_OPERATED_BY_GP_SELF%>" />
										<layout:option
											value="<%=MISConstants.SCHEME_OPERATED_BY_GP_OUTSOURCED%>" />
										<layout:option
											value="<%=MISConstants.SCHEME_OPERATED_BY_GPWSC_SELF%>" />
										<layout:option
											value="<%=MISConstants.SCHEME_OPERATED_BY_GPWSC_OUTSOURCED%>" />
										<layout:option
											value="<%=MISConstants.SCHEME_OPERATED_BY_DWSS_SELF%>" />
										<layout:option
											value="<%=MISConstants.SCHEME_OPERATED_BY_DWSS_OUTSOURCED%>" />
									</layout:datagridSelect>


									<layout:datagridColumn property="operationArrangementDate"
										title="Village HandedOver On Date"></layout:datagridColumn>

									<%-- <layout:datagridSelect property="schemeHours"
										title="Supply Hours">
										<layout:option value="0" />
										<layout:option value="1" />
										<layout:option value="2" />
										<layout:option value="3" />
										<layout:option value="4" />
										<layout:option value="5" />
										<layout:option value="6" />
										<layout:option value="7" />
										<layout:option value="8" />
										<layout:option value="9" />
										<layout:option value="10" />
										<layout:option value="11" />
										<layout:option value="12" />
										<layout:option value="13" />
										<layout:option value="14" />
										<layout:option value="15" />
										<layout:option value="16" />
										<layout:option value="17" />
										<layout:option value="18" />
										<layout:option value="19" />
										<layout:option value="20" />
										<layout:option value="21" />
										<layout:option value="22" />
										<layout:option value="23" />
										<layout:option value="24" />

									</layout:datagridSelect>
									<layout:datagridSelect property="schemeFP" title="Supply Mod">
										<layout:option value="" />
										<layout:option value="Continuous" />
										<layout:option value="Intermittentl" />
									</layout:datagridSelect>
								 --%></layout:datagrid>
							</div>
						</td>
					</tr>
				</table>
			</center>
		</fieldset>
		<script>
	if(document.getElementById('schemeName').value!=''){
		readOnly_ctrl_nb('schemeName',true);
		
	}
	//var valid = new Validation('schemeMaster', {onSubmit:false});
	triggerEvent(document.getElementById('locationId'), 'onchange');
	<%if (MisUtility.ifEmpty(request.getAttribute("schemeId"))) {%>
	document.getElementById("schemeId").value="<%=request.getAttribute("schemeId")%>";
		<%}%>
			
		</script>
	</html:form></html:html>