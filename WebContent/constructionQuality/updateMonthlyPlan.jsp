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
<link rel="stylesheet" type="text/css" href="css/jquery.datepick.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery.plugin.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.datepick.js"></script>
<script type="text/javascript">

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
			 <%if (MisUtility.ifEmpty(request.getAttribute("yearlyPlan"))) {%>
				document.getElementById('yearPlan').value="<%=request.getAttribute("yearlyPlan")%>";
				triggerEvent(document.getElementById("yearPlan"), 'onchange');
				<%if (MisUtility.ifEmpty(request.getAttribute("team"))) {%>
				document.getElementById('team').value="<%=request.getAttribute("team")%>";
			<%}%>
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("component"))) {%>
				document.getElementById('component').value="<%=request.getAttribute("component")%>";
				triggerEvent(document.getElementById("component"), 'onchange');
				<%if (MisUtility.ifEmpty(request.getAttribute("perMonthhVisit"))) {%>
				document.getElementById('visitPerMonth').value="<%=request.getAttribute("perMonthhVisit")%>";
	    	<%}%>
			<%}%>
			<%if (MisUtility.ifEmpty(request.getAttribute("month"))) {%>
				document.getElementById('month').value="<%=request.getAttribute("month")%>";
			<%}%>
	Req = "ent_frwrd";
						de_init('ent_frwrd', "test");
					});
	function de_modify() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.monthlyPlanInspectionForm.action = "updateMonthlyPlanAction.do?method=update&d__mode="
					+ d__mode + "&menuId=CQ005";
			document.monthlyPlanInspectionForm.submit();
		}
	}

	function de_find() {
		//var status = validateSearchFields();
		var status = true;
		if (status) {
			document.monthlyPlanInspectionForm.action = "updateMonthlyPlanAction.do?method=find&d__mode="
					+ d__mode + "&menuId=CQ005";
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

<html:form action="updateMonthlyPlanAction" method="post"
	styleId="monthlyPlanInspectionForm">

	<div class="panel panel-danger">
		<div class="panel-body">
			<h4
				class="text-on-pannel text-primary col-lg-12-col-xs-12 col-sm-12 col-md-12 center-block text-center">
				Monthly Plan for Inspection</h4>
			<div class="line"></div>
			<html:hidden property="monthlyPlanId" />
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="yearly.plan" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property='yearPlan' styleId='yearPlan'
					styleClass="form-control ci5" style="width: 150px;"
					onchange="ajaxFunction('monthlyPlanInspectionAction.do?financialYearlyId='+this.value+'&method=fetchTeam','team');">
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
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-12">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="team" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="team" styleId="team" style="width: 150px;"
					styleClass=" form-control">
				</html:select>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-12 col-md-12 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-3 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-5 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" onclick="de_find()"
					style="width: 200px;">Populate Record</button>
			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="village.visited" /><span
					class="text-danger"> &nbsp;*</span> </label>
				<html:select property="visitPerMonth" styleId="visitPerMonth"
					style="width: 150px;" styleClass="cs2 form-control" disabled="true">
				</html:select>
			</div>
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


		</div>
	</div>

	<c:if test="${not empty monthlyPlanDtos}">
		<div class="panel panel-danger" id="revalidate">
			<div class="panel-body">

				<div class="col-lg-9 col-md-2 sm-hidden xs-hidden">&nbsp;
					&nbsp;</div>

				<div class=" form-inline col-lg-12 col-md-5 col-xs-12 col-sm-12">
					<div class="table-responsive">
						<table class="table table-bordered table-striped table-highlight"
							id="monthlyPlan" style='display: block;'>
							<thead style='display: block;' class='thead-dark'>
								<tr class="row">
									<th class='col-lg-3 col-xs-3' style='text-align: center'>District</th>
									<th class='col-lg-3 col-xs-3' style='text-align: center'>Division</th>
									<th class='col-lg-3 col-xs-3' style='text-align: center'>Scheme</th>
									<th class='col-lg-3 col-xs-3' style='text-align: center'>Constituency</th>

								</tr>
							</thead>


							<tbody id='fnlTable' style='display: block;'>
								<%
									int i = 1;
								%>
								<logic:iterate name="monthlyPlanDtos" id="monthlyPlanLst"
									property="monthlyPlanDtos" indexId="rowindex"
									type="com.prwss.min.construction.quality.bean.MonthlyPlanDto">
									<tr class="row">

										<html:hidden name="monthlyPlanLst" property="divisionId"
											styleId='<%="div" + i%>' indexed="true" />

										<html:hidden name="monthlyPlanLst" property="schemeId"
											styleId='<%="schm" + i%>' indexed="true" />
										<html:hidden name="monthlyPlanLst"
											property="monthly_plan_scheme_id" indexed="true" />

										<html:hidden name="monthlyPlanLst" property="constituencyId"
											styleId='<%="const" + i%>' indexed="true" />

										<td class='col-lg-3 col-xs-1' id='<%="districts" + i%>'><html:select
												name="monthlyPlanLst" property="district"
												styleId='<%="district" + i%>' indexed="true"
												styleClass="cs2 form-control"
												onchange="getDivision('GetFilterValues.to?division='+this.value+'&method=fetchDivision',this);
												ajaxConstituencyFunction('GetFilterValues.to?constituency='+this.value+'&method=fetchConstituency',this);
												
												">
												<html:option value="">Select Location</html:option>
												<html:options collection="districtLocations"
													labelProperty="label" property="value"></html:options>
											</html:select></td>

										<td class='col-lg-3 col-xs-3' style='text-align: center'>
											<html:select name="monthlyPlanLst" property="division"
												indexed="true" styleId='<%="division" + i%>'
												styleClass="cs2 form-control"
												onchange="ajaxSchemeFunction('GetFilterValues.to?divisionIds='+this.value+'&method=fetchSchemeByDivisionId',this);">
											</html:select>
										</td>
										<td class='col-lg-3 col-xs-3'
											style='font-size: 0.9em; text-align: center'><html:select
												name="monthlyPlanLst" property="scheme"
												styleId='<%="scheme" + i%>' styleClass="cs2 form-control"
												style="width: 150px;" indexed="true">
											</html:select></td>
										<td class='col-lg-3 col-xs-3'
											style='font-size: 0.9em; text-align: center'><html:select
												property="constituency" styleId='<%="constituency" + i%>'
												name="monthlyPlanLst" styleClass="cs2 form-control"
												indexed="true">
											</html:select></td>

									</tr>
									<%
										i++;
									%>
								</logic:iterate>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-5 col-md-4 sm-hidden xs-hidden">&nbsp;</div>
		<div class="col-lg-4 col-md-4 sm-hidden xs-hidden">
				<button type="button" class="btn btn-primary" onclick="de_modify()"
					style="width: 200px;">Update Record</button>
			</div>
			<div class="col-lg-3 col-md-4 sm-hidden xs-hidden">&nbsp;</div>
	</c:if>
</html:form>

</body>
<script type="text/javascript">
	$("#monthlyPlan >tbody>tr")
			.each(
					function() {
						var k = $(this).closest('tr').index();
						var dstrct = "district" + (k + 1);
						triggerEvent(document.getElementById(dstrct),
								'onchange');
						if (document.getElementById('div' + (k + 1)).value != '') {
							document.getElementById("division" + (k + 1)).value = document
									.getElementById('div' + (k + 1)).value;
						}
						var division = "division" + (k + 1)
						triggerEvent(document.getElementById(division),
								'onchange');
						if (document.getElementById('schm' + (k + 1)).value != '') {
							document.getElementById("scheme" + (k + 1)).value = document
									.getElementById('schm' + (k + 1)).value;
						}

						var division = "division" + (k + 1)
						triggerEvent(document.getElementById(division),
								'onchange');
						if (document.getElementById('schm' + (k + 1)).value != '') {
							document.getElementById("scheme" + (k + 1)).value = document
									.getElementById('schm' + (k + 1)).value;
						}

						var scheme = "scheme" + (k + 1)
						triggerEvent(document.getElementById(scheme),
								'onchange');
						if (document.getElementById('const' + (k + 1)).value != '') {
							document.getElementById("constituency" + (k + 1)).value = document
									.getElementById('const' + (k + 1)).value;
						}
					});

	
	function getDivision(url, e) {
		var distIds = $(e).attr('id');
		myArray = distIds.split(/(?=\d+)/);
		var divisionn = "division" + myArray[1];
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
					var targetOption = document.getElementById(divisionn);
					//targetOption.options.length = 0;

					select_innerHTML(targetOption, optionContent);
					//document.all[targetOption].innerHTML=optionContent;
				} else {
					var targetOption = document.getElementById(divisionn);
					targetOption.innerHTML = optionContent;
				}
			}
		}
	}

	function ajaxSchemeFunction(url, e) {
		var distIds = $(e).attr('id');
		myArray = distIds.split(/(?=\d+)/);
		var schemes = "scheme" + myArray[1];
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
					var targetOption = document.getElementById(schemes);
					//targetOption.options.length = 0;

					select_innerHTML(targetOption, optionContent);
					//document.all[targetOption].innerHTML=optionContent;
				} else {
					var targetOption = document.getElementById(schemes);
					targetOption.innerHTML = optionContent;
				}
			}
		}
	}

	function ajaxConstituencyFunction(url, e) {
		var distIds = $(e).attr('id');
		myArray = distIds.split(/(?=\d+)/);
		var constituencys = "constituency" + myArray[1];
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
					var targetOption = document.getElementById(constituencys);
					//targetOption.options.length = 0;

					select_innerHTML(targetOption, optionContent);
					//document.all[targetOption].innerHTML=optionContent;
				} else {
					var targetOption = document.getElementById(constituencys);
					targetOption.innerHTML = optionContent;
				}
			}
		}
	}
</script>

</html:html>