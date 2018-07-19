<%@page import="com.prwss.mis.common.util.MisUtility"%>
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
			document.labTestingForm.action="labTestingAction.do?method=find&d__mode="+d__mode+"&menuId=PMM010";
			document.labTestingForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.labTestingForm.action="labTestingAction.do?method=update&d__mode="+d__mode+"&menuId=PMM010";
				document.labTestingForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.labTestingForm.action="labTestingAction.do?method=delete&d__mode="+d__mode+"&menuId=PMM010";
				document.labTestingForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.labTestingForm.action="labTestingAction.do?method=post&d__mode="+d__mode+"&menuId=PMM010";
				document.labTestingForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.labTestingForm.action="labTestingAction.do?method=save&d__mode="+d__mode+"&menuId=PMM010";
				document.labTestingForm.submit();
			}
		}

		function hideControl(value){
			if(value == 'PHYSICAL-CHEMICAL'){
				hide_ctrl('bacteriological',true);
				hide_ctrl('chemical',false);
				}else{
					hide_ctrl('chemical',true);
					hide_ctrl('bacteriological',false);
					}
			}
	</script>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
	-->
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body bgcolor="#6699FF">

<html:form action="labTestingAction" method="post">
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
	<fieldset><legend>Laboratory Testing Details </legend>
	<center>
	<table>
		<tr>
			<td><label>Lab Test Number</label></td>
			<td><html:text property="testId" styleId="testElementId"
				styleClass="ci5"></html:text></td>
			<td><label>Lab Name</label></td>
			<td><html:text property="labName" styleClass="ci5"
				onkeypress="return charKey(event);"></html:text></td>
			<td><label>Test date</label></td>
			<td><html:text property="testDate" styleId="testDateId"
				styleClass="ci5" readonly="readonly"></html:text> <!--<input class=ci4 type=button onclick="c1.innerpopup('testDateId','calendar_frame');" value="..."/>-->
			</td>
		</tr>

		<tr>
			<td><label>Habitation Name</label></td>
			<td><html:text property="habitation" styleClass="ci5"
				onkeypress="return charKey(event);"></html:text></td>
			<td><label>Type of Parameter to be tested </label></td>
			<td><html:select property="typeOfParameter" styleClass="cs1"
				onchange="hideControl(this.value)" styleId="typeOfParameterId">
				<html:option value="">Select</html:option>
				<html:option value="PHYSICAL-CHEMICAL">Physical & Chemical</html:option>
				<!--<html:option value="CHEMICAL">Chemical</html:option>-->
				<html:option value="BACTERIOLOGICAL">Bacteriological</html:option>
				<!--<html:option value="BOTH">Both</html:option>-->
			</html:select></td>

			<td><label>Overall Sample Result</label></td>
			<td><html:select property="overallSampleResult" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="FAIL">Fail</html:option>
				<html:option value="PASS">Pass</html:option>
			</html:select></td>
		</tr>

		<tr>
			<td><label>Choose the type of water source</label></td>
			<td colspan="5"><html:radio property="typeOfWaterSource"
				value="SCHEME-SOURCES" styleClass="ci5">Schemes Sources</html:radio>
			<html:radio property="typeOfWaterSource" value="DELIVERY-POINT"
				styleClass="ci5">Delivery Point</html:radio> <html:radio
				property="typeOfWaterSource" value="SURFACE-WATER" styleClass="ci5">Surface Water</html:radio>
			<html:radio property="typeOfWaterSource" value="PRIVATE"
				styleClass="ci5">Private</html:radio> <html:radio
				property="typeOfWaterSource" value="PUBLIC" styleClass="ci5">Public</html:radio>
			<html:radio property="typeOfWaterSource" value="ALL-SOURCES"
				styleClass="ci5">All Sources</html:radio></td>
		</tr>
		<tr>

		</tr>
		<tr>

		</tr>
	</table>
	</center>
	</fieldset>
	<div id="chemical">
	<fieldset><legend>Chemical & Physical Parameters</legend>
	<center>
	<table class="form2" style="margin: 0 1em 1em 0;">
		<tr>
			<th class="form2TH"><label>Test</label></th>
			<th class="form2TH"><label>Desirable Limit</label></th>
			<th class="form2TH"><label>Permissible Limit</label></th>
			<th class="form2TH"><label>Actual Level</label></th>
			<th class="form2TH"><label>Sample Status</label></th>
		</tr>
		<tr>
			<td><label>Turbidity(NTU)</label></td>
			<td><input type="text" disabled="disabled" value="5.0"></td>
			<td><input type="text" disabled="disabled" value="10"></td>
			<td><html:text property="turbidityActual" styleClass="ci5"></html:text></td>
			<td><html:text property="turbidityResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Colour(Unit on Pt.-Co-Scale)</label></td>
			<td><input type="text" disabled="disabled" value=""></td>
			<td><input type="text" disabled="disabled" value=""></td>
			<td><html:text property="colourActual" styleClass="ci5"></html:text></td>
			<td><html:text property="colourResult" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Test and odour(Qualitative)</label></td>
			<td><input type="text" disabled="disabled" value=""></td>
			<td><input type="text" disabled="disabled" value=""></td>
			<td><html:text property="tasteOdourActual" styleClass="ci5"></html:text></td>
			<td><html:text property="tasteOdourResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>pH</label></td>
			<td><input type="text" disabled="disabled" value="6.5 to 8.5"></td>
			<td><input type="text" disabled="disabled" value="No Relaxation"></td>
			<td><html:text property="phActual" styleClass="ci5"></html:text></td>
			<td><html:text property="phResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Total Dissolved solids,ppm</label></td>
			<td><input type="text" disabled="disabled" value="500"></td>
			<td><input type="text" disabled="disabled" value="2000"></td>
			<td><html:text property="dissolvedActual" styleClass="ci5"></html:text></td>
			<td><html:text property="dissolvedResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Total Alkalinity(as Ca, Co3), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="200"></td>
			<td><input type="text" disabled="disabled" value="600"></td>
			<td><html:text property="alkalinityActual" styleClass="ci5"></html:text></td>
			<td><html:text property="alkalinityResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Total Hardness(as Ca, Co3), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="300"></td>
			<td><input type="text" disabled="disabled" value="600"></td>
			<td><html:text property="hardnessActual" styleClass="ci5"></html:text></td>
			<td><html:text property="hardnessResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Residual chlorine(as CI), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="0.2"></td>
			<td><input type="text" disabled="disabled" value="-"></td>
			<td><html:text property="residualActual" styleClass="ci5"></html:text></td>
			<td><html:text property="residualResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Fluorides(as F), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="1.0"></td>
			<td><input type="text" disabled="disabled" value="1.5"></td>
			<td><html:text property="fluoridesActual" styleClass="ci5"></html:text></td>
			<td><html:text property="fluoridesResult" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Nitrate(as NO3), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="45"></td>
			<td><input type="text" disabled="disabled" value="100"></td>
			<td><html:text property="nitrateActual" styleClass="ci5"></html:text></td>
			<td><html:text property="nitrateResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Sulphate(as SO4), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="200"></td>
			<td><input type="text" disabled="disabled" value="400"></td>
			<td><html:text property="sulphateActual" styleClass="ci5"></html:text></td>
			<td><html:text property="sulphateResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Iron(as Fe), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="0.3"></td>
			<td><input type="text" disabled="disabled" value="1.0"></td>
			<td><html:text property="ironActual" styleClass="ci5"></html:text></td>
			<td><html:text property="ironResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Chloride(Ch), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="250"></td>
			<td><input type="text" disabled="disabled" value="1000"></td>
			<td><html:text property="chlorideActual" styleClass="ci5"></html:text></td>
			<td><html:text property="chlorideResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Calcium(Ca), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="75"></td>
			<td><input type="text" disabled="disabled" value="200"></td>
			<td><html:text property="calciumActual" styleClass="ci5"></html:text></td>
			<td><html:text property="calciumResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><label>Uranium(Ur), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="30"></td>
			<td><input type="text" disabled="disabled" value="60"></td>
			<td><html:text property="uraniumActual" styleClass="ci5"></html:text></td>
			<td><html:text property="uraniumResult" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Selenium(Se), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="0.01"></td>
			<td><input type="text" disabled="disabled" value="No Relaxation"></td>
			<td><html:text property="seleniumActual" styleClass="ci5"></html:text></td>
			<td><html:text property="seleniumResult" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Cadmium(Cd), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="0.01"></td>
			<td><input type="text" disabled="disabled" value="No Relaxation"></td>
			<td><html:text property="cadmiumActual" styleClass="ci5"></html:text></td>
			<td><html:text property="cadmiumResult" styleClass="ci5"></html:text></td>
		</tr>
		<tr>
			<td><label>Arsenic(As), mg/l</label></td>
			<td><input type="text" disabled="disabled" value="0.05"></td>
			<td><input type="text" disabled="disabled" value="No Relaxation"></td>
			<td><html:text property="arsenicActual" styleClass="ci5"></html:text></td>
			<td><html:text property="arsenicResult" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><b><label>Others</label> </b></td>
		</tr>

		<tr>

			<td><html:text property="other1Name" styleClass="ci5"></html:text></td>
			<td><html:text property="other1Desirable" styleClass="ci5"></html:text></td>
			<td><html:text property="other1Permissible" styleClass="ci5"></html:text></td>
			<td><html:text property="other1Actual" styleClass="ci5"></html:text></td>
			<td><html:text property="other1Result" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><html:text property="other2Name" styleClass="ci5">
			</html:text></td>
			<td><html:text property="other2Desirable" styleClass="ci5">
			</html:text></td>
			<td><html:text property="other2Permissible" styleClass="ci5"></html:text></td>
			<td><html:text property="other2Actual" styleClass="ci5"></html:text></td>
			<td><html:text property="other2Result" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><html:text property="other3Name" styleClass="ci5">
			</html:text></td>
			<td><html:text property="other3Desirable" styleClass="ci5">
			</html:text></td>
			<td><html:text property="other3Permissible" styleClass="ci5"></html:text></td>
			<td><html:text property="other3Actual" styleClass="ci5"></html:text></td>
			<td><html:text property="other3Result" styleClass="ci5"></html:text></td>
		</tr>

		<tr>
			<td><html:text property="other4Name" styleClass="ci5">
			</html:text></td>
			<td><html:text property="other4Desirable" styleClass="ci5"></html:text></td>
			<td><html:text property="other4Permissible" styleClass="ci5"></html:text></td>
			<td><html:text property="other4Actual" styleClass="ci5"></html:text></td>
			<td><html:text property="other4Result" styleClass="ci5"></html:text></td>

		</tr>

	</table>
	</center>
	</fieldset>
	</div>
	<div id="bacteriological">
	<fieldset><legend>Bacteriological Parameters</legend>
	<center>
	<table class="form2" style="margin: 0 1em 1em 0;">
		<tr>
			<th class="form2TH"><label>Test</label></th>
			<th class="form2TH"><label>Desirable Limit</label></th>
			<th class="form2TH"><label>Permissible Limit</label></th>
			<th class="form2TH"><label>Actual Level</label></th>
			<th class="form2TH"><label>Sample Status</label></th>
		</tr>
		<tr>
			<td><label>Bacterriological examination</label></td>
		</tr>
		<tr>
			<td><html:text property="otherbact1Name" styleClass="ci5">
			</html:text></td>
			<td><html:text property="otherbact1Desirable" styleClass="ci5"></html:text></td>
			<td><html:text property="otherbact1Permissible" styleClass="ci5"></html:text></td>
			<td><html:text property="otherbact1Actual" styleClass="ci5"></html:text></td>
			<td><html:text property="otherbact1Result" styleClass="ci5"></html:text></td>

		</tr>

		<tr>
			<td><html:text property="otherbact2Name" styleClass="ci5">
			</html:text></td>
			<td><html:text property="otherbact2Desirable" styleClass="ci5"></html:text></td>
			<td><html:text property="otherbact2Permissible" styleClass="ci5"></html:text></td>
			<td><html:text property="otherbact2Actual" styleClass="ci5"></html:text></td>
			<td><html:text property="otherbact2Result" styleClass="ci5"></html:text></td>

		</tr>

	</table>
	</center>

	</fieldset>
	</div>
	<script language="javascript">
		triggerEvent(document.getElementById('locationId'), 'onchange');
		triggerEvent(document.getElementById('typeOfParameterId'), 'onchange');
		 <%if(MisUtility.ifEmpty(request.getAttribute("blockId"))){%>
			document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
			triggerEvent(document.getElementById('blockId'), 'onchange');
		<%}%>
		 <%if(MisUtility.ifEmpty(request.getAttribute("villageId"))){%>
			document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
		<%}%>
	</script>
</html:form>
</html:html>

