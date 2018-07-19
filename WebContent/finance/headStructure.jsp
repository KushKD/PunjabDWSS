<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>


<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
	function de_add() {
		var result = validateForm()

		if (result) {
			document.financeHeadsStructureForm.action = "financialHeadStructureAction.do?method=save&d__mode="
					+ d__mode + "&menuId=FN002";
			document.financeHeadsStructureForm.submit();
		}
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			var result = validateForm()

			if (result) {
				document.financeHeadsStructureForm.action = "financialHeadStructureAction.do?method=update&d__mode="
						+ d__mode + "&menuId=FN002";
				document.financeHeadsStructureForm.submit();
			}
		}
	}
</script>



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

<html:form action="financialHeadStructureAction" method="post"
	styleId="financeHeadsStructureForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Head Structure</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="description" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="description" styleId="description"
					style="width: 150px;" styleClass="cs2 form-control">

				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="financial.year" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="financialYear" styleId="financialYear"
					style="width: 150px;" styleClass="cs2 form-control">

				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="demand.no" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="dimandNo" styleId="dimandNo"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="	ajaxFunction(
						'financialHeadStructureAction.do?headType='+this.value+'&method=fetchHeadType',
						'majorHead');">

				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="major.head" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="majorHead" styleId="majorHead"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction(
						'financialHeadStructureAction.do?headType='+this.value+'&method=fetchHeadType',
						'subMajorHead');">

				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="sub.major.head" /> <span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="subMajorHead" styleId="subMajorHead"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction(
						'financialHeadStructureAction.do?headType='+this.value+'&method=fetchHeadType',
						'minorHead');">

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="minor.head" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="minorHead" styleId="minorHead"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction(
						'financialHeadStructureAction.do?headType='+this.value+'&method=fetchHeadType',
						'subHead');">
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
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="sub.head" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="subHead" styleId="subHead"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction(
						'financialHeadStructureAction.do?headType='+this.value+'&method=fetchHeadType',
						'detailedHead');">

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="detailed.head" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="detailedHead" styleId="detailedHead"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction(
						'financialHeadStructureAction.do?headType='+this.value+'&method=fetchHeadType',
						'SOE');">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="SOE" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="SOE" styleId="SOE" style="width: 150px;"
					styleClass="cs2 form-control">

				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="plan.non.plan" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="planNonPlan" styleId="planNonPlan"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="finance" key="voted.charged" /> <span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="votedCharged" styleId="votedCharged"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<html:hidden property="headStructureId" styleId="headStructureId"/>
		</div>
	</div>

	<!-- Table -->

	<div class="panel panel-danger">
		<div class="panel-body">
			<div id="datatable_sh" style="display: none"
				class="col-lg-12 col-sm-12 col-xs-12 col-md-12 panel-body">
				<table id="financeHeadDT"
					class="display table-responsive table-bordered table-striped table-hover nowrap"
					style="width: 100%">
					<thead>
						<tr>
							<th></th>
							<th>Description</th>
							<th>Financial Year</th>
							<th>HOA</th>

							<th>financial year</th>
							<th>Demand number</th>
							<th>Major Head</th>
							<th>Sub Major Head</th>
							<th>Minor Head</th>
							<th>Sub Head</th>
							<th>Detailed Head</th>
							<th>SOE</th>
							<th>Plan/Non Plan</th>
							<th>Voted/Charged</th>
							<th>head structure id</th>
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
<script type='text/javascript'>
	$(document)
			.ready(
					function() {

						ajaxFunction(
								'GetFilterValues.to?parentComboId=106&method=getCombo',
								'financialYear');
						ajaxFunction(
								'financialHeadStructureAction.do?headType=178&method=fetchDiamond',
								'dimandNo');

						ajaxFunction(
								'GetFilterValues.to?parentComboId=185&method=getCombo',
								'planNonPlan');

						ajaxFunction(
								'GetFilterValues.to?parentComboId=188&method=getCombo',
								'votedCharged');

						document.getElementById("datatable_sh").style.display = '';

						financeHeadStr.financeHeadStructure();

					});

	function validateForm() {
		var description = document.getElementById('description').value;
		var financialYear = document.getElementById('financialYear').value;
		var dimandNo = document.getElementById('dimandNo').value;
		var majorHead = document.getElementById('majorHead').value;
		var subMajorHead = document.getElementById('subMajorHead').value;
		var minorHead = document.getElementById('minorHead').value;
		var subHead = document.getElementById('subHead').value;
		var detailedHead = document.getElementById('detailedHead').value;
		var SOE = document.getElementById('SOE').value;
		var planNonPlan = document.getElementById('planNonPlan').value;
		var votedCharged = document.getElementById('votedCharged').value;

		if (description === '') {
			alert("Please Enter Description");
			return false;
		}
		if (financialYear === '') {
			alert("Please select Financial Year");
			return false;
		}
		if (dimandNo === '') {
			alert("Please select Demand No");
			return false;
		}
		if (majorHead === '') {
			alert("Please select Major Head");
			return false;
		}
		if (subMajorHead === '') {
			alert("Please select Sub Major Head");
			return false;
		}
		if (minorHead === '') {
			alert("Please select Minor Head");
			return false;
		}
		if (subHead === '') {
			alert("Please select Sub Head");
			return false;
		}
		if (detailedHead === '') {
			alert("Please select Detailed Head");
			return false;
		}
		if (SOE === '') {
			alert("Please select SOE");
			return false;
		}
		if (planNonPlan === '') {
			alert("Please select Plan-NonPlan");
			return false;
		}
		if (votedCharged === '') {
			alert("Please Select Voted-Charged");
			return false;
		}
		return true;
	}
</script>

</html:html>