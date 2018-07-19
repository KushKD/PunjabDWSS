<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">

<link rel="stylesheet" type="text/css" href="css/common.css">
<!-- <link href='css/buttons.jqueryui.min.css' rel="stylesheet"
	type="text/css"> -->
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	hide_ctrl('modalPeriod', true);

	function de_add() {
		var result = validateField();
		if (result) {
			document.resultEntryForm.action = "resultEntryAction.do?method=save&d__mode="
					+ d__mode + "&menuId=WQ002";
			document.resultEntryForm.submit();

		}
	}

	function de_modify() {
		var result = validateField();
		if (result) {
			document.resultEntryForm.action = "resultEntryAction.do?method=update&d__mode="
					+ d__mode + "&menuId=WQ002";
			document.resultEntryForm.submit();

		}
	}
	function de_data() {
		var resultEntryId=document.getElementById('resultEntryId').value;
		if (d__mode == 'ent_modify') {
			document.resultEntryForm.action = "resultEntryAction.do?method=find&d__mode="+d__mode +"&menuId=WQ002&resultEntryId="+resultEntryId;
			document.resultEntryForm.submit();
		}else{
			document.resultEntryForm.action = "resultEntryAction.do?method=find&d__mode="+d__mode +"&menuId=WQ002";
			document.resultEntryForm.submit();
		}
	}
	
</script>
<style type="text/css">
body {
	background-color: #bdc3c7;
}

tbody {
	max-height: 300px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
	overflow-x: hidden; /* Hide the horizontal scroll */
}

.table>thead>tr>th, .table>thead>tr>td {
	font-size: .7em;
	border-bottom: 0;
	vertical-align: center;
	padding: 2px;
}
</style>
</head>
<html:html>
<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="waterQuality" style='display: none;'>
			<html:errors bundle="Waterquality" />
		</p>
		<script type="text/javascript">
		displayMessage(true);
			var para = document.getElementById('waterQuality');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML=text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>

<html:form action="resultEntryAction" method="post"
	styleId="resultEntryForm">
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">Test
				Results</h4>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<input type='hidden' name='resultEntryId' id='resultEntryId'>

			<input type='hidden' name='sampleId' id='sampleId'>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Lab Name</label>

				<html:select property="labname" styleId="labname"
					style="width: 150px;" styleClass="form-control" value=""
					onblur="if(d__mode == 'ent_modify'){
					ajaxFunction('resultEntryAction.do?labId='+this.value+'&d_mode=modify&method=fetchSamplePartNo', 'partno');
					}else{
					ajaxFunction('resultEntryAction.do?labId='+this.value+'&d_mode=add&method=fetchSamplePartNo', 'partno');
					}">
					<html:option value="">Select Lab</html:option>
					<html:options collection="labNames" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Sample PartNo.</label>
				<html:select property="partno" styleId="partno"
					style="width: 150px;" styleClass="form-control"
					onchange="de_data()">

				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			
			<html:hidden property="technician" styleId="technician"/>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">Completion Date</label>

				<html:text property='comDate' styleId='comDate'
					styleClass="form-control ci5" style="width:150px;"></html:text>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
		<%-- 	<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style='vertical-align: center'>District</label>
				<c:if test="${not empty districtName}">
					<%=request.getAttribute("districtName")%>
				</c:if>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style='vertical-align: center'>Block</label>
				<c:if test="${not empty blockName}">
					<%=request.getAttribute("blockName")%>
				</c:if>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style='vertical-align: center'>Village</label>
				<c:if test="${not empty vilageName}">
					<%=request.getAttribute("vilageName")%>
				</c:if>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style='vertical-align: center'>Scheme</label>
				<c:if test="${not empty schemeName}">
					<%=request.getAttribute("schemeName")%>
				</c:if>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign" style='vertical-align: center'>Water
					Source</label>
				<c:if test="${not empty waterSource}">
					<%=request.getAttribute("waterSource")%>
				</c:if>
			</div> --%>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
		</div>
	</div>
	<c:if test="${not empty parameterMasterBeans}">
		<div class="panel panel-danger" id="revalidate">
			<div class="panel-body">
				<div class=" form-inline col-lg-12 col-md-5 col-xs-12 col-sm-12">
					<div class="table-responsive">
						<table class="table table-bordered table-striped table-highlight"
							id="surveyTable" style='display: block;'>
							<thead style='display: block;' class='thead-dark'>
								<tr class="row">
									<th class='col-lg-4 col-xs-3'
										style='font-size: 0.9em; text-align: center'>Parameter</th>
									<th class='col-lg-2 col-xs-2'
										style='font-size: 0.9em; text-align: center'>Acceptable
										Limit</th>
									<th class='col-lg-2 col-xs-2'
										style='font-size: 0.9em; text-align: center'>Permissible
										Limit</th>
									<th class='col-lg-2 col-xs-2'
										style='font-size: 0.9em; text-align: center'>Unit</th>
									<th class='col-lg-2 col-xs-3'
										style='font-size: 0.9em; text-align: center'>Value</th>
								</tr>
							</thead>


							<tbody id='fnlTable' style='display: block;'>
								<logic:iterate name="parameterMasterBeans" id="parameterLst"
									property="parameterMasterBeans" indexId="rowindex"
									type="com.prwss.min.bean.ParameterMasterDto">
									<tr class="row">
										<td class='col-lg-4 col-xs-2'
											style='font-size: 0.9em; text-align: center'><bean:write
												name="parameterLst" property="parameterName" /></td>
										<td class='col-lg-2 col-xs-2'
											style='font-size: 0.9em; text-align: center'><bean:write
												name="parameterLst" property="acceptableLimit" /></td>
										<td class='col-lg-2 col-xs-2'
											style='font-size: 0.9em; text-align: center'><bean:write
												name="parameterLst" property="permissibleLimit" /></td>
										<td class='col-lg-2 col-xs-2'
											style='font-size: 0.9em; text-align: center'><bean:write
												name="parameterLst" property="uom" /></td>
										<td class='col-lg-2 col-xs-2'
											style='font-size: 0.9em; text-align: center'>
											<div class='form-inline'>
												<html:text name="parameterLst" property="parameterValue"
													style='width: 100%' styleClass='form-control input-sm'
													indexed="true" 	onkeypress="return validateKey1(event,	this,'9@10@3')"></html:text>
											</div> <html:hidden name="parameterLst" property="parameterId"
												indexed="true" />
												<html:hidden name="parameterLst" property="resultEntryDetailId"
												indexed="true" />
										</td>



									</tr>

								</logic:iterate>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="resultEntry"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					cellspacing="0" width="100%">

					<thead>
						<tr>
							<th></th>
							<th>Sample Part Number</th>
							<th>Completion Date</th>
							<th>Village</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<br>
