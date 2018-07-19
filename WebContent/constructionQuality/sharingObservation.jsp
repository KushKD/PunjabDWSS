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
		alert('inside')
		if (result) {
			document.shareObservationForm.action = "shareObservationAction.do?method=save&d__mode="
					+ d__mode + "&menuId=CQ012";
			document.shareObservationForm.submit();
		}
	}

	function de_find() {
		var scheme = document.getElementById('scheme').value;
		//var status = validateFields();
		if (scheme != '') {
			var html = "";
			html += "<table class='table table-hover table-bordered table-striped'  id='surveyTable'>";
			html += "<thead style='display: block;' class='thead-inverse'>";
			html += "<tr class='row'>";
			html += "<th class='col-lg-2 col-xs-2'>Sno.</th>";
			html += "<th class='col-lg-10 col-xs-10'>Remarks</th>";
			html += "</tr>"
			html += "</thead>";
			html += "<tbody style='display: block;'>";
			$.ajax({
				type : 'POST',
				url : "shareObservationAction.do?method=find&d__mode="
						+ d__mode + "&menuId=CQ004",
				data : {
					scheme : scheme
				},
				success : function(data) {
					var parsed = JSON.parse(data);
					for ( var i in parsed) {
						
						html += "<tr class='row'>";
						html += "<td class='col-lg-2 col-xs-2'>";
						html += " <span>" + (++i)
								+ "</span> ";
						html += "<td class='form-inline col-lg-10 col-xs-8'>";
						html += " <input type='text' name='remarkss' value=" + parsed[i] + " readonly class='form-control' style='width: 100%;'></input> </td>";
						html += "</tr>";
						i++;
					}
					html += "</tbody>";
					html += "</table>";
					document.getElementById('schemeComment').innerHTML = html;
				}

			})

			document.monthlyPlanInspectionForm.action = "shareObservationAction.do?method=save&d__mode="
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

<html:form action="shareObservationAction" method="post"
	styleId="shareObservationForm">

	<c:set var="employeeId">
		<bean:message bundle="construction" key="employee.id" />
	</c:set>
	<c:set var="employeeName">
		<bean:message bundle="construction" key="employee.name" />
	</c:set>
	<c:set var="designationId">
		<bean:message bundle="construction" key="designation.id" />
	</c:set>
	<c:set var="designationName">
		<bean:message bundle="construction" key="designation.name" />
	</c:set>

	<c:set var="schemeId">
		<bean:message bundle="construction" key="scheme.id" />
	</c:set>

	<c:set var="schemeName">
		<bean:message bundle="construction" key="scheme.name" />
	</c:set>

	<c:set var="stageId">
		<bean:message bundle="construction" key="stage.id" />
	</c:set>

	<c:set var="stageName">
		<bean:message bundle="construction" key="stage.name" />
	</c:set>

	<c:set var="dateOfInspection">
		<bean:message bundle="construction" key="date.of.inspection" />
	</c:set>

	<c:set var="checkedId">
		<bean:message bundle="construction" key="checked.id" />
	</c:set>

	<c:set var="checkedName">
		<bean:message bundle="construction" key="checked.name" />
	</c:set>

	<c:set var="remarks">
		<bean:message bundle="construction" key="remarks" />
	</c:set>


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
					onchange="
					if(this.value!=''){
						document.getElementById('yearlyPlanName').value=$('#yearPlan option:selected').text();
						}
					">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="month" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='month' styleId='month'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction(
						'shareObservationAction.do?monthId='+this.value+'&method=getSuperintendingEngineer',
						'to');ajaxFunction('shareObservationAction.do?yearlyPlanId='+document.getElementById('yearPlan').value+'&monthId='+this.value+'&method=fetchScheme','scheme')">
				</html:select>
			</div>


			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="form-inline col-lg-8 col-md-10 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="subject" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property='subject' styleId='subject'
					styleClass="form-control ci5" style="width: 500px;">
				</html:text>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="to" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='to' styleId='to'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction(
						'shareObservationAction.do?to='+this.value+'&method=fetchEmpDesignation',
						'designation');">
				</html:select>
			</div>
			<div class="form-inline col-lg-4 col-md-5 sm-hidden xs-hidden">&nbsp;
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
						bundle="construction" key="CC.to" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='designation' styleId='designation'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction(
						'shareObservationAction.do?designation='+this.value+'&method=getSuperintendingEngineer',
						'employee');">
				</html:select>
			</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<html:select property='employee' styleId='employee'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">

				<table onclick="changeRowColor1()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID"
									property="employeeDetailsGrid" selectionAllowed="true"
									multipleSelectionAllowed="true" model="datagrid">
									<layout:datagridColumn property="employeeId"
										title="${employeeId}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="employeeName"
										title="${employeeName}" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="designationId"
										title="${designationId}" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="designationName"
										title="${designationName}" mode="I,I,I"></layout:datagridColumn>

								</layout:datagrid>

							</div>
						</td>
						<td><button title='Add' type='button'
								class="btn btn-primary active" style="width: 50px;"
								onclick="addDataGrid()">
								<span class="glyphicon glyphicon-plus-sign"></span>
							</button> <br>
							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="StrutsLayout.setDatagridLineState('employeeDetailsGrid','removed');remove11();">
								<i class='fa fa-trash-o'></i>
							</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="panel panel-danger">
		<div class="panel-body">
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="scheme" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='scheme' styleId='scheme'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>

			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<button type="button" class="btn btn-primary" onclick='de_find()'
					style="width: 240px;">Populate</button>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="stage.of.scheme" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='schemeStage' styleId='schemeStage'
					styleClass="form-control" style="width: 150px;">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="checked.for" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='checkedFor' styleId='checkedFor'
					styleClass="form-control" style="width: 150px;">
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
						bundle="construction" key="date.of.inspection" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:text property='dateOfInspection' styleId='dateOfInspection'
					styleClass="form-control ci5" style="width: 150px;">
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

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<html:hidden property="employeeName" styleId="employeeName" />
			<html:hidden property="designationName" styleId="designationName" />
			<div class="col-lg-8 col-md-1 sm-hidden xs-hidden">

				<div class="table-responsive" id='schemeComment'></div>


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
			<div class=" form-inline col-lg-8 col-md-10 col-xs-12 col-sm-6">
				<label class="text-right labledesign"><bean:message
						bundle="construction" key="remarks" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:text property='remarks' styleId='remarks'
					styleClass="form-control ci5" style="width: 300px;">
				</html:text>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<html:hidden property="schemeName" styleId="schemeName" />
			<html:hidden property="checkedName" styleId="checkedName" />
			<html:hidden property="stageName" styleId="stageName" />

			<div class="col-lg-8 col-md-8 sm-hidden xs-hidden">
				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid">
								<layout:datagrid styleClass="DATAGRID" property="schemeGrid"
									selectionAllowed="true" multipleSelectionAllowed="false"
									model="datagrid">
									<layout:datagridColumn property="schemeId" title="${schemeId}"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="schemeName"
										title="${schemeName}" mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="stageId" title="${stageId}"
										mode="I,I,I"></layout:datagridColumn>
									<layout:datagridColumn property="stageName"
										title="${stageName}" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="inspectionDate"
										title="${dateOfInspection}" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="checkedId"
										title="${checkedId}" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="checkedName"
										title="${checkedName}" mode="I,I,I"></layout:datagridColumn>

									<layout:datagridColumn property="remarks" title="${remarks}"
										mode="I,I,I"></layout:datagridColumn>

								</layout:datagrid>

							</div>
						</td>
						<td><button title='Add' type='button'
								class="btn btn-primary active" style="width: 50px;"
								onclick="addDataGrid1()">
								<span class="glyphicon glyphicon-plus-sign"></span>
							</button> <br>

							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="StrutsLayout.setDatagridLineState('schemeGrid','removed');remove111();">
								<i class='fa fa-trash-o'></i>
							</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</html:form>

</body>
<script type="text/javascript">
	$('#dateOfInspection,#defaultInline').datepick();
	function changeRowColor() {
		$('#schemeGridJsId>tbody>tr').each(function() {
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

	function changeRowColor1() {
		$('#employeeDetailsGridJsId>tbody>tr').each(function() {
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

		var employeeName = $("#employee option:selected").text();
		document.getElementById('employeeName').value = employeeName;

		var designationName = $("#designation option:selected").text();
		document.getElementById('designationName').value = designationName;

		//var status = validateGridField();
		if (true) {
			StrutsLayout.addDatagridLine('employeeDetailsGrid',
					'employee~employeeName~designation~designationName');
		}
	}

	function addDataGrid1() {

		var stageName = $("#schemeStage option:selected").text();
		document.getElementById('stageName').value = stageName;

		var checkedName = $("#checkedFor option:selected").text();
		document.getElementById('checkedName').value = checkedName;

		var schemeName = $("#scheme option:selected").text();
		document.getElementById('schemeName').value = schemeName;

		//var status = validateGridField();
		if (true) {
			StrutsLayout
					.addDatagridLine(
							'schemeGrid',
							'scheme~schemeName~schemeStage~stageName~dateOfInspection~checkedFor~checkedName~remarks');
		}
	}

	$(document).ready(
			function() {
				ajaxFunction(
						'GetFilterValues.to?parentComboId=106&method=getCombo',
						'yearPlan');

				ajaxFunction(
						'GetFilterValues.to?parentComboId=122&method=getCombo',
						'month');

				ajaxFunction(
						'GetFilterValues.to?parentComboId=141&method=getCombo',
						'schemeStage');

				ajaxFunction(
						'GetFilterValues.to?parentComboId=144&method=getCombo',
						'checkedFor');

			});
	$('#finalizationDate,#defaultInline').datepick();
	function remove11() {
		$('#employeeDetailsGridJsId>tbody>tr').each(function() {
			var tableHdr = $(this).closest('tr').find('th');
			if (tableHdr.length < 1) {
				if ($(this).hasClass("DATAGRID_DEL")) {
					$(this).hide();
				}
			}
		});
	}
	
	function remove111() {
		$('#schemeGridJsId>tbody>tr').each(function() {
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
	
</script>
</html:html>