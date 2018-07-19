
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/messages.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">

<link href="css/datatables.min.css" rel="stylesheet" type="text/css" />


<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href='css/buttons.dataTables.min.css' rel="stylesheet"
	type="text/css">

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<link rel="stylesheet" type="text/css" href="css/dwss_datatable.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/finance.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/dataTables.select.min.js"></script>
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.jqueryui.min.js"></script>

<script type="text/javascript" src="js/buttons.flash.min.js"></script>
<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script>
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script>
<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script type="text/javascript" src="js/buttons.colVis.min.js"></script>




<script type="text/javascript">
	function de_find() {
		var status = validateFields();
		if (status) {
			document.divisionBudgetForm.action = "divisionBudgetViewAction.do?method=find&d__mode="
					+ d__mode + "&menuId=FN006";
			document.divisionBudgetForm.submit();
		}
	}
	function de_forward(){
		document.divisionBudgetForm.action = "divisionBudgetViewAction.do?method=forward&d__mode="
			+ d__mode + "&menuId=FN006";
		document.divisionBudgetForm.submit();
	}
</script>
<style type="text/css">
div.dataTables_wrapper {
	width: 1000px;
	margin: 0 auto;
}

th {
	background: #ffffff;
	color: black;
	font-weight: bold;
}
</style>
</head>
<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="finance" style='display: none;'>
			<html:errors bundle="finance" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('finance');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent> 


<html:form action="divisionBudgetViewAction" method="post"
	styleId="divisionBudgetForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Division Budget View</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="Division" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('divisionBudgetViewAction.do?divisionIds='+this.value+'&method=fetchFinancialYear', 'financialYear');">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="financial.year" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="financialYear" styleId="financialYear"
					style="width: 150px;" styleClass="cs2 form-control"
					>
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">
				<%-- <div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="budget.ref.no" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="budgetRefNo" styleId="budgetRefNo"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div> --%>

				<button type="button" class="btn btn-primary" onclick="de_find()"
					style="width: 150px;">Populate Record</button>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
				<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<c:if test="${not empty financeDtos}">
				<div class="col-lg-8 col-md-12 sm-hidden xs-hidden"
					style="overflow-x: auto;">
					<table class="table table-bordered table-primary"
						id="divisionBudgetTbl">

						<thead>
							<tr>
								<th scope="col"></th>
								<logic:iterate name="financeDtos" id="financeDto">
									<th scope="col"><bean:write name="financeDto"
											property="componentName" /></th>

								</logic:iterate>
								<th scope="col">Grand Total</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">Sub Totals</th>
								<logic:iterate name="financeDtos" id="financeDto">
									<td scope="col"><bean:write name="financeDto"
											property="reqNxtYear" /></td>
								</logic:iterate>
								<td scope="col"><%=request.getAttribute("granTotal")%></td>
							</tr>
						</tbody>

					</table>
				</div>
			</c:if>



		</div>
	</div>

	<div class="panel panel-danger" id="datagrid">
		<div class="panel-body">
			<div id="datatable_sh"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12"
				style="display: none">
				<table id="divisionBudgetView"
					class="display table-responsive table-bordered  table-hover nowrap"
					cellspacing="0" width="100%">
					<thead class='th1'>
						<tr>
							<th>Component</th>
							<th>Sub Component</th>
							<th>Activity</th>
							<th>Sub Activity</th>
							<th>Estimated Costs</th>
							<th>Total requirement for Next Year</th>
							<th>Already Spent till the end of year</th>
							<th>Qrt 1</th>
							<th>Qrt 2</th>
							<th>Qrt 3</th>
							<th>Qrt 4</th>

						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</html:form>

</body>
<script type="text/javascript">

	$(document).ready(
			function() {

				ajaxFunction(
						'divisionBudgetViewAction.do?method=fetchDivision',
						'division');
				
				<%if (MisUtility.ifEmpty(request.getAttribute("division"))) {%>
				document.getElementById('division').value="<%=request.getAttribute("division")%>";
				triggerEvent(document.getElementById("division"), 'onchange');
				<%}%>
				 <%if (MisUtility.ifEmpty(request.getAttribute("financialYear"))) {%>
						document.getElementById('financialYear').value="<%=request.getAttribute("financialYear")%>";
						triggerEvent(document.getElementById("financialYear"),
								'onchange');
						
<%}%>
Req = "ent_forward";
de_init('ent_forward', "test"); 

					});
	function validateFields() {
		if ($('#division').val() === "") {
			alert("Please Select Division");
			return false;
		}
		if ($('#financialYear').val() === "") {
			alert("Please Select Financial Year");
			return false;
		}

		return true;
	}
<%if (MisUtility.ifEmpty(request.getAttribute("division"))) {%>
	divisionBudgedView.divisionBudgedViews(<%=request.getAttribute("division")%>, <%=request.getAttribute("financialYear")%>);
			document.getElementById('datatable_sh').style.display = '';
<%}%>
 

</script>

</html:html>