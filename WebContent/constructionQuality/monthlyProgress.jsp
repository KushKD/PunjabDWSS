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
$(document).ready(function(){
	if(d__mode==='ent_update'){
		$("#gridMonthlyProgress").hide();
		$("#addDetails").hide();
	}
})

	function de_add() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.monthlyProgressForm.action = "enterMonthlyProgressAction.do?method=save&d__mode="
					+ d__mode + "&menuId=CQ007";
			document.monthlyProgressForm.submit();
		}
	}
	
function de_modify() {
	var result = true;
	//var status = validateFields();
	if (result) {
		document.monthlyProgressForm.action = "enterMonthlyProgressAction.do?method=update&d__mode="
				+ d__mode + "&menuId=CQ007";
		document.monthlyProgressForm.submit();
	}
}
	function de_find() {
		var result = true;
		//var status = validateFields();
		if (result) {
			document.monthlyProgressForm.action = "enterMonthlyProgressAction.do?method=find&d__mode="
					+ d__mode + "&menuId=CQ007";
			document.monthlyProgressForm.submit();
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

<html:form action="enterMonthlyProgressAction" method="post"
	styleId="monthlyProgressForm">

	<c:set var="component">
		<bean:message bundle="construction" key="component" />
	</c:set>
	<c:set var="componentName">
		<bean:message bundle="construction" key="component.name" />
	</c:set>
	<c:set var="componentId">
		<bean:message bundle="construction" key="component.id" />
	</c:set>

	<c:set var="villageVisited">
		<bean:message bundle="construction" key="No.Villages.Visited" />
	</c:set>
	<c:set var="planning">
		<bean:message bundle="construction" key="planning" />
	</c:set>
	<c:set var="implementation">
		<bean:message bundle="construction" key="implementation" />
	</c:set>
	<c:set var="postImplementation">
		<bean:message bundle="construction" key="post.implementation" />
	</c:set>

	<c:set var="team">
		<bean:message bundle="construction" key="team" />
	</c:set>
	<c:set var="teamId">
		<bean:message bundle="construction" key="team.id" />
	</c:set>
	<c:set var="teamName">
		<bean:message bundle="construction" key="team.name" />
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
					onchange="ajaxFunction('monthlyPlanInspectionAction.do?financialYearlyId='+this.value+'&method=fetchTeam','team');">
				</html:select>
			</div>
			<div class="form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign"><bean:message
						bundle="construction" key="month" /><span class="text-danger">
						&nbsp;*</span> </label>
				<html:select property="month" styleId="month" style="width: 150px;"
					styleClass="cs2 form-control">
				</html:select>

			</div>

			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">${component}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:select property="component" styleId="component"
					style="width: 150px;" styleClass="cs2 form-control">
				</html:select>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">${team}<span
					class="text-danger">&nbsp;*</span>
				</label>
				<html:select property="team" styleId="team" style="width: 150px;"
					styleClass="form-control text-left"
					onchange="ajaxVillageToBeVisited('enterMonthlyProgressAction.do?yearlyPlanId='+document.getElementById('yearPlan').value+'&monthId='+document.getElementById('month').value+'&componentId='+document.getElementById('component').value+'&teamId='+this.value+'&method=fetchVisitedVillage', 'villageToBeVisit');">
				</html:select>
			</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">${villageVisited}<span
					class="text-danger">&nbsp;*</span>
				</label>
				<html:text property="villageToBeVisit" styleId="villageToBeVisit"
					style="width: 150px;" styleClass="form-control text-left">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">${planning}<span
					class="text-danger"> &nbsp;*</span>
				</label>
				<html:text property="planning" styleId="planning"
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
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">${implementation}<span
					class="text-danger">&nbsp;*</span>
				</label>
				<html:text property="implementation" styleId="implementation"
					style="width: 150px;" styleClass="form-control text-left">
				</html:text>
			</div>
			<div class=" form-inline col-lg-4 col-md-5 col-xs-12 col-sm-6">
				<label class="text-left labledesign">${postImplementation}<span
					class="text-danger">&nbsp;*</span>
				</label>
				<html:text property="postImplementation"
					styleId="postImplementation" style="width: 150px;"
					styleClass="form-control text-left">
				</html:text>
			</div>
			<html:hidden property="componentName" styleId="componentName" />
			<html:hidden property="teamName" styleId="teamName" />
			<!-- ------------------------- -->

			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-4 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-2 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>
			<div class="col-lg-1 col-md-1 sm-hidden xs-hidden">&nbsp;
				&nbsp;</div>



			<button type="button" class="btn btn-primary" onclick='addDataGrid()' id='addDetails'
				style="width: 200px;">Add Detail</button>
		</div>
	</div>
	<div id="gridMonthlyProgress">
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
										property="monthlyProgressGrid" selectionAllowed="true"
										multipleSelectionAllowed="true" model="datagrid">

										<layout:datagridColumn property="progressId"
											title="Progress Id" mode="N,N,N"></layout:datagridColumn>
										<layout:datagridColumn property="componentId"
											title="${componentId}" mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="componentName"
											title="${componentName}" mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="villageVisited"
											title="${villageVisited}" mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="teamId" title="${teamId}"
											mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="teamName" title="${teamName}"
											mode="I,I,I"></layout:datagridColumn>
										<layout:datagridColumn property="planning" title="${planning}"
											mode="I,I,I"></layout:datagridColumn>

										<layout:datagridColumn property="implementation"
											title="${implementation}" mode="I,I,I"></layout:datagridColumn>

										<layout:datagridColumn property="postImplementation"
											title="${postImplementation}" mode="I,I,I"></layout:datagridColumn>


									</layout:datagrid>

								</div>
							</td>
							<td>
								<button title='Remove' type='button'
									class='btn btn-danger remove show_tip' id='removeRow'
									style="width: 50px;"
									onclick="StrutsLayout.setDatagridLineState('monthlyProgressGrid','removed');remove11();">
									<i class='fa fa-trash-o'></i>
								</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>


	<div id="monthlyProgressTbl1">

		<c:if test="${not empty monthlyProgressDtos}">
			<div class="panel panel-danger" id="revalidate">
				<div class="panel-body">
					<div class=" form-inline col-lg-1 col-md-1 col-xs-12 col-sm-12">&nbsp;&nbsp;</div>

					<div class=" form-inline col-lg-9 col-md-5 col-xs-12 col-sm-12">
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-highlight"
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
									<logic:iterate name="monthlyProgressDtos" id="monthlyPlanLst"
										property="monthlyProgressDtos" indexId="rowindex"
										type="com.prwss.min.construction.quality.bean.MonthlyProgressDto">
										<tr class="row">


											<html:hidden name="monthlyPlanLst"
												property="monthlyProgressId" indexed="true" />

											<html:hidden name="monthlyPlanLst" property="componentId"
												indexed="true" styleId='<%="componentIds" + i%>' />
											<html:hidden name="monthlyPlanLst" property="teamId"
												indexed="true" styleId='<%="teamIds" + i%>' />

											<td class='col-lg-2 col-xs-3' style='text-align: center'>
												<html:select name="monthlyPlanLst" property="componentName"
													indexed="true" styleId='<%="componentName" + i%>'
													style="width: 182px;text-align:center"
													styleClass=" form-control">	
												</html:select>
											</td>

											<td class='col-lg-2 col-xs-1'><html:text
													name="monthlyPlanLst" property="villageToBeVisited"
													indexed="true" styleClass="form-control"
													style="width:182px">
												</html:text></td>

											<td class='col-lg-2 col-xs-3'
												style='font-size: 0.9em; text-align: center'><html:select
													name="monthlyPlanLst" property="teamName"
													styleId='<%="teamName" + i%>' styleClass="cs2 form-control"
													style="width:185px" indexed="true">
												</html:select></td>
											<td class='col-lg-2 col-xs-1'><html:text
													name="monthlyPlanLst" property="planning" indexed="true"
													styleClass="cs2 form-control" style="width:182px">
												</html:text></td>
											<td class='col-lg-2 col-xs-1'><html:text
													name="monthlyPlanLst" property="implementation"
													indexed="true" styleClass="cs2 form-control"
													style="width:182px">
												</html:text></td>
											<td class='col-lg-2 col-xs-1'><html:text
													name="monthlyPlanLst" property="postimplementaion"
													indexed="true" styleClass="cs2 form-control"
													style="width:180px">
													
													<html:hidden name="monthlyPlanLst" property="monthlyPlanId"/>
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


</html:form>

</body>
<script type="text/javascript">

	
	function changeRowColor() {
		$('#monthlyProgressGridJsId>tbody>tr').each(function() {
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
		var status = validateGridField();
		if (status) {
			var componentName = $("#component option:selected").text();
			document.getElementById('componentName').value = componentName;

			var teamName = $("#team option:selected").text();
			document.getElementById('teamName').value = teamName;

			StrutsLayout
					.addDatagridLine(
							'monthlyProgressGrid',
							'component~componentName~villageToBeVisit~team~teamName~planning~implementation~postImplementation');
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
				
				 <%if (MisUtility.ifEmpty(request.getAttribute("yearlyPlan"))) {%>
					document.getElementById('yearPlan').value="<%=request.getAttribute("yearlyPlan")%>";
					triggerEvent(document.getElementById("yearPlan"), 'onchange');
				 <%}%>
				 <%if (MisUtility.ifEmpty(request.getAttribute("month"))) {%>
					document.getElementById('month').value="<%=request.getAttribute("month")%>";
<%}%>
	$("#monthlyPlanProgress >tbody>tr")
								.each(
										function() {
											var k = $(this).closest('tr')
													.index();
											var componentId = "componentIds"
													+ (k + 1);
											var componentName = "componentName"
													+ (k + 1);
											var teamId = "teamIds" + (k + 1);
											var teamName = "teamName" + (k + 1);
											ajaxFunction(
													'GetFilterValues.to?parentComboId=115&method=getCombo',
													componentName);
											document
													.getElementById(componentName).value = document
													.getElementById(componentId).value;
											ajaxFunction1(
													'monthlyPlanInspectionAction.do?financialYearlyId='
															+ document
																	.getElementById('yearPlan').value
															+ '&method=fetchTeam',
													teamName);
											document.getElementById(teamName).value = document
													.getElementById(teamId).value;
										});
	
	

					});
	function remove11() {
		$('#monthlyProgressGridJsId>tbody>tr').each(function() {
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

function updateComponentId(e){
	var componentIds = $(e).attr('id');
	alert(componentIds)
	var componentId = componentIds.split(/(\d+)/);
	alert(componentId[1]);
	
}
	function validateGridField() {
		var component = document.getElementById("component").value;
		var villageToBeVisit = document.getElementById('villageToBeVisit').value;
		var planning = document.getElementById('planning').value;
		var implementation = document.getElementById('implementation').value;
		var postImplementation = document.getElementById('postImplementation').value;
		if (component === '' || component === null) {
			alert('Please Select Component');
			return false;
		} else if (villageToBeVisit === '' || villageToBeVisit === null) {
			alert('Please Select VillageToBeVisit');
			return false;
		} else if (planning === '' || planning === null) {
			alert('Please Select Planning');
			return false;
		} else if (implementation === '' || implementation === null) {
			alert('Please Select Implementation');
			return false;
		} else if (postImplementation === '' || postImplementation === null) {
			alert('Please Select Post Implementation');
			return false;
		}
		return true;

	}

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