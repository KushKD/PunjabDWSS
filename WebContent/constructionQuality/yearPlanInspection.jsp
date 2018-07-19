<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>

<script type="text/javascript">
	function de_add() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.yearlyPlanInspectionForm.action = "yearlyInspectionPlanAction.do?method=save&d__mode="
					+ d__mode + "&menuId=CQ001";
			document.yearlyPlanInspectionForm.submit();
		}
	}
</script>
<style>
tr th.DATAGRID {
	border-left: 2px solid #BDBDBD;
	width: 189px;
	top: 0;
}

.DATAGRID {
	border: .5px;
	color: #000000;
}
</style>
</head>
<html:html>

<logic:messagesPresent>
	<body bgcolor="#6699FF">
		<div id="modalContainer"></div>
		<p id="construction" style='display: none;'>
			<html:errors bundle="construction" />
		</p>
		<script type="text/javascript">
			displayMessage(true);
			var para = document.getElementById('construction');
			var text = para.firstChild.nodeValue;
			if (text != "") {
				document.getElementById("p1").innerHTML = text;
				$("#myModal").modal('show');
			}
		</script>
</logic:messagesPresent>
<c:set var="financialYear">
	<bean:message bundle="construction" key="financial.year" />
</c:set>
<c:set var="financialYear">
	<bean:message bundle="construction" key="financial.year" />
</c:set>
<c:set var="finalizationDate">
	<bean:message bundle="construction" key="date.of.finalization" />
</c:set>
<c:set var="component">
	<bean:message bundle="construction" key="component" />
</c:set>
<c:set var="totalVillage">
	<bean:message bundle="construction" key="total.number.villages" />
</c:set>


<c:set var="periodImplementation">
	<bean:message bundle="construction" key="period.for.implementation" />
</c:set>
<c:set var="visitToBeVillage">
	<bean:message bundle="construction" key="No.Villages.Visited" />
</c:set>
<c:set var="visitPerVillage">
	<bean:message bundle="construction" key="visit.per.village" />
</c:set>
<c:set var="totalVisit">
	<bean:message bundle="construction" key="total.visits" />
</c:set>
<c:set var="totalDuration">
	<bean:message bundle="construction" key="total.duration" />
</c:set>

<c:set var="visitedVillage">
	<bean:message bundle="construction" key="village.visited" />
</c:set>