</html:form>

<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/waterquality.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.flash.min.js"></script>
<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script>
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script type="text/javascript" src="js/buttons.colVis.min.js"></script>

</body>
<script type='text/javascript'>
function validateField(){
	var comDate=document.getElementById("comDate").value;
	if(comDate===''||comDate===null){
		alert("Please enter Completion date");
		return false;
	}
	return true;
}
	$('#startDate,#defaultInline').datepick();
	$('#endDate,#defaultInline').datepick();

	document.getElementById("datatable_sh").style.display = '';

	ResultEntryAction.ResultEntryAct();

	$('#comDate,#defaultInline').datepick();
	
</script>
<script type="text/javascript">
	
	<%if (MisUtility.ifEmpty(request.getAttribute("labId"))) {%>
	 document.getElementById('labname').value="<%=request.getAttribute("labId")%>";
	<%}%>
	triggerEvent(document.getElementById('labname'), 'onblur');
	<%if (MisUtility.ifEmpty(request.getAttribute("partno"))) {%>
  	document.getElementById('partno').value="<%=request.getAttribute("partno")%>";
<%}%>

<%if (MisUtility.ifEmpty(request.getAttribute("testDoneBy"))) {%>
	document.getElementById('technician').value="<%=request.getAttribute("testDoneBy")%>";
<%}%>

<%if (MisUtility.ifEmpty(request.getAttribute("completionDate"))) {%>
 document.getElementById('comDate').value="<%=request.getAttribute("completionDate")%>";
<%}%>

<%if (MisUtility.ifEmpty(request.getAttribute("sampleId"))) {%>
document.getElementById('sampleId').value="<%=request.getAttribute("sampleId")%>";
<%}%>

<%if (MisUtility.ifEmpty(request.getAttribute("resultEntryId1"))) {%>
document.getElementById('resultEntryId').value="<%=request.getAttribute("resultEntryId1")%>";
<%}%>

<%if (MisUtility.ifEmpty(request.getAttribute("testDoneBy"))) {%>
document.getElementById('technician').value="<%=request.getAttribute("testDoneBy")%>";
<%}%>


</script>
</html:html>