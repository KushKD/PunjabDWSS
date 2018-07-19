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
			document.monthlyPlanInspectionForm.action = "monthlyPlanInspectionAction.do?method=save&d__mode="
					+ d__mode + "&menuId=CQ004";
			document.monthlyPlanInspectionForm.submit();
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

<html:form action="monthlyPlanInspectionAction" method="post"
	styleId="monthlyPlanInspectionForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Monthly Plan for Inspection</h4>
			<div class="line"></div>
	
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="yearly.plan" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='yearPlan' styleId='yearPlan'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction('monthlyPlanInspectionAction.do?financialYearlyId='+this.value+'&method=fetchTeam','team');
					if(this.value!=''){
						document.getElementById('yearlyPlanName').value=$('#yearPlan option:selected').text();
						}
					">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="component" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('monthlyPlanInspectionAction.do?yearlyPlanId='+document.getElementById('yearPlan').value+'&componentId='+document.getElementById('component').value+'&method=fetchVisitedVillagePerMonth', 'visitPerMonth');">
				</html:select>
			</div>
			<html:hidden property="yearlyPlanName" styleId="yearlyPlanName"/>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="month" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="month" styleId="month" style="width: 150px;"
					styleClass="cs2 form-control">
				</html:select>

			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="village.visited" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="visitPerMonth" styleId="visitPerMonth"
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
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="villages.visit.this.month" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property="currentMonthVisit" styleId="currentMonthVisit"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:text>
			</div>
			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
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
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="team" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="team" styleId="team" style="width: 150px;"
					styleClass=" form-control">
				</html:select>
			</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="district" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="district" styleId="district"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?division='+this.value+'&method=fetchDivision', 'division');ajaxFunction('GetFilterValues.to?constituency='+this.value+'&method=fetchConstituency', 'constituency')">
					<html:option value="">Select Location</html:option>
					<html:options collection="districtLocations" labelProperty="label"
						property="value"></html:options>
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="division" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="division" styleId="division"
					style="width: 150px;" styleClass="cs2 form-control"
					onchange="ajaxFunction('GetFilterValues.to?divisionIds='+this.value+'&method=fetchSchemeByDivisionId', 'scheme');">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="scheme" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="scheme" styleId="scheme"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="constituency" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="constituency" styleId="constituency"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>

			<html:hidden property="districtName" styleId="districtName" />
			<html:hidden property="divisionName" styleId="divisionName" />
			<html:hidden property="schemeName" styleId="schemeName" />
			<html:hidden property="constituencyName" styleId="constituencyName" />

			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
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
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
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
								<layout:datagrid styleClass="DATAGRID"
									property="monthlyPlanGrid" selectionAllowed="true"
									multipleSelectionAllowed="false" model="datagrid">
									<layout:datagridColumn property="district" title="District Id"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="districtName"
										title="District Name" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="division" title="Division Id"
										mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="divisionName"
										title="Division Name" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="scheme" title="Scheme Id"
										mode="I,I,I"></layout:datagridColumn>
										
									<layout:datagridColumn property="schemeName"
										title="Scheme Name" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="constituency"
										title="Constituency" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="constituencyName"
										title="Constituency Name" mode="I,I,I"></layout:datagridColumn>

								</layout:datagrid>

							</div>
						</td>
						<td>
							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="StrutsLayout.setDatagridLineState('monthlyPlanGrid','removed');remove11();">
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
	$('#monthlyPlanGridJsId>tbody>tr').each(function() {
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

		var districtName = $("#district option:selected").text();
		document.getElementById('districtName').value = districtName;

		var divisionName1 = $("#division option:selected").text();
		document.getElementById('divisionName').value = divisionName1;

		var schemeName = $("#scheme option:selected").text();
		document.getElementById('schemeName').value = schemeName;

		var constituencyName = $("#constituency option:selected").text();
		document.getElementById('constituencyName').value = constituencyName;

		var status = validateGridField();
		if (status) {
			StrutsLayout.addDatagridLine('monthlyPlanGrid',
					'district~districtName~division~divisionName~scheme~schemeName~constituency~constituencyName');
		}
	}

	$(document).ready(
			function() {
				ajaxFunction(
						'GetFilterValues.to?parentComboId=106&method=getCombo',
						'yearPlan');
				ajaxFunction(
						'GetFilterValues.to?parentComboId=115&method=getCombo',
						'component');
				ajaxFunction(
						'GetFilterValues.to?parentComboId=122&method=getCombo',
						'month');
			});
	$('#finalizationDate,#defaultInline').datepick();
	function remove11() {
		$('#monthlyPlanGridJsId>tbody>tr').each(function() {
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


	function validateGridField() {
		var district = document.getElementById("district").value;
		var division = document.getElementById('division').value;
		var scheme = document.getElementById('scheme').value;
		var constituency = document.getElementById('constituency').value;

		if (district === '' || district === null) {
			alert('Please Select District');
			return false;
		} else if (division === '' || division === null) {
			alert('Please Select Division');
			return false;
		} else if (scheme === '' || scheme === null) {
			alert('Please Select Scheme');
			return false;
		} else if (constituency === '' || constituency === null) {
			alert('Please Select Constituency');
			return false;
		}
		return true;

	}
</script>
</html:html>