<html:form action="yearlyInspectionPlanAction" method="post"
	styleId="yearlyPlanInspectionForm">



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Yearly Plan for Inspection</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${financialYear}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property='financialYear' styleId='financialYear'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="populateField()">
				</html:select>
			</div>


			<input type='hidden' name='financialYearName' id="financialYearName">
			<input type='hidden' name='componentName' id="componentName">


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign">${finalizationDate}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="finalizationDate" styleId="finalizationDate"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>

			</div>
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
						bundle="construction" key="plan.name" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property="planName" styleId="planName"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">&nbsp;</div>

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
						bundle="construction" key="inspection.type" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="inspectionType" styleId="inspectionType"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="assigned" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="assignedTo" styleId="assignedTo"
					style="width: 150px;" styleClass="cs2 form-control">
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

			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${component}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass=" form-control">
				</html:select>
			</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${totalVillage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="numberOfVillage" styleId="numberOfVillage"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="period.for.implementation" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="periodIml" styleId="periodIml"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${visitToBeVillage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="visitVillage" styleId="visitVillage"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${visitPerVillage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="visitPerVillage" styleId="visitPerVillage"
					style="width: 150px;" styleClass="cs2 form-control"
					onblur="getTotalVisit();">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${totalVisit}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="totalVisit" styleId="totalVisit"
					style="width: 150px;" styleClass="cs2 form-control" readonly="true">
				</html:text>
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${totalDuration}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="totalDuration" styleId="totalDuration"
					style="width: 150px;" styleClass="cs2 form-control"
					onblur="getTotalMonthVisit()">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign">${visitedVillage}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="perMonthVisit" styleId="perMonthVisit"
					style="width: 150px;" styleClass="cs2 form-control" readonly="true">
				</html:text>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-5 col-xs-12 col-sm-12">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="comments" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:textarea property="comments" styleId="comments"
					style="width: 400px;" styleClass="cs2 form-control">
				</html:textarea>
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

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<button type="button" class="btn btn-primary" onclick='addDataGrid()'
				style="width: 200px;">Add Detail</button>
		</div>
	</div>
	<div class="panel panel-danger">
		<div class="panel-body">

			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="yearlyPlanGrid"
									selectionAllowed="true" multipleSelectionAllowed="true"
									model="datagrid">
									<layout:datagridColumn property="financialYear"
										title="${financialYear}" mode="N,N,N"></layout:datagridColumn>
									<layout:datagridColumn property="financialYearName"
										title="${financialYear}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="component"
										title="${component}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="numberOfVillage"
										title="${totalVillage}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="finalizationDate"
										title="${finalizationDate}"></layout:datagridColumn>
									<layout:datagridColumn property="periodIml"
										title="${periodImplementation}" mode="I,I,I" />

									<layout:datagridColumn property="visitVillage"
										title="${visitToBeVillage}" mode="I,I,I" />

									<layout:datagridColumn property="visitPerVillage"
										title="${visitPerVillage}" mode="I,I,I" />

									<layout:datagridColumn property="totalVisit"
										title="${totalVisit}" mode="I,I,I" />

									<layout:datagridColumn property="totalDuration"
										title="${totalDuration}" mode="I,I,I" />

									<layout:datagridColumn property="perMonthVisit"
										title="${visitedVillage}" mode="I,I,I" />


								</layout:datagrid>

							</div>
						</td>
						<td>
							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="StrutsLayout.setDatagridLineState('yearlyPlanGrid','removed');remove11();">
								<i class='fa fa-trash-o'></i>
							</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</html:form>

</body>
<script type="text/javascript">
	function changeRowColor() {

		$('#yearlyPlanGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_SEL")) {
					//$('table tr').css('color','#c51717');
					$(this).css('color', '#c51717');
				} else {
					//$('table tr').css('color','#000000');
					$(this).css('color', '#000000');

				}
			}

		});

	}

	function addDataGrid() {

		var componentName = $("#component option:selected").text();
		var component = document.getElementById('component').value;
		var componentNameId = componentName + "(" + component + ")";
		document.getElementById('componentName').value = componentNameId;

		var status = validateGridField();
		//var status = true;
		if (status) {
			StrutsLayout
					.addDatagridLine(
							'yearlyPlanGrid',
							'financialYearName~componentName~numberOfVillage~finalizationDate~periodIml~visitVillage~visitPerVillage~totalVisit~totalDuration~perMonthVisit');
		}
	}

	$(document)
			.ready(
					function() {
						ajaxFunction(
								'GetFilterValues.to?parentComboId=106&method=getCombo',
								'financialYear');
						ajaxFunction(
								'GetFilterValues.to?parentComboId=112&method=getCombo',
								'inspectionType');
						ajaxFunction(
								'GetFilterValues.to?parentComboId=115&method=getCombo',
								'component');
						ajaxFunction(
								'yearlyInspectionPlanAction.do?method=fetchExternalAgency',
								'assignedTo');

					});
	$('#finalizationDate,#defaultInline').datepick();
	function remove11() {
		$('#yearlyPlanGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					$(this).hide();
				}
			}
		});
	}
</script>
<script type="text/javascript">
	function populateField() {
		document.getElementById("financialYearName").value = $(
				"#financialYear option:selected").text();
	}

	function getTotalVisit() {

		if (document.getElementById("visitPerVillage").value != ""
				&& document.getElementById("visitPerVillage").value != null) {
			document.getElementById("totalVisit").value = parseInt(document
					.getElementById("visitVillage").value)
					* parseInt(document.getElementById("visitPerVillage").value);
		}
	}
	function getTotalMonthVisit() {
		if (document.getElementById("totalDuration").value != ""
				&& document.getElementById("totalDuration").value != null) {
			var result = parseInt(document.getElementById("totalVisit").value)
					/ parseInt(document.getElementById("totalDuration").value);
	
			document.getElementById("perMonthVisit").value = parseInt(result);
					
			
		}
	}

	function validateGridField() {
		var financialYear = document.getElementById("financialYear").value;
		var component = document.getElementById('component').value;
		var finalizationDate = document.getElementById('finalizationDate').value;
		var numberOfVillage = document.getElementById('numberOfVillage').value;

		if (financialYear === '' || financialYear === null) {
			alert('Please Select Financial Year');
			return false;
		} else if (component === '' || component === null) {
			alert('Please Select Component');
			return false;
		} else if (finalizationDate === '' || finalizationDate === null) {
			alert('Please enter Finalization Date');
			return false;
		} else if (numberOfVillage === '' || numberOfVillage === null) {
			alert('Please enter No of Villages');
			return false;
		}
		return true;

	}
	/* 
	function regex(e11){
		var regex1=/^[0-9]+\.?[0-9]*$/;
		alert(e11);
		alert(regex1.match(e11))
		return e11.match(regex1);
	} */
</script>
</html:html>