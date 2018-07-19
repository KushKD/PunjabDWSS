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
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript">

	function de_add() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.saveMonthlyReportForm.action = "saveMonthlyReportAction.do?method=save&d__mode="
					+ d__mode + "&menuId=CQ008";
			document.saveMonthlyReportForm.submit();
		}
	}

	function de_find() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.saveMonthlyReportForm.action = "saveMonthlyReportAction.do?method=find&d__mode="
					+ d__mode + "&menuId=CQ008";
			document.saveMonthlyReportForm.submit();
		} 
	}
	
</script>
<style>
#monthlyProgressTbl {
	max-height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
}

#monthlyProgressTbl {
	display: block;
}
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

<html:form action="saveMonthlyReportAction" method="post"
	styleId="saveMonthlyReportForm">

	<c:set var="observation">
		<bean:message bundle="construction" key="observation" />
	</c:set>
<c:set var="phaseId">
<bean:message bundle="construction" key="phase.id"/>
</c:set>
<c:set var="phaseName">
<bean:message bundle="construction" key="phase.name"/>
</c:set>
<c:set var="schemeId">
<bean:message bundle="construction" key="scheme.id"/>
</c:set>
<c:set var="schemeName">
<bean:message bundle="construction" key="scheme.name"/>
</c:set>
	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Saving Monthly Report</h4>
			<div class="line"></div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="yearly.plan" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='yearPlan' styleId='yearPlan'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="month" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="month" styleId="month" style="width: 150px;"
					styleClass="cs2 form-control"
					onchange="ajaxFunction('saveMonthlyReportAction.do?yearlyPlanId='+document.getElementById('yearPlan').value+'&monthId='+this.value+'&method=fetchScheme','scheme')">
				</html:select>

			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<!-- ------------------------- -->

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" style="width: 150px;"
					onclick="de_find()">Populate</button>
			</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">
				<div id="monthlyProgressTbl1">

					<c:if test="${not empty monthlyProgressDtos}">
						<div class="panel panel-danger" id="revalidate">
							<div class="panel-body">
								<div class=" form-inline col-lg-1 col-md-1 col-xs-12 col-sm-12">&nbsp;&nbsp;</div>

								<div class=" form-inline col-lg-9 col-md-5 col-xs-12 col-sm-12">
									<div class="table-responsive">
										<table
											class="table table-bordered table-striped table-highlight"
											id="monthlyPlanProgress">
											<thead style='display: block;' class='thead-dark'>
												<tr class="row">

													<th class='col-lg-2 col-xs-2' style='text-align: center;'>Component
														Name</th>
													<th class='col-lg-2 col-xs-2' style='text-align: center;'>No.
														Of Villages to be Visited</th>
													<th class='col-lg-2 col-xs-2' style='text-align: center;'>Team
														Name</th>
													<th class='col-lg-2 col-xs-1' style='text-align: center;'>Planning</th>
													<th class='col-lg-2 col-xs-1' style='text-align: center;'>Implementation</th>
													<th class='col-lg-2 col-xs-2' style='text-align: center;'>Post
														Implementation</th>
												</tr>
											</thead>


											<tbody id="monthlyProgressTbl">
												<%
													int i = 1;
												%>
												<logic:iterate name="monthlyProgressDtos"
													id="monthlyPlanLst" property="monthlyProgressDtos"
													indexId="rowindex"
													type="com.prwss.min.construction.quality.bean.MonthlyProgressDto">
													<tr class="row">
														<td class='col-lg-2 col-xs-3' style='text-align: center'>
															<html:text name="monthlyPlanLst" property="componentName"
																indexed="true" style="width: 182px;text-align:center"
																styleClass=" form-control" readonly="true">
															</html:text>
														</td>

														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="villageToBeVisited"
																indexed="true" styleClass="form-control"
																style="width:182px" readonly="true">
															</html:text></td>

														<td class='col-lg-2 col-xs-3'
															style='font-size: 0.9em; text-align: center'><html:text
																name="monthlyPlanLst" property="teamName"
																styleClass="cs2 form-control" style="width:185px"
																indexed="true" readonly="true">
															</html:text></td>
														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="planning" indexed="true"
																styleClass="cs2 form-control" style="width:182px"
																readonly="true">
															</html:text></td>
														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="implementation"
																indexed="true" styleClass="cs2 form-control"
																style="width:182px" readonly="true">
															</html:text></td>
														<td class='col-lg-2 col-xs-1'><html:text
																name="monthlyPlanLst" property="postimplementaion"
																indexed="true" styleClass="cs2 form-control"
																style="width:180px" readonly="true">
															</html:text></td>

													</tr>
													<%
														i++;
													%>
												</logic:iterate>
											</tbody>

										</table>
									</div>
								</div>
								<div class=" form-inline col-lg-1 col-md-1 col-xs-12 col-sm-12">&nbsp;&nbsp;</div>
							</div>
						</div>

					</c:if>
				</div>
			</div>

		</div>
	</div>



	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Observations</h4>
			<div class="col-lg-2 col-md-2 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>


			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="phase" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='commentType' styleId='commentType'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="scheme" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property='scheme' styleId='scheme'
					styleClass="form-control ci5" style="width: 150px;">
				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-8 col-md-8 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="comment" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:textarea property='observation' styleId='observation'
					styleClass="form-control ci5" style="width: 300px;">
				</html:textarea>
			</div>
			<html:hidden property="phaseName" styleId="phaseName"/>
			<html:hidden property="schemeName" styleId="schemeName"/>
			<html:hidden property="monthlyPlanId" styleId="monthlyPlanId"/>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<!-- <button type="button" class="btn btn-primary" onclick='addDataGrid()'
				id='addDetails' style="width: 200px;">Add Observation</button> -->
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div><div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">

				<table onclick="changeRowColor()">
					<tr>
						<td>
							<div class="divgrid"
								>
								<layout:datagrid styleClass="DATAGRID"
									property="saveMonthlyReportGrid" selectionAllowed="true"
									multipleSelectionAllowed="true" model="datagrid">

									
											<layout:datagridColumn property="phaseId"
										title="${phaseId}" mode="I,I,I"></layout:datagridColumn>
											<layout:datagridColumn property="phaseName"
										title="${phaseName}" mode="I,I,I"></layout:datagridColumn>
											<layout:datagridColumn property="schemeId"
										title="${schemeId}" mode="I,I,I"></layout:datagridColumn>
											<layout:datagridColumn property="schemeName"
										title="${schemeName}" mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="observation"
										title="${observation}" mode="I,I,I"></layout:datagridColumn>
										
								</layout:datagrid>
								
								

							</div>
						</td>
						<td>
							<button title='Add' type='button' class="btn btn-primary active"
								style="width: 50px;" onclick="addDataGrid()">
								<span class="glyphicon glyphicon-plus-sign"></span>
							</button> <br>

							<button title='Remove' type='button'
								class='btn btn-danger remove show_tip' id='removeRow'
								style="width: 50px;"
								onclick="StrutsLayout.setDatagridLineState('saveMonthlyReportGrid','removed');remove11();">
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
		$('#saveMonthlyReportGridJsId>tbody>tr').each(function() {
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
		var commentType = $("#commentType option:selected").text();
		document.getElementById('phaseName').value = commentType;
		
		var scheme = $("#scheme option:selected").text();
		document.getElementById('schemeName').value=scheme;
		
		var status = validateGridField();
		
		if (status) {
			StrutsLayout
					.addDatagridLine(
							'saveMonthlyReportGrid',
							'commentType~phaseName~scheme~schemeName~observation');
		}
	}
	
	function validateGridField() {
		var observation = document.getElementById("observation").value;
		var commentType = document.getElementById("commentType").value;
		var scheme = document.getElementById("scheme").value;
	
		if (observation === '' || observation === null) {
			alert('Please Enter Observation');
			return false;
		} if (commentType === '' || commentType === null) {
			alert('Please Select Phase');
			return false;
		} if (scheme === '' || scheme === null) {
			alert('Please Select Scheme');
			return false;
		} 	
		return true;

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
						'GetFilterValues.to?parentComboId=136&method=getCombo',
						'commentType');
				
				 <%if (MisUtility.ifEmpty(request.getAttribute("yearlyPlan"))) {%>
					document.getElementById('yearPlan').value="<%=request.getAttribute("yearlyPlan")%>";
					triggerEvent(document.getElementById("yearPlan"), 'onchange');
				 <%}%>
				 <%if (MisUtility.ifEmpty(request.getAttribute("month"))) {%>
					document.getElementById('month').value="<%=request.getAttribute("month")%>";
				<%}%>
				<%if (MisUtility.ifEmpty(request.getAttribute("monthlyPlanId"))) {%>
					document.getElementById('monthlyPlanId').value="<%=request.getAttribute("monthlyPlanId")%>";
			 	<%}%>
				triggerEvent(document.getElementById("month"),
								'onchange');

					});
	function remove11() {
		$('#saveMonthlyReportGridJsId>tbody>tr').each(function() {
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
	function ajaxVillageToBeVisited(url, e) {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST", url, false);
		xmlHttp.send(null);

		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				if (browser == 'Microsoft Internet Explorer') {
					var targetOption = document.getElementById(e);
					//targetOption.options.length = 0;

					select_innerHTML(targetOption, optionContent);
					//document.all[targetOption].innerHTML=optionContent;
				} else {
					var targetOption = document.getElementById(e);
					targetOption.value = optionContent;
				}
			}
		}
	}
	function ajaxFunction1(url, targetElementName) {
		var xmlHttp;
		var browser = navigator.appName;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("POST", url, false);
		xmlHttp.send(null);

		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var optionContent = xmlHttp.responseText;
				if (browser == 'Microsoft Internet Explorer') {
					var targetOption = document
							.getElementById(targetElementName);
					//targetOption.options.length = 0;

					select_innerHTML(targetOption, optionContent);
					//document.all[targetOption].innerHTML=optionContent;
				} else {
					var targetOption = document
							.getElementById(targetElementName);

					targetOption.innerHTML = optionContent;
				}
			}
		}
	}
</script>
</html:html>