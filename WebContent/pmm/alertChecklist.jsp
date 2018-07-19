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
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		function de_add(){
			if(d__mode=='ent_add') {
				var result = true;//valid.validate();
				if(result){
					document.alertChecklistForm.action="alertChecklistAction.do?method=save&d__mode="+d__mode+"&menuId=PMM014";
					document.alertChecklistForm.submit();
				}
			}
		}		    
	</script>
</head>


<html:html>
<body bgcolor="#6699FF">


<html:form action="alertChecklistAction" method="post"
	styleId="alertChecklistForm">
	<fieldset><legend>Check List</legend>
	<center>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				styleClass="cs3" style="width:585"
				onchange="ajaxPopulate('alertChecklistAction.do?method=getStatus&locationId='+this.value+'&menuId=PMM014');
			readOnly_ctrl('tMonth~tYear',true)">
				<html:option value="">Select</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
		</tr>
		<tr>
			<td><label>Month</label></td>
			<td><html:text property="tMonth" style="width:200"
				styleId="tMonth" styleClass="ci5" readonly="readonly"></html:text></td>
		</tr>
		<tr>
			<td><label>Year</label></td>
			<td><html:text property="tYear" styleId="tYear"
				style="width:200" styleClass="ci5" readonly="readonly"></html:text></td>
		</tr>
	</table>
	<table class="form2" style="margin: 0 1em 1em 0;">
		<tr>
			<th class="form2TH"><label>Sr. No.</label></th>
			<th class="form2TH"><label>Detail</label></th>
			<th class="form2TH"><label>Status</label></th>
		</tr>
		<tr class="form2TR">
			<td><label>1.</label></td>
			<td>Is Admin Approval of all new Schemes are filled?</td>
			<td><html:radio property="adminApproval"
				styleId="adminApproval_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="adminApproval" styleId="adminApproval_false"
				value="false" title="Not Yet">Not Yet</html:radio> <html:radio
				property="adminApproval" styleId="adminApproval_notDue" value="true"
				title="Not Due">Not Due</html:radio></td>
		</tr>
		<tr class="form2TR">
			<td><label>2.</label></td>
			<td>Is Scheme Commissioning of all commissioned Schemes are
			filled?</td>
			<td><html:radio property="schemeCommissioning"
				styleId="schemeCommissioning_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="schemeCommissioning"
				styleId="schemeCommissioning_false" value="false" title="Not Yet">Not Yet</html:radio>
			<html:radio property="schemeCommissioning"
				styleId="schemeCommissioning_notDue" value="true" title="Not Due">Not Due</html:radio>
			</td>
		</tr>
		<tr class="form2TR">
			<td><label>3.</label></td>
			<td>Is Water Connections of habitations are filled?</td>
			<td><html:radio property="waterConnection"
				styleId="waterConnection_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="waterConnection"
				styleId="waterConnection_false" value="false" title="Not Yet">Not Yet</html:radio>
			<html:radio property="waterConnection"
				styleId="waterConnection_notDue" value="true" title="Not Due">Not Due</html:radio>
			</td>
		</tr>
		<tr class="form2TR">
			<td><label>4.</label></td>
			<td>Is Household data of habitations are filled?</td>
			<td><html:radio property="household" styleId="household_true"
				value="true" title="Completed">Completed</html:radio> <html:radio
				property="household" styleId="household_false" value="false"
				title="Not Yet">Not Yet</html:radio> <html:radio
				property="household" styleId="household_notDue" value="true"
				title="Not Due">Not Due</html:radio></td>
		</tr>
		<tr class="form2TR">
			<td><label>5.</label></td>
			<td>Is Operation Sustainability data of habitations are filled?</td>
			<td><html:radio property="operationSustainability"
				styleId="operationSustainability_true" value="true"
				title="Completed">Completed</html:radio> <html:radio
				property="operationSustainability"
				styleId="operationSustainability_false" value="false"
				title="Not Yet">Not Yet</html:radio> <html:radio
				property="operationSustainability"
				styleId="operationSustainability_notDue" value="true"
				title="Not Due">Not Due</html:radio></td>
		</tr>
		<tr class="form2TR">
			<td><label>6.</label></td>
			<td>Is CB and IEC training are filled?</td>
			<td><html:radio property="iecTraining"
				styleId="iecTraining_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="iecTraining" styleId="iecTraining_false"
				value="false" title="Not Yet">Not Yet</html:radio> <html:radio
				property="iecTraining" styleId="iecTraining_notDue" value="true"
				title="Not Due">Not Due</html:radio></td>
		</tr>
		<tr class="form2TR">
			<td><label>7.</label></td>
			<td>Is Beneficiary Share are filled?</td>
			<td><html:radio property="beneficiayShare"
				styleId="beneficiayShare_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="beneficiayShare"
				styleId="beneficiayShare_false" value="false" title="Not Yet">Not Yet</html:radio>
			<html:radio property="beneficiayShare"
				styleId="beneficiayShare_notDue" value="true" title="Not Due">Not Due</html:radio>
			</td>
		</tr>
		<tr class="form2TR">
			<td><label>8.</label></td>
			<td>Is SPMC Payment Voucher are filled?</td>
			<td><html:radio property="spmcPaymentVoucher"
				styleId="spmcPaymentVoucher_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="spmcPaymentVoucher"
				styleId="spmcPaymentVoucher_false" value="false" title="Not Yet">Not Yet</html:radio>
			<html:radio property="spmcPaymentVoucher"
				styleId="spmcPaymentVoucher_notDue" value="true" title="Not Due">Not Due</html:radio>
			</td>
		</tr>
		<tr class="form2TR">
			<td><label>9.</label></td>
			<td>Is DPMC Payment Voucher are filled?</td>
			<td><html:radio property="dpmcPaymentVoucher"
				styleId="dpmcPaymentVoucher_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="dpmcPaymentVoucher"
				styleId="dpmcPaymentVoucher_false" value="false" title="Not Yet">Not Yet</html:radio>
			<html:radio property="dpmcPaymentVoucher"
				styleId="dpmcPaymentVoucher_notDue" value="true" title="Not Due">Not Due</html:radio>
			</td>
		</tr>
		<tr class="form2TR">
			<td><label>10.</label></td>
			<td>Is GPWSC register entry are filled?</td>
			<td><html:radio property="gpwscRegisterEntry"
				styleId="gpwscRegisterEntry_true" value="true" title="Completed">Completed</html:radio>
			<html:radio property="gpwscRegisterEntry"
				styleId="gpwscRegisterEntry_false" value="false" title="Not Yet">Not Yet</html:radio>
			<html:radio property="gpwscRegisterEntry"
				styleId="gpwscRegisterEntry_notDue" value="true" title="Not Due">Not Due</html:radio>
			</td>
		</tr>
		<tr class="form2TR">
			<td><label>11.</label></td>
			<td>Is Updation of Procurement plan are filled?</td>
			<td><html:radio property="updationProcurementPlan"
				styleId="updationProcurementPlan_true" value="true"
				title="Completed">Completed</html:radio> <html:radio
				property="updationProcurementPlan"
				styleId="updationProcurementPlan_false" value="false"
				title="Not Yet">Not Yet</html:radio> <html:radio
				property="updationProcurementPlan"
				styleId="updationProcurementPlan_notDue" value="true"
				title="Not Due">Not Due</html:radio></td>
		</tr>

	</table>
	</center>
	</fieldset>
</html:form>
</html:html